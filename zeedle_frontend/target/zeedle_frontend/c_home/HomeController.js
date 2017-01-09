'use strict';

app.controller('HomeController',['$scope','UserService','$rootScope', 
                                 function($scope, UserService, $rootScope) {
	
	console.log("HomeController..")
	var ob=this;
	this.getCurrentUser =function()
	{
		console.log("Loading current user")
		console.log("Current user :"+$rootScope.currentUser)
		if(!$rootScope.currentUser)
			{
			console.log("User not loggedIn")
			$rootScope.currentUser="";
			
			}
		console.log("User is logged in")
		return $rootScope.currentUser;
	}
	
    ob.getCurrentUser();
}]);

function HomeController($location) {
    Session.clear();
    $location.path('/home');
}