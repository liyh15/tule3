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
</head>
<style type="text/css">
tr td {
	text-align: center;
}
</style>
<body>
    <h1>编辑火车安排界面</h1>
	<table border="1" cellspacing="0" width="100%" height="10%">
		<tr>
			<th>列车编号</th>
			<th>该列车安排情况</th>
		</tr>
		<c:forEach items="${arranges}" var="t" varStatus="ts">
			<tr>
				<td>${t.key}</td>
				<td><c:forEach items="${t.value}" var="tt" varStatus="tts">
						<table border="1" cellspacing="0" width="100%" height="10%">
							<tr>
								<th>起始站</th>
								<th>到达站</th>
								<th>起始时间</th>
								<th>到达时间</th>
								<th>总时间</th>
							</tr>
							<c:forEach items="${tt.value}" var="ttt" varStatus="ttts">
								<tr>
									<td>${ttt.startStation}</td>
									<td>${ttt.endStation}</td>
									<td>${ttt.startTime}</td>
									<td>${ttt.endTime}</td>
									<td>${ttt.totalTime}</td>
								</tr>
							</c:forEach>
						</table>
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	<button class="add">添加车次安排</button>
	<button class = "returnMain">返回主页</button>
	<form id = "form_id">
	    <button class="cancel">取消</button>	
	<select class = "aaa" style = "display:none" name = "trainName">
		<c:forEach items="${trains}" var="t" varStatus="ts">
			<option>${t.name}</option>
		</c:forEach>
	</select>
	<button class= "submit aaa" style = "display:none">确定</button>
	<table border="1" cellspacing="0" width="100%" height="10%" class = "aaa" style = "display:none">
		<tr>
	        <th>选择按钮</th>
			<th>行程编号</th>
			<th>行程安排</th>
		</tr>
		<c:forEach items="${tripMaps}" var="t" varStatus="ts">
			<tr>
			    <td><input name="trip" type="radio" value="${t.key}" /></td>
				<td>${t.key}</td>
				<td>
					<table border="1" cellspacing="0" width="100%" height="10%">
						<tr>
							<th>车站名称</th>
							<th>到达时间</th>
							<th>出发时间</th>
							<th>停靠时间</th>
						</tr>
						<c:forEach items="${t.value}" var="tt" varStatus="tts">
							<tr>
								<td>${tt.station}</td>
								<td>${tt.arriveTime}</td>
								<td>${tt.startTime}</td>
								<td>${tt.stopTime}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>	
</body>
<script type="text/javascript">
	// 添加车次安排
	$(".add").click(function() {
         $(".aaa").css("display","");
	});
	// 取消添加行程
	$(".cancel").click(function(){
		$(".aaa").css("display","none");
	});
	// 提交修改
	$(".submit").click(function(){
		var form = $("#form_id").serialize();
		$.ajax({
       		"url":"addTrainArrange.do",
       		"data":form,
       		"dataType":"json",
       		"type":"post",     		
       		"success":function(data){
       			alert(data.message);
       			location.reload();
	         }
          });
	});
	$(".returnMain").click(function(){
		   window.location.href = "mainView.do";
	   })
</script>
</html>