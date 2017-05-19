package com.music.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.AlbumDao;
import com.music.dao.FavAlbumDao;
import com.music.dao.FavListDao;
import com.music.dao.FavSongDao;
import com.music.dao.ListAndSongDao;
import com.music.dao.SongsDao;
import com.music.pojo.Album;
import com.music.pojo.FavAlbum;
import com.music.pojo.FavList;
import com.music.pojo.FavSong;
import com.music.pojo.ListAndSong;
import com.music.pojo.Songs;

@Service
public class AlbumService {
	@Autowired
	AlbumDao albumDao;
	@Autowired
	SongsDao songsDao;
	@Autowired
	FavSongDao favSongDao;
	@Autowired
	FavListDao favListDao;
	@Autowired
	FavAlbumDao favAlbumDao;
	@Autowired
	ListAndSongDao listAndSongDao;
	
	public List<Album> findBySingerIdPage(int singerId,int pageNo,String type,int size){
		return albumDao.findBySingerIdPage(singerId, pageNo, type, size);
	}
	
	public int findCountBySingerId(int singerId){
		return albumDao.findCountBySingerId(singerId);		
	}
	
	public List<Album> findAllAlbumPage(int start,int end,String type){
		List<Album> list= new ArrayList<Album>();
		list=albumDao.findAllAlbumPage(start, end, type);
		return list;
	}
	
	public int findAllAlbumCount(){
		return albumDao.findAllAlbumCount();
	}
	
	public Album findAlbumByAlbumId(int id){
		return albumDao.findAlbumByAlbumId(id);
	}
	
	public List<Songs> findAlbumSongsByAlbumId(int id){
		List<Songs> list=new ArrayList<Songs>();
		list=songsDao.findAlbumSongsByAlbumId(id);
		return list;
	}
	
	public List<FavSong> findFavSongListByUserId(int id){
		List<FavSong> list=new ArrayList<FavSong>();
		list=favSongDao.findFavSongListByUserId(id);
		return list;
	}
	
	public FavAlbum findFavAlbumByAlbumIdandUserId(int albumId,int userId){
		return favAlbumDao.findFavAlbumByAlbumIdandUserId(albumId, userId);
	}
	
	public void addFavAlbum(int albumId,int userId){
		favAlbumDao.addFavAlbum(albumId, userId);
	}
	
	public void deleteFavAlbum(int albumId,int userId){
		favAlbumDao.deleteFavAlbum(albumId, userId);
	}
	public List<Album> findBySingerIdHotFive(int singerId){
		return albumDao.findBySingerIdHotFive(singerId);
	}

	public List<Album> showAlbumList(String name,int page,int size){
		return albumDao.vagueSearchAlbum(name, page, size);
	}

	public int findCountByAlbumName(String name){
		return albumDao.findCountByAlbumName(name);
	}

	public List<Album> searchAlbumByName(String albumname){
		return albumDao.searchAlbumByName(albumname);
	}
	
	public void addFavSong(String favSongName,int userId){
		favSongDao.addFavSong(favSongName, userId);
	}
	
	public void addListAndSong(int favsongId,int songId){
		listAndSongDao.addListAndSong(favsongId, songId);
	}
	
	public FavSong findFavsongByfavSongNameAndUserid(String favSongName,int userId){
		return favSongDao.findFavsongByfavSongNameAndUserid(favSongName, userId);
	}
	
	public void addAlbumClickCount(int clickCount,int albumId){
		albumDao.addAlbumClickCount(clickCount, albumId);
	}
	
	public void deletealbum(int albumid){
		albumDao.deletealbum(albumid);
	}
}

