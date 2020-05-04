<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- 引入公用资源文件 -->
<%@ include file="../inc.jsp" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<style type="text/css">
	.mytable{
		width: 500px;
		font-size: 15px;
		border-collapse: collapse;
	}
	.mytable tr,.mytable td{
		border: 1px solid black;
		padding: 5px;
	}
	.mytable .myimg{
		width: 50px;
		height: 50px;
	}
	
	.mytable .mybuttonrm{
		width: 30px;
	}
	.mytable .mybuttonrm:hover{
		cursor: hand;
	}
	
	.mytable .mybuttondl{
		width:30px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		datagridInit();
	}); 
	
	/*数据表格加载函数*/
	function datagridInit(){
		$('#tab1').datagrid({
			url:'${pageContext.request.contextPath}/api/sysuser/getByPage',
			method:"GET",
			rownumbers:true,
			pagination:true,
			singleSelect:true,
			columns:[[
				{field:'id',title:'主键'},
				{field:'lname',title:'登录名称'},
				{field:'lpass',title:'登录密码'},
				{field:'rname',title:'真实姓名'},
				{field:'birthday',title:'出生日期'},
				{field:'phone',title:'联系方式'},
				{field:'address',title:'地址'},
				{field:'dept',title:'部门',formatter: function(value,row,index){
					return value.name;}
				},
				{field:'null',title:'操作',formatter:function(value,row,index){
					var str='';
					
					str='<a href="javascript:updDataBind('+row.id+');">修改</a>';
					str+='&nbsp;';
					str+='<a href="javascript:del('+row.id+');">删除</a>';
					str+='&nbsp;';
					str+='<a href="javascript:uploadFile('+row.id+');">文件管理</a>';
					
					return str;}
				}
			]],
			toolbar:[
				{
					iconCls: 'icon-add',
					text:'新增用户信息',
					handler: addDialog
				}
			]
		});
	}
		
	/*新增用户窗口操作函数*/
	function addDialog(){
		//绑定用户名检查事件
		$('#addform #lname').on('blur',function(){
			var url='${pageContext.request.contextPath}/api/sysuser/getCountByLname';
			var params={lname:$('#addform #lname').val()};
			
			$.getJSON(url,params,function(data){
				if(data>0){
					$.messager.alert('提示','该用户名已经存在!','warning');
					$('#addform #lname').val('');
				}
			});
		});
		
		//打开新增窗口之前，先调用公用js工具绑定部门下拉框
		var url='${pageContext.request.contextPath}/api/sysdept/getAll';
		bindCombox($('#addform #dept'),url,{k:"id",v:"name"},-1);
		
		//打开新增窗口
		$('#adddialog').dialog('open');
	}
	
	/*待修改数据的绑定*/
	function updDataBind(sysuserid){
		//查询待修改数据
		var url1='${pageContext.request.contextPath}/api/sysuser/getById';
		var params={id:sysuserid};
		$.getJSON(url1,params,function(data){
			$('#updform [name=id]').val(data.id);
			$('#updform [name=lname]').val(data.lname);
			$('#updform [name=lpass]').val(data.lpass);
			$('#updform [name=rname]').val(data.rname);
			$('#updform [name=phone]').val(data.phone);
			$('#updform [name=address]').val(data.address);
			//特殊绑定日期控件
			$('#updform #birthday').datebox('setValue',data.birthday);			
			//特殊绑定部门
			var url2='${pageContext.request.contextPath}/api/sysdept/getAll';
			bindCombox($('#updform #dept'),url2,{k:"id",v:"name"},data.dept.id);
		});
		
		//打开修改窗口
		$('#upddialog').dialog('open');
	}
	
	/*删除数据的操作*/
	function del(sysuserid){
		$.messager.confirm('提示', '确定删除该笔资料?', function(r){
			if (r){
				var url='${pageContext.request.contextPath}/api/sysuser/del';
				var params={id:sysuserid};
				
				$.getJSON(url,params,function(data){
					if(data.success){
						$('#tab1').datagrid('reload');
					}
					
					$.messager.show({
						title:'消息',
						msg:data.msg
					});
				});
			}
		});
	}
	
	/*文件窗口操作*/
	function uploadFile(sysuserid){
		$('#fileform [name=sysuserid]').val(sysuserid);
		
		//构建已上传文件展示内容
		createFileContent(sysuserid);
		
		//打开文件管理窗口
		$('#filedialog').dialog('open');
	}
	
	/*构建已上传文件的展示*/
	function createFileContent(sysuserid){
		var url='${pageContext.request.contextPath}/api/sysuser/getSysFileByUserId';
		var params={id:sysuserid};
		var htmlContent='';
		
		$.getJSON(url,params,function(data){
			htmlContent='<table class="mytable">';
			
			
			$.each(data,function(i,v){
				htmlContent+='<tr>';				
				htmlContent+='<td>';				
				htmlContent+='<img src="/upload/'+v.fileName+'" class="myimg"/>';
				htmlContent+='<input type="hidden" name="imgid" value="'+v.id+'"/>';
				htmlContent+='<input type="hidden" name="imgname" value="'+v.fileName+'"/>';
				htmlContent+='</td>';
				
				htmlContent+='<td><img src="${pageContext.request.contextPath}/resources/img/remove.jpg" class="mybuttonrm" name="rmbutton"></td>';
				htmlContent+='<td><a href="${pageContext.request.contextPath}/api/sysuser/download?fileName='+v.fileName+'">';
				htmlContent+='<img src="${pageContext.request.contextPath}/resources/img/download.jpg" class="mybuttondl">';
				htmlContent+='</a></td>';
				htmlContent+='</tr>';
			});
			
			htmlContent+='</table>';			
			
			$('#fileContent').html(htmlContent);			
			
			//删除图片事件加载
			createRemoveButtonEvent();
		});
		
	}
	
	//构建删除按钮处理事件
	function createRemoveButtonEvent(){
		$('.mytable img[name=rmbutton]').on('click',function(){
			//取得隐藏域中的待下载图片名称
			var trObj=$(this).parent().parent();
			var fileName=trObj.first().find('[name=imgname]').val();
			var fileId=trObj.first().find('[name=imgid]').val();
			
			
			$.messager.confirm('提示', '确定删除该文件?', function(r){
				if(r){					
					var url='${pageContext.request.contextPath}/api/sysuser/delSysFileById';
					var params={id:fileId,fileName:fileName};
					
					$.getJSON(url,params,function(data){
						
						if(data.success){
							trObj.remove();
						}
						
						$.messager.show({
							title:'消息',
							msg:data.msg
						});
						
					});
				}
			});
			
		});
	}
	
	
