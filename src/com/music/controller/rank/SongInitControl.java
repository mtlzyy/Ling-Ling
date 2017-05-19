package com.music.controller.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.SongRankService;
import com.music.pojo.Songs;

@Controller
public class SongInitControl {
	@Autowired
	private SongRankService rankService;
	@RequestMapping("rankinit")
	public ModelAndView initRank(String page){
		ModelAndView mav = new ModelAndView();
		List<String> languageList = rankService.findSongLanguage();
		int pageNo;
		if(page == null){
			pageNo = 1;
		}else{
			pageNo	 = Integer.parseInt(page);	
		}
		List<Songs> songsList = rankService.rankSongs(pageNo,"clickcount","");
		System.out.println("init"+pageNo);
		int maxpage = rankService.rankCount("");
		mav.addObject("pageNo", pageNo);
		mav.addObject("maxPage", maxpage);
		mav.setViewName("RankList");
		mav.addObject("songsList", songsList);
		mav.addObject("language", languageList);
		return mav;
	}
}
