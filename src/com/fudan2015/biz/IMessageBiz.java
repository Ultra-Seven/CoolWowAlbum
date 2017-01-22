package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Message;

public interface IMessageBiz {
   public void addMessage(String sender, String reciever, String content, int state);//add a message
   public void delMessage(int id);//delete a message
   public List<Message> getInboxMessages(String account);//get inbox message
   public List<Message> getOutboxMessages(String account);//get outbox message
   public void updateMessage(String content, int state, int id);
}
