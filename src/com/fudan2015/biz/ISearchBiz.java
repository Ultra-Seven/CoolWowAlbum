package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Photo;

public interface ISearchBiz {
	//search photo has the string
	public List<Photo> search(String  search);
}
