package com.fudan2015.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fudan2015.biz.impl.FriendBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class showEditAction {
	private Map<String,Object> session;
	User u;
	private List<Photo> photo;
	private PhotoBizImpl biz=new PhotoBizImpl();
	
	
	public String getAllEditPic(){
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		photo=biz.getAllEditPic(u.getId());
		Iterator iterator=photo.iterator();
		while(iterator.hasNext()){
			Photo temp=(Photo) iterator.next();
			temp.setPicsUrl("uploadFiles/"+temp.getPicsUrl());
		}
		session.put("photoZX", photo);
		return "success";
	}

}
