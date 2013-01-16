package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.util.DataGridModel;

public interface IUserService {
	SysUser getUserById(int id) throws Exception;
	SysUser getUserByName(String name) throws Exception;
	Map<String, Object> getPageListByExemple(DataGridModel dgm,SysUser user) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,SysUser user) throws Exception;
	void addOrUpdate(SysUser user) throws Exception;
	void deleteUsers(List<Integer> usersId) throws Exception;
}
