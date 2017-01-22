package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IPhotoBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;

public class PhotoBizImpl implements IPhotoBiz {
	private PhotoDaoImpl pDao=new PhotoDaoImpl();
	private UserDaoImpl uDao=new UserDaoImpl();
	public void photoDelete(Photo p) {
		//delete a photo
		pDao.deletePhoto(p);
	}

	public void photoUpdate(Photo p) {
		// update a photo
		pDao.updatePhoto(p);
	}

	public void photoUpload(Photo p,String albumId,String account) {
		// upload photos
		Album album=new Album();
		User user=new User();
		album=pDao.showAlbumById(Integer.parseInt(albumId));
		//get the album to upload
		p.setAlbum(album.getAlbum());
		//set the photo's album
		pDao.addPhoto(p);
		//add a photo
		user=uDao.findUserByAccount(account);
		//get the owner of the photo
		album.setNum(album.getNum()+1);
		//set the number of photos the album has
		pDao.updateAlbum(album);
		//increase the number of photos the album has
		user.setPicturesNum(user.getPicturesNum()+1);
		//set the number of photos the user has
		uDao.updateUser(user);
		//increase the number of photos the user has
	}

	public void albumAdd(Album a) {
		// add a album
		pDao.addAlbum(a);
	}

	public void albumDelete(Album a) {
		// delete a album
		pDao.deleteAlbum(a);
	}

	public Album albumSearch(String classification,int id) {
		List<Album> list=pDao.showAlbumByUser(id);
		//get all albums the user has
		Album a=null;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getAlbum().equals(classification)){
				// get the album by its classification
				a=list.get(i);
				break;
			}
		}
		return a;
		// return the album

	}

	public void albumUpdate(Album a) {
		// update a album
		pDao.updateAlbum(a);
	}

	public List<Photo> photoLoad(int id,String album) {
		//get the list of photos in a album
		List<Photo> pList=pDao.showPhotoByAlbum(album, id);
		return pList;
	}

	public List<Album> albumsSearch(int id) {
		// get all albums of a user
		return pDao.showAlbumByUser(id);
	}

	public List<Photo> photoLoadAll(int id) {
		//get all photos of a user
		return pDao.showPhotoByUser(id);
	}

	public Photo showPhotoById(int id) {
		// TODO Auto-generated method stub
		return pDao.showPhotoById(id);
	}
	public Album showAlbumById(int id) {
		// TODO Auto-generated method stub
		return pDao.showAlbumById(id);
	}

	public List<Album> showAlbumByUser(int id) {
		// TODO Auto-generated method stub
		return pDao.showAlbumByUser(id);
	}

	public List<Photo> getAllEditPic(int id) {
		// TODO Auto-generated method stub
		return pDao.getAllEditPic(id);
	}
	public Album showAlbumByPic(int picId) {
		// TODO Auto-generated method stub
		Photo photo=pDao.showPhotoById(picId);
		String albumName=photo.getAlbum();
		Album re=null;
		List<Album> list=pDao.showAlbumByUser(photo.getId());
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getAlbum().equals(albumName)) {
				re=list.get(i);
			}
		}
		return re;
	}
}
