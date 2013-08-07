package com.fsj.spring.service.sys;

import java.util.Map;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.TService;
import com.fsj.spring.util.DataGridModel;
/**
 * Title:用户管理服务层接口
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
public interface UserService extends TService{
	SysUser getUserByName(String name) throws Exception;
	//这是使用HQL分页查询的例子
	//Map<String, Object> getPageList(DataGridModel dgm,SysUser user) throws Exception;
	
	//这是使用SQL分页查询的例子
	Map<String, Object> getPageListBySQL(DataGridModel dgm,SysUser user) throws Exception;
	
	/**
	 * 检查唯一
	 * @param checkProperty 被检查的属性
	 * @param toBeCheckVal	被检查的值
	 * @return 1：存在；0：不存在
	 */
	int checkUnique(String checkProperty,Object toBeCheckVal);
	/**
	 * 得到用户菜单权限
	 * @param user 用户
	 * @return 菜单权限json
	 */
	String getUserRoleMenus(SysUser user);
	/**
	 * 获得部门树
	 * @return
	 */
	String getUserOrgs() throws Exception;
	/**
	 * 获得上级用户信息
	 * @param userId 编辑的用户id
	 * @return
	 */
	String getSups(Long userId);
	
	void updatePWD(Long id,String pwd);
}
