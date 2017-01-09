package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.FriendDAO;
import com.zeedle.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO{

	public FriendDAOImpl() {
		
		}
	@Autowired (required=true)
	private SessionFactory sessionFactory;
	
		
	public FriendDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}


	public boolean save(Friend friend){
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean update(Friend friend){
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return false;
		
	}

	public List<Friend> getMyFriends(int id) {

		String hql ="from Friend where id='"+id+"'  and status='"+"A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list= (List<Friend>) query.list();
		return list;
	}

	public Friend get(int id, int friendId) {
	String hql="from Friend where id ='"+id+"' and friendId='"+friendId+"'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	List<Friend> list = (List<Friend>) query.list();
	if(list!=null && !list.isEmpty()){
		return list.get(0);
	}
	return null;
		
	}

	public void delete(int id, int friendId) {
		Friend friend =new Friend();
		friend.setFriendId(friendId);
		friend.setId(id);  //	THIS IS USER ID 
		sessionFactory.getCurrentSession().delete(friend);
		}

	public List<Friend> getNewFriendRequests(int friendId) {
		String hql="from Friend where friendId= " + "'" + friendId + "' and status = '"+ "N'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}

	public void setOnline(int friendId) {
		String hql="UPDATE Friend SET isOnline = 'Y' where friendId='"+friendId+"'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		}

	public void setOffLine(int friendId) {
		String hql="UPDATE Friend SET isOnline='N' where friendId='"+friendId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}


	public List<Friend> getMyFriend(int friendId) {
		String hql="from Friend where friendId= " + "'" + friendId + "' and status ='"+ "A'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>)query.list();
		return list;

	}


	
		
}
