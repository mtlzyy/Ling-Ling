package com.music.pojo;

public class FavAlbum {
	private int favalbumID;
	private int userid;
	private int albumID;
	public FavAlbum() {
		super();
	}
	public FavAlbum(int favalbumID, int userid, int albumID) {
		super();
		this.favalbumID = favalbumID;
		this.userid = userid;
		this.albumID = albumID;
	}
	public int getFavalbumID() {
		return favalbumID;
	}
	public void setFavalbumID(int favalbumID) {
		this.favalbumID = favalbumID;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getAlbumID() {
		return albumID;
	}
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

}
