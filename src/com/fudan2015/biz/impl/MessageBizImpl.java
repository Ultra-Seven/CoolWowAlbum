package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IMessageBiz;
import com.fudan2015.dao.impl.MessageDaoImpl;
import com.fudan2015.entity.Message;

public class MessageBizImpl implements IMessageBiz 
{
	private MessageDaoImpl mDao = new MessageDaoImpl();

	public void addMessage(String sender, String reciever, String content, int state)
	{
		Message t = new Message();
		t.setSender(sender);
		t.setReciever(reciever);
		t.setContent(content);
		t.setState(state);
		mDao.newMessage(t);
	}

	public void delMessage(int id)
	{
		Message t = new Message();
		t.setId(id);
		mDao.deleteMessage(t);
	}

	public List<Message> getInboxMessages(String account)
	{
		List<Message> msgs = mDao.showMessagesTo(account);
		for (int i = 0; i < msgs.size(); i++) 
		{ 
			if (msgs.get(i).getState() == 3) 
			{ 
				msgs.remove(i); 
				i--; 
			} 
		}
		return msgs;
	}

	public List<Message> getOutboxMessages(String account)
	{
		List<Message> msgs = mDao.showMessagesFrom(account);
		for (int i = 0; i < msgs.size(); i++) 
		{ 
			if (msgs.get(i).getState() == 4) 
			{ 
				msgs.remove(i); 
				i--; 
			} 
		}
		return msgs;
	}

	public void updateMessage(String content, int state, int id)
	{
		if(content == null)
			mDao.updateMessage(state, id);
		else
			mDao.updateMessage(content, state, id);
	}
}
