package com.music.dao.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.AlbumDao;
import com.music.dao.FavSongDao;
import com.music.dao.SingerDao;
import com.music.dao.SongsDao;
import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.Singer;
import com.music.pojo.Songs;

@Service
public class SongRankService {
	@Autowired
	private SongsDao songsDao;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private SingerDao singerDao;
	@Autowired
	private FavSongDao favSongDao;
	public List<String> findSongLanguage(){
		List<String> list = songsDao.findLanguage();
		return list;
	}
	public List<Songs> rankSongs(int page,String order,String languages){	
		List<Songs> songsList;
		if(languages != null){
			songsList = songsDao.rankSongs(page,order,languages);
		}else{
			songsList = songsDao.rankSongs(page,order,"");
		}
		for (Songs songs : songsList) {
			Album album = albumDao.findAlbumByAlbumId(songs.getAlbumID());
			Singer singerList =  singerDao.findSinger(songs.getSingerID());
			songs.setAlbum(album);
			songs.setSinger(singerList);
		}

		return songsList;
	}
	
	public int rankCount(String languages){
		int rankCount;
		if(languages != null){
			rankCount =songsDao.rankcount(languages);
		}else{
			rankCount = songsDao.rankcount("");
		}
		  
		if(rankCount>100){
			rankCount = 100;
		}
		int size = 15;
		int maxpage=rankCount/size;
		if(rankCount/size == 0){
			maxpage = 1;
		}else if(rankCount%size != 0){
	     maxpage++;
	     }
		return maxpage;
	}
	
	public List<FavSong> findLoginForm(int userID){
		List<FavSong> list = favSongDao.findFavSongByUser(userID);
		return list ;
	}
}
