<%@page import="entity.TrainArrange"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>火车票详情页</title>
<link rel="stylesheet" href="css/trainall.css">
<link rel="stylesheet" href="css/title_menu.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
</head>
<body>
    <%
        List<TrainArrange> trainArranges=(List<TrainArrange>)session.getAttribute("trainArranges");
    %>
	<div id="head">
		<div id="head_one">
			<c:choose>
		    <c:when test="${empty user}">
		       <a href="login.jsp" class="head_left">登录|</a> <a href="login.jsp" class="head_left">注册</a>
		    </c:when>
		    <c:otherwise>		    
		       <a class="head_left_b">账号:</a> <a class="head_left_b">${user.phone}</a>
		       <a class="head_left_b" href="exit?view=train_detail">退出登录</a>
		    </c:otherwise>		
		 </c:choose>
			<ul>
				<li><a href="main.jsp" class="head_right">途乐首页</a></li>
				<li><a href="orderlist" class="head_right">我的途乐</a></li>
				<li class="menu"><a href="orderlist"
					class="head_right my_order">我的订单</a>
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
			<img src="image/logo.png" id="logo"> <img src="image/title.png"
				id="title"> <input type="text" id="search_input"> <a
				href="#" id="search_button"> <img src="image/search.png">
			</a>
			<div id="tel">
				<img src="image/tel.png"> <span class="tel_a">24h客户服务电话</span>
				<span class="tel_b">157-2078-6592</span>
			</div>
		</div>
	</div>
	<div id="menu">
		<div id="menu_list">
			<ul>
				<li><a href="main.jsp">首页</a></li>
				<li><a href="#">机票</a></li>
				<li><a href="train_main.jsp">火车票</a></li>
				<li><a href="#">汽车票</a></li>
			</ul>
		</div>
	</div>
	<div class="wrap" id="wrap">
		<div class="top">
			<div class="crumbs">
				<p>
					<input type="hidden" id="md"> <a href="#">首页 &gt;</a> <a
						href="#">火车票 &gt;</a> <span id="J_Crumb">上海到南京火车票</span>
				</p>
			</div>

			<div class="pkg">
				<div id="J_PkgInfo" class="pkg_info_detail">
					<div data-type="TK" class="address">
						<div class="pkg_info_item">
							<div class="pkg_from">
								<div class="pkg_placholder">出发地</div>
								<input id="start" name="start"
									class="hide_placeholder input_addr" type="text"
									value="${trainArranges[0].startStation}" />
							</div>
							<i class="ticketbtn" onclick="huanfn()"></i>
							<div class="pkg_to">
								<div class="pkg_placholder">目的地</div>
								<input id="dest" name="start"
									class="hide_placeholder input_addr" type="text"
									value="${trainArranges[0].endStation}" />
							</div>
							<div class="pkg_date">
								<!-- <div class="pkg_placholder">今天 出发</div>
                        <input name="start_time" type="text" class="input_date" readonly="readonly"/> -->
								<input type="date" placeholder="时间"
									value="${trainArranges[0].date}">
							</div>
							<div class="pkg_button" id="J_SearchBtn">
								<a href="#">搜索列车</a>
							</div>
							<div class="APP_buy">
								<ul class="APP_buy_icon">
									<li class="nav_item"><i class="header_icon"></i></li>
								</ul>
								<span class="APP_buy_title" onmouseover="moveon()"
									onmouseout="remove()">APP购票专享优惠</span> <span class="APP_arrow"
									onmouseover="moveon()" onmouseout="remove()">▼</span>
								<div class="APP_buy_detail" style="display: none;">
									<img src="image/ewm.jpg" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="pkg_main">
			<div class="flight" style="display: block;">
				<span>更多特价机票></span>
			</div>
			<div class="re-flight" style="display: block;">
				<span class="flight-title">
					<div class="flight-icon"></div>
					<div class="flight-name">特价机票</div>
				</span> <span class="flight-date">
					<div class="startDate">10-05</div>
				</span> <span class="flight-city">
					<div class="orgcity">${trainArranges[0].startStation}</div>
					<div class="arrow"></div>
					<div class="dstcity">${trainArranges[0].endStation}</div>
				</span> <span class="flight-price">
					<div class="logo">￥</div>
					<div class="price">410</div>
					<div class="start">起</div>
				</span> <span class="flight-btn">
					<div class="btn">查看</div>
				</span>
			</div>
			<div class="pkg_main_inner" style="display: block;">
				<div class="pkg_filter">
					<div class="pkg_filter_con" style="display: block;">
						<div class="place">
							<h1 style="float: left;">
								<em id="startcity">${start}</em> → <em id="endcity">${end}</em>
							</h1>
							<span class="pkg_startdate">${trainArranges[0].date}</span> <span> （共<em
								id="alltrain"><%=trainArranges.size()%></em>个车次）
							</span>
						</div>
						<div class="pkg_date_select">
							<div class="pkg_date_prev">
								<a href="#"> <i class="icon"></i>
								</a>
							</div>
							<div class="pkg_date_list">
								<ul class="clearfix" style="left: 0px;">
									<li class="pkg_date_single current"><a href="#"> <strong>10-05</strong>
											<span>周五</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-06</strong>
											<span>周六</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-07</strong>
											<span>周日</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-08</strong>
											<span>周一</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-09</strong>
											<span>周二</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-10</strong>
											<span>周三</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-11</strong>
											<span>周四</span>
									</a></li>
									<li class="pkg_date_single"><a href="#"> <strong>10-12</strong>
											<span>周五</span>
									</a></li>
								</ul>
							</div>
							<div class="pkg_date_next">
								<a href="#"> <i class="icon"></i>
								</a>
							</div>
							<div class="pkg_date_calendar">
								<a href="#" class="pkg_date_calendar_btn"> <i class="icon"></i>
									<span>日历</span>
								</a>
							</div>
						</div>
						<div class="pkg_filter_item" style="height: 308px;">
							<dl>
								<dt>车型</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a>不限</a>
									</div>
									<div class="pkg_filter_others">
										<input type="checkbox" class="icon" id="gt"><label
											for="gt">G-高铁</label>
										<input type="checkbox" class="icon" id="dc"><label
											for="dc">D-动车</label>
										<input type="checkbox" class="icon" id="zd"><label
											for="zd">Z-直达</label>
										<input type="checkbox" class="icon" id="pk"><label
											for="pk">K-普快</label>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>座席</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="swz"><label
											for="swz">商务座</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="ydz"><label
											for="ydz">一等座</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="edz"><label
											for="edz">二等座</label>
										</a> <a href="#"> <input type="checkbox" class="icon"
											id="gjrw"><label for="gjrw">高级软卧</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="rw"><label
											for="rw">软卧</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="yw"><label
											for="yw">硬卧</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="yz"><label
											for="yz">硬座</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="dw"><label
											for="dw">动卧</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>出发车站</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="shhq"><label
											for="shhq">上海虹桥</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="shn"><label
											for="shn">上海南</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="sh"><label
											for="sh">上海</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>到达车站</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="hz"><label
											for="hz">杭州</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="hzd"><label
											for="hzd">杭州东</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>出发时段</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="sz"><label
											for="sz">18-24点</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>到达时段</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="zs"><label
											for="zs">0-6点</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="sx"><label
											for="sx">18-24点</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>是否始发</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="#" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a href="#"> <input type="checkbox" class="icon" id="sf"><label
											for="sf">始发</label>
										</a> <a href="#"> <input type="checkbox" class="icon" id="gl"><label
											for="gl">过路</label>
										</a>
									</div>
								</dd>
							</dl>
							<a class="slidesign" style="display: none;" onclick="sign()">更多筛选条件▼</a>
							<a class="slideup" style="display: block;" onclick="up()">收起▲</a>
						</div>
					</div>
					<div class="pkg_filter_sort" style="display: block;">
						<div class="pkg_sort">
							<a href="#"
								class="pkg_sort_item pkg_sort_item_0 pkg_sort_item_id_0">车次</a>
							<a href="#"
								class="pkg_sort_item pkg_sort_item_1 pkg_sort_item_id_1 desc">
								发时<i class="icon"></i>
							</a> <a href="#"
								class="pkg_sort_item pkg_sort_item_2 pkg_sort_item_id_2"> 到时<i
								class="icon"></i>
							</a> <a class="pkg_sort_item pkg_sort_item_5 pkg_sort_item_id_5">出发/到达车站</a>
							<a href="#"
								class="pkg_sort_item pkg_sort_item_3 pkg_sort_item_id_3">
								运行时间<i class="icon"></i>
							</a> <a href="#"
								class="pkg_sort_item pkg_sort_item_4 pkg_sort_item_id_4">
								参考票价<i class="icon"></i>
							</a> <a class="pkg_sort_item pkg_sort_item_0 pkg_sort_item_id_0">剩余票数</a>
							<a href="#"
								class="pkg_sort_item pkg_sort_item_6 pkg_sort_item_id_6"> <input
								type="checkbox" class="icon" id="zxs"><label for="zxs">只显示有票车次</label>
							</a>
						</div>
					</div>
				</div>
				<div class="pkg_list" style="display: block;">
					<div class="pkg_list_con">
						<c:forEach items="${trainArranges}" var="train" varStatus="tt">
							<div class="tk_item_wrap">
								<div class="J_PkgList pkg_list_item pkg_list_ticket clearfix">
									<div class="checi">
										<span>${train.trainName}</span>
										<br><a 
											class="J_PlaneExpand tn_fontface stopping stopp">经停站 <span>▼</span>
										</a>
									</div>
									<div class="time">
										<span class="startTime change1">${train.startTime}</span> <br>
										<span class="endTime"> <span class="change2">${train.endTime}</span>
										</span>
									</div>
									<div class="station">
										<span class="departStation"> <i class="icon-0"></i>${train.startStation}
										</span> <br> <span class="destStation"> <i class="icon-2"></i>${train.endStation}
										</span>
									</div>
									<div class="duration change3">${train.totalTime}</div>
									<div class="price">

										<c:forEach items="${train.trainSeats}" var="t" varStatus="ts">

											<dl class="tk_seat_disabled">
												<dd class="J_Sleeper">${t.seatType}</dd>
												<dt>
													<c:if test="${t.count==0}">
														<span class="pri pricecolor2">￥${t. price}</span>
													</c:if>
													<c:if test="${t.count!=0}">
														<span class="pri pricecolor1">￥${t. price}</span>
													</c:if>
													<c:choose>
														<c:when test="${t.count==0}">
															<span class="left">余${t.count}张</span>
															<span class="pkg_item_btn"> <span> <a
																	href="#" class="J_PkgItemSelectBtn btn_grab">抢票</a>
															</span>
															</span>
														</c:when>
														<c:when test="${t.count>5}">
															<span class="left">有票</span>
															<span class="pkg_item_btn"> <span> <a
																	href="writeorder?arrangeid=${tt.getIndex()}&seatid=${ts.getIndex()}" class="J_PkgItemSelectBtn btn">预定</a>
															</span>
															</span>
														</c:when>
														<c:otherwise>
															<span class="left"><a class="leftLess">票量紧张</a></span>
															<span class="pkg_item_btn"> <span> <a
																	href="writeorder?arrangeid=${tt.getIndex()}&seatid=${ts.getIndex()}" class="J_PkgItemSelectBtn btn">预定</a>
															</span>
															</span>
														</c:otherwise>
													</c:choose>
												</dt>
											</dl>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="stopover">
								<table>
									<tr>
										<td>站名</td>
										<td>到达时间</td>
										<td>开车时间</td>
										<td>停靠时间</td>
									</tr>
									<tr>
										<c:forEach items="${train.stopOverSations}" var="sta">
											<tr>
												<td>${sta.station}</td>
												<td>${sta.arriveTime}</td>
												<td>${sta.startTime}</td>
												<td>${sta.stopTime}</td>
											</tr>
										</c:forEach>
									</tr>
								</table>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class=""></div>
</body>
<script type="text/javascript" src="js/head_menu.js"></script>
<script type="text/javascript" src="js/trainall.js"></script>
</html>