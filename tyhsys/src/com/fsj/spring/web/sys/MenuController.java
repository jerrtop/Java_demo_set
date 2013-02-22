package com.fsj.spring.web.sys;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.fsj.spring.model.sys.SysMenu;
import com.fsj.spring.model.sys.SysMenuOper;
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
	//1.Spring会自动绑定form提交的值到menu对象
	//2.多对象保存例子,增加request参数
	public Map<String, String> addOrUpdate(SysMenu menu,WebRequest request) throws Exception {
		// spring会利用jackson自动将返回值封装成JSON对象
		Map<String, String> map = new HashMap<String, String>();
		try {
			menuService.setLoginUser(sessionUser);//dao保存操作，自动增加创建人
			
			//updateMenu
			if(menu.getId() != null)
				menu = (SysMenu) menuService.getObjectById(menu.getClass(), menu.getId());
			
			//1.先删除子对象
			Set<SysMenuOper> sysMenuOpers = menu.getSysMenuOpers();
			String[] smoNames = request.getParameterValues("smOpers.smoName");
			if(smoNames.length > 0 && sysMenuOpers.size() > 0){
				//TODO
			}
			
			//2.插入子对象
			String[] smoOperations = request.getParameterValues("smOpers.smoOperation");
			String[] smoValids = request.getParameterValues("smOpers.smoValid");
			
			Set<SysMenuOper> sysMenuOperSet = new HashSet<SysMenuOper>();
			for (int i = 0; i < smoNames.length; i++) {
				SysMenuOper sysMenuOper = new SysMenuOper();
				if(smoNames[i] != null)
					sysMenuOper.setSmoName(smoNames[i]);
				if(smoOperations[i] != null)
					sysMenuOper.setSmoOperation(smoOperations[i]);
				if(smoValids[i] != null)
					sysMenuOper.setSmoValid(smoValids[i]);
				
				sysMenuOper.setSysMenu(menu);
				sysMenuOperSet.add(sysMenuOper);
			}
			if(sysMenuOperSet.size() > 0)
				menu.setSysMenuOpers(sysMenuOperSet);
			
			menuService.saveOrUpdate(menu);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/checkUnique", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> checkUnique(@RequestParam("checkProperty") String checkProperty,@RequestParam("checkValue") String toBeCheckVal) throws Exception {
		Map map = new HashMap();
		try {
			menuService.setLoginUser(sessionUser);//dao保存操作，自动增加创建人
			int result = menuService.checkUnique(checkProperty,toBeCheckVal);// result: 0 不存在	1 存在
			map.put("mes", result);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败，请联系管理员。");
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
