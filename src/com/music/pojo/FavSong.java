package com.music.pojo;

import java.util.Date;
/**
 * ÊÕ²Ø¸èÇú±í£¨¸èµ¥£©
 * @author Administrator
 *
 */
public class FavSong {
	private int favsongID;
	private String favsongName;
	private int userID;
	private int clickCount;
	private String image;
	private Date createDate;
	private SingUser singUser;
	
	public SingUser getSingUser() {
		return singUser;
	}

	public void setSingUser(SingUser singUser) {
		this.singUser = singUser;
	}

	public FavSong() {
		super();
	}

	public FavSong(int favsongID, String favsongName, int userID,
			int clickCount, String image, Date createDate) {
		super();
		this.favsongID = favsongID;
		this.favsongName = favsongName;
		this.userID = userID;
		this.clickCount = clickCount;
		this.image = image;
		this.createDate = createDate;
	}


	public int getFavsongID() {
		return favsongID;
	}

	public void setFavsongID(int favsongID) {
		this.favsongID = favsongID;
	}

	public String getFavsongName() {
		return favsongName;
	}

	public void setFavsongName(String favsongName) {
		this.favsongName = favsongName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
