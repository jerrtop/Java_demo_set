package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;

public interface IDeptService {
	void addDept(TDept dept) throws Exception;
	void updateDept(TDept dept) throws Exception;
	void deleteDept(int id) throws Exception;
	TDept getDeptById(int id) throws Exception;
	List<TDept> getDeptList() throws Exception;
	Map<String, Object> getAllJson() throws Exception ;
}
