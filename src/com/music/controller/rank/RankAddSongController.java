package com.music.controller.rank;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.controller.rank.util.RankUtil;
import com.music.dao.service.RankAddSongService;
import com.music.pojo.ListAndSong;

@Controller
@RequestMapping(value="rank")
public class RankAddSongController {
	@Autowired
	private RankAddSongService addSongService;
	@RequestMapping("add")
	public ModelAndView addSong(String arrChk,String favsongID){
		List list = RankUtil.change(arrChk);
		int favsongid = Integer.parseInt(favsongID);
		List<ListAndSong> ListAndSong = addSongService.findRepeat(favsongid);
		System.out.println(favsongID);
		boolean flag=true;
		for (Object obj : list) {
			int value = Integer.parseInt(obj.toString());
			for (ListAndSong listAndSong2 : ListAndSong) {
				if(listAndSong2.getSongid()==value){
					flag = false;
					break;
				}
			}
			if(flag==true){
				addSongService.updateListAndSong(favsongid, value);
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page/success");
		return mav;
	}
	@RequestMapping("addsong")
	public ModelAndView add(String favsongID,String songID){
		int favsongid = Integer.parseInt(favsongID);
		int value = Integer.parseInt(songID);
		boolean flag=true;
		List<ListAndSong> ListAndSong = addSongService.findRepeat(favsongid);
		for (ListAndSong listAndSong2 : ListAndSong) {
			if(listAndSong2.getSongid()==value){
				flag = false;
				break;
			}
		}
		if(flag == true){
			addSongService.updateListAndSong(favsongid, value);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page/success");
		return mav;
	}
}
