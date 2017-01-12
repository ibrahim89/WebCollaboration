package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.ForumMemberDAO;
import com.zeedle.model.ForumMember;

@Repository
public class ForumMemberDAOImpl implements ForumMemberDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public ForumMemberDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addForumMember(ForumMember forumMember) {
		sessionFactory.getCurrentSession().save(forumMember);
		
	}

	public List<ForumMember> listByForumId(int forumId) {
		String hql="from ForumMember where forumId= " + "'" + forumId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ForumMember> list = (List<ForumMember>)query.list();
		return list;
	}

	public void delete(int forumMemberId) {
		ForumMember forumMemberToDelete = new ForumMember();
		forumMemberToDelete.setForumMemberId(forumMemberId);
		sessionFactory.getCurrentSession().delete(forumMemberToDelete);		
	}

	public ForumMember get(int forumMemberId) {
		String hql = "from ForumMember where forumMemberID=" + "'" + forumMemberId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<ForumMember> listForumMember = (List<ForumMember>) query.list();

		if (listForumMember != null && !listForumMember.isEmpty()) {
			return listForumMember.get(0);
		}
		return null;
	}

	public void updateForumMember(ForumMember forumMember) {
		ForumMember f =get(forumMember.getForumMemberId());
		sessionFactory.getCurrentSession().update(f);			
	}
	
	
}
