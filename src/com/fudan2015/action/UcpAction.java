package com.fudan2015.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.fudan2015.biz.impl.CommentUBizImpl;
import com.fudan2015.biz.impl.UcpBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.Message;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*user control panel action created by XuHailong
**note: should consider if someone access the usp.jsp directly instead of ucpAction.action
**(may use session to make it)
*/ 
public class UcpAction extends ActionSupport implements SessionAware {
	
	//不进行修改，用于调用biz方法
	private UserBizImpl uBiz = new UserBizImpl();
	private UcpBizImpl biz = new UcpBizImpl();
	private CommentUBizImpl cBiz = new CommentUBizImpl();
	private User u;
	
	//当前状态
	private String inf;
	//被访问人的用户名
	private String owner;

	//用户修改的新签名
	private String description;
	//用户修改的新性别
	private int gender;
	
	//用户输入的旧密码
	private String oldPassword;
	//用户修改的新密码
	private String newPassword;
	//发表的留言内容
	private String comment;
	
	int st;
	int pages;
	int next;

	//用户的私信和留言 为当前页所有数据（最多十条）
	private List<Message> msgs = new ArrayList<Message>();
	private List<Comment> cmts = new ArrayList<Comment>(); 
	
	private Map session;
	private Map request;

	public void UcpAction()
	{
		u = (User) this.session.get("logUser");
		request = (Map)ActionContext.getContext().get("request");
		String owner_t = (String)request.get("owner");
		if(owner_t != null)
			owner = owner_t;
		else owner = u.getAccount();
		cmts = biz.getAllComments( (String)this.session.get("owner") );
		
		
	}
	//check if an user is logged in
	private boolean isLoggedIn()
	{
		u = (User) this.session.get("logUser");
		if(u == null) 
			return false;
		else 
			return true;
	}
	
	//ucp default method
	public String execute()
	{
		if(!isLoggedIn() )
			return "login";
		//get owner and inf from request
		request = (Map)ActionContext.getContext().get("request");
		owner = (String)request.get("owner");
		String inf = (String)request.get("inf");
		
		if(owner == null)
			owner = u.getAccount();
		
		if(owner.equals("DEFAULT") )
			owner = (String)this.session.get("owner");

		cmts = biz.getAllComments(owner);		
		Collections.sort(cmts, new Comparator<Comment>() 
		{
            public int compare(Comment arg0, Comment arg1) 
            {
                return arg1.getDate().compareTo(arg0.getDate());
            }
        });
		//pass the values
		this.session.put("owner", owner);
		request.put("inf", inf);
		request.put("owner", owner);
		String account=u.getAccount();
		User user=uBiz.findUserByAccount(account);
		session.put("logUser", user);
		session.put("showAccount", user);
		
		return "ucp";
	}
	
	//update user's gender and description
	public String UpdateProfile() 
	{
		if(!isLoggedIn() )
			return "login";
		//update profile
		//System.out.println(u.getAccount() );
		biz.updateUserProfile(u.getAccount(), gender, description);
		inf = "suc";
		return "success";
	}
	
	//update user's password if the old password given is right
	public String UpdatePassword() 
	{
		if(!isLoggedIn() )
			return "login";
		//check old password
		if(!biz.checkUserPassWord(u.getAccount(), oldPassword) )
		{
			inf = "wrg";
			return "wrong";
		}
		else
		{
			biz.updateUserPassword(u.getAccount(), newPassword);
			inf = "suc";
		}
		return "success";
	}
	
	public String addComment()
	{
		if(!isLoggedIn() )
			return "login";
		System.out.println((String)this.session.get("owner"));
		biz.addComment(u.getAccount() , (String)this.session.get("owner"), comment );
		cmts = biz.getAllComments( (String)this.session.get("owner") );
		owner = (String)this.session.get("owner");
		inf = "suc";
		return "success";
	}
	//setters and getters

	public User getU()
	{
		return u;
	}

	public void setU(User u)
	{
		this.u = u;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getGender()
	{
		return gender;
	}

	public void setGender(int gender)
	{
		this.gender = gender;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public List<Message> getMsgs()
	{
		return msgs;
	}

	public void setMsgs(List<Message> msgs)
	{
		this.msgs = msgs;
	}

	public List<Comment> getCmts()
	{
		return cmts;
	}

	public void setCmts(List<Comment> cmts)
	{
		this.cmts = cmts;
	}

	public Map getSession()
	{
		return session;
	}

	public void setSession(Map session)
	{
		this.session = session;
	}	
	
	public Map getRequest()
	{
		return request;
	}

	public void setRequest(Map request)
	{
		this.request = request;
	}

	public String getInf()
	{
		return inf;
	}

	public void setInf(String inf)
	{
		this.inf = inf;
	}

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public int getSt()
	{
		return st;
	}
	public void setSt(int st)
	{
		this.st = st;
	}
	public int getPages()
	{
		return pages;
	}
	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public int getNext()
	{
		return next;
	}
	public void setNext(int next)
	{
		this.next = next;
	}
}
