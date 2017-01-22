package com.fudan2015.entity;

public class Doodle {
	private int id_doodle;
	private int picId;
	private String doodle_url;
	private int id;
	public Doodle() {
		
	}
	public void setId_doodle(int id_doodle) {
		this.id_doodle = id_doodle;
	}
	public int getId_doodle() {
		return id_doodle;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getDoodle_url() {
		return doodle_url;
	}
	public void setDoodle_url(String doodleUrl) {
		doodle_url = doodleUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
