package com.zeedle.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zeedle.model.UserDetail;
import com.zeedle.service.FriendsService;
import com.zeedle.service.UserService;

@RestController
public class UserController {
	
	 private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	@Autowired (required=true)
	private UserService userService;
	
	@Autowired (required=true)
	private FriendsService friendService;
	@Autowired
	UserDetail user;
	private Path path;
	
	
	//for getting id
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserDetail> getUserById(@PathVariable("id") Integer id) {
		UserDetail user = userService.getUserById(id);
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}
	
	// for getAll user
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetail>> getAllUser() {
		List<UserDetail> list = userService.getAllUser();
		return new ResponseEntity<List<UserDetail>>(list, HttpStatus.OK);
	}
	/*// FOR REGISTERATOIN
	 @RequestMapping(value="/user", method = RequestMethod.POST)
	   public ResponseEntity<UserDetail> register(@RequestBody UserDetail user , HttpServletRequest request)
	   {
		   Logger.debug("Starting the method register");
		   
		   if(userService.getUserById(user.getId()) != null)
		   {
			   //User Exist with this id.
			   
			   user.setErrorCode("404"); 
			   user.setErrorMessage("User already exist with the id :" + user.getId());
		   }
		   else
		   {
			   
			   user.setStatus("N");
			   user.setIsOnline('N');
			   Logger.debug("Going to save in DB");

		   
		   MultipartFile file=user.getImage();
			String rootDirectory=request.getSession().getServletContext().getRealPath("/");
			path=(Path) Paths.get(rootDirectory + "\\resources\\images\\"+user.getId()+".jpg");
			
			
			if(file!=null && !file.isEmpty()){
				try{
					file.transferTo(new File(path.toString()));
					System.out.println("Image Uploaded to Product.....");
				}catch(Exception e)
				{
					e.printStackTrace();
					throw new RuntimeException("image saving failed ",e);
				}
			}
			
			FileUtil.upload(path.toString(), file, user.getId() + ".jpg");
		   
		   Logger.debug("Ending the method register");
		   
		   boolean flag = userService.addUser(user); 
		  
		   
	   }
		   return new ResponseEntity<UserDetail>(user , HttpStatus.OK);
	
	   }*/
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetail> saveUserDataAndFile( @RequestParam(value = "file") MultipartFile file,@RequestParam(value = "name") String name,@RequestParam(value = "email") String email,@RequestParam(value = "address") String address,@RequestParam(value = "mobile") String mobile,@RequestParam(value = "password") String password,
	    HttpServletRequest request) {
		System.out.println("Inside name" + name);
		System.out.println("Inside email" + email);
		System.out.println("Inside address" + address);
		System.out.println("Inside password" + password);
		System.out.println("Inside File upload" + file);
		UserDetail user = new UserDetail();
		if(userService.getUserById(user.getId())==null)
		user.setName(name);
		user.setEmail(email);
		user.setAddress(address);
		user.setMobile(mobile);
		user.setPassword(password);
		
		user.setRole("Student");
		userService.addUser(user);
		int Id =user.getId();
		//C:\\zeedle_frontend\\src\\main\\webapp\\resources\\images\\
	   String rootDirectory = "C:\\collaboration-website\\zeedle_frontend\\src\\main\\webapp\\resources\\images\\"+Id+".jpg";
	   File f=new File(rootDirectory);
	 

		if(!file.isEmpty()){
			try{
				byte[] bytes=file.getBytes();
				FileOutputStream fos=new FileOutputStream(f);
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				bs.write(bytes);
				bs.close();
				System.out.println("File successfully upoaded"+rootDirectory);
			} catch(Exception ex){
				System.out.println("Exception Occured"+ex);
			}
		}
		else {
			System.out.println("File is empty not uploaded");
			}
	       return new ResponseEntity<UserDetail>(HttpStatus.OK);
		
	}
	
