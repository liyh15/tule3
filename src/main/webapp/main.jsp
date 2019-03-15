<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/title_menu.css" />
<link type="text/css" rel="styleSheet" href="css/MainView.css">
</style>
</head>
<body>
	<div id="head">
		<div id="head_one">
		<c:choose>
		    <c:when test="${empty user}">
		       <a href="login.jsp?view=main" class="head_left">登录|</a> <a href="login.jsp" class="head_left">注册</a>
		    </c:when>
		    <c:otherwise>		    
		       <a class="head_left_b">账号:</a><a class="head_left_b">${user.phone}</a>
		       <a class="head_left_b" href="exit?view=main">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
		    
			<ul>
				<li><a href="main.jsp" class="head_right">途乐首页</a></li>
				<li><a href="orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="orderlist" class="head_right my_order">我的订单</a>
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

	<div id="body">
		<div id="body_menu">
			<ul>
				<li><a href="orderlist">
						<div>
							<img src="image/plane2.png"></br>我的订单
						</div>
				</a></li>
				<li><a href="train_main.jsp">
						<div>
							<img src="image/train2.png"></br>火车票
						</div>
				</a></li>
				<li><a href="air.jsp">
						<div class="plane">
							<img src="image/plane2.png"></br> 飞机票
						</div>
				</a></li>
				<li><a href="BusIndex1.jsp">
						<div>
							<img src="image/plane2.png"></br>汽车票
						</div>
				</a></li>
				<li><a href="person.jsp">
						<div>
							<img src="image/plane2.png"></br>我的途乐
						</div>
				</a></li>
			</ul>
		</div>
		<div id="main_image">
			<img src="image/main.png">
		</div>
	</div>
	<div class="foot">
		<span><img src="image/hot.png"></span>热门汽车票 <span>
			<ul class="city">
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
			</ul>
		</span>
	</div>
	<div class="foot">
	     <ul class="foot_list">
	        <li>
	           <ul>
	               <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	           <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	            <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	       <li>
	            <ul>
	                 <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	     </ul>
	</div>
	
	
	<div class="foot">
		<span><img src="image/hot.png"></span>热门飞机票 <span>
			<ul class="city">
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
			</ul>
		</span>
	</div>
	<div class="foot">
	     <ul class="foot_list">
	        <li>
	           <ul>
	               <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	           <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	            <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	       <li>
	            <ul>
	                 <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	     </ul>
	     <div class="foot">
		<span><img src="image/hot.png"></span>热门火车票 <span>
			<ul class="city">
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
				<li><a href="#">重庆</a></li>
			</ul>
		</span>
	</div>
	<div class="foot">
	     <ul class="foot_list">
	        <li>
	           <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	           <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	        <li>
	            <ul>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	       <li>
	            <ul>
	                 <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	                <li><a href="#">重庆-大足<span class="money">￥2</span><span>元</span></a></li>
	           </ul>
	        </li>
	     </ul>
	</div>
	</div>	
</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/MainView.js"></script>
<script type="text/javascript" src="js/head_menu.js"></script>
</html>