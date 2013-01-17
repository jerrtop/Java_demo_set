package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.BaseDao;
import com.fsj.spring.model.sys.SysUser;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Map<String, Object> getPageListByExemple(DataGridModel dgm, SysUser user) throws Exception {
		return baseDao.getPageListByExemple(dgm, user);
	}

	public Map<String, Object> getPageList(DataGridModel dgm, SysUser user) throws Exception {
		String totalQuery = "select count(*) from SysUser user";
		String fullQuery = "select new map(user as user,user.id as uid) from SysUser user";
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (user != null) {
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

	public SysUser getUserById(int id) throws Exception {
		return (SysUser) baseDao.findById(SysUser.class, id);
	}

	public SysUser getUserByName(String name) throws Exception {
		List<SysUser> list = baseDao.findByProperty(SysUser.class, "suUsername", name);
		return list == null || list.size() == 0 ? null : (SysUser) list.get(0);
	}

	public void addOrUpdate(SysUser user) throws Exception {
		baseDao.saveOrUpdate(user);
	}

	public void deleteUsers(List<Integer> usersId) throws Exception {
		if(usersId != null && usersId.size() > 0) {
			for (Integer id : usersId) {
				baseDao.delete(getUserById(id));
			}
		}
	}

}
