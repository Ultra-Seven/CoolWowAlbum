package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IHotPicsBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Photo;

public class HotPicsBizImpl implements IHotPicsBiz {
	PhotoDaoImpl photo=new PhotoDaoImpl();
	UserDaoImpl user=new UserDaoImpl();
	public List<Photo> selectPics() {
		//get new hot list
		return photo.hotPics();
	}
	public String[] personName() {
		//get the authors' names in the hot list
		List<Photo> list=photo.hotPics();
		String[] person=new String[20];
		for(int i=0;i<list.size();i++) {
			person[i]=user.findUserById(list.get(i).getId()).getAccount();
		}
		return person;
	}

}
