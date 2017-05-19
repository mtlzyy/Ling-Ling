package com.music.dao.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SearchService {
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private SingerDao singerDao;
	@Autowired
	private SongsDao songsDao;
	@Autowired
	private FavSongDao favsongDao;
	public List<Album> findAlbum(String albumName,int page,int size){
		String name = "%"+albumName+"%";
		List<Album> list = albumDao.vagueSearchAlbum(name,page,size);
		return list;
	}
	public List<Songs> findSongs(String songName,int page,int size){
		String name = "%"+songName+"%";
		List<Songs> list = songsDao.vagueSearchSongs(name,page,size);
		return list;
	}
	public List<Singer> findSingers(String singerName,int page,int size){
		if(singerName != null){
			String name = "%"+singerName+"%";
			List<Singer> list = singerDao.vagueSearchSinger(name,page,size);
			return list;
		}
		return null;
	}
	
}
