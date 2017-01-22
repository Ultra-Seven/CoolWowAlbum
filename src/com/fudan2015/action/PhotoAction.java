package com.fudan2015.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.fudan2015.biz.impl.CommentBizImpl;
import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.Friends;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class PhotoAction {
	private CommentBizImpl cBiz=new CommentBizImpl();
	private PhotoBizImpl biz=new PhotoBizImpl();
	private UserBizImpl userBiz=new UserBizImpl();
	private Map<String, Object> dataMap;
	//bizs used
	private Photo p;
	private Album a;
	private int picId=0;
	private String comments="";
	private String albumId="";
	private String albumName="default";
	//albumName in session
	private String preview="";
	//save the URLs of preview
	private String album="";
	//HTMLs to show album(myAlbum.jsp)
	private String[] photo;
	//HTMLs to show photos(myPhotos.jsp)
	private String photoInfo="";
	//HTMLs to show albumInfo(myPhotos.jsp)
	private String photoId;//ajax dynamic pic information
	//showAccount id
	private String showId;
	//HTMLs to make a PPT(myPhotos.jsp)
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	private int id=0;
	@SuppressWarnings("unchecked")
	public String photoLoad(){
		// load preview photos
		if(session.get("userId")!=null){
			id=Integer.parseInt(session.get("userId").toString());
			List<Album> aList=biz.albumsSearch(id);
			//get the list of albums

			for(int i=0;i<aList.size();i++){
				List<Photo> pList=biz.photoLoad(id,aList.get(i).getAlbum());
				//get the first photo of each album as a cover
				if(pList.size()==0){
					//if there is no picture in a album wo use a defalut one(nopic.jpg)
					preview+="{src : \"nopic.jpg\",id : \"none\"}";
					//save preview URLs
					album+="<li class=\"content-album content-album-hover\">" +
					//save album HTMLs
					"<div class=\"content-album-cover\"><a href=photo/photoView!photoView.action?albumName="+aList.get(i).getAlbum()+"&albumId="+aList.get(i).getAlbumId()+
					" id=\"albumHover\"> <img class=\"album\" src=\"images/nopic.jpg\" " +
					//get the src of image
					"alt=\"我的相册\" onload=\"\" style=\"opacity: 0.8;\" />" +
					"<div class=\"content-album-picture-total\">" +
					"<span>"+aList.get(i).getNum()+"</span>张</div> </a><div class=\"content-album-del ico-show\">" +
					//get the photos' number
					"<a class=\"modify icon-pencil\" title=\"编辑\" href=\"#\"></a>" +
					"<a class=\"delete icon-trash\" title=\"删除\" href=\"#\"></a></div></div>" +
					"<div class=\"content-album-info\"><a class=\"content-album-name yahei content-album-name-hover\" " +
					"title=\"我的相册\" href=\"photo/photoView!photoView.action?albumName="+aList.get(i).getAlbum()+"\">"+aList.get(i).getAlbum()+"</a><span class=\"content-album-date\">"+aList.get(i).getDate()+"</span>" +
					//<a> to view the photos
					"</div><a class=\"icon-lock opens\" title=\"全部公开\"></a></li>";
				}
				else{
					//if album is not empty we will use the lastest picture as the cover
					preview+="{src : \""+pList.get(0).getPicsUrl()+"\",id : \""+pList.get(0).getPicName()+"\"}";
					//same as above
					album+="<li class=\"content-album content-album-hover\">" +
					//same as above
					"<div class=\"content-album-cover\"><a href=photo/photoView!photoView.action?albumName="+aList.get(i).getAlbum()+"&albumId="+aList.get(i).getAlbumId()+
					//same as above
					" id=\"albumHover\"> <img class=\"album\" src=\"uploadFiles/"+pList.get(0).getPicsUrl()+"\" " +
					//same as above
					"alt=\"我的相册\" onload=\"\" style=\"opacity: 0.8;\" />" +
					"<div class=\"content-album-picture-total\">" +
					"<span>"+aList.get(i).getNum()+"</span>张</div> </a><div class=\"content-album-del ico-show\">" +
					//same as above
					"<a class=\"modify icon-pencil\" title=\"编辑\" href=\"#\"></a>" +
					"<a class=\"delete icon-trash\" title=\"删除\" href=\"#\"></a></div></div>" +
					"<div class=\"content-album-info\"><a class=\"content-album-name yahei content-album-name-hover\" " +
					//same as above
					"title=\"我的相册\" href=\"photo/photoView!photoView.action?albumName="+aList.get(i).getAlbum()+"\">"+
					aList.get(i).getAlbum()+"</a><span class=\"content-album-date\">"+aList.get(i).getDate()+"</span>" +
					//same as above
					"</div><a class=\"icon-lock opens\" title=\"全部公开\"></a></li>";
				}
				if(i<(aList.size()-1)){
					//if the code of picture not the last one wo use comma to split them
					preview+=",";
				}
			}
			session.put("pre",preview);
			//save preview in session
			session.put("album",album);
			//save album in session
			return "photoLoad";
		}
		else{
			return "error";
		}
	}
	@SuppressWarnings("unchecked")
	public String photoView(){//view photos in a album

		if(session.get("userId")!=null&&!albumName.equals("default")){//check userId & albumName
			//System.out.println(albumName);
			String login=((User)session.get("logUser")).getAccount();
			String show=((User)session.get("showAccount")).getAccount();
			String photoView="";
			id=Integer.parseInt(session.get("userId").toString());
			List<Photo> pList=biz.photoLoad(id, albumName);
			//get all photos
			photo=new String [pList.size()];
			//give a size to photo(same as the album)
			for(int i=0;i<pList.size();i++){
				if(!login.equals(show)) {
					photo[i]="\"<li class=grid-item>"+
					"<a href=uploadFiles/"+pList.get(i).getPicsUrl()+" data-id="+pList.get(i).getPicId()+" data-date="+pList.get(i).getDate()+" data-name="+pList.get(i).getPicName()+"><img src=uploadFiles/"+pList.get(i).getPicsUrl()+" />"+
					//get the src of a photo
					"</a></li>\"";
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
				picId=pList.get(i).getPicId();
				

			}

			System.out.println(albumId);
			System.out.println(albumName);
			System.out.println(id);
			photoView="["+photoView+"]";
			photoInfo="<h1 id=photoH1 class=yahei data-id="+albumId+">"+biz.albumSearch(albumName, id).getAlbum()+"&nbsp;" +
			"</h1><em class=\"pic-num yahei\">("+biz.albumSearch(albumName, id).getNum()+")" +
			"</em>";


			String albumView="";
			User user=(User) session.get("showAccount");
			List<Album> albumList=biz.showAlbumByUser(user.getId());
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
			return "photoView";
		}
		else{
			return "error";
		}
	}
	@SuppressWarnings("unchecked")
	public String managePhoto() {
		id=Integer.parseInt(session.get("userId").toString());
		//get the user id now loading
		List<Photo> list=new ArrayList<Photo>();
		list=biz.photoLoad(id, albumName);
		
		//get user's photos
		String showContent="";
		
		//string used to save management code
		for(int i=0;i<list.size();i++) {
			String content="<a href=# id="+list.get(i).getPicName()+" class=pic data-id="+list.get(i).getPicId()+" data-name="+list.get(i).getPicName()+"> <img src=uploadFiles/"+list.get(i).getPicsUrl()+">"+
			"<div class=select-frame></div> </a>";
			showContent=showContent+content;
			//get the picture's name and id then save it into the whole code
		}
		Album album=biz.showAlbumByPic(list.get(0).getPicId());
		String albumContent="<a class=\"yahei myAlbumName\" title="+albumName+" href=photo/photoView!photoView.action?albumName="+albumName+"&albumId="+album.getAlbumId()+" data-id="+album.getAlbumId()+">"+albumName+"</a>"+
		"&nbsp;&gt;&nbsp;批量管理（<span class=yahei id=manage-pic-num>"+album.getNum()+"</span>张）";
		session.put("managePhoto",showContent);
		session.put("albumContent",albumContent);
		//save management code into session
		return "success";
	}
	@SuppressWarnings("unchecked")
	public String supersized() {
		id=Integer.parseInt(session.get("userId").toString());
		//get user's id
		List<Photo> pList=biz.photoLoad(id, albumName);
		//load photos
		String supersize="";
		//string used to save code about supersize
		for(int i=0;i<pList.size();i++) {
			supersize=supersize+"{image:\"uploadFiles/"+pList.get(i).getPicsUrl()+"\",title:\""+pList.get(i).getPicName()+"\",url:\"#\"}";
			//get the url and name of photo then save one piece into the whole code
			if(i!=pList.size()-1) {
				//if the code is not the last one wo use a comma to split them
				supersize=supersize+",";
			}
		}
		//System.out.println(supersize);
		session.put("supersized",supersize);
		return "supersized";
	}
	@SuppressWarnings("unchecked")
	public String zoomPic() {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		Photo photo=biz.showPhotoById(Integer.parseInt(photoId));
		String log=((User)session.get("logUser")).getAccount();
		String show=((User)session.get("showAccount")).getAccount();
		if(!log.equals(show)) {
			photo.setVisit(photo.getVisit()+1);
			biz.photoUpdate(photo);
		}
		
		
		String data=photo.getPicName()+","+photo.getDate()+","+photo.getVisit()+","+photo.getComments();
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
	public String albumAdd(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String album= request.getParameter("name");
		User u=(User) session.get("logUser");
		id=u.getId();
		List<Album> albumList=biz.showAlbumByUser(u.getId());
		boolean already=false;
		Iterator iterator=albumList.iterator();
		while(iterator.hasNext()){
			if(album.equals(((Album) iterator.next()).getAlbum())){
				already=true;
				break;
			}
		}
		if(already){
			try { 
			     response.getWriter().write("fail"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
			return null;
		}
		Album newa=new Album();
		newa.setId(id);
		newa.setAlbum(album);
		biz.albumAdd(newa);
		u.setAlbumNum(u.getAlbumNum()+1);
		userBiz.updateUser(u);
		session.put("logUser", u);
		try { 
		     response.getWriter().write("success"); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}



	// the following are getters and setters used in the action


	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public Photo getP() {
		return p;
	}
	public void setP(Photo p) {
		this.p = p;
	}
	public Album getA() {
		return a;
	}
	public void setA(Album a) {
		this.a = a;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getAlbum() {
		return album;
	}

	public String[] getPhoto() {
		return photo;
	}
	public void setPhoto(String[] photo) {
		this.photo = photo;
	}
	public void setPhotoInfo(String photoInfo) {
		this.photoInfo = photoInfo;
	}
	public String getPhotoInfo() {
		return photoInfo;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumId() {
		return albumId;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhotoId() {
		return photoId;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getShowId() {
		return showId;
	}

}
