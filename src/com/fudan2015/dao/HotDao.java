package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.HotPics;

public interface HotDao {
	//增加一个hot picture
	public void addHot(HotPics hot);
	//删除一个hot picturesɾ
	public void deleteHot(int id);
	//查询hot pictures
	public void updateHot(int id,HotPics hot);
	//返回一个hot pictures的list
	public List<HotPics> showPics(int id);
}
