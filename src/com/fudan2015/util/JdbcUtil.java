package com.fudan2015.util;

import java.sql.*;


public class JdbcUtil {
	private String userName;
	private String passwd;
	private String url;
	public JdbcUtil() {
		url="jdbc:mysql://127.0.0.1:3306/coolalbum";
		userName="root";
		passwd="weiziyun2821666";//zb72020600
		//加载数据库
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection myCon=null;
		try {
			myCon=DriverManager.getConnection(url, userName, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCon;
	}
	public void close(ResultSet rs, Statement st, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
}