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
	
	public void addForum(Forum forum) {
		forumDAO.addForum(forum);
	}
	
	public List<Forum> listForum() {
		return forumDAO.listForum();
	}
	
	public void delete(int forumId) {
		forumDAO.delete(forumId);
	}
	
	public Forum get(int forumId) {
		return forumDAO.get(forumId);
	}
	
	public void updateForum(Forum forum) {
       forumDAO.updateForum(forum);
	}
	
	
}
