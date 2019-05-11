<%@page import="entity.TrainArrange"%>
<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>火车票订单界面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/trainorder.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/title_menu.css" />	   
	</head>
	<body>
	     <%
			TrainArrange trainArrange = (TrainArrange)session.getAttribute("TrainArrange");
		    Order order = (Order)session.getAttribute("order");
		    String [] explain = order.getExplain();
		 %>
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
				<li><a href="../user/mainView.do" class="head_right">途乐首页</a></li>
				<li><a href="../orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="../orderlist" class="head_right my_order">我的订单</a>
					<ul class="menu_list">
						<li><a href="orderlist">全部订单</a></li>
						<li><a href="#">火车票订单</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div id="search">
		<div id="search_one">
			<img src="${pageContext.request.contextPath }/image/logo.png" id="logo"> 
			<img src="${pageContext.request.contextPath }/image/title.png"id="title"> 
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
	          <li><a href="../user/mainView.do">首页</a></li>
	          <li><a href="../user/trainView.do">火车票</a></li>
	       </ul>
	    </div>
	</div>	
		<div id="main">
			<div class="top">
				<span>你的位置：</span>
				<a href="../user/mainView.do"><span class="sp">首页</span></a>
				<span class="sp1">></span>
				<a href="../orderlist"><span class="sp">会员中心</span></a>
				<span class="sp1">></span>
				<a href="../orderlist"><span class="sp1">我的订单</span></a>
			</div>
			<div class="handle">
				<div class="handle_top">
					<img src="${pageContext.request.contextPath }/image/train_handle.jpg" style="width: 996px;">
					<span class="clz"><img src="${pageContext.request.contextPath }/image/train_cl.jpg">待付款</span>
					<span class="dfk1">已付款</span>
					<span class="dfk">出票成功</span><br>
					<span class="date">2018-10-09</span>
					<span class="time">19:17:27</span>
				</div>
				<div class="handle_bottom">
					<span class="dqzt">当前状态：</span><span class="zt">${order.status}</span>
					<span class="ddje">订单金额：</span><span class="money">
					     ￥ <%
					        String[] totalPrice = order.getTotlePrice();
					        Integer p = 0;
					        for(String price : totalPrice){
					        	p += Integer.parseInt(price.split(":")[1]);
					        }
					        out.print(p);
					    %>
					</span>
					<span style = "color:red;font-size:15px;display:none;margin-left:100px;cursor:pointer" class = "returnTicket">退票</span>
					<span style = "color:red;font-size:15px;display:none;margin-left:20px;cursor:pointer" class = "changeTicket">改签</span>	
					<span><input type = "date" class = "changedate" style = "display:none"/><input type = "button" class = "putchange" value = "确定" style = "display:none"></span>				
