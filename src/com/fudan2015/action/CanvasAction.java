package com.fudan2015.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.DoodleBizImpl;
import com.fudan2015.entity.Doodle;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

import sun.misc.BASE64Decoder;

public class CanvasAction {
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	private DoodleBizImpl dBiz=new DoodleBizImpl();
	String name;
	String data;
	String picId;
	public String saveCanvas() {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		
		BASE64Decoder decoder = new BASE64Decoder();
		long now=new Date().getTime();  
		//得到保存上传文件的真实路径  
		String path=ServletActionContext.getServletContext().getRealPath("");
		path=path+"/doodle";
		File dir=new File(path); 
		//System.out.println(path);
		//如果这个目录不存在,则创建它  
		if (!dir.exists()) {  
			dir.mkdir();  
		}  
		String newFileName = Long.toString(now);  
		path=path+"/"+newFileName+".png";
		System.out.println(path);
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(data);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = path;// 新生成的图片
			String savePath="doodle/"+newFileName+".png";
			System.out.println(savePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			Doodle doodle=new Doodle();
			doodle.setDoodle_url(savePath);
			doodle.setId_doodle(((User)session.get("logUser")).getId());
			doodle.setPicId(Integer.parseInt(picId));
			dBiz.addDoodle(doodle);
			out.write(b);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}

}
