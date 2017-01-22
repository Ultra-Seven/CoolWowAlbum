package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fudan2015.dao.CommentDao;
import com.fudan2015.entity.Comment;
import com.fudan2015.util.JdbcUtil;

public class CommentDaoImpl implements CommentDao {
	private JdbcUtil util;
	public CommentDaoImpl() {
		util=new JdbcUtil();
	}
	
	//实现增加评论到数据库方法
	public void addComment(Comment com) {
		// TODO Auto-generated method stub
		//获得connection
		Connection con=util.getConnection();
		//插入语句
		String sql="insert into comments_infor (comment,observer,date,picId)values(?,?,now(),?)";
		PreparedStatement pst=null;
		try {
			//执行插入语句
			pst=con.prepareStatement(sql);
			pst.setString(1, com.getComment());
			pst.setString(2, com.getObserver());
			pst.setInt(3, com.getPicId());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null, pst, con);
		}

	}
	//实现删除评论到数据库的方法
	public void deleteComment(int id) {
		// TODO Auto-generated method stub
		Connection con=util.getConnection();
		//删除语句
		String sql="delete from comments_infor where id='"+id+"'";
		PreparedStatement pst=null;
		try {
			//执行语句
			pst=con.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			util.close(null, pst, con);
		}
	}
	//实现展示评论的方法
	public List<Comment> showCommentAll(int picId) {
		// TODO Auto-generated method stub
		List<Comment> list=new ArrayList<Comment>();
		//获得connection
		Connection conn=util.getConnection();
		//从department表中查找id和name
		String sql="select id,comment,observer,date from comments_infor where picId='"+picId+"'";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			//执行语句
			rs=pst.executeQuery();
			while(rs.next()){
				Comment com=new Comment();
				//d.setId(rs.getInt(1));
				com.setComment(rs.getString("comment"));
				com.setDate(rs.getString("date"));
				com.setId(rs.getInt("id"));
				com.setObserver(rs.getString("observer"));
				com.setPicId(picId);
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭
			util.close(rs, pst, conn);
		}
		return list;
		
	}

	public Comment showCommentById(int id) {
		// TODO Auto-generated method stub
		Comment com=new Comment();
		Connection conn=util.getConnection();
		String sql="select * from comments_infor where id='"+id+"'";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			//执行语句
			rs=pst.executeQuery();
			while(rs.next()){
				//d.setId(rs.getInt(1));
				com.setComment(rs.getString("comment"));
				com.setDate(rs.getString("date"));
				com.setId(rs.getInt("id"));
				com.setObserver(rs.getString("observer"));
				com.setPicId(rs.getInt("picId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭
			util.close(rs, pst, conn);
		}
		return com;
	}

}
