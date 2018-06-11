package com.mvcoder.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class HibernateSessionFactory {
	
	//ָ�� Hibernate �����ļ���·��
	//private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	
	//private static String configFile = CONFIG_FILE_LOCATION;
	
	//����SessionFactory����
	@Qualifier("sessionFactory")
	@Autowired
	private SessionFactory sessionFactory;
	
	//����Configuration����
	//private static Configuration configuration = new Configuration();
	
	//�����̱߳��ر���
	private final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();
	
	/*static{


		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( configFile )
			    .build();

			Metadata metadata = new MetadataSources( standardRegistry )
			    //.addAnnotatedClass( User.class )
			   // .addAnnotatedClassName( "com.mvp.bean.User")
			    .getMetadataBuilder()
			    //.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
			    .build();

		 sessionFactory = metadata.getSessionFactoryBuilder()

			    .build();

		*//*configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);*//*
	}*/
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private HibernateSessionFactory(){};
	
	/*public static void rebuildSessionFactory(){
		synchronized (sessionFactory) {
			configuration = configuration.configure(configFile);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}*/
	
	public Session getSession(){
		
		Session session = sessionThreadLocal.get();
		try{
		if(session == null || !session.isOpen()){
			if(sessionFactory == null){
				//rebuildSessionFactory();
				throw new IllegalStateException("session factory is null");
			}
		}
		session = (sessionFactory == null)?null:sessionFactory.openSession();
		sessionThreadLocal.set(session);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return session;
	}
	
	//�ر�session����
	public void closeSession(){
		Session session = sessionThreadLocal.get();
		sessionThreadLocal.set(null);
		
		if(session != null && session.isOpen()){
			session.close();
		}
	}
	
	/*//configFile ���Ե�set����
	public static void setConfigFile(String configFilePath){
		HibernateSessionFactory.configFile = configFilePath;
		sessionFactory = null;
	}

	//configuration ���Ե�get����
	public static Configuration getConfiguration(){
		return configuration;
	}
	*/
}
