package com.fsj.spring.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.web.TUserAware;

public class MyHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//就简单判断了一下，如果要详细控制可以用spring security
		String url = request.getRequestURI();
		if(url.endsWith("welcome"))
			return true;
		if(request.getSession() != null && request.getSession().getAttribute(Constants.USER_INFO_SESSION) != null) {
			//向controller注入会话用户信息
			if(handler instanceof TUserAware){
				SysUser user = (SysUser) request.getSession().getAttribute(Constants.USER_INFO_SESSION);
				TUserAware tUserAware = (TUserAware) handler;
				tUserAware.setUser(user);
			}
			
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");	
		return false;
			
	}
	
}
