package com.fsj.spring.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fsj.spring.util.CustomDateSerializer;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer id;
	private String password;
	private String name;
	private Integer age;
	private Date birthday;
	//private Integer deptId;
	private TDept dept;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(String password, String name, Integer age, Date birthday,
			TDept dept) {
		this.password = password;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		//this.deptId = deptId;
		this.dept = dept;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public TDept getDept() {
		return this.dept;
	}

	public void setDept(TDept dept) {
		this.dept = dept;
	}

}