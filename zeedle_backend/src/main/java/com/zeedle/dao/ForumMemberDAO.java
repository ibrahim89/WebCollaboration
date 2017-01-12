package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.ForumMember;

public interface ForumMemberDAO {
	public void addForumMember(ForumMember forumMember);
	/*public List<ForumMember> listForumMember();*/
	public List<ForumMember> listByForumId(int forumId);
	public void delete(int forumMemberId);
	public ForumMember get(int forumMemberId);
	public void updateForumMember(ForumMember forumMember);
}
