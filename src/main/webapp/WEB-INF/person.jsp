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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/person.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>

</head>
<body>
	<header>
		
	</header>
	<main>
		<div id="navigation">
			<a href="#" id="icon">途乐</a>
			<ul>
				<li><a href="user/mainView.do">途乐首页</a></li>
				<li class="second"><a>账户设置</a>
					<ul>
						<li><a target="person_detail.jsp">个人资料</a></li>
						<li><a target="setting.jsp">密码设置</a></li>
						<li><a target="security.jsp">账户安全</a></li>
					</ul>
				</li>
				<li class="second"><a href="#">社区互动</a>
					<ul>
						<li><a target="#">我的主页</a></li>
						<li><a target="#">发表游记</a></li>
						<li><a target="#">我的游记</a></li>
					</ul>
				</li>
			</ul>
			<a href="user/mainView.do" id="back">返回途乐首页</a>
		</div>
		
		<div id="left_navigation">
			<ul>
				<li class="second" onclick="showSecond()">我的订单
					<ul>
						<li><a href="orderlist">旅游订单</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">个人中心
					<ul>
						<li><a target="person_detail.jsp">个人资料</a></li>
						<li><a target="setting.jsp">密码设置</a></li>
						<li><a target="security.jsp">账户安全</a></li>
					</ul>
				</li>
				<li class="second" onclick="showSecond()">常用工具
					<ul>
						<li><a href="#">我的点评</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div id="left_bottom_navigation">
			<span>快捷服务</span><br>
			<a href=""><img alt="" src="${pageContext.request.contextPath }/image/prove.png"></a>
			<a href=""><img alt="" src="${pageContext.request.contextPath }/image/letter.png"></a>
			<a href=""><img alt="" src="${pageContext.request.contextPath }/image/bind.png"></a>
			<a href=""><img alt="" src="${pageContext.request.contextPath }/image/present.png"></a>
		</div>
		
		<div id="right">
			<div id="right_navigation">
				<ul>
					<li id="all_order" onclick="change(0)">全部订单</li>
					<li onclick="change(1)">待付款</li>
					<li onclick="change(2)">待出行</li>
					<li onclick="change(3)">待点评</li>
				</ul>
			</div>
			<div id="right_bottom_navigation">
				<ul>
					<li class="title">产品信息</li>
					<li class="type">车票类型</li>
					<li class="number">数量</li>
					<li class="remainTime">时间</li>
					<li class="price">订单金额</li>
					<li class="status">状态</li>
					<li class="operat">操作</li>
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
									<span class="ticket_date">下单时间: ${order.createTime}</span>
									<span class="ticket_id">订单号:${order.id}</span>
								</div>
								<div class="single_ticket_content">
									<img src="${pageContext.request.contextPath }/image/bus.png">
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
									<span class="trip_date">${order.createTime}</span>
									<span class="price">
									￥ <d:mySplit ex=":" params="${order.totlePrice }"/>
									</span>
									<span style = "display:none">${order.id}</span>
									<span class="remain-time">
										${order.status}
									</span>
									<c:if test="${order.status == '未付款'}">
									   <a href=""><span aotopostback = "false" class="operation" oid="${order.id}">去付款</span></a>
									</c:if>
									<c:if test="${order.status != '未付款'}">
									   <span class="deleteorder" oid="${order.id}">删除订单</span>
									</c:if>									
								</div>
								<div>
									<a href="order/orderDetailView.do?id=${order.id}"><span class="ticket_detail">订单详情</span></a>
								</div>	
							    
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
	</main>
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/person.js"></script>
</html>