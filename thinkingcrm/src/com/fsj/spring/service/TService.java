package com.fsj.spring.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fsj.spring.model.sys.SysUser;
/**
 * Title:基础服务层接口
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
public interface TService {
	/**
	 * 设置会话用户信息
	 * @param user
	 */
	public void setLoginUser(SysUser user);
	
	/**
	 * 新增 or 更新
	 * @param instance
	 */
	public void saveOrUpdate(Object instance);
	
	/**
	 * 保存多个对象
	 * @param objects Map用来存在要保存的对象数据
	 */
	public void saveOrUpdateMultiObjects(Map objects);
	/**
	 * 删除
	 * @param instance
	 */
	public void deleteObject(Object instance);
	/**
	 * 删除集合里的对象
	 * @param collection
	 */
	public void deleteAllObjects(Collection<?> collection);
	/**
	 * 用id集合删除多个对象
	 * @param clazz
	 * @param uids
	 */
	public void deleteAllObjects(Class clazz , List<?> uids);
	/**
	 * 通过id得到对象
	 * @param o
	 * @param id
	 * @return
	 */
	public Object getObjectById(Class clazz,Serializable id);
	
}
