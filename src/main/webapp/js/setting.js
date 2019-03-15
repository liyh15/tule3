function myCheck() {

	var flag = true;
	var old = $("#old_pwd").attr("value");
	$(".pwd_again_info").css({
		"opacity" : "100"
	});
	if (old == "" || old == null) {
		flag = false;
		$(".pwd_again_info").text("旧密码为空");
	} else {
		// 获得新密码框的输入值
		var value1 = $("#new_pwd").attr("value");
		// 获得确认密码的输入值
		var value2 = $("#new_pwd_again").attr("value");
		if (value1 == null || value1 == "") {
			$(".pwd_again_info").text("新密码为空");
			flag = false;
		} else {
			if (value2 == null || value2 == "") {
				$(".pwd_again_info").text("确认密码为空");
				flag = false;
			} else {
				if (value1 != value2) {
					$(".pwd_again_info").text("两次密码输入不相同");
					flag = false;
				} else {
					$(".pwd_again_info").text("");
					$(".pwd_again_info").css({
						"opacity" : "0"
					});
					flag = true;
				}
			}
		}
	}
	if (flag) {
		var form = $("#form_id").serialize();
		$.ajax({
			"url":"user/changePass.do",
			"data":form,
			"dataType":"json",
			"type":"POST",
			"success":function(data)
			{
				alert(data.message);
			},
			"error":function(data)
			{
				
			}				
		})
	}
}