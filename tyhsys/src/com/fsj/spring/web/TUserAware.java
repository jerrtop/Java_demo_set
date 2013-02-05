package com.fsj.spring.web;

import com.fsj.spring.model.sys.SysUser;

/**
 * Title:Controller层会话用户信息接口
 * 
 * @author 唐有欢
 * @version 1.0 , 2013-1-21 创建
 */
public interface TUserAware {
	public void setUser(SysUser user);
}
