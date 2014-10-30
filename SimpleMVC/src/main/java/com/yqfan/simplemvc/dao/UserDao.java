package com.yqfan.simplemvc.dao;

import come.yqfan.simplemvc.model.User;

public interface UserDao {
	public void insert(User user);
	public User findByName(String userName);
}
