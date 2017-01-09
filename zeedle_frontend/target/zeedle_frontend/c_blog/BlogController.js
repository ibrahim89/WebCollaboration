
app.factory('Blog', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/blogs/:blogId', {blogId: '@blogId'},
	{
		updateBlog: {method: 'PUT'}
	}
    );
}]);
app.controller('BlogController', ['$scope', 'Blog', function($scope, Blog) {
    var ob = this;
    ob.blogs=[];
    ob.blog = new Blog(); 
    ob.fetchAllBlogs = function(){
        ob.blogs = Blog.query();
    };
    ob.fetchAllBlogs();
    ob.addBlog = function(){
	console.log('Inside save');
	if($scope.blogForm.$valid) {
	  ob.blog.$save(function(blog){
	     console.log(blog); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllBlogs();
	     /*document.location.reload(true);*/
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editBlog = function(blogId){
	    console.log('Inside edit');
            ob.blog = Blog.get({ blogId: blogId}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    
    ob.getSelectedBlog = getBlog
    function getBlog(blogId){
   	console.log("->getting blog:" +blogId)
   	BlogService.getBlogById(blogId)
   		.then(
   				function(d){
   					ob.blog = d;
   					$location.path('/blogview');
   				},
   				function(errResponse){
   					console.error('Eror while fetching Blogs');
   				}
   		);
   };
    
    ob.updateBlogDetail = function(){
	console.log('Inside update');
	if($scope.blogForm.$valid) {
    	   ob.blog.$updateBlog(function(blog){
    		console.log(blog); 
		ob.updatedId = blog.blogId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllBlogs();
           });
	}
    };	
    ob.deleteBlog = function(blogId){
	    console.log('Inside delete');
	    ob.blog = Blog.delete({ blogId: blogId}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllBlogs(); 
	    });
    };		
    ob.reset = function(){
    	ob.blog = new Blog();
        $scope.blogForm.$setPristine();
        
    };	
    ob.cancelUpdate = function(blogId){
	    ob.blog = new Blog();
	    ob.flag= '';	
   	    ob.fetchAllBlogs();
    };    
}]);      