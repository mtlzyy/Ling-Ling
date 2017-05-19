package com.music.controller.personal;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.music.dao.service.FavAlbumService;
import com.music.dao.service.FavListService;
import com.music.dao.service.FavSongService;
import com.music.dao.service.ListAndSongService;
import com.music.dao.service.SingUserService;
import com.music.email.SendMail;
import com.music.pojo.FavAlbum;
import com.music.pojo.FavList;
import com.music.pojo.FavSong;
import com.music.pojo.SingUser;
import com.music.util.CommonUtil;
import com.music.util.ReqRespSupport;

@Controller
@RequestMapping("personal")
@SessionAttributes("loginUser")
public class PersonalController {
	private ModelAndView mav = new ModelAndView();
	@Autowired
	private SingUserService sus;
	@Autowired
	private FavSongService fss;
	@Autowired
	private FavAlbumService fas;
	@Autowired
	private FavListService fls;
	@Autowired
	private ListAndSongService lss;
	
	private SingUser curUser;
	
	@RequestMapping("test")
	public ModelAndView test(){
		//SingUser u = sus.findUserByUserName("zyy");
		SingUser u =(SingUser)ReqRespSupport.getSession().getAttribute("loginUser");
		curUser = u;
		int uid = u.getUserID();
		List<FavSong> favsongs = fss.findAllFavSongByUser(uid);
		List<FavAlbum> favAlbums = fas.findFavAlbumByUser(uid);
		List<FavList> favList = fls.findFavListByUser(uid);
		int fsmp;
		if(favsongs==null){
			fsmp = 0;
		}else{
			fsmp = CommonUtil.findMaxPage(favsongs.size(), 10);
		}
		int famp;
		if(favAlbums==null){
			famp = 0;
		}else{
			famp = CommonUtil.findMaxPage(favAlbums.size(),10);
		}
		int flmp;
		if(favList==null){
			flmp = 0;
		}else{
			flmp = CommonUtil.findMaxPage(favList.size(),10);
		}
		//返回收藏的各项的最大页数
		mav.addObject("fsMaxPage", fsmp);
		mav.addObject("flMaxPage", flmp);
		mav.addObject("faMaxPage", famp);
		//初始界面为收藏--》收藏歌曲（歌单）
		mav.addObject("curMode", "fav");
		mav.addObject("favMode", "fs");
		//
		mav.addObject("loginUser", u);
		//初始页码1
		mav.addObject("pageNo", 1);
		//初始最大页
		mav.addObject("maxPage", fsmp);

		mav.setViewName("personalMain");
		return mav;
	}
	
	@RequestMapping("main")
	public ModelAndView getToMain(){
		mav.setViewName("personalMain");
		return mav;
	}
	@RequestMapping("money")
	public ModelAndView goToPay(){
		mav.setViewName("personal/money");
		return mav;
	}
	
	@RequestMapping("menu")
	public ModelAndView switchMenu(@RequestParam("mode")String mode){
		System.out.println(mode);
		List<FavSong> favsongs = fss.findAllFavSongByUser(curUser.getUserID());
		System.out.println("进入menu.do");
		List<FavSong> flist = fss.findFavSongByUserIdWithPage(curUser.getUserID(), 1);
		int maxPage;
		if(favsongs==null){
			maxPage = 0;
		}
		else{
			int listSize = favsongs.size();
			maxPage = CommonUtil.findMaxPage(listSize, 10);
		}	
		if(mode.equals("fav")){
			mav.addObject("resultList", flist);
			mav.addObject("pageNo", 1);
			mav.addObject("maxPage", maxPage);
			mav.setViewName("personal/myFav");
		}
		else if(mode.equals("psn")){
			mav.setViewName("personal/personalInfo");
		}
		else if(mode.equals("btm")){
			mav.setViewName("../page/main");
		}
		else{
			mav.setViewName("failure");
		}
		return mav;
	}
	
	@RequestMapping("cinfo")
	public ModelAndView changeInfo(@RequestParam("nn")String nkName,@RequestParam("em")String email,@RequestParam("pw")String pwd){
		System.out.println("进入changeinfo");
		sus.updateUserExEmail(nkName, pwd, curUser.getUserID());
		System.out.println("除EMAIL外更新结束");
		if(email!=null){
			if(!curUser.getEmail().equals(email)){
				SendMail sm = new SendMail();
				Date d = new Date();
				long x = d.getTime();
				String eswlink = "http://localhost:8080/MusicWeb/personal/switchemail.do?d="+x+"&e="+email+"&u="+curUser.getUserID();
				sm.sendActivationMail(email, eswlink);
			}
		}
		curUser = refreshUser(curUser.getUserID());
		//刷新
		mav.setViewName("personal/personalInfo");
		return mav;
	}
	
