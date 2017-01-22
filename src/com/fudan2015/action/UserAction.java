package com.fudan2015.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware {
	
	//不进行修改，用于调用biz方法
	private UserBizImpl biz=new UserBizImpl();
	private User u=new User();
	//用户的密保问题
	private String passwdQuestion;
	//用户的密保答案
	private String passwdAnswer;
	//用户输入的新的密码
	private String newpasswd;
	//用户的账号
	private String account;
	//登录方法
	
	@SuppressWarnings("unchecked")
	private Map session;
	@SuppressWarnings("unchecked")
	
	//注册方法
	
	public String  userLogin() {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String account= request.getParameter("account");
		String password=request.getParameter("password");
		User getSelect=biz.findUserByAccount(account);
		if(getSelect==null||!getSelect.getPassword().equals(password)) {
			try { 
			     response.getWriter().write("wrong"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
			return null;
		}
		else if(getSelect.getState()==1) {
			try { 
			     response.getWriter().write("forbidden"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
			return null;
		}
		else if(getSelect.getIdentity()==1) {
			try { 
			     response.getWriter().write("admin"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
			return null;
		}
		else {
			this.session.put("logUser", getSelect);
			this.session.put("showAccount", getSelect);
			this.session.put("userId", getSelect.getId());
			try { 
			     response.getWriter().write("success"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
			return null;
		}
	}
	
	public String userRegist(){
		biz.userRegist(u);
		return "regist";
	}
	public String checkAccount(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String account= request.getParameter("account");
		List<User> users=biz.userAll();
		Iterator iterator=users.iterator();
		boolean already=false;
		while(iterator.hasNext()){
			User temp=(User) iterator.next();
			if(temp.getAccount().equals(account)){
				already=true;
				break;
			}
		}
		System.out.print(already);
		if(!already){
			try { 
			     response.getWriter().write("success"); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		}
		else{
			try { 
			     response.getWriter().write("fail"); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		}
		return null;
	}
	//==========找回密码相关==========
	//其中有部分方法biz中尚未给出，因此直接调用了dao
	//这些方法包括：通过account返回user，更新数据库中user信息
	//发送密保答案的方法
	public String userGetAnswer(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/plain;charset=UTF-8"); 
		String account= request.getParameter("account");
		UserDaoImpl uDao=new UserDaoImpl();
		u=uDao.findUserByAccount(account);
		//获取问题答案，发回前台
		passwdAnswer=u.getPasswdAnswer();
		try { 
		     response.getWriter().write(passwdAnswer); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	//密码修改
	public String userPasswdUpdate(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/plain;charset=UTF-8"); 
		account= request.getParameter("account");
		newpasswd= request.getParameter("newpasswd");
		passwdAnswer= request.getParameter("answer");
		UserDaoImpl uDao=new UserDaoImpl();
		u=uDao.findUserByAccount(account);
		String accountPasswdAnswer=u.getPasswdAnswer();
		if(accountPasswdAnswer.equals(passwdAnswer)){
			u.setPassword(newpasswd);
			uDao.updateUser(u);
			try {
				response.getWriter().write("密码修改成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//return "passwdUpdateSuccess";
		}
		else{
			try {
				response.getWriter().write("密码修改失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return null;
	}
	//发送密保问题的答案
	public String userGetQuestion(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/plain;charset=UTF-8"); 
		String account= request.getParameter("account");
		UserDaoImpl uDao=new UserDaoImpl();
		if(uDao.findUserByAccount(account)!=null){
			u=uDao.findUserByAccount(account);
			passwdQuestion=u.getPasswdQuestion();
			try { 
				response.getWriter().write(passwdQuestion); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		}
		else{
			try { 
				response.getWriter().write(""); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		}
		return null; 
	}
	//================================
	
	
	public void setU(User u) {
		this.u = u;
	}
	public User getU() {
		return u;
	}
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	public String getPasswdQuestion() {
		return passwdQuestion;
	}
	public void setPasswdQuestion(String passwdQuestion) {
		this.passwdQuestion = passwdQuestion;
	}
	public String getPasswdAnswer() {
		return passwdAnswer;
	}
	public void setPasswdAnswer(String passwdAnswer) {
		this.passwdAnswer = passwdAnswer;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
