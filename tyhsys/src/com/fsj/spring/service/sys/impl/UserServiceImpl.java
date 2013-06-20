package com.fsj.spring.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fsj.spring.model.person.PersonInfo;
import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.TServiceImpl;
import com.fsj.spring.service.sys.UserService;
import com.fsj.spring.util.DataGridModel;
import com.fsj.spring.util.MD5Util;
/**
 * Title:用户管理服务层实现类
 *
 * @author 唐有欢
 * @version 1.0 , 2013-1-18 创建
 */
@Service("userService")
public class UserServiceImpl extends TServiceImpl implements UserService {
	/**
	 * 使用HQL分页查询的例子
	 */
//	public Map<String, Object> getPageList(DataGridModel dgm, SysUser user) throws Exception {
//		String totalQuery = "select count(*) from SysUser user";
//		String fullQuery = "select new map(user as user,user.id as uid) from SysUser user";
//		StringBuffer sb = new StringBuffer();
//		Map<String, Object> params = new HashMap<String, Object>();
//
//		if (user != null) {
//			if (StringUtils.isNotBlank(user.getSuUsername())) {
//				sb.append(" and user.suUsername like :userName");
//				params.put("userName", "%" + user.getSuUsername() + "%");
//			}
//			if (StringUtils.isNotBlank(user.getSuNameCn())) {
//				sb.append(" and user.suNameCn like :suNameCn");
//				params.put("suNameCn", "%" + user.getSuNameCn() + "%");
//			}
//		}
//
//		if (sb.toString().startsWith(" and")) {
//			sb.delete(0, 4);
//			sb.insert(0, " where ");
//		}
//		totalQuery += sb.toString();
//		fullQuery += sb.toString();
//		return baseDao.getPageList(dgm, totalQuery, fullQuery, params);
//	}
	/**
	 * 使用SQL分页查询的例子
	 */
	public Map<String, Object> getPageListBySQL(DataGridModel dgm, SysUser user) throws Exception {
		String totalQuery = "select count(*) from sys_user user join person_info pi on user.su_person_id = pi.id";
		String fullQuery = "select USER.ID,USER.SU_USERNAME,USER.SU_PASSWORD,USER.SU_ACC_ENA,USER.SU_MEMO,USER.SU_PERSON_ID,USER.ID AS uid,pi.*,pi.ID as PERSONID from sys_user user join person_info pi on user.su_person_id = pi.id";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		PersonInfo person = user.getPerson();
		if (user != null) {
			if (StringUtils.isNotBlank(user.getSuUsername())) {
				sb.append(" and user.su_username like :suUsername");
				params.put("suUsername", "%" + user.getSuUsername() + "%");
			}
			
			if (person!= null && StringUtils.isNotBlank(person.getPiName())) {
				sb.append(" and pi.pi_name like :piName");
				params.put("piName", "%" + person.getPiName() + "%");
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

	public SysUser getUserByName(String name) throws Exception {
		List<SysUser> list = baseDao.findByProperty(SysUser.class, "suUsername", name);
		return list == null || list.size() == 0 ? null : (SysUser) list.get(0);
	}

	@Override
	public int checkUnique(String checkProperty, Object toBeCheckVal) {
		String countSql = "select count(*) from sys_user user where " + checkProperty + "= ?";
		java.util.List pl = new ArrayList();
		pl.add(toBeCheckVal);
		int count = baseDao.findObjectsCount(countSql,pl);
		if(count >=1){
			count = 1;
		}
		return count;
	}
	/**
	 * 保存用户
	 */
	@Override
	public void saveOrUpdate(Object o) {
		SysUser user = (SysUser)o;
		Long id = user.getId();
		if(id != null){ //编辑
			SysUser persistUser = (SysUser) super.getObjectById(SysUser.class, id);
			//修改密码
			if(!user.getSuPassword().equals(persistUser.getSuPassword())){
				//MD5加密
				String md5PassWord = MD5Util.getMD5String(user.getSuPassword());
				user.setSuPassword(md5PassWord);
			}
		}else{
			//MD5加密
			String md5PassWord = MD5Util.getMD5String(user.getSuPassword());
			user.setSuPassword(md5PassWord);
		}
		
		PersonInfo person = user.getPerson();
		if(person != null){
			Object[] obj = new Object[]{person};
			setObjectSaveValue(obj);
		}
		super.saveOrUpdate(user);
	}
	/*
	 * (non-Javadoc)
	 * @see com.fsj.spring.service.sys.UserService#getUserRoleMenus(com.fsj.spring.model.sys.SysUser)
	 */
	@Override
	public String getUserRoleMenus(SysUser user) {
		JSONArray menus = new JSONArray();
		menus = fetchMenuIterator(null,user.getId());
		return menus.toString();
	}
	
	/**
	 * 递归得到菜单信息
	 * @param smParentCode
	 * @param srRoleId
	 * @return
	 */
	private JSONArray fetchMenuIterator(String smParentCode,Long userId){
		List pl = new ArrayList();
		//String sql = "select menu.*,(select group_concat(concat_ws('=',SMO_NAME,smo_operation)) from sys_menu_oper smo where smo.smo_menu_id = menu.id and smo.smo_valid = 'Y') as 'menuOpers' from sys_menu menu ";
		String sql = "select sm.id,sm.sm_name,sm.sm_code,sm.sm_page,GROUP_CONCAT(srm.SRM_OPERS) as 'selectedMenuOpers' from sys_role_menu srm join sys_menu sm on srm.srm_menu_id = sm.id ";
		if(StringUtils.isNotBlank(smParentCode)){
			sql += " where sm.SM_PARENT = ?";
			pl.add(smParentCode);
		}else{
			sql += " where (sm.SM_PARENT is null or sm.SM_PARENT = '')";
		}
		
		sql += " and exists (select id from sys_user_role sur where sur.sur_role_id = srm.SRM_ROLE_ID and sur.sur_user_id = ?) group by SRM_MENU_ID ";
		pl.add(userId);
		
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
				json.put("menuname", menuMap.get("SM_NAME"));
				json.put("menucode", menuMap.get("SM_CODE"));
				json.put("menuurl", menuMap.get("SM_PAGE"));
				json.put("selectedMenuOpers", menuMap.get("SELECTEDMENUOPERS"));
				
				String currentSmParentCode = menuMap.get("SM_CODE").toString();
				json.put("children", fetchMenuIterator(currentSmParentCode,userId));
				submenus.add(json);
			}
		}
		
		return submenus;
	}
	
	@Override
	public String getUserOrgs() throws Exception {
		JSONArray orgs = new JSONArray();
		orgs = fetchOrgIterator(null,this.sysUser.getPerson().getPiOrg());
		return orgs.toString();
	}
	
	/**
	 * 递归部门（组织结构）信息
	 * @param soParentId	上级部门id
	 * @param currUserOrgId 当前用户部门id	
	 * @return
	 */
	private JSONArray fetchOrgIterator(Long soParentId,Long currUserOrgId){
		List pl = new ArrayList();
		String sql = "select so.id,so.so_name from sys_org so";
		if(soParentId != null){
			sql += " where so.SO_PARENT = ?";
			pl.add(soParentId);
		}else{
			//过滤当前登录用户的部门
			sql += " where so.id = ?";
			pl.add(currUserOrgId);
		}
		
		sql += " order by so.so_code asc";
		
		
		List result = baseDao.findBySQL(sql, pl);
		JSONArray suborgs = null;
		if(result != null && result.size() > 0){
			suborgs = new JSONArray();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map orgMap = (Map) iterator.next();
				
				JSONObject json = new JSONObject();
				Long parentId = Long.parseLong(orgMap.get("ID").toString());
				json.put("id", parentId);
				json.put("text", orgMap.get("SO_NAME"));
				json.put("state", "open");
				
				json.put("children", fetchOrgIterator(parentId,currUserOrgId));
				suborgs.add(json);
			}
		}
		
		return suborgs;
	}
	
	@Override
	public String getSups(Long userId) {
		JSONArray sups = new JSONArray();
		String hql = "from SysUser info";
		List pl = new ArrayList();
		if(userId != null){
			hql += " where info.id != ?";
			pl.add(userId);
		}
		List result = baseDao.findByHQL(hql, pl);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			SysUser user = (SysUser) iterator.next();
			JSONObject item = new JSONObject();
			item.put("id", user.getId());
			item.put("text", user.getPerson().getPiName());
			sups.add(item);
		}
		return sups.toString();
	}
	
	@Override
	public void updatePWD(Long id, String pwd) {
		String sql = "update sys_user set su_password = ? where id = ?";
		List pl =new ArrayList();
		pl.add(pwd);
		pl.add(id);
		baseDao.updateBySQL(sql, pl);
		
	}
}
