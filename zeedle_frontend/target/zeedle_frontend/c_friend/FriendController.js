'use strict';

app.controller('FriendController', ['$scope','FriendService','UserService','$location','$rootScope',function($scope,FriendService,UserService,$location, $rootScope){
	console.log("FriendController...")
	var ob = this;
	ob.friend={fId:'',id:'',friendId:'',status:''};
	ob.friends=[];
	
	ob.user = {
			id : '',
			name : '',
			email :'',
			address : '',
			mobile : '',
			role : '',
			password :'',
			errorMessage :'',
			};
	ob.users = [];
	 
	ob.sendFriendRequest=sendFriendRequest
	
	function sendFriendRequest(friendId)
	{
		console.log("->sendFriendRequest :"+friendId)
		   FriendService.sendFriendRequest(friendId)
		   	.then(
		   			function(d){
		   				ob.friend = d;
		   				alert("Friend request sent")
		   			},
		   			function(errResponse){
		   				console.error('Error while sending friend request');
		   			}
		   			);
	};
	ob.acceptFriendRequest=acceptFriendRequest
	function acceptFriendRequest(friendId)
	{
		console.log("->acceptFriendRequest :"+friendId)
		   FriendService.acceptFriendRequest(friendId)
		   	.then(
		   			function(d){
		   				ob.friend = d;
		   				/*alert("Friend request sent")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while accepting friend request');
		   			}
		   			);
	};
	ob.rejectFriendRequest=rejectFriendRequest
	function rejectFriendRequest(friendId)
	{
		console.log("->rejectFriendRequest :"+friendId)
		   FriendService.rejectFriendRequest(friendId)
		   	.then(
		   			function(d){
		   				ob.friend = d;
		   				/*alert("Friend request sent")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while rejecting friend request');
		   			}
		   			);
	};
	ob.unFriend=unFriend
	function unFriend(friendId)
	{
		console.log("->unFriend :"+friendId)
		   FriendService.unFriend(friendId)
		   	.then(
		   			function(d){
		   				ob.friend = d;
		   				/*alert("Friend request sent")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while unfriend');
		   			}
		   			);
	};
	ob.getMyFriends = function(){
		console.log("Getting my friends")
		FriendService.getMyFriends()
			.then(
					function(d){
		   				ob.friend = d; 
		   				/*alert("get my friends")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while getting my friends');
		   			}	
				);
	};
	ob.getMyFriend = function(){
		console.log("Getting my friend")
		FriendService.getMyFriend()
			.then(
					function(d){
		   				ob.friend = d;
		   				/*alert("get my friends")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while getting my friend');
		   			}	
				);
	};
	ob.getMyFriendRequests = function(){
		console.log("Getting my friend requests")
		FriendService.getMyFriendRequests()
			.then(
					function(d){
		   				ob.friend = d;
		   				/*alert("get my friend requests")*/
		   			},
		   			function(errResponse){
		   				console.error('Error while getting my friend requests');
		   			}	
				);
	};
	ob.updateFriendRequest = function(fId, friend){
		FriendService.updateFriendRequest(id, friend)
			.then(
					ob.fetAllFriends,
					function(errResponse){
						console.error('Error while updating Friend.');
					}
				);
	};
	ob.deleteFriend = function(fId){
		FriendService.deleteFriend(fId)
			.then(
					ob.fetAllFriends,
					function(errResponse){
						console.error('Error while deleting Friend.');
					}
				);
	};
	ob.fetchAllUsers = function(){
		UserService.fetchAllUsers().then(function(d){
			ob.users = d;
		}, function(errResponse){
			console.error('Error while fetching users');
		});
	};
	
	ob.fetchAllUsers();
	/*ob.getMyFriends();*/
	
	
}]); 