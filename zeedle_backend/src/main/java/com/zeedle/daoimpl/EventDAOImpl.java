package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.EventDAO;
import com.zeedle.model.Event;
@Repository
public class EventDAOImpl  implements EventDAO{

	public EventDAOImpl() {
		
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;


	public EventDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}


	public List<Event> getAllEvent() {
		@SuppressWarnings("unchecked")
		List<Event> listEvent = (List<Event>) 
		          sessionFactory.getCurrentSession()
				.createCriteria(Event.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return listEvent;
		
	}


	public Event getEventById(int id) {
		//select *from Event where id='101'
		String hql = "from Event where id=" + "'"+ id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Event> listEvent = (List<Event>) query.list();
		
		if (listEvent != null && !listEvent.isEmpty()) {
			return listEvent.get(0);
		}
		return null;

	}


	public boolean addEvent(Event event) {
		sessionFactory.getCurrentSession().save(event);
		return false;
	}


	public void updateEvent(Event event) {
		sessionFactory.getCurrentSession().update(event);
		
	}


	public void deleteEvent(int id) {
		Event EventToDelete = new Event();
		EventToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(EventToDelete);
		
		
	}
	
	
	
	
	

}
