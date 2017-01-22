package com.fudan2015.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fudan2015.biz.impl.FriendBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class FriendAction {
	private UserBizImpl uBiz=new UserBizImpl();
	private FriendBizImpl biz=new FriendBizImpl();
	private Map<String,Object> session;
	private User u;
	//user used in action
	private List<Friends> friends;
	//list of friends


	public String getFriendList(){
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		//get the user login
		friends=biz.getAllFriendList(u.getId());
		//get the list of the user
		session.put("friends", friends);
		//save the information in the session
		return "get";
	}
	//增加好友分类
	public String addFriends(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//ajax code
		String classification= request.getParameter("classification");
		//get the name of the friends
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		//get the user login
		List<Friends> check=biz.getAllFriendList(u.getId());
		//get the list of his friends
		Iterator iterator=check.iterator();
		//用迭代器来判断是否存在重名的好友分类
		boolean already=false;
		while(iterator.hasNext()){
			if(((Friends) iterator.next()).getClassification().equals(classification)){
				already=true;
				break;
			}
		}
		//如果存在的话则返回前台并且不进行改名
		if(already){
			try { 
			     response.getWriter().write("already"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		}
		//如果不存在，且满足条件，那么就进行改名
		else if(classification!=null&&classification.length()<7){
			Friends Friends=new Friends();
			Friends.setClassification(classification);
			Friends.setNum(0);
			Friends.setIdClass(u.getId());
			//增加分类
			biz.addFriends(Friends);
			friends=biz.getAllFriendList(u.getId());
			session.put("friends", friends);
			try { 
			     response.getWriter().write("success"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		}
		//否则失败
		else{
			try { 
			     response.getWriter().write("fail"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		}
		return null;
	}
	//删除好友
	public String deleteFriend(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//从前台获取数据
		int id= Integer.parseInt(request.getParameter("id"));
		int classId= Integer.parseInt(request.getParameter("classId"));
		//要删除的好友
		Friend deleteFriend=new Friend();
		//设置id
		deleteFriend.setId(id);
		//删除好友
		biz.deleteFriend(deleteFriend);
		session = ActionContext.getContext().getSession();
		Friends update=biz.getFriendsByClassId(classId);
		//将好友分类的好友数减去1
		update.setNum(update.getNum()-1);
		String output=update.getNum()+"";
		//更新好友分类
		biz.updateFriends(update);
		u=(User) session.get("logUser");
		u.setFriendsNum(u.getFriendsNum()-1);
		uBiz.updateUser(u);
		//更新session
		friends=biz.getAllFriendList(u.getId());
		session.put("friends", friends);
		try { 
		     response.getWriter().write(output); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return null;
	}
	//删除好友分类
	public String deleteFriendList(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取前台的参数
		int classId= Integer.parseInt(request.getParameter("classId"));
		//根据classId获取要删除的好友列表
		Friends deleteFriendList=biz.getFriendsByClassId(classId);
		//获取该好友列表的名称与好友数量
		String classification=deleteFriendList.getClassification();
		int number=deleteFriendList.getNum();
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		//将该目录下的所有好友全部移动到未分类的好友列表之下
		if(number!=0){
			Friends weifenlei=biz.getWeifenlei(u.getId());
			weifenlei.setNum(weifenlei.getNum()+number);
			biz.updateFriends(weifenlei);
			biz.changeFriend(u.getId(), classification);
			try { 
			     response.getWriter().write("no"); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
			biz.deleteFriends(deleteFriendList);
			friends=biz.getAllFriendList(u.getId());
			session.put("friends", friends);
			return null;
		}
		//如果该分类下没有好友则执行此处方法，前台将不会进行刷新
		biz.deleteFriends(deleteFriendList);
		friends=biz.getAllFriendList(u.getId());
		session.put("friends", friends);
		try { 
		     response.getWriter().write("yes"); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return null;
	}
	//重命名好友列表
	public String rename(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取前台传入的数据
		int classId= Integer.parseInt(request.getParameter("classId"));
		String name= request.getParameter("name");
		Friends renameFriends=biz.getFriendsByClassId(classId);
		session = ActionContext.getContext().getSession();
		u=(User) session.get("logUser");
		List<Friends> check=biz.getAllFriendList(u.getId());
		//判断是否存重复命名的现象
		Iterator iterator=check.iterator();
		boolean already=false;
		while(iterator.hasNext()){
			if(((Friends) iterator.next()).getClassification().equals(name)){
				already=true;
				break;
			}
		}
		//如果命名满足条件则进行一下操作，进行重命名
		if(!already&&name.length()<7){
			String oldname=renameFriends.getClassification();
			renameFriends.setClassification(name);
			biz.updateFriends(renameFriends);
			biz.changeFriend(u.getId(), oldname, name);
			friends=biz.getAllFriendList(u.getId());
			session.put("friends", friends);
			try { 
			     response.getWriter().write("success"); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
			return null;
		}
		return null;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public User getU() {
		return u;
	}


	public void setU(User u) {
		this.u = u;
	}


	public List<Friends> getFriends() {
		return friends;
	}


	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}
}
