package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;

public interface IFriendDao {
	/*
	 * 新增好友
	 * 新增好友分类
	 * 根据id删除好友
	 * 删除好友分类
	 * 移动好友
	 * 更新好友分类
	 * 查询某人所有好友
	 * 根据用户的id查询该用户的所有好友分类
	 * 根据某用户分类名返回该分类下的所有好友
	 * 根据classId得到该用户分类
	 * 找到某用户下名为“未分类”的好友列表
	 * 将某用户的某分类下的好友的分类全部都改为未分类
	 * 将某用户的某分类下的好友的分类全部改为另一名称
	 * 根据用户id得到该用户的所有好友的list
	 * 根据用户id和好友id得到该好友
	 * 根据用户id与好友列表名获取该好友列表
	 * 根据id得到好友
	 */
   public void addFriend(Friend f);
   public void addFriends(Friends f);
   public void deleteFriend(Friend f);
   public void deleteFriends(Friends f);
   public void moveFriend(Friend f,String classification);
   public void updateFriends(Friends f);
   public List<Friend> showAllFriends(int idFriend);
   public List<Friends> getAllFriendList(int id);
   public List<Friend> showClassFriends(String classification,int idFriend);
   public Friends getFriendsByClassId(int classId);
   public Friends getWeifenlei(int id);
   public void changeFriend(int id,String classification);
   public void changeFriend(int id,String classification,String newName);
   public List<Friend> getAllFriendById(int id);
   public Friend getFriendByTwoId(int idFriend,int friendId);
   public Friends getFriendsByIdClassification(int id,String classification);
   public Friend getFriendById(int id);
}
