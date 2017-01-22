package com.fudan2015.entity;

public class Share {
	private int id;
	private int id_share;
	private int pictureId;
	private String url;
	private String name;
	private String master;
	public Share() {
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId_share(int id_share) {
		this.id_share = id_share;
	}
	public int getId_share() {
		return id_share;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public int getPictureId() {
		return pictureId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
}
