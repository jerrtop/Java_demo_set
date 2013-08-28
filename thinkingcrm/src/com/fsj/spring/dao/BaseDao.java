package com.fsj.spring.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface BaseDao {
	public void saveOrUpdate(Object transientInstance);

	public void delete(Object persistentInstance);

	public void deleteAll(Collection entities);

	public Object findById(Class clazz, Serializable id);

	public List findByExample(Object instance);

	public List findByProperty(Class clazz, String propertyName, Object value);

	/**
	 * 查询记录数
	 * @param sql 查询记录数的sql
	 * @param pl 查询值集合
	 */
	public Integer findObjectsCount(String sql,List pl );
	
	public List findAll(Class clazz);

	public List findByHQL(String hql, List pl);

	public List findBySQL(String sql, List pl);

	public int updateBySQL(String sql, List pl);

	public Map<String, Object> getPageListBySQL(Map<String , Object> params, String countQuery, String resultQuery,Map<String , Object> queryParams) throws Exception;
	

}