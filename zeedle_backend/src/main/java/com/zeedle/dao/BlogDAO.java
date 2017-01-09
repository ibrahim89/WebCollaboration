package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.Blog;




public interface BlogDAO {
	
	public boolean save(Blog blog);
	public boolean update(Blog blog);
	public Blog delete(int blogId);
	public List<Blog> list();
	public Blog get(int blogId);


}
