<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>火车票详情</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/busindex.js"></script>
<link href="css/busList.css" rel="stylesheet">
<link href="css/title_menu.css" rel="stylesheet">
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
		       <a class="head_left_b" href="exit?view=BusList1">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
			<ul>
				<li><a href="main.jsp" class="head_right">途乐首页</a></li>
				<li><a href="orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="orderlist" class="head_right my_order">我的订单</a>
					<ul class="menu_list">
						<li><a href="#">全部订单</a></li>
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
	<header>
		<form action="">
			<div class="header1">
				<div class="one">出发</div>
				<div class="one1"><input type="text" name="start" id="in11"></div>
			</div>
			<div class="header2">
				<div class="one">到达</div>
				<div class="one1"><input type="text" name="dir" id="in22"></div>
			</div>
			
			<div class="header3">
				<div class="one11">出发日期</div>
				<div class="one1"><input type="date" name="date"></div>
			</div>
			<div class="header4">
				<div><a href="#"><img src="image/12.png" onclick="fn2()"></a></div>
			</div>
			<div class="header5"><input type="submit" value="搜索"></div>
		</form>
	</header>
	<nav>
		<div id="nav_top">
			<div class="nav1 nav_top1"><img alt="" src=""></div>
			<div class="nav1 nav_top2" onclick="fn1(this)">星期1</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期2</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期3</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期4</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期5</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期6</div>
			<div class="nav1 nav_top2" onclick="fn1()">星期7</div>
			<div class="nav1 nav_top1"><img alt="" src=""></div>
		</div>
	</nav>
	<div id="nav_top3">
			<div><span class="two">南京</span><span>-</span><span class="two">仪征</span><span class="two1">共</span><span class="two2">20</span><span>条车次</span></div>
			<div><span class="thr">出发时段</span><span><img class="twoo" src="image/13.png"></span>
			<span class="thr"><input type="checkbox">凌晨（00:00-06:00）</span>
			<span class="thr"><input type="checkbox">上午（06:00-12:00）</span>
			<span class="thr"><input type="checkbox">下午（12:00-18:00）</span>
			<span class="thr"><input type="checkbox">晚上（18:00-24:00）</span>
			</div>
			
			<div><span class="thr">出发车站</span><span><img class="twoo1" src="image/13.png"></span>
			<span class="thr"><input type="checkbox">南京北站</span>
			<span class="thr"><input type="checkbox">南京客运南站</span>
			<span class="thr"><input type="checkbox">南京长途汽车东站</span>
			<span class="thr"><input type="checkbox">南京葛塘站</span>
			</div>
			<div><span class="thr">到达车站</span><span><img class="twoo2" src="image/13.png"></span>
			<span class="thr"><input type="checkbox">仪征</span>
			</div>
			<article>
				<div class="arc"><span>发车时间</span></div>
				<div><img src=""></div>
				<div><img src=""></div>
				<div class="arc"><span>发车站/到车站</span></div>
				<div class="arc"><span>车型</span></div>
				<div class="arc"><span>票价</span></div>
				<div><img src=""></div>
				<div><img src=""></div>
			</article>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
				<div id="artc">
					<div><span>06:20</span></div>
					<div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
						<div><span><img src=""></span><span>南京汽车客运南站</span></div>
					</div>
					<div><span>大型高一</span></div>
					<div><span class="four">￥25</span></div>
					<div><span><input class="five" type="submit" value="预订"></span></div>
				</div>
			
		</div>
	
	<footer></footer>
</body>
</html>