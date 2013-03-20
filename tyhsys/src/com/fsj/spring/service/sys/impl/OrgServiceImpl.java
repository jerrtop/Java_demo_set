package com.fsj.spring.service.sys.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.fsj.spring.model.sys.SysOrg;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.OrgService;

@Service("orgService")
public class OrgServiceImpl extends TServiceImpl implements OrgService {
	/**
	 *检查值唯一性
	 *@param property 被检查的属性
	 *@param toBeCheckVal 被检查的值
	 *@return int 
	 */
	@Override
	public int checkUnique(String property,Object toBeCheckVal) {
		String countSql = "select count(*) from sys_org org where " + property + "= ?";
		java.util.List pl = new ArrayList();
		pl.add(toBeCheckVal);
		int count = baseDao.findObjectsCount(countSql,pl);
		if(count >=1){
			count = 1;
		}
		return count;
	}
	@Override
	public String fetchOrgs(Long parentId) throws Exception {
		JSONArray orgs = new JSONArray();
		String sql = "select org.*,orgParent.so_name as 'PARENTNAME' from sys_org org left join sys_org orgParent on org.so_parent = orgParent.id ";
		List params = new ArrayList();
		if(parentId != null){
			sql += " where org.so_parent = ?";
			params.add(parentId);
		}else{
			sql += " where org.so_parent is null or org.so_parent = ''";
		}
		sql = "select * from (" + sql + ") t";
		List result = baseDao.findBySQL(sql, params);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Map orgMap = (Map) iterator.next();
			JSONObject json = new JSONObject();
			json.put("id", orgMap.get("ID"));
			json.put("text", orgMap.get("SO_NAME"));
			JSONObject attributes = new JSONObject();
			attributes.put("soCode", orgMap.get("SO_CODE"));
			attributes.put("parentName", orgMap.get("PARENTNAME"));
			attributes.put("parentId", orgMap.get("SO_PARENT"));
			json.put("attributes", attributes);
			int childCount = getChildrenCount(Long.parseLong(orgMap.get("ID").toString()));
			if(childCount > 0){
				json.put("state", "closed");
				json.put("children", new JSONArray());
			}
			orgs.add(json);
		}
		return orgs.toString();
	}
	/**
	 * 查询子记录条数
	 * @param parentId
	 * @return
	 */
	private int getChildrenCount(Long parentId){
		int count = 0;
		String sql = "select count(1) from sys_org where so_parent = ?";
		List params = new ArrayList();
		params.add(parentId);
		count = baseDao.findObjectsCount(sql, params);
		return count;
	}
	/**
	 * 保存并返回Org
	 */
	public SysOrg saveOrUpdateCustom(SysOrg o) {
		Object[] objs = new Object[] { o };
		setObjectSaveValue(objs);
		baseDao.saveOrUpdate(objs[0]);
		List result = (List) baseDao.findByProperty(SysOrg.class, "soCode", o.getSoCode());
		return (SysOrg) result.get(0);
	}
	/**
	 * 删除节点及子节点
	 */
	@Override
	public void deleteOrgs(Long id) {
		String sql = "select id from (";
		sql += " select "+ id +" as id";
		sql += " union";
		sql += " select org2.id from sys_org org1,sys_org org2 where org1.id = org2.so_parent and (org1.id = ? or org1.so_parent = ?)";
		sql += "  ) t";
		
		List params = new ArrayList();
		params.add(id);
		params.add(id);
		List orgs = baseDao.findBySQL(sql, params);
		List toBeDeletedEntis = new ArrayList();
		for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
			Map orgMap = (Map) iterator.next();
			SysOrg org = new SysOrg();
			org.setId(Long.parseLong(orgMap.get("ID").toString()));
			toBeDeletedEntis.add(org);
		}
		baseDao.deleteAll(toBeDeletedEntis);
	}
}
