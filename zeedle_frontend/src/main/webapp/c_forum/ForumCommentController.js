'use strict';

app.factory('ForumComment', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/forumcomment/:forumCommentId', {forumCommentId: '@forumCommentId'},
	{
		updateForumComment: {method: 'PUT'}
	}
    );
}]);
app.controller('ForumCommentController', ['$scope', 'ForumComment','$location', function($scope, ForumComment, $location) {
    var ob = this;
    ob.forumcomments=[];
    ob.forumcomment = new ForumComment(); 
    ob.fetchAllForumComments = function(forumId){
        ob.forumcomments = ForumComment.query({forumId: forumId});
    };
    //ob.fetchAllForumComments();
    ob.addForumComment = function(){
	console.log('Inside save');
	if($scope.forumCommentForm.$valid) {
	  ob.forumcomment.$save(function(forumcomment){
	     console.log(forumcomment); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForumComments();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editForumComment = function(forumCommentId){
	    console.log('Inside edit');
            ob.forumcomment = ForumComment.get({ forumCommentId: forumCommentId}, function() {
	       ob.flag = 'edit'; 
	    });
    };
    ob.updateForumCommentDetail = function(){
	console.log('Inside update');
	if($scope.forumCommentForm.$valid) {
    	   ob.forumcomment.$updateForumComment(function(forumcomment){
    		console.log(forumcomment); 
		ob.updatedId = forumcomment.forumCommentId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllForumComments();
           });
	}
    };	
    ob.viewForumComment = function(forumCommentId){
    	console.log('Inside view');
    	ob.viewforumcomment = ForumComment.get({ forumCommentId: forumCommentId}, function(){
    		ob.flag = 'view';
    	});
    };
    ob.deleteForumComment = function(forumCommentId){
	    console.log('Inside delete');
	    ob.forumcomment = ForumComment.delete({ forumCommentId: forumCommentId}, function() {
		/*ob.reset(); */ 
		ob.flag = 'deleted';
    		ob.fetchAllForumComments(); 
	    });
    };		
    ob.reset = function(){
    	ob.forumcomment = new ForumComment();
        $scope.forumCommentForm.$setPristine();
    };	
    ob.cancelUpdate = function(forumCommentId){
	    ob.forumcomment = new ForumComment();
	    ob.flag= '';	
   	    ob.fetchAllForumComments();
    };    
}]);     
