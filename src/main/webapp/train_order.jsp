<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>火车票订单界面</title>
		<link rel="stylesheet" href="css/trainorder.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/title_menu.css" />
	</head>
	<body>
	   <div id="head">
		<div id="head_one">
		<c:choose>
		    <c:when test="${empty user}">
		       <a href="login.do?view=main" class="head_left">登录|</a> <a href="login.do?method=register&view=main" class="head_left">注册</a>
		    </c:when>
		    <c:otherwise>		    
		       <a class="head_left_b">账号:</a><a class="head_left_b">${user.phone}</a>
		       <a class="head_left_b" href="../exit?view=main">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
		    
			<ul>
				<li><a href="mainView.do" class="head_right">途乐首页</a></li>
				<li><a href="../orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="../orderlist" class="head_right my_order">我的订单</a>
					<ul class="menu_list">
						<li><a href="orderlist">全部订单</a></li>
						<li><a href="#">火车票订单</a></li>
						<li><a href="#">飞机票订单</a></li>
						<li><a href="#">汽车票订单</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div id="search">
		<div id="search_one">
			<img src="${pageContext.request.contextPath }/image/logo.png" id="logo"> 
			<img src="${pageContext.request.contextPath }/image/title.png"id="title"> 
			<input type="text" id="search_input"> 
			<a href="#" id="search_button">
			     <img src="${pageContext.request.contextPath }/image/search.png">
			</a>
			<div id="tel">
				<img src="${pageContext.request.contextPath }/image/tel.png">
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
	          <li><a href="trainView.do">火车票</a></li>
	          <li><a href="BusIndex1.jsp">汽车票</a></li>
	       </ul>
	    </div>
	</div>	
		<div id="main">
			<div class="top">
				<span>你的位置：</span>
				<a href="#"><span class="sp">首页</span></a>
				<span class="sp1">></span>
				<a href="#"><span class="sp">会员中心</span></a>
				<span class="sp1">></span>
				<a href="#"><span class="sp1">我的订单</span></a>
			</div>
			<div class="handle">
				<div class="handle_top">
					<img src="image/train_handle.jpg" style="width: 996px;">
					<span class="clz"><img src="image/train_cl.jpg">待付款</span>
					<span class="dfk1">已付款</span>
					<span class="dfk">出票成功</span><br>
					<span class="date">2018-10-09</span>
					<span class="time">19:17:27</span>
				</div>
				<div class="handle_bottom">
					<span class="dqzt">当前状态：</span><span class="zt">未付款</span>
					<span class="ddje">订单金额：</span><span class="money">￥56.5</span>
					<span class="jemx">金额明细</span><br>
					<span class="fkdjs">付款倒计时：</span><span class="sjian" style="color: red;">28分57秒</span>
					<span class="sjian">，请在剩余时间内完成付款，逾期将自动取消</span><br>
					<span class="zysx">途乐不保证一定购票成功哦，但若购票失败，票款会立即退回</span>
					<div class="qfk">去付款</div>
					<span class="zysx1">（正常当天退款到账，最迟7个工作日）。</span>
					<div class="qxdd">取消订单</div>
				</div>
			</div>
			<div class="ccinformation">
				<div class="ccxx">车次信息</div>
				<div class="information">
					<span class="tou">K1511</span>
					<div class="start">
						<span class="start_time">00:46</span><br>
						<span class="start_station">南京站</span><br>
						<span class="start_date">2018-10-16</span>
					</div>
					<div class="all_time">
						<span class="alltime">3小时35分钟</span><br>
						<img src="image/train_dao.jpg">
					</div>
					<div class="end">
						<span class="end_time">04:21</span><br>
						<span class="end_station">上海南站</span><br>
						<span class="end_date">2018-10-16</span>
					</div>
				</div>
			</div>
			<div class="personinformation">
				<div class="ckxx">乘客信息</div>
				<div class="allinformation">
					<span class="ck" style="margin-left: 20px;">乘客</span><span class="pz" style="margin-left: 63px;">票种</span>
					<span class="zjlx" style="margin-left: 50px;">证件类型</span><span class="zjhm" style="margin-left: 50px;">证件号码</span>
					<span class="zw" style="margin-left: 80px;">座位</span><span class="bx" style="margin-left: 30px;">保险</span>
					<span class="bbr" style="margin-left: 110px;">被保人</span><span class="bdh" style="margin-left: 50px;">保单号</span>
				</div>
				<div class="information">
					<span class="ck" style="margin-left: 20px;">李元浩</span><span class="pz1" style="margin-left: 47px;">成人票</span>
					<span class="zjlx1" style="margin-left: 39px;">身份证</span><span class="zjhm1" style="margin-left: 62px;">3****************8</span>
					<span class="zw1" style="margin-left: 19px;">硬座</span><span class="bx1" style="margin-left: 29px;">途乐火车意外伤害险</span>
					<span class="bbr1" style="margin-left: 12px;">李元浩</span><span class="bdh1"></span>
				</div>
			</div>
			<div class="ddinformation">
				<div class="ddxx">订单信息</div>
				<div class="ddxxinformation">
					<span style="margin-left: 20px;">订单</span><span style="margin-left: 70px;">下单时间</span>
					<span style="margin-left: 86px;">取票方式</span><span style="margin-left: 30px;">车票总价</span>
					<span style="margin-left: 20px;">保险</span><span style="margin-left: 37px;">铁路客票代售费</span>
					<span style="margin-left: 30px;">配送费</span><span style="margin-left: 20px;">订单总价</span>
					<span style="margin-left: 20px;">优惠金额</span><span style="margin-left: 20px;">支付总额</span>
					<span style="margin-left: 20px;">退还金额</span>
				</div>
				<div class="fkinformation">
					<span style="margin-left: 20px;">1154575774</span><span style="margin-left: 21px;">2018-10-09 19:17:26</span>
					<span style="margin-left: 15px;">自取票</span><span style="margin-left: 45px;">￥46.5</span>
					<span style="margin-left: 38px;">1x￥10</span><span style="margin-left: 20px;">￥0x1</span>
					<span style="margin-left: 96px;">￥0</span><span class="money1">￥56.5</span>
					<span class="youhui">￥0</span><span class="money2">￥56.5</span><span class="money3">-</span>
				</div>
			</div>
			<div class="phone">
				<div class="lxrdh">联系人电话：</div><span class="mobile">15720786592</span>
			</div>
		</div>
	</body>
</html>