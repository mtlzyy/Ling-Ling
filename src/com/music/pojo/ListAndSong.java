package com.music.pojo;

public class ListAndSong {
	private int listAndSongid;
	private int favsongid;
	private int songid;
	
	public ListAndSong() {
		super();
	}

	public ListAndSong(int listAndSongid, int favsongid, int songid) {
		super();
		this.listAndSongid = listAndSongid;
		this.favsongid = favsongid;
		this.songid = songid;
	}

	public int getListAndSongid() {
		return listAndSongid;
	}

	public void setListAndSongid(int listAndSongid) {
		this.listAndSongid = listAndSongid;
	}

	public int getFavsongid() {
		return favsongid;
	}

	public void setFavsongid(int favsongid) {
		this.favsongid = favsongid;
	}

	public int getSongid() {
		return songid;
	}

	public void setSongid(int songid) {
		this.songid = songid;
	}
	
}
