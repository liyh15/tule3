<%@page import="entity.Train"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑列车界面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<title>途乐管理主页</title>
<style type="text/css">
#body {
	width: 800px;
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
		List<Train> train = (List<Train>) session.getAttribute("train");
	%>
	<div id="body">
		<table border="1" cellspacing="0" width="100%" height="10%">
			<tr>
				<th>列车编号</th>
				<th>列车名称</th>
				<th>所属部门</th>
				<th>操作</th>
			</tr>
			<!-- t代表当前条目的变量名称 ,ts代表循环状态的变量名称 -->
			<c:forEach items="${train}" var="t" varStatus="ts">
				<tr>
					<td>${t.id}</td>
					<td>${t.name}</td>
					<td>江苏铁道部</td>
					<td>
					  <button onclick = "edit(this)">编辑</button>
					  <input type="text" class = "editName" style = "display:none" placeholder = "请输入修改列车名称"/>
					  <button class = "editok" style = "display:none">确定</button>
					  <button class = "editcancel" style = "display:none">取消</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<button class = "addbtn">添加车次</button>	
		<button class = "backMain">回到主页</button>		
		<span style = "display:none" class = "addspan" > 		
		<input type="text" name="name" class = "trainName" placeholder = "请输入列车名称"/> 
		  <select style="height: 23px;width: 80px" class = "type">
				<option>动车</option>
				<option>高铁</option>
				<option>快车</option>
				<option>特快</option>
				<option>直达</option>
		  </select>
		<button class = "addsubmit">确定</button>
		<button class = "cancelsubmit">取消</button>
		</span>
	</div>
</body>
<script type="text/javascript">

   // 编辑列车
   function edit(tag) {
	   $(".addspan").css("display","none");	   
	   var tt = $(tag);
	   tt.css("display","none");
	   tt.siblings().css("display","inline");
   }
   // 编辑取消按钮
   $(".editcancel").click(function(){
	   
	   $(this).siblings().css("display","none");
	   $(this).css("display","none");
	   $(this).prev().prev().prev().css("display","inline");
   });
   
   // 编辑确认按钮
   $(".editok").click(function(){
	   var name = $(this).prev().val();
	   var id = $(this).parent().prev().prev().prev().text();
	   if(name == null && name == "") {
		   alert("列车名称能为空");
	   } else {
		   $.ajax({
	       		"url":"editTrain.do",
	       		"data":"name="+name+"&id="+id,
	       		"dataType":"json",
	       		"type":"post",     		
	       		"success":function(data){
	       			alert(data.message);
	       			location.reload();
		         }
	          });
	   }
   });
   
   // 点击显示添加车次按钮
   $(".addbtn").click(function(){
	   $(".addspan").css("display","inline");
   });
   // 添加车次提交
   $(".addsubmit").click(function(){
	   
	   var trainName = $(".trainName").val();
	   var type = $(".type").val();
	   if(trainName == null || trainName == "") {		   
		   
		   alert("列车的名称不能为空");
	   } else {
		   $.ajax({
       		"url":"addTrain.do",
       		"data":"name="+trainName+"&type="+type,
       		"dataType":"json",
       		"type":"post",     		
       		"success":function(data){
       			alert(data.message);
       			location.reload();
	         }
          });
	   }
   });
   // 取消添加车次
   $(".cancelsubmit").click(function(){
	   $(".addspan").css("display","none");
   });
   // 回到主页的按钮
   $(".backMain").click(function(){
	   window.location.href="mainView.do";
   })
</script>
</html>