package com.zeedle.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.zeedle.model.Friends;
import com.zeedle.model.UserDetail;
import com.zeedle.service.FriendsService;;

@RestController

public class FriendController {

	 private static final Logger Logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendsService friendService;
	
	@Autowired
	Friends friends;
	
	@RequestMapping(value="/myFriends",method = RequestMethod.GET)
    public ResponseEntity<List<Friends>> getMyFriends(HttpSession session) {
	 
		int loggedInUserId =(Integer)session.getAttribute("loggedInUserId");
		System.out.println("loggedInUserId is="+loggedInUserId);
		List<Friends> myFriends= friendService.getMyFriend(loggedInUserId);
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
		
		/*Logger.debug("->->->calling method get my friend");
		int loggedInUserID = (Integer) session.getAttribute("loggedInUserID");
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		List<Friends> myFriends = friendService.getMyFriend(loggedInUserID);
		
		if(myFriends.isEmpty())
		{
			Logger.debug("friends doest not exits for the user :" +loggedInUserID);
			friends.setErrorCode("404");
			friends.setErrorMessage("You does not have friends");
			myFriends.add(friends);
			
		}
		Logger.debug("send the friend list");
		
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);*/
	}
	
	@RequestMapping(value="/addFriend/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Friends> sendFriendRequest(@PathVariable("friendID") int friendID,HttpSession session) {
		
		Logger.debug("->->->calling method sendfriendRequest");
		UserDetail loggedInUser=(UserDetail) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("N");
		friends.setIsOnline('Y');
		friendService.save(friends);
		
		
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> unFriend(@PathVariable("friendID") int friendID, HttpSession session) {
		
		UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("U");//N -> New , R-> Rejected , A -> Accepted
		friendService.save(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> rejectFriendFriendRequest(@PathVariable("friendID") int friendID, HttpSession session){
		
		UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("R");
		friendService.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/getMyFriendRequests",method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> getMyFriendRequests(HttpSession session) {
		
		Logger.debug("->->->calling method getmyfriendRequests");
		int loggedInUserID = (Integer) session.getAttribute("loggedInUserID");
		List<Friends> myFriendRequests = friendService.getNewFriendRequests(loggedInUserID);
		return new ResponseEntity<List<Friends>>(myFriendRequests, HttpStatus.OK);
		
		/*User loggedInUser = (User) session.getAttribute("loggedInUser");
		friendsDAO.getNewFriendRequests(loggedInUser.getId());
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);*/
	}
	
	@RequestMapping(value="/acceptFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> acceptFriendRequest(@PathVariable("friendID") int friendID, HttpSession session) {
		
		UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("A");
		friendService.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	//Just for testing purpose from restclient.
	@RequestMapping(value="/myFriends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> getMyFriendsTemp(@PathVariable("id") int id) {
	
		List<Friends> myFriends = friendService.getMyFriend(id);
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
	}
	
	 @RequestMapping(value="/friendaccept/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<Friends> friendaccept(@PathVariable("id") String id, @RequestBody Friends friends ) 
	   {
		   friends = friendService.get(friends.getId());
		
		  if(friends==null)
		  {
			  Logger.debug("->->->->User does not exist with id "+ friends.getId());
			  friends = new Friends();
			  friends.setErrorMessage("User does not exist with id "+ friends.getId());
			   return new ResponseEntity<Friends>(friends, HttpStatus.NOT_FOUND);
		  }
		  
		  friends.setStatus("A");
		   
		  friendService.update(friends);
		   
		   return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	   }
	
	 @RequestMapping(value="/friendreject/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<Friends> friendreject(@PathVariable("id") String id, @RequestBody Friends friends ) 
	   {
		   friends = friendService.get(friends.getId());
		
		  if(friends==null)
		  {
			  Logger.debug("->->->->User does not exist with id "+ friends.getId());
			  friends = new Friends();
			  friends.setErrorMessage("User does not exist with id "+ friends.getId());
			   return new ResponseEntity<Friends>(friends, HttpStatus.NOT_FOUND);
		  }
		  
		  friends.setStatus("R");
		   
		  friendService.update(friends);
		   
		   return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	   }
		 
}
