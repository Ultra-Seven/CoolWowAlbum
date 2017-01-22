package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;

public interface IAdminBiz {
	/*
	 * change hot pictures
	 * change a user's state
	 * 获得所有用户
	 */
     public void changeHotPictures(List<Photo> pl);
     public void userState(User u,int state);
     public List<User> showAllUser();
}
