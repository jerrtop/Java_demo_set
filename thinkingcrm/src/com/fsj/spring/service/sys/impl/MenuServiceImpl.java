package com.fsj.spring.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fsj.spring.model.sys.SysMenu;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.MenuService;
import com.fsj.spring.util.DataGridModel;
/**
 * Title:用户管理服务层实现类
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
@Service("menuService")
public class MenuServiceImpl extends TServiceImpl implements MenuService {
	/**
	 * 查询菜单列表
	 *@param dgm	grid对象
	 *@param menu	查询的菜单对象
	 *@return
	 *@throws Exception
	 */
	public Map<String, Object> getPageListBySQL(DataGridModel dgm, SysMenu menu) throws Exception {
		String totalQuery = "select count(*) from sys_menu menu";
		//多表查询的例子
		String fullQuery = "select menu.*,menu.id as uid,parent.sm_name as parentMenuName from sys_menu menu left join sys_menu parent on menu.sm_parent = parent.sm_code ";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (menu != null) {
			if (StringUtils.isNotBlank(menu.getSmCode())) {
				sb.append(" and menu.SM_CODE like :smCode");
				params.put("smCode", "%" + menu.getSmCode() + "%");
			}
			if (StringUtils.isNotBlank(menu.getSmName())) {
				sb.append(" and menu.SM_NAME like :smName");
				params.put("smName", "%" + menu.getSmName() + "%");
			}
			if (StringUtils.isNotBlank(menu.getSmParent())) {
				sb.append(" and menu.SM_PARENT like :smParent");
				params.put("smParent", "%" + menu.getSmParent() + "%");
			}
		}

		if (sb.toString().startsWith(" and")) {
			sb.delete(0, 4);
			sb.insert(0, " where ");
		}
		totalQuery += sb.toString();
		fullQuery += sb.toString();
		return baseDao.getPageListBySQL(dgm, totalQuery, fullQuery, params);
	}
	
	/**
	 *检查值唯一性
	 *@param property 被检查的属性
	 *@param toBeCheckVal 被检查的值
	 *@return String 
	 */
	@Override
	public int checkUnique(String property,Object toBeCheckVal) {
		String countSql = "select count(*) from sys_menu menu where " + property + "= ?";
		java.util.List pl = new ArrayList();
		pl.add(toBeCheckVal);
		int count = baseDao.findObjectsCount(countSql,pl);
		if(count >=1){
			count = 1;
		}
		return count;
	}
	/**
	 * 保存菜单时，同时保存操作权限
	 */
	public void saveOrUpdate(Object o){
		SysMenu menu = (SysMenu) o;
		Long menuId = menu.getId();
					
		// 1. 操作权限采取先删后插入的做法
		String deleteOpersSQL = "delete from sys_menu_oper where smo_menu_id = ?";
		java.util.List pl = new ArrayList();
		pl.add(menuId);
		baseDao.updateBySQL(deleteOpersSQL,pl);
		
		// 2.复制属性值
		Object[] menuObj = new Object[]{menu};
		setObjectSaveValue(menuObj);
		// 3. 保存菜单
		baseDao.saveOrUpdate(menuObj[0]);
	}

	/**
	 * 根据菜单ID，查找操作
	 */
	@Override
	public List findOpers(Long smMenuId) {
		String hql = "select * from sys_menu_oper smo where smo.smo_menu_id = ?";
		List pl = new ArrayList();
		pl.add(smMenuId);
		return baseDao.findBySQL(hql, pl);
	}
}
