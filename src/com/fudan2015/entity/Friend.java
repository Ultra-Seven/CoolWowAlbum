package com.fudan2015.entity;

public class Friend {
	/*
	 * 用户序号 F
	 * 好友分类
	 * 好友名
	 * 好友序号 P
	 * 好友的账户名称
	 * 好友的签名
	 * 好友的状态
	 * 好友的头像
	 * 
	 */
	private int idFriend;
	private String classification;
	private int friendId;
	private int id;
	private String name;
	private String introduction;
	private int state;
	private String image;
	public int getIdFriend() {
		return idFriend;
	}
	public void setIdFriend(int idFriend) {
		this.idFriend = idFriend;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
