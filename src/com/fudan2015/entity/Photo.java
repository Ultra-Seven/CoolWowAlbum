package com.fudan2015.entity;



public class Photo {
	/*
	 * 用户序号 F
	 * 相册名
	 * 相片url
	 * 相片序号 P
	 * 相片日期
	 * 相片浏览量
	 * 相片评论量
	 */
	private int id;
	private String album;
	private String picsUrl;
	private String picName;
	private int picId;
	private String date;
	private int visit;
	private int comments;
	private boolean share;
	private int shareId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getPicsUrl() {
		return picsUrl;
	}
	public void setPicsUrl(String picsUrl) {
		this.picsUrl = picsUrl;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public boolean isShare() {
		return share;
	}
	public void setShare(boolean share) {
		this.share = share;
	}
	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

}
