package com.fsj.spring.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.UserService;
import com.fsj.spring.util.DataGridModel;
/**
 * Title:用户管理服务层实现类
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
@Service("userService")
public class UserServiceImpl extends TServiceImpl implements UserService {
	/**
	 * 使用HQL分页查询的例子
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, SysUser user) throws Exception {
		String totalQuery = "select count(*) from SysUser user";
		String fullQuery = "select new map(user as user,user.id as uid) from SysUser user";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (user != null) {
			if (StringUtils.isNotBlank(user.getSuUsername())) {
				sb.append(" and user.suUsername like :userName");
				params.put("userName", "%" + user.getSuUsername() + "%");
			}
			if (StringUtils.isNotBlank(user.getSuNameCn())) {
				sb.append(" and user.suNameCn like :suNameCn");
				params.put("suNameCn", "%" + user.getSuNameCn() + "%");
			}
		}

		if (sb.toString().startsWith(" and")) {
			sb.delete(0, 4);
			sb.insert(0, " where ");
		}
		totalQuery += sb.toString();
		fullQuery += sb.toString();
		return baseDao.getPageList(dgm, totalQuery, fullQuery, params);
	}
	/**
	 * 使用SQL分页查询的例子
	 */
	public Map<String, Object> getPageListBySQL(DataGridModel dgm, SysUser user) throws Exception {
		String totalQuery = "select count(*) from sys_user user";
		String fullQuery = "select user.*,user.id as uid from sys_user user";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (user != null) {
			if (StringUtils.isNotBlank(user.getSuUsername())) {
				sb.append(" and user.su_username like :suUsername");
				params.put("suUsername", "%" + user.getSuUsername() + "%");
			}
			if (StringUtils.isNotBlank(user.getSuNameCn())) {
				sb.append(" and user.su_name_cn like :suNameCn");
				params.put("suNameCn", "%" + user.getSuNameCn() + "%");
			}
		}
		
		if (sb.toString().startsWith(" and")) {
			sb.delete(0, 4);
			sb.insert(0, " where ");
		}
		totalQuery += sb.toString();
		fullQuery += sb.toString();
		return baseDao.getPageListBySQL(dgm, totalQuery, fullQuery, params);
	}

	public SysUser getUserByName(String name) throws Exception {
		List<SysUser> list = baseDao.findByProperty(SysUser.class, "suUsername", name);
		return list == null || list.size() == 0 ? null : (SysUser) list.get(0);
	}

}
