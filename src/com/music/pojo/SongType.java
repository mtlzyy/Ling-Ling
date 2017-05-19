package com.music.pojo;

public class SongType {
	private int songTypeID;
	private String songTypeName;
	public SongType() {
		super();
	}
	public SongType(int songTypeID, String songTypeName) {
		super();
		this.songTypeID = songTypeID;
		this.songTypeName = songTypeName;
	}
	public int getSongTypeID() {
		return songTypeID;
	}
	public void setSongTypeID(int songTypeID) {
		this.songTypeID = songTypeID;
	}
	public String getSongTypeName() {
		return songTypeName;
	}
	public void setSongTypeName(String songTypeName) {
		this.songTypeName = songTypeName;
	}

}
