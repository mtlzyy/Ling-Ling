package com.music.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.ListAndSongDao;
@Service
public class ListAndSongService {
	@Autowired
	private ListAndSongDao lasDao;
	
	public void addListAndSong(int favsongId,int songId){
		lasDao.addListAndSong(favsongId, songId);
	}
	
	public void removeSongFromFavSong(int favsongid,int songid){
		lasDao.removeSongFromFavSong(favsongid, songid);
	}
}
