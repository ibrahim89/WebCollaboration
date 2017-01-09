package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.zeedle.dao.BlogCommentDAO;
import com.zeedle.model.BlogComment;

@Repository
public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	
	public boolean save(BlogComment blogComment) {
		
			sessionFactory.getCurrentSession().save(blogComment);
			return false;
		
		
	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> list() {
		
		String hql= "from BlogComment";

		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		List<BlogComment> listBlogComment = query.list();
		if(listBlogComment == null || listBlogComment.isEmpty())
		{
			return null;	
		}
		return query.list();
		
	}

	@SuppressWarnings({ "unchecked" })
	public List<BlogComment> get(int blogID) {
		
		String hql = "from BlogComment where blogID=" +"'" +blogID +"'";
		
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		List<BlogComment> list=query.list();
		if(list==null || list.isEmpty())
		{
			
			return null;
		}
		else
		{
			return list;
		}
	}

}
