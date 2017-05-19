package com.music.pojo;

import java.util.Date;

public class Album {
	private int albumID;
	private String albumName; 
	private String albumInfo; 
	private int clickCount;
	private String image; 
	private Date createDate;
	private int singerid;
	private String singerName;
	
	public Album() {
		super();
	}



	public Album(int albumID, String albumName, String albumInfo,
			int clickCount, String image, Date createDate, int singerid) {
		super();
		this.albumID = albumID;
		this.albumName = albumName;
		this.albumInfo = albumInfo;
		this.clickCount = clickCount;
		this.image = image;
		this.createDate = createDate;
		this.singerid = singerid;
	}


	
	public String getSingerName() {
		return singerName;
	}



	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}



	public int getSingerid() {
		return singerid;
	}



	public void setSingerid(int singerid) {
		this.singerid = singerid;
	}



	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumInfo() {
		return albumInfo;
	}

	public void setAlbumInfo(String albumInfo) {
		this.albumInfo = albumInfo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	

}
