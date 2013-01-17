package com.fsj.spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.sys.SysOrg;
import com.fsj.spring.service.IOrgService;
import com.fsj.spring.util.DataGridModel;

@Controller
@RequestMapping("/org")
public class OrgController {

	private IOrgService orgService;

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		// model.addAttribute("deptList", deptService.getDeptList());
		return "org/list";
	}

	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, SysOrg org) throws Exception {
		return orgService.getPageList(dgm, org);
	}

	@RequestMapping(value = "/popWindow", method = RequestMethod.GET)
	public String popWindow() throws Exception {
		return "org/popWindow";
	}

	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(SysOrg org) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			orgService.addOrUpdate(org);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("uid")
	List<Integer> uid) throws Exception {
		// spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			orgService.deleteOrgs(uid);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;// 重定向
	}
}
