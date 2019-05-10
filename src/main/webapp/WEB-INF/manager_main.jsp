<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<title>途乐管理主页</title>
<style type="text/css">
   #head {
      width : 500px;
      height: 500px;
      border: 1px solid red;
      margin: 0 auto;
   }
   .title {    
      font-size: 30px;
      display: inline-block;
      maigin: 0 auto;
   }
   .btn {
      font-size: 20px;
      width: 200px;
      height: 40px;
   }
</style>
</head>
<body>
   <div id = "head">
       <img src = "../image/logo.png" style = "width: 100px;height: 100px"></br>
       <span class = "title">途乐车票管理系统</span></br></br>
       <button id = "editTrain" class = "btn">编辑列车</button></br>
       <button id = "editTrainTrip" class = "btn">编辑列车行程</button></br>
       <button id = "editTrainStation" class = "btn">编辑火车站</button></br>
       <button id = "editTrainArrange" class = "btn">编辑火车安排</button></br>
       <button id = "editTrainDateArrange" class = "btn">编辑火车日期安排</button>
   </div>
</body>
<script type="text/javascript">
   $("#editTrain").click(function(){
	   // 转发到编辑列车界面
	   window.location.href="editTrainView.do";
   });
   $("#editTrainTrip").click(function(){
	   // 转发到编辑列车行程界面
	   window.location.href="editTrainTripView.do";
   });
   $("#editTrainStation").click(function(){
	   // 转发到编辑列车行程界面
	   window.location.href="editTrainStaionView.do";
   });
   $("#editTrainArrange").click(function(){
	   // 转发到添加火车安排界面
	   window.location.href="editTrainArrangeView.do";
   })
   $("#editTrainDateArrange").click(function(){
	   // 转发到编辑火车日期安排界面
	   window.location.href="editTrainDateArrangeView.do";
   })
</script>
</html>