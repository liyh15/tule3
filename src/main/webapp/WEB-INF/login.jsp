<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<META    HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META    HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META    HTTP-EQUIV="Expires" CONTENT="0">
<title>注册登录界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/login.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/login.js"></script>
</head>
<body>
	<%
		String view = request.getParameter("view");
	    String interupt=request.getParameter("interupt");
	    request.setAttribute("view",view);
	    //被拦截访问的页面路径
	    request.setAttribute("interupt", interupt);
	%>
	<div id="header">
		<img src="${pageContext.request.contextPath }/image/logo.png" alt="">
		<span> 途乐 </span>
		<a href="mainView.do" style="text-decoration:none;float:right;margin-top:28px;color:#008FEE;margin-right:20px">返回主页</a>
	</div>
	<div id="main">
		<div id="outer">
			<div id="login" onclick="show('login')">登录</div>
			<div id="register" onclick="show('register')">注册</div>
			<div id="loginForm">
				<form  class="form_id" action="../login?view=${view}&interupt=${interupt}" method="POST"
					onsubmit="return loginConfirm()">
					<input type="text" name="username" id="loginName"
						placeholder="请输入手机号"><br> <span
						id="login_name_info">***用户名不能为空***</span><br> <input
						type="password" name="password" id="loginPwd" placeholder="请输入密码"><br>
					<span id="login_pwd_info">***密码不能为空***</span><br> <input
						type="text" name="vercode" id="loginCode" placeholder="请输入验证码">
					<div id="code">
						<img src="codePic.do" style="width: 100px; height: 31px" />
						<p onclick="changeCode()">看不清换一张</p>
					</div>
					<br> <span id="login_code_info">***验证码不能为空***</span>
				     
					<br>
					<span id="login_fail_info">${login_fail_info}</span> 
					      <%
					       session.removeAttribute("login_fail_info");
					     %>
					<input
						type="submit" value="登录" class="submit" onclick="loginConfirm()">
				</form>
			</div>
			<div id="registerForm">
				<form class="form_id" action="../register?view=${view}" method="POST"
					onsubmit="return registerConfirm()" >
					<input type="text" name="username" id="registerName"
						placeholder="请输入手机号"><br> <span
						id="register_name_info">***用户名不能为空***</span><br> <input
						type="password" name="password" id="registerPwd"
						placeholder="请输入密码"><br> <span id="registerpwd_info">***密码不能为空***</span><br>
					<input type="password" name="password" id="register_again_pwd"
						placeholder="请再次输入密码"><br> <span
						id="register_again_pwd_info">***两次密码不同***</span><br> <input
						type="text" name="vercode" id="registerCode" placeholder="请输入验证码">
					<div id="code" class="code">
						<img src="codePic.do" style="width: 100px; height: 31px" />
						<p onclick="changeCode2()">看不清换一张</p>
					</div>
					<br> <span id="register_code_info">***验证码不能为空***</span>
					     <span style="display:inline" id="register_fail">${register_fail}</span>
					     <%
					       session.removeAttribute("register_fail");
					     %>
					     <br>
					<input type="checkbox" name="agreement" id="protocol"><label
						for="protocol">同意我们的<a>服务协议</a>和<a>隐私政策</a></label> <input
						type="submit" value="注册" class="submit"
						onclick="registerConfirm()">
				</form>
			</div>


			<div id="th_part">
				<hr>
				<i>第三方登录</i>
				<hr>
				<div>
					<a href="#"><img
						src="${pageContext.request.contextPath }/image/QQ.png"
						alt="QQ图片加载中..."></a> <a href="#"><img
						src="${pageContext.request.contextPath }/image/WeChat.png"
						alt="WeChat图片加载中..."></a> <a href="#"><img
						src="${pageContext.request.contextPath }/image/Alipay.png"
						alt="Alipay图片加载中..."></a>
				</div>
			</div>
		</div>
	</div>

	<div id="footer">
		<div>24小时客户服务电话(免长途费): 4007-999-999 呼叫中心位于南京 来电统一显示为025-54088686</div>
		<div>
			<a href=""><span>关于我们</span></a> <a href=""><span>Investor
					Relations</span></a> <a href=""><span>联系我们</span></a> <a href=""><span>投诉建议</span></a>
			<a href=""><span>广告服务</span></a> <a href=""><span>旅游券</span></a> <a
				href=""><span id="red">招聘</span></a> <a href=""><span>隐私保护</span></a>
			<a href=""><span>免责声明</span></a> <a href=""><span>用户协议</span></a> <a
				href=""><span>网站地图</span></a>
		</div>
		<div>
			<a href="#"><span>团队介绍</span></a> <a href="#"><span>网站联盟</span></a> <a
				href="#"><span>帮助中心</span></a>
		</div>
	</div>
</body>
<script type="text/javascript">
     var method="${method}";
     if(method=="register")
    	 {
    	    show("register");
    	 }
    var value2 = $("#register_fail").text();
 	if (value2 != "" || value2 != null) {
 		$("#register_fail").css({
 			"display" : "inline-block"
 		});
 	}
</script>
</html>