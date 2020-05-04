package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.beans.pojo.SysDept;

public interface SysDeptDao {
	@Select("SELECT * FROM SYSDEPT WHERE ID=#{id}")
	public SysDept getById(int id);
	
	@Select("SELECT * FROM SYSDEPT")
	public List<SysDept> getAll();
}
