package com.mvcoder.dao;


import com.mvcoder.bean.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {

	private BaseHibernateDao baseDao;

	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		return baseDao.add(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		baseDao.delete(user);
	}

	@Override
	public void update(User users) {
		// TODO Auto-generated method stub
		baseDao.update(users);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return (User) baseDao.get(User.class, id);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public User get(String username,String password) {
		// TODO Auto-generated method stub
		User user = null;
		Session session = baseDao.getSession();
		Query<User> query = null;
		String hql = null;
		hql = "from User as t where t.username=? and t.password=?";
		query = session.createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);
		user = query.uniqueResult();
		session.close();
		return user;
	}

    public BaseHibernateDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		User user = dao.get("test2","123");
		System.out.println("user : " + user.getUsername());
	}
}
