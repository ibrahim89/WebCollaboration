app.service("ChatForumService", function($q,$timeout){

	var service = {}, listener = $q.defer(),socket = {
		client: null,
		stomp: null
	}, messageIds = [];
	
	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/zeedle_backend/chat_forum";
	service.CHAT_TOPIC = "/topic/message";
	service.CHAT_BROKER = "/app/chat_forum";
	
	service.receive = function(){
		console.log("receive")
	return listener.promise;
	};
	
	service.send = function(message) {
		console.log("send")
	var id = Math.floor(Math.random() * 1000000);
	socket.stomp.send(service.CHAT_BROKER, {
		priority: 9
	}, JSON.stringify({
		message: message,
		id: id
	}));
	messageIds.push(id);
	};
	
	var reconnect = function(){
		console.log("reconnect")
		$timeout(function(){
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};
	 var getMessage = function(data) {
	      var message = JSON.parse(data), out = {};
	      out.message = message.message;
	      /*out.username=message.username;*/
	      out.time = new Date(message.time);
	     /* if (_.contains(messageIds, message.id)) {
	        out.self = true;
	        messageIds = _.remove(messageIds, message.id);
	      }*/
	      return out;
	};

	var startListener = function(){
		console.log("receive")
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data){
			listener.notify(getMessage(data.body));
		});
	};
	
	var initialize = function(){
		console.log("initialize")
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;	
	};
	
	initialize();
	return service;
});