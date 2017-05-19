package com.music.controller.vague;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.AlbumService;
import com.music.dao.service.FavSongService;
import com.music.dao.service.SearchService;
import com.music.dao.service.SingUserService;
import com.music.dao.service.SingerService;
import com.music.dao.service.SongsService;
import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.SingUser;
import com.music.pojo.Singer;
import com.music.pojo.Songs;


@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private SingerService singerService;
	@Autowired
	private SongsService songsService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private FavSongService favSongService;
	@Autowired
	private SingUserService singUserService;
	@RequestMapping("search")
	public ModelAndView findAlbum(String name){
		ModelAndView mav = new ModelAndView();
		List<Album> albumList = searchService.findAlbum(name,1,3);
		List<Songs> songList = searchService.findSongs(name,1,3);
		List<Singer> singerList = searchService.findSingers(name,1,3);
		mav.addObject("album", albumList);
		mav.addObject("song", songList);
		mav.addObject("singer", singerList);
		mav.setViewName("vagueSearch");
		return mav;
	}
	
	@RequestMapping("search2")
	public ModelAndView findall(String name){
		int maxpage=0;
		try {
			byte[] bytes1 = name.getBytes("ISO-8859-1");
			name = new String(bytes1,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		Map<String, List> map=new HashMap<String, List>();	
			int count=albumService.findCountByAlbumName("%"+name+"%");
			maxpage=count/10;
			if(count%10!=0){
				maxpage++;
			}
			List<Album> albumlist=albumService.showAlbumList("%"+name+"%", 1, 10);
			for (Album album : albumlist) {
				String singername=singerService.findSinger(album.getSingerid()).getSingerName();
				album.setSingerName(singername);
			}
			map.put("albumlist",albumlist);
		
			mav.addObject("searchmap",map);
			mav.addObject("name",name);
			mav.setViewName("searchAndShow");
			mav.addObject("pageNo",1);
			mav.addObject("choose", "album");
			mav.addObject("maxPage", maxpage);
		return mav;
	}
	
	@RequestMapping("ajaxSearch2")
	public ModelAndView ajaxFindAll(String name,String page,String choose){
		int maxpage=0;
		try {
			byte[] bytes1 = name.getBytes("ISO-8859-1");
			name = new String(bytes1,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		ModelAndView mav=new ModelAndView();
		int newpage=0;
		if(page==null){
			newpage=1;
		}else{
			newpage=Integer.parseInt(page);
		}
		
		Map<String, List> map=new HashMap<String, List>();
		if(choose.equals("album")){
			int count=albumService.findCountByAlbumName("%"+name+"%");
			maxpage=count/10;
			if(count%10!=0){
				maxpage++;
			}
			List<Album> albumlist=albumService.showAlbumList("%"+name+"%", newpage, 10);
			for (Album album : albumlist) {
				String singername=singerService.findSinger(album.getSingerid()).getSingerName();
				album.setSingerName(singername);
			}
			map.put("albumlist",albumlist);
		}else if(choose.equals("songs")){
			int count=songsService.findCountBySongname("%"+name+"%");
			maxpage=count/10;
			if(count%10!=0){
				maxpage++;
			}
			List<Songs> songlist=songsService.showSonglist("%"+name+"%", newpage, 10);
			for (Songs songs : songlist) {
				String albumname=null;
				if(albumService.findAlbumByAlbumId(songs.getAlbumID())==null){
					albumname="нч";
				}else{
					albumname=albumService.findAlbumByAlbumId(songs.getAlbumID()).getAlbumName();
				}
				
				String singername=singerService.findSinger(songs.getSingerID()).getSingerName();
				songs.setAlbumName(albumname);
				songs.setSingerName(singername);
			}
			map.put("songlist", songlist);
		}else if(choose.equals("favsong")){	
			int count=favSongService.findcountbyname("%"+name+"%");
			maxpage=count/10;
			if(count%10!=0){
				maxpage++;
			}

			List<FavSong> favsonglist=favSongService.showFavsongList("%"+name+"%", newpage, 10);
			for (FavSong favsongs : favsonglist) {
				SingUser singUser=singUserService.findUserByUserid(favsongs.getUserID());
				favsongs.setSingUser(singUser);
			}
			map.put("favsonglist", favsonglist);
		}else{
			int count=singerService.findCountByName("%"+name+"%");
			maxpage=count/10;
			if(count%10!=0){
				maxpage++;
			}
			List<Singer> singerlist=singerService.showSingerList("%"+name+"%", newpage, 10);
			map.put("singerlist", singerlist);
		}
		System.out.println("choose:"+choose);
		mav.addObject("name",name);
		mav.addObject("choose",choose);
		mav.addObject("searchmap",map);
		mav.addObject("pageNo",newpage);
		mav.addObject("maxPage", maxpage);
		mav.setViewName("ajax/ajax_searchAndShow");
		return mav;
	}

}
