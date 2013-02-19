package com.fsj.spring.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fsj.spring.util.DataGridModel;

@SuppressWarnings("rawtypes")
public interface BaseDao {
	public void saveOrUpdate(Object transientInstance);

	public void delete(Object persistentInstance);

	public void deleteAll(Collection entities);

	public Object findById(Class clazz, Serializable id);

	public List findByExample(Object instance);

	public List findByProperty(Class clazz, String propertyName, Object value);

	public List findAll(Class clazz);

	public List findByHQL(String hql, List pl);

	public List findBySQl(String sql, List pl);

	public int updateBySQL(String sql, List pl);

	public Map<String, Object> getPageListByExemple(DataGridModel dgm, Object instance) throws Exception;

	public Map<String, Object> getPageList(DataGridModel dgm, String countQuery, String resultQuery,Map<String , Object> params) throws Exception;
	
	public Map<String, Object> getPageListBySQL(DataGridModel dgm, String countQuery, String resultQuery,Map<String , Object> params) throws Exception;

}