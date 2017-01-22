package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Comment;

public interface CommentDao {
	//提供comment对象，将comment加到数据库中
	public void addComment(Comment com);
	//提供id（主键），将对应的comment删除
	public void deleteComment(int id);
	//提供picture的id，返回相应的所有comment的list
	public List<Comment> showCommentAll(int id);
	public Comment showCommentById(int id); 
}
