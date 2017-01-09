package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.BlogCommentDAOImpl;
import com.zeedle.model.BlogComment;

@Service
@Transactional

public class BlogCommentService {
	@Autowired (required=true)
	BlogCommentDAOImpl blogCommentDAO;
	
	public boolean save(BlogComment blogComment) {
		return blogCommentDAO.save(blogComment);
	}
	
	public List<BlogComment> list() {
		return blogCommentDAO.list();
	}
	
	public List<BlogComment> get(int blogID) {
		return blogCommentDAO.get(blogID);
	}
	
}
