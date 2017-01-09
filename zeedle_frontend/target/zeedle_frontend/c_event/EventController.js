


app.factory('Event', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/zeedle_backend/event/:id', {id: '@id'},
	{
		updateEvent: {method: 'PUT'}
	}
    );
}]);
app.controller('EventController', ['$scope', 'Event', function($scope, Event) {
    var ob = this;
    ob.events=[];
    ob.event = new Event(); 
    ob.fetchAllEvents = function(){
        ob.events = Event.query();
    };
    ob.fetchAllEvents();
    ob.addEvent = function(){
	console.log('Inside save');
	if($scope.eventForm.$valid) {
	  ob.event.$save(function(event){
	     console.log(event); 
	    ob.flag= 'created';	
	     ob.reset();	
	   
	     ob.fetchAllEvents();
	     document.location.reload(true);
	     
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editEvent = function(id){
	    console.log('Inside edit');
            ob.event = Event.get({ id: id}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateEventDetail = function(){
	console.log('Inside update');
	if($scope.eventForm.$valid) {
    	   ob.event.$updateEvent(function(event){
    		console.log(event); 
		ob.updatedId = event.id;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllEvents();
           });
	}
    };	
    ob.deleteEvent = function(id){
	    console.log('Inside delete');
	    ob.event = Event.delete({ id: id}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllEvents(); 
	    });
    };		
    ob.reset = function(){
    	ob.event = new Event();
        $scope.eventForm.$setPristine();
    };	
    ob.cancelUpdate = function(id){
	    ob.event = new Event();
	    ob.flag= '';	
   	    ob.fetchAllEvents();
    };    
}]);      