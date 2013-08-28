package com.fsj.spring.service.sys;

import java.util.Map;

import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.TService;
/**
 * <p>
 * 	用户管理服务层
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 * wwww.9tang.info
 */
public interface UserService extends TService{
	SysUser getUserByName(String name) throws Exception;
	
	/**
	 * 分页查询用户
	 * @param params dataTables参数
	 * @return Map
	 * @throws Exception
	 */
	Map<String, Object> getPageListBySQL(Map<String,Object> params) throws Exception;
	
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
