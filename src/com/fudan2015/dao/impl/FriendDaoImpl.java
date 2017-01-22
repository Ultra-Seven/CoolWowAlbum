package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.IFriendDao;
import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;
import com.fudan2015.entity.User;
import com.fudan2015.util.JdbcUtil;

public class FriendDaoImpl implements IFriendDao {
	private JdbcUtil util;

	public FriendDaoImpl(){
		util=new JdbcUtil();
	}
	public void addFriend(Friend f) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into friends_infor(id_friend,classification,friendId) values(?,?,?)";
		//set sql statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, f.getIdFriend());
			pst.setString(2, f.getClassification());
			pst.setInt(3, f.getFriendId());
			//set parameters
			pst.execute();
			//execute the statement
			sql="update account_infor set friends_num=? where id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(2, f.getIdFriend());
			User u=new UserDaoImpl().findUserById(f.getIdFriend());
			pst.setInt(1, u.getFriendsNum()+1);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close statement and connection
		}
	}

	public void addFriends(Friends f) {
		// add a friendClass
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into friends_class(id_class,classification,num) values(?,?,0)";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, f.getIdClass());
			pst.setString(2, f.getClassification());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
	}

	public void deleteFriend(Friend f) {
		// delete a friend
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from friends_infor where id=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, f.getId());
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

	public void deleteFriends(Friends f) {
		// delete a friendClass
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from friends_class where classId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, f.getClassId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close the connection and statement
		}
	}

	public void moveFriend(Friend f,String classification) {
		// move a friend into another friendClass
		Connection conn=util.getConnection();
		//get connection
		String sql="update friends_infor set classification=? where classification=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, classification);
			pst.setString(2, f.getClassification());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close the connection and statement
		}
	}

	public void updateFriends(Friends f) {
		// update a friendClass
		Connection conn=util.getConnection();
		//get connection
		String sql="update friends_class set classification=?,num=? where classId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, f.getClassification());
			pst.setInt(2, f.getNum());
			pst.setInt(3, f.getClassId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close the connection and statement
		}
	}

	public List<Friend> showAllFriends(int idFriend) {
		// show all friend in a friendClass
		List<Friend> list=new ArrayList<Friend>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select*from friends_infor";
		//create statemnt
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				if(idFriend==rs.getInt("id_friend")){
				Friend f=new Friend();
				//if the friend is correct ,save it one by one
				f.setClassification(rs.getString("classification"));
				f.setFriendId(rs.getInt("friendId"));
				list.add(f);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close the connection and statement
		}

		return list;
	}

	public List<Friend> showClassFriends(String classification,int idFriend) {
		// TODO Auto-generated method stub
		List<Friend> list=new ArrayList<Friend>();
		Connection conn=util.getConnection();
		String sql="select*from friends_infor";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				if(classification.equals(rs.getString("classification"))&&idFriend==rs.getInt("id_friend")){
				Friend f=new Friend();
				f.setClassification(rs.getString("classification"));
				f.setFriendId(rs.getInt("friendId"));
				list.add(f);
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
	public List<Friends> getAllFriendList(int id) {
		// TODO Auto-generated method stub
		List<Friends> list=new ArrayList<Friends>();
		List<Friend> list2 = new ArrayList<Friend>();
		Connection conn=util.getConnection();
		String sql="select * from friends_class where id_class="+id;
		PreparedStatement pst=null;
		ResultSet rs=null;
		UserDaoImpl dao=new UserDaoImpl();
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Friends f=new Friends();
				f.setClassification(rs.getString("classification"));
				f.setNum(rs.getInt("num"));
				f.setIdClass(id);
				f.setClassId(rs.getInt("classId"));
				sql="select * from friends_infor where classification='"+f.getClassification()+"'";
				pst=conn.prepareStatement(sql);
				ResultSet rs2=pst.executeQuery();
				list2=new ArrayList<Friend>();
				while(rs2.next()){
					if(rs2.getInt("id_friend")==id){
						Friend f2=new Friend();
						f2.setClassification(f.getClassification());
						f2.setFriendId(rs2.getInt("friendId"));
						f2.setId(rs2.getInt("id"));
						f2.setIdFriend(id);
						User u2=new User();
						u2=dao.findUserById(rs2.getInt("friendId"));
						f2.setName(u2.getAccount());
						f2.setIntroduction(u2.getDescription());
						f2.setImage(u2.getImage());
						list2.add(f2);
					}
				}
				f.setFriend(list2);
				list.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
		}

		return list;
	}
	public Friends getFriendsByClassId(int classId) {
		// TODO Auto-generated method stub
		Friends friends=new Friends();
		Connection conn=util.getConnection();
		String sql="select * from friends_class where classId="+classId;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				friends.setClassId(classId);
				friends.setClassification(rs.getString("classification"));
				friends.setNum(rs.getInt("num"));
				friends.setIdClass(rs.getInt("id_class"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
		}

		return friends;
	}
	public Friends getWeifenlei(int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		Friends friends=new Friends();
		String sql="select * from friends_class where id_class=? and classification=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,"未分类");
			rs=pst.executeQuery();
			while(rs.next()){
				friends.setClassId(rs.getInt("classId"));
				friends.setClassification("未分类");
				friends.setNum(rs.getInt("num"));
				friends.setIdClass(id);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
		return friends;
	}
	public void changeFriend(int id, String classification) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="update friends_infor set classification=? where id_friend=? and classification=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, "未分类");
			pst.setInt(2,id);
			pst.setString(3,classification);
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}
	public void changeFriend(int id, String classification, String newName) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		String sql="update friends_infor set classification=? where id_friend=? and classification=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1,newName);
			pst.setInt(2,id);
			pst.setString(3,classification);
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
	}
	public List<Friend> getAllFriendById(int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		List<Friend> friend=new ArrayList();
		String sql="select * from friends_infor where id_friend=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			while(rs.next()){
				Friend f=new Friend();
				f.setIdFriend(id);
				f.setId(rs.getInt("id"));
				f.setFriendId(rs.getInt("friendId"));
				f.setClassification(rs.getString("classification"));
				User u=new UserDaoImpl().findUserById(f.getFriendId());
				f.setName(u.getAccount());
				f.setIntroduction(u.getDescription());
				f.setState(u.getState());
				friend.add(f);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
		return friend;
	}
	public Friend getFriendByTwoId(int idFriend, int friendId) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		Friend friend=new Friend();
		String sql="select * from friends_infor where id_friend=? and friendId=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1,idFriend);
			pst.setInt(2,friendId);
			rs=pst.executeQuery();
			while(rs.next()){
				friend.setIdFriend(idFriend);
				friend.setId(rs.getInt("id"));
				friend.setFriendId(friendId);
				friend.setClassification(rs.getString("classification"));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
		return friend;
	}
	public Friends getFriendsByIdClassification(int id, String classification) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		Friends friends=new Friends();
		String sql="select * from friends_class where id_class=? and classification=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,classification);
			rs=pst.executeQuery();
			while(rs.next()){
				friends.setClassId(rs.getInt("classId"));
				friends.setIdClass(id);
				friends.setNum(rs.getInt("num"));
				friends.setClassification(classification);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
		return friends;
	}
	public Friend getFriendById(int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		Friend friend=new Friend();
		String sql="select * from friends_infor where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			while(rs.next()){
				friend.setIdFriend(rs.getInt("id_friend"));
				friend.setId(rs.getInt("id"));
				friend.setFriendId(rs.getInt("friendId"));
				friend.setClassification(rs.getString("classification"));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
		}
		return friend;
	}

}
