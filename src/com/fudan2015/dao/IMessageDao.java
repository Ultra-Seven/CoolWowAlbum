package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Message;

public interface IMessageDao {
	/*
	 * 查询某个站内信
	 * 查询某人所有站内信
	 * 改变站内信状态
	 * 新增站内信
	 * 删除站内信
	 */
	public Message showMessage(int id);
	public List<Message> showAllMessage(int idMessage);
	public void updateMessage(String content, int state, int id);
	public void updateMessage(int state, int id);
	public void newMessage(Message m);
	public void deleteMessage(Message m);
	
	//Added by XuHailong
	public List<Message> showMessagesFrom(String account) ;
	public List<Message> showMessagesTo(String account) ;
}
