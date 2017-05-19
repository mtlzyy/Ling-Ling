package com.music.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.ListAndSongDao;
import com.music.pojo.ListAndSong;

@Service
public class RankAddSongService {
	@Autowired
	private ListAndSongDao listAndSongDao;
	public List<ListAndSong> findRepeat(int favsongid){
		List<ListAndSong> list = listAndSongDao.findSongIDby(favsongid);
		return list;
	}
	
	public void updateListAndSong(int favsongid,int songid){
		listAndSongDao.insertListAndSong(favsongid, songid);
	}
}
