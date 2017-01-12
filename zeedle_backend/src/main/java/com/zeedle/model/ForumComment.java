package com.zeedle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="C_ForumComment")
public class ForumComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumCommentId;
	private int userId;   //id
	private String forumCommentContent;
	private int forumId;
	
	
	public int getForumCommentId() {
		return forumCommentId;
	}
	public void setForumCommentId(int forumCommentId) {
		this.forumCommentId = forumCommentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getForumCommentContent() {
		return forumCommentContent;
	}
	public void setForumCommentContent(String forumCommentContent) {
		this.forumCommentContent = forumCommentContent;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	
	
}
