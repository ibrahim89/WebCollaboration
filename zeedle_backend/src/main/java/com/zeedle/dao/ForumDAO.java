package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.Forum;

public interface ForumDAO {
public boolean addForum(Forum forum);
	
	List<Forum> forumList();
	
	Forum getForumById(int forumId);
	
	void update(Forum forum);
	
    void delete(int forumId);

}
