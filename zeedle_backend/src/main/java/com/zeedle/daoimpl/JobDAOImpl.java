package com.zeedle.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeedle.dao.JobDAO;
import com.zeedle.model.Job;
import com.zeedle.model.JobApplication;
@Repository
public class JobDAOImpl implements JobDAO{

	public JobDAOImpl() {
	
	}
	@Autowired (required=true)
	private SessionFactory sessionFactory;
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public boolean save(Job job) {
		 sessionFactory.getCurrentSession().save(job);
		return false;
	}
	public boolean save(JobApplication jobApplication) {
		 sessionFactory.getCurrentSession().save(jobApplication);
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Job> list() {
		String hql = "from Job";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Job> listJob = query.list();
		if(listJob == null  || listJob.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<JobApplication> listJobApplication() {
		String hql = "from JobApplication";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		List<JobApplication> listJobApplication = query.list();
		if(listJobApplication == null  || listJobApplication.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
	}
	public Job getJobDetails(int JobID) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class, JobID);
	}
	public boolean postJob(Job job) {
		sessionFactory.getCurrentSession().save(job);
		return false;
	}
	public boolean update(Job job) {
		sessionFactory.getCurrentSession().update(job);
		return false;
	}
	public List<Job> getAllVacantJobs() {
		String hql = "from Job where status = 'V' ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public List<Job> getAllJobs() {
		String hql = "from Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	public boolean applyForJob(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		}
	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public JobApplication get(int id, int jobID) {
		String hql = "from JobApplication where id = '" + id + "'and jobID = '"+ jobID+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}
	public JobApplication getMyAppliedJobs(int id) {
		String hql = "from Job where jId in (select jId from JobApplication where id = '" + id + "')";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}
	public JobApplication getJobApplication(int jId) {
		String hql="from JobApplication where jId = " + "'" + jId + "'";
		
		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<JobApplication> list=query.list();
		if(list==null || list.isEmpty())
		{
			
			return null;
		}
		else
		{
			return list.get(0);
		}
		
		
		
	}
	
	
	
}
