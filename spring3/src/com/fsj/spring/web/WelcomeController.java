package com.fsj.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.Constants;

/*
 * 不需要实现任何接口，也不需要继承任何的类，也不需要任何 Servlet API
 */
@Controller
@RequestMapping("/welcome")
//将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class WelcomeController {
	
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String checkUser(SysUser user,Model model) throws Exception {
		SysUser user1 = userService.getUserByName(user.getSuUsername());
		if(user1 == null) {
			model.addAttribute("message", "账号不存在");
			return "relogin";
		}else if(user.getSuPassword() == null || !user.getSuPassword().equals(user1.getSuPassword()) ){
			model.addAttribute("message", "密码错误");
			return "relogin";
		}else {
			model.addAttribute(Constants.USER_INFO_SESSION, user1); //名为Constants.USER_INFO_SESSION的属性放到Session属性列表中
			return "main";
		}
	}
}
