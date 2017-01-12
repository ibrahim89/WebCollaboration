'use strict';

app.factory('Forum', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/forum/:forumId', {forumId: '@forumId'},
	{
		updateForum: {method: 'PUT'}
	}
    );
}]);
app.controller('ForumController', ['$scope', 'Forum','$location', function($scope, Forum, $location) {
    var ob = this;
    ob.forums=[];
    ob.forum = new Forum(); 
    ob.fetchAllForums = function(){
        ob.forums = Forum.query();
    };
    ob.fetchAllForums();
    ob.addForum = function(){
	console.log('Inside save');
	if($scope.forumForm.$valid) {
	  ob.forum.$save(function(forum){
	     console.log(forum); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForums();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editForum = function(forumId){
	    console.log('Inside edit');
            ob.forum = Forum.get({ forumId: forumId}, function() {
	       ob.flag = 'edit'; 
	    });
    };
    ob.updateForumDetail = function(){
    	console.log('Inside update');
    	if($scope.forumForm.$valid) {
        	   ob.forum.$updateForum(function(forum){
        		console.log(forum); 
    		ob.updatedId = forum.forumId;
    		ob.reset();
    		ob.flag = 'updated'; 
        		ob.fetchAllForums();
               });
    	}
        };
    ob.viewForum = function(forumId){
    	console.log('Inside view');
    	ob.viewforum = Forum.get({ forumId: forumId}, function(){
    		ob.flag = 'view';
    	});
    };
    ob.deleteForum = function(forumId){
	    console.log('Inside delete');
	    ob.forum = Forum.delete({ forumId: forumId}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllForums(); 
	    });
    };		
    ob.reset = function(){
    	ob.forum = new Forum();
        $scope.forumForm.$setPristine();
    };	
    ob.cancelUpdate = function(forumId){
	    ob.forum = new Forum();
	    ob.flag= '';	
   	    ob.fetchAllForums();
    }; 
    ob.close = function(){
    	location.reload();
    };
}]);     