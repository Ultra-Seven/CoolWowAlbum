package com.fudan2015.entity;

public class Album {
	/*
	 * 用户序号
	 * 相册名
	 * 相册序号
	 * 相册内相片数
	 * 相册创建日期
	 */
  private int id;
  private String album;
  private int albumId;
  private int num;
  private String date;
  
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
public int getAlbumId() {
	return albumId;
}
public void setAlbumId(int albumId) {
	this.albumId = albumId;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public void setDate(String date) {
	this.date = date;
}
public String getDate() {
	return date;
}
}
