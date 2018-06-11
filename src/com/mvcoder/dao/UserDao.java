package com.mvcoder.dao;

import com.mvcoder.bean.User;

public interface UserDao {
	boolean add(User user);
	void delete(User user);
	User get(int id);
	User get(String username, String password);
	void update(User users);
}
