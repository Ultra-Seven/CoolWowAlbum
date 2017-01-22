package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.ICommentBiz;
import com.fudan2015.dao.impl.CommentDaoImpl;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.entity.Comment;
import com.fudan2015.entity.Photo;

public class CommentBizImpl implements ICommentBiz {
private CommentDaoImpl cDao=new CommentDaoImpl();
private PhotoDaoImpl pDao=new PhotoDaoImpl();
	public List<Comment> allComments(int picId) {
		//get all comments of a photo
		return cDao.showCommentAll(picId);
	}

	public void newComment(Comment c) {
		//add a comment
		cDao.addComment(c);
		int picId=c.getPicId();
		Photo photo=pDao.showPhotoById(picId);
		photo.setComments(photo.getComments()+1);
		pDao.updatePhoto(photo);
	}

	public void deleteComment(int id) {
		//delete a comment
		int picId=cDao.showCommentById(id).getPicId();
		cDao.deleteComment(id);
		Photo photo=pDao.showPhotoById(picId);
		photo.setComments(photo.getComments()-1);
		pDao.updatePhoto(photo);
	}

}
