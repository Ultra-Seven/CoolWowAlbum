package com.fudan2015.action;

import java.util.List;
import java.util.Map;

import com.fudan2015.biz.impl.DoodleBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.entity.Doodle;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class DoodleAction {
	PhotoBizImpl pBiz=new PhotoBizImpl();
	DoodleBizImpl dBiz=new DoodleBizImpl();
	String userId;
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	public DoodleAction() {

	}
	@SuppressWarnings("unchecked")
	public String showWall() {
		System.out.println("in");
		String show="";
		int id=0;
		if(userId.equals("1")) {
			id=((User)session.get("logUser")).getId();
		}
		else {
			id=((User)session.get("showAccount")).getId();
		}
		List<Doodle> list=dBiz.showDoodleByAccount(id);

		for(int i=0;i<list.size();i++) {
			int picId=list.get(i).getPicId();
			Photo p=pBiz.showPhotoById(picId);
			String li="<li><img src="+list.get(i).getDoodle_url()+" data-img="+list.get(i).getDoodle_url()+" />"+
			"<div><h2>"+p.getPicName()+"</h2><p>Date:"+p.getDate()+"</p></div></li>";
			show=show+li;
		}
		show="<ul class=iw_thumbs id=iw_thumbs>"+show+"</ul>";
		session.put("doodleView", show);
		return "wall";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
