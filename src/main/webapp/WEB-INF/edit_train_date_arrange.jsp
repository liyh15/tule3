<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑火车日期安排界面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
</head>
<style type="text/css">
tr td {
	text-align: center;
}
</style>
<body>
   <h1>编辑火车日期安排界面</h1>
	<span>请选择需要查看的列车日期安排:</span>
	<select id="train">
		<c:forEach items="${trains}" var="t" varStatus="ts">
			<option>${t.name}</option>
		</c:forEach>
	</select>
	<button id="checkArrange">确定</button>
	<table border="1" cellspacing="0" width="100%" height="10%" id="table">
		<tr>
			<th>火车行程编号</th>
			<th>火车日期安排</th>
		</tr>		
	</table>
	<button id = "addTrainArrange">添加火车日期安排</button>
	<select id="train2" class = "aaa" style = "display:none">
		<c:forEach items="${trains}" var="t" varStatus="ts">
			<option>${t.name}</option>
		</c:forEach>
	</select>
	<button class = "chooseArrange aaa" style = "display:none">选择车次安排</button>	
	<button class = "submit aaa" style = "display:none">提交</button>	
	<span class = "chooseDate aaa" style = "display:none"></span>
    

</body>
<script type="text/javascript">
	// 搜索该车次的所有写的日期安排
	$("#checkArrange")
			.click(
					function() {

						var trainName = $("#train").val();
						$
								.ajax({
									"url" : "queryTrainDateArrangeByName.do",
									"data" : "trainName=" + trainName,
									"dataType" : "json",
									"type" : "post",
									"success" : function(data) {
										//alert(data.param);
										var table = $("#table");
										table.children().remove();
										var title = $("<tr><th>火车行程编号</th><th>火车日期安排</th></tr>");
										table.append(title);
										for (var key in data.param) {
											var one = $("<tr><td></td><td></td></tr>");
			    
											one.children().eq(0).text(key);
											var map2 = data.param[key];
											var two = $("<table border='1' cellspacing='0' width='100%' height='10%'><tr><th>火车日期安排组编号</th><th>火车日期安排</th></tr></table>");
											for(var key2 in map2) {
												
												var five = $("<tr><td></td><td></td></tr>");						
												five.children().eq(0).text(key2);
											    var list = map2[key2];
											    var three = $("<table border='1' cellspacing='0' width='100%' height='10%'><tr><th>火车日期安排编号</th><th>开始时间日期</th><th>到达日期</th><th>开车时间</th><th>到达时间</th><th>开始站</th><th>到达站</th><th>总时间</th></tr></table>");
											    for(var i=0;i<list.length;i++){
											    	var four = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
											    	four.children().eq(0).text(list[i].id);
											    	four.children().eq(1).text(list[i].date);
											    	four.children().eq(2).text(list[i].endDate);
											    	four.children().eq(3).text(list[i].startTime);
											    	four.children().eq(4).text(list[i].endTime);
											    	four.children().eq(5).text(list[i].startStation);
											    	four.children().eq(6).text(list[i].endStation);
											    	four.children().eq(7).text(list[i].totalTime);
											    	three.append(four);
											    }
											    five.children().eq(1).append(three);
											    two.append(five);
											}
											one.children().eq(1).append(two);
											table.append(one);
										}
									}
								});
					});
	// 添加火车日期安排
	$("#addTrainArrange").click(function(){
		$(".aaa").css("display","");
	})
	// 显示选择的车次安排
	$(".chooseArrange").click(function(){
		
		
		var trainName = $("#train2").val();
		$.ajax({
			"url" : "queryTrainArrangeByTrain.do",
			"data" : "trainName=" + trainName,
			"dataType" : "json",
			"type" : "post",
			"success" : function(data) {
				$(".chooseDate").next().remove();
				var map = data.param;
				var one = $("<table border='1' cellspacing='0' width='100%' height='10%'><tr><th>是否选择</th><th>行程编号</th><th>该列车该行程安排情况</th></tr></table>");
				var eight = $("<form id = 'form_id'></form>");
				for(var key in map) {				
					var two = $("<tr><td></td><td></td><td></td></tr>");
					var five = $("<input type='radio' name='tripId' value ='"+key+"'/>");
					two.children().eq(0).append(five);
					two.children().eq(1).text(key);
					var list = map[key];
					var three = $("<table border='1' cellspacing='0' width='100%' height='10%'><tr><th>起始站</th><th>到达站</th><th>起始时间</th><th>到达时间</th><th>总时间</th></tr></table>");
					
					for(var i=0;i<list.length;i++){
						 var four = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
						 four.children().eq(0).text(list[i].startStation);
						 four.children().eq(1).text(list[i].endStation);
						 four.children().eq(2).text(list[i].startTime);
						 four.children().eq(3).text(list[i].endTime);
						 four.children().eq(4).text(list[i].totalTime);
						 three.append(four);
					}
					two.children().eq(2).append(three);		
					one.append(two);
				}
				eight.append(one);
				var scan = $("<span>请选择发车时间:</scan>");
				var date = $("<input type = 'date' name = 'date'/>");
				eight.append(scan);
				eight.append(date);
				$(".chooseDate").after(eight);
			}
			});
	});
	// 提交创建火车日期安排信息
	$(".submit").click(function(){
		
		var form = $("#form_id").serialize();
		var trainName = $("#train2").val();
		$.ajax({
			"url" : "addTrainDateArrange.do",
			"data" : form+"&trainName="+trainName,
			"dataType" : "json",
			"type" : "post",
			"success" : function(data) {
				alert(data.message);
			}
		});
	})
</script>
</html>