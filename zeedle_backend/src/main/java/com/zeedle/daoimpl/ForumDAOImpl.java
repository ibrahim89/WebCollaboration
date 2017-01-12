package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.ForumDAO;
import com.zeedle.model.Forum;

@Repository
public class ForumDAOImpl implements ForumDAO {

	public ForumDAOImpl() {
	}

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addForum(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);	
	
	}

	public List<Forum> listForum() {
		@SuppressWarnings("unchecked")
		List<Forum> listForum = (List<Forum>)sessionFactory.getCurrentSession().createCriteria(Forum.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listForum;
	}

	public void delete(int forumId) {
		Forum forumToDelete = new Forum();
		forumToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);			
	}

	public Forum get(int forumId) {
		String hql = "from Forum where forumID=" + "'" + forumId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Forum> listForum = (List<Forum>) query.list();

		if (listForum != null && !listForum.isEmpty()) {
			return listForum.get(0);
		}
		return null;
	}

	public void updateForum(Forum forum) {
		Forum f =get(forum.getForumId());
		f.setTitle(forum.getTitle());
		f.setDescription(forum.getDescription());
		sessionFactory.getCurrentSession().update(f);		
	}

	
}
