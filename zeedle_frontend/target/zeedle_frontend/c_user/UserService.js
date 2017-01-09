'use strict';
 
app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q, $rootScope){
		console.log("UserService")
    var BASE_URL = 'http://localhost:8080/zeedle_backend';
			
		return {				
			
			fetchAllUsers: function(){
				return $http.get(BASE_URL+'/user')
				.then(
					function(response){
						return response.data;
					},
					null
				);
			}, 
			
			myProfile: function(){
				return $http.get(BASE_URL+'/myProfile')
				.then(
					function(response){
						$rootScope.selectedUser = response.data;
						return response.data;
					},
					null
				);
			},
			
			logout: function(){
				console.log('logout....')
				return $http.get(BASE_URL+'/user/logout')
				.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while logging out')
						return $q.reject(errMessage);
					}
				);
			},

			authenticate: function(user){
				console.log("Callig the method authenticate with the user :"+user)
				return $http.post(BASE_URL+'/user/authenticate/',user)
				.then(
					function(response){
						return response.data;
					},
					null
				);
			}
	};

   
}])