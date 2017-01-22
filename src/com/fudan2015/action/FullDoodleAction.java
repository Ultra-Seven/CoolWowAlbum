package com.fudan2015.action;

import java.util.Map;

import com.fudan2015.biz.impl.DoodleBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.entity.Doodle;
import com.fudan2015.entity.Photo;
import com.opensymphony.xwork2.ActionContext;

public class FullDoodleAction {
	private String photoId;
	DoodleBizImpl dBiz=new DoodleBizImpl();
	PhotoBizImpl pBiz=new PhotoBizImpl();
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	@SuppressWarnings("unchecked")
	public String full() {
		Photo photo=new Photo();
		photo=pBiz.showPhotoById(Integer.parseInt(photoId));
		System.out.println(photo.getPicsUrl());
		session.put("doodle", "uploadFiles/"+photo.getPicsUrl());
		session.put("doodlePic", photoId);
		return "full";
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoId() {
		return photoId;
	}
}
