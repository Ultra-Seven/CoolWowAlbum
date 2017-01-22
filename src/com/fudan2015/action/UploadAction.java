package com.fudan2015.action;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.Date;  
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;  

import com.fudan2015.biz.impl.PhotoBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Album;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;  

@SuppressWarnings("serial")  
public class UploadAction extends ActionSupport {  
	private PhotoBizImpl biz=new PhotoBizImpl(); 
	private UserBizImpl uBiz=new UserBizImpl(); 
	private Photo photo=new Photo();
	private File uploadify;//上传文件file对象  
	private String uploadifyFileName;//上传文件名  
	private String uploadifyContentType;//上传文件类型  
	private String description;//上传文件的描述  
	private String uploadDir;//保存上传文件的目录,相对于web应用程序的根路径,在struts.xml文件中配置  
	private String albumId;//接受传入的相册id信息  
	private String account;
	private String albumName;
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	
	public String execute(){ 
		//System.out.println("in");
		//System.out.println(albumId);
		Map map = ActionContext.getContext().getSession();
		User temp = (User)map.get("logUser");
		//获得相册id
		albumId=getRequest().getParameter("albumId");
		//获得account
		account=temp.getAccount();

		//测试account值
		//System.out.println(account);
		//测试是否接受到相册id
		//System.out.println(albumId);
		String newFileName=null;  
		//得到当前时间开始流逝的毫秒数,将这个毫秒数作为上传文件新的文件名  
		long now=new Date().getTime();  
		//得到保存上传文件的真实路径  
		String path=ServletActionContext.getServletContext().getRealPath(uploadDir);  
		File dir=new File(path); 
		//System.out.println(path);
		//如果这个目录不存在,则创建它  
		if (!dir.exists()) {  
			dir.mkdir();  
		}  
		int index=uploadifyFileName.lastIndexOf(".");  

		//判断上传文件是否有扩展名,以时间戳作为新的文件名  
		if (index!=-1) {  
			newFileName=now+uploadifyFileName.substring(index);  
		}else {  
			newFileName=Long.toString(now);  
		}  
		BufferedOutputStream bos=null;  
		BufferedInputStream bis=null;  

		//读取保存在临时目录下的上传文件,写入到新的文件中  
		try {  
			FileInputStream fis=new FileInputStream(uploadify);  
			bis=new BufferedInputStream(fis);  

			FileOutputStream fos=new FileOutputStream(new File(dir,newFileName));  
			//System.out.println(newFileName);
			bos=new BufferedOutputStream(fos);  

			byte [] buf=new byte[4096];  
			int len=-1;  
			while ((len=bis.read(buf))!=-1) {  
				bos.write(buf,0,len);  
			}  
		} catch (FileNotFoundException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}finally{  
			if (null!=bis) {  
				try {  
					bis.close();  
				} catch (IOException e) {  
					// TODO Auto-generated catch block  
					e.printStackTrace();  
				}  
			}  
			if (null!=bos) {  
				try {  
					bos.close();  
				} catch (IOException e) {  
					// TODO Auto-generated catch block  
					e.printStackTrace();  
				}  
			}  
		}  
		String url=newFileName;
		albumName=biz.showAlbumById(Integer.parseInt(albumId)).getAlbum();
		//System.out.println(albumName);
		//System.out.println(account);
		insertIntoDatabase(albumName,getFileName(uploadifyFileName),url,albumId,account);
		return null;  
	}  
	//将图片写入数据库的方法
	@SuppressWarnings("unchecked")
	public void insertIntoDatabase(String album,String name,String url,String albumId,String account) {
		photo.setAlbum(album);
		photo.setComments(0);
		photo.setId(uBiz.findUserByAccount(account).getId());
		photo.setPicName(name);
		photo.setPicsUrl(url);
		photo.setVisit(0);
		biz.photoUpload(photo,albumId,account);
		User user=uBiz.findUserByAccount(account);
		session.put("logUser", user);
		String logIn=((User)session.get("logUser")).getAccount();
		String show=((User)session.get("showAccount")).getAccount();
		if(logIn.equals(show)) {
			session.put("showAccount", user);
			session.put("logAccount", user);
		}
	}
	//获得文件的名字
	public String getFileName(String name) {
		//System.out.println(name);
		return name;
	}
	@SuppressWarnings("unchecked")
	public String albumView() {
		
		String albumView="";
		User user=(User) session.get("showAccount");
		List<Album> albumList=biz.showAlbumByUser(user.getId());
		for(int i=0;i<albumList.size();i++) {
			albumView=albumView+"<li>"+
			"<a href=uploadView!albumView.action?albumName="+albumList.get(i).getAlbum()+"&albumId="+albumList.get(i).getAlbumId()+">"+albumList.get(i).getAlbum()+"</a></li>";
		}
		int albumId_int=(albumId==null)?albumList.get(0).getAlbumId():Integer.parseInt(albumId);
		Album album=biz.showAlbumById(albumId_int);
		
		albumView="<ul class=nav nav-pills><li class=\"dropdown all-camera-dropdown\" id=accountmenu data-id="+albumId_int+">"+
						"<a class=dropdown-toggle data-toggle=dropdown href=uploadView!albumView.action?albumName="+album.getAlbum()+"&albumId="+albumId_int+">"+album.getAlbum()+"<b class=caret></b></a>"+
						"<ul class=dropdown-menu>"+albumView+"</ul>"+
					"</li></ul>";
		session.put("uploadList",albumView);
		return "album";
	}
	public HttpServletRequest getRequest() {
		// TODO Auto-generated method stub
		return ServletActionContext.getRequest();
	}

	public File getUploadify() {  
		return uploadify;  
	}  

	public void setUploadify(File uploadify) {  
		this.uploadify = uploadify;  
	}  

	public String getUploadifyFileName() {  
		return uploadifyFileName;  
	}  

	public void setUploadifyFileName(String uploadifyFileName) {  
		this.uploadifyFileName = uploadifyFileName;  
	}  

	public String getUploadifyContentType() {  
		return uploadifyContentType;  
	}  

	public void setUploadifyContentType(String uploadifyContentType) {  
		this.uploadifyContentType = uploadifyContentType;  
	}  

	public String getDescription() {  
		return description;  
	}  

	public void setDescription(String description) {  
		this.description = description;  
	}  

	public String getUploadDir() {  
		return uploadDir;  
	}  

	public void setUploadDir(String uploadDir) {  
		this.uploadDir = uploadDir;  
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getAlbumId() {
		return albumId;
	}  


}  