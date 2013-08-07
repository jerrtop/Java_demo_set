package com.fsj.spring.web;

import com.fsj.spring.model.sys.SysUser;

/**
 * Title:封装会话用户信息实现类
 * 
 * @author 唐有欢
 * @version 1.0 , 2013-1-24 创建
 */
public class TUserAwareImpl implements TUserAware {
	protected SysUser sessionUser;

	public void setUser(SysUser sessionUser) {
		this.sessionUser = sessionUser;

	}
}
