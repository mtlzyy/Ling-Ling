package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.Singer;

public interface SingerDao {
	public List<Singer> findSingers(@Param("beginEn")String beginEn,@Param("nation")String nation,@Param("sex")int sex,@Param("pageNo")int pageNo);
	public List<String> findBeginEn();
	public List<String> findNation();
	public int singersCount(@Param("beginEn")String beginEn,@Param("nation")String nation,@Param("sex")int sex);
	public void addSinger(Singer s);
	public Singer findSinger(int singerid);
	public List<Singer> vagueSearchSinger(String singerName,int page,int size);
	public List<Singer> findSingerByname(String singerName);
	public int findCountByName(String singerName);
	public void deleteSinger(int singerid);
}
