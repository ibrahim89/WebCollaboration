'use strict';

app.controller("FriendController", ['UserService','$scope','FriendService','$location','$rootScope',   
                                    function(UserService,$scope,FriendService,$location,$rootScope){
	console.log("FriendController...")
  	var self = this;
	self.friend ={
			id : '',
			userID : '',
			friendID : '',
			status : '',
			isOnline : '',
			errorMessage : '',
			errorCode : ''
	};
	self.friends = [];
	
	
	self.user = {
			id : '',
			name :'',
			email : '',
			password : '',
			address: '',
			mobile : '',
			role : '',
			errorMessage : '',
			errorCode : ''
	};
	self.users = [];
	
	self.sendRequestCount=false;
	
	self.friendaccept = function(friend){
		{
			self.accept(friend,friend.id);
		}
	};

	self.accept =function (friend,id){
		console.log('accepting the user');
		FriendService.accept(friend,id).then(self.fetchAllUsers,
		  function(errresponse){
			console.log('Error while accepting user')
		});
	};
	
	self.friendreject = function(friend){
		{
			self.reject(friend,friend.id);
		}
	};

	self.reject =function (friend,id){
		console.log('accepting the user');
		FriendService.reject(friend,id).then(self.fetchAllUsers,
		  function(errresponse){
			console.log('Error while accepting user')
		});
	};
	
	self.sendFriendRequest=sendFriendRequest
	function sendFriendRequest(friendID)
	{
		console.log("->sendFriendRequest :"+friendID);
		FriendService.sendFriendRequest(friendID)
		.then
		(function(d){
			self.friend = d;
			self.sendRequestCount=true;
			alert("Friend request sent"+self.sendRequestCount)
		},
		  function(errResponse){
			self.sendRequestCount=false;
			console.error('Error while sending friend request');
		}
		);
	}
	
	self.getMyFriends = function(){
		console.log("Getting my friends");
		FriendService.getMyFriends()
		.then(
		     function(d)	{
		    	 self.friends = d;
					//alert("Got the friend request")
		     },
		     function(errResponse){
					console.error('Error while geting friends');
				}
		);
	};
	
	self.getMyAcceptedFriends = function(){
		console.log("Getting my friends");
		FriendService.getMyAcceptedFriends()
		.then(
		     function(d)	{
		    	 self.friends = d;
					//alert("Got the friend request")
		     },
		     function(errResponse){
					console.error('Error while accepting friend request');
				}
		);
	};
	
	self.updateFriendRequest = function(friend, id){
		FriendService.updateFriendRequest(friend, id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while updating friend');
					} 		
		);
	};
	
	self.deleteFriend = function(id){
		FriendService.deleteFriend(id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while deleting friend');
					} 		
		);
	};
	
	self.fetchAllUsers = function(id){
		UserService.fetchAllUsers().then(function(d){
			self.users = d;
		},
			     function(errResponse){
						console.error('Error while feteching users');
					} 		
		);
	};
	
	self.fetchAllUsers();
	
	self.getMyFriends();
	
}])
  

	
	