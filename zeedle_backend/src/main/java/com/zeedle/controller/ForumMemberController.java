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

import com.zeedle.model.ForumMember;
import com.zeedle.service.ForumMemberService;

@RestController
public class ForumMemberController {

	@Autowired(required = true)
	private ForumMemberService forumMemberService;
	
	@RequestMapping(value="/forummember", method=RequestMethod.GET)
	public ResponseEntity<List<ForumMember>> getBlogCommentByBlogId(HttpSession session)
	{
		int forumId = (Integer)session.getAttribute("fId");
		List<ForumMember> forumMember = forumMemberService.listByForumId(forumId);
		return new ResponseEntity<List<ForumMember>>(forumMember, HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/forummember", method=RequestMethod.GET)
	public ResponseEntity<List<ForumMember>> listForumMembers(){
		
		List<ForumMember> forumMember = forumMemberService.listForumMember() ;
		if(forumMember.isEmpty()){		
			return new ResponseEntity<List<ForumMember>>(HttpStatus.NO_CONTENT);		
		}	
		return new ResponseEntity<List<ForumMember>>(forumMember,HttpStatus.OK);	
	}*/
	
	@RequestMapping(value="/forummember", method=RequestMethod.POST)
	public ResponseEntity<ForumMember> createForumMember(@RequestBody ForumMember forumMember, HttpSession session){
		
		if(forumMemberService.get(forumMember.getForumMemberId())== null)
		{
			int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
			forumMember.setUserId(loggedInUserID);
			int forumId = (Integer)session.getAttribute("fId");
			forumMember.setForumId(forumId);
			forumMemberService.addForumMember(forumMember);
			return new ResponseEntity<ForumMember>(forumMember,HttpStatus.OK);
		}
		return new ResponseEntity<ForumMember>(forumMember,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forummember/{forumMemberId}", method=RequestMethod.DELETE)
	public ResponseEntity<ForumMember> deleteForumMember(@PathVariable("forumMemberId") int forumMemberId)
	{
		ForumMember forumMember=forumMemberService.get(forumMemberId);
		if(forumMember==null)
		{
			forumMember=new ForumMember();
			return new ResponseEntity<ForumMember>(forumMember,HttpStatus.NOT_FOUND);
		}
		forumMemberService.delete(forumMemberId);
		return new ResponseEntity<ForumMember>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/forummember/{forumMemberId}", method=RequestMethod.GET)
	public ResponseEntity<ForumMember> getForumMember(@PathVariable("forumMemberId") int forumMemberId, HttpSession session)
	{
		ForumMember forumMember=forumMemberService.get(forumMemberId);
		if(forumMember==null)
		{
			forumMember=new ForumMember();
			return new ResponseEntity<ForumMember>(forumMember,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForumMember>(forumMember,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forummember/{forumMemberId}", method=RequestMethod.PUT)
	public ResponseEntity<ForumMember> updateForumMember(@RequestBody ForumMember forumMember){
		
		if(forumMemberService.get(forumMember.getForumMemberId())== null)
		{
			forumMember=new ForumMember();
			return new ResponseEntity<ForumMember>(forumMember,HttpStatus.NOT_FOUND);	
		}
		forumMemberService.updateForumMember(forumMember);
		return new ResponseEntity<ForumMember>(forumMember,HttpStatus.OK);
	}

}
