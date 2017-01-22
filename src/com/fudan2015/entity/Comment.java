package com.fudan2015.entity;

public class Comment {
	/*
	 * comment's id
	 * picture's id
	 * content of the comment
	 * observer of the comment
	 * date of the comment
	 */
	private int id;
	private int picId;
	private String comment;
	private String observer;
	private String date;
	public Comment() {
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setObserver(String observer) {
		this.observer = observer;
	}
	public String getObserver() {
		return observer;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getPicId() {
		return picId;
	}
}
