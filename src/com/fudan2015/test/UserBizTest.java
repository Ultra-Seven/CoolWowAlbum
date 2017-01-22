package com.fudan2015.test;

import org.junit.Test;

import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.User;

public class UserBizTest {
	private UserBizImpl uBiz=new UserBizImpl();
	private User u=new User();
	/*
	 * some arguments about the test User(following)
	 */
	private String account="test";
	private String password="1234";
	private String email="email";
	private int albumNum=1;
	private int picturesNum=2;
	private int friendsNum=3;
	private String image="url";
	private int state=0;
	private int identity=0;
	private String passwdQuestion="q";
	private String passwdAnswer="a";
	@Test
	public void test(){
		u.setAccount(account);//set test user's arguments
		u.setPassword(password);
		u.setEmail(email);
		u.setAlbumNum(albumNum);
		u.setPicturesNum(picturesNum);
		u.setFriendsNum(friendsNum);
		u.setImage(image);
		u.setState(state);
		u.setIdentity(identity);
		u.setPasswdQuestion(passwdQuestion);
		u.setPasswdAnswer(passwdAnswer);
		
		uBiz.userRegist(u);//regist a test User u
		User login=uBiz.userLogin(u);//login a test User u
		System.out.print("user:"+login.getAccount());
		u=uBiz.userFind("weiziyun");//find a existed User and print its info
		System.out.print("user:"+u.getAccount());
		login.setPassword("123");//change test-user's password
		uBiz.userUpdatePasswd(login);
	}
}


