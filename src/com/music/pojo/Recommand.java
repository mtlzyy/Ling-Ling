package com.music.pojo;

import java.util.Date;

public class Recommand {
	private int recommandID;
	private String image;
	private int favsongID;
	private Date recommandDate;
	public Recommand() {
		super();
	}
	public Recommand(int recommandID, String image, int favsongID,Date recommandDate) {
		super();
		this.recommandID = recommandID;
		this.image = image;
		this.favsongID = favsongID;
		this.recommandDate=recommandDate;
	}
	
	public Date getRecommandDate() {
		return recommandDate;
	}
	public void setRecommandDate(Date recommandDate) {
		this.recommandDate = recommandDate;
	}
	public int getRecommandID() {
		return recommandID;
	}
	public void setRecommandID(int recommandID) {
		this.recommandID = recommandID;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFavsongID() {
		return favsongID;
	}
	public void setFavsongID(int favsongID) {
		this.favsongID = favsongID;
	}
	
}
