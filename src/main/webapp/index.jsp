<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>ç½ç«åå°ç®¡çæ¨¡ç</title>
		<link rel="stylesheet" type="text/css" href="static/admin/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="static/admin/css/admin.css"/>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--ä¾§è¾¹æ -->
			<div class="main-layout-side">
				<div class="m-logo">
				</div>
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				  <li class="layui-nav-item layui-nav-itemed">
				    <a href="javascript:;"><i class="iconfont">&#xe607;</i>火车车次管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="menu1.html" data-id='1' data-text="åå°èå"><span class="l-line"></span>åå°èå</a></dd>
				      <dd><a href="javascript:;" data-url="menu2.html" data-id='2' data-text="åå°èå"><span class="l-line"></span>åå°èå</a></dd>
				    </dl>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe608;</i>火车行程管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="article-list.html" data-id='3' data-text="æç« ç®¡ç"><span class="l-line"></span>æç« ç®¡ç</a></dd>
				      <dd><a href="javascript:;" data-url="danye-list.html" data-id='9' data-text="åé¡µç®¡ç"><span class="l-line"></span>åé¡µç®¡ç</a></dd>
				    </dl>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe604;</i>行程线路管理</a>
				  </li>
				   <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe60c;</i>火车行程安排</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="email.html" data-id='4' data-text="é®ä»¶ç³»ç»"><i class="iconfont">&#xe603;</i>é®ä»¶ç³»ç»</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe60d;</i>çæéæ</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe600;</i>å¤ä»½ç®¡ç</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="ä¸ªäººä¿¡æ¯"><i class="iconfont">&#xe606;</i>ä¸ªäººä¿¡æ¯</a>
				  </li>
				  <li class="layui-nav-item">
				  	<a href="javascript:;" data-url="system.html" data-id='6' data-text="ç³»ç»è®¾ç½®"><i class="iconfont">&#xe60b;</i>ç³»ç»è®¾ç½®</a>
				  </li>
				</ul>
			</div>
			<div class="main-layout-container">
				<!--å¤´é¨-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a href="javascript:;" data-url="email.html" data-id='4' data-text="é®ä»¶ç³»ç»"><i class="iconfont">&#xe603;</i></a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="ä¸ªäººä¿¡æ¯">è¶çº§ç®¡çå</a>
					  </li>
					  <li class="layui-nav-item"><a href="javascript:;">éåº</a></li>
					</ul>
				</div>
				<!--ä¸»ä½åå®¹-->
				<div class="main-layout-body">
					<!--tab åæ¢-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">åå°ä¸»é¡µ</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="welcome.jsp" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
					    	<!--1end-->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<!--é®ç½©-->
			<div class="main-mask">
				
			</div>
		</div>
		<script type="text/javascript">
			var scope={
				link:'./welcome.jsp'
			}
		</script>
		<script src="static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
		
	</body>
</html>
