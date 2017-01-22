package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.HotDao;
import com.fudan2015.entity.HotPics;
import com.fudan2015.util.JdbcUtil;

public class HotDaoImpl implements HotDao{
	private JdbcUtil util;
	public HotDaoImpl() {
		util=new JdbcUtil();
	}
	public void updateHot(int id, HotPics hot) {
		// update hot list
		Connection conn=util.getConnection();
		//get connection
		String sql="update hotpictures set idAccount=?,idPic=?,htext=?,hspan=?,introduction=? where id=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, hot.getIdAccount());
			pst.setInt(2, hot.getIdPic());
			pst.setString(3, hot.getHtext());
			pst.setString(4, hot.getHspan());
			pst.setString(5, hot.getIntroduction());
			pst.setInt(6, id);
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

	public void addHot(HotPics hot) {
		// add photo into hot list
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into hotpictures(idAccount,idPic,htext,hspan,introduction) values(?,?,?,?,?)";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, hot.getIdAccount());
			pst.setInt(2, hot.getIdPic());
			pst.setString(3, hot.getHtext());
			pst.setString(4, hot.getHspan());
			pst.setString(5, hot.getIntroduction());
			//set parameters
			pst.execute();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection and statement
		}
	}

	public void deleteHot(int id) {
		// delete photo from hot list
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from hotpictures where id=?";
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

	public List<HotPics> showPics(int id) {
		// get all hot list photos
		List<HotPics> list=new ArrayList<HotPics>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from hotpictures";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				HotPics hot=new HotPics();
				hot.setId(rs.getInt("id"));
				hot.setIdAccount(rs.getInt("idAccount"));
				hot.setIdPic(rs.getInt("idPic"));
				hot.setHtext(rs.getString("htext"));
				hot.setHspan(rs.getString("hspan"));
				hot.setIntroduction(rs.getString("introduction"));
				list.add(hot);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close statement and connection
		}

		return list;
	}

}
