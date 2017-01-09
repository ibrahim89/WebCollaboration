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
		/*********************************UPDATE USER******************************************/
			updateUser: function(user,id){
				console.log("updating in the service of user")
				return $http.put(BASE_URL+'/user/'+user.id,user)
				.then(
						function(response){
							console.log("updated the  user in service")
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating user');
							return $q.reject(errResponse);
						});
			},
		/**************************************************************************************/	
			myProfile: function(){
				return $http.get(BASE_URL+'/myProfile')
				.then(
					function(response){
						$rootScope.currentUser = response.data;
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
			/**********************************************************************************/
			accept: function(user,id){
				console.log("accepting in service")
				return $http.put(BASE_URL+'/useraccept/'+user.id,user)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating user');
							return $q.reject(errResponse);
						});
			},
			
			reject: function(user,id){
				console.log("rejecting in service")
				return $http.put(BASE_URL+'/userreject/'+user.id,user)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while rejecting user');
							return $q.reject(errResponse);
						});
			},
			
			updateUser: function(user,id){
				console.log("updating in the service of user")
				return $http.put(BASE_URL+'/user/'+user.id,user)
				.then(
						function(response){
							console.log("updated the  user in service")
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating user');
							return $q.reject(errResponse);
						});
			},
			/**********************************************************************************/

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