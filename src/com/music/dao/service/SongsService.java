package com.music.dao.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.SongsDao;
import com.music.pojo.Songs;

@Service
public class SongsService {
	@Autowired 
	private SongsDao songsDao;
	public List<Songs> findBySingerId(int singerId){
		return songsDao.findBySingerId(singerId);
	}
	public int findCountBySingerId(int singerId){
		return songsDao.findCountBySingerId(singerId);
	}
	public List<Songs> showSonglist(String name, int page,int size){
		return songsDao.vagueSearchSongs(name, page, size);
	}
	public List<Songs> findBySingerIdPage(int singerId,int pageNo,String type,int size){
		return songsDao.findBySingerIdPage(singerId, pageNo,type,size);
		
	}
	public List<Songs> findBySingerIdHotTen(int singerId){
		return songsDao.findBySingerIdHotTen(singerId);
	}

	public int findCountBySongname(String name){
		return songsDao.findCountBySongName(name);
	}

	public List<Songs> searchSongsByName(String songName){
		return songsDao.searchSongsByName(songName);
	}
	
	public Songs findSongToMedia(int songID){
		return songsDao.findSongToMedia(songID);
	}
	public void deletesongs(int songid){
		songsDao.deletesongs(songid);
	}

	}


