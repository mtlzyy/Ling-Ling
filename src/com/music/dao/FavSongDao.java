package com.music.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.FavSong;


public interface FavSongDao {

	public List<FavSong> findFavSongPage(@Param("start")int start,@Param("end")int end);

	public List<FavSong> findHotFavSong();
	
	public int findFavSongCount();
	
	public List<FavSong> findFavSongByUser(int userID);
	
	public List<FavSong> vagueSearchFavsong(String songname,int page ,int size);
	
	public List<FavSong> findFavSongByUserIdWithPage(int userid,int page);
	
	public List<FavSong> findNewFavSongPage(@Param("start")int start,@Param("end")int end);
	
	public FavSong findFavSongById(int id);
	
	public List<FavSong> findFavSongListByUserId(int userId);
	
	public void uploadFavsongImage (FavSong favsong);
	
	public FavSong findFavsongByFavsongid(int favsongid);
	
	public int findCountbyvagueSearch(String songname);
	
	public void addFavSong(String favSongName,int userId);
	
	public FavSong findFavsongByfavSongNameAndUserid(String favSongName,int userId);
	
	public void addFavSongClickCount(int clickCount,int favSongId);
	
	public void deleteFavSongById(int favsongId);
	
	public void deleteOwnFavSongById(int favsongId);
	
	public int currFavSongId();
}
