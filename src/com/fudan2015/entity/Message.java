package com.fudan2015.entity;

public class Message {
	/*
	 * 收信人
	 * 发信人
	 * 站内信内容
	 * 站内信日期
	 * 站内信状态
	 * 用户（收信人）序号 F
	 * 站内信序号 P
	 */
private String reciever;
private String sender;
private String content;
private String date;
private int state;
private int idMessage;
private int id;
public String getReciever() {
	return reciever;
}
public void setReciever(String reciever) {
	this.reciever = reciever;
}
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public int getIdMessage() {
	return idMessage;
}
public void setIdMessage(int idMessage) {
	this.idMessage = idMessage;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
