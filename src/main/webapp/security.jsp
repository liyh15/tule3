<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>security</title>
	<link rel="stylesheet" style="text/css" href="css/security.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/security.js"></script>
</head>
<body>
	<div id="title">账户安全</div>
	<img src="">
	<ul id="security">
		<li>
			<span class="name">身份认证</span>
			<span class="value">身份认证用于提升账户安全性</span>
		</li>
		<li>
			<span class="name">登录密码</span>
			<span class="value">密码安全度:
				<span class="level low">低</span>
				<span class="level">中</span>
				<span class="level">高</span>
			</span>
			<span class="operation" id="change_pwd">修改</span>
		</li>
		<li>
			<span class="name">安全问题</span>
			<span class="value">安全问题用于您找回密码, 进一步提升您的账户安全性</span>
			<span class="operation">修改</span>
		</li>
	</ul>
</body>
</html>