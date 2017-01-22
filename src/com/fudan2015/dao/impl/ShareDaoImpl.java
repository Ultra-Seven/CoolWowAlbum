package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.ShareDao;

import com.fudan2015.entity.Photo;
import com.fudan2015.entity.Share;
import com.fudan2015.entity.User;
import com.fudan2015.util.JdbcUtil;

public class ShareDaoImpl implements ShareDao {
	private JdbcUtil util;
	public ShareDaoImpl() {
		util=new JdbcUtil();
	}
	public void addShare(Share share) {
		// TODO Auto-generated method stub
		//获得connection
		Connection con=util.getConnection();
		//插入语句
		String sql="insert into share (id_share,pictureId) values(?,?)";
		PreparedStatement pst=null;
		try {
			//执行插入语句
			pst=con.prepareStatement(sql);
			
			pst.setInt(1, share.getId_share());
			pst.setInt(2, share.getPictureId());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null, pst, con);
		}

	}
	
	public void deleteShare(int id) {
		// TODO Auto-generated method stub
		Connection con=util.getConnection();
		//删除语句
		String sql="delete from share where id='"+id+"'";
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

	public List<Share> showShareAll(int idShare) {
		// TODO Auto-generated method stub
		List<Share> list=new ArrayList<Share>();
		//获得connection
		Connection conn=util.getConnection();
		//从department表中查找id和name
		String sql="select id,pictureId from share where id_share='"+idShare+"'";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			//执行语句
			rs=pst.executeQuery();
			while(rs.next()){
				Share share=new Share();
				//d.setId(rs.getInt(1));
				share.setId(rs.getInt("id"));
				share.setPictureId(rs.getInt("pictureId"));
				Photo photo=new PhotoDaoImpl().showPhotoZX(share.getPictureId());
				share.setUrl("uploadFiles/"+photo.getPicsUrl());
				share.setName(photo.getPicName());
				int idtwo=photo.getId();
				User user=new UserDaoImpl().findUserById(idtwo);
				share.setMaster(user.getAccount());
				list.add(share);
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
	public Share showShareNotById(int idShare, int pictureId) {
		// TODO Auto-generated method stub
		Share s=new Share();
		//获得connection
		Connection conn=util.getConnection();
		//从department表中查找id和name
		String sql="select * from share where id_share='"+idShare+"'"+"and pictureId="+pictureId;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			//执行语句
			rs=pst.executeQuery();
			while(rs.next()){
				//d.setId(rs.getInt(1));
				s.setId(rs.getInt("id"));
				s.setPictureId(rs.getInt("pictureId"));
				s.setId_share(idShare);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭
			util.close(rs, pst, conn);
		}
		return s;
	}

}

