/**
 * 
 */
package com.fsj.spring.util;

import java.io.Serializable;

/** <p>
 * 
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 * www.9tang.info
 */
public class DataTableParam implements Serializable{

	//参数名称
	private String name;
	//参数值
	private Object value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
