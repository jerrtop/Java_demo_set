package com.bstek.demo.ims.entity;

import java.io.Serializable;


/**
 * 客户信息
 * 
 * @author JERR
 * 
 */
public class Customer implements Serializable {
	private Integer id;
	private String companyName;
	private String address;
	private String postal_code;
	private String phone;

	private String fax;

	private String email;

	private String contact_name;

	private String contact_phone;

	private String comment;

	public Customer(Integer id, String companyName, String address,
			String postal_code, String phone, String fax, String email,
			String contact_name, String contact_phone, String comment) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.postal_code = postal_code;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.contact_name = contact_name;
		this.contact_phone = contact_phone;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
