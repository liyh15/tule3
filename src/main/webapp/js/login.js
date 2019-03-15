/*
点击上面的div, 切换登录和注册, 顺便改变样式
 */

function show(a) {
	if (a == "login") {
		login.style.backgroundColor = "#fff";
		login.style.color = "#34BB60";
		register.style.backgroundColor = "#34BB60";
		register.style.color = "#fff";
		loginForm.style.display = "block";
		registerForm.style.display = "none";
		var spans2 = loginForm.getElementsByTagName("span");
		if (spans2!=null) {
			for (var i = 0; i < spans2.length; i++) {
				spans2[i].style.display = "none";
			}
		}
	}
	if (a == "register") {
		register.style.backgroundColor = "#fff";
		register.style.color = "#34BB60";
		login.style.backgroundColor = "#34BB60";
		login.style.color = "#fff";
		loginForm.style.display = "none";
		registerForm.style.display = "block";
		var spans = registerForm.getElementsByTagName("span");
		if (spans!=null) {
			for (var i = 0; i < spans.length; i++) {
				spans[i].style.display = "none";
			}
		}
	}
}

/*
 * 点击登录和注册按钮会进行校验 判断每个单选框是否为空, 若为空则提示不能为空 注册的时候判断两次输入的密码是否相同, 若不相同, 则提示两次密码输入不同
 * 点击的时候判断验证码, 是否和生成的相同, 若不相同, 则提示验证码输入错误
 */
function loginConfirm() {

	var flag = true;
	login_name_info.style.display = "none";
	login_pwd_info.style.display = "none";
	login_code_info.style.display = "none";
	
	// 用户名不填写的话, 显示用户名为空
	var loginNameVal = loginName.value;
	var reg=/^[0-9]{11}$/;
	if (loginNameVal == "" || loginNameVal == null) {
		$("#login_name_info").text("用户名不能为空！");
		login_name_info.style.display = "inline-block";
		flag = false;
	}
	else if(!reg.test(loginNameVal))
    	{
    	$("#login_name_info").text("账号格式不正确！为十一位的数字");
    	  login_name_info.style.display = "inline-block";
  		  flag = false;
    	}
	// 密码不填写的话, 显示密码为空
	var loginPwdVal = loginPwd.value;
	if (loginPwdVal == "" || loginPwdVal == null) {
		login_pwd_info.style.display = "inline-block";
		flag = false;
	}

	// 若验证码不填写则显示验证码为空
	var loginCodeVal = loginCode.value;
	if (loginCodeVal == "" || loginCodeVal == null) {
		login_code_info.style.display = "inline-block";
		flag = false;
	}
	return flag;

}

function registerConfirm() {

	var flag = true;
	//现将验证码失败情况给隐藏
	$("#register_fail").css({
			"display" : "none"
		});	
	register_name_info.style.display = "none";
	registerpwd_info.style.display = "none";
	register_again_pwd_info.style.display = "none";
	register_code_info.style.display = "none";	
	// 用户名不填写的话, 显示用户名为空
	var registerNameVal = registerName.value;
	var reg=/^[0-9]{11}$/;
	if (registerNameVal == "" || registerNameVal == null) {
		$("#register_name_info").text("账号不能为空！");
		register_name_info.style.display = "inline-block";
		flag = false;
	}
	if(!reg.test(registerNameVal))
    	{
    	  $("#register_name_info").text("账号格式不正确！为十一位的数字");
    	  register_name_info.style.display = "inline-block";
  		  flag = false;
    	}

	// 密码不填写的话, 显示密码为空
	var registerPwdVal = registerPwd.value;
	if (registerPwdVal == "" || registerPwdVal == null) {
		registerpwd_info.style.display = "inline-block";
		flag = false;
	}

	// 若两次填写密码不同, 则显示两次输入密码不同
	var registerAgainPwdVal = register_again_pwd.value;
	if (registerAgainPwdVal == "" || registerAgainPwdVal == null) {
		register_again_pwd_info.innerText = "第二次输入的密码为空";
		register_again_pwd_info.style.display = "inline-block";
		flag = false;
	}
	if (registerPwdVal != registerAgainPwdVal) {
		register_again_pwd_info.innerText = "两次密码输入不相同";
		register_again_pwd_info.style.display = "inline-block";
		flag = false;
	}
	// 若验证码不填写则显示验证码为空
	var registerCodeVal = registerCode.value;
	if (registerCodeVal == "" || registerCodeVal == null) {
		register_code_info.style.display = "inline-block";
		flag = false;
	}
	return flag;

}
//看不清换一张验证码
function changeCode()
{
	var p=event.target;
	var ps=$(p);
	ps.prev("img").remove();
	$.ajax({
		"url":"codePic.do",
		"type":"post"		
	});
	var img=$("<img src='codePic.do' style='width: 100px; height: 31px' />");
    $("#code").prepend(img);
}
function changeCode2()
{
	var p=event.target;
	var ps=$(p);
	ps.prev("img").remove();
	$.ajax({
		"url":"codePic.do",
		"type":"post"		
	});
	var img=$("<img src='codePic.do' style='width: 100px; height: 31px' />");
    $(".code").prepend(img);
}
$(function() {
	var value = $("#login_fail_info").text();
	if (value != "" || value != null) {
		$("#login_fail_info").css({
			"display" : "inline-block"
		});
	}	
})


