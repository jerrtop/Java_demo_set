package com.fsj.spring.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;



import com.fsj.spring.model.sys.SysMenu;
import com.fsj.spring.model.sys.SysMenuOper;
import com.fsj.spring.model.sys.SysRole;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.MenuService;
import com.fsj.spring.service.sys.RoleService;
import com.fsj.spring.util.DataGridModel;
/**
 * 角色管理服务层实现类
 * @author JERR
 *
 */
@Service("roleService")
public class RoleServiceImpl extends TServiceImpl implements RoleService {
	/**
	 * 查询菜单列表
	 *@param dgm	grid对象
	 *@param menu	查询的菜单对象
	 *@return
	 *@throws Exception
	 */
	public Map<String, Object> getPageListBySQL(DataGridModel dgm, SysRole role) throws Exception {
		String totalQuery = "select count(*) from sys_role role";
		//多表查询的例子
		String fullQuery = "select role.*,role.id as uid from sys_role role ";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (role != null) {
			if (StringUtils.isNotBlank(role.getSrCode())) {
				sb.append(" and role.SR_CODE like :srCode");
				params.put("srCode", "%" + role.getSrCode() + "%");
			}
			if (StringUtils.isNotBlank(role.getSrName())) {
				sb.append(" and role.SR_NAME like :srName");
				params.put("srName", "%" + role.getSrName() + "%");
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
		String countSql = "select count(*) from sys_role role where " + property + "= ?";
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
//	public void saveOrUpdate(Object o){
//		SysMenu menu = (SysMenu) o;
//		Long menuId = menu.getId();
//					
//		// 1. 操作权限采取先删后插入的做法
//		String deleteOpersSQL = "delete from sys_menu_oper where smo_menu_id = ?";
//		java.util.List pl = new ArrayList();
//		pl.add(menuId);
//		baseDao.updateBySQL(deleteOpersSQL,pl);
//		
//		// 2.复制属性值
//		Object[] menuObj = new Object[]{menu};
//		setObjectSaveValue(menuObj);
//		// 3. 保存菜单
//		baseDao.saveOrUpdate(menuObj[0]);
//	}

}
