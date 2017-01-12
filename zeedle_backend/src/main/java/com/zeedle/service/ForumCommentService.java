package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.ForumCommentDAOImpl;
import com.zeedle.model.ForumComment;

@Service
@Transactional
public class ForumCommentService {

	@Autowired
	ForumCommentDAOImpl forumCommentDAO;
	
	public void addForumComment(ForumComment forumComment) {
		forumCommentDAO.addForumComment(forumComment);
	}
	
	public List<ForumComment> listByForumId(int forumId) {
		return forumCommentDAO.listByForumId(forumId);
	}
	
	public void delete(int forumCommentId) {
		forumCommentDAO.delete(forumCommentId);
	}
	
	public ForumComment get(int forumCommentId) {
		return forumCommentDAO.get(forumCommentId);
	}
  
	public void updateForumComment(ForumComment forumComment) {
		forumCommentDAO.updateForumComment(forumComment);
	}


}
