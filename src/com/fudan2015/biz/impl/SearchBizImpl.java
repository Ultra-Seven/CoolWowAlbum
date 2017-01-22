package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.ISearchBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Photo;

public class SearchBizImpl implements ISearchBiz {
	private PhotoDaoImpl pDao=new PhotoDaoImpl();
	public SearchBizImpl() {
		
	}
	public List<Photo> search(String search) {
//get all photos has the String
		return pDao.showAllPhotoByString(search);
	}

}
