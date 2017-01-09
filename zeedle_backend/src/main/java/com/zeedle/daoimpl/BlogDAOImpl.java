package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.zeedle.model.Blog;
import com.zeedle.dao.BlogDAO;

@Repository

public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	
	public boolean save(Blog blog){	
		
		try{
		  sessionFactory.getCurrentSession().save(blog);
	return true;
		}catch (Exception e ){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
	
	public boolean update(Blog blog){
		
		try{
			sessionFactory.getCurrentSession().update(blog);
	return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}
	
	public boolean delete(Blog blog){
		try{
	       sessionFactory.getCurrentSession().delete(blog);
	return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}//To list the forumitems.
	
	@SuppressWarnings("unchecked")
	
	public List<Blog> list(){
		
		String hql = "from Blog";
	@SuppressWarnings("rawtypes")
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	
	List<Blog> listBlog = query.list();
	if(listBlog == null  || listBlog.isEmpty())
	{
		 return null;
		 
	}
	return query.list();
	}

	
	public Blog get(int blogId) {

		String hql="from Blog where blogId = " + "'" + blogId + "'";

		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Blog> list=query.list();
		if(list==null || list.isEmpty())
		{
			
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

	
	public Blog delete(int blogId) 
	{
		Blog BlogToDelete = new Blog();
		BlogToDelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(BlogToDelete);
		return null;
	}

	
}
