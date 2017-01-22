package com.fudan2015.entity;

import java.util.List;


public class User {
	/*
	 * 用户序号 P
	 * 用户名
	 * 用户密码
	 * 用户邮箱
	 * 用户性别
	 * 用户签名
	 * 用户相册数
	 * 用户相片数
	 * 用户好友数
	 * 用户头像url
	 * 用户状态
	 * 用户类型
	 * 用户好友列表数
	 *用户的好友List
	 * =======反正下面的两个你们都用不到=======
	 * 用户密保问题
	 * 用户密保答案
	 */
	private int id;
	private String account;
	private String password;
	private String email;
	private int gender;
	private String description;
	private int albumNum;
	private int picturesNum;
	private int friendsNum;
	private int friendListNum;
	private int shareNum;
	private String image;
	private int state;
	private int identity;
	private int messageNum;
	private List<Friend> friend;
	private String passwdQuestion;
	private String passwdAnswer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAlbumNum() {
		return albumNum;
	}
	public void setAlbumNum(int albumNum) {
		this.albumNum = albumNum;
	}
	public int getPicturesNum() {
		return picturesNum;
	}
	public void setPicturesNum(int picturesNum) {
		this.picturesNum = picturesNum;
	}
	public int getFriendsNum() {
		return friendsNum;
	}
	public void setFriendsNum(int friendsNum) {
		this.friendsNum = friendsNum;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}
	public int getMessageNum() {
		return messageNum;
	}
	public void setPasswdQuestion(String passwdQuestion) {
		this.passwdQuestion = passwdQuestion;
	}
	public String getPasswdQuestion() {
		return passwdQuestion;
	}
	public void setPasswdAnswer(String passwdAnswer) {
		this.passwdAnswer = passwdAnswer;
	}
	public String getPasswdAnswer() {
		return passwdAnswer;
	}
	public int getFriendListNum() {
		return friendListNum;
	}
	public void setFriendListNum(int friendListNum) {
		this.friendListNum = friendListNum;
	}
	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}
	public int getShareNum() {
		return shareNum;
	}
	public List<Friend> getFriend() {
		return friend;
	}
	public void setFriend(List<Friend> friend) {
		this.friend = friend;
	}
}
