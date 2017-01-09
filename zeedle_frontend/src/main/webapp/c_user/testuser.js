'use strict';

app.controller('UserController',['$http','$scope','UserService','$location','$rootScope','$cookieStore',
       function($http,$scope,UserService,$location,$rootScope,$cookieStore){
	console.log("UserController...")
	var self = this;
	self.user = {
			id : '',
			name :'',
			email : '',
			password : '',
			mob_no : '',
			dob : '',
			gender : '',
			role : '',
			status : '',
			errorMessage : '',
			errorCode : ''
	};
	self.users = [];
	
	$scope.getform=false;
	$scope.getgroup=false;
	
	        
			
	        self.fetchAllUsers = function(){
				UserService.fetchAllUsers().then(function(d){
					self.users = d;
				}, function(errResponse){
					console.error('Error while fetching Users');
				});
			};
	
			self.createUser = function(user){
				console.log("createUser...")
				UserService
				   .createUser(user)
				   .then(self.fetchAllUsers,
						function(errResponse)
						{
					console.error('Error while creating User');
				});
			};
			
			self.useraccept = function(user){
				{
					self.accept(user,user.id);
				}
			};
	
			self.accept =function (user,id){
				console.log('accepting the user');
				UserService.accept(user,id).then(self.fetchAllUsers,
				  function(errresponse){
					console.log('Error while accepting user')
				});
			};
			
			
			self.userreject = function(user){
				{
					self.reject(user,user.id);
				}
			};
	
			self.reject =function (user,id){
				console.log('rejecting the user');
				UserService.reject(user,id).then(self.fetchAllUsers,
				  function(errresponse){
					console.log('Error while rejecting user')
				});
			};
			
			self.userupdate = function(user)
			{
				self.updateUser(self.user);
			
			}
			
			self.updateUser = function(user,id){
				console.log('updating the user');
				UserService.updateUser(user,id).then(self.fetchAllUsers,
						function(errResponse){
					console.error('Error while updating User');
				});
			};
	
			self.authenticate = function(user){
				console.log("authenticate in userctrller")
				UserService.authenticate(user).then(
						function(d) {
							   
							self.user = d;
							console.log("user.errorcode: " + self.user.errorcode)
							
							 if(self.user.status=='R')
								   {
								 
								 alert("Your registration  is Rejected. Please contact Admin");
									   user.setErrorCode("404");
									   user.setErrorMessage("Your registration is Rejected. Please contact Admin");
								   }
							 
							
							if(self.user.status=='N')
								
								{
								
								alert("Your registration is not approved still. Please contact Admin.")
								 user.setErrorCode("404");
								   user.setErrorMessage("Your registration is not approved still. Please contact Admin");
								
								
								}
							
							/*if(self.user.errorcode="404"){
								alert("Invalid Credentials . Please Try again")
								self.user.id = "";
								self.user.password = "";
							}*/
							     else
							     
							     {
							    	 console.log("Valid credentials. Navigating to home page.")
							    	 $rootScope.currentUser = {
							    		 
							    		 name : self.user.name,
							    		 id : self.user.id,
							    		 role : self.user.role,
							    		 email:self.user.email,
									  password:self.user.password,
											dob:self.user.dob,
											mob_no:self.user.mob_no,
											address:self.user.address,
											gender:self.user.gender
							    		 
							    	 };
							    	 $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
							    	 
							    	 $cookieStore.put('currentUser', $rootScope.currentUser);
							    	 
							    	 $location.path("/");
							     }
						   },
							
							function(errResponse){
							console.error('Error while aunthenticate users');
						});
			};
				
			
			
			self.logout = function(){
				console.log('calling the method logout')
				$rootScope.currentUser={};
				$cookieStore.remove('currentUser');
				
				console.log('calling the method logout of User Service')
				UserService.logout()
				$location.path('#/login');
			}
			
					self.deleteUser = function(id){
						UserService.deleteUser(id).then(self.fetchAllUsers,
								function(errResponse){
							console.error('Error while deleting User');
						});
					};
					
				
					self.fetchAllUsers();	
				
				self.login= function(){
					{
						console.log('login validation??',self.user);
						self.authenticate(self.user);
					}
				};
				
				self.submit = function(){
					{
						console.log('saving new User', self.user);
						self.createUser(self.user);
					}
					self.reset();
				};
				
				
				
				self.usermyprofile = function(){
					{
						self.myprofile();
					}
				}
				
				self.myprofile = function(){
					console.log("myProfile...")
					UserService.myprofile($rootScope.currentUser.id)
					.then(function(d){
						self.user=d;
						$location.path("/myprofile")
					},
					function(errResponse){
						console.error('Error while fetching profile.');
					})
				}
				
				 self.reset=function(){
		        	  console.log('resetting the form',self.user);
		        	  self.user={
		        			  id : '',
		        				name :'',
		        				email : '',
		        				password : '',
		        				mob_no : '',
		        				dob : '',
		        				gender : '',
		        				role : '',
		        				errorMessage : '',
		        				errorCode : ''
		        	  };
		        	  $scope.myForm.$setPristine();//reset form
		          };
		          
		          
				
				self.edit= function(id){
					console.log('id to be edited',id);
					for(var i = 0; i < self.users.length; i++){
						if (self.users[i].id === id){
							self.user = angular.copy(self.users[i]);
							break;
						}
					}
				};
}])