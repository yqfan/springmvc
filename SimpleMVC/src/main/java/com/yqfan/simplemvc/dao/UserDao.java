package com.yqfan.simplemvc.dao;

import java.util.Collection;

import com.yqfan.simplemvc.model.MyUser;

public interface UserDao {
	public void insert(MyUser user);
	public MyUser findByName(String userName);
	public void updateItem(MyUser user);
	public Collection<MyUser> getAll();
}
