package com.zeedle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zeedle.model.BlogComment;
import com.zeedle.service.BlogCommentService;


@RestController
public class BlogCommentController {

	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private BlogComment blogComment;
	
	@RequestMapping(value = "/blogcomments" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getblogcomments()
	{
		List<BlogComment> blogcomment = blogCommentService.list();
		if(blogcomment == null)
		{
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		 else
		 {
       	  return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blogcomment/{blogID}" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getBlog(@PathVariable("blogID")int blogID){
		
		List<BlogComment> blogcomments = blogCommentService.get(blogID);
		if(blogcomments == null)
		{
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("Blog not found with the id"+ blogID);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogcomments,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blogcomment/{blogId}/{bcomment}/{rating}" , method = RequestMethod.POST)
	public ResponseEntity<BlogComment> createBlog(@RequestBody BlogComment blogcomment, @PathVariable("blogId") int blogId,
			                @PathVariable("bcomment") String bcomment, @PathVariable("rating") String rating, HttpSession httpsession) {
		
		System.out.println("Comment from the front end "+blogcomment.getBcomment());
		int loggedInUserId = (Integer) httpsession.getAttribute("loggedInUserId");
		System.out.println("loggedInUserId="+loggedInUserId);
   		blogComment.setId(loggedInUserId);  	
   		blogComment.setBlog_ID(blogId);
   		blogComment.setBcomment(bcomment);
   		blogComment.setRating(rating);
   		blogComment.setDateTime(null);
   	
		blogCommentService.save(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	
	
}
