package com.music.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.music.dao.FavListDao;
import com.music.dao.FavSongDao;
import com.music.dao.ListAndSongDao;
import com.music.dao.SingUserDao;
import com.music.dao.SongsDao;
import com.music.dao.service.AlbumService;
import com.music.dao.service.FavSongService;
import com.music.pojo.FavList;
import com.music.pojo.FavSong;
import com.music.pojo.SingUser;
import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;

@Controller
public class FavSongController {
	@Autowired
	private FavSongDao favSongDao;
	@Autowired
	private SongsDao songsDao;
	@Autowired
	private FavListDao favListDao;
	@Autowired
	private ListAndSongDao listAndSongDao;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("favsong")
	public ModelAndView forwardFavsong(String page,String flag){
		ModelAndView mav=new ModelAndView();
		//��תfasong.jspҳ��
		mav.setViewName("favsong");
		//��ǰҳ��
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		//���£����ȱ�ʶ
		if(flag==null){
			flag="hot";
		}
		int size=15;
		//���ݿ���ʼ����
		int start=(pageNo-1)*size+1;
		//���ݿ��������
		int end=pageNo*size;
		//favsong����������
		int total=favSongDao.findFavSongCount();
		//��ҳ��
		int max=total/size;
		if(max%size!=0){
			max++;
		}
		mav.addObject("flag", flag);
		mav.addObject("page", pageNo);
		mav.addObject("max", max);
		List<FavSong> list=new ArrayList<FavSong>();
		if(flag.equals("hot")){
			list=favSongDao.findFavSongPage(start, end);
		}else{
			list=favSongDao.findNewFavSongPage(start, end);
		}
		
		for (FavSong favSong : list) {
			favSong.getFavsongName();
		}
		mav.addObject("listCount",list.size());
		mav.addObject("favSongList",list);
		return mav;
	}
	
	@RequestMapping("playlist")
	public ModelAndView forwardPlaylist(String favsongId){
		//�����õ�loginUser
		SingUser loginUser= (SingUser) ReqRespSupport.getSession().getAttribute("loginUser");
		ModelAndView mav=new ModelAndView();
		//��ת��playlist.jsp
		mav.setViewName("playlist");
		int id=Integer.parseInt(favsongId);
		FavSong favSong=favSongDao.findFavSongById(id);
		//
		int clickCount=favSong.getClickCount();
		clickCount+=1;
		favSongDao.addFavSongClickCount(clickCount, id);
		//
		favSong=favSongDao.findFavSongById(id);
		mav.addObject("favSong", favSong);
		//�赥�и���
		List<Songs> list=songsDao.findFavSongsByFavSongId(id);
		//�Ƿ��ղع��ø赥,0δ�ղأ�1�ղ�
		int flag=0;
		if(loginUser!=null){
			List<FavSong> favSongList=favSongDao.findFavSongListByUserId(loginUser.getUserID());
			mav.addObject("favSongList", favSongList);
			mav.addObject("listSize", favSongList.size());
			FavList fav=favListDao.findFavListByFavSongIdAndUserId(id,loginUser.getUserID());
			if(fav!=null){
				flag=1;
			}
		}
		
		mav.addObject("flag", flag);
		mav.addObject("playList", list);
		return mav;
	}
	@RequestMapping("addFavlist")
	public void addFavlist(String favsongId,String userId){
		int favId=Integer.parseInt(favsongId);
		int uId=Integer.parseInt(userId);
		favListDao.addFavlist(favId, uId);
	}
	@RequestMapping("deleteFavlist")
	public void deleteFavlist(String favsongId,String userId){
		int favId=Integer.parseInt(favsongId);
		int uId=Integer.parseInt(userId);
		favListDao.deleteFavlist(favId, uId);
	}
	@ResponseBody
	@RequestMapping("addSingle")
	public String addSingleToFavSong(String favsongId,String songId){
		int favId=Integer.parseInt(favsongId);
		int sId=Integer.parseInt(songId);
		String info="";
		//�Ƿ���ӹ��õ������ø赥
		if(listAndSongDao.findByFavIdAndSongId(favId, sId)==null){
			listAndSongDao.addListAndSong(favId, sId);
			info="�ɹ���Ӹ���";
		}else{
			info="����ӹ��ø���";
		}
		return info;
	}
	@RequestMapping("createList2")
	public ModelAndView createList(String listName,String favsongId,String songId,String userId) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView();
		byte[] bytes=listName.getBytes("ISO-8859-1");
		listName=new String(bytes, "utf-8");
		int fid=Integer.parseInt(favsongId);
		int sid=Integer.parseInt(songId);
		int uid=Integer.parseInt(userId);
		mav.setView(new RedirectView("playlist.do?favsongId="+fid));
		System.out.println(listName);
		albumService.addFavSong(listName, uid);
		FavSong favSong=albumService.findFavsongByfavSongNameAndUserid(listName, uid);
		albumService.addListAndSong(favSong.getFavsongID(), sid);
		return mav;
	}
	@RequestMapping("addTest")
	public ModelAndView addTest(String songId){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("NewFavSong");
		mav.addObject("songId", songId);
		return mav;
	}
}