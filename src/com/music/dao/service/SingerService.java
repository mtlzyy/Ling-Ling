package com.music.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.SingerDao;
import com.music.pojo.Singer;
@Service
public class SingerService {
	@Autowired
	private SingerDao singerDao;
	public List<Singer> findSingers(String beginEn,String nation,int sex,int pageNo){
		return singerDao.findSingers(beginEn,nation,sex,pageNo);
	}
	public Singer findSinger(int singerId){
		return singerDao.findSinger(singerId);
	}
	
	public List<String> findBeginEn(){
		return singerDao.findBeginEn();
	}
	public List<String> findNation(){
		return singerDao.findNation();
	}
	public int singersCount(String beginEn,String nation,int sex){
		return singerDao.singersCount(beginEn, nation, sex);
	}
	public List<Singer> showSingerList(String name,int page, int size){	
		return singerDao.vagueSearchSinger(name, page, size);
	}
	public int findCountByName(String name){	
		return singerDao.findCountByName(name);
	}
	public void deleteSinger(int singerid){
		singerDao.deleteSinger(singerid);
	}
	public List<Singer> searchSingerByName(String name){
		return singerDao.findSingerByname(name);
	}
}
