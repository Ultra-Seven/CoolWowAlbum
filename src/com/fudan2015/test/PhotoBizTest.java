package com.fudan2015.test;

import java.util.List;

import org.junit.Test;

import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;

public class PhotoBizTest {
	private PhotoBizImpl pBiz=new PhotoBizImpl();
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
	public void test(){//test Biz
       List<Photo> pList=pBiz.photoLoad(id, album);
       List<Album> aList=pBiz.albumsSearch(id);
       //test search
       System.out.println(pList.size()+"|"+aList.size());
       System.out.println(pList.get(1).getAlbum()+"|"+pList.get(1).getPicsUrl());//test2|image5.jpg
       
       
       
       a.setAlbum("DaoTest");
       a.setId(id);
       pBiz.albumAdd(a);
       p.setId(id);
       p.setPicName("test");
       p.setAlbum("DaoTest");
       p.setPicsUrl("image0.jpg");
       pBiz.photoUpload(p, album, album);
       //test add
       
       Photo pt=pBiz.photoLoad(id, "DaoTest").get(0);
       Album at=pBiz.albumSearch("DaoTest", id);
       System.out.println(pt.getPicName()+"|"+at.getAlbum());
       pt.setPicName("update");
       at.setAlbum("update");
       pBiz.albumUpdate(at);
       pBiz.photoUpdate(pt);
       pt=pBiz.photoLoad(id, "DaoTest").get(0);
       at=pBiz.albumSearch("DaoTest", id);
       System.out.println(pt.getPicName()+"|"+at.getAlbum());
       //test update
       
       
       a.setAlbumId(pBiz.photoLoad(id,"DaoTest").size());
       p.setPicId(pBiz.albumsSearch(id).size());
       pBiz.albumDelete(a);
       pBiz.photoDelete(p);
       //test delete
       
       
	}
}
