package com.fsj.spring.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	protected void initDao() {
	}
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageListByExemple(DataGridModel dgm,TUser user) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List totalList = getHibernateTemplate().findByExample(user); 
		//这里使用了findByExample，如果没有外键关联（我的hibernate配置文件没有配置主外键对应关系），用这个可以简单很多，
		List pagelist = getHibernateTemplate().findByExample(user, (dgm.getPage() - 1) * dgm.getRows(), dgm.getRows());
		
		result.put("total", totalList == null ? 0 : totalList.size());
		result.put("rows", pagelist);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageList(DataGridModel dgm,TUser user) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>(2); 
		String countQuery = "select count(*) from TUser user,TDept dept where user.deptId=dept.id "; 
		String fullQuery = "select new map(user as user,user.id as uid,dept.name as deptName) from TUser user,TDept dept where user.deptId=dept.id "; 
		//这里需要用new map()，jquery easyui datagrid有一个小bug，必须把idField单独列出来，要不然不能多选
		String orderString = "";
		if(StringUtils.isNotBlank(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder(); //排序
		//增加条件
		StringBuffer sb = new StringBuffer();
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(user != null) {
			if(StringUtils.isNotBlank(user.getName())) {
				sb.append(" and user.name like :userName");
				params.put("userName", "%"+user.getName()+"%");
			}
			if(user.getAge() != null) {
				sb.append(" and user.age = :age");
				params.put("age", user.getAge());
			}
			if(user.getBirthday() != null) {
				sb.append(" and user.birthday = :birthday");
				params.put("birthday", user.getBirthday());
			}
			if(user.getDeptId() != null) {
				sb.append(" and dept.id = :deptId");
				params.put("deptId", user.getDeptId());
			}
		}
		
		//查询总数可以用getHibernateTemplate()，我下面用的是createQuery
		//Hibernate的动态条件查询用DetachedCriteria是一个比较好的解决
//			List totalList = getHibernateTemplate().findByNamedParam(countQuery, params.keySet().toArray(new String[0]), params.values().toArray());
//			int total = ((Long)totalList.iterator().next()).intValue();
		
		Query queryTotal = getSession().createQuery(countQuery + sb.toString());
		Query queryList = getSession().createQuery(fullQuery + sb.toString() + orderString)
							.setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
		if(params!=null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){					
				String key = it.next();	
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}	
		}			
		int total = ((Long)queryTotal.uniqueResult()).intValue(); //这里必须先转成Long再取intValue，不能转成Integer
		
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
			
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findById(java.lang.Integer)
	 */
	public TUser findById(java.lang.Integer id)  throws Exception {
		log.debug("getting TUser instance with id: " + id);
		try {
			TUser instance = (TUser) getHibernateTemplate().get(
					"com.fsj.spring.model.TUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<TUser> findByProperty(String propertyName, Object value) throws Exception {
		log.debug("finding TUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByName(java.lang.Object)
	 */
	public List<TUser> findByName(Object name) throws Exception {
		return findByProperty(NAME, name);
	}

	public static IUserDao getFromApplicationContext(ApplicationContext ctx) throws Exception {
		return (IUserDao) ctx.getBean("TUserDAO");
	}

	public void addOrUpdate(TUser user) throws Exception {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public void deleteUsers(List<Integer> usersId) throws Exception {
		if(usersId != null && usersId.size() > 0) {
			for (Integer id : usersId) {
				getHibernateTemplate().delete(findById(id));
			}
		}
	}
	
	public void executeHql(final String hql) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createQuery(hql).executeUpdate();
				return null;

			}
		});

	}
}