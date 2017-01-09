package com.zeedle.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zeedle.model.Blog;
import com.zeedle.service.BlogService;


@RestController
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private Blog blog;
	
	@RequestMapping(value = "/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getblogs()
	{
		List<Blog> blogs = blogService.list();
		if(blogs == null)
		{
			blog = new Blog();
			 blog.setErrorCode("404");
       	  blog.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blog/{blogId}" , method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId")int blogId){
		
		Blog blog = blogService.get(blogId);
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found with the id"+ blogId);
		}
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/" , method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession httpsession) {
		
   		int loggedInuserId = (Integer) httpsession.getAttribute("loggedInUserId");
	
		blog.setId(loggedInuserId);
		blog.setStatus("N");
		
		blogService.save(blog);
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value="/blog/{blogId}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable int blogId) {
		
		
		if(blogService.get(blogId)!=null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		blogService.delete(blogId);
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	
}
