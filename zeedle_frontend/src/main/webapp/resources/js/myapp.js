'use strict';
var app = angular.module('app', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		controller : 'HomeController',
		templateUrl : 'resources/home.jsp',

	})
	.when('/aboutus', {
		controller : 'UserController',
		templateUrl : 'resources/aboutus.jsp',

	})

	.when('/login', {
		controller : 'UserController',
		templateUrl : 'resources/login.jsp',

	}).when('/userlist', {
		controller : 'UserController',
		templateUrl : 'resources/userlist.jsp'
	})

	.when('/userdetail', {
		controller : 'UserController',
		templateUrl : 'resources/userdetail.jsp',

	})

	.when('/myProfile', {
		controller : 'UserController',
		templateUrl : 'resources/profile.jsp'
	})

	.when('/blog', {
		controller : 'BlogController',
		templateUrl : 'resources/blog2.jsp',

	})

	.when('/blogList', {
		controller : 'BlogController',
		templateUrl : 'resources/blogList.jsp'
	}).when('/viewblog', {
		controller : 'BlogController',
		templateUrl : 'resources/viewblog.jsp',

	}).when('/chat', {
		controller : 'ChatController',
		templateUrl : 'resources/chat.jsp',

	}).when('/chatforum', {
		controller : 'ChatForumController',
		templateUrl : 'resources/chatforum.jsp'
	}).when('/event', {
		controller : 'EventController',
		templateUrl : 'resources/event.jsp',

	})

	.when('/friend', {
		controller : 'FriendController',
		templateUrl : 'resources/viewfriend.jsp'
	})

	.when('/friendrequest', {
		controller : 'FriendController',
		templateUrl : 'resources/friend.jsp'
	})

	.otherwise({
		redirectTo : '/'
	});

})
app.run(function($rootScope, $location, $cookieStore, $http) {

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		// redirect to login page if not logged in and trying to access a restricted page
		var restrictedPage = $.inArray($location.path(), [ '/login',
				'/bloglist' ]) === -1;
		console.log("restrictedPage:" + restrictedPage)
		var loggedIn = $rootScope.currentUser.id;
		console.log("loggedIn:" + loggedIn)
		if (!loggedIn) {
			if (restrictedPage) {
				console.log("Navigating to login page")
				$location.path('/login');
			}
		} else {
			var role = $rootScope.currentUser.role;
			var userRestrictedPage = $.inArray($location.path(),
					[ '/userlist' ]) == 0;

			if (userRestrictedPage && role != 'admin') {
				alert("You can not do this operation as you are logged as :"
						+ role);
				$location.path('/');
			}
		}

	});

	// keep user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic '
				+ $rootScope.currentUser;
	}
});
