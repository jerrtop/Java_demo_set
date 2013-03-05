package com.fsj.spring.service.sys;

import java.util.Map;

import com.fsj.spring.model.sys.SysRole;
import com.fsj.spring.service.TService;
import com.fsj.spring.util.DataGridModel;
/**
 * 角色管理服务层接口
 * @author JERR
 *
 */
public interface RoleService extends TService{
	//这是使用SQL分页查询的例子
	Map<String, Object> getPageListBySQL(DataGridModel dgm,SysRole role) throws Exception;
	/**
	 * 检查唯一
	 * @param checkProperty 被检查的属性
	 * @param toBeCheckVal	被检查的值
	 * @return 1：存在；0：不存在
	 */
	int checkUnique(String checkProperty,Object toBeCheckVal);
	/**
	 * 通过角色ID获得菜单
	 * @param srRoleId 角色ID
	 * @return 菜单JSON
	 * @throws Exception
	 */
	String fetchMenus(Long srRoleId) throws Exception;
}
