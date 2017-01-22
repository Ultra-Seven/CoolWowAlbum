package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Photo;

public interface IManageBiz {
	public void deletePhoto(List<Photo> photo,int albumId); 
	//delete a photo by its id
	public void updatePhoto(List<Photo> photo);
	//update the information of a photo
}
