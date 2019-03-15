<%@page import="service.OrderService"%>
<%@page import="java.util.List"%>
<%@page import="entity.Order"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tedu.cn/mytag" prefix="d" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>订单详情页</title>
	<link rel="stylesheet" type="text/css" href="css/person.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/person.js"></script>
</head>
<body>
	<header>
		
	</header>
	<main>
		<div id="navigation">
			<a href="#" id="icon">途乐</a>
			<ul>
				<li><a href="main.jsp">会员首页</a></li>
				<li class="second"><a>账户设置</a>
					<ul>
						<li><a target="person_detail.jsp">个人资料</a></li>
						<li><a target="#">站内信息</a></li>
						<li><a target="setting.jsp">密码设置</a></li>
						<li><a target="security.jsp">账户安全</a></li>
						<li><a target="#">我的银行卡</a></li>
						<li><a target="#">常用旅客信息</a></li>
						<li><a target="#">常用配送地址</a></li>
						<li><a target="#">常用发票信息</a></li>
					</ul>
				</li>
				<li class="second"><a href="#">社区互动</a>
					<ul>
						<li><a target="#">我的主页</a></li>
						<li><a target="#">发表游记</a></li>
						<li><a target="#">我的游记</a></li>
					</ul>
				</li>
				<li><a href="#">会员俱乐部</a></li>
				<li><a href="#">积分商城</a></li>
			</ul>
			<a href="user/mainView.do" id="back">返回途乐首页</a>
		</div>
		
		<div id="left_navigation">
			<ul>
				<li class="second" onclick="showSecond()">我的订单
					<ul>
						<li><a href="#">旅游订单</a></li>
						<li><a href="#">旅游券订单</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">我的资产
					<ul>
						<li><a href="#">优惠券</a></li>
						<li><a href="#">旅游券</a></li>
						<li><a href="#">抵用券</a></li>
						<li><a href="#">第三方券</a></li>
						<li><a href="#">我的银行卡</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">个人中心
					<ul>
						<li><a href="#">个人资料</a></li>
						<li><a href="#">安全资料</a></li>
						<li><a href="#">密码设置</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">常用信息
					<ul>
						<li><a href="#">游客信息</a></li>
						<li><a href="#">常用发票信息</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">常用工具
					<ul>
						<li><a href="#">我的收藏</a></li>
						<li><a href="#">我的点评</a></li>
					</ul>
				</li>
				<li><a href="#">社区互动</a></li>
				<li><a href="#">会员俱乐部</a></li>
			</ul>
		</div>
		<div id="left_bottom_navigation">
			<span>快捷服务</span><br>
			<a href=""><img alt="" src="image/prove.png"></a>
			<a href=""><img alt="" src="image/letter.png"></a>
			<a href=""><img alt="" src="image/bind.png"></a>
			<a href=""><img alt="" src="image/present.png"></a>
		</div>
		
		<div id="right">
			<div id="right_navigation">
				<ul>
					<li id="all_order" onclick="change()">全部订单</li>
					<li onclick="change()">待付款(0)</li>
					<li onclick="change()">待出行/收货(0)</li>
					<li onclick="change()">待点评(0)</li>
				</ul>
			</div>
			<div id="right_bottom_navigation">
				<ul>
					<li class="title">产品信息</li>
					<li>
						<select name="" id="order">
							<option value="" selected="selected">全部订单</option>
							<option value="">订单1</option>
							<option value="">订单2</option>
							<option value="">订单3</option>
						</select>
					</li>
					<li class="type">车票类型</li>
					<li class="number">数量</li>
					<li class="remain-time">时间</li>
					<li class="price">订单金额</li>
					<li class="status">状态</li>
					<li class="operation">操作</li>
				</ul>
			</div>
			<div id="ticket">
				<c:choose>
					<c:when test="${empty orderList }">
						<h3>未添加订单</h3>
					</c:when>
					<c:otherwise>
						<c:forEach items="${orderList }" var="order" varStatus="orders">
							<div class="single_ticket">
								<div>
									<span class="ticket_date">下单时间: ${order.created }</span>
									<span class="ticket_id">订单号: ${order.id}</span>
								</div>
								<span class="obligation">${order.status}</span>
								<div class="single_ticket_content">
									<img src="image/bus.png">
									<span class="ticket_trip">${order.trainName}</span>
									<span class="ticket_type">${order.type }</span>
									<span class="ticket_num">
									<%
										Order o = (Order)pageContext.getAttribute("order");
										Integer[] passengers = o.getPassengerId();
										int size = passengers.length;
										out.println(size);
									%>
									张</span>
									<span class="trip_date">2018-10-08 10:00:00</span>
									<span class="price">
									￥ <d:mySplit ex=":" params="${order.totlePrice }"/>
									</span>
									<span class="remain-time">
										<c:choose>
											<c:when test="${order.status == '已付款' }">
												--
											</c:when>
											<c:when test="${order.status == '已取消' }">
												--
											</c:when>
											<c:otherwise>
												00:19
											</c:otherwise>
										</c:choose>
									</span>
									<a href=""><span class="operation">去付款</span></a>
								</div>
								<div>
									<a href=""><span class="ticket_detail">订单详情</span></a>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
	</main>
	<!-- <footer>
		<div id="footer">
			<div>24小时客户服务电话(免长途费): 4007-999-999 呼叫中心位于南京 来电统一显示为025-54088686</div>
			<div>
				<a href=""><span>关于我们</span></a>
				<a href=""><span>Investor Relations</span></a>
				<a href=""><span>联系我们</span></a>
				<a href=""><span>投诉建议</span></a>
				<a href=""><span>广告服务</span></a>
				<a href=""><span>旅游券</span></a>
				<a href=""><span id="red">招聘</span></a>
				<a href=""><span>隐私保护</span></a>
				<a href=""><span>免责声明</span></a>
				<a href=""><span>用户协议</span></a>
				<a href=""><span>网站地图</span></a>
			</div>
			<div>
				<a href="#"><span>团队介绍</span></a>
				<a href="#"><span>网站联盟</span></a>
				<a href="#"><span>帮助中心</span></a>
			</div>
		</div>
	</footer> -->
</body>
</html>