package com.beans.vo;

//用来描述通用通知模型
public class NoticeVO {
	private boolean success;//成功与否
	private String msg;//通知内容
	private Object obj;//通知实体	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
