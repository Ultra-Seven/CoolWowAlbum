package com.fudan2015.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan2015.dao.IPhotoDao;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;
import com.fudan2015.util.JdbcUtil;

public class PhotoDaoImpl implements IPhotoDao {
	private JdbcUtil util;

	public PhotoDaoImpl(){
		util=new JdbcUtil();
	}

	public void addAlbum(Album a) {
		// add a album
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into album_infor(id,album,num,date) values(?,?,0,curdate())";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, a.getId());
			pst.setString(2, a.getAlbum());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection
		}
	}

	public void addPhoto(Photo p) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="insert into pics_infor(id_pics,album,pics_url,picName,date,visit,comments) values(?,?,?,?,now(),0,0)";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getAlbum());
			pst.setString(3, p.getPicsUrl());
			pst.setString(4, p.getPicName());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection
		}
	}

	public void deleteAlbum(Album a) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from album_infor where albumId=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, a.getAlbumId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection
		}
	}

	public void deletePhoto(Photo p) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="delete from pics_infor where picId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getPicId());
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

	public List<Photo> showPhotoByAlbum(String album, int id) {
		// TODO Auto-generated method stub
		List<Photo> list=new ArrayList<Photo>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from pics_infor order by picId desc";
		//create statement
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				if(album.equals(rs.getString("album"))&&id==rs.getInt("id_pics")){
					Photo p=new Photo();
					p.setId(rs.getInt("id_pics"));
					p.setAlbum(rs.getString("album"));
					p.setPicsUrl(rs.getString("pics_url"));
					p.setPicId(rs.getInt("picId"));
					p.setPicName(rs.getString("picName"));
					p.setDate(rs.getString("date"));
					p.setVisit(rs.getInt("visit"));
					p.setComments(rs.getInt("comments"));
					list.add(p);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}

		return list;
	}

	public List<Photo> showPhotoByUser(int id) {
		// TODO Auto-generated method stub
		List<Photo> list=new ArrayList<Photo>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from pics_infor order by picId desc";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				if(id==rs.getInt("id_pics")){
					Photo p=new Photo();
					p.setId(rs.getInt("id_pics"));
					p.setAlbum(rs.getString("album"));
					p.setPicsUrl(rs.getString("pics_url"));
					p.setPicId(rs.getInt("picId"));
					p.setPicName(rs.getString("picName"));
					p.setDate(rs.getString("date"));
					p.setVisit(rs.getInt("visit"));
					p.setComments(rs.getInt("comments"));
					list.add(p);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}

		return list;
	}

	public void updateAlbum(Album a) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="update album_infor set album=?,num=? where albumId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, a.getAlbum());
			pst.setInt(2, a.getNum());
			pst.setInt(3, a.getAlbumId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection
		}
	}

	public void updatePhoto(Photo p) {
		// TODO Auto-generated method stub
		Connection conn=util.getConnection();
		//get connection
		String sql="update pics_infor set picName=?,visit=?,comments=? where picId=?";
		//create statement
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, p.getPicName());
			pst.setInt(2, p.getVisit());
			pst.setInt(3, p.getComments());
			pst.setInt(4, p.getPicId());
			//set parameters
			pst.execute();
			//execute statement
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}finally{
			util.close(null,pst,conn);
			//close connection
		}
	}

	public List<Album> showAlbumByUser(int id) {
		// TODO Auto-generated method stub
		List<Album> list=new ArrayList<Album>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from album_infor where id='"+id+"' order by albumId desc";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while(rs.next()){
				if(id==rs.getInt("id")){
					Album a=new Album();
					a.setId(rs.getInt("id"));
					a.setAlbum(rs.getString("album"));
					a.setAlbumId(rs.getInt("albumId"));
					a.setNum(rs.getInt("num"));
					a.setDate(rs.getString("date"));
					list.add(a);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}
		return list;
	}

	public Album showAlbumById(int id) {
		// TODO Auto-generated method stub
		Album album=new Album();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from album_infor where albumId='"+id+"'";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			if (rs.next()){
				album.setId(rs.getInt("id"));
				album.setAlbum(rs.getString("album"));
				album.setAlbumId(rs.getInt("albumId"));
				album.setNum(rs.getInt("num"));
				album.setDate(rs.getString("date"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}
		return album;
	}
	public List<Photo> hotPics() {
		// TODO Auto-generated method stub
		int index=0;
		List<Photo> hot=new ArrayList<Photo>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from pics_infor order by visit desc";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while (rs.next()){
				if(index<20) {
					Photo photo=new Photo();
					photo.setAlbum(rs.getString("album"));
					photo.setComments(rs.getInt("comments"));
					photo.setDate(rs.getString("date"));
					photo.setId(rs.getInt("id_pics"));
					photo.setPicId(rs.getInt("picId"));
					photo.setPicName(rs.getString("picName"));
					photo.setPicsUrl(rs.getString("pics_url"));
					photo.setVisit(rs.getInt("visit"));
					hot.add(photo);
				}
				else {
					break;
				}
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}

		return hot;
	}

	public List<Photo> showAllPhotoByString(String str) {
		// TODO Auto-generated method stub
		UserDaoImpl user=new UserDaoImpl();
		List<Photo> list=new ArrayList<Photo>();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from pics_infor ";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			while (rs.next()){
				if(checkRelation(rs.getString("picName"),str)||checkRelation(user.findUserById(rs.getInt("id_pics")).getAccount(),str)) {
					Photo photo=new Photo();
					photo.setAlbum(rs.getString("album"));
					photo.setComments(rs.getInt("comments"));
					photo.setDate(rs.getString("date"));
					photo.setId(rs.getInt("id_pics"));
					photo.setPicId(rs.getInt("picId"));
					photo.setPicName(rs.getString("picName"));
					photo.setPicsUrl(rs.getString("pics_url"));
					photo.setVisit(rs.getInt("visit"));
					list.add(photo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}

		changeList(list,str);

		return list;
	}
	public boolean checkRelation(String target,String str) {
		boolean check=false;
		if(target.contains(str)) {
			check=true;
		}
		return check;
	}
	public void changeList(List<Photo>list,String str) {
		//搜素按照片名和人名进行搜索，其中，照片名权重为60%，人名权重为40%
		//Integer[] picNameLength=new Integer[list.size()];
		//Integer[] personNameLength=new Integer[list.size()];
		UserDaoImpl user=new UserDaoImpl();
		Double[] weight=new Double[list.size()];
		for(int i=0;i<list.size();i++) {
			int strLength=str.length();
			int picNameLength=list.get(i).getPicName().length();
			int personLength=user.findUserById(list.get(i).getId()).getAccount().length();
			double weight1=(strLength*1.0/picNameLength)*0.6;
			double weight2=(strLength*1.0/personLength)*0.4;
			weight[i]=weight1+weight2;
		}
		for(int k=0;k<list.size()-1;k++) {
			int min = k;  
			for(int i=k+1; i<list.size();i++) {  
				if(weight[i]<weight[min]) {  
					min = i;  
				}  
			}  
			if(k != min) {  
				double temp = weight[k];  
				weight[k] = weight[min];  
				weight[min] = temp; 
				Photo temp_photo=list.get(k);
				list.set(k, list.get(min));
				list.set(min, temp_photo);
			}  
		}

	}

	public Photo showPhotoById(int id) {
		// TODO Auto-generated method stub
		Photo photo=new Photo();
		Connection conn=util.getConnection();
		//get connection
		String sql="select * from pics_infor where picId='"+id+"'";
		//create statement
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			//execute statement
			if (rs.next()){
				//System.out.println("insql");
				photo.setAlbum(rs.getString("album"));
				photo.setComments(rs.getInt("comments"));
				photo.setDate(rs.getString("date"));
				photo.setId(rs.getInt("id_pics"));
				photo.setPicId(rs.getInt("picId"));
				photo.setPicName(rs.getString("picName"));
				photo.setPicsUrl(rs.getString("pics_url"));
				photo.setVisit(rs.getInt("visit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
			//close connection
		}
		//System.out.println("sql:"+photo.getId());
		return photo;
	}
	public Photo showPhotoZX(int id) {
		// TODO Auto-generated method stub
		Photo photo=new Photo();
		Connection conn=util.getConnection();
		String sql="select * from pics_infor where picId="+id;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while (rs.next()){
				photo.setAlbum(rs.getString("album"));
				photo.setComments(rs.getInt("comments"));
				photo.setDate(rs.getString("date"));
				photo.setId(rs.getInt("id_pics"));
				photo.setPicId(rs.getInt("picId"));
				photo.setPicName(rs.getString("picName"));
				photo.setPicsUrl(rs.getString("pics_url"));
				photo.setVisit(rs.getInt("visit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
		}
		return photo;
	}

	public List<Photo> getAllEditPic(int id) {
		// TODO Auto-generated method stub
		List<Photo> p=new ArrayList();
		Connection conn=util.getConnection();
		String sql="select * from pics_infor where id_pics="+id;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while (rs.next()){
				Photo photo=new Photo();
				photo.setPicId(rs.getInt("picId"));
				photo.setPicsUrl(rs.getString("pics_url"));
				p.add(photo);
			}
			sql="select * from friends_infor where id_friend="+id;
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				int friendId=rs.getInt("friendId");
				sql="select * from pics_infor where id_pics="+friendId;
				pst=conn.prepareStatement(sql);
				rs2=pst.executeQuery();
				while (rs2.next()){
					Photo photo=new Photo();
					photo.setPicId(rs2.getInt("picId"));
					photo.setPicsUrl(rs2.getString("pics_url"));
					p.add(photo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs,pst,conn);
		}
		return p;
	}
	



}
