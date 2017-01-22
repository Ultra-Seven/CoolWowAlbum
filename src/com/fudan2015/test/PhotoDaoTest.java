package com.fudan2015.test;

import java.util.List;

import org.junit.Test;

import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;

public class PhotoDaoTest {
	private PhotoDaoImpl pDao=new PhotoDaoImpl();
	private Photo p=new Photo();
	private Album a=new Album();
	private int id=3;
	private String album="test2";
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
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	@Test
	public void test(){//test Dao
       List<Photo> pList1=pDao.showPhotoByUser(id);
       List<Photo> pList2=pDao.showPhotoByAlbum(album,id);
       List<Album> aList=pDao.showAlbumByUser(id);
       //test search
       System.out.println(pList1.size()+"|"+pList2.size()+"|"+aList.size());
       System.out.println(pList2.get(1).getAlbum()+"|"+pList2.get(1).getPicsUrl());//test2|image5.jpg
       
       
       
       a.setAlbum("DaoTest");
       a.setId(id);
       pDao.addAlbum(a);
       p.setId(id);
       p.setPicName("test");
       p.setAlbum("DaoTest");
       p.setPicsUrl("image0.jpg");
       pDao.addPhoto(p);
       //test add
       
       Photo pt=pDao.showPhotoByAlbum("DaoTest", id).get(0);
       Album at=pDao.showAlbumByUser(id).get(0);
       System.out.println(pt.getPicName()+"|"+at.getAlbum());
       pt.setPicName("update");
       at.setAlbum("update");
       pDao.updateAlbum(at);
       pDao.updatePhoto(pt);
       pt=pDao.showPhotoByAlbum("DaoTest", id).get(0);
       at=pDao.showAlbumByUser(id).get(0);
       System.out.println(pt.getPicName()+"|"+at.getAlbum());
       //test update
       
       
       a.setAlbumId(pDao.showAlbumByUser(id).size());
       p.setPicId(pDao.showPhotoByUser(id).size());
       pDao.deleteAlbum(a);
       pDao.deletePhoto(p);
       //test delete
       
       
	}
}
