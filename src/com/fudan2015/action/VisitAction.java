package com.fudan2015.action;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.CommentBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class VisitAction {
	PhotoBizImpl pBiz=new PhotoBizImpl();
	UserBizImpl uBiz=new UserBizImpl();
	CommentBizImpl cBiz=new CommentBizImpl();
	private String account;
	private String photoId;
	private String[] photo;
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	@SuppressWarnings({ "unchecked", "unchecked" })
	
	
	public String visit() {
		User user=uBiz.findUserByAccount(account);
		session.put("showAccount",user);
		session.put("userId",user.getId());
		Photo photoObj=pBiz.showPhotoById(Integer.parseInt(photoId));
		String album="default";
		String albumName=photoObj.getAlbum();
		
		
		if(session.get("userId")!=null){//check userId & albumName
			//System.out.println(albumName);
			String login=((User)session.get("logUser")).getAccount();
			String show=((User)session.get("showAccount")).getAccount();
			String photoView="";
			String comments="";
			String photoInfo="";
			int albumId=pBiz.showAlbumByPic(Integer.parseInt(photoId)).getAlbumId();
			
			int id=Integer.parseInt(session.get("userId").toString());
			List<Photo> pList=pBiz.photoLoad(id, albumName);
			//get all photos
			photo=new String [pList.size()];
			//System.out.println(pList.size());
			//give a size to photo(same as the album)
			for(int i=0;i<pList.size();i++){
				//System.out.println(i);
				if(!login.equals(show)) {
					photo[i]="\"<li class=grid-item>"+
					"<a href=uploadFiles/"+pList.get(i).getPicsUrl()+" data-id="+pList.get(i).getPicId()+" data-date="+pList.get(i).getDate()+" data-name="+pList.get(i).getPicName()+"><img src=uploadFiles/"+pList.get(i).getPicsUrl()+" />"+
					//get the src of a photo
					"</a>" +
					"</li>\"";
				}
				else {
					photo[i]="\"<li class=grid-item>"+
					"<a href=uploadFiles/"+pList.get(i).getPicsUrl()+" data-id="+pList.get(i).getPicId()+" data-date="+pList.get(i).getDate()+" data-name="+pList.get(i).getPicName()+"><img src=uploadFiles/"+pList.get(i).getPicsUrl()+" />"+
					//get the src of a photo
					"</a></li>\"";
				}
				photoView=photoView+photo[i];
				//add the code of one photo into the whole code
				if(i!=pList.size()-1) {
					//if the photo is not the last one we use a comma to split them
					photoView=photoView+",";
				}
				int picId=pList.get(i).getPicId();
				List<Comment> cList=cBiz.allComments(picId);
				String url=pList.get(i).getPicsUrl();
				StringTokenizer s=new StringTokenizer(url,".");
				String name=s.nextToken();
				//after use Tokenizer the url without .format will be use as an id
				
				for(int j=0;j<cList.size();j++){
					//get the comments of every picture
					comments+="<li><div class=\"comment-list-item "+name+"\"><a target=\"_blank\" " +
					"href=\"/u/443332106\"> <img src=\"#\" width=\"30\" height=\"30\"> " +
					"</a><div><a target=\"_blank\" href=\"/u/443332106\">"+cList.get(j).getComment()+"<strong>"+cList.get(j).getObserver()+"说：</strong>" +
					//get content and author
					"</a><blockquote></blockquote><cite> <span>"+cList.get(j).getDate()+"</span><a class" +
					//get date
					"=\"delete-comment\" href=\"\" onclick=\"deleteData("+cList.get(j).getId()+")\">删除</a></span> </cite></div></div></li>";
					//define code used for delete
				}

			}


			photoView="["+photoView+"]";
			photoInfo="<h1 id=photoH1 class=yahei data-id="+albumId+">"+pBiz.albumSearch(albumName, id).getAlbum()+"&nbsp;" +
			"</h1><em class=\"pic-num yahei\">("+pBiz.albumSearch(albumName, id).getNum()+")" +
			"</em>";


			String albumView="";
			
			List<Album> albumList=pBiz.showAlbumByUser(user.getId());
			for(int i=0;i<albumList.size();i++) {
				albumView=albumView+"<li>"+
				"<a href=photo/photoView!photoView.action?albumName="+albumList.get(i).getAlbum()+"&albumId="+albumList.get(i).getAlbumId()+">"+albumList.get(i).getAlbum()+"</a></li>";
			}

			albumView="<ul class=dropUl><li class=dropdown id=accountmenu>"+
			"<a class=dropdown-toggle data-toggle=dropdown href=photo/photoView!photoView.action?albumName="+albumName+"&albumId="+albumId+">"+albumName+"<b class=caret></b></a>"+
			"<ul class=dropdown-menu>"+albumView+"</ul>"+
			"</li></ul>";




			//photoInfo
			//System.out.println(photoView);
			//User user=userBiz.findUserById(id);
			session.put("photo",photoView);
			session.put("albumList",albumView);
			//save photos
			session.put("photoInfo", photoInfo);
			//save Info
			session.put("albumName",albumName);
			//save albumName
			session.put("comments", comments);

			//save comments

			//save into session
			return "visit";
		}
		else{
			return "error";
		}
		
	}
	@SuppressWarnings("unchecked")
	public String returnMyAccount() {
		User user=(User)session.get("logUser");
		session.put("showAccount",user);
		session.put("userId",user.getId());
		return "return";
	}
	public String visitFriend(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		int id= Integer.parseInt(request.getParameter("id"));
		User user=uBiz.findUserById(id);
		session.put("showAccount",user);
		session.put("userId",user.getId());
		return null;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount() {
		return account;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhotoId() {
		return photoId;
	}
	public String[] getPhoto() {
		return photo;
	}
	public void setPhoto(String[] photo) {
		this.photo = photo;
	}
	
}
