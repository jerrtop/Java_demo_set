package com.fsj.spring.service.sys;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.sys.SysMenu;
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

	int checkUnique(String checkProperty,Object toBeCheckVal);
}
