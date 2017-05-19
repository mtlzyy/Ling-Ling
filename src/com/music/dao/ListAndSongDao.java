package com.music.dao;

import java.util.List;

import com.music.pojo.ListAndSong;

public interface ListAndSongDao {
	public List<ListAndSong> findSongIDby(int favsongid);
	public void insertListAndSong(int favsongid,int songid);
	public void addListAndSong(int favsongId,int songId);
	
	public ListAndSong findByFavIdAndSongId(int favsongId,int songId);
	
	public void removeSongFromFavSong(int favsongid,int songid);
}
