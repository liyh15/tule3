<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="css/air.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/head_menu.js"></script>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/air.js"></script>
</head>
<body>
	<header>
		
<div id="head">
		<div id="head_one">
			<c:choose>
		    <c:when test="${empty user}">
		       <a href="login.jsp" class="head_left">登录|</a> <a href="login.jsp" class="head_left">注册</a>
		    </c:when>
		    <c:otherwise>		    
		       <a class="head_left_b">账号:</a> <a class="head_left_b">${user.phone}</a>
		       <a class="head_left_b" href="exit?view=air">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
			<ul>
				<li><a href="main.jsp" class="head_right">途乐首页</a></li>
				<li><a href="person.jsp" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="orderlist" class="head_right my_order">我的订单</a>
					<ul class="menu_list">
						<li><a href="#" >全部订单</a></li>
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
	          <li><a href="main.jsp" class="shou">首页</a></li>
	          <li><a href="air.jsp" class="a">机票</a></li>
	          <li><a href="train_main.jsp" class="a">火车票</a></li>
	          <li><a href="BusIndex1.jsp" class="a">汽车票</a></li>
	       </ul>
	    </div>
	</div>
	</header>
	<article>
		<div id="carousel">
			<div id="img_body">
				<img src="image/1.jpg" id="img" >
				<div id="count">
					<span class="cir" style="background-color:#FF8800 "  onclick="fn(1)"></span>
					<span class="cir"  onclick="fn(2)"></span>
					<span class="cir"  onclick="fn(3)"></span>
					<span class="cir"  onclick="fn(4)"></span>
					<span class="cir"  onclick="fn(5)"></span>
					<span class="cir"  onclick="fn(6)"></span>
				</div>
			</div>
			<form action="" method="post" id="form_panel">
				<div id="form_header">
					<img src="image/airicon.png" id="iconimg">
					<span id="header_title">国内机票查询</span>
				</div>
				<div id="voyagetype">
					<span>乘航类型：</span>
					<div id="button">
						<input type="button" value="单程" id="oneway_button" class="btn" onclick="oneway()" style="margin-top: 0px;">
						<input type="button" value="往返" id="toway_button" class="btn" onclick="toway()"  style="margin-top: 0px;">
					</div>	
				</div>
				
				<span>出发城市：</span><input type="text" name="decity" id="decity">
				<span style="margin-left: 35px;">出发日期：</span><input type="date" name="dedate"  
				 id="dedate"><br>
				<span>到达城市：</span><input type="text" name="arrcity" id="arrcity">
				<span style="margin-left: 35px;">返回日期：</span><input type="date" name="redate" id="redate" ><br>
				<img src="image/huan.png" id="huan" 	onclick="huanfn()">
				<span>乘客：</span>
				<select name="adultnum">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
					<option>9</option>
				</select>
				<select name="childnum" style="margin-left: 70px;">
					<option>1</option>
					<option>2</option>
				</select>
				<span style="width:200px;">儿童（2-12周岁） 儿童/婴儿票</span>
				<span id="warn" onmouseover="warnfn()" onmouseout="removewarn()">!</span>
				<div id="wrancontent">
					<p style="text-align: center;"><strong>儿童/婴儿票说明</strong></p>
					<p style="text-align: left;"><strong>儿童票购买说明（按航班起飞当日的实际年龄计算）</strong> </p>
					<p style="text-align: left;">1. 2周岁（含）-12周岁（不含）可购买儿童票</p>
					<p style="text-align: left;">2. 儿童购票可使用身份证、户口簿、护照等，具体以航班和舱位要求为准</p>
					<p style="text-align: left;">3. 儿童乘机须有成人陪同，1名成人最多携带2名儿童</p>
					<p style="text-align: left;">4. 儿童单独乘机请联系航空公司购票</p>
					<p style="text-align: left;"><strong>婴儿票购买说明（按航班起飞当日的实际年龄计算）</strong> </p>
					<p style="text-align: left;">1. 足月婴儿出生满14天且未满2周岁可购买婴儿票，早产婴儿出生满90天且未满2周岁可购买婴儿票</p>
					<p style="text-align: left;">2. 婴儿购票可使用身份证、户口簿、护照、出生证明等，具体以航班和舱位要求为准</p>
					<p style="text-align: left;">	3. 婴儿乘机须有成人陪同，1名成人最多携带1名婴儿</p>
					<p style="text-align: left;">4. 婴儿不单独占座</p>
					<p style="text-align: left;">5. PC暂不支持购买婴儿票，请至途牛app购买</p>
				</div>
				<span>舱位：</span>
				<select name="berth" style="width: 100px;">
					<option selected="selected">不限</option>
					<option>经济舱</option>
					<option>超级经济舱</option>
					<option>公务舱</option>
					<option>头等舱</option>
				</select>
				<br>
				<input type="submit" value="搜索" id="seacher">
			</form>
		</div>

		<div id="special_offer">
		<div id="special_title">超值特价</div>
		<div id="city">
			<div id="special_title">南京出发</div>
			<div id="specail_body">
				<ul>
					<li><a href=""><span class="special">南京-昆明</span> <strong >￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul id="last">
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>
			</div>
		</div>

		<div id="_area">
			<div id="special_title">航司专区</div>
			<div id="specail_body">
				<ul>
					<li class="body_title">中国南方航空</li>	
					<li><a href=""><span class="special">南京-昆明</span> <strong >￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul>
					<li class="body_title">中国东方航空</li>	
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul>
					<li class="body_title">南海航空</li>	
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>

				<ul id="last">
					<li class="body_title">中国国际航空</li>	
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					<li><a href=""><span class="special">南京-昆明</span> <strong>￥200</strong>起 </a></li>
					
				</ul>
			</div>
		</div>

	</div>
	</article>
</body>
</html>