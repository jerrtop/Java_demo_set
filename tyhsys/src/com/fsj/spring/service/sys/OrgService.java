package com.fsj.spring.service.sys;

import com.fsj.spring.model.sys.SysOrg;
import com.fsj.spring.service.TService;

public interface OrgService extends TService{
	/**
	 * 通过上级组织架构查询子架构
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	String fetchOrgs(Long parentId) throws Exception;
	
	/**
	 * 检查唯一
	 * @param checkProperty 被检查的属性
	 * @param toBeCheckVal	被检查的值
	 * @return 1：存在；0：不存在
	 */
	int checkUnique(String checkProperty,Object toBeCheckVal);
	
	SysOrg saveOrUpdateCustom(SysOrg org);
	
	void deleteOrgs(Long id);
}
