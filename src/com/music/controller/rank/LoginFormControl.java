package com.music.controller.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.SongRankService;
import com.music.pojo.FavSong;

@Controller
@RequestMapping(value="rank")
public class LoginFormControl {
	@Autowired
	private SongRankService rankService;
	@RequestMapping("loginform")
	public ModelAndView show(String userID){
		ModelAndView mav = new ModelAndView();
		List<FavSong> favSongList = null;
		if(userID != null){
			int user = Integer.parseInt(userID);
			favSongList = rankService.findLoginForm(user);
		}
		mav.setViewName("ajax/LoginForm");
		mav.addObject("favSongList", favSongList);
		return mav;
	}
	@RequestMapping("addSong")
	public ModelAndView addsong(String userID,String songid){
		
		ModelAndView mav = new ModelAndView();
		List<FavSong> favSongList = null;
		if(userID != null){
			int user = Integer.parseInt(userID);
			favSongList = rankService.findLoginForm(user);
		}
		mav.setViewName("ajax/addsong");
		mav.addObject("favSongList", favSongList);
		mav.addObject("songID", songid);
		return mav;
	}
}
