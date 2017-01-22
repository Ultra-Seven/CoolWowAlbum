package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IUserBiz;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.User;

public class UserBizImpl implements IUserBiz {
	private UserDaoImpl uDao=new UserDaoImpl();

	public void userFaceUpdate(User u) {
		//update a user's face
	}

	public void userFindPasswd(User u) {
		//find a user's password

	}

	public User userLogin(User u) {
		// search from the database about the user ready to login
		return uDao.findUserByAccount(u.getAccount());
	}

	public void userRegist(User u) {
		// add a user
		uDao.addUser(u);
	}
	public void userUpdatePasswd(User u) {
		// update a user's password
		uDao.updateUser(u);
	}

	public User userFind(String account) {
		//find a user by his account
		return uDao.findUserByAccount(account);
	}

	public void userUpdateDescription(User u) {
		// update a user's description
		uDao.updateUser(u);
	}

	public List<User> userAll() {
		// get all users
		return uDao.showAllUser();
	}

	public User findUserById(int id) {
		// find user by his id
		return uDao.findUserById(id);
	}
	public User findUserByAccount(String account) {
		// find a user by his account(different name)
		return uDao.findUserByAccount(account);
	}

	public void updateUser(User u) {
		// update user's information
		uDao.updateUser(u);
	}

}
