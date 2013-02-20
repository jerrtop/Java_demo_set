package com.fsj.spring.service.sys;

import java.util.Map;

import com.fsj.spring.model.sys.SysMenu;
import com.fsj.spring.service.TService;
import com.fsj.spring.util.DataGridModel;
/**
 * Title:用户管理服务层接口
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
public interface MenuService extends TService{
	//这是使用SQL分页查询的例子
	Map<String, Object> getPageListBySQL(DataGridModel dgm,SysMenu menu) throws Exception;
}
