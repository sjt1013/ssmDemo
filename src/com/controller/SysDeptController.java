package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.SysDeptService;
import com.util.JsonHelper;

@Controller
@RequestMapping(value="api/sysdept/",produces = "text/html;charset=utf-8")
public class SysDeptController {
	@Autowired
	private SysDeptService sds;
	
	@RequestMapping("getAll")
	@ResponseBody
	public String getAll() {
		return JsonHelper.toJsonString(sds.getAll());
	}
}
