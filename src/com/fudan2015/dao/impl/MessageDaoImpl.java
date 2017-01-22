package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.IMessageDao;
import com.fudan2015.entity.Message;
import com.fudan2015.util.JdbcUtil;

public class MessageDaoImpl implements IMessageDao {
	private JdbcUtil util;

	public MessageDaoImpl(){
		util=new JdbcUtil();
	}
	public void deleteMessage(Message m) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="delete from message where id=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, m.getId());
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}

	public void newMessage(Message m) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="insert into message(reciever,sender,content,date,state,id_message) values(?,?,?,now(),?,?)";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, m.getReciever());
			pst.setString(2, m.getSender());
			pst.setString(3, m.getContent());
			pst.setInt(4, m.getState());
			pst.setInt(5, m.getIdMessage());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}

	public void updateMessage(String content, int state, int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="update message set content=?, state=? where id=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, content);
			pst.setInt(2, state);
			pst.setInt(3, id);
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}
	
	public void updateMessage(int state, int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="update message set state=? where id=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setInt(2, id);
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}
	
	public List<Message> showAllMessage(int idMessage) {
		// TODO Auto-generated method stub
		List<Message> list=new ArrayList<Message>();
		Connection conn=util.getConnection();
		String sql="select*from message";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				if(idMessage==rs.getInt("id_message")){
				Message m=new Message();
				m.setReciever(rs.getString("reciever"));
				m.setSender(rs.getString("sender"));
				m.setContent(rs.getString("content"));
				m.setDate(rs.getString("date"));
				m.setState(rs.getInt("state"));
				list.add(m);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
		}

		return list;
	}
	public Message showMessage(int id) {
		// TODO Auto-generated method stub
		Message m=new Message();
		Connection conn=util.getConnection();
		String sql="select*from message";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				if(id==rs.getInt("id")){
				m.setReciever(rs.getString("reciever"));
				m.setSender(rs.getString("sender"));
				m.setContent(rs.getString("content"));
				m.setDate(rs.getString("date"));
				m.setState(rs.getInt("state"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}

		return m;
	}
	
	//added by XuHailong
	
	//show outbox messages
	public List<Message> showMessagesFrom(String account) 
	{
		List<Message> list= new ArrayList<Message>();
		Connection conn = util.getConnection();
		String sql = "select*from message";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try 
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next() )
			{
				if(account.equals(rs.getString("sender") ) )
				{
					Message m = new Message();
					m.setReciever(rs.getString("reciever") );
					m.setSender(rs.getString("sender") );
					m.setContent(rs.getString("content") );
					m.setDate(rs.getString("date") );
					m.setState(rs.getInt("state") );
					m.setId(rs.getInt("id") );
					list.add(m);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			util.close(rs,pst,conn);
		}

		return list;
	}
	//show inbox messages
	public List<Message> showMessagesTo(String account) 
	{
		List<Message> list= new ArrayList<Message>();
		Connection conn = util.getConnection();
		String sql = "select*from message";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try 
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next() )
			{
				if(account.equals(rs.getString("reciever") ) )
				{
					Message m = new Message();
					m.setReciever(rs.getString("reciever") );
					m.setSender(rs.getString("sender") );
					m.setContent(rs.getString("content") );
					m.setDate(rs.getString("date") );
					m.setState(rs.getInt("state") );
					m.setId(rs.getInt("id") );
					list.add(m);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			util.close(rs,pst,conn);
		}

		return list;
	}
	
}
