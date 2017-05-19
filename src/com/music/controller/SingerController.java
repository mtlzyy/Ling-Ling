package com.music.controller;


import java.util.ArrayList;
import java.util.List;



import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.music.dao.service.AlbumService;
import com.music.dao.service.FavSongService;
import com.music.dao.service.ListAndSongService;
import com.music.dao.service.SingerService;
import com.music.dao.service.SongsService;
import com.music.form.SingersForm;

import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.Singer;
import com.music.pojo.Songs;

@Controller
@RequestMapping("singer")
public class SingerController {
	@Autowired
	private SingerService singerService;
	@Autowired
	private SongsService songsService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private FavSongService favSongService;
	@Autowired
	private ListAndSongService listAndSongService;
	@RequestMapping("singers")
	public ModelAndView singers(SingersForm singersForm){
		ModelAndView mav = new ModelAndView();
		if(singersForm.getNation()!=null&&singersForm.getNation().equals("null")){
			singersForm.setNation(null);
		}
		if(singersForm.getBeginEn()!=null&&singersForm.getBeginEn().equals("null")){
			singersForm.setBeginEn(null);
		}
		if(singersForm.getPageNo()==null){
			singersForm.setPageNo("1");
		}
		if(singersForm.getSex()==null){
			singersForm.setSex("-1");
		}
		int pageNo = Integer.parseInt(singersForm.getPageNo());
		List<Singer> singerList = singerService.findSingers(singersForm.getBeginEn(),singersForm.getNation(),Integer.parseInt(singersForm.getSex()) ,pageNo);
		int max = singerService.singersCount(singersForm.getBeginEn(),singersForm.getNation(),Integer.parseInt(singersForm.getSex()))/15;
		if(max%15!=0){
			max+=1;
		}
		mav.addObject("singerList", singerList);
		mav.addObject("maxPage", max);
		mav.addObject("pageNo",pageNo);
		mav.setViewName("singer/singers");
		return mav;
	}
	
	@RequestMapping("singersMain")
	public ModelAndView mainPage(){
		ModelAndView mav = new ModelAndView();
		List<String> nationList = singerService.findNation();
		List<String> beginEnList = singerService.findBeginEn();
		mav.addObject("nationList", nationList);
		mav.addObject("beginEnList", beginEnList);
		mav.setViewName("singer/singersMain");
		return mav;
	}
	
	@RequestMapping("singer")
	public ModelAndView singer(String singerId){
		ModelAndView mav =new ModelAndView();
		int sid = Integer.parseInt(singerId);
		Singer singer = singerService.findSinger(sid);
		mav.addObject("singer", singer);
		mav.setViewName("singer/singer");
		return mav;
	}
	
	@RequestMapping("singerAjax")
	public ModelAndView singerAjax(String singerId){
		ModelAndView mav = new ModelAndView();
		int sid = Integer.parseInt(singerId);
		Singer singer = singerService.findSinger(sid);
		List<Songs> songsList = songsService.findBySingerIdPage(sid, 1, "Hot",10);
		List<Album> albumList = albumService.findBySingerIdPage(sid, 1, "Hot", 5);
		int songsNum = songsService.findCountBySingerId(sid);
		int albumNum = albumService.findCountBySingerId(sid);
		mav.addObject("singer", singer);
		mav.addObject("albumList", albumList);
		mav.addObject("songsList",songsList );
		mav.addObject("songsNum", songsNum);
		mav.addObject("albumNum", albumNum);
		mav.setViewName("singer/singerAjax");
		return mav;
	}
	
	
	
	@RequestMapping("songsAjax")
	public ModelAndView songsAjax(String singerId,String pageNo,String type){
		ModelAndView mav = new ModelAndView();
		int sid = Integer.parseInt(singerId);
		int page;
		if(pageNo==null){
			page=1;
		}
		else{
			page=Integer.parseInt(pageNo);
		}
		if(type==null){
			type="Hot";
		}
		int size=20;
		
		List<Songs> songsList = songsService.findBySingerIdPage(sid, page, type, size);
		int songsNum = songsService.findCountBySingerId(sid);
		int maxPage = songsNum/size;
		if(songsNum%size!=0){
			maxPage++;
		}
		mav.addObject("type", type);
		mav.addObject("songsNum",songsNum);
		mav.addObject("maxPage", maxPage);
		mav.addObject("pageNo", page);
		mav.addObject("songsList",songsList);
		mav.setViewName("singer/songsAjax");
		return mav;
	}
	
	@RequestMapping("albumsAjax")
	public ModelAndView albumsAjax(String singerId,String pageNo,String type){
		ModelAndView mav = new ModelAndView();
		int sid = Integer.parseInt(singerId);
		int page;
		if(pageNo==null){
			page=1;
		}
		else{
			page=Integer.parseInt(pageNo);
		}
		if(type==null){
			type="Hot";
		}
		int size=10;
		List<Album> albumList = albumService.findBySingerIdPage(sid, page, type, size);
		int albumNum = albumService.findCountBySingerId(sid);
		int maxPage = albumNum/size;
		if(albumNum%size!=0){
			maxPage++;
		}
		mav.addObject("type", type);
		mav.addObject("albumNum",albumNum);
		mav.addObject("maxPage", maxPage);
		mav.addObject("pageNo", page);
		mav.addObject("albumList",albumList);
		mav.setViewName("singer/albumAjax");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("favlist")
	public String favlist(String userId){
		List<FavSong> favlist = new ArrayList<FavSong>();
		if(userId!=null){
			favlist = favSongService.findAllFavSongByUser(Integer.parseInt(userId));
		}
		JSONArray array = new JSONArray();
		array.addAll(favlist);
		return array.toString();
	}
	
	@ResponseBody
	@RequestMapping("addFavSong")
	public String addFavSong(String listName,String userId,String songId){
		int favsongId = favSongService.userAddFavSong(listName, Integer.parseInt(userId));
		listAndSongService.addListAndSong(favsongId,Integer.parseInt(songId));
		
		return "Ìí¼Ó³É¹¦";
	}
}