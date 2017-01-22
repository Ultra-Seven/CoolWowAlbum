package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;

public interface IPhotoDao {
	/*
	 * 新增相片
	 * 新增相册
	 * 删除相片
	 * 删除相册
	 * 修改相片
	 * 修改相册
	 * 查询某人某相册相片
	 * 查询某人相片
	 * 查询某人相册
	 * 获得所有可编辑的相片
	 */
	public void addPhoto(Photo p);
	public void addAlbum(Album a);
	public Album showAlbumById(int id);
	public Photo showPhotoById(int id);
	public void deletePhoto(Photo p);
	public void deleteAlbum(Album a);
	public void updatePhoto(Photo p);
	public void updateAlbum(Album a);
	public List<Photo> showPhotoByAlbum(String album,int id);
	public List<Photo> showPhotoByUser(int id);
	public List<Album> showAlbumByUser(int id);
	public List<Photo> showAllPhotoByString(String str);
	public List<Photo> hotPics();
	public Photo showPhotoZX(int id);
	public List<Photo> getAllEditPic(int id);
}
