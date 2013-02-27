package com.fsj.spring.model.sys;

import java.io.Serializable;
/**
 * 用户角色
 * @author JERR
 *
 */
public class SysUserRole implements Serializable {
	private Long id;
	//用户ID
	private Long surUserId;
	//角色ID
	private Long surRoleId;

	public SysUserRole() {
	}

	public Long getSurUserId() {
		return surUserId;
	}

	public void setSurUserId(Long surUserId) {
		this.surUserId = surUserId;
	}

	public Long getSurRoleId() {
		return surRoleId;
	}

	public void setSurRoleId(Long surRoleId) {
		this.surRoleId = surRoleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysUserRole(Long id , Long surUserId, Long surRoleId) {
		super();
		this.id = id;
		this.surUserId = surUserId;
		this.surRoleId = surRoleId;
	}

}
