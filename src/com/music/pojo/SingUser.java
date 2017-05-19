package com.music.pojo;

import java.util.Date;

public class SingUser {
	private int userID;
	private String userName;
	private String passwords;
	private String nickName;
	private int sex;
	private String email;
	private int vip;
	private Date registDate;
	private int manager;
	private int points;
	private String head;
	public SingUser() {
		super();
	}

	public SingUser(int userID, String userName, String passwords,
			String nickName, int sex, String email, int vip, Date registDate,
			int manager, int points,String head) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.passwords = passwords;
		this.nickName = nickName;
		this.sex = sex;
		this.email = email;
		this.vip = vip;
		this.registDate = registDate;
		this.manager = manager;
		this.points = points;
		this.head=head;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	

}
