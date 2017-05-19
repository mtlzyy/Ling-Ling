package com.music.dao;

import java.util.List;

import com.music.pojo.FavList;



public interface FavListDao {
	
	public List<FavList> findFavListByUser(int userid);
	
	public List<FavList> findFavListByUserWithPage(int userid,int page);
	
	public FavList findFavListByFavSongIdAndUserId(int favsongId,int userId);
	
	public void addFavlist(int favsongId,int userId);
	
	public void deleteFavlist(int favsongId,int userId);
}
