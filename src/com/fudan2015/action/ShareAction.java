package com.fudan2015.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.ShareBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.Share;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class ShareAction {
	ShareBizImpl biz=new ShareBizImpl();
	PhotoBizImpl pBiz=new PhotoBizImpl();
	private Map<String,Object> session;
	private User u;
	private List<Share> list;
	private List<Photo> pList;

	
	public String getSharePic(){
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		list=biz.showShareAll(u.getId());
		session.put("sharePics", list);
		return "success";
	}
	public String getFriendSharePic(){
		session = ActionContext.getContext().getSession();
		u=(User) session.get("showAccount");
		pList=pBiz.photoLoadAll(u.getId());
		u=(User) session.get("logUser");
		list=biz.showShareAll(u.getId());
		Iterator iterator=pList.iterator();
		Iterator iterator2=list.iterator();
		while(iterator.hasNext()){
			Photo temp=(Photo) iterator.next();
			temp.setShare(false);
			temp.setPicsUrl("uploadFiles/"+temp.getPicsUrl());
			iterator2=list.iterator();
			while(iterator2.hasNext()){
				Share temp2=(Share) iterator2.next();
				//System.out.println(temp2.getId());
				if(temp2.getPictureId()==temp.getPicId()){
					temp.setShare(true);
					temp.setShareId(temp2.getId());
					break;
				}
			}
		}
		session.put("allPicsZX", pList);
		return "friends";
	}
	public String deleteFriendShare(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String id= request.getParameter("id");
		biz.deleteShare(Integer.parseInt(id));
		session = ActionContext.getContext().getSession();
		u=(User) session.get("showAccount");
		pList=pBiz.photoLoadAll(u.getId());
		u=(User) session.get("logUser");
		u.setShareNum(u.getShareNum()-1);
		new UserBizImpl().updateUser(u);
		u=new UserBizImpl().findUserByAccount(u.getAccount());
		session.put("logUser", u);
		list=biz.showShareAll(u.getId());
		Iterator iterator=pList.iterator();
		Iterator iterator2=list.iterator();
		while(iterator.hasNext()){
			Photo temp=(Photo) iterator.next();
			temp.setShare(false);
			temp.setPicsUrl("uploadFiles/"+temp.getPicsUrl());
			while(iterator2.hasNext()){
				Share temp2=(Share) iterator2.next();
				if(temp2.getPictureId()==temp.getPicId()){
					temp.setShare(true);
					temp.setShareId(temp2.getId());
					break;
				}
			}
		}
		session.put("allPicsZX", pList);
		return null;
	}
	public String deleteShare(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String id= request.getParameter("id");
		biz.deleteShare(Integer.parseInt(id));
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		u.setShareNum(u.getShareNum()-1);
		new UserBizImpl().updateUser(u);
		u=new UserBizImpl().findUserByAccount(u.getAccount());
		session.put("logUser", u);
		list=biz.showShareAll(u.getId());
		session.put("sharePics", list);
		return null;
	}
	public String addFriendShare(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String pictureId= request.getParameter("pictureId");
		Share newShare=new Share();
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		u.setShareNum(u.getShareNum()+1);
		new UserBizImpl().updateUser(u);
		u=new UserBizImpl().findUserByAccount(u.getAccount());
		session.put("logUser", u);
		newShare.setId_share(u.getId());
		newShare.setPictureId(Integer.parseInt(pictureId));
		biz.addShare(newShare);
		session = ActionContext.getContext().getSession();
		u=(User) session.get("showAccount");
		pList=pBiz.photoLoadAll(u.getId());
		u=(User) session.get("logUser");
		list=biz.showShareAll(u.getId());
		Iterator iterator=pList.iterator();
		Iterator iterator2=list.iterator();
		while(iterator.hasNext()){
			Photo temp=(Photo) iterator.next();
			temp.setShare(false);
			temp.setPicsUrl("uploadFiles/"+temp.getPicsUrl());
			while(iterator2.hasNext()){
				Share temp2=(Share) iterator2.next();
				if(temp2.getPictureId()==temp.getPicId()){
					temp.setShare(true);
					temp.setShareId(temp2.getId());
					break;
				}
			}
		}
		session.put("allPicsZX", pList);
		Share sharetwo=biz.showShareNotById(u.getId(), Integer.parseInt(pictureId));
		String newId=sharetwo.getId()+"";
		try { 
		     response.getWriter().write(newId); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	public String addShare(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String pictureId= request.getParameter("pictureId");
		Share newShare=new Share();
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		u.setShareNum(u.getShareNum()+1);
		new UserBizImpl().updateUser(u);
		u=new UserBizImpl().findUserByAccount(u.getAccount());
		session.put("logUser", u);
		newShare.setId_share(u.getId());
		newShare.setPictureId(Integer.parseInt(pictureId));
		biz.addShare(newShare);
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		list=biz.showShareAll(u.getId());
		session.put("sharePics", list);
		Share sharetwo=biz.showShareNotById(u.getId(), Integer.parseInt(pictureId));
		String newId=sharetwo.getId()+"";
		try { 
		     response.getWriter().write(newId); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	
}
