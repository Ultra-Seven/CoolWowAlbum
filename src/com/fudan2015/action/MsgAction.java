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



import com.fudan2015.dao.impl.FriendDaoImpl;
import com.fudan2015.biz.impl.MessageBizImpl;
import com.fudan2015.biz.impl.UcpBizImpl;
import com.fudan2015.biz.impl.UserBizImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Friend;
import com.fudan2015.entity.Friends;
import com.fudan2015.entity.Message;
import com.fudan2015.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*message action created by XuHailong
*/ 
public class MsgAction extends ActionSupport implements SessionAware {
	
	//不进行修改，用于调用biz方法
	private UcpBizImpl biz = new UcpBizImpl();
	private MessageBizImpl mBiz = new MessageBizImpl();
	private FriendDaoImpl fDao = new FriendDaoImpl();
	private UserBizImpl uBiz = new UserBizImpl();
	private User u;
	
	//待删除私信ID
	private int delId;
	
	private String sendTo;
	private String content;
	private String friend;
	private int state;
	//当前状态
	private String inf;
	//删除收件箱还是发件箱
	private int delState;

	//用户的私信, 为当前页所有数据（最多十条）
	private List<Message> inmsgs = new ArrayList<Message>();
	private List<Message> outmsgs = new ArrayList<Message>(); 
	
	private Map session;
	private Map request;
	
	public void MsgAction()
	{
		u = (User) this.session.get("logUser");
		inmsgs = mBiz.getInboxMessages(u.getAccount() );
		outmsgs = mBiz.getOutboxMessages(u.getAccount() );
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
		inmsgs = mBiz.getInboxMessages(u.getAccount() );
		Collections.sort(inmsgs, new Comparator<Message>() 
				{
		            public int compare(Message arg0, Message arg1) 
		            {
		                return arg1.getDate().compareTo(arg0.getDate());
		            }
		        });
		outmsgs = mBiz.getOutboxMessages(u.getAccount() );
		Collections.sort(outmsgs, new Comparator<Message>() 
				{
		            public int compare(Message arg0, Message arg1) 
		            {
		                return arg1.getDate().compareTo(arg0.getDate());
		            }
		        });
		request = (Map)ActionContext.getContext().get("request");
		request.put("inf", inf);
		
		return "msg";
	}
	

	public String sendMsg()
	{
		if(!isLoggedIn() )
			return "login";
		if(uBiz.userFind(sendTo) == null)
			return "invalid";
		mBiz.addMessage(u.getAccount(), sendTo, content, state);
		inf = "suc";
		request = (Map)ActionContext.getContext().get("request");
		request.put("inf", inf);
		return "refresh";
	}
	
	public String delMsg()
	{
		if(!isLoggedIn() )
			return "login";
		mBiz.updateMessage(null, delState, delId);
		inf = "suc";
		return "success";
	}
	
	public String addFriend()
	{
		if(!isLoggedIn() )
			return "login";
		Friend f = new Friend();
		User uFriend = uBiz.userFind(friend);
		f.setFriendId(uFriend.getId() );
		f.setIdFriend(u.getId() );
		f.setClassification("未分类");
		fDao.addFriend(f);

		f.setFriendId(u.getId() );
		f.setIdFriend(uFriend.getId() );
		fDao.addFriend(f);
		
		Friends fs = fDao.getWeifenlei(u.getId() );
		if(fs.getClassId() == 0)	//default class not created
		{
			fs.setIdClass(u.getId() );
			fs.setClassification("未分类");
			fs.setNum(0);
			fDao.addFriends(fs);
		}		
		fs = fDao.getWeifenlei(u.getId() );
		fs.setNum(fs.getNum() + 1);
		fDao.updateFriends(fs);
		
		fs = fDao.getWeifenlei(uFriend.getId() );
		if(fs.getClassId() == 0)
		{
			fs.setIdClass(uFriend.getId() );
			fs.setClassification("未分类");
			fs.setNum(0);
			fDao.addFriends(fs);
		}		
		fs = fDao.getWeifenlei(uFriend.getId() );
		fs.setNum(fs.getNum() + 1);
		fDao.updateFriends(fs);
		
		mBiz.updateMessage("好友请求（已同意）", 0, delId);
		inf = "suc";
		
		u=uBiz.findUserById(u.getId());
		this.session.put("logUser", u);
		this.session.put("showAccount", u);
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
	public int getDelId()
	{
		return delId;
	}
	public void setDelId(int delId)
	{
		this.delId = delId;
	}
	public String getSendTo()
	{
		return sendTo;
	}
	public void setSendTo(String sendTo)
	{
		this.sendTo = sendTo;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public List<Message> getInmsgs()
	{
		return inmsgs;
	}
	public void setInmsgs(List<Message> inmsgs)
	{
		this.inmsgs = inmsgs;
	}
	public List<Message> getOutmsgs()
	{
		return outmsgs;
	}
	public void setOutmsgs(List<Message> outmsgs)
	{
		this.outmsgs = outmsgs;
	}
	public Map getSession()
	{
		return session;
	}
	public void setSession(Map session)
	{
		this.session = session;
	}
	public String getInf()
	{
		return inf;
	}
	public void setInf(String inf)
	{
		this.inf = inf;
	}
	public Map getRequest()
	{
		return request;
	}
	public void setRequest(Map request)
	{
		this.request = request;
	}
	public String getFriend()
	{
		return friend;
	}
	public void setFriend(String friend)
	{
		this.friend = friend;
	}
	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public int getDelState()
	{
		return delState;
	}
	public void setDelState(int delState)
	{
		this.delState = delState;
	}
	
	
}
