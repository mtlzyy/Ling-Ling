package com.music.controller.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.dao.service.AlbumService;
import com.music.dao.service.SingerService;
import com.music.dao.service.SongsService;
import com.music.pojo.Album;
import com.music.pojo.Singer;
import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;
@Controller
public class AddToMediaController {
	@Autowired
	private SongsService songsService;
	@Autowired
	private SingerService singerService;
	@Autowired
	private AlbumService albumService;
	/**
	 * 添加多个(默认添加到最后)
	 * @param songadd
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("SongToMedia")
	public String media(String[] songadd){
		boolean flag = true;
		List<Songs> list;
		if(ReqRespSupport.getSession().getAttribute("MediaList") == null){
			list = new ArrayList<Songs>();
			for (String string : songadd) {
				int k = Integer.parseInt(string);
				Songs song = songsService.findSongToMedia(k);
				//System.out.println(song.getSingerID());
				Singer singer = singerService.findSinger(song.getSingerID());
				song.setSinger(singer);
				Album album = albumService.findAlbumByAlbumId(song.getAlbumID());
				song.setAlbum(album);
				list.add(song);
			}
		}else{
			  list = (List<Songs>) ReqRespSupport.getSession().getAttribute("MediaList");
			  for (String string : songadd){
					int k = Integer.parseInt(string);
					for(Songs player : list){
						if(k == player.getSongID()){
							flag = false;
							list.remove(player);
							list.add(player);
							break;
						}else{
							flag = true;
						}
					}
					if(flag == true){
						Songs song = songsService.findSongToMedia(k);
						Singer singer = singerService.findSinger(song.getSingerID());
						song.setSinger(singer);
						Album album = albumService.findAlbumByAlbumId(song.getAlbumID());
						song.setAlbum(album);
						list.add(song);
					}
				}	
		}
		
		ReqRespSupport.getSession().setAttribute("MediaList", list);
		return "添加成功！";
	}
}
