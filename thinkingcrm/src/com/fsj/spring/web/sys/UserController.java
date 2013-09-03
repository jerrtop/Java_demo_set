package com.fsj.spring.web.sys;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.sys.UserService;
import com.fsj.spring.util.Constants;
import com.fsj.spring.util.ConversionUtils;
import com.fsj.spring.util.DataTableParam;
import com.fsj.spring.web.TUserAwareImpl;
/**
 * <p>
 * 		用户管理控制层
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 * wwww.9tang.info
 */
@Controller
@RequestMapping("/user")
public class UserController extends TUserAwareImpl {

	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) throws Exception {
		return "sys/sysuserlist";
	}

	@RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(@RequestBody DataTableParam[] dp) throws Exception {
		//设置会话用户信息 ， 用于数据过滤
		userService.setLoginUser(sessionUser);
		
		return userService.getPageListBySQL(ConversionUtils.convertToMap(dp));
	}

	@RequestMapping(value = "/sysuser-edit", method = RequestMethod.GET)
	public String popWindow() throws Exception {
		return "sys/sysuser-edit";
	}

	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(SysUser user, HttpSession session) throws Exception {
		// spring会利用jackson自动将返回值封装成JSON对象
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.setLoginUser(sessionUser);
			userService.saveOrUpdate(user);
			map.put("mes", "1");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "0");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/deleteUsers", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("uid") List<Long> uid) throws Exception {
		// spring 将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.deleteAllObjects(SysUser.class, uid);
			map.put("mes", "1");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "0");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/checkUnique", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> checkUnique(@RequestParam("checkProperty") String checkProperty,@RequestParam("checkValue") String toBeCheckVal) throws Exception {
		Map map = new HashMap();
		try {
			int result = userService.checkUnique(checkProperty,toBeCheckVal);// result: 0 不存在	1 存在
			map.put("mes", result);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败，请联系管理员。");
			throw e;
		}
		return map;
	}
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		if (session != null && session.getAttribute(Constants.USER_INFO_SESSION) != null) {
			session.removeAttribute(Constants.USER_INFO_SESSION);
		}
		return "relogin";
	}
	
	//查询部门树
	@RequestMapping(value = "/queryOrgs", method = RequestMethod.POST)
	public void queryOrgs(HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			userService.setLoginUser(sessionUser);
			String orgsJsonString = userService.getUserOrgs();
			out.print(orgsJsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	//查询部门树
		@RequestMapping(value = "/querySups", method = RequestMethod.GET)
		public void querySups(HttpServletRequest request,HttpServletResponse response){
			PrintWriter out = null;
			try {
				out = response.getWriter();
				userService.setLoginUser(sessionUser);
				Long userId = null;
				if(request.getParameter("userId") != null){
					userId = Long.parseLong(request.getParameter("userId"));
				}
				String supsJsonString = userService.getSups(userId);
				out.print(supsJsonString);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush();
					out.close();
				}
			}
		}
		
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