<br>
					<span class="fkdjs">付款倒计时：</span><span class="sjian" style="color: red;"></span>
					<span class="sjiann">请在剩余时间内完成付款，逾期将自动取消</span><br>
					<span class="zysx">途乐不保证一定购票成功哦，但若购票失败，票款会立即退回</span>
					<div class="qfk">去付款</div>
					<span class="zysx1">（正常当天退款到账，最迟7个工作日）。</span>
					<div class="qxdd">取消订单</div>
				</div>
			</div>
			<div class="ccinformation">
				<div class="ccxx">车次信息</div>
				<div class="information">
					<span class="tou"><%
					out.print(trainArrange.getTrainName());
					%></span>
					<div class="start">
						<span class="start_time">
						<%
					       out.print(trainArrange.getStartTime());
					    %>
						</span><br>
						<span class="start_station">
						<%
					       out.print(trainArrange.getStartStation());
					    %>
						</span><br>
						<span class="start_date">
						<%
					       out.print(trainArrange.getDate());
					    %>
						</span>
					</div>
					<div class="all_time">
						<span class="alltime">
						<%
					       out.print(trainArrange.getTotalTime());
					    %>
						</span><br>
						<img src="${pageContext.request.contextPath }/image/train_dao.jpg">
					</div>
					<div class="end">
						<span class="end_time"><%
					       out.print(trainArrange.getEndTime());
					    %></span><br>
						<span class="end_station">
						<%
					       out.print(trainArrange.getEndStation());
					    %></span><br>
						<span class="end_date">
						<%
					       out.print(trainArrange.getEndDate());
					    %></span>
					</div>
				</div>
			</div>
			<div class="personinformation">
				<div class="ckxx">乘客信息</div>
				<div class="allinformation">
					<span class="ck" style="margin-left: 20px;">乘客</span><span class="pz" style="margin-left: 63px;">票种</span>
					<span class="zjlx" style="margin-left: 60px;">证件类型</span><span class="zjhm" style="margin-left: 95px;">证件号码</span>
					<span class="zw" style="margin-left: 86px;">座位</span><span class="bx" style="margin-left: 85px;">保险</span>
					<span class="bbr" style="margin-left: 110px;">被保人</span><span class="bdh" style="margin-left: 50px;">保单号</span>
				</div>
				<%int count = 0; %>
				<c:forEach items="${passengers}" var="pass" varStatus="tt">
				   <div class="information">
				    <div style="width: 65px">${pass.name}</div>
				    <div style="width: 120px">成人票</div>
				    <div style="width: 80px">${pass.type}</div>
				    <div style="width: 210px">${pass.personalId}</div>
				    <div style="width: 45px"><%=explain[count++].split(",")[0]%></div>
				    <div style="width: 171px">途乐火车意外伤害险</div>
				    <div style="width: 115px">${pass.name}</div>
				    <div></div>
				</div>
				</c:forEach>				
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
				    <div style="width: 65px">${order.id}</div>
				    <div style="width: 159px">${order.createTime}</div>
				    <div style="width: 114px">自取票</div>
				    <div style="width: 48px">￥<%=order.getTotlePrice()[0].split(":")[1]%></div>
				    <div style="width: 87px">1x￥10</div>
				    <div style="width: 103px">￥0x1</div>
				    <div style="width: 100px">￥0</div>
				    <div style="width: 23px">￥<%=order.getTotlePrice()[0].split(":")[1]%></div>
				    <div style="width: 133px">￥0</div>
				    <div style="width: 36px">￥<%=order.getTotlePrice()[0].split(":")[1]%></div>
				    <div style="width: 89px">-</div>
				</div>
			</div>
			<div class="phone">
				<div class="lxrdh">联系人电话：</div><span class="mobile">15720786592</span>
			</div>
		</div>
	</body>
	 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
	 <script type="text/javascript">
	 var time;
	 var ref;
	 $(function() {
			var timeClose="${timeClose}";
			var minate;
			var second;
			var date; // 订单的发车日期
			var myDate;
			if( $(".zt").text() == "未付款") {
				$(".returnTicket").css("display","none");
				$(".changeTicket").css("display","none");
				ref = setInterval(function(){
					myDate = (new Date()).valueOf();
					time = timeClose - myDate; // 获取与规定过期时间的时间差
					if(time > 0){
						minate = parseInt(time / 1000 / 60);
						second = parseInt((time - (minate * 60 * 1000)) / 1000);
		                 $(".sjian").text(minate+"分"+second+"秒");     
		                 if(minate == 0 && second == 0){
		                	 clearInterval(ref);
		                	 cancelOrder();
		                 }
					} else {
						 clearInterval(ref);
						 $(".zt").text("已取消");
						 $(".fkdjs").remove();
						 $(".sjian").remove();
						 $(".sjiann").remove();
						 $(".zysx").remove();
						 $(".qfk").remove();
						 $(".zysx1").remove();
						 $(".qxdd").remove();
					}		
				},500);
			} else {				
				 $(".returnTicket").css("display","inline-block");
				 $(".changeTicket").css("display","inline-block");
				 $(".fkdjs").remove();
				 $(".sjian").remove();
				 $(".sjiann").remove();
				 $(".zysx").remove();
				 $(".qfk").remove();
				 $(".zysx1").remove();
				 $(".qxdd").remove();
			}			
		});
	    
	    $(".qxdd").click(function(){
	    	cancelOrder();
	    });
	    
	    // 取消订单
	    function cancelOrder(){
	    	var orderId = "${order.id}";
	    	$.ajax({
	    		"url": "cancelOrder.do",
	    	    "data":"id="+orderId,
	    	    "dataType":"json",
	    	    "type":"post",
	    	    "success":function(data){
	    	    	  alert(data.message);
	    	    	  if(data.state == 200){
	    	    		     window.location.reload();
	    	    	  }                	
	    	    }
	    	});
	    }
	    
	    $(".returnTicket").click(function(){
	    	
	    	// 获取火车日期安排编号
	    	var orderId = "${order.id}";
	    	if(confirm("您确定要退票吗？")) {
	    		$.ajax({
		    		"url": "returnTicket.do",
		    	    "data":"id="+orderId,
		    	    "dataType":"json",
		    	    "type":"post",
		    	    "success":function(data){
		    	    	  alert(data.message);
		    	    	  if(data.state == 200){
		    	    		     window.location.reload();
		    	    	  }                	
		    	    }
		    	});
	    	}
	    });
	    
	    // 点击改签按钮
	    $(".changeTicket").click(function(){
	    	
	    	// 获取火车日期安排编号
	    	var orderId = "${order.id}";
	    		 
	    	$.ajax({
	    		"url": "getOrderDate.do",
	    	    "data":"id="+orderId,
	    	    "dataType":"json",
	    	    "type":"post",
	    	    "success":function(data){
	    	    	  if(data.state == 200){
	    	    		    date = data.param;
	    	    		    alert("请选择改签的日期，只能当前车次日期向后的日期");
	    	    	    	$(".changedate").css("display","inline-block");
	    	    	    	$(".putchange").css("display","inline-block");	 
	    	    	  } else {
	    	    		  alert(data.message);
	    	    	  }                	
	    	    }
	    	});
	    });
	    
	    // 点击改签确定按钮
	    $(".putchange").click(function(){
	    	
	    	// 获取火车日期安排编号
	    	var orderId = "${order.id}";
	    	var changeDate = $(".changedate").val();
	    	if(date == null || date == "") {
	    		alert("时间不能为空");
	    	} else {
	    		if(changeDate < date) {
	    			alert("选择的时间不能小于本车次的发车时间");
	    		} else {
	    			window.location.href="changeTicket.do?id="+orderId+"&date="+changeDate;
	    		}
	    	}
	    });
	 </script>
</html>