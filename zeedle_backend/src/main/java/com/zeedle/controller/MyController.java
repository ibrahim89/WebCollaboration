package com.zeedle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	@Autowired	(required=true)
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping("/")
	public String getHome(){
		return "index";
	}
	

	@RequestMapping("/aboutus")
	public String getabout(){
		return"aboutus";
	}
	
	

	}