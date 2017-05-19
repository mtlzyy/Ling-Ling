package com.music.controller.main;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.MainService;
import com.music.dao.service.SingUserService;
import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.Recommand;
import com.music.pojo.SingUser;
import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;



@Controller
@RequestMapping(value="main")
public class MainController {
	@Autowired
	private MainService mainService;
	@Autowired
	private SingUserService sus;
	
	@RequestMapping("init")
	public ModelAndView mainPage(){
		ModelAndView mav = new ModelAndView();
		List<Recommand> recommandList = mainService.findRecommand();
		List<Album> albumList = mainService.findHotAl();
		List<FavSong> favSongList = mainService.findHotFav();
		List<Songs> hotSongsList = mainService.findHotMusic();
		List<Songs> newSongList = mainService.findNewMusic();
		HttpServletRequest req = ReqRespSupport.getReq();
		HttpSession ss = ReqRespSupport.getSession();
		System.out.println(ss.getAttribute("loginUser"));
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				if(c.getName().equals("userName")){
					SingUser u = sus.findUserByUserName(c.getName());
					ss.setAttribute("loginUser", u);
					break;
				}
			}
		}	
		mav.setViewName("main");
		mav.addObject("recommand", recommandList);
		mav.addObject("album", albumList);
		mav.addObject("favSong", favSongList);
		mav.addObject("hotSongs", hotSongsList);
		mav.addObject("newSongs", newSongList);
		return mav;
	}
}