	@RequestMapping("switchemail")
	public ModelAndView swEmail(@RequestParam("d")String d,@RequestParam("e")String em,@RequestParam("u")String u){
		long x = Long.parseLong(d);
		long y = (new Date()).getTime();
		if(x-y>1000*60*60*24*2){
			mav.addObject("linkVf", "链接已失效");
			mav.addObject("sf", "failure");
		}
		else{
			int userid = Integer.parseInt(u);
			SingUser uzer = sus.findUserByEmail(em);
			if(uzer!=null){
				mav.addObject("linkVf", "邮箱已被注册");
				mav.addObject("sf", "failure");
			}
			else{
				sus.updateEmail(userid, em);
				mav.addObject("linkVf", "邮箱修改成功");
				mav.addObject("sf", "success");
				curUser = refreshUser(userid);
			}
		}
		mav.setViewName("personal/email_changed");
		return mav;
	}
	@RequestMapping("sw")
	public ModelAndView switchPageForFs(@RequestParam("page")String pg,@RequestParam("mp")String mpg,@RequestParam("favMode")String favMode){
		int page = Integer.parseInt(pg);
		int maxPage = Integer.parseInt(mpg);
		System.out.println("xxxxenret");
		if(favMode.equals("fs")){
			System.out.println("fs");
			mav.addObject("resultList", fss.findFavSongByUserIdWithPage(curUser.getUserID(), page));
		}
		else if(favMode.equals("fa")){
			mav.addObject("resultList", fas.findFavAlbumInfoWithIdAndPage(curUser.getUserID(), page));
		}
		else if(favMode.equals("fl")){
			mav.addObject("resultList",fls.findFavSongsByIdAndPage(curUser.getUserID(), page));
		}
		mav.addObject("pageNo", page);
		mav.addObject("maxPage", maxPage);
		mav.addObject("favMode", favMode);
		mav.setViewName("personal/myFav_fx");
		return mav;
	}
	@RequestMapping("deleteFav")
	public ModelAndView deleteFav(@RequestParam("favMode")String favMode,@RequestParam("mp")String mp,@RequestParam("page")String pg,@RequestParam("fId")String favId){
		int oldMp = Integer.parseInt(mp);
		int page = Integer.parseInt(pg);
		int fid = Integer.parseInt(favId);
		int newMp;
		int maxPage=-1;
		int userid = curUser.getUserID();
		if(favMode.equals("fs")){
			//fss.deleteFavSongById(fid);
			fss.deleteOwnFavSongById(fid);
			newMp = CommonUtil.findMaxPageForList(fss.findAllFavSongByUser(userid), 10);
			if(CommonUtil.maxPageChange(page, oldMp, newMp)){
				maxPage = newMp;
				mav.addObject("resultList", fss.findFavSongByUserIdWithPage(userid, page-1));
			}
			else{
				mav.addObject("resultList", fss.findFavSongByUserIdWithPage(userid, page));
			}	
			mav.addObject("fsMP", maxPage);
		}
		else if(favMode.equals("fa")){
			fas.deleteFavAlbumByIds(userid, fid);
			newMp = CommonUtil.findMaxPageForList(fas.findFavAlbumByUser(userid), 10);
			if(CommonUtil.maxPageChange(page, oldMp, newMp)){
				maxPage = newMp;
				mav.addObject("resultList", fas.findFavAlbumInfoWithIdAndPage(userid, page-1));
			}
			else{
				mav.addObject("resultList", fas.findFavAlbumInfoWithIdAndPage(userid, page));
			}
			mav.addObject("faMP", maxPage);
		}
		else if(favMode.equals("fl")){
			fls.deleteFavListByIds(userid, fid);
			newMp = CommonUtil.findMaxPageForList( fls.findFavListByUser(userid), 10);
			if(CommonUtil.maxPageChange(page, oldMp, newMp)){
				maxPage = newMp;
				mav.addObject("resultList", fls.findFavSongsByIdAndPage(userid, page-1));
			}
			else{
				mav.addObject("resultList", fls.findFavSongsByIdAndPage(userid, page));
			}
			mav.addObject("flMP", maxPage);
		}
		/*else{
			
		}*/
		mav.addObject("favMode", favMode);
		mav.setViewName("personal/myFav_fx");
		return mav;
	}
	
	@RequestMapping("delFav")
	public ModelAndView deleteFavFromList(@RequestParam("sid")int sid,@RequestParam("fid")int fid){
		lss.removeSongFromFavSong(fid, sid);
		mav.addObject("favsongId", fid);
		mav.setView(new RedirectView("../playlist.do"));
		return mav;
	}
	//__________________________________________________________________________________________________
	public SingUser refreshUser(int userid){
		 SingUser uzer = sus.findUserByUserid(userid);
		 mav.addObject("loginUser", uzer);
		 return uzer;
	}
	
	@ExceptionHandler
	public ModelAndView errorPage(){
		mav.setViewName("exception");
		return mav;
	}
	
}
