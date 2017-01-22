package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Photo;

public interface IHotPicsBiz {
	public List<Photo>selectPics();
	//get photos in the hot list
	public String[] personName();
	//get authors in the hot list
}
