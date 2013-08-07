package com.fsj.spring.web.sys;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.sys.SysOrg;
import com.fsj.spring.service.sys.OrgService;
import com.fsj.spring.web.TUserAwareImpl;
/**
 * 组织结构管理 控制层
 * @author JERR
 * Copyright © 2013 9tang technology
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
@Controller
@RequestMapping("/org")
public class OrgController extends TUserAwareImpl {

	private OrgService orgService;

	public OrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		return "sys/sysorg";
	}

	@RequestMapping(value = "/fetchOrgs", method = RequestMethod.GET)
	public void fetchOrgs(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long parentId = null;
		if(request.getParameter("id") != null){
			parentId = Long.parseLong(request.getParameter("id"));
		}
		PrintWriter out = response.getWriter();
		try{
			String result = orgService.fetchOrgs(parentId);
			out.print(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}

	@RequestMapping(value = "/popWindow", method = RequestMethod.GET)
	public String popWindow() throws Exception {
		return "sys/sysorg-edit";
	}

	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(SysOrg org) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			orgService.setLoginUser(sessionUser);
			SysOrg savedOrg = orgService.saveOrUpdateCustom(org);
			map.put("id",savedOrg.getId()+"");
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/deleteOrgs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("uid") Long uid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			orgService.deleteOrgs(uid);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;
	}
	
	@RequestMapping(value = "/checkUnique", method = RequestMethod.GET)
	@ResponseBody
	public Map checkUnique(@RequestParam("checkProperty") String checkProperty,@RequestParam("checkValue") String toBeCheckVal) throws Exception {
		Map map = new HashMap();
		try {
			int result = orgService.checkUnique(checkProperty,toBeCheckVal);// result: 0 不存在	1 存在
			map.put("mes", result);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败，请联系管理员。");
			throw e;
		}
		return map;
	}
}
