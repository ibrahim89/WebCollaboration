package com.zeedle.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class BlogComment extends BaseDomain{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int blogId;  //blogcommentid
	private int blog_ID;
	private int id;        // userId
	private String bcomment;
	private Date dateTime;
	private String rating;
	
	
	
	public int getBlog_ID() {
		return blog_ID;
	}
	public void setBlog_ID(int blog_ID) {
		this.blog_ID = blog_ID;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBcomment() {
		return bcomment;
	}
	public void setBcomment(String bcomment) {
		this.bcomment = bcomment;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	
}
