package com.service;

import java.util.List;

import com.beans.pojo.SysDept;

public interface SysDeptService {
	/**
	 * 查询所有部门信息
	 * @return 集合
	 */
	public List<SysDept> getAll();
}
