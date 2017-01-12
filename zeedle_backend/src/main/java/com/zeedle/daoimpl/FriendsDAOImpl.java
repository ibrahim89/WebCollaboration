package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.FriendsDAO;
import com.zeedle.model.Friends;

@Repository
public class FriendsDAOImpl implements FriendsDAO {
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(FriendsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public FriendsDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	public List<Friends> getMyFriend(int userID) {
		String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "N'";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friends> list = (List<Friends>) query.list();
		if(list!=null||!list.isEmpty())
		return list;
		else
		return null;
	}
	public Friends get(int userID, int friendID) {
		String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + friendID + "N'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Friends> list = (List<Friends>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	public Friends get(int id) {
		String hql="from Friends where id = " + "'" + id + "'";
		
		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Friends> list=query.list();
		if(list==null || list.isEmpty())
		{
			
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	public boolean save(Friends friends) {
		try{
			  sessionFactory.getCurrentSession().save(friends);
		return true;
			}catch (Exception e ){
				
				e.printStackTrace();
				return false;
			}
	}
	public boolean update(Friends friends) {
		try{
			sessionFactory.getCurrentSession().update(friends);
	return true;
		} catch (Exception e){
			
	       e.printStackTrace();
	       return false;
		}
	}
	public boolean delete(int userID, int friendID) {
		Friends friend = new Friends();
		friend.setFriendID(friendID);
		friend.setUserID(userID);
		sessionFactory.getCurrentSession().delete(friend);
		return true;
	
	}
	public List<Friends> getNewFriendRequests(int userID) {
		String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "N'";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friends> list = (List<Friends>) query.list();
		return list;
	}
	public void setOnline(int loggedInUserID) {
		Logger.debug("Starting of the method setOnline");
		String hql = "UPDATE Friend SET isOnline = 'Y' where userID ='" + loggedInUserID + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Logger.debug("Ending of the method setOnline");
		
	}
	public void setOffLine(int loggedInUserID) {
		Logger.debug("Starting of the method setOffline");
		String hql = "UPDATE Friend SET isOnline = 'N' where userID = '" + loggedInUserID + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Logger.debug("Ending of the method setOffline");
		
	}
	
}
