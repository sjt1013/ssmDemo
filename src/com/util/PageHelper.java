package com.util;

public class PageHelper {
	private int pageInex;
	private int pageSize;
	private long count;
	private int begin;
	
	
	public PageHelper(int pageInex, int pageSize, long count) {
		this.pageInex = pageInex;
		this.pageSize = pageSize;
		this.count = count;
		
		this.begin=(this.pageInex-1)*this.pageSize;
	}
	public int getPageInex() {
		return pageInex;
	}
	public void setPageInex(int pageInex) {
		this.pageInex = pageInex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}
