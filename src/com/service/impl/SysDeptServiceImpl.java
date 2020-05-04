package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.pojo.SysDept;
import com.dao.SysDeptDao;
import com.service.SysDeptService;

@Service("sysdeptService")
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sdd;

	@Override
	public List<SysDept> getAll() {
		return sdd.getAll();
	}

}
