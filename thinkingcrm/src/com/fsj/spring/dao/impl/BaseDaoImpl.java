package com.fsj.spring.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.BaseDao;
import com.fsj.spring.util.DataGridModel;

@Repository("baseDao")
@SuppressWarnings("rawtypes")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	private static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	protected void initDao() {
	}
	/**
	 * 保存对象
	 */
	public void saveOrUpdate(Object transientInstance) {
		log.debug("saving Object instance");
		try {
			//getHibernateTemplate().saveOrUpdate(transientInstance);
			getHibernateTemplate().merge(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	/**
	 * 删除指定对象
	 */
	public void delete(Object persistentInstance) {
		log.debug("deleting Object instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/**
	 * 查询集合里的所有对象
	 */
	public void deleteAll(Collection entities) {
		log.debug("deleting all Object instance");
		try {
			getHibernateTemplate().deleteAll(entities);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/**
	 * 通过ID查询对象
	 */
	public Object findById(Class clazz, Serializable id) {
		log.debug("getting Object instance with id: " + id);
		try {
			Object instance = (Object) getHibernateTemplate().get(clazz, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 通过Example查询对象结合
	 */
	public List findByExample(Object instance) {
		log.debug("finding instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * 通过属性查询对象集合
	 */
	public List findByProperty(Class clazz, String propertyName, Object value) {
		log.debug("finding " + clazz.getSimpleName() + "instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from " + clazz.getName() + " as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find " + clazz.getSimpleName() + "by property name failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询记录数
	 * @param sql 查询记录数的sql
	 * @param pl 查询值集合
	 */
	public Integer findObjectsCount(String sql,List pl ) {
		log.debug("finding objectsCount by sql : " + sql);
		try {
			SQLQuery queryCount = getSession().createSQLQuery(sql);
			if(pl != null && !pl.isEmpty()){
				for (int i = 0; i < pl.size(); i++) {
					queryCount.setParameter(i, pl.get(i));
				}
			}
			List<BigInteger> countList = queryCount.list();
			int count = countList.get(0).intValue();
			return count;
		} catch (RuntimeException re) {
			log.error("find objectsCount by sql : "+ sql +" , failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询所有对象集合
	 */
	public List findAll(Class clazz) {
		log.debug("finding " + clazz.getSimpleName() + " all instances");
		try {
			String queryString = "from " + clazz.getName();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find " + clazz.getSimpleName() + " all failed", re);
			throw re;
		}
	}
	/**
	 * HQL查询
	 */
	public List findByHQL(String hql, List pl) {
		log.debug("finding instance by hql");
		List result = null;
		try {
			result =  getHibernateTemplate().find(hql, pl.toArray());
		} catch (RuntimeException re) {
			log.error("find instance by hql failed", re);
			throw re;
		}
		return result;
	}

	/**
	 * SQL更新
	 */
	public int updateBySQL(final String sql, final List pl) {
		log.debug("updating instance by sql");
		try {
			HibernateCallback callback = new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					if (pl != null && !pl.isEmpty()) {
						for (int i = 0; i < pl.size(); i++) {
							query.setParameter(i, pl.get(i));
						}
					}
					return query.executeUpdate();
				}
			};

			return (Integer) getHibernateTemplate().execute(callback);
		} catch (RuntimeException re) {
			logger.error("update by sql failed.");
			throw re;
		}
	}
	/**
	 * 执行SQL，返回List<Map<String,Object>>结合
	 */
	public List findBySQL(final String sql, final List pl) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				List resultlist = new ArrayList();

				PreparedStatement preparedStatement = session.connection().prepareStatement(sql);
				if (pl != null && !pl.isEmpty()) {
					for (int i = 0; i < pl.size(); i++) {
						preparedStatement.setObject(i + 1, pl.get(i));
					}
				}
				ResultSet resultSet = preparedStatement.executeQuery();
				ResultSetMetaData metaData = resultSet.getMetaData();
				int cols = metaData.getColumnCount();
				String[] columnName = new String[cols];
				for (int i = 0; i < columnName.length; i++) {
					columnName[i] = metaData.getColumnName(i + 1).toUpperCase();
				}
				while (resultSet.next()) {
					Map map = new HashMap();
					for (int i = 0; i < columnName.length; i++) {
						map.put(columnName[i], resultSet.getString(columnName[i]));
					}
					resultlist.add(map);
				}

				return resultlist;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	/**
	 * 使用example查询分页
	 */
	public Map<String, Object> getPageListByExemple(DataGridModel dgm,Object instance) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		List totalList = getHibernateTemplate().findByExample(instance); 
		//这里使用了findByExample，如果没有外键关联（我的hibernate配置文件没有配置主外键对应关系），用这个可以简单很多，
		List pagelist = getHibernateTemplate().findByExample(instance, (dgm.getPage() - 1) * dgm.getRows(), dgm.getRows());
		
		result.put("total", totalList == null ? 0 : totalList.size());
		result.put("rows", pagelist);
		return result;
	}
	/**
	 * 通过hql查询分页
	 * @param dgm dataGrid对象
	 * @param countQuery 总行数HQL
	 * @param resultQuery 查询结果集SQL
	 * @param params	查询条件
	 * @return	Map<String,Object>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageList(DataGridModel dgm,String countQuery,String resultQuery,Map<String,Object> params) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		String orderString = "";
		if(StringUtils.isNotBlank(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder(); //排序

		Query queryTotal = getSession().createQuery(countQuery);
		Query queryList = getSession().createQuery(resultQuery + orderString)
							.setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
		if(params!=null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){					
				String key = it.next();	
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}	
		}
		//这里必须先转成Long再取intValue，不能转成Integer
		int total = ((Long)queryTotal.uniqueResult()).intValue();
		
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
			
		return result;
	}
	/**
	 * 通过sql查询分页
	 * @param dgm dataGrid对象
	 * @param countQuery 总行数SQL
	 * @param resultQuery 查询结果集SQL
	 * @param params	查询条件
	 * @return	Map<String,Object>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageListBySQL(DataGridModel dgm,String countSQL,String resultSQL,Map<String,Object> params) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		String orderString = "";
		if(StringUtils.isNotBlank(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder(); //排序
		
		Query queryTotal = getSession().createSQLQuery(countSQL);
		Query queryList = getSession().createSQLQuery(resultSQL + orderString).setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());;
		
		if(params!=null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){					
				String key = it.next();	
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}	
		}	
		
		List<BigInteger> totalList = queryTotal.list();
		int total = totalList.get(0).intValue();
		
		//返回一个map,KEY:为DB中名称一致（大小写一致）
		queryList.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
			
		return result;
	}
	// public static IDeptDao getFromApplicationContext(ApplicationContext ctx)
	// {
	// return (IDeptDao) ctx.getBean("TDeptDAO");
	// }

}