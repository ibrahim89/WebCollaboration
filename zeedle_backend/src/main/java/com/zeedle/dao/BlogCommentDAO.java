package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.BlogComment;

public interface BlogCommentDAO {

	
	public boolean save(BlogComment blogComment);
	public List<BlogComment> list();
	public List<BlogComment> get(int blogID); 
	
}
