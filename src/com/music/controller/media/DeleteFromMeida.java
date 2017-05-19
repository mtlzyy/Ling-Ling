package com.music.controller.media;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;

@Controller
public class DeleteFromMeida {
	@RequestMapping("deleteFromMedia")
	public String deleteFromMedia(String songID){
	List<Songs>	list = (List<Songs>) ReqRespSupport.getSession().getAttribute("MediaList");
	System.out.println(songID);
	int song = Integer.parseInt(songID);
	Songs delSong = null;
	for (Songs songs : list) {
		if(songs.getSongID() == song){
			delSong = songs;
		}
	}
	list.remove(delSong);
	ReqRespSupport.getSession().setAttribute("MediaList", list);
	return "MediaPlayer";
	}
}
