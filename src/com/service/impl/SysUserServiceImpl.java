package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.pojo.SysFile;
import com.beans.pojo.SysUser;
import com.beans.vo.DataGridVO;
import com.dao.SysUserDao;
import com.service.SysUserService;
import com.util.PageHelper;

@Service("sysuserService")
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserDao sud;
		
	@Override
	public DataGridVO<SysUser> getByPage(int pageIndex,int pageSize) {
		Map<String,Object> params =new HashMap<String,Object>();
		DataGridVO<SysUser> dgvo =null;
		
		long dataCount=sud.getCount();
		
		//分页辅助
		PageHelper ph=new PageHelper(pageIndex,pageSize,dataCount);
		
		//准备参数容器
		params.put("dataBegin", ph.getBegin());
		params.put("pageSize", ph.getPageSize());
		
		List<SysUser> dataList =sud.getByPage(params);
		
		if(dataList !=null && dataList.size()>0) {
			dgvo=new DataGridVO<SysUser>();
			dgvo.setTotal(dataCount);
			dgvo.setRows(dataList);
		}
		
		return dgvo;
	}

	@Override
	public SysUser login(SysUser su) {
		return sud.getByLogin(su);
	}

	@Override
	public boolean add(SysUser su) {
		return sud.add(su)>0?true:false;
	}

	@Override
	public int getCountByLname(String lname) {
		return sud.getCountByLname(lname);
	}

	@Override
	public SysUser getById(int id) {
		return sud.getById(id);
	}

	@Override
	public boolean upd(SysUser su) {
		return sud.upd(su)>0?true:false;
	}

	@Override
	public boolean del(int id) {
		return sud.del(id)>0?true:false;
	}

	@Override
	public boolean addFileInfo(int sysuserid, String fileName) {
		Map<String,Object> params =new HashMap<String,Object>();
		params.put("sysuserid",sysuserid);
		params.put("filename", fileName);
		
		return sud.addFileInfo(params)>0?true:false;
	}

	@Override
	public List<SysFile> getSysFileByUserid(int id) {
		return sud.getFileByUserId(id);
	}

	@Override
	public boolean delSysFileById(int id) {
		return sud.delSysFileById(id)>0?true:false;
	}
	
}
