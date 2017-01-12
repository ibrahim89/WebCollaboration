package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.ForumLikeDAOImpl;
import com.zeedle.model.ForumLike;

@Service
@Transactional
public class ForumLikeService {

	@Autowired
	public ForumLikeDAOImpl forumLikeDAO;
	
	public void addForumLike(ForumLike forumLike) {
		forumLikeDAO.addForumLike(forumLike);
	}
	
	public void delete(int forumLikeId) {
		forumLikeDAO.delete(forumLikeId);
	}
	
	public ForumLike get(int forumLikeId) {
		return forumLikeDAO.get(forumLikeId);
	}
	
	public List<ForumLike> listByForumId(int forumId) {
		return forumLikeDAO.listByForumId(forumId);
	}
	


}
