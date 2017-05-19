package com.music.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.FavListDao;
import com.music.dao.FavSongDao;
import com.music.pojo.FavSong;

@Service
public class FavSongService {
	@Autowired
	private FavSongDao fsdao;
	@Autowired
	private FavListDao fldao;
	public List<FavSong> findFavSongByUserIdWithPage(int userid,int page){
		return fsdao.findFavSongByUserIdWithPage(userid, page);
	}
	public List<FavSong> showFavsongList(String name,int page,int size){
		return fsdao.vagueSearchFavsong(name, page, size);
	}
	
	public int findcountbyname(String name){
		return fsdao.findCountbyvagueSearch(name);
	}

	public List<FavSong> findAllFavSongByUser(int userid){
		return fsdao.findFavSongByUser(userid);
	}
	
	public void deleteFavSongById(int favId){
		fsdao.deleteFavSongById(favId);
	}
	
	public void deleteOwnFavSongById(int favId){
		fsdao.deleteOwnFavSongById(favId);
	}
	
	public synchronized int userAddFavSong(String favSongName,int userId){
		fsdao.addFavSong(favSongName, userId);
		int favSongId = fsdao.currFavSongId();
		fldao.addFavlist(favSongId, userId);
		return favSongId;
	}
}
