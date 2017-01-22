package com.fudan2015.action;

import java.util.Map;

import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.entity.Album;
import com.opensymphony.xwork2.ActionContext;

public class AlbumAction {
	private PhotoBizImpl biz=new PhotoBizImpl();
	private String albumName;
	//the name of the album
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	public String addAlbum() {
		//add a album
		int id=Integer.parseInt(session.get("userId").toString());
		//get the id of the user
		Album album=new Album();
		album.setAlbum(albumName);
		album.setId(id);
		album.setNum(0);
		//set the information of the new album
		biz.albumAdd(album);
		//save the new album into the database
		return "add";
	}
	
	//the following are getters and setters used in the action
	
	
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumName() {
		return albumName;
	}
	
}
