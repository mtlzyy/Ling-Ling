package com.music.dao;

import java.util.List;

import com.music.pojo.SongType;

public interface SongTypeDao {
	public SongType findTypeByTypeName(String TypeName);
	public List<SongType> findType();
}
