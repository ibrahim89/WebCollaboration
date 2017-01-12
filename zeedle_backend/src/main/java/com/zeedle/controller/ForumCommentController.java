package com.zeedle.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zeedle.model.ForumComment;
import com.zeedle.service.ForumCommentService;

@RestController
public class ForumCommentController {
	@Autowired(required = true)
	private ForumCommentService forumCommentService;
	
	/*@Autowired
	private ForumComment forumComment;*/
	
	@RequestMapping(value="/forumcomment", method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> getForumCommentByForumId(HttpSession session)
	{
		int forumId = (Integer)session.getAttribute("fId");
		List<ForumComment> forumComment = forumCommentService.listByForumId(forumId);
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/forumcomment", method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> listForumComments(){
		
		List<ForumComment> forumComment = ForumCommentService.listForumComments();
		if(forumComment.isEmpty()){		
			return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);		
		}	
		return new ResponseEntity<List<ForumComment>>(forumComment,HttpStatus.OK);	
	}*/
	
	@RequestMapping(value="/forumcomment", method=RequestMethod.POST)
	public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment, HttpSession session){
		
		if(forumCommentService.get(forumComment.getForumCommentId())== null)
		{
			int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
			forumComment.setUserId(loggedInUserID);
			int forumId = (Integer)session.getAttribute("fId");
			forumComment.setForumId(forumId);
			forumCommentService.addForumComment(forumComment);
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}
		return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forumcomment/{forumCommentId}", method=RequestMethod.DELETE)
	public ResponseEntity<ForumComment> deleteForumComment(@PathVariable("forumCommentId") int forumCommentId)
	{
		ForumComment forumComment=forumCommentService.get(forumCommentId);
		if(forumComment==null)
		{
			forumComment=new ForumComment();
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.NOT_FOUND);
		}
		forumCommentService.delete(forumCommentId);
		return new ResponseEntity<ForumComment>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/forumcomment/{forumCommentId}", method=RequestMethod.GET)
	public ResponseEntity<ForumComment> getForumComment(@PathVariable("forumCommentId") int forumCommentId)
	{
		ForumComment forumComment=forumCommentService.get(forumCommentId);
		if(forumComment==null)
		{
			forumComment=new ForumComment();
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forumcomment/{forumCommentId}", method=RequestMethod.PUT)
	public ResponseEntity<ForumComment> updateForumComment(@RequestBody ForumComment forumComment){
		
		if(forumCommentService.get(forumComment.getForumCommentId())== null)
		{
			forumComment=new ForumComment();
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.NOT_FOUND);	
		}
		forumCommentService.updateForumComment(forumComment);
		return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
	}
}
