package com.zeedle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C_ForumMember")
public class ForumMember {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumMemberId;
	private int forumId;
	private int userId;
	public int getForumMemberId() {
		return forumMemberId;
	}
	public void setForumMemberId(int forumMemberId) {
		this.forumMemberId = forumMemberId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	
	
	
	
}
