package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.ForumCommentDAO;
import com.zeedle.model.ForumComment;

@Repository
public class ForumCommentDAOImpl implements ForumCommentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ForumCommentDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	public void addForumComment(ForumComment forumComment) {
		sessionFactory.getCurrentSession().save(forumComment);
		
	}

	public List<ForumComment> listByForumId(int forumId) {
		String hql="from ForumComment where forumId= " + "'" + forumId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ForumComment> list = (List<ForumComment>)query.list();
		return list;
	}

	public void delete(int forumCommentId) {
		ForumComment forumCommentToDelete = new ForumComment();
		forumCommentToDelete.setForumCommentId(forumCommentId);
		sessionFactory.getCurrentSession().delete(forumCommentToDelete);
				
	}

	public ForumComment get(int forumCommentId) {
		String hql = "from ForumComment where forumCommentID=" + "'" + forumCommentId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<ForumComment> listForumComment = (List<ForumComment>) query.list();

		if (listForumComment != null && !listForumComment.isEmpty()) {
			return listForumComment.get(0);
		}
		return null;
	}

	public void updateForumComment(ForumComment forumComment) {
		ForumComment f =get(forumComment.getForumCommentId());
		f.setForumCommentContent(forumComment.getForumCommentContent());
		sessionFactory.getCurrentSession().update(f);		
	}
	
	
	
	
	
    
     	
}
