package com.fsj.spring.service.sys;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.TService;
import com.fsj.spring.util.DataGridModel;
/**
 * 
 * @author JERR
 * Copyright © 2013 9tang technology
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
public interface UserRoleService extends TService{
	Map<String, Object> getPageListBySQL(DataGridModel dgm,SysUser user) throws Exception;

	List getUserRoles(Long userId);
}
