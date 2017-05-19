package com.music.pojo;

public class Comments {
	private int commentid;
	private String commentcontent;
	private int userid;
	private int recommandID;
	public Comments() {
		super();
	}
	public Comments(int commentid, String commentcontent, int userid,
			int recommandID) {
		super();
		this.commentid = commentid;
		this.commentcontent = commentcontent;
		this.userid = userid;
		this.recommandID = recommandID;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRecommandID() {
		return recommandID;
	}
	public void setRecommandID(int recommandID) {
		this.recommandID = recommandID;
	}
	

}
