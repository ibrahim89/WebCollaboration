package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.ForumMemberDAOImpl;
import com.zeedle.model.ForumMember;

@Service
@Transactional
public class ForumMemberService {
	@Autowired
	public ForumMemberDAOImpl forumMemberDAO;
	
	public void addForumMember(ForumMember forumMember) {
		forumMemberDAO.addForumMember(forumMember);
	}
	
	public List<ForumMember> listByForumId(int forumId) {
		return forumMemberDAO.listByForumId(forumId);
	}
	
	public void delete(int forumMemberId) {
		forumMemberDAO.delete(forumMemberId);
	}
	
	public ForumMember get(int forumMemberId) {
		return forumMemberDAO.get(forumMemberId);
	}
	
	public void updateForumMember(ForumMember forumMember) {
		forumMemberDAO.updateForumMember(forumMember);
	}
}
