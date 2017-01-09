'use strict';

app.factory('Forum', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/forum/:forumId', {forumId: '@forumId'},
	{
		updateForum: {method: 'PUT'}
	}
    );
}]);
app.controller('ForumController', ['$scope', 'Forum', function($scope, Forum) {
    var ob = this;
    ob.forums=[];
    ob.forum = new Forum(); 
    ob.fetchAllForums = function(){
        ob.forums = Forum.query();
    };
    ob.fetchAllForums();
    ob.createForum = function(){
        ob.forum.$save(function(){
        	ob.flag= 'created';	
   	        ob.reset();	
            ob.fetchAllForums();
        });
    };
    ob.edit = function(forumId){
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
    ob.deleteForum = function(identity){
        var forum = Forum.get({forumId:identity}, function() {
             forum.$delete(function(){
                 console.log('Deleting forum with forumId ', identity);
                 ob.fetchAllForums();
             });
        });
     };		
    ob.reset = function(){
    	ob.forum = new Forum();
        $scope.forumForm.$setPristine();
    };	
    ob.cancelUpdate = function(c_id){
	    ob.forum = new Forum();
	    ob.flag= '';	
   	    ob.fetchAllForums();
    };    
}]);     