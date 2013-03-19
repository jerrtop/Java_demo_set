package com.fsj.spring.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.model.sys.SysUserRole;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.UserRoleService;
import com.fsj.spring.util.DataGridModel;
/**
 * @author JERR
 * Copyright © 2013 9tang technology
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends TServiceImpl implements UserRoleService {

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
	
	public List getUserRoles(Long userId) {
		String sql = " select sr.*," +
					"		case " +
					"			when sur.id is null then 'false'" +
					"			else 'true'" +
					"		end checked" +
					" from sys_role sr left join (select * from sys_user_role where sur_user_id = ? ) sur on sur.sur_role_id = sr.id order by sr.sr_code";
		List params = new ArrayList();
		params.add(userId);
		List result = baseDao.findBySQL(sql, params);
		return result;
	}
	
	public void saveOrUpdate(Object o) {
		JSONArray items = JSONArray.fromObject(o);
		JSONObject user = items.getJSONObject(0);
		JSONArray roles = items.getJSONArray(1);
		
		//先删除，后插入
		String sql = "delete from sys_user_role where sur_user_id = ?";
		List params = new ArrayList();
		Long userId = user.getLong("userId");
		params.add(userId);
		baseDao.updateBySQL(sql, params);
		
		for(int i = 0 ; i < roles.size() ; i ++){
			JSONObject role = roles.getJSONObject(i);
			SysUserRole sur = new SysUserRole();
			sur.setSurRoleId(role.getLong("roleId"));
			sur.setSurUserId(userId);
			baseDao.saveOrUpdate(sur);
		}
	}
}
