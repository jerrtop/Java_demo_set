package com.fsj.spring.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.sys.UserService;
import com.fsj.spring.util.Constants;
import com.fsj.spring.util.MD5Util;
/**
 * <p>
 * 		登录
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
@Controller
@RequestMapping("/welcome")
//将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session中
@SessionAttributes({Constants.USER_INFO_SESSION,Constants.USER_ROLE_MENUS})
public class WelcomeController {
	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(SysUser user,Model model) throws Exception {
		SysUser user1 = userService.getUserByName(user.getSuUsername());
		if(user1 == null) {
			model.addAttribute("message", "账号不存在!");
			return "relogin";
		}else if(user.getSuPassword() == null || !MD5Util.getMD5String(user.getSuPassword()).equals(user1.getSuPassword()) ){//增加MD5加密
			model.addAttribute("message", "密码错误!");
			return "relogin";
		}else {
			model.addAttribute(Constants.USER_INFO_SESSION, user1); //名为Constants.USER_INFO_SESSION的属性放到Session属性列表中
			String roleMenus = userService.getUserRoleMenus(user1);
			model.addAttribute(Constants.USER_ROLE_MENUS,roleMenus);
			model.addAttribute("systemTime",System.currentTimeMillis());
			return "main";
		}
	}
	@RequestMapping(method=RequestMethod.GET)
	public String refresh(HttpSession session,Model model) throws Exception{
		if(session != null && session.getAttribute(Constants.USER_INFO_SESSION) != null){
			model.addAttribute("systemTime",System.currentTimeMillis());
			return "main";
		}
		return "relogin";
	}
	
	@RequestMapping(value="checkOldPwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> checkOldPwd(@RequestParam("pwd") String pwd,HttpSession session){
		Map result = new HashMap();
		if(session != null && session.getAttribute(Constants.USER_INFO_SESSION) != null){
			SysUser sessionUser = (SysUser) session.getAttribute(Constants.USER_INFO_SESSION);
			pwd = MD5Util.getMD5String(pwd);
			if(!sessionUser.getSuPassword().equals(pwd)){
				result.put("mes",0);
			}else{
				result.put("mes",1);
			}
		}
		return result;
	}
	
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> updatePwd(@RequestParam("pwd") String pwd,HttpSession session){
		Map result = new HashMap();
		if(session != null && session.getAttribute(Constants.USER_INFO_SESSION) != null){
			SysUser sessionUser = (SysUser) session.getAttribute(Constants.USER_INFO_SESSION);
			pwd = MD5Util.getMD5String(pwd);
			userService.updatePWD(sessionUser.getId(), pwd);
			
			sessionUser.setSuPassword(pwd);
			session.setAttribute(Constants.USER_INFO_SESSION, sessionUser);
			
			result.put("mes",1);
		}
		return result;
	}
}
