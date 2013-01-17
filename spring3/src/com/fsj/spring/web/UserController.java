package com.fsj.spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.Constants;
import com.fsj.spring.util.DataGridModel;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		//model.addAttribute("deptList", deptService.getDeptList());
		return "user/list";
	}

	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm,SysUser user) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
	    return userService.getPageList(dgm, user);
	}
	
	@RequestMapping(value="/popWindow",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "user/popWindow";
	}
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(SysUser user) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.addOrUpdate(user);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map; 
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("uid") List<Integer> uid)throws Exception{
		//spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.deleteUsers(uid);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}
	
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)
	public String loginOut(HttpSession session){
		if(session != null && session.getAttribute(Constants.USER_INFO_SESSION) != null){
			session.removeAttribute(Constants.USER_INFO_SESSION);
		}
		return "relogin";
	}
	
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
