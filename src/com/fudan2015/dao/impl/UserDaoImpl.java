package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.IUserDao;
import com.fudan2015.entity.User;
import com.fudan2015.util.JdbcUtil;

public class UserDaoImpl implements IUserDao {
	private JdbcUtil util;

	public UserDaoImpl(){
		util=new JdbcUtil();
	}

	public void addUser(User u) {
		// TODO Auto-generated method stub
		//获得所有的用户
		List<User> list=showAllUser();
		//用户数量加一作为新用户的id
		int id=list.size()+1;
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into account_infor(id,account,password,email,gender,description,album_num,pictures_num,friends_num,message_num,share_num,image,state,identity,passwdQuestion,passwdAnswer) values(?,?,?,?,?,?,1,0,0,0,0,?,0,0,?,?)";
		//create statement
		PreparedStatement pst=null;
		String sql2="insert into friends_class(id_class,classification,num)values(?,?,0)";
		String sql3="insert into album_infor(id,album,num,date)values(?,?,0,curdate())";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, u.getAccount());
			pst.setString(3, u.getPassword());
			pst.setString(4, u.getEmail());
			pst.setInt(5, 0);
			pst.setString(6, "大家好，我是"+u.getAccount()+",欢迎光临我的相册！");
			pst.setString(7, "headPortrait/default.png");
			pst.setString(8, u.getPasswdQuestion());
			pst.setString(9, u.getPasswdAnswer());
			//set parameters
			pst.execute();
			//execute statement
			pst=conn.prepareStatement(sql2);
			pst.setInt(1, id);
			pst.setString(2, "未分类");
			pst.execute();
			pst=conn.prepareStatement(sql3);
			pst.setInt(1, id);
			pst.setString(2, "默认相册");
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
	}

	public User findUserByAccount(String account) {
		// TODO Auto-generated method stub
		User u=new User();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from account_infor where account='"+account+"'";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				u.setAccount(rs.getString("account"));
				u.setDescription(rs.getString("description"));
				u.setPassword(rs.getString("password"));
				u.setState(rs.getInt("state"));
				u.setAlbumNum(rs.getInt("album_num"));
				u.setEmail(rs.getString("email"));
				u.setFriendsNum(rs.getInt("friends_num"));
				u.setGender(rs.getInt("gender"));
				u.setId(rs.getInt("id"));
				u.setIdentity(rs.getInt("identity"));
				u.setImage(rs.getString("image"));
				u.setPicturesNum(rs.getInt("pictures_num"));
				u.setMessageNum(rs.getInt("message_num"));
				u.setPasswdQuestion(rs.getString("passwdQuestion"));
				u.setPasswdAnswer(rs.getString("passwdAnswer"));
				u.setShareNum(rs.getInt("share_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
		//检验如果user类的密码为空，则返回null，反之，返回user类
		if(u.getAccount()==null) {
			return null;
		}
		else {
			return u;
		}
	}

	
	public List<User> showAllUser() {
		// TODO Auto-generated method stub
		List<User> list=new ArrayList<User>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from account_infor";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){

				User u=new User();
				u.setAccount(rs.getString("account"));
				u.setDescription(rs.getString("description"));
				u.setId(rs.getInt("id"));
				u.setState(rs.getInt("state"));
				u.setIdentity(rs.getInt("identity"));
				u.setFriend(new FriendDaoImpl().getAllFriendById(u.getId()));
				u.setFriendsNum(rs.getInt("friends_num"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setAlbumNum(rs.getInt("album_num"));
				u.setPicturesNum(rs.getInt("pictures_num"));
				u.setImage(rs.getString("image"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
		return list;
	}

	public void updateUser(User u) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="update account_infor set password=?,gender=?,album_num=?,pictures_num=?,friends_num=?,message_num=?,description=?,image=?,state=?,identity=?,share_num=?,email=? where id=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, u.getPassword());
			pst.setInt(2, u.getGender());
			pst.setInt(3, u.getAlbumNum());
			pst.setInt(4, u.getPicturesNum());
			pst.setInt(5, u.getFriendsNum());
			pst.setInt(6, u.getMessageNum());
			pst.setString(7, u.getDescription());
			pst.setString(8, u.getImage());
			pst.setInt(9, u.getState());
			pst.setInt(10, u.getIdentity());
			pst.setInt(11, u.getShareNum());
			pst.setString(12, u.getEmail());	
			pst.setInt(13, u.getId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
	}

	public void userState(User u, int state) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="update account_infor set state=? where id=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setInt(2, u.getId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
	}

	public User findUserById(int id) {
		// TODO Auto-generated method stub
		User u=new User();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from account_infor where id='"+id+"'";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				u.setAccount(rs.getString("account"));
				u.setDescription(rs.getString("description"));
				u.setPassword(rs.getString("password"));
				u.setState(rs.getInt("state"));
				u.setAlbumNum(rs.getInt("album_num"));
				u.setEmail(rs.getString("email"));
				u.setFriendsNum(rs.getInt("friends_num"));
				u.setGender(rs.getInt("gender"));
				u.setId(id);
				u.setIdentity(rs.getInt("identity"));
				u.setImage(rs.getString("image"));
				u.setPicturesNum(rs.getInt("pictures_num"));
				u.setMessageNum(rs.getInt("message_num"));
				u.setPasswdQuestion(rs.getString("passwdQuestion"));
				u.setPasswdAnswer(rs.getString("passwdAnswer"));
				u.setShareNum(rs.getInt("share_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
		return u;
		
	}



}
