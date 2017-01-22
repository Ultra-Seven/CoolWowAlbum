package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IManageBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;

public class ManageBizImpl implements IManageBiz {
	PhotoDaoImpl photoDao=new PhotoDaoImpl();
	UserDaoImpl userDao=new UserDaoImpl();
	public void deletePhoto(List<Photo> photo,int albumId) {
		//System.out.println("in it");
		//delete a photo
		int num=photo.size();
		//number used for delete more photos a time
		int id=photoDao.showPhotoById(photo.get(0).getPicId()).getId();
		//get the first photo's id in the list
		//System.out.println(id);
		for(int i=0;i<num;i++) {
			//delete the photos one by one
			photoDao.deletePhoto(photo.get(i));
		}
		User newUser=new User();
		Album newAlbum=new Album();
		newUser=userDao.findUserById(id);
		//get the user deleted photos
		//System.out.println(newUser.getPicturesNum());
		newUser.setPicturesNum(newUser.getPicturesNum()-num);
		//System.out.println(newUser.getPicturesNum());
		//change the number of photos he has
		userDao.updateUser(newUser);
		//save into the database
		System.out.println(albumId);
		newAlbum=photoDao.showAlbumById(albumId);
		//get the album delete photos
		newAlbum.setNum(newAlbum.getNum()-num);
		//change the number of photos it has
		photoDao.updateAlbum(newAlbum);
		//save into the database
	}

	public void updatePhoto(List<Photo> photo) {
		//update a photo
		for(int i=0;i<photo.size();i++) {
			photoDao.updatePhoto(photo.get(i));
		}
	}

}
