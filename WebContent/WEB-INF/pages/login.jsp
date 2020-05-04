<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- 引入公用资源文件 -->
<%@ include file="inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录界面</title>
<style>
	#loginmsg{
		color:red;
	}
	#loginform p{
		margin-left:60px;
	}
</style>
</head>
<body>
	<div id="dd" class="easyui-dialog" title="登录" style="width:300px;height:190px;"
	    data-options="iconCls:'icon-save',resizable:false,modal:true,closable:false,draggable:false,
	    buttons:[
	    	{
	    		text:'登录',
				handler:function(){
					if($('#loginform [name=lname]').val()==''){
						$.messager.alert('警告','用户名不能为空!','warning');
						return;
					}else if($('#loginform [name=lpass]').val()==''){
						$.messager.alert('警告','密码不能为空!','warning');
						return;
					}
					
					//提交表单
					$('#loginform').submit();
				}
	    	},
	    	{
	    		text:'清空',
				handler:function(){
					$('#loginform').form('clear');
				}
	    	}
	    ]">	    
	    <form id="loginform" action="${pageContext.request.contextPath}/api/sysuser/login" method="POST">
	    	<span id="loginmsg">${requestScope.loginmsg }</span>
	    	<p>
	    		<input type="text" name="lname" placeholder="此处填写用户名" value="admin"/>
	    	</p>
	    	<p>
	    		<input type="password" name="lpass" placeholder="此处填写密码" value="abc"/>
	    	</p>
	    </form>
	</div>
</body>
</html>