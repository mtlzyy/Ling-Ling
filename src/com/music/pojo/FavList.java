package com.music.pojo;

public class FavList {
	private int favlistID;
	private int userid;
	private int favsongID;
	
	public FavList() {
		super();
	}

	public FavList(int favlistID, int userid, int favsongID) {
		super();
		this.favlistID = favlistID;
		this.userid = userid;
		this.favsongID = favsongID;
	}

	public int getFavlistID() {
		return favlistID;
	}

	public void setFavlistID(int favlistID) {
		this.favlistID = favlistID;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getFavsongID() {
		return favsongID;
	}

	public void setFavsongID(int favsongID) {
		this.favsongID = favsongID;
	}
	
}
