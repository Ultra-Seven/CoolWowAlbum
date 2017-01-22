package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IAdminBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;

public class AdminBizImpl implements IAdminBiz {
private PhotoDaoImpl pDao=new PhotoDaoImpl();
private UserDaoImpl uDao=new UserDaoImpl();
	public void changeHotPictures(List<Photo> pl) {
		//change photos in the hot list
	}
	public void userState(User u,int state) {
		//change a user's state 
		uDao.userState(u, state);
	}
	public List<User> showAllUser() {
		//get all users
		return uDao.showAllUser();
	}
}
