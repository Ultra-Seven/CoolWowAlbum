package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.Comment;

public interface ICommentUBiz {
   public void newComment(Comment c);
   //give a comment
   public void deleteComment(int id);
   //delete a comment
   public List<Comment> allComments(int picId);
   //get all comments
}
