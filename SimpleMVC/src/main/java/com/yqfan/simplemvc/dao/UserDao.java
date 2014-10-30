package com.yqfan.simplemvc.dao;

import com.yqfan.simplemvc.model.User;

public interface UserDao {
	public void insert(User user);
	public User findByName(String userName);
}
