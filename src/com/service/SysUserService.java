package com.service;

import java.util.List;

import com.beans.pojo.SysFile;
import com.beans.pojo.SysUser;
import com.beans.vo.DataGridVO;

public interface SysUserService {
	/**
	 * 查询集合
	 * @param su 实体
	 * @return 集合
	 */
	public DataGridVO<SysUser> getByPage(int pageIndex,int pageSize);
	
	/**
	 * 用户登录
	 * @param su 实体
	 * @return 实体
	 */
	public SysUser login(SysUser su);
	
	/**
	 * 新增用户
	 * @param su 实体
	 * @return true || false
	 */
	public boolean add(SysUser su);
	
	/**
	 * 查询用户名数量
	 * @param name 用户名
	 * @return 数量
	 */
	public int getCountByLname(String lname);
	
	/**
	 * 通过ID查询用户信息
	 * @param id ID值
	 * @return 实体
	 */
	public SysUser getById(int id);
	
	/**
	 * 修改用户资料
	 * @param su 实体
	 * @return true || false
	 */
	public boolean upd(SysUser su);
	
	/**
	 * 通过ID删除用户信息
	 * @param id DI值
	 * @return true || false
	 */
	public boolean del(int id);
	
	/**
	 * 新增文件对应的信息
	 * @param sysuserid 用户ID
	 * @param fileName 上传后的文件名称
	 * @return true || false
	 */
	public boolean addFileInfo(int sysuserid,String fileName);
	
	/**
	 * 通过用户ID查询用户对应文件关联信息
	 * @param id 用户ID
	 * @return 集合
	 */
	public List<SysFile> getSysFileByUserid(int id);
	
	/**
	 * 删除文件信息
	 * @param id 文件id
	 * @return true || false
	 */
	public boolean delSysFileById(int id);
}
