package com.music.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.Songs;


public interface SongsDao {


	public List<Songs> findHotSongs();
	public List<Songs> findNewSongs();
	public List<Songs> vagueSearchSongs(String songName,int page ,int size);
	public void addsongs(Songs songs);
	public List<Songs> rangeSongs();
	public List<String> findLanguage();
	public int rankcount(@Param("languages")String languages);
	public List<Songs> rankSongs(int page,@Param("order")String order,@Param("languages")String languages);
	public List<Songs> findBySingerId(int singerId);
	public List<Songs> findBySingerIdHotTen(int singerId);
	public List<Songs> findBySingerIdPage(@Param("singerId")int singerId,@Param("pageNo")int pageNo,@Param("type")String type,@Param("size")int size);
	public int findCountBySingerId(int singerId);
	//���ݸ赥id�Ҹ����б�
	public List<Songs> findFavSongsByFavSongId(int id);
	//����ר��id�Ҹ����б�
	public List<Songs> findAlbumSongsByAlbumId(int id);
	public List<Songs> searchSongsByName(String songName);
	public int findCountBySongName(String name);
	public void deletesongs(int songid);
	//���ݸ���id���Ҹ���
	public Songs findSongToMedia(int songID);
}
