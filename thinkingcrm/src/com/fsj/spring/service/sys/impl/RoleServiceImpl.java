package com.fsj.spring.service.sys.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fsj.spring.model.sys.SysRole;
import com.fsj.spring.model.sys.SysRoleMenu;
import com.fsj.spring.service.TServiceImpl;
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
	 * 获得菜单
	 * @param smParentCode 上级菜单编码
	 */
	@Override
	public String fetchMenus(Long srRoleId) throws Exception {
		JSONArray menus = new JSONArray();
		menus = fetchMenuIterator(null,srRoleId);
		return menus.toString();
	}
	/**
	 * 递归得到菜单信息
	 * @param smParentCode
	 * @param srRoleId
	 * @return
	 */
	private JSONArray fetchMenuIterator(String smParentCode,Long srRoleId){
		List pl = new ArrayList();
		String sql = "select menu.*,(select group_concat(concat_ws('=',SMO_NAME,smo_operation)) from sys_menu_oper smo where smo.smo_menu_id = menu.id and smo.smo_valid = 'Y') as 'menuOpers' from sys_menu menu ";
		
		if(StringUtils.isNotBlank(smParentCode)){
			sql += " where SM_PARENT = ?";
			pl.add(smParentCode);
		}else{
			sql += " where (SM_PARENT is null or SM_PARENT = '')";
		}
		sql = "select * from (" + sql + ") t order by t.sm_code asc";
		//System.out.println(".......... " + sql);
		
		List result = baseDao.findBySQL(sql, pl);
		JSONArray submenus = null;
		if(result != null && result.size() > 0){
			submenus = new JSONArray();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map menuMap = (Map) iterator.next();
				
				JSONObject json = new JSONObject();
				json.put("id", menuMap.get("ID"));
				json.put("text", menuMap.get("SM_NAME"));
				json.put("state", "open");
				
				JSONObject attributes = findRoleMenu(srRoleId, Long.parseLong(menuMap.get("ID").toString()));
				attributes.put("menuOpers", menuMap.get("MENUOPERS"));
				json.put("checked", attributes.getBoolean("checked"));
				attributes.remove("checked");
				json.put("attributes", attributes);
				
				String currentSmParentCode = menuMap.get("SM_CODE").toString();
				json.put("children", fetchMenuIterator(currentSmParentCode,srRoleId));
				submenus.add(json);
			}
		}
		
		return submenus;
	}
	
	/**
	 * 角色菜单信息
	 * @param srRoleId 角色ID
	 * @param smMenuId 菜单ID
	 * @return
	 */
	private JSONObject findRoleMenu(Long srRoleId,Long smMenuId){
		JSONObject roleMenu = new JSONObject();
		
		boolean checked = false;
		String selectedMenuOpers = "";
		
		if(srRoleId != null){ //编辑
			String sql = "select * from sys_role_menu srm where srm_role_id = ? and srm_menu_id = ?";
			List pl = new ArrayList();
			pl.add(srRoleId);
			pl.add(smMenuId);
			List result = baseDao.findBySQL(sql, pl);
			
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map object = (Map) iterator.next();
				checked = true;
				selectedMenuOpers = object.get("SRM_OPERS").toString();
			}
		}
			
		roleMenu.put("checked", checked);
		roleMenu.put("selectedMenuOpers", selectedMenuOpers);
		return roleMenu;
	}
	
	/**
	 * 保存角色
	 */
	@Override
	public void saveOrUpdate(Object str) {
		JSONObject role = JSONObject.fromObject(str);
		SysRole sysRole = new SysRole();
		String where = "";
		List pl = new ArrayList();
		Long srRoleId = null;
		if(role.get("id") != null && !role.get("id").toString().equals("")){
			srRoleId = Long.parseLong(role.get("id").toString());
			sysRole.setId(srRoleId);
			where = " where srm_role_id = ?";
			pl.add(srRoleId);
		}
		// 1.保存角色
		sysRole.setSrCode(role.get("srCode").toString());
		sysRole.setSrName(role.get("srName").toString());
		Object[] o = new Object[]{sysRole};
		setObjectSaveValue(o);//复制属性值
		baseDao.saveOrUpdate(o[0]);
		
		
		// 2.先删除角色菜单权限，再插入
		if(srRoleId == null){
			List roles = baseDao.findByExample(sysRole);
			srRoleId = ((SysRole) roles.get(0)).getId();
		}
		if(where.length() > 0){
			String sql = "delete from sys_role_menu ";
			sql += where;
			
			baseDao.updateBySQL(sql, pl);
		}
		String menuOpers = role.getString("menuopers");
		JSONArray items = JSONArray.fromObject(menuOpers);
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = items.getJSONObject(i);
			SysRoleMenu srm = (SysRoleMenu) JSONObject.toBean(item, SysRoleMenu.class);
			srm.setSrmRoleId(srRoleId);
			
			baseDao.saveOrUpdate(srm);
		}
	}

	/**
	 * 删除角色
	 */
	@Override
	public void deleteAllObjects(Class clazz, List<?> uids) {
		if (uids != null && uids.size() > 0) {
			SysRole role = null;
			List roleMenus = new ArrayList();
			for (Iterator iter = uids.iterator(); iter.hasNext();) {
				role = (SysRole) baseDao.findById(clazz, (Serializable) iter.next());
				//删除角色
				deleteObject(role);
				
				//删除角色菜单权限
				roleMenus = baseDao.findByProperty(SysRoleMenu.class, "srmRoleId", role.getId());
				if(roleMenus != null && roleMenus.size() > 0)
					deleteAllObjects(roleMenus);
			}
		}
	}
}
