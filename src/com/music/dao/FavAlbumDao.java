package com.music.dao;

import java.util.List;

import com.music.pojo.FavAlbum;

public interface FavAlbumDao {
	
	public List<FavAlbum> findAllAlbumByUser(int userid);
	
	public List<FavAlbum> findFavAlbumByUserWithPage(int userid,int page);
	
	public FavAlbum findFavAlbumByAlbumIdandUserId(int albumId,int userId);
	
	public void addFavAlbum(int albumId,int userId);
	
	public void deleteFavAlbum(int albumId,int userId);
}
