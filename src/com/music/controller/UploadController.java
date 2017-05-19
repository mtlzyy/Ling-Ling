package com.music.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.AlbumService;
import com.music.dao.service.SingerService;
import com.music.dao.service.SongTypeService;
import com.music.dao.service.SongsService;
import com.music.dao.service.UploadService;
import com.music.pojo.Album;
import com.music.pojo.Singer;
import com.music.pojo.SongType;
import com.music.pojo.Songs;
@Controller
@RequestMapping(value="upload")
public class UploadController {
	@Autowired
	private UploadService uploadservice;
	@Autowired
	private SongTypeService songTypeService;
	@Autowired
	private SingerService singerService;
	@Autowired
	private SongsService songsService;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("upload")
	public ModelAndView upload(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("upload");
		uploadservice.upload();
		return mav;
	}
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mav=new ModelAndView();
		List<SongType> songtypelist=songTypeService.findSongType();
		mav.addObject("songtypelist",songtypelist);
		mav.setViewName("upload");
		return mav;
	}
	@RequestMapping("showsinger")
	public ModelAndView showsinger(String singername){
		ModelAndView mav=new ModelAndView();
		List<Singer> singerlist=uploadservice.findallsinger(singername);
		mav.addObject("singerlist",singerlist );
		mav.setViewName("ajax/ajax_showsinger");
		return mav;
	}
	@RequestMapping("delete")
	public ModelAndView delete(String id,String choose,String search){
		ModelAndView mav=new ModelAndView();
		try {
			byte[] bytes1 = choose.getBytes("ISO-8859-1");
			choose = new String(bytes1,"utf-8");
			byte[] bytes2 = search.getBytes("ISO-8859-1");
			search = new String(bytes2,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(id!=null){
			int newId=Integer.parseInt(id);
			if(choose.equals("É¾³ý¸èÊÖ(×éºÏ)")){
				albumService.deletealbum(newId);
				songsService.deletesongs(newId);
				singerService.deleteSinger(newId);
			}else if(choose.equals("É¾³ý¸èÇú")){
				albumService.deletealbum(newId);
				songsService.deletesongs(newId);
			}else{
				albumService.deletealbum(newId);
			}
		}
		
		if(choose.equals("É¾³ý¸èÊÖ(×éºÏ)")){
			List<Singer> singerlist=singerService.searchSingerByName("%"+search+"%");
			mav.addObject("singerlist",singerlist);
		}else if(choose.equals("É¾³ý¸èÇú")){
			List<Songs> songlist = songsService.searchSongsByName("%"+search+"%");
			mav.addObject("songlist", songlist);
		}else{
			List<Album> albumlist=albumService.searchAlbumByName("%"+search+"%");
			mav.addObject("albumlist", albumlist);
		}
		
		mav.setViewName("ajax/ajax_showdelete");
		mav.addObject("search", search);
		mav.addObject("choose", choose);
		return mav;
	}
}
