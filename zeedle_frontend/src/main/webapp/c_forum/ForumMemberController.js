'use strict';

app.factory('ForumMember', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/forummember/:forumMemberId', {forumMemberId: '@forumMemberId'},
	{
		updateForumMember: {method: 'PUT'}
	}
    );
}]);
app.controller('ForumMemberController', ['$scope', 'ForumMember','$location', function($scope, ForumMember, $location) {
    var ob = this;
    ob.forummembers=[];
    ob.forummember = new ForumMember(); 
    ob.fetchAllForumMembers = function(forumId){
        ob.forummembers = ForumMember.query({forumId: forumId});
    };
    //ob.fetchAllForumMembers();
    ob.addForumMember = function(){
	console.log('Inside save');
	if($scope.forumMemberForm.$valid) {
	  ob.forummember.$save(function(forummember){
	     console.log(forummember); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForumMembers();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editForumMember = function(forumMemberId){
	    console.log('Inside edit');
            ob.forummember = ForumMember.get({ forumMemberId: forumMemberId}, function() {
	       ob.flag = 'edit'; 
	    });
    };
    ob.updateForumMemberDetail = function(){
    	console.log('Inside update');
    	if($scope.forumMemberForm.$valid) {
        	   ob.forummember.$updateForumMember(function(forummember){
        		console.log(forummember); 
    		ob.updatedId = forummember.forumMemberId;
    		ob.reset();
    		ob.flag = 'updated'; 
        		ob.fetchAllForumMembers();
               });
    	}
        };
    ob.viewForumMember = function(forumMemberId){
    	console.log('Inside view');
    	ob.viewforummember = ForumMember.get({ forumMemberId: forumMemberId}, function(){
    		ob.flag = 'view';
    	});
    };
    ob.deleteForumMember = function(forumMemberId){
	    console.log('Inside delete');
	    ob.forummember = ForumMember.delete({ forumMemberId: forumMemberId}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllForumMembers(); 
	    });
    };		
    ob.reset = function(){
    	ob.forummember = new ForumMember();
        $scope.forumMemberForm.$setPristine();
    };	
    ob.cancelUpdate = function(forumId){
	    ob.forummember = new ForumMember();
	    ob.flag= '';	
   	    ob.fetchAllForumMembers();
    };    
}]);     