<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>setting.html</title>
	<link rel="stylesheet" type="text/css" href="css/setting.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/setting.js"></script>
</head>
<body>
	<div id="title">密码设置</div>
	<form id="form_id">
		<span class="name">当前密码 : </span><input type="text" id="old_pwd" name="old_pwd" placeholder="请输入当前使用的密码"><br>
		<span class="pwd_info">旧密码不能为空</span><br>
		<span class="name">新密码 : </span><input type="password" id="new_pwd" name="new_pwd" placeholder="请输入新密码"><br>
		<span class="pwd_info">由字母, 数字或符号组成的6-16位字符, 区分大小写</span><br>
		<span class="name">确认密码 : </span><input type="password" id="new_pwd_again" name="new_pwd_again" placeholder="请再次输入新密码"><br>
		<span class="pwd_again_info">两次输入的密码不相同, 请重新输入</span><br>
		<input type="button" value="确认" onclick="myCheck()">
	</form>	
</body>
</html>