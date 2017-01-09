package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.UserDAO;
import com.zeedle.model.UserDetail;


@Repository
public class UserDAOImpl implements UserDAO{
	private static final Logger Logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl() {
		}
	@Autowired
	private SessionFactory sessionFactory;
	public UserDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	public List<UserDetail> getAllUser() {
		@SuppressWarnings("unchecked")
		List<UserDetail> listUser = (List<UserDetail>) 
		          sessionFactory.getCurrentSession()
				.createCriteria(UserDetail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return listUser;
		
	}
	public UserDetail getUserById(int id) {
		//select *from UserDetail where id='101'
				String hql = "from UserDetail where id=" + "'"+ id +"'";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				
				@SuppressWarnings("unchecked")
				List<UserDetail> listUser = (List<UserDetail>) query.list();
				
				if (listUser != null && !listUser.isEmpty()) {
					return listUser.get(0);
				}
				return null;
			
	}
	public boolean addUser(UserDetail user) {
		sessionFactory.getCurrentSession().save(user);
		return false;
	}
	public boolean updateUser(UserDetail user) {
		sessionFactory.getCurrentSession().update(user);
		return false;
	}
	public void deleteUser(int id) {
		UserDetail UserToDelete = new UserDetail();
		UserToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(UserToDelete);
		
	}
	public UserDetail authenticate(String name,String password) {
		String hql="from UserDetail where name='"+name+"' and "+"password='"+password+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserDetail> list=(List<UserDetail>)query.list();
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	public UserDetail getByName(String name) {
		// select *from UserDetail where name=''
		String hql ="from UserDetail where name =" + "'"+ name +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserDetail> listUserDetail = (List<UserDetail>) query.list();
		
		if (listUserDetail != null && !listUserDetail.isEmpty()) {
			return listUserDetail.get(0);
		}
		return null;


		
	}
	public void setOnline(int loggedInUserId) {
		Logger.debug("Starting of the method setOffline");
		String hql = "UPDATE User SET isOnline = 'Y' where id = '" + loggedInUserId + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Logger.debug("Ending of the method setOffline");
		}
		
	
	public void setOffLine(int loggedInUserId) {
		Logger.debug("Starting of the method setOnline");
		String hql = "UPDATE UserDetail SET isOnline = 'N' where id ='" + loggedInUserId + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Logger.debug("Ending of the method setOnline");
		
	}
	



}
