package com.zeedle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zeedle.model.Forum;
import com.zeedle.service.ForumService;

@RestController
public class ForumController {
	@Autowired(required = true)
	ForumService forumService;

	// TO SAVE THE FORUM
	@RequestMapping(value = "/forum", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Forum forum, UriComponentsBuilder builder) {
		boolean flag = forumService.addForum(forum);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/forum/{forumId}").buildAndExpand(forum.getForumId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// TO UPDATE THE FORUM
	@RequestMapping(value = "/forum/{forumId}", method = RequestMethod.PUT)
	public ResponseEntity<Forum> update(@RequestBody Forum forum) {
		forumService.update(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	// TO DELETE THE FORUM
	@RequestMapping(value = "/forum/{forumId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("forumId") int forumId) {
		forumService.delete(forumId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// TO GET THE LIST OF FORUM
	@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listForum() {
		List<Forum> list = forumService.forumList();
		return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
	}

	// TO GET FORUM BY ID
	@RequestMapping(value = "/forum/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumService.getForumById(forumId);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

}
