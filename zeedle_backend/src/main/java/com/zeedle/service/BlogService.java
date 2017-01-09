package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.BlogDAOImpl;
import com.zeedle.model.Blog;

@Transactional
@Service
public class BlogService {

	@Autowired (required=true)
	BlogDAOImpl blogDAO;
	
	public boolean save(Blog blog){
		return blogDAO.save(blog);
	}
	
	public boolean update(Blog blog){
		return blogDAO.update(blog);
	}
	
	public boolean delete(Blog blog){
		return blogDAO.delete(blog);
	}
	public List<Blog> list(){
		return blogDAO.list();
	}
	
	public Blog get(int blogId) {
		return blogDAO.get(blogId);
	}
	
	public Blog delete(int blogId){
		return blogDAO.delete(blogId);
	}
	
	
}
