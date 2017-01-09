package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.ForumDAOImpl;
import com.zeedle.model.Forum;

@Service
@Transactional
public class ForumService {

	@Autowired(required=true)
	ForumDAOImpl forumDAO;
	
	public boolean addForum(Forum forum) {
		return forumDAO.addForum(forum);
	}
	
	
	public List<Forum> forumList() {
		return forumDAO.forumList();
	}
	
	public Forum getForumById(int forumId) {
		return forumDAO.getForumById(forumId);
	}
	
	public void update(Forum forum) {
		forumDAO.update(forum);
	}
	
	public void delete(int forumId) {
		forumDAO.delete(forumId);
	}
}
