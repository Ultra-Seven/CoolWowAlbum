package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IFriendBiz;
import com.fudan2015.dao.impl.FriendDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;

public class FriendBizImpl implements IFriendBiz {
	private FriendDaoImpl uDao=new FriendDaoImpl();
	public List<Friends> getAllFriendList(int id) {
		//get a user's friendsClasses' list
		return uDao.getAllFriendList(id);
	}
	public List<Friend> showClassFriends(String classification, int idFriend) {
		// get all friends in a friendClass
		return uDao.showClassFriends(classification, idFriend);
	}
	public void addFriends(Friends f) {
		// add a friend
		uDao.addFriends(f);
	}
	public void deleteFriend(Friend f) {
		//delete a friend
		uDao.deleteFriend(f);
	}
	public Friends getFriendsByClassId(int classId) {
		// get a friendClass
		return uDao.getFriendsByClassId(classId);
	}
	public void updateFriends(Friends f) {
		// update a friendClass
		uDao.updateFriends(f);
	}
	public void deleteFriends(Friends f) {
		// delete a friendClass
		uDao.deleteFriends(f);
	}
	public Friends getWeifenlei(int id) {
		// get friendClass weifeilei
		return uDao.getWeifenlei(id);
	}
	public void changeFriend(int id, String classification) {
		//move a friend into weifeilei
		uDao.changeFriend(id, classification);
	}
	public void changeFriend(int id, String classification, String newName) {
		// move a friend into target friendClass
		uDao.changeFriend(id, classification, newName);
	}
	public Friend getFriendByTwoId(int idFriend, int friendId) {
		// get a friend
		return uDao.getFriendByTwoId(idFriend, friendId);
	}
	public Friends getFriendsByIdClassification(int id, String classification) {
		// get a friend
		return uDao.getFriendsByIdClassification(id, classification);
	}
	public Friend getFriendById(int id) {
		//get a friend
		return uDao.getFriendById(id);
	}
	

}
