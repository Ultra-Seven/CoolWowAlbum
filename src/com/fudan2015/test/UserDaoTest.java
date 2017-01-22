package com.fudan2015.test;

import java.util.List;

import org.junit.Test;

import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.User;

public class UserDaoTest {//test Dao
	private UserDaoImpl uDao=new UserDaoImpl();
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
		u.setAccount(account);//set the test User's arguments
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
		
		
		
		
		System.out.println(u.getAccount()+"Description:"+u.getDescription()+"State"+u.getState());//get the test User u and print its infor
		uDao.addUser(u);
		u=uDao.findUserByAccount("weiziyun");//find a User existed in database
	    System.out.println(u.getAccount()+"Description:"+u.getDescription()+"State"+u.getState());//print its info
		u.setDescription("update");//change its description
		uDao.updateUser(u);//update
		int newState=1;
	    uDao.userState(u,newState);//change its state
	    u=uDao.findUserByAccount("weiziyun");//find the User again
	    System.out.println(u.getAccount()+"Description:"+u.getDescription()+"State"+u.getState());//print its info
	    List<User> uList=uDao.showAllUser();//find and print all users' name
	    for(int i=0;i<uList.size();i++){
	    	User a=uList.get(i);
	    	System.out.println(a.getAccount());
	    }
	}
}
