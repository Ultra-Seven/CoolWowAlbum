package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;

public interface IFriendBiz {
	//根据用户的账户查询该用户的所有好友分类
	public List<Friends> getAllFriendList(int id);
	//展现某用户某分类名下的所有好友
	public List<Friend> showClassFriends(String classification,int idFriend);
	//增加好友分类
	public void addFriends(Friends f);
	//删除好友
	public void deleteFriend(Friend f);
	//根据classId获取好友列表
	public Friends getFriendsByClassId(int classId);
	//更新好友列表
	public void updateFriends(Friends f);
	//根据classId删除好友列表
	public void deleteFriends(Friends f);
	//找到某用户下名为未分类的好友列表
	public Friends getWeifenlei(int id);
	//将某用户某分类下的好友的分类名称全部改为未分类
	public void changeFriend(int id,String classification);
	//将某用户的某分类下的好友的分类全部改为另一名称
	public void changeFriend(int id,String classification,String newName);
	//根据用户id和好友id得到该好友
	public Friend getFriendByTwoId(int idFriend,int friendId);
	//根据用户id与好友列表名获取该好友列表
	public Friends getFriendsByIdClassification(int id,String classification);
	//根据id得到好友
	public Friend getFriendById(int id);
}
