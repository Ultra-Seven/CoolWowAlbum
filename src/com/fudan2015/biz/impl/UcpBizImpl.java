package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IUcpBiz;
import com.fudan2015.biz.IUserBiz;
import com.fudan2015.dao.impl.CommentUDaoImpl;
import com.fudan2015.dao.impl.MessageDaoImpl;
import com.fudan2015.dao.impl.UserDaoImpl;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.User;

/*user control panel business created by XuHailong
**note1: used picture comment class instead of creating a new user comment class
**use negative id so that user comment's id and picture comment's id won't conflict
**note on note1: remove negative id because database has a bond between picture id and comment id
**note2: the String "account" may be wrong, so that User "u" could be null
*/ 
public class UcpBizImpl implements IUcpBiz 
{
	private UserDaoImpl uDao = new UserDaoImpl();
	private CommentUDaoImpl cDao = new CommentUDaoImpl();
	private MessageDaoImpl mDao = new MessageDaoImpl();

	public void updateUserAvatar()//not finished
	{
	}
	
	//check if the password given is right
	public boolean checkUserPassWord(String account, String password)
	{
		User u = uDao.findUserByAccount(account);
		if(u.getPassword().equals(password) )
			return true;
		return false;
	}
	
	//update profile
	public void updateUserProfile(String account, int gender, String description)
	{
		User u = uDao.findUserByAccount(account);
		u.setGender(gender);
		u.setDescription(description);
		uDao.updateUser(u);
	}
	
	//update password
	public void updateUserPassword(String account, String password)
	{
		User u = uDao.findUserByAccount(account);
		u.setPassword(password);
		uDao.updateUser(u);
	}
	
	//get all user's comments 
	public List<Comment> getAllComments(String account)
	{
		User u = uDao.findUserByAccount(account);
		//user comment's id  stored in comment database are negative(removed)
		int id = u.getId();
		return cDao.showCommentAll(id);
	}
	
	//"observer" add a comment on "user"
	public void addComment(String observer, String user, String comment)
	{
		User u = uDao.findUserByAccount(user);
		//no constructor provided in Comment class （╯‵□′）╯
		Comment com = new Comment();
		com.setComment(comment);
		com.setObserver(observer);
		com.setPicId(u.getId() ); //use negative id(removed)
		cDao.addComment(com);
	}
	
	public void delComment(int id)
	{
		cDao.deleteComment(id); //id may be wrong
	}
	
}
