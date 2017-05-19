package com.music.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.music.dao.service.AlbumService;
import com.music.pojo.Album;
import com.music.pojo.FavAlbum;
import com.music.pojo.FavSong;
import com.music.pojo.SingUser;
import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;

@Controller
public class AlbumController {
	@Autowired
	AlbumService albumService;
	@RequestMapping("album")
	public ModelAndView forwardAlbum(String page,String flag){
		ModelAndView mav=new ModelAndView();
		//��תalbum.jspҳ��
		mav.setViewName("album");
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
		//album����������
		int total=albumService.findAllAlbumCount();
		//��ҳ��
		int max=total/size;
		if(max%size!=0){
			max++;
		}
		mav.addObject("flag", flag);
		mav.addObject("page", pageNo);
		mav.addObject("max", max);
		List<Album> list=new ArrayList<Album>();
		if(flag.equals("hot")){
			list=albumService.findAllAlbumPage(start, end, "hot");
		}else{
			list=albumService.findAllAlbumPage(start, end, "new");
		}
		mav.addObject("listCount",list.size());
		mav.addObject("albumList",list);
		return mav;
	}
	@RequestMapping("albumSong")
	public ModelAndView forwardPlaylist(String albumId){
		
		//�õ�session��loginUser
		SingUser loginUser=(SingUser)ReqRespSupport.getSession().getAttribute("loginUser");
		//
		ModelAndView mav=new ModelAndView();
		//��ת��albumSong.jsp
		mav.setViewName("albumSong");
		int id=Integer.parseInt(albumId);
		Album album=albumService.findAlbumByAlbumId(id);
		//
		int clickCount=album.getClickCount();
		clickCount+=1;
		albumService.addAlbumClickCount(clickCount, id);
		//
		albumService.findAlbumByAlbumId(id);
		mav.addObject("album", album);
		//ר���и���
		List<Songs> list=albumService.findAlbumSongsByAlbumId(id);
		//�Ƿ��ղع��ø赥,0δ�ղأ�1�ղ�
		int flag=0;
		if(loginUser!=null){
			List<FavSong> favSongList=albumService.findFavSongListByUserId(loginUser.getUserID());
			mav.addObject("favSongList", favSongList);
			mav.addObject("listSize", favSongList.size());
			FavAlbum fav=albumService.findFavAlbumByAlbumIdandUserId(id, loginUser.getUserID());
			if(fav!=null){
				flag=1;
			}
		}
		
		mav.addObject("flag", flag);
		mav.addObject("playList", list);
		return mav;
	}
	@RequestMapping("addFavAlbum")
	public void addFavlist(String albumId,String userId){
		int aId=Integer.parseInt(albumId);
		int uId=Integer.parseInt(userId);
		albumService.addFavAlbum(aId, uId);
	}
	@RequestMapping("deleteFavAlbum")
	public void deleteFavAlbum(String albumId,String userId){
		int aId=Integer.parseInt(albumId);
		int uId=Integer.parseInt(userId);
		albumService.deleteFavAlbum(aId, uId);
	}
	@RequestMapping("createList")
	public ModelAndView createList(String listName,String albumId,String songId,String userId) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView();
		byte[] bytes=listName.getBytes("ISO-8859-1");
		listName=new String(bytes, "utf-8");
		int aid=Integer.parseInt(albumId);
		int sid=Integer.parseInt(songId);
		int uid=Integer.parseInt(userId);
		mav.setView(new RedirectView("albumSong.do?albumId="+aid));
		System.out.println(listName);
		albumService.addFavSong(listName, uid);
		FavSong favSong=albumService.findFavsongByfavSongNameAndUserid(listName, uid);
		albumService.addListAndSong(favSong.getFavsongID(), sid);
		return mav;
	}
}
