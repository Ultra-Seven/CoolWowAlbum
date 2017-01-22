package com.fudan2015.test;

import java.sql.Connection;

import org.junit.Test;

import com.fudan2015.util.JdbcUtil;

public class JdbcUtilTest {
	@Test
	public void testConnection(){
		Connection conn=new JdbcUtil().getConnection();//get connection and print its referring address
		System.out.println("this conn is: "+conn);
	}

}

