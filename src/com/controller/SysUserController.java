package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beans.pojo.SysUser;
import com.beans.vo.NoticeVO;
import com.service.SysUserService;
import com.util.Code_zh_cn;
import com.util.FileHelper;
import com.util.JsonHelper;

@Controller
@RequestMapping(value="api/sysuser/",produces = "text/html;charset=utf-8")
public class SysUserController {
	@Autowired
	@Qualifier("sysuserService")
	private SysUserService sus;
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public ModelAndView login(SysUser su,HttpSession session) {
		ModelAndView mv =new ModelAndView();
		
		//����ҵ�����е�¼
		SysUser sysuser=sus.login(su);
		
		if(sysuser !=null) {
			//��¼ƾ֤����session
			session.setAttribute("loginuser", sysuser);
			mv.setViewName("main");
		}else {
			mv.addObject("loginmsg", "�û����������벻��ȷ��");
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@RequestMapping(value="getByPage",method = RequestMethod.GET)
	@ResponseBody
	public String getByPage(int page,int rows) {
		return JsonHelper.toJsonString(sus.getByPage(page, rows));
	}
	
	@RequestMapping("exit")
	public ModelAndView exit(HttpSession session) {
		session.invalidate();
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public String add(SysUser su) {
		//֪ͨģ��
		NoticeVO nvo =new NoticeVO();
		nvo.setMsg(Code_zh_cn.ADD_FAIL);
		
		if(sus.add(su)) {
			nvo.setSuccess(true);
			nvo.setMsg(Code_zh_cn.ADD_SUCCESS);
		}
		
		return JsonHelper.toJsonString(nvo);
	}
	
	@RequestMapping(value="getCountByLname",method = RequestMethod.GET)
	@ResponseBody
	public String getCountByLname(String lname) {
		return String.valueOf(sus.getCountByLname(lname));
	}
	
	@RequestMapping(value="getById",method = RequestMethod.GET)
	@ResponseBody
	public String getById(int id) {
		return JsonHelper.toJsonString(sus.getById(id));
	}
	
	@RequestMapping(value="upd",method = RequestMethod.POST)
	@ResponseBody
	public String upd(SysUser su) {
		//֪ͨģ��
		NoticeVO nvo =new NoticeVO();
		nvo.setMsg(Code_zh_cn.UPD_FAIL);
		
		if(sus.upd(su)) {
			nvo.setSuccess(true);
			nvo.setMsg(Code_zh_cn.UPD_SUCCESS);
		}
		
		return JsonHelper.toJsonString(nvo);
	}
	
	@RequestMapping(value="del",method = RequestMethod.GET)
	@ResponseBody
	public String del(int id) {
		//֪ͨģ��
		NoticeVO nvo =new NoticeVO();
		nvo.setMsg(Code_zh_cn.REMOVE_FAIL);
		
		if(sus.del(id)) {
			nvo.setSuccess(true);
			nvo.setMsg(Code_zh_cn.REMOVE_SUCCESS);
		}
		
		return JsonHelper.toJsonString(nvo);
	}
	
	@RequestMapping(value="upload",method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(int sysuserid,@RequestParam("myfiles") MultipartFile[] files,HttpServletRequest request){
		//֪ͨģ��
		NoticeVO nvo =new NoticeVO();
		nvo.setMsg(Code_zh_cn.UPLOAD_FAIL);
		
		if (files != null && files.length > 0) {
			try {
				for(MultipartFile mulFile:files) {
					//�����ļ�������Ϊ���ļ���
					String newFileName=FileHelper.newFileName(mulFile.getOriginalFilename());
					
					//�����ε����ļ��������е��ϴ�
					FileHelper.saveFile(mulFile, newFileName);
					
					//�����ļ������û�ID�������ݿ�
					sus.addFileInfo(sysuserid, newFileName);
				}
				
				nvo.setSuccess(true);
				nvo.setMsg(Code_zh_cn.UPLOAD_SUCCESS);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return JsonHelper.toJsonString(nvo);
	}
	
	@RequestMapping(value="download",method = RequestMethod.GET)
	@ResponseBody
	public void downloadFile(String fileName,HttpServletResponse response) {
		FileHelper.dowloadFile(response, fileName);
	}
	
	@RequestMapping(value="getSysFileByUserId",method = RequestMethod.GET)
	@ResponseBody
	public String getSysFileByUserId(int id) {
		return JsonHelper.toJsonString(sus.getSysFileByUserid(id));
	}
	
	@RequestMapping(value="delSysFileById",method = RequestMethod.GET)
	@ResponseBody
	public String delSysFileById(int id,String fileName) {
		//֪ͨģ��
		NoticeVO nvo =new NoticeVO();
		nvo.setMsg(Code_zh_cn.REMOVE_FAIL);
		
		//ɾ���ļ�����Ϣ
		if(FileHelper.removeFile(fileName) && sus.delSysFileById(id)) {
			nvo.setSuccess(true);
			nvo.setMsg(Code_zh_cn.REMOVE_SUCCESS);
		}
		
		return JsonHelper.toJsonString(nvo);
	}
}
