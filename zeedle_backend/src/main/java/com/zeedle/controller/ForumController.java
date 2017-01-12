package com.zeedle.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zeedle.model.Forum;
import com.zeedle.service.ForumService;

@RestController
public class ForumController {
	@Autowired(required = true)
	ForumService forumService;

	@RequestMapping(value="/forum", method=RequestMethod.GET)
	public ResponseEntity<List<Forum>> listForums(){
		
		List<Forum> forum = forumService.listForum();
		if(forum.isEmpty()){		
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);		
		}	
		return new ResponseEntity<List<Forum>>(forum,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/forum", method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum, HttpSession session){
		
		if(forumService.get(forum.getForumId())== null)
		{
			int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
			forum.setUserId(loggedInUserID);
			forumService.addForum(forum);
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forumId}", method=RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=forumService.get(forumId);
		if(forum==null)
		{
			forum=new Forum();
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
		forumService.delete(forumId);
		return new ResponseEntity<Forum>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forumId}", method=RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("forumId") int forumId, HttpSession session)
	{
		Forum forum=forumService.get(forumId);
		session.setAttribute("fId", forum.getForumId());
		/*if(forum==null)
		{
			forum=new Forum();
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}*/
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forumId}", method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum){
		
		if(forumService.get(forum.getForumId())== null)
		{
			forum=new Forum();
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);	
		}
		forumService.updateForum(forum);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
}
