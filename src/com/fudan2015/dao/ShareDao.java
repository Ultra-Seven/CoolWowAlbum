package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Share;

public interface ShareDao {
	//通过share对象，增加一个分享
	public void addShare(Share share);
	//通过id删除一个share
	public void deleteShare(int id);
	//通过账号id，返回pictureId列表
	public List<Share> showShareAll(int id_share);
	//通过id_share与pictureId来返回一个share
	public Share showShareNotById(int id_share,int pictureId);
}
