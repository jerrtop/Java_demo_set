package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IUserDao {
	public static final String NAME = "name";
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TUser user) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TUser user) throws Exception;
	void addOrUpdate(TUser user) throws Exception;
	void deleteUsers(List<Integer> usersId) throws Exception;
	public abstract TUser findById(java.lang.Integer id) throws Exception;
	public abstract List<TUser> findByName(Object name) throws Exception;
}