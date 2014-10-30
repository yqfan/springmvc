package com.yqfan.simplemvc.dao;

import java.util.Collection;

import com.yqfan.simplemvc.model.Gift;

public interface GiftDao {
	public void insert(Gift gift);
	public Gift findById(long id);
	public Collection<Gift> findByTitle(String title);
	public Collection<Gift> getAll();
}
