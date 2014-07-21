/**
 * 
 */
package com.fsj.spring.util;

import java.io.Serializable;

/** <p>
 * 		DataTable对象
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
public class DataTableModel implements Serializable {
	
	private static final long serialVersionUID = -8650037949420256892L;
	//起始记录
	private int iDisplayStart;
	//分页记录数
	private int iDisplayLength;
	//排序列数
	private int iSortingCols;
	//搜索关键字
	private String sSearch;
	//请求次数
	private int sEcho;
	//以逗号分隔的列表中的列名称
	private String sColumns;
	//正则
	private boolean bRegex;
	//表格列数
	private int iColumns;
	// 排序列索引iSortColumnIndex
    public int iSortCol_0;
    //排序方式 sSortDirection // asc or desc
    public String sSortDir_0;
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public boolean isbRegex() {
		return bRegex;
	}
	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public int getiColumns() {
		return iColumns;
	}
	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}
	public int getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0() {
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

}
