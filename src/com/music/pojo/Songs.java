package com.music.pojo;

import java.util.Date;


public class Songs {
	private int songID;
	private String songName;
	private int albumID;
	private int songTypeID;
	private int downloadCount;
	private int clickCount;
	private String urls;
	private Date songDate;
	private String languages;
	private int vipSong;
	private String lyric;
	private String image;
	private int singerID;
	
	private Singer singer;
	private String albumName;
	private String singerName;
	private Album album;
	
	public Songs() {
		super();
	}
	
	
	public Songs(int songID, String songName, int albumID, int songTypeID,String languages,
			int downloadCount, int clickCount,int vipSong, String urls, Date songDate,
			  String lyric, String image,int singerID
			) {
		super();
		this.songID = songID;
		this.songName = songName;
		this.albumID = albumID;
		this.songTypeID = songTypeID;
		this.downloadCount = downloadCount;
		this.clickCount = clickCount;
		this.urls = urls;
		this.songDate = songDate;
		this.languages = languages;
		this.vipSong = vipSong;
		this.lyric = lyric;
		this.image = image;
		this.singerID = singerID;
	}


	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public int getSingerID() {
		return singerID;
	}

	public void setSingerID(int singerID) {
		this.singerID = singerID;
	}

	public int getSongID() {
		return songID;
	}

	public void setSongID(int songID) {
		this.songID = songID;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	public int getSongTypeID() {
		return songTypeID;
	}

	public void setSongTypeID(int songTypeID) {
		this.songTypeID = songTypeID;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public Date getSongDate() {
		return songDate;
	}

	public void setSongDate(Date songDate) {
		this.songDate = songDate;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public int getVipSong() {
		return vipSong;
	}

	public void setVipSong(int vipSong) {
		this.vipSong = vipSong;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
