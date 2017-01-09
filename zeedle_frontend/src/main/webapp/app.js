'use strict';
var app = angular.module('app', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

app.config(function($routeProvider) {
	$routeProvider
	/******************home related*******************************/
	.when('/', {
		controller : 'HomeController',
		templateUrl : 'c_home/home.html'

	}).when('/aboutus', {
		controller : 'UserController',
		templateUrl : 'c_about/aboutus.html'

	})
	
	.when('/file', {
		controller : 'FileUploadController',
		templateUrl : 'c_upload/upload.html'

	})
	/******************admin related*******************************/
	.when('/adminhome',{
	templateUrl : 'c_admin/adminhome.html'
    })
	.when('/manage_users',{  //when() method takes a pathand a route as parameters.
		templateUrl : 'c_admin/manage_users.html',
		controller : 'UserController'
	})
	
	.when('/manage_jobs',{ //when() method takes a panthad a route as parameters.
		templateUrl  :  'c_admin/manage_jobs.html',
		controller   :  'UserController'
	})
	
	.when('/manage_friends',{//when() method takes a panthad a route as parameters.
		templateUrl  : 'c_admin/manage_friends.html',
		controller   :  'UserController'
	})

/******************User related*******************************/
	.when('/login', {
		controller : 'UserController',
		templateUrl : 'c_user/login.html'

	}).when('/userlist', {
		controller : 'UserController',
		templateUrl : 'resources/userlist.jsp'
	})

	.when('/userdetail', {
		controller : 'UserController',
		templateUrl : 'c_user/userdetail.html'

	})

	.when('/myProfile', {
		controller : 'UserController',
		templateUrl : 'c_user/profile.html'
	})

	
	
	
	/********************* Blog Related***************************/
   .when('/create_blog',{
	templateUrl : 'c_blog/create_blog.html',
	controller : 'BlogController'
	})
	
   .when('/list_blog',{
	templateUrl : 'c_blog/list_blog.html',
		controller : 'BlogController'	
   })

   .when('/view_blog',{
	templateUrl : 'c_blog/view_blog.html',
	controller : 'BlogController'
	})
	
	/********************* Chat Related***************************/
	.when('/chat', {
		controller : 'ChatController',
		templateUrl : 'c_chat/chat.html'

	     })
	.when('/chatforum', {
		controller : 'ChatForumController',
		templateUrl : 'c_chatForum/chatforum.html'

	     })
	.when('/forum', {
		controller : 'ForumController',
		templateUrl : 'c_forum/forum.html'

	}).when('/event', {
		controller : 'EventController',
		templateUrl : 'c_event/event.html'

	})

	.when('/logout', {
		controller : 'UserController'
	})
	/******************Job related*******************************/
  .when('/view_applied_jobs',{
	templateUrl : 'c_job/view_applied_jobs.html',
	controller : 'JobController'
	})
	
   .when('/post_job',{
	templateUrl : 'c_job/post_job.html',
		controller : 'JobController'	
    })
    
   .when('/view_job_details',{
	templateUrl : 'c_job/view_job_details.html',
	controller : 'JobController'
	})
	
   .when('/search_job',{
	templateUrl : 'c_job/search_job.html',
	controller : 'JobController'	
    })

    /*******************Friends related mapping*******************/
    .when('/add_friend',{
	templateUrl : 'c_friend/add_friend.html',
	controller : 'FriendController'
	})
	
	.when('/search_friend',{
		templateUrl : 'c_friend/search_friend.html',
		controller : 'FriendController'
	})

	.when('/view_friend',{
		templateUrl : 'c_friend/view_friend.html',
		controller : 'FriendController'
	})

	.otherwise({
		redirectTo : '/'
	});

})

/****************Security Related*************************/
app.run(function($rootScope, $location, $cookieStore, $http) {

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		console.log("$locationChangeStart")
		http://localhost:8081/zeedle_frontend/addjob
		// redirect to login page if not logged in and trying to access a restricted page
		var restrictedPage = $.inArray($location.path(), [ '//','/','/search_job','/view_blog','/login','/list_blog' ]) === -1;
		console.log("restrictedPage:" + restrictedPage)
		var loggedIn = $rootScope.currentUser.id;
		console.log("loggedIn:" + loggedIn)
		
		if (!loggedIn) {
			
			if (restrictedPage) {
				console.log("Navigating to login page")
				$location.path('/login');
			}
		} 
		else {
			var role = $rootScope.currentUser.role;
			var userRestrictedPage = $.inArray($location.path(), [ '/post_job','/adminhome' ]) === 0;

			if (userRestrictedPage && role != 'admin') {
				alert("You cannot do this operation as you are not logged in as:"+ role);
				$location.path('/');
			}
		}

	});

	 //keep user logged in after page refresh

	 $rootScope.currentUser = $cookieStore.get('currentUser') ||{};
	 if($rootScope.currentUser){
		 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
		 }
});
