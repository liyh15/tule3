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
	<table border="1" cellspacing="0" width="100%" height="10%">
		<tr>
			<th>火车站编号</th>
			<th>火车站名称</th>
			<th>火车站城市</th>
		</tr>
		<c:forEach items="${stations}" var="t" varStatus="ts">
		   <tr>
			 <td>${t.id}</td>
			 <td>${t.stationName}</td>
			 <td>${t.cityName}</td>
		   </tr>
		</c:forEach>		
    </table>
    <button class = "addStation">添加车站</button>
    <input type = "text" class = "stationName aaa" style = "width:100px"placeholder = "请输入车站名称"><span class  ="aaa">站</span>
    <select style = "height:24px;width:110px" class = "cityName aaa">
        <c:forEach items="${citys}" var="ct" varStatus="cts">
           <option>${ct.name}</option>      
        </c:forEach>
    </select>  
    <button class = "add aaa">添加</button>
    <button class = "cancel aaa">取消</button>
</body>
<script type="text/javascript">
   $(function(){
	   $(".aaa").css("display","none");
   });
   
   $(".addStation").click(function(){
	   $(".aaa").css("display","");
   });
   
   $(".cancel").click(function(){
	   $(".aaa").css("display","none");
   });
   
   $(".add").click(function(){
	   
	   var stationName = $(".stationName").val();
	   var cityName = $(".cityName").val();
	   $.ajax({
      		"url":"editTrainStaion.do",
      		"data":"stationName="+stationName+"&cityName="+cityName,
      		"dataType":"json",
      		"type":"post",     		
      		"success":function(data){
      			if(data.state != 200) {
      				alert(data.message);
      			} else {
      				alert(data.message);
      				location.reload();
      			}    			
	         }
         });
   });
</script>
</html>