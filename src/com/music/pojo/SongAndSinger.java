package com.music.pojo;
/**
 * 歌曲与歌手的关系表
 * @author Administrator
 *
 */
public class SongAndSinger {
	private int songAndSingerid;
	private int singerID;
	private int songID;
	public SongAndSinger() {
		super();
	}
	public SongAndSinger(int songAndSingerid, int singerID, int songID) {
		super();
		this.songAndSingerid = songAndSingerid;
		this.singerID = singerID;
		this.songID = songID;
	}
	public int getSongAndSingerid() {
		return songAndSingerid;
	}
	public void setSongAndSingerid(int songAndSingerid) {
		this.songAndSingerid = songAndSingerid;
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

}
