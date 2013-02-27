package com.fsj.spring.model.sys;

import java.io.Serializable;
/**
 * 角色菜单配置
 * @author JERR
 *
 */
public class SysRoleMenu implements Serializable {
	private Long id;
	//角色ID
	private Long srmRoleId;
	
	//菜单ID
	private Long srmMenuId;
	
	//菜单操作，以,分隔 ， 如add,edit,search 
	private String srmOpers;

	public Long getSrmRoleId() {
		return srmRoleId;
	}

	public void setSrmRoleId(Long srmRoleId) {
		this.srmRoleId = srmRoleId;
	}

	public Long getSrmMenuId() {
		return srmMenuId;
	}

	public void setSrmMenuId(Long srmMenuId) {
		this.srmMenuId = srmMenuId;
	}

	public String getSrmOpers() {
		return srmOpers;
	}

	public void setSrmOpers(String srmOpers) {
		this.srmOpers = srmOpers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysRoleMenu(Long id,Long srmRoleId, Long srmMenuId, String srmOpers) {
		super();
		this.id = id;
		this.srmRoleId = srmRoleId;
		this.srmMenuId = srmMenuId;
		this.srmOpers = srmOpers;
	}

	public SysRoleMenu() {
		super();
	}
	
	
}
