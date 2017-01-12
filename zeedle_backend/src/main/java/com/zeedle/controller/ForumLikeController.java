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

import com.zeedle.model.ForumLike;
import com.zeedle.service.ForumLikeService;

@RestController
public class ForumLikeController {

	@Autowired(required = true)
	private ForumLikeService forumLikeService;
	
	@RequestMapping(value="/forumlike", method=RequestMethod.POST)
	public ResponseEntity<ForumLike> createForumLike(@RequestBody ForumLike forumLike, HttpSession session){
		
		if(forumLikeService.get(forumLike.getForumLikeId())== null)
		{
			int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
			forumLike.setUserId(loggedInUserID);
			int ForumId = (Integer)session.getAttribute("fId");
			forumLike.setForumId(ForumId);
			forumLikeService.addForumLike(forumLike);
			return new ResponseEntity<ForumLike>(forumLike,HttpStatus.OK);
		}
		return new ResponseEntity<ForumLike>(forumLike,HttpStatus.OK);
	}
	
	@RequestMapping(value="/forumlike/{forumLikeId}", method=RequestMethod.DELETE)
	public ResponseEntity<ForumLike> deleteForumLike(@PathVariable("forumLikeId") int forumLikeId)
	{
		ForumLike forumLike=forumLikeService.get(forumLikeId);
		if(forumLike==null)
		{
			forumLike=new ForumLike();
			return new ResponseEntity<ForumLike>(forumLike,HttpStatus.NOT_FOUND);
		}
		forumLikeService.delete(forumLikeId);
		return new ResponseEntity<ForumLike>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/forumlike/{forumLikeId}", method=RequestMethod.GET)
	public ResponseEntity<ForumLike> getForumLike(@PathVariable("forumLikeId") int forumLikeId)
	{
		ForumLike forumLike=forumLikeService.get(forumLikeId);
		if(forumLike==null)
		{
			forumLike=new ForumLike();
			return new ResponseEntity<ForumLike>(forumLike,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForumLike>(forumLike,HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/forumlike/{forumId}", method=RequestMethod.GET)
	public ResponseEntity<ForumLike> getForumAllLike(@PathVariable("forumId") int forumId)
	{
		ForumLike forumLike=forumLikeService.getAllLike(forumId);
		if(forumLike==null)
		{
			forumLike=new ForumLike();
			return new ResponseEntity<ForumLike>(forumLike,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForumLike>(forumLike,HttpStatus.OK);
	}*/
	@RequestMapping(value="/forumlike", method=RequestMethod.GET)
	public ResponseEntity<List<ForumLike>> getForumLikeByForumId(HttpSession session)
	{
		int forumId = (Integer)session.getAttribute("fId");
		List<ForumLike> forumLike = forumLikeService.listByForumId(forumId);
		return new ResponseEntity<List<ForumLike>>(forumLike, HttpStatus.OK);
	}

}
