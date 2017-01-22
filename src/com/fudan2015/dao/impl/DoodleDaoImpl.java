package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.IDoodleDao;
import com.fudan2015.entity.Doodle;
import com.fudan2015.entity.Friend;
import com.fudan2015.util.JdbcUtil;

public class DoodleDaoImpl implements IDoodleDao {
	private JdbcUtil util;

	public DoodleDaoImpl(){
		util=new JdbcUtil();
	}
	public void addDoodle(Doodle d) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into doodle(id_doodle,picId,doodle_url) values(?,?,?)";
		//set sql statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, d.getId_doodle());
			pst.setInt(2, d.getPicId());
			pst.setString(3, d.getDoodle_url());

			//set parameters
			pst.execute();
			//execute the statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close statement and connection
		}

	}

	public void deleteDoodle(int id) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from doodle where id=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
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

	public List<Doodle> showDoodleByAccount(int userId) {
		// TODO Auto-generated method stub
		List<Doodle> list=new ArrayList<Doodle>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from doodle where id_doodle="+userId;
		//create statemnt
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Doodle d=new Doodle();
				//if the friend is correct ,save it one by one
				d.setDoodle_url(rs.getString("doodle_url"));
				d.setId(rs.getInt("id"));
				d.setId_doodle(rs.getInt("id_doodle"));
				d.setPicId(rs.getInt("picId"));
				list.add(d);

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

	public Doodle showDoodleById(int id) {
		// TODO Auto-generated method stub
		Doodle d=new Doodle();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from doodle where id="+id;
		//create statemnt
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				
				//if the friend is correct ,save it one by one
				d.setDoodle_url(rs.getString("doodle_url"));
				d.setId(rs.getInt("id"));
				d.setId_doodle(rs.getInt("id_doodle"));
				d.setPicId(rs.getInt("picId"));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close the connection and statement
		}

		return d;
	}

	public void updateDoodle(Doodle d) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="update doodle set id_doodle=?,picId=?,doodle_url=?,id=? where classId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, d.getId_doodle());
			pst.setInt(2, d.getPicId());
			pst.setString(3, d.getDoodle_url());
			pst.setInt(4, d.getId());
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
	public Doodle showDoodleByPicId(int picId) {
		// TODO Auto-generated method stub
		Doodle d=new Doodle();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from doodle where picId="+picId;
		//create statemnt
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				
				//if the friend is correct ,save it one by one
				d.setDoodle_url(rs.getString("doodle_url"));
				d.setId(rs.getInt("id"));
				d.setId_doodle(rs.getInt("id_doodle"));
				d.setPicId(rs.getInt("picId"));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close the connection and statement
		}

		return d;
	}

}
