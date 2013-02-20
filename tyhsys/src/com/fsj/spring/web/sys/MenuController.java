package com.fsj.spring.web.sys;

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

import com.fsj.spring.model.sys.SysMenu;
import com.fsj.spring.service.sys.MenuService;
import com.fsj.spring.util.DataGridModel;
import com.fsj.spring.web.TUserAwareImpl;
/**
 * Title:菜单管理控制层
 * 继承TUserAwareImpl，提供会话用户信息
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-24 创建
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends TUserAwareImpl {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		return "sys/sysmenulist";
	}

	@RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, SysMenu menu) throws Exception {
		// spring可以自动装配两个对象 会自动的装返回的Map转换成JSON对象
		return menuService.getPageListBySQL(dgm, menu);
	}

	@RequestMapping(value = "/sysmenu-edit", method = RequestMethod.GET)
	public String popWindow() throws Exception {
		return "sys/sysmenu-edit";
	}

	@RequestMapping(value = "/addOrUpdateMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(SysMenu menu, HttpSession session) throws Exception {
		// spring会利用jackson自动将返回值封装成JSON对象
		Map<String, String> map = new HashMap<String, String>();
		try {
			menuService.setLoginUser(sessionUser);//dao保存操作，自动增加创建人
			menuService.saveOrUpdate(menu);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/deleteMenus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("uid") List<Long> uid) throws Exception {
		// spring 将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			menuService.deleteAllObjects(SysMenu.class, uid);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;
	}

	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
