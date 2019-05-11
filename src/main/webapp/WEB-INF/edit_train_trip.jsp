<%@page import="entity.StopOverSation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
#body {
	width: 1400px;
	height: 600px;
	margin: 0 auto;
}

tr td {
	text-align: center;
}
</style>
</head>
<body>
	<%
		Map<Integer, List<StopOverSation>> maps = (Map<Integer, List<StopOverSation>>) session.getAttribute("map");
	%>
	<div id="body">
	<h1>编辑火车行程界面</h1>
		<table border="1" cellspacing="0" width="100%" height="10%">
			<tr>
				<th>行程编号</th>
				<th>查看</th>
				<th>编辑</th>
				<th>车次行程</th>
			</tr>
			<c:forEach items="${map}" var="t" varStatus="ts">
				<tr>
					<td>${t.key}</td>
					<td><button class="checkTrip">查看</button>
						<button class="closeTrip" style="display: none">收起</button></td>
					<td><button class="editTrip" style="display: none">编辑</button>
						<button class="canceleditTrip" style="display: none">取消</button>
						<button class="okeditTrip" style="display: none">确认</button></td>
					<td>
						<table border="1" cellspacing="0" width="100%" height="10%"
							style="display: none">
							<tr>
								<th>车站名称</th>
								<th style="display: none">请选择车站名称</th>
								<th>到达时间</th>
								<th style="display: none">请修改到达时间</th>
								<th>出发时间</th>
								<th style="display: none">请修改出发时间</th>
								<th>停靠时间</th>
								<th style="display: none">请修改停靠时间</th>
							</tr>
							<c:forEach items="${t.value}" var="tv" varStatus="tvs">
								<tr>
									<td>${tv.station}</td>
									<td style="display: none"><select style="height: 25px">
											<option>未设置</option>
											<c:forEach items="${stations}" var="ts" varStatus="tss">
												<option>${ts.name}</option>
											</c:forEach>
									</select></td>
									<td>${tv.arriveTime}</td>
									<td class="chengae" style="display: none"><select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select></td>
									<td>${tv.startTime}</td>
									<td class="chengae" style="display: none"><select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
									</select> <select>
											<option>z</option>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
									</select></td>
									<td>${tv.stopTime}分钟</td>
									<td class="chengae" style="display: none"><input
										style="width: 40px" type="text" class="topTime"
										value="${tv.stopTime}" />分钟</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 添加行程方面的按钮 -->
		<button class="addTrainTrip">添加行程</button>
		<button class="returnMainView">返回主页</button>
		<button class="cancelAddTrainTrip" style = "display:none">取消添加行程</button>
		<div class = "totalAddTrain">
		   <button class = "addTripBtn" style = "display:none">添加</button>
		</div>
	</div>
