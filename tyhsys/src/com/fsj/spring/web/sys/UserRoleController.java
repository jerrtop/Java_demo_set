package com.fsj.spring.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.sys.UserRoleService;
import com.fsj.spring.util.DataGridModel;
import com.fsj.spring.web.TUserAwareImpl;
/**
 * @author JERR
 * Copyright © 2013 9tang technology
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController extends TUserAwareImpl {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		return "sys/sysuserrolelist";
	}

	@RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, SysUser user) throws Exception {
		//设置会话用户信息 ， 用于数据过滤
		userRoleService.setLoginUser(sessionUser);
		return userRoleService.getPageListBySQL(dgm, user);
	}

	@RequestMapping(value = "/sysuserrole-edit", method = RequestMethod.GET)
	public String popWindow() throws Exception {
		return "sys/sysuserrole-edit";
	}
	
	@RequestMapping(value = "/getUserRoles", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,List> getUserRoles(@RequestParam("userId") Long userId) throws Exception{
		Map<String,List> map = new HashMap<String,List>();
		map.put("result", userRoleService.getUserRoles(userId));
		return map;
	}
	
	@RequestMapping(value = "/addOrUpdateUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(@RequestParam("data") String data) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			userRoleService.setLoginUser(sessionUser);
			userRoleService.saveOrUpdate(data);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	private UserRoleService userRoleService;

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
}
