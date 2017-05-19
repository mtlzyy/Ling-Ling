package com.music.controller.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.SongRankService;
import com.music.pojo.Songs;

@Controller
@RequestMapping(value="rank")
public class SongRankController {
	@Autowired
	private SongRankService rankService;
	@RequestMapping("rank")
	public ModelAndView songRankShow(String page,String order,String languages){
		ModelAndView mav = new ModelAndView();
		int pageNo;
		if(page == null){
			pageNo = 1;
		}else{
			pageNo	 = Integer.parseInt(page);	
		}
		System.out.println("rank"+pageNo);
		int maxpage = rankService.rankCount(languages);
		List<Songs> songsList =rankService.rankSongs(pageNo,order,languages);
		mav.setViewName("AjaxRank");
		mav.addObject("songsList", songsList);
		mav.addObject("languages", languages);
		mav.addObject("pageNo", pageNo);
		mav.addObject("maxPage", maxpage);
		return mav;
	}
}
