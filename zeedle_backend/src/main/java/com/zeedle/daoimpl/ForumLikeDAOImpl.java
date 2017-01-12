package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.ForumLikeDAO;
import com.zeedle.model.ForumLike;

@Repository
public class ForumLikeDAOImpl implements ForumLikeDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public ForumLikeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addForumLike(ForumLike forumLike) {
		sessionFactory.getCurrentSession().save(forumLike);
		
	}

	public void delete(int forumLikeId) {
		ForumLike forumLikeToDelete = new ForumLike();
		forumLikeToDelete.setForumLikeId(forumLikeId);
		sessionFactory.getCurrentSession().delete(forumLikeToDelete);
		
		
	}

	public ForumLike get(int forumLikeId) {
		String hql = "from ForumLike where forumLikeID=" + "'" + forumLikeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<ForumLike> listForumLike = (List<ForumLike>) query.list();

		if (listForumLike != null && !listForumLike.isEmpty()) {
			return listForumLike.get(0);
		}
		return null;
	}

	public List<ForumLike> listByForumId(int forumId) {
		String hql="from ForumLike where forumId= " + "'" + forumId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ForumLike> list = (List<ForumLike>)query.list();
		return list;
	}
	
	
	
	
	
}
