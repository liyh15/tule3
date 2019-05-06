<%@page import="entity.TrainArrange"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>火车票详情页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/trainall.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/title_menu.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
</head>
<body>
    <%
        List<TrainArrange> trainArranges=(List<TrainArrange>)session.getAttribute("trainArranges");
    %>
	<div class="wrap" id="wrap">
		<div class="pkg_main">
			<div class="pkg_main_inner" style="display: block;">
				<div class="pkg_filter">
					<div class="pkg_filter_con" style="display: block;">
						<div class="place">
							<h1 style="float: left;">
								<em id="startcity">${start}</em> → <em id="endcity">${end}(改签用途)</em>
							</h1>
							<span class="pkg_startdate">${trainArranges[0].date}</span> <span> （共<em
								id="alltrain"><%=trainArranges.size()%></em>个车次）
							</span>
						</div>
						<div class="pkg_filter_item" style="height: 308px;">
							<dl>
								<dt>车型</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a> <input type="checkbox" class="icon tn" id="G" ><label
											for="G">G-高铁</label>
										</a> <a > <input type="checkbox" class="icon tn" id="D" ><label
											for="D">D-动车</label>
										</a> <a > <input type="checkbox" class="icon tn" id="Z" ><label
											for="Z">Z-直达</label>
										</a> <a > <input type="checkbox" class="icon tn" id="K" ><label
											for="K">K-普快</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>座席</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a> <input type="checkbox" class="icon zw" id="swz"><label
											for="swz">商务座</label>
										</a> <a> <input type="checkbox" class="icon zw" id="ydz"><label
											for="ydz">一等座</label>
										</a> <a> <input type="checkbox" class="icon zw" id="edz"><label
											for="edz">二等座</label>
										</a> <a> <input type="checkbox" class="icon zw" id="rw"><label
											for="rw">软卧</label>
										</a> <a> <input type="checkbox" class="icon zw" id="yw"><label
											for="yw">硬卧</label>
										</a> <a> <input type="checkbox" class="icon zw" id="yz"><label
											for="yz">硬座</label>
										</a> <a> <input type="checkbox" class="icon zw" id="dw"><label
											for="dw">无座</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>出发车站</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
									    <c:forEach items="${trainArranges}" var="train" varStatus="tt">
											<a> <input type="checkbox" class="icon" ><label
											>${train.startStation}</label>
										</a> 
									    </c:forEach>
									   
									</div>
								</dd>
							</dl>
							<dl>
								<dt>到达车站</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										 <c:forEach items="${trainArranges}" var="train" varStatus="tt">
											<a> <input type="checkbox" class="icon" ><label
											>${train.endStation}</label>
										</a> 
									    </c:forEach>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>出发时段</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a> <input type="checkbox" class="icon gt" id="zs"><label
											for="zs">0-6点</label>
										</a> 
										<a> <input type="checkbox" class="icon gt" id="zs"><label
											for="zs">6-12点</label>
										</a>
										<a> <input type="checkbox" class="icon gt" id="zs"><label
											for="zs">12-18点</label>
										</a>
										<a> <input type="checkbox" class="icon gt" id="sx"><label
											for="sx">18-24点</label>
										</a>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>到达时段</dt>
								<dd class="pkg_filter_properties">
									<div class="pkg_filter_buxian">
										<a href="" class="checked">不限</a>
									</div>
									<div class="pkg_filter_others">
										<a> <input type="checkbox" class="icon at" id="zs"><label
											for="zs">0-6点</label>
										</a> 
										<a> <input type="checkbox" class="icon at" id="zs"><label
											for="zs">6-12点</label>
										</a>
										<a> <input type="checkbox" class="icon at" id="zs"><label
											for="zs">12-18点</label>
										</a>
										<a> <input type="checkbox" class="icon at" id="sx"><label
											for="sx">18-24点</label>
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
							<a 
								class="pkg_sort_item pkg_sort_item_0 pkg_sort_item_id_0">车次</a>
							<a
								class="pkg_sort_item pkg_sort_item_1 pkg_sort_item_id_1 desc">
								发时<i class="icon"></i>
							</a> <a 
								class="pkg_sort_item pkg_sort_item_2 pkg_sort_item_id_2"> 到时<i
								class="icon"></i>
							</a> <a class="pkg_sort_item pkg_sort_item_5 pkg_sort_item_id_5">出发/到达车站</a>
							<a
								class="pkg_sort_item pkg_sort_item_3 pkg_sort_item_id_3">
								运行时间<i class="icon"></i>
							</a> <a
								class="pkg_sort_item pkg_sort_item_4 pkg_sort_item_id_4">
								参考票价<i class="icon"></i>
							</a> <a class="pkg_sort_item pkg_sort_item_0 pkg_sort_item_id_0">剩余票数</a>
							<a
								class="pkg_sort_item pkg_sort_item_6 pkg_sort_item_id_6"> <input
								type="checkbox" class="icon onlyht" id="zxs"><label for="zxs">只显示有票车次</label>
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
										<br> <a 
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
																	 onclick = "changeTicket(${tt.getIndex()},${ts.getIndex()})" class="J_PkgItemSelectBtn btn">预定</a>
															</span>
															</span>
														</c:when>
														<c:otherwise>
															<span class="left"><a class="leftLess">票量紧张</a></span>
															<span class="pkg_item_btn"> <span> <a
																	 onclick = "changeTicket(${tt.getIndex()},${ts.getIndex()})" class="J_PkgItemSelectBtn btn">预定</a>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/head_menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/trainall.js"></script>
<script type="text/javascript">
    function changeTicket(indexOne,indexTwo) {
    	$.ajax({
	 		"url": "ticketChange.do",
	 		"data": "arrangeid="+indexOne+"&seatid="+indexTwo,
	 		"dataType":"json",
	 		"type":"post",     		
	 		"success":function(data){
	 			alert(data.message);
	 			window.history.back(-1); 
	 	}
		});	      
    }
</script>
</html>