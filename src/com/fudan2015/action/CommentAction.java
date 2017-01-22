package com.fudan2015.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.CommentBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class CommentAction {
	private CommentBizImpl biz=new CommentBizImpl();
	private PhotoBizImpl pBiz=new PhotoBizImpl();
	private UserBizImpl uBiz=new UserBizImpl();
	//bizs used
	private String photoId;
	private Comment c=new Comment();
	//comment used to delete or add
	private int picId=1;
	//id of picture the comment belong
	private String observer="";
	//name of the author of the comment
	private String picUrl="";
	//url of the picture of the comment
	private String comment="";
	//content of the comment
	private int id=0;
	//id of the comment
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();

	@SuppressWarnings("unchecked")
	public String newComment(){
		//add a comment
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		
		User user=(User)session.get("logUser");
		//get user's id
		Comment com=new Comment();
		com.setPicId(Integer.parseInt(photoId));
		com.setComment(comment);
		com.setObserver(user.getAccount());
		biz.newComment(com);
		
		//update pic 
		Photo photo=pBiz.showPhotoById(Integer.parseInt(photoId));
	
		
		
		session = ActionContext.getContext().getSession();
		User u1=(User) session.get("logUser");
		User u2=(User) session.get("showAccount");
		String data="{'comment':'"+comment+"','observer':'"+user.getAccount()+"','date':'刚刚','id':'delete','head':'"+user.getImage()+"','commentNum':'"+photo.getComments()+"'}";
		
		try { 
			response.getWriter().write(data); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		//save it into database
		return null;
	}
	public String deleteComment(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		
		biz.deleteComment(id);
		System.out.println("in");
		Photo photo=pBiz.showPhotoById(Integer.parseInt(photoId));
		String str=photo.getComments()+"";
		System.out.println(str);
		//delete the comment by id from database
		try { 
			response.getWriter().write(str); 
			System.out.println(str);
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return null;
	}
	public String showComment() {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Comment> list=new ArrayList<Comment>();
		list=biz.allComments(Integer.parseInt(photoId));
		String data="";
		
		for(int i=0;i<list.size();i++) {
			String url=uBiz.findUserByAccount(list.get(i).getObserver()).getImage();
			data=data+"{'comment':'"+list.get(i).getComment()+"','observer':'"+list.get(i).getObserver()+"','date':'"+list.get(i).getDate()+"','id':'"+list.get(i).getId()+"','head':'"+url+"'}";
			if(i!=list.size()-1) {
				data=data+",";
			}
		}
		//System.out.println(data);
		//session.put("showPhotoObj",photo.getPicName());
		//System.out.println(session.get("showPhotoObj"));
		try { 
			response.getWriter().write(data); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 

		//如果不存在，且满足条件，那么就进行改名

		return null;
	}


	//the following are getters and setters used in the action


	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}
	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getObserver() {
		return observer;
	}
	public void setObserver(String observer) {
		this.observer = observer;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public Comment getC() {
		return c;
	}
	public void setC(Comment c) {
		this.c = c;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}



}
