package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.beans.pojo.SysFile;
import com.beans.pojo.SysUser;

public interface SysUserDao {
	@Select("SELECT * FROM SYSUSER")
	@Results(value={
			@Result(column = "DEPT_ID",property = "dept",
					one=@One(select = "com.dao.SysDeptDao.getById"))
	})
	public List<SysUser> getList(Map<String,Object> params);
	
	@Select("SELECT * FROM SYSUSER WHERE LNAME=#{lname} AND LPASS=#{lpass}")
	public SysUser getByLogin(SysUser su);
	
	@Select("SELECT * FROM SYSUSER ORDER BY ID DESC LIMIT #{dataBegin},#{pageSize}")
	@Results(value={
			@Result(column = "DEPT_ID",property = "dept",
					one=@One(select = "com.dao.SysDeptDao.getById"))
	})
	public List<SysUser> getByPage(Map<String,Object> params);
	
	@Select("SELECT COUNT(ID) FROM SYSUSER")
	public long getCount();
	
	@Insert("INSERT INTO SYSUSER(LNAME,LPASS,RNAME,PHONE,ADDRESS,BIRTHDAY,DEPT_ID) "
			+ "VALUES(#{lname},#{lpass},#{rname},#{phone},#{address},#{birthday},#{dept.id})")
	public int add(SysUser su);
	
	@Select("SELECT COUNT(ID) FROM SYSUSER WHERE LNAME=#{lname}")
	public int getCountByLname(String lname);
	
	@Select("SELECT * FROM SYSUSER WHERE ID=#{id}")
	@Results(value={
			@Result(column = "DEPT_ID",property = "dept.id")
	})
	public SysUser getById(int id);
	
	@Update("UPDATE SYSUSER "
			+ "SET LPASS=#{lpass},"
			+ "RNAME=#{rname},"
			+ "PHONE=#{phone},"
			+ "ADDRESS=#{address},"
			+ "BIRTHDAY=#{birthday},"
			+ "DEPT_ID=#{dept.id} "
			+ "WHERE ID=#{id}")
	public int upd(SysUser su);
	
	@Delete("DELETE FROM SYSUSER WHERE ID=#{id}")
	public int del(int id);
	
	@Insert("INSERT INTO SYSFILE(PID,FILE_NAME) VALUES(#{sysuserid},#{filename})")
	public int addFileInfo(Map<String,Object> params);
	
	@Select("SELECT * FROM SYSFILE WHERE PID=#{id}")
	@Results(value={
			@Result(column = "FILE_NAME",property = "fileName")
	})
	public List<SysFile> getFileByUserId(int id);
	
	@Delete("DELETE FROM SYSFILE WHERE ID=#{id}")
	public int delSysFileById(int id);
}
