package com.fudan2015.test;





import org.junit.Test;

import com.fudan2015.dao.impl.CommentDaoImpl;
import com.fudan2015.entity.Comment;

public class CommentImplTest {

	
	public CommentImplTest() {
		
	}
	@Test
	public void test() {
		CommentDaoImpl comment=new CommentDaoImpl();
		Comment com=new Comment();
		com.setComment("");
		com.setDate("2015-07-16 20:00:00");
		com.setId(1);
		com.setObserver("zhangsan");
		comment.addComment(com);
	}
}
