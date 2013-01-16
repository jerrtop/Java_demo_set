package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IUserService {
	TUser getUserById(int id) throws Exception;
	TUser getUserByName(String name) throws Exception;
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TUser user) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TUser user) throws Exception;
	void addOrUpdate(TUser user) throws Exception;
	void deleteUsers(List<Integer> usersId) throws Exception;
}
