package com.fudan2015.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudan2015.biz.impl.ManageBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.fudan2015.util.JSONUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport {
	private PhotoBizImpl pBiz=new PhotoBizImpl();
	private UserBizImpl uBiz=new UserBizImpl();
	ManageBizImpl biz=new ManageBizImpl();
	JSONUtil util=new JSONUtil();
	private String photoId;
	//id of photo
	private String photoName;
	//name of photo
	private String deleteId;
	//id of delete photo
	private String albumId;
	//id of album
	private String photoInfo="";
	//string to save photos' information
	private String albumName;
	//name of album
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	public String json(){  
		util.setId_json(photoId);
		util.setName_json(photoName);
		List<Photo> list=util.returnPhoto();
		biz.updatePhoto(list);
		return "json";  
	}
	@SuppressWarnings("unchecked")
	public String delete() {
		System.out.println("in");
		int id=Integer.parseInt(session.get("userId").toString());
		util.setId_json(deleteId);
		List<Photo> list=util.returnPhoto2();
		biz.deletePhoto(list, Integer.parseInt(albumId));
		
		photoInfo="<h1 id=photoH1 class=yahei data-id="+albumId+">"+pBiz.albumSearch(albumName, id).getAlbum()+"&nbsp;" +
		"</h1><em class=\"pic-num yahei\">("+pBiz.albumSearch(albumName, id).getNum()+")" +
		"</em>";
		//photoInfo
		session.put("photoInfo", photoInfo);
		//System.out.println(photoInfo);
		//save photoInfo in session
		User user=uBiz.findUserById(id);
		session.put("logUser", user);
		session.put("showAccount", user);
		return "json"; 
	}
	
	
	//following are getters and setters
	
	
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}

	public String getDeleteId() {
		return deleteId;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
}
