package com.music.dao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.AlbumDao;
import com.music.dao.FavAlbumDao;
import com.music.dao.SingerDao;
import com.music.pojo.Album;
import com.music.pojo.FavAlbum;
import com.music.pojo.Singer;

@Service
public class FavAlbumService {
	@Autowired
	private FavAlbumDao fadao;
	@Autowired
	private AlbumDao adao;
	@Autowired
	private SingerDao sdao;
	
	public List<FavAlbum> findFavAlbumByUser(int userid){
		return fadao.findAllAlbumByUser(userid);
	}
	
	public List<FavAlbum> findFavAlbumByUserWithPage(int userid,int page){
		return fadao.findFavAlbumByUserWithPage(userid, page);
	}
	
	public void deleteFavAlbumByIds(int userid,int albumId){
		fadao.deleteFavAlbum(albumId, userid);
	}
	
	public Map<Album,Singer> findFavAlbumInfoWithIdAndPage(int userid,int page){
		List<FavAlbum> favalbums = fadao.findFavAlbumByUserWithPage(userid, page);
		Map<Album,Singer> map = new HashMap<Album, Singer>();
		for(FavAlbum fa:favalbums){
			int aid = fa.getAlbumID();
			Album ab = adao.findAlbumByAlbumId(aid);
			int sid = ab.getSingerid();
			Singer sg = sdao.findSinger(sid);
			map.put(ab, sg);
		}
		return map;
		
	} 
}
