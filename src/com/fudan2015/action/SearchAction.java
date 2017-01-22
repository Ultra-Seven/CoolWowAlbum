
package com.fudan2015.action;

import java.util.List;
import java.util.Map;

import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.SearchBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{
	SearchBizImpl biz=new SearchBizImpl();
	PhotoBizImpl pBiz=new PhotoBizImpl();
	UserBizImpl uBiz=new UserBizImpl();
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	private String search;
	private String[] photo;
	private String photoView="";
	//HTMLs to show search results(searchResult.jsp)

	@SuppressWarnings("unchecked")
	public String photoView(){
		//view photos in a album
		List<Photo> pList=biz.search(search);
		photo=new String [pList.size()];
		//get all photos
		for(int i=0;i<pList.size();i++){
			User user=uBiz.findUserById(pList.get(i).getId());
			photo[i]="\"<li class=grid-item>"+
			"<a href=visit!visit.action?account="+user.getAccount()+"&photoId="+pList.get(i).getPicId()+" data-id="+pList.get(i).getPicId()+" data-date="+pList.get(i).getDate()+" data-name="+pList.get(i).getPicName()+"><img src=uploadFiles/"+pList.get(i).getPicsUrl()+" />"+
			//get the src of a photo
			"</a></li>\"";
			//photoURLs
			photoView=photoView+photo[i];
			if(i!=pList.size()-1) {
				//if the photo is not the last one we use a comma to split them
				photoView=photoView+",";
			}
			//System.out.println(photoView);
		}
		
		photoView="["+photoView+"]";
		//System.out.println(photoView);
		session.put("search",photoView);//save photos
		return "photoView";

	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search=search;
	}
}
