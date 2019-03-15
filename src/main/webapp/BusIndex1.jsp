<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>汽车票</title>
<link rel="stylesheet" href="css/bus.css">
<link rel="stylesheet" href="css/title_menu.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/busindex.js"></script>
</head>
<body>
	<div id="head">
		<div id="head_one">
			<c:choose>
		    <c:when test="${empty user}">
		       <a href="login.jsp" class="head_left">登录|</a> <a href="login.jsp" class="head_left">注册</a>
		    </c:when>
		    <c:otherwise>		    
		       <a class="head_left_b">账号:</a> <a class="head_left_b">${user.phone}</a>
		       <a class="head_left_b" href="exit?view=BusIndex1">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
			<ul>
				<li><a href="main.jsp" class="head_right">途乐首页</a></li>
				<li><a href="orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="orderlist" class="head_right my_order">我的订单</a>
					<ul class="menu_list">
						<li><a href="person.jsp">全部订单</a></li>
						<li><a href="#">火车票订单</a></li>
						<li><a href="#">飞机票订单</a></li>
						<li><a href="#">汽车票订单</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div id="search">
		<div id="search_one">
			<img src="image/logo.png" id="logo"> 
			<img src="image/title.png"id="title"> 
			<input type="text" id="search_input"> 
			<a href="#" id="search_button">
			     <img src="image/search.png">
			</a>
			<div id="tel">
				<img src="image/tel.png">
				<span class="tel_a">24h客户服务电话</span>
				<span class="tel_b">157-2078-6592</span>
			</div>
		</div>
	</div>
	<div id="menu">
	    <div id="menu_list">
	       <ul>
	          <li><a href="main.jsp">首页</a></li>
	          <li><a href="air.jsp">机票</a></li>
	          <li><a href="train_main.jsp">火车票</a></li>
	          <li><a href="BusIndex1.jsp">汽车票</a></li>
	       </ul>
	    </div>
	</div>
	<div id="header">
		<div id="header_left">
			<img alt="" src="image/one.jpg">		
		</div>
		<div id="header_right">
			<ul>
				<li><img src="image/city.png">&nbsp<span class="header_sp_left">1000</span><span class="header_sp_right">余个出发地点</span></li>
				<li><img src="image/part.png">&nbsp<span class="header_sp_left">50万</span><span class="header_sp_right">余条车次线路</span></li>
				<li><img src="image/road.png">&nbsp<span class="header_sp_left">300万</span><span class="header_sp_right">余班次数据</span></li>
			</ul>	
		</div>
	</div>
	<nav>
		<div id="nav_left">
			<div id="nav_left1">
				<span>汽车票</span>&nbsp&nbsp<a href="#"><span>火车票</span></a>	
			</div>
			<br>
			<form action="#" method="get">
			<div id="nav_left2">
			
				<span class="form-infos" class="departure">出发城市</span><span class="form-field "class="form-fields"><input type="text" id="in1" name="start"></span><br>
				<span class="form-infos" class="departure">到达城市</span><span class="form-field "class="form-fields"><input type="text" id="in2" name="direct"></span><br>
				<span class="form-infos" class="departure">出发日期</span><span class="form-field "class="form-fields"><input type="date" name="date"></span><br>
				<input type="button" value="搜索" onclick="location='BusList1.jsp'">
			</div>
			</form>
			<div id="changeImg">
				<img src="image/bus_change.png" class="change" onclick="fn1()">
				<img src="image/figure.png" >
				<img src="image/changeHover.png" alt style="display:none;">
			</div>
		</div>
		<div id="nav_right">
			<img alt="" src="image/bus1.PNG" id="img1">
			<img alt="" src="image/bus1.PNG" id="img1">
			<img alt="" src="image/bus1.PNG" id="img1">
		</div>
		
	</nav>
	<article>
		<div id="art_top">
			<span class="art_span1"><img src="image/hot.png">热门汽车票</span>
			&nbsp&nbsp&nbsp&nbsp
			<a href="#"><span class="art_span2">重庆</span></a>
			<a href="#"><span class="art_span2">南京</span></a>
			<a href="#"><span class="art_span2">上海</span></a>
			<a href="#"><span class="art_span2">成都</span></a>
			<a href="#"><span class="art_span2">郑州</span></a>
			<a href="#"><span class="art_span2">长沙</span></a>
			<a href="#"><span class="art_span2">苏州</span></a>
			<a href="#"><span class="art_span2">西安</span></a>
		</div>
		<div id="art_bottom">
			<a href="#"><span id="one" class="art_bot_sp" onclick="fn2()">重庆-大促</span></a><span class="art_bot_span">￥2</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-合川</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥4</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥5</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥6</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥2</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-合川</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥4</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥5</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥6</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
			
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥2</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-合川</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥4</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥5</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥6</span><span class="art_bot_sp2">起</span>
			<a href="#"><span class="art_bot_sp">重庆-大促</span></a><span class="art_bot_span">￥3</span><span class="art_bot_sp2">起</span>
		</div>
	</article>
	
	<footer>
		<div id="footer_top">
		<span>
		<img src="image/commonQuestion.png"></span><span class="h2">常见问题</span></div>
		<div id="foorer_center">
			<div class="foot_1">
				<div class="foot_2">票类型限制</div>
				<div class="foot_3">目前支持预订成人全价汽车票，暂不能购买免票（携儿童）、儿童票、学生票、优待票等。</div>
			</div>
			<div class="foot_1">
				<div class="foot_2">购票是否成功</div>
				<div class="foot_3">预订成功后，途牛旅游网将为您进行出票，出票成功后或出票失败有短信提醒，请及时查收短信。</div>
			</div>
			<div class="foot_1">
				<div class="foot_2">取票方式</div>
				<div class="foot_3">凭取票号码/取票密码/身份证等凭证（参照客运站具体规定）到车站售票窗口/ 自助取票机取票上车。</div>
			</div>
			<div class="foot_1">
				<div class="foot_2">退改签</div>
				<div class="foot_3">出票成功的客运票订单，途牛暂不支持网站和客服电话退票或改签。城际巴士票订单可支持发车前退票，退票收取部分退票手续费，具体细则请在订单填写页查看预订协议。</div>
			</div>
		</div>
	</footer>
</body>
</html>