package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;

public interface IPhotoBiz {
/*
 * upload a photo
 * delete a photo
 * update a photo
 */
	public void photoUpload(Photo p,String albumId,String account);
	public void photoDelete(Photo p);
	public void photoUpdate(Photo p);
	public void albumAdd(Album a);
	public void albumDelete(Album a);
	public void albumUpdate(Album a);
	public Photo showPhotoById(int id);
	public Album albumSearch(String classification,int id);
	public List<Photo> photoLoad(int id,String album);
	public List<Photo> photoLoadAll(int id);
 	public List<Album> albumsSearch(int id);
	//ͨ��id������
 	public Album showAlbumById(int id);
 	//���ĳ�û������
 	public List<Album> showAlbumByUser(int id);
 	//获得所有可编辑的相片
 	public List<Photo> getAllEditPic(int id);
	public Album showAlbumByPic(int picId);
 	
}