</body>
<script type="text/javascript">
     
    // 返回主页
    $(".returnMainView").click(function(){
	   
    	window.location.href="mainView.do";
    });

    // 取消行程添加
    $(".cancelAddTrainTrip").click(function(){
    	location.reload();
    });
    
    //添加行程列表按钮
    $(".addTrainTrip").click(function(){
    	$(".addTripBtn").css("display","");
    	$(".cancelAddTrainTrip").css("display","");
    	var divTotal = $(".totalAddTrain");
    	var addDiv = $("<div class = 'addTrainDiv'><span>车站名称:</span> <select style='height: 25px'><option>未设置</option><c:forEach items='${stations}' var='ts' varStatus='tss'><option>${ts.name}</option></c:forEach></select> <span>到达时间:</span> <select><option>z</option><option>0</option><option>1</option><option>2</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <span>开车时间:</span> <select><option>z</option><option>0</option><option>1</option><option>2</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select> <select><option>z</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option></select> <span>请选择停靠时间:</span> <input type='text' placeholder='请选择停靠时间' /><button class = 'deleteAdd' onclick = 'deleteAdd(this)'>删除</button></div>");
    	var addTripBtn = $(".addTripBtn");    	
    	addTripBtn.before(addDiv);
    });
    
    // 添加行程到服务器的按钮
    $(".addTripBtn").click(function(){
    	
    	var arrContact = new Array();
    	var allAddDiv = $(".addTrainDiv");
    	allAddDiv.each(function(index,e){
    		// 首先获得车站的名称
    		var stationName = $(e).children().eq(1).val();
    		// 获得车站到达时间
    		var arriveTime = "";
    		for(var i=3;i<9;i++) {
    			if(i == 5 || i == 7) {
    				arriveTime = arriveTime + ":";
    			}
    			arriveTime = arriveTime + $(e).children().eq(i).val();
    		}
    		
    		var startTime = "";
    		for(var j=10;j<16;j++) {
    			if(j == 12 || j == 14) {
    				startTime = startTime + ":";
    			}
    			startTime = startTime + $(e).children().eq(j).val();
    		}
    		
    		// 获得停靠时间
    		var stopTime = $(e).children().eq(17).val();
    		var stopOverStation = new Object();
    		stopOverStation["station"] = stationName;
    		stopOverStation["arriveTime"] = arriveTime;
    		stopOverStation["startTime"] = startTime;
    		stopOverStation["stopTime"] = stopTime;
    		arrContact.push(stopOverStation);
    	});
    	$.ajax({
			"url" : "addTrainTrip.do",
			"data" : JSON.stringify(arrContact),
			"contentType" : "application/json",
			"dataType" : "json",
			"type" : "post",
			"success" : function(data) {
				if (data.state == 200) {
					alert("添加成功");
					location.reload();
				} else {
					alert(data.message);
				}
			}
		});
    })
    
    // 删除一个行程
    function deleteAdd(tag) {    	
    	$(tag).parent().remove();
    }


	// 查看车次行程
	$(".checkTrip").click(function() {
		$(this).parent().next().next().children().eq(0).css("display", "");
		$(this).css("display", "none");
		$(this).next().css("display", "");
		$(this).parent().next().children().eq(0).css("display", "");
	});
	// 收起车次行程
	$(".closeTrip").click(
			function() {
				$(this).parent().next().next().children().eq(0).css("display",
						"none");
				$(this).css("display", "none");
				$(this).prev().css("display", "");
				$(this).parent().next().children().css("display", "none");
				$(this).parent().next().next().children().eq(0).children()
						.eq(0).children().each(function(index, e) {
							$(e).children().eq(1).css("display", "none");
							$(e).children().eq(3).css("display", "none");
							$(e).children().eq(5).css("display", "none");
							$(e).children().eq(7).css("display", "none");
						});
				s
			});
	// 打开编辑
	$(".editTrip").click(
			function() {
				$(this).css("display", "none");
				$(this).next().css("display", "");
				$(this).next().next().css("display", "");
				$(this).parent().next().children().eq(0).children().eq(0)
						.children().each(function(index, e) {
							$(e).children().eq(1).css("display", "");
							$(e).children().eq(3).css("display", "");
							$(e).children().eq(5).css("display", "");
							$(e).children().eq(7).css("display", "");
						});
			});
	// 取消编辑
	$(".canceleditTrip").click(
			function() {
				$(this).css("display", "none");
				$(this).next().css("display", "none");
				$(this).prev().css("display", "");
				$(this).parent().next().children().eq(0).children().eq(0)
						.children().each(function(index, e) {
							$(e).children().eq(1).css("display", "none");
							$(e).children().eq(3).css("display", "none");
							$(e).children().eq(5).css("display", "none");
							$(e).children().eq(7).css("display", "none");
						});
			})

	// 确认提交编辑
	$(".okeditTrip")
			.click(
					function() {
						var arrContact = new Array();
						var id = $(this).parent().prev().prev().text();
						$(this)
								.parent()
								.next()
								.children()
								.eq(0)
								.children()
								.eq(0)
								.children()
								.each(
										function(index, e) {
											if (index > 0) {
												// 依次获取行程内容
												var stopOverStation = new Object();
												stopOverStation["station"] = $(
														e).children().eq(1)
														.children().eq(0).val();
												var startTime = "";
												$(e)
														.children()
														.eq(3)
														.children()
														.each(
																function(index,
																		e) {
																	if (index == 2
																			|| index == 4) {
																		startTime = startTime
																				+ ":"
																				+ $(
																						e)
																						.val();
																	} else {
																		startTime = startTime
																				+ $(
																						e)
																						.val();
																	}
																});
												stopOverStation["arriveTime"] = startTime;
												var endTime = "";
												$(e)
														.children()
														.eq(5)
														.children()
														.each(
																function(index,
																		e) {
																	if (index == 2
																			|| index == 4) {
																		endTime = endTime
																				+ ":"
																				+ $(
																						e)
																						.val();
																	} else {
																		endTime = endTime
																				+ $(
																						e)
																						.val();
																	}
																});
												stopOverStation["startTime"] = endTime;
												stopOverStation["stopTime"] = $(
														e).children().eq(7)
														.children().eq(0).val();
												stopOverStation["id"] = id;
												arrContact
														.push(stopOverStation);
											}
										});

						$.ajax({
							"url" : "editTrainTrip.do",
							"data" : JSON.stringify(arrContact),
							"contentType" : "application/json",
							"dataType" : "json",
							"type" : "post",
							"success" : function(data) {
								if (data.state == 200) {
									alert("编辑成功");
									location.reload();
								} else {
									alert(data.message);
								}
							}
						});
					})
</script>
</html>