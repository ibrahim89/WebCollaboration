'use strict';
app.factory('FriendService',['$http','$q','$rootScope',function($http, $q, $rootScope){
	
console.log("FriendService...")
	
	var BASE_URL = 'http://localhost:8080/zeedle_backend';
		return{
		getMyFriends: function(){
			return $http.get(BASE_URL+'/myFriends')
			.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching friends');
					return $q.reject(errResponse);
					}			
				);
		},
		getMyFriend: function(){
			return $http.get(BASE_URL+'/myFriend')
			.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching friend');
					return $q.reject(errResponse);
					}			
				);
		},
		getMyFriendRequests: function(){
			return $http.get(BASE_URL+'/getMyFriendRequests/')
			.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching friend requests');
					return $q.reject(errResponse);
					}			
				);
		},
		sendFriendRequest: function(friendId){
			return $http.post(BASE_URL+'/addFriend/'+friendId)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Creating friend');
							return $q.reject(errResponse);
							}			
						);
		},
		acceptFriendRequest: function(friendId){
			return $http.get(BASE_URL+'/acceptFriend/'+friendId)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while accepting friend request');
						return $q.reject(errResponse);
						}			
					);
		},
		rejectFriendRequest: function(friendId){
			return $http.get(BASE_URL+'/rejectFriend/'+friendId)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while rejecting friend request');
						return $q.reject(errResponse);
						}			
					);
		},
		unFriend: function(friendId){
			return $http.get(BASE_URL+'/unFriend/'+friendId)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while unfriend');
						return $q.reject(errResponse);
						}			
					);
		},
		updateFriendRequest: function(id, friend){
			return $http.put(BASE_URL+'/friend/'+fId, friend)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating friend');
							return $q.reject(errResponse);
							}			
						);
		},
		deleteFriend: function(fId){
			return $http.delete(BASE_URL+'/friend/'+fId)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while deleting friend');
							return $q.reject(errResponse);
							}			
						);
		}
	}
}]);