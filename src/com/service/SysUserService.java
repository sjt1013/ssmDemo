package com.service;

import java.util.List;

import com.beans.pojo.SysFile;
import com.beans.pojo.SysUser;
import com.beans.vo.DataGridVO;

public interface SysUserService {
	/**
	 * ��ѯ����
	 * @param su ʵ��
	 * @return ����
	 */
	public DataGridVO<SysUser> getByPage(int pageIndex,int pageSize);
	
	/**
	 * �û���¼
	 * @param su ʵ��
	 * @return ʵ��
	 */
	public SysUser login(SysUser su);
	
	/**
	 * �����û�
	 * @param su ʵ��
	 * @return true || false
	 */
	public boolean add(SysUser su);
	
	/**
	 * ��ѯ�û�������
	 * @param name �û���
	 * @return ����
	 */
	public int getCountByLname(String lname);
	
	/**
	 * ͨ��ID��ѯ�û���Ϣ
	 * @param id IDֵ
	 * @return ʵ��
	 */
	public SysUser getById(int id);
	
	/**
	 * �޸��û�����
	 * @param su ʵ��
	 * @return true || false
	 */
	public boolean upd(SysUser su);
	
	/**
	 * ͨ��IDɾ���û���Ϣ
	 * @param id DIֵ
	 * @return true || false
	 */
	public boolean del(int id);
	
	/**
	 * �����ļ���Ӧ����Ϣ
	 * @param sysuserid �û�ID
	 * @param fileName �ϴ�����ļ�����
	 * @return true || false
	 */
	public boolean addFileInfo(int sysuserid,String fileName);
	
	/**
	 * ͨ���û�ID��ѯ�û���Ӧ�ļ�������Ϣ
	 * @param id �û�ID
	 * @return ����
	 */
	public List<SysFile> getSysFileByUserid(int id);
	
	/**
	 * ɾ���ļ���Ϣ
	 * @param id �ļ�id
	 * @return true || false
	 */
	public boolean delSysFileById(int id);
}