	// update user picture
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetail> updateUserDataAndFile( @RequestParam(value = "file") MultipartFile file, HttpServletRequest request,HttpSession session) {
	
		System.out.println("Inside File upload" + file);
		
		
		
		int Id =(Integer)session.getAttribute("loggedInUserId");
		
		//C:\\zeedle_frontend\\src\\main\\webapp\\resources\\images\\
		/*String rootDirectory = request.getSession().getServletContext().getRealPath("/");*/
		/*System.out.println("Real path is="+rootDirectory);*/
	   String rootDirectory = "C:\\collaboration-website\\zeedle_frontend\\src\\main\\webapp\\resources\\images\\"+Id+".jpg";
	   File f=new File(rootDirectory);
	 

		if(!file.isEmpty()){
			try{
				byte[] bytes=file.getBytes();
				FileOutputStream fos=new FileOutputStream(f);
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				bs.write(bytes);
				bs.close();
				System.out.println("File successfully upoaded"+rootDirectory);
			} catch(Exception ex){
				System.out.println("Exception Occured"+ex);
			}
		}
		else {
			System.out.println("File is empty not uploaded");
			}
	       return new ResponseEntity<UserDetail>(HttpStatus.OK);
		
	}

		
	// For MyProfile
	@RequestMapping(value="/myProfile", method=RequestMethod.GET)
	public ResponseEntity<UserDetail> myProfile(HttpSession session)
	{
		int loggedInUserId =(Integer)session.getAttribute("loggedInUserId");
		UserDetail user=userService.getUserById(loggedInUserId);
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}
	
	
	//For update user
	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT )
	public ResponseEntity<UserDetail> updateUser(@RequestBody UserDetail user) {
		userService.updateUser(user);
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}
 //for profile update
	/*@RequestMapping(value = "/user/{id}" , method = RequestMethod.PUT)
	   public ResponseEntity<UserDetail> updateuser (@PathVariable("id") int id, @RequestBody UserDetail user,HttpSession session)
	   {
		   Logger.debug("->->-> calling method UpdateUser");
		   int loggedInUserId =(Integer)session.getAttribute("loggedInUserId");
		   user=userService.getUserById(loggedInUserId);

		   user = userDAO.get(user.getId());
		   user = userDAO.get(id);
		   
		   if(userService.getUserById(id) == null)
		   { 
			   Logger.debug("->->->->User does not exist with id "+ user.getId());
			   user = new UserDetail();
			   user.setErrorMessage("User does not exist with id "+ user.getId());
			   return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		   }
		   
		   user = userDAO.get(id);
		   userService.updateUser(user);
		   
		   return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	   }*/
	//For delete user
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}
	  
	  @RequestMapping(value="/user/authenticate/", method=RequestMethod.POST)
	  public ResponseEntity<UserDetail> authenticate(@RequestBody UserDetail user, HttpSession session){
		  Logger.debug("->->-> calling method authencate"); 
		  user = userService.authenticate(user.getName(), user.getPassword());
		  if(user==null){
			  user = new UserDetail(); // Do we need to create new user?
			  user.setErrorCode("404");
			  user.setErrorMessage("Invalid Credentials. please enter valid credentials");
			  }
		  else{
			  if(user.getStatus() == 'R')
			   {
				   user.setErrorCode("404");
				   user.setErrorMessage("Your registration is not approved. Please contact Admin");
			   }
		  
		  else
			  
		  {
			 
			  session.setAttribute("loggedInUser", user);
			  session.setAttribute("loggedInUserId", user.getId());
			  user.setIsOnline('Y');
		  }
			  if(userService.updateUser(user)==true){
				  user.setErrorCode("200");
				  user.setErrorMessage("You successfully logged and updated");
			  }
			  else{
				  
			  user.setErrorCode("400");
			  user.setErrorMessage("Not able to do the operation");
		  }
		  }
		  return new ResponseEntity<UserDetail> (user, HttpStatus.OK );
	  }
	    
	  @RequestMapping(value="/user/logout", method=RequestMethod.GET)
	  public  String logout(HttpSession session){
		  int loggedInUserId =(Integer)session.getAttribute("loggedInUserId");
		  session.invalidate();
		  
		  return("You successfully loggouedout");
	  }
	    
	  @RequestMapping(value="/user/makeadmin/{id}" , method = RequestMethod.POST)
	   public  ResponseEntity<UserDetail> makeadmin(@PathVariable("id") int id,@RequestBody UserDetail user) 
	   {
		   user = userService.getUserById(id);
		   user.setRole("admin");
		   
		   userService.updateUser(user);
		   
		   return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	   }
	  
	   @RequestMapping(value="/useraccept/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<UserDetail> useraccept(@PathVariable("id") String id, @RequestBody UserDetail user ) 
	   {
		   
		  user = userService.getUserById(user.getId());
		  if(user==null)
		  {
			  Logger.debug("->->->->User does not exist with id "+ user.getId());
			   user = new UserDetail();
			   user.setErrorMessage("User does not exist with id "+ user.getId());
			   return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		  }
		  
		   user.setStatus('A');;
		   
		   userService.updateUser(user);
		   
		   return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	   }
	  

	   
	   @RequestMapping(value="/userreject/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<UserDetail> userreject(@PathVariable("id") String id, @RequestBody UserDetail user ) 
	   {
		   
		  user = userService.getUserById(user.getId());
		  if(user==null)
		  {
			  Logger.debug("->->->->User does not exist with id "+ user.getId());
			   user = new UserDetail();
			   user.setErrorMessage("User does not exist with id "+ user.getId());
			   return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		  }
		  
		   user.setStatus('R');
		   
		   userService.updateUser(user);
		   
		   return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	   }
	   
	   
	 

	  
}
