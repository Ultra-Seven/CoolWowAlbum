package com.fudan2015.entity;

import java.util.List;

public class Friends {
	/*
	 * 用户序号 F
	 * 个人好友分类序号 P
	 * 分类名
	 * 分类中的好友列表
	 * 分类好友数
	 */
   private int idClass;
   private int classId;
   private String classification;
   private List<Friend> friend;
   private int num;
public int getIdClass() {
	return idClass;
}
public void setIdClass(int idClass) {
	this.idClass = idClass;
}
public int getClassId() {
	return classId;
}
public void setClassId(int classId) {
	this.classId = classId;
}
public String getClassification() {
	return classification;
}
public void setClassification(String classification) {
	this.classification = classification;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public List<Friend> getFriend() {
	return friend;
}
public void setFriend(List<Friend> friend) {
	this.friend = friend;
}
}
