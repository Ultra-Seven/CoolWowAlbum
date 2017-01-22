package com.fudan2015.action;

import java.util.List;
import java.util.Map;

import com.fudan2015.biz.impl.HotPicsBizImpl;
import com.fudan2015.entity.Photo;
import com.opensymphony.xwork2.ActionContext;

public class HotAction {
	HotPicsBizImpl biz=new HotPicsBizImpl();
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	@SuppressWarnings("unchecked")
	public String execute() {
		//get hot list
		List<Photo> list=biz.selectPics();
		String[] picName=new String [20];
		String[] personName=biz.personName();
		String[] date=new String [20];
		String[] url=new String [20];
		String[] picUrl=new String [20];
		//prepate to save four parameters of every photo
		for(int i=0;i<list.size();i++) {
			picName[i]=splits(list.get(i).getPicName());
			date[i]=list.get(i).getDate();
			url[i]="uploadFiles/"+list.get(i).getPicsUrl();
			picUrl[i]="visit!visit.action?account="+personName[i]+"&photoId="+list.get(i).getPicId();
		}
		//save information
		session.put("picName", picName);
		session.put("personName", personName);
		session.put("date", date);
		session.put("picsUrl", url);
		session.put("href", picUrl);
		//set information in session
		
		return "success";
	}
	public String splits(String str) {
		//get picture url without .format
		String[] arr=str.split("[.]");
		return arr[0];
	}
}
