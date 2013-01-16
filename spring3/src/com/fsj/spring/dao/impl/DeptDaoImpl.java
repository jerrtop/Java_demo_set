package com.fsj.spring.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.model.TDept;

@Repository("deptDao")
@SuppressWarnings("rawtypes")
public class DeptDaoImpl extends HibernateDaoSupport implements IDeptDao {
	
	private static final Logger log = LoggerFactory.getLogger(DeptDaoImpl.class);

	protected void initDao() {
	}

	public void save(TDept transientInstance) {
		log.debug("saving TDept instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TDept persistentInstance) {
		log.debug("deleting TDept instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TDept findById(java.lang.Integer id) {
		log.debug("getting TDept instance with id: " + id);
		try {
			TDept instance = (TDept) getHibernateTemplate().get(
					"com.fsj.spring.model.TDept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TDept instance) {
		log.debug("finding TDept instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TDept instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TDept as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all TDept instances");
		try {
			String queryString = "from TDept";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TDept merge(TDept detachedInstance) {
		log.debug("merging TDept instance");
		try {
			TDept result = (TDept) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TDept instance) {
		log.debug("attaching dirty TDept instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TDept instance) {
		log.debug("attaching clean TDept instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IDeptDao getFromApplicationContext(ApplicationContext ctx) {
		return (IDeptDao) ctx.getBean("TDeptDAO");
	}

}