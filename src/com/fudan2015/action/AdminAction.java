package com.fudan2015.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.AdminBizImpl;
import com.fudan2015.biz.impl.FriendBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class AdminAction {
	private AdminBizImpl biz=new AdminBizImpl();
	//get biz
	private UserBizImpl uBiz=new UserBizImpl();
	private User u;
	//user
	private Photo p;
	//photo
	private int state;
	//user's state
	private List<Photo> pl;
	//hot pictures' list
	private ActionContext ctx=ActionContext.getContext();
	@SuppressWarnings("unchecked")
	private Map session=ctx.getSession();
	@SuppressWarnings("unchecked")
	
	
	public String getUsers(){
		//get all users to prepare for management
		List<User> allUsers=biz.showAllUser();
		session = ActionContext.getContext().getSession();
		session.put("allUserzx", allUsers);
		//save users into session
		
		return "success";
	}
	@SuppressWarnings("unchecked")
	public String updatePasswd(){
		//update a user's password
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//ajax code
		String account= request.getParameter("account");
		String passwd= request.getParameter("passwd");
		//get the information of a user
		User user=uBiz.findUserByAccount(account);
		//search from database
		user.setPassword(passwd);
		//change the password
		uBiz.userUpdatePasswd(user);
		//save it into database
		List<User> allUsers=biz.showAllUser();
		session = ActionContext.getContext().getSession();
		session.put("allUserzx", allUsers);
		//refresh the information of users in the page and session
		return null;
	}
	@SuppressWarnings("unchecked")
	public String updateEmail(){
		//update a user's email
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//ajax code
		String account= request.getParameter("account");
		String email= request.getParameter("email");
		//get user's information
		User user=uBiz.findUserByAccount(account);
		//search datebases
		user.setEmail(email);
		//change email
		uBiz.updateUser(user);
		//save into database
		List<User> allUsers=biz.showAllUser();
		session = ActionContext.getContext().getSession();
		session.put("allUserzx", allUsers);
		//refresh the information in page and session
		return null;
	}
	@SuppressWarnings("unchecked")
	public String updateState(){
		//update the state of a user
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//ajax code
		String state= request.getParameter("state");
		String account= request.getParameter("account");
		//get the information  of the user
		User user=uBiz.findUserByAccount(account);
		//search from the database
		user.setState(Integer.parseInt(state));
		//change the state of the user
		uBiz.updateUser(user);
		//save it into database
		List<User> allUsers=biz.showAllUser();
		session = ActionContext.getContext().getSession();
		session.put("allUserzx", allUsers);
		//refresh the information in the page and session
		return null;
	}
	@SuppressWarnings("unchecked")
	public String deleteFriend(){
		//delete a user's friend
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest()
		;//ajax code
		String idFriend= request.getParameter("idFriend");
		String id= request.getParameter("friendId");
		//get the information of the user
		User user=uBiz.findUserById(Integer.parseInt(idFriend));
		//search from databases
		user.setFriendsNum(user.getFriendsNum()-1);
		//decrease the number of friends
		uBiz.updateUser(user);
		//save into database
		Friend friend=new FriendBizImpl().getFriendById(Integer.parseInt(id));
		//get the information of the friend deleted
		String classification=friend.getClassification();
		//get the classification the friend from
		new FriendBizImpl().deleteFriend(friend);
		//delete the friend
		Friends friends=new FriendBizImpl().getFriendsByIdClassification(Integer.parseInt(idFriend), classification);
		//get the information of the friends where the friend in before
		friends.setNum(friends.getNum()-1);
		//decrease the number of the friends
		new FriendBizImpl().updateFriends(friends);
		//save it into database
		List<User> allUsers=biz.showAllUser();
		session = ActionContext.getContext().getSession();
		session.put("allUserzx", allUsers);
		//refresh the information in the page and session
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String logOff() {
		session.put("logUser",null);
		//clear the session saved userID
		return "logOff";
	}
	
	
	
	//the following are getters and setters used in action
	
	
	public List<Photo> getPl() {
		return pl;
	}
	public void setPl(List<Photo> pl) {
		this.pl = pl;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public Photo getP() {
		return p;
	}
	public void setP(Photo p) {
		this.p = p;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
