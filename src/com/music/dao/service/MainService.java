package com.music.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.AlbumDao;
import com.music.dao.FavSongDao;
import com.music.dao.RecommandDao;
import com.music.dao.SingerDao;
import com.music.dao.SongsDao;
import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.Recommand;
import com.music.pojo.Singer;
import com.music.pojo.Songs;



@Service
public class MainService {
	@Autowired
	private RecommandDao recommandDao;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private FavSongDao favSongDao;
	@Autowired
	private SongsDao songsDao;
	@Autowired
	private SingerDao singerDao;
	public List<Recommand> findRecommand(){
		List<Recommand> list = recommandDao.findRecommand();
		return list;
	}
	public List<Album> findHotAl(){
		List<Album> list = albumDao.findHotAlbum();
		return list;
	}
	public List<FavSong> findHotFav(){
		List<FavSong> list = favSongDao.findHotFavSong();
		return list;
	}
	
	public List<Songs> findHotMusic(){
		List<Songs> list = songsDao.findHotSongs();
		for (Songs songs : list) {
			Singer singer = singerDao.findSinger(songs.getSingerID());
			songs.setSinger(singer);
		}
		
		return list;
	}
	
	public List<Songs> findNewMusic(){
		List<Songs> list = songsDao.findNewSongs();
		for (Songs songs : list) {
			Singer singerList = singerDao.findSinger(songs.getSingerID());
			songs.setSinger(singerList);
		}
		return list;
	}

}
