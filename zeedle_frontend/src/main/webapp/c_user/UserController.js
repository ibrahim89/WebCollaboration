'use strict';

app.factory('UserDetail', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/user/:id', {id: '@id'},
	{
		updateUser: {method: 'PUT'}
	}
    );
}]);

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
app.controller('UserController', ['$http','$scope','$cookieStore','UserDetail','UserService','$location','$rootScope',function($http,$scope,$cookieStore,UserDetail, UserService,$location, $rootScope)  {
    var self = this;
    self.user= new UserDetail();
     
    self.users=[];
    self.fetchAllUsers = function(){
      self.users = UserDetail.query();
    	UserService.fetchAllUsers().then(function(d){
				self.users = d;
			},function(errResponse){
				console.error('Error while fetching users');
	});
    };
      
    
 self.getSelectedUser = myProfile
    
 function myProfile(){
     console.log("MyProfile...")
 	UserService.myProfile($rootScope.currentUser.id).then(function(d){
				self.user = d;
				consol.log("User Id="+self.user.id);
				$location.path("/myProfile");
			},function(errResponse){
				console.error('Error while fetch profile');
 	});
 };
    
 self.reset=function(){
	  console.log('resetting the form',self.user);
	  self.user={
			  id : '',
				name :'',
				email : '',
				password : '',
				mobile : '',
				role : '',
				errorMessage : '',
				errorCode : ''
	  };
	  $scope.myForm.$setPristine();//reset form
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
     
   /* self.updateUserDetail = function(){
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
*/
   self.deleteUser = function(identity){
       var user = UserDetail.get({id:identity}, function() {
            user.$delete(function(){
                console.log('Deleting user with id ', identity);
                self.fetchAllUsers();
            });
       });
    };

    self.fetchAllUsers();

   /*self.submit = function() {
        if(self.user.id==null){
            console.log('Saving New User', self.user);    
            self.createUser();
        }else{
            console.log('Updating user with id ', self.user.id);
            self.updateUser();
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
   */
    /**********************************SUBMIT USER WITH IMAGE**************************************/
    self.submitUser = function(isValid) {
    	var name = $scope.name;
    	var address = $scope.address;
    	var email = $scope.email;
    	var password = $scope.password;
    	var file = $scope.myFile;
    	var mobile = $scope.mobile;
    	/* console.log('file is ' );
    	console.dir(file);*/
    	var uploadUrl = "http://localhost:8080/zeedle_backend/user";
    	var fd = new  FormData();
    	fd.append('name', name);
    	console.log("name"+name);
    	fd.append('address', address);
    	fd.append('mobile', mobile);
    	fd.append('email', email);
    	fd.append('password', password);
    	fd.append('file', file);
    	console.log("image"+file)
    	$http.post(uploadUrl, fd, {
    	transformRequest : angular.identity,
    	headers : {
    	'Content-Type' : undefined
    	}
    	}).success(function() {
    	console.log('success');
    	}).error(function() {
    	console.log('error');
    	});
    	}
    /**********************************UPDATE USER IMAGE**************************************/
    self.updateUser = function() {
    	
    	var file = $scope.myFile;
    
    	 console.log('file is ' );
    	console.dir(file);
    	var uploadUrl = "http://localhost:8080/zeedle_backend/userupdate";
    	var fd = new  FormData();
    	console.log("image"+file)
    	fd.append('file', file);
    	$http.post(uploadUrl, fd, {
    	transformRequest : angular.identity,
    	headers : {
    	'Content-Type' : undefined
    	}
    	}).success(function() {
    	console.log('success');
    	}).error(function() {
    	console.log('error');
    	});
    	}
    /**********************************USER UPDATE********************************************************/
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
    /**********************************ADMIN PART*************************************/
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
   /**********************************************************************************/
         
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
    	UserService.authenticate(user).then(
    			function(d){
    				
    				self.user=d;
    				console.log("user.errorCode:"+self.user.errorCode)
    			if(self.user.status=='R')
    				{
    				alert("Your Registration is Rejected. Please contact Admin");
    					user.setErrorCode("404");
    					user.setErrorMessage("Your Registeration is Rejected. Please contact Admin");
    				}
    				if(self.user.status=='N')
								
								{
								
								alert("Your registration is not approved still. Please contact Admin.")
								 user.setErrorCode("404");
								   user.setErrorMessage("Your registration is not approved still. Please contact Admin");
								
								
								}
    				/*if(self.user.errorCode== "404")
    					{
    					alert("Invalid Credentials.Please try again")
    					self.user.name="";
    					self.user.password="";
    					self.user.mobile="";
    					self.user.address="";
    					self.user.email="";
    					}*/
    				else{
    					console.log("Valid creditials.Navigating to home page")
    					$rootScope.currentUser={
    						name:self.user.name,
    						password:self.user.password,
    						id:self.user.id,
    						role:self.user.role,
    						email:self.user.email,
    						address:self.user.address,
    						mobile:self.user.mobile,
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