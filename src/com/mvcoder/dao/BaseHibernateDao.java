package com.mvcoder.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

public class BaseHibernateDao {

	@Qualifier("hibernateSessionFactory")
	@Autowired
	private HibernateSessionFactory hibernateSessionFactory;

	public Session getSession(){
		return getHibernateSessionFactory().getSession();
	}

	public HibernateSessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(HibernateSessionFactory hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	protected boolean add(Object obj) {
		boolean flag = false;
		Transaction tran = null;
		Session session = hibernateSessionFactory.getSession();
		try {
			tran = session.beginTransaction();
			session.save(obj);
			tran.commit();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tran!=null){
				tran.rollback();
			}
			e.printStackTrace();
		}finally {
			hibernateSessionFactory.closeSession();
		}
		return flag;
	}
	
	protected Object get(Class<?> cla,Serializable id) {
		Session session = hibernateSessionFactory.getSession();
		Object object = null;
		try {
			object = session.get(cla,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			hibernateSessionFactory.closeSession();
		}
		return object;
	}
	
	protected void delete(Object obj) {
		Transaction tran = null;
		Session session = hibernateSessionFactory.getSession();
		try {
			tran = session.beginTransaction();
			session.delete(obj);
			tran.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tran!=null){
				tran.rollback();
			}
			e.printStackTrace();
		}finally {
			hibernateSessionFactory.closeSession();
		}
	}

	
	protected void update(Object obj) {
		Transaction tran = null;
		Session session = hibernateSessionFactory.getSession();
		try {
			tran = session.beginTransaction();
			session.update(obj);
			tran.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tran!=null){
				tran.rollback();
			}
			e.printStackTrace();
		}finally {
			hibernateSessionFactory.closeSession();
		}
	}
	
}
