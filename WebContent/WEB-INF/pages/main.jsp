<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- 引入公用资源文件 -->
<%@ include file="inc.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
	#mynorth{
		background-color: #BCD2EE;
		text-align: right;
		width:100%;
		line-height: 70px;
		padding-right: 10px;
	}
	
	#mynorth a{
		text-decoration: none;
	}
</style>
<title></title>
<script type="text/javascript">	
	$(function(){
		treeInit();
	});
		
	//树菜单操作
	function treeInit(){		
		//给树菜单点击节点添加事件
		$('#tt').tree({
			url:'${pageContext.request.contextPath}/resources/tree.json',
			method:"GET",
			parentField:"pid",
			textFiled:"text",
			idFiled:"id",
			animate:true,
			onClick:function(node){		
				//打开新的tabs
				if($('#mytabs').tabs('exists',node.text)){
					//已经打开就选中
					$('#mytabs').tabs('select',node.text);
				}else{
					//新建tab
					$('#mytabs').tabs('add',{
						title:node.text,
						closable:true,
						content:'<iframe src="${pageContext.request.contextPath}/api/httpurl/go?page=/'+node.attributes.url+'" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
						tools:[{
							iconCls:'icon-mini-refresh',
							handler:function(){
								//刷新当前选项卡
								refreshTab(node.text,$('#mytabs'));
							}
					    }]
					});
				}
			}
		});
		
	}
	
	//刷新tabs
	function refreshTab(title,oTab){
		
		//开进度条
		$.messager.progress({
			text:'页面加载中...',
			interval:100
		});		
		
		//得到指定标题的选项卡
		var tabObj=oTab.tabs('getTab',title);
		oTab.tabs('update',{
			tab:tabObj,
			options:tabObj.panel('options')
		});
		
		//关进度条
		$.messager.progress('close');
	}
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'',split:true" style="height:70px;overflow: hidden;">
    	<div id="mynorth">
    		欢迎您，${sessionScope.loginuser.rname }&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/api/sysuser/exit">【安全退出】</a>
		</div>
    </div>
    <!-- <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div> -->
    <!-- <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div> -->
    <div data-options="region:'west',title:'功能区',split:true" style="width:180px;">
    	<ul id="tt"></ul>
    </div>
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    	<div id="mytabs" class="easyui-tabs" style="width:99%;">
		    <div title="首页">
				
				<h2>AI 还没让人类失业，搞 AI 的人先失业了</h2>
				<p>阿杰和在座的几位同学，虽然各个都是 AI 领域的精英，但和老王比起来还是逊色了很多。
				
				他们自己也是这么认为，觉得老王就是这家科技公司的船长，乃至国内人工智能领域的船长。
				
				但他们万万没想到，船长的船这么快翻了。作为初始团队成员也是一线骨干，他们其实早就发现了一些问题，但却忽视了问题的严重性。
				
				当年，阿杰还在 H 大读硕士时，发表的两篇关于 NNs 的论文，被老王看中。
				
				随即老王托关系联系到了阿杰，两人的结交对彼此的研究领域上都有了突破。这也是老王在回国创业时，马上招募阿杰成为合伙人的原因。
				
				对阿杰来说，认识老王有 3 年多的时间，老王是亦师亦友般的存在。
				
				公司创立时，最核心的产品是一款人脸识别系统，此系统的核心算法是老王的心血，阿杰和其他伙伴负责优化。
				
				历时 6 个月的研发和 4 个月的训练和优化，10个月后产品正式发布，引来业界瞩目。
				
				接踵而来的，便是资本的进入，公司的扩张，老王在公司的时间越来越少。
				
				产品研发的重担几乎全压在阿杰他们几个人身上，公司急缺人手，加快脚步招兵买马，招聘网站投广告，众多猎头举荐。
				
				AI 人才如雨后春笋般加入到这家公司，前途貌似一片光明。
				但时日不长，虽然引入这么多人才，但产品研发进度却越拖越久，最后都是勉强上线；Bug 也层出不穷，甚至有些产品根本无法正常使用。
				此时，销售上报了客户从不断投诉，到最终弃用的消息。老王那边又传来投资人将要撤资的消息，顿时一种大厦将倾的感觉袭来。
				阿杰深究代码发现，研发人员的代码质量糟糕得要命。这让他突然有了不安的想法，于是进行了更加深入调查，终于验证了他那不安的想法。
				裁员，这是老王和阿杰等人一致的意见，也是保住公司及时止损的最佳方案。
				至于裁员多少，阿杰只说了四个字：回到从前。
				老王虽然不敢相信要裁员这么多，但没多问，支持了阿杰的决定。
				裁员的消息不胫而走。
				人工智能没能让人类失业，搞人工智能的人先失业了。
				接下来的事情，如上面所说。
				12月25日，圣诞节的气氛隔绝在这家公司门前，老王阿杰等人在会议室里各自沉默。

				自从这家公司裁员、投资人撤资等消息爆出后，各家媒体转变风头，公司成为众矢之的。
				
				更有媒体为了博大众眼球，更是谣传各种王葱葱跑路的消息。
				
				“这里写我跑路的消息实在太夸张了。”老王哼笑了一下，把消息分享给群里。
				
				阿杰也看了消息，笑了笑。在座几位也都笑了。
				
				其实今天大家聚在这里，也是为了复盘反思这次创业大起大落的主要原因。
				
				深究各个问题，大家把公司和行业内外部形势都详细做了分析。
				
				老王和阿杰他们讨论了很久，有人觉得是投机的资本害了他们，有人觉得产品的商业模式很差。
				
				阿杰却觉得，人才质量是主要原因。他最后调查公司新招的一批人才，打着 AI 工程师的头衔，却没有任何项目经验，或学术经历。
				
				深究才发现，很多人都是「3 个月培训」出来的，培训机构和猎头包装一下，全都是人才。有人甚至连编程经验都不到 1 年。产品死在这些人手上。
				
				老王只觉得，自己有不可推脱的责任，创业时依靠自己掌握的技术来发展，没能让产品最终落地到商业场景，却一味地想要扩张。
				
				自己远离技术和产品，只顾在外应酬各种场面。是膨胀的自己，让公司崩塌。
				
				老王决定将公司留给阿杰等人打理，自己转而潜心于学术，用研发来支撑公司走下去。
				
				阿杰接手公司后，仍然从核心产品的优化做起，同时加强对商业模式的探索。
				
				1月10日，被限制高消费的王葱葱，索性哪也不去了。沉下心来，打开了许久没有上过的博客，写下了自己这两年创业的感受，和新的研发目标。
				
				技术领袖的认证称号重新点亮。
				
				老王觉得还是这个状态更适合自己。
			</p>
		    </div>		    
		</div>
    </div>
</body>
</html>