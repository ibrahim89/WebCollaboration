package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.Event;

public interface EventDAO {
		List<Event> getAllEvent();
		Event getEventById(int id);
	    boolean addEvent(Event event);
	    void updateEvent(Event event);
	    void deleteEvent(int id);
		

}
