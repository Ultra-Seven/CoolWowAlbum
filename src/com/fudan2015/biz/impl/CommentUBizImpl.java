package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.ICommentUBiz;
import com.fudan2015.dao.impl.CommentUDaoImpl;
import com.fudan2015.entity.Comment;

public class CommentUBizImpl implements ICommentUBiz {
private CommentUDaoImpl cDao=new CommentUDaoImpl();
	public List<Comment> allComments(int picId) {
		// get the comments of a photo
		return cDao.showCommentAll(picId);
	}

	public void newComment(Comment c) {
		// add a comment
		cDao.addComment(c);
	}

	public void deleteComment(int id) {
		// delete a comment
		cDao.deleteComment(id);
	}

}
