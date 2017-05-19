package com.music.pojo;

import java.util.Date;

public class Singer {
	private int singerID;
	private String singerName;
	private int sex;
	private Date birth;
	private String nationality;
	private String image;
	private String beginEn;
	private String intro;
	private int grouptype; 
	
	public Singer() {
		super();
	}



	public Singer(int singerID, String singerName, int sex, Date birth,
			String nationality, String image, String beginEn, String intro,
			int grouptype) {
		super();
		this.singerID = singerID;
		this.singerName = singerName;
		this.sex = sex;
		this.birth = birth;
		this.nationality = nationality;
		this.image = image;
		this.beginEn = beginEn;
		this.intro = intro;
		this.grouptype = grouptype;
	}



	public int getGrouptype() {
		return grouptype;
	}



	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}



	public int getSingerID() {
		return singerID;
	}

	public void setSingerID(int singerID) {
		this.singerID = singerID;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBeginEn() {
		return beginEn;
	}

	public void setBeginEn(String beginEn) {
		this.beginEn = beginEn;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}


}
