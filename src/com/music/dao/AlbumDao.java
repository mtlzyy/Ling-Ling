package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.Album;
import com.music.pojo.SingUser;

public interface AlbumDao {
	
	public List<Album> findHotAlbum();
	public List<Album> vagueSearchAlbum(String albumName,int page,int size);
	public Album findAlbumByAlbumId(int albumID);
	public void addAlbum(Album album);
	public Album findAlbumByname(String albumname);
	public List<Album> findBySingerIdHotFive(int singerId);
	public int findCountBySingerId(int singerId);
	public List<Album> findBySingerIdPage(@Param("singerId")int singerId,@Param("pageNo")int pageNo,@Param("type")String type,@Param("size")int size);
	//专辑最热，最新分页查询
	public List<Album> findAllAlbumPage(@Param("start")int start,@Param("end")int end,@Param("type")String type);
	//找到专辑总数
	public int findAllAlbumCount();
	public int findCountByAlbumName(String albumname);
	public List<Album> searchAlbumByName(String albumname);
	public void deletealbum(int albumid);
	public void addAlbumClickCount(int clickCount,int albumId);

}