'use strict';
app.factory('FriendService', ['$http', '$q', '$rootScope', function($http, $q, $rootScope)
                   
   {
	console.log("FriendService....")
	
	var BASE_URL="http://localhost:8080/zeedle_backend"
		return{
		getMyFriends: function(){
			console.log("Getting friends from service")
			return $http.get(BASE_URL+'/myFriends')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching Friends');
						return $q.reject(errResponse);
					});
			},
			
			getMyAcceptedFriends: function(){
				console.log("Getting accepted friends from service")
				return $http.get(BASE_URL+'/myaccepetedFriends')
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Friends');
							return $q.reject(errResponse);
						});
				},
			
			accept: function(friend,id){
				console.log("accepting in friend")
				return $http.put(BASE_URL+'/friendaccept/'+friend.id,friend)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while friend user');
							return $q.reject(errResponse);
						});
			},
			
			reject: function(friend,id){
				console.log("rejecting in friend")
				return $http.put(BASE_URL+'/friendreject/'+friend.id,friend)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while friend user');
							return $q.reject(errResponse);
						});
			},
			
			sendFriendRequest: function(friendID){
				return $http.post(BASE_URL+'/addFriend/'+friendID)
				.then(
						function(response){
							
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating friend');
							return $q.reject(errResponse);
						});
			},
			updateFriendRequest:function(friend,id){
				return $http.put(BASE_URL+'/friend/'+id,friend)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating friend');
							return $q.reject(errResponse);
						});
			},
			
			deleteFriend: function(id){
				return $http,delete(BASE_URL+'/friend/'+id)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while deleting friend');
							return $q.reject(errResponse);
						});
			},
	};	
	}])