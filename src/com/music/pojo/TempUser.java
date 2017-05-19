package com.music.pojo;

public class TempUser {
	private int tempID;
	private String userpwd;
	private String userName;
	private String email;
	private String actionCode;
	public TempUser() {
		super();
	}
	public TempUser(int tempID, String userpwd, String userName, String email,
			String actionCode) {
		super();
		this.tempID = tempID;
		this.userpwd = userpwd;
		this.userName = userName;
		this.email = email;
		this.actionCode = actionCode;
	}
	public int getTempID() {
		return tempID;
	}
	public void setTempID(int tempID) {
		this.tempID = tempID;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

}
