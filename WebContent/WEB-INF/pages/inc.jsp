<!-- 引入jquery的文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.min.js"></script>

<!-- 引入jquery的form插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>

<%
	String easyuiThemeName="default";
%>
<!-- 引入easyui的样式表 -->
<link id="easyuiTheme" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/easyui/themes/<%=easyuiThemeName%>/easyui.css" ></link>
<!-- 引入easyui的图标导航 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css" ></link>
<!-- 引入easyui的文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入easyui的中文语言包，如果不导入，自动引入所有的语言包 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sysutil.js"></script>

<!-- easyui tree的扩展 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/extEasyUI.js"></script>