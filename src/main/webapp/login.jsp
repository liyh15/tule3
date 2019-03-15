<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册登录界面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" type="text/csss" href="css/footer.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
    <%
        String view=request.getParameter("view");
    %>
	<div id="header">
		<img src="image/logo.png" alt="">
		<span>
			途乐
		</span>
	</div>
	<div id="main">
		<div id="outer">
			<div id="login" onclick="show()">登录</div>
			<div id="register" onclick="show()">注册</div>
			<div id="loginForm">
				<form action="login?view=${view}" method="POST" onsubmit="return loginConfirm()">
					<input type="text" name="username" id="loginName" placeholder="请输入手机号/邮箱" ><br>
					<span id="login_name_info">***用户名不能为空***</span><br>
					<input type="password" name="password" id="loginPwd" placeholder="请输入密码"><br>
					<span id="login_pwd_info">***密码不能为空***</span><br>
					<input type="text" name="vercode" id="loginCode" placeholder="请输入验证码">
					<div id="code">验证码</div><br>
					<span id="login_code_info">***验证码不能为空***</span><br>
					<span id="login_fail_info">${login_fail_info}</span>
					<input type="submit" value="登录" class="submit" onclick="loginConfirm()" >
				</form>
			</div>
			<div id="registerForm">
				<form action="register" method="POST" onsubmit="return registerConfirm()">
					<input type="text" name="username" id="registerName" placeholder="请输入手机号"><br>
					<span id="register_name_info">***用户名不能为空***</span><br>
					<input type="password" name="password" id="registerPwd" placeholder="请输入密码"><br>
					<span id="registerpwd_info">***密码不能为空***</span><br>
					<input type="password" name="password" id="register_again_pwd" placeholder="请再次输入密码"><br>
					<span id="register_again_pwd_info">***两次密码不同***</span><br>
					<input type="text" name="vercode" id ="registerCode" placeholder="请输入验证码">
					<div id="code">验证码</div><br>
					<span id="register_code_info">***验证码不能为空***</span><br>
					<input type="checkbox" name="agreement" id="protocol"><label for="protocol">同意我们的<a>服务协议</a>和<a>隐私政策</a></label>
					<input type="submit" value="注册" class="submit">
				</form>
				
			</div>
			
			<div id="th_part">
				<hr>
				<i>第三方登录</i>
				<hr>
				<div>
				<a href="#"><img src="image/QQ.png" alt="QQ图片加载中..."></a>
				<a href="#"><img src="image/WeChat.png" alt="WeChat图片加载中..."></a>
				<a href="#"><img src="image/Alipay.png" alt="Alipay图片加载中..."></a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		<div>24小时客户服务电话(免长途费): 4007-999-999 呼叫中心位于南京 来电统一显示为025-54088686</div>
		<div>
			<a href=""><span>关于我们</span></a>
			<a href=""><span>Investor Relations</span></a>
			<a href=""><span>联系我们</span></a>
			<a href=""><span>投诉建议</span></a>
			<a href=""><span>广告服务</span></a>
			<a href=""><span>旅游券</span></a>
			<a href=""><span id="red">招聘</span></a>
			<a href=""><span>隐私保护</span></a>
			<a href=""><span>免责声明</span></a>
			<a href=""><span>用户协议</span></a>
			<a href=""><span>网站地图</span></a>
		</div>
		<div>
			<a href="#"><span>团队介绍</span></a>
			<a href="#"><span>网站联盟</span></a>
			<a href="#"><span>帮助中心</span></a>
		</div>
	</div>
</body>
</html>