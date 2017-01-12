package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.ForumComment;

public interface ForumCommentDAO {

	public void addForumComment(ForumComment forumComment);
	/*public List<ForumComment> listForumComments();*/
	public List<ForumComment> listByForumId(int forumId);
	public void delete(int forumCommentId);
	public ForumComment get(int forumCommentId);
	public void updateForumComment(ForumComment forumComment);
}
