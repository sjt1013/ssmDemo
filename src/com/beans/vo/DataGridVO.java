package com.beans.vo;

import java.util.List;

//easyUI����������ݸ�ʽ
public class DataGridVO<T> {
	private List<T> rows;
	private long total;
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
