package com.zeedle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C_ForumLike")
public class ForumLike {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumLikeId;
	private int userId;
	private int forumId;
	public int getForumLikeId() {
		return forumLikeId;
	}
	public void setForumLikeId(int forumLikeId) {
		this.forumLikeId = forumLikeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	
	

}
