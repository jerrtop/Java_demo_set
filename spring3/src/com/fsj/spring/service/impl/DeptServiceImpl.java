package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.BaseDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.service.IDeptService;

@Service("deptService")
public class DeptServiceImpl implements IDeptService{

	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void addDept(TDept dept) throws Exception {
		baseDao.saveOrUpdate(dept);
	}

	public void deleteDept(int id) throws Exception {
		baseDao.delete(getDeptById(id));
	}

	public TDept getDeptById(int id) throws Exception {
		return (TDept) baseDao.findById(TDept.class,id);
	}

	@SuppressWarnings("unchecked")
	public List<TDept> getDeptList() throws Exception {
		return baseDao.findAll(TDept.class);
	}

	public void updateDept(TDept dept) throws Exception {
		baseDao.saveOrUpdate(dept);
	}

	public Map<String, Object> getAllJson() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<TDept> list = getDeptList();
		result.put("total", list==null ? 0 : list.size());
		result.put("rows", list);
		return result;
	}

}
