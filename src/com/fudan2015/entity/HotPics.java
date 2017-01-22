package com.fudan2015.entity;

public class HotPics {
	/*
	 * id of hotPicture
	 * id of the owner
	 * id of the picture
	 * hyperText
	 * hyperSpan
	 * introduction of the hotPicture
	 */
	private int id;
	private int idAccount;
	private int idPic;
	private String htext;
	private String hspan;
	private String introduction;
	public HotPics() {
		
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdPic(int idPic) {
		this.idPic = idPic;
	}
	public int getIdPic() {
		return idPic;
	}
	public void setHtext(String htext) {
		this.htext = htext;
	}
	public String getHtext() {
		return htext;
	}
	public void setHspan(String hspan) {
		this.hspan = hspan;
	}
	public String getHspan() {
		return hspan;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
}
