'use strict';

app.factory('UserDetail', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/user/:id', {id: '@id'},
	{
		updateUser: {method: 'PUT'}
	}
    );
}]);
app.controller('UserController', ['$http','$scope','$cookieStore','UserDetail','UserService','$location','$rootScope',function($http,$scope,$cookieStore,UserDetail, UserService,$location, $rootScope)  {
    var self = this;
    self.user= new UserDetail();
     
    self.users=[];
    self.fetchAllUsers = function(){
      /*  self.users = UserDetail.query();*/
    	UserService.fetchAllUsers().then(function(d){
				self.users = d;
			},function(errResponse){
				console.error('Error while fetching users');
	});
    };
      
    
 self.getSelectedUser = myProfile
    
 function myProfile(){
     console.log("MyProfile...")
 	UserService.myProfile().then(function(d){
				self.user = d;
				$location.path("/myProfile");
			},function(errResponse){
				console.error('Error while fetch profile');
 	});
 };
    
    
    
    self.fetchAllUsers();
    self.createUser = function(){
        self.user.$save(function(){
            self.fetchAllUsers();
            self.flag='created';
            self.reset();
            self.fetchAllUsers();
        });
    };
     
    self.updateUserDetail = function(){
    	console.log('Inside update');
    	if($scope.userForm.$valid) {
        	   self.user.$updateUser(function(user){
        		console.log(user); 
    		self.updatedId = user.id;
    		self.reset();
    		self.flag = 'updated'; 
        		self.fetchAllUsers();
               });
    	}
        };

   self.deleteUser = function(identity){
       var user = UserDetail.get({id:identity}, function() {
            user.$delete(function(){
                console.log('Deleting user with id ', identity);
                self.fetchAllUsers();
            });
       });
    };

    self.fetchAllUsers();

    self.submit = function() {
        if(self.user.id==null){
            console.log('Saving New User', self.user);    
            self.createUser();
        }else{
            console.log('Updating user with id ', self.user.id);
            self.updateUser();
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
    };
         
    self.edit = function(id){
    	 console.log('Inside edit');
         self.user = UserDetail.get({ id: id}, function() {
	       self.flag = 'edit'; 
	    });
         
    };
         
    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//If it is the one shown on screen, reset screen
           self.reset();
        }
        self.deleteUser(id);
    };

     
    self.reset = function(){
        self.user= new UserDetail();
        $scope.myForm.$setPristine(); //reset Form
    };
    
    self.login= function (){
    	{
    	console.log('Login Validation????????', self.user);
    	self.authenticate(self.user);
    	/*document.location.reload(true);*/
    }
    };
    self.logout = function(){
    	$rootScope.currentUser = {};
    	$cookieStore.remove('currentUser');
    	
    	console.log('calling the method logout of user service');
    	UserService.logout()
    	
    	$location.path('/');	
    };


    self.authenticate = function(user){
    	console.log("authenticate...")
    	UserService
    	.authenticate(user)
    	.then(
    			function(d){
    				self.user=d;
    				console.log("user.errorCode:"+self.user.errorCode)
    				if(self.user.errorCode== "404")
    					{
    					alert("Invalid Credentials.Please try again")
    					self.user.name="";
    					self.user.password="";
    					}
    				else{
    					console.log("Valid creditials.Navigating to index page")
    					$rootScope.currentUser={
    						name:self.user.name,
    						id:self.user.id,
    						role:self.user.role
    					};
    					$http.defaults.headers.common['Authorization']= 'Basic'+$rootScope.currentUser;
    					$cookieStore.put(
    								'currentUser',$rootScope.currentUser);
    					$location.path('/')
    					
    				
    				}
    			},
    			function(errResponse){
    				console.err('Error while authenticate Users');
    			});
    	 
    }
   

}]);