package com.fsj.spring.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.BaseDao;
import com.fsj.spring.model.sys.SysOrg;
import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.sys.IOrgService;
import com.fsj.spring.util.DataGridModel;

@Service("orgService")
public class OrgServiceImpl implements IOrgService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Map<String, Object> getPageListByExemple(DataGridModel dgm, SysOrg org) throws Exception {
		return baseDao.getPageListByExemple(dgm, org);
	}

	public Map<String, Object> getPageList(DataGridModel dgm, SysOrg org) throws Exception {
		String totalQuery = "select count(*) from SysUser user";
		String fullQuery = "select new map(user as user,user.id as uid,user.dept.name as deptName) from SysUser user";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (org != null) {
//			if (StringUtils.isNotBlank(user.getName())) {
//				sb.append(" and user.name like :userName");
//				params.put("userName", "%" + user.getName() + "%");
//			}
//			if (user.getAge() != null) {
//				sb.append(" and user.age = :age");
//				params.put("age", user.getAge());
//			}
//			if (user.getBirthday() != null) {
//				sb.append(" and user.birthday = :birthday");
//				params.put("birthday", user.getBirthday());
//			}
//			if(user.getDept() != null && user.getDept().getId() != null){
//				sb.append(" and user.dept.id = :deptId");
//				params.put("deptId", user.getDept().getId());
//			}
//			if (user.getDeptId() != null) {
//				sb.append(" and dept.id = :deptId");
//				params.put("deptId", user.getDeptId());
//			}
		}
		
		if(sb.toString().startsWith(" and")){
			sb.delete(0, 4);
			sb.insert(0, " where ");
		}
		totalQuery += sb.toString();
		fullQuery += sb.toString();
		return baseDao.getPageList(dgm, totalQuery, fullQuery, params);
	}

	public SysOrg getOrgById(int id) throws Exception {
		return (SysOrg) baseDao.findById(SysOrg.class, id);
	}

	public SysOrg getOrgByName(String name) throws Exception {
		List<SysOrg> list = baseDao.findByProperty(SysUser.class, "suUsername", name);
		return list == null || list.size() == 0 ? null : (SysOrg) list.get(0);
	}

	public void addOrUpdate(SysOrg org) throws Exception {
		baseDao.saveOrUpdate(org);
	}

	public void deleteOrgs(List<Integer> orgsId) throws Exception {
		if(orgsId != null && orgsId.size() > 0) {
			for (Integer id : orgsId) {
				baseDao.delete(getOrgById(id));
			}
		}
	}

}
