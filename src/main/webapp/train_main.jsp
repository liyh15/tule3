<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>火车票网页</title>
	<link rel="stylesheet" href="css/train.css">
	<link rel="stylesheet" href="css/title_menu.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/train.js"></script>		
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
		       <a class="head_left_b" href="exit?view=train_main">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
			<ul>
				<li><a href="user/mainView.do" class="head_right">途乐首页</a></li>
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
		<div id="header1">
			<div id="form">
				<form action="trainseacher" method="post">
					<div id="top"><span id="s1"><a href="#">国内火车票</a></span><span id="s2"><a href="#">汽车票</a></span><span id="s3"><a href="#">租车</a></span></div>
					<hr id="hr">
					<input type="text" placeholder="请输入城市" id="p1" name="startArea"><br>
					<input type="text" placeholder="请输入城市" id="p2" name="endArea"><br>
					<img src="image/huan1.jpg" id="huan" onclick="huanfn()">
					<input type="date" placeholder="请选择时间" name="date"><br>
					<div class="all">仅搜索高铁和动车
					<div class="div1 open1">
    					<span class="left"></span>
    					<span class="right"></span>
    					<div class="div2 open2"></div>
					</div>
					</div>
					<input type="submit" value="搜索列车" id="search" >
				</form>
			</div>
		</div>
	</header>
	<article>
		<div id="article">
			<div id="article-top">
				<span class="art_s1">热门火车票</span>
				<a href="#"><span class="art_s2">上海</span></a>
				<a href="#"><span class="art_s3">北京</span></a>
				<a href="#"><span class="art_s3">杭州</span></a>
				<a href="#"><span class="art_s3">广州</span></a>
				<a href="#"><span class="art_s3">南京</span></a>
				<a href="#"><span class="art_s3">武汉</span></a>
				<a href="#"><span class="art_s3">西安</span></a>
			</div>
			<div id="article-context">
				<div class="square">
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
				</div>
				<div class="square">
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
				</div>
				<div class="square">
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
				</div>
				<div class="square">
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
					<a href="#"><p>上海-北京<span><i>￥</i>177.5<em>起</em></span></p></a>
				</div>
			</div>
		</div>
	</article>
	<footer>
		<div id="footer">
			<h2>常见问题</h2>
			<div id="question">
				<div class="square">
					<h3>身份验证</h3>
           			2014年新规：从未在网络购票的用户，请先携身份证去火车站专门窗口核验身份证信息，核验通过后才能网上购票。
				</div>
				<div class="square">
					<h3>取票方式</h3>
           			 若您使用二代居民身份证预订火车票产品可凭预订时所使用的乘车人证件到车站售票窗口、铁路客票代售点或车站自动售票机上办理换票手续...
           			 <a href="#">更多>></a>
				</div>
				<div class="square">
					<h3>如何退票</h3>
           			预订成功后，如未取票且离产品内显示的火车发车时间大于1小时30分钟，您可在线申请退票。预订成功后，如已取票或离产品内显示的火车发车时间小于1小时30分钟...
           			 <a href="#">更多>></a>
				</div>
				<div class="square">
					<h3>如何改签</h3>
           			 预订成功后，如需办理订单内的车票改签，您须在换取纸质车票后携带预订时所使用的乘车人有效身份证件原件，在列车开车前前往车站改签窗口办续...
           			 <a href="#">更多>></a>
				</div>
			</div>
		</div>
	</footer>
</body>
	<script type="text/javascript" src="js/head_menu.js"></script>
	<script type="text/javascript">
	     var line="${line}";
	     if(line!=null || line!="")
	    	 {
	    	    alert(line);
	    	 }
	</script>
</html>