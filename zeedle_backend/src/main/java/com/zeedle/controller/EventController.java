package com.zeedle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zeedle.model.Event;
import com.zeedle.service.EventService;

@RestController
public class EventController {
	@Autowired (required=true)
	private EventService eventService;
	
	// To get Event id
		@RequestMapping(value="/event/{id}", method = RequestMethod.GET )
		public ResponseEntity<Event> getEventById(@PathVariable("id") Integer id) {
			Event event = eventService.getEventById(id);
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		
		// To get all events
		@RequestMapping(value= "/event", method = RequestMethod.GET)
		public ResponseEntity<List<Event>> getAllEvent() {
			List<Event> list = eventService.getAllEvent();
			return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
		}
	//To Add Event 
		@RequestMapping(value= "/event", method = RequestMethod.POST)
		public ResponseEntity<Void> addEvent(@RequestBody Event event, UriComponentsBuilder builder) {
	        boolean flag = eventService.addEvent(event);
	               if (flag == false) {
	        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	               }
	               HttpHeaders headers = new HttpHeaders();
	               headers.setLocation(builder.path("/event/{id}").buildAndExpand(event.getId()).toUri());
	               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		
	
	//To Update update
	@RequestMapping(value="/event/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Event> updateevent(@RequestBody Event event) {
		eventService.updateEvent(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	// To delete event
	@RequestMapping(value="/event/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteEvent(@PathVariable("id") Integer id) {
		eventService.deleteEvent(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	} 

}
