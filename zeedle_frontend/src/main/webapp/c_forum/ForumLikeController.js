'use strict';

app.factory('ForumLike', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/forumlike/:forumLikeId', {forumLikeId: '@forumLikeId'},
	{
		updateForumLike: {method: 'PUT'}
	}
    );
}]);
app.controller('ForumLikeController', ['$scope', 'ForumLike','$location', function($scope, ForumLike, $location) {
    var ob = this;
    ob.forumlikes=[];
    ob.forumlike = new ForumLike(); 
    ob.fetchAllForumLikes = function(forumId){
        ob.forumlikes = ForumLike.query({forumId: forumId});
    };
    ob.fetchAllForumLikes();
    ob.addForumLike = function(){
	console.log('Inside save');
	if($scope.forumLikeForm.$valid) {
	  ob.forumlike.$save(function(forumlike){
	     console.log(forumlike); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForumLikes();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editForumLike = function(forumLikeId){
	    console.log('Inside edit');
            ob.forumlike = ForumLike.get({ forumLikeId: forumLikeId}, function() {
	       ob.flag = 'edit'; 
	    });
    };
    ob.updateForumLikeDetail = function(){
	console.log('Inside update');
	if($scope.forumLikeForm.$valid) {
    	   ob.forumlike.$updateForumLike(function(forumlike){
    		console.log(forumlike); 
		ob.updatedId = forumlike.forumLikeId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllForumLikes();
           });
	}
    };	
    ob.viewForumLike = function(forumLikeId){
    	console.log('Inside view');
    	ob.viewforumlike = ForumLike.get({ forumLikeId: forumLikeId}, function(){
    		ob.flag = 'view';
    	});
    };
 
    ob.deleteForumLike = function(forumLikeId){
	    console.log('Inside delete');
	    ob.forumlike = ForumLike.delete({ forumLikeId: forumLikeId}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllForumLikes(); 
	    });
    };	
    ob.reset = function(){
    	ob.forumlike = new ForumLike();
        $scope.forumLikeForm.$setPristine();
    };	
    ob.cancelUpdate = function(forumLikeId){
	    ob.forumlike = new ForumLike();
	    ob.flag= '';	
   	    ob.fetchAllForumLikes();
    };   
}]);     