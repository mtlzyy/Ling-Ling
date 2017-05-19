package com.music.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.FavListDao;
import com.music.dao.FavSongDao;
import com.music.pojo.FavList;
import com.music.pojo.FavSong;

@Service
public class FavListService {
	
	@Autowired
	private FavListDao fldao;
	@Autowired
	private FavSongDao fsdao;
	
	public List<FavList> findFavListByUser(int userid){
		return fldao.findFavListByUser(userid);
	}
	
	public List<FavList> findFavListByUserWithPage(int userid,int page){
		return fldao.findFavListByUserWithPage(userid, page);
	}
	
	public void deleteFavListByIds(int userId, int favsongId){
		fldao.deleteFavlist(favsongId, userId);
	}
	
	public List<FavSong> findFavSongsByIdAndPage(int userid,int page){
		List<FavList> favlist = fldao.findFavListByUserWithPage(userid, page);
		List<FavSong> favsongList = new ArrayList<FavSong>();
		for(FavList fl:favlist){
			int fsId = fl.getFavsongID();
			FavSong fs = fsdao.findFavsongByFavsongid(fsId);
			favsongList.add(fs);
		}
		return favsongList;
	}
}
