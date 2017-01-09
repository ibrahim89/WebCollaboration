'use strict';
var app = angular.module('app', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		controller : 'HomeController',
		templateUrl : 'c_home/home.html'

	}).when('/aboutus', {
		controller : 'UserController',
		templateUrl : 'c_about/aboutus.html'

	})

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

	.when('/blog', {
		controller : 'BlogController',
		templateUrl : 'c_blog/blog.html'

	})

	.when('/blogList', {
		controller : 'BlogController',
		templateUrl : 'c_blog/blogList.html'
		
		
	}) .when('/blogview', {
        controller: 'BlogController',
        templateUrl: 'c_blog/viewblog.html'
    })
    	.when('/chat', {
		controller : 'ChatController',
		templateUrl : 'c_chat/chat.html'

		
	}).when('/chatforum', {
		controller : 'ChatForumController',
		templateUrl : 'c_forum/chatforum.html'
		
		
	}).when('/event', {
		controller : 'EventController',
		templateUrl : 'c_event/event.html'

	})

	.when('/friend', {
		controller : 'FriendController',
		templateUrl : 'c_friend/viewfriend.html'
		
	})

	.when('/friendrequest', {
		controller : 'FriendController',
		templateUrl : 'c_friend/friend.html'
	}).when('/logout', {
		controller : 'UserController'
	})

	.otherwise({
		redirectTo : '/'
	});

})
app.run(function($rootScope, $location, $cookieStore, $http) {

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		// redirect to login page if not logged in and trying to access a restricted page
		var restrictedPage = $.inArray($location.path(), [ '/login','/blogList','/aboutus' ]) === -1;
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
