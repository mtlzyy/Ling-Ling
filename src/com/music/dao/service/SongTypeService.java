package com.music.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.SongTypeDao;
import com.music.pojo.SongType;
@Service
public class SongTypeService {
	@Autowired
	private SongTypeDao songTypeDao;
	
	public List<SongType> findSongType(){
		return songTypeDao.findType();
	}
}