</script>
<style type="text/css">
	#addform table:first-child tr td:nth-child(1) {
		text-align:right;
	}
</style>
</head>
<body>
	<table id="tab1"></table>
	
	<!-- 新增窗口 -->
	<div id="adddialog" class="easyui-dialog" title="新增用户信息" style="width:400px;height:300px;"
	    data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
	    buttons:[
	    	{
	    		text:'确认新增' ,
	    		iconCls:'icon-add',
	    		handler:function(){
	    			$('#addform').form('submit',{
	    				url:'${pageContext.request.contextPath}/api/sysuser/add',
	    				onSubmit:function(){
	    					//return false;//阻止提交
	    				},
	    				success:function(data){
	    					//参数data是后台返回的json字符串，所以需要进行转换为json对象
	    					var jsonObj=JSON.parse(data);
	    					
	    					if(jsonObj.success){
	    						//清空form表单
	    						$('#addform').form('clear');
	    						
	    						//关闭新增窗口
	    						$('#adddialog').dialog('close');
	    						
	    						//刷新数据表格
	    						$('#tab1').datagrid('reload');
	    					}
	    					
	    					//提示
	    					$.messager.show({
								title:'提示',
								msg:jsonObj.msg
							});
	    				}
	    			});
	    		}
	    	}
	    ]">
	    <form id="addform" method="post">
	    	<table>
	    		<tr>
	    			<td>用户名：</td>
	    			<td><input type="text" name="lname" id="lname"/></td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td><input type="text" name="lpass"/></td>
	    		</tr>
	    		<tr>
	    			<td>真实姓名：</td>
	    			<td><input type="text" name="rname"/></td>
	    		</tr>
	    		<tr>
	    			<td>电话：</td>
	    			<td><input type="text" name="phone"/></td>
	    		</tr>
	    		<tr>
	    			<td>地址：</td>
	    			<td><input type="text" name="address"/></td>
	    		</tr>
	    		<tr>
	    			<td>生日：</td>
	    			<td>
						<input name="birthday" type="text" class="easyui-datebox" required="required">
					</td>
	    		</tr>
	    		<tr>
	    			<td>部门：</td>
	    			<td>
	    				<input id="dept" name="dept.id">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 修改窗口 -->
	<div id="upddialog" class="easyui-dialog" title="修改用户信息" style="width:400px;height:300px;"
	    data-options="iconCls:'icon-edit',resizable:false,modal:true,closed:true,
	    buttons:[
	    	{
	    		text:'确认修改' ,
	    		iconCls:'icon-edit',
	    		handler:function(){
	    			$('#updform').form('submit',{
	    				url:'${pageContext.request.contextPath}/api/sysuser/upd',
	    				onSubmit:function(){
	    					//return false;//阻止提交
	    				},
	    				success:function(data){
	    					//参数data是后台返回的json字符串，所以需要进行转换为json对象
	    					var jsonObj=JSON.parse(data);
	    					
	    					if(jsonObj.success){
	    						//清空form表单
	    						$('#updform').form('clear');
	    						
	    						//关闭新增窗口
	    						$('#upddialog').dialog('close');
	    						
	    						//刷新数据表格
	    						$('#tab1').datagrid('reload');
	    					}
	    					
	    					//提示
	    					$.messager.show({
								title:'提示',
								msg:jsonObj.msg
							});
	    				}
	    			});
	    		}
	    	}
	    ]">
	    <form id="updform" method="post">
	    	<table>
	    		<tr>
	    			<td>用户名：</td>
	    			<td>
	    				<input type="hidden" name="id" />
	    				<input type="text" name="lname" disabled="disabled"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td><input type="text" name="lpass"/></td>
	    		</tr>
	    		<tr>
	    			<td>真实姓名：</td>
	    			<td><input type="text" name="rname"/></td>
	    		</tr>
	    		<tr>
	    			<td>电话：</td>
	    			<td><input type="text" name="phone"/></td>
	    		</tr>
	    		<tr>
	    			<td>地址：</td>
	    			<td><input type="text" name="address"/></td>
	    		</tr>
	    		<tr>
	    			<td>生日：</td>
	    			<td>
						<input id="birthday" name="birthday" type="text" class="easyui-datebox" required="required">
					</td>
	    		</tr>
	    		<tr>
	    			<td>部门：</td>
	    			<td>
	    				<input id="dept" name="dept.id">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 文件管理窗口 -->
	<div id="filedialog" class="easyui-dialog" title="用户文件处理" style="width:600px;height:400px;"
	    data-options="iconCls:'icon-edit',resizable:false,modal:true,closed:true,
	    buttons:[
	    	{
	    		text:'确认上传' ,
	    		iconCls:'icon-edit',
	    		handler:function(){
	    			$('#fileform').ajaxSubmit({
	    				type:'post',
	    				url:'${pageContext.request.contextPath}/api/sysuser/upload',	    				
	    				success:function(data){
	    					//参数data是后台返回的json字符串，所以需要进行转换为json对象
	    					var jsonObj=JSON.parse(data);
	    					
	    					if(jsonObj.success){
	    						//清空form表单
	    						$('#fileform').form('clear');	    						
	    					}
	    					
	    					//提示
	    					$.messager.show({
								title:'提示',
								msg:jsonObj.msg
							});
	    				}
	    			});
	    		}
	    	}
	    ]">
	    <!-- 展示已上传文件内容 -->
	    <div id="fileContent"></div>
	    <form id="fileform" enctype="multipart/form-data">
	    	<table>
	    		<tr>
	    			<td>文件1：</td>
	    			<td>
	    				<input type="hidden" name="sysuserid" />
	    				<input type="file" name="myfiles" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>文件2：</td>
	    			<td>
	    				<input type="file" name="myfiles" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>文件3：</td>
	    			<td>
	    				<input type="file" name="myfiles" />
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
</body>
</html>