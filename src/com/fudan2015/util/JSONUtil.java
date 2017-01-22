package com.fudan2015.util;

import java.util.ArrayList;
import java.util.List;

import com.fudan2015.entity.Photo;

public class JSONUtil {
	private String id_json;
	private String name_json;
	
	public JSONUtil() {
		
	}
	public List<Photo> returnPhoto() {
		List<Photo> list=new ArrayList<Photo>();
		String[] arr1=id_json.split(",");
		String[] arr2=name_json.split(",");
		for(int i=0;i<arr1.length;i++) {
			Photo photo=new Photo();
			photo.setPicId(Integer.parseInt(arr1[i]));
			photo.setPicName(arr2[i]);
			list.add(photo);
		}
		return list;
	}
	public List<Photo> returnPhoto2() {
		List<Photo> list=new ArrayList<Photo>();
		String[] arr1=id_json.split(",");
		for(int i=0;i<arr1.length;i++) {
			Photo photo=new Photo();
			photo.setPicId(Integer.parseInt(arr1[i]));
			list.add(photo);
		}
		return list;
	}
	public String getId_json() {
		return id_json;
	}
	public void setId_json(String idJson) {
		id_json = idJson;
	}
	public String getName_json() {
		return name_json;
	}
	public void setName_json(String nameJson) {
		name_json = nameJson;
	}
	
	
}
