package com.fsj.spring.model.person;
// default package

import java.util.Date;

/**
 * PersonType generated by MyEclipse Persistence Tools
 */

public class PersonType implements java.io.Serializable {

	// Fields

	private Long id;

	private String ptCode;

	private String ptName;

	private String crtC;

	private Date crtDate;

	// Constructors

	/** default constructor */
	public PersonType() {
	}

	/** minimal constructor */
	public PersonType(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PersonType(Long id, String ptCode, String ptName, String crtC, Date crtDate) {
		this.id = id;
		this.ptCode = ptCode;
		this.ptName = ptName;
		this.crtC = crtC;
		this.crtDate = crtDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPtCode() {
		return this.ptCode;
	}

	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}

	public String getPtName() {
		return this.ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public String getCrtC() {
		return this.crtC;
	}

	public void setCrtC(String crtC) {
		this.crtC = crtC;
	}

	public Date getCrtDate() {
		return this.crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

}