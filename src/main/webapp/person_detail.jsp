<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人详情页</title>
<link rel="stylesheet" type="text/css" href="css/person_detail.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/person_detail.js"></script>
<script type="text/javascript" src="js/distpicker.data.js"></script>
<script type="text/javascript" src="js/distpicker.js"></script> 
</head>
<body>
	<div id="outer">
		<span id="title">基本信息</span>
		<form action="setup" method="POST">
			<span><span class="red">*</span>昵称: </span><input type="text" name="nickname" placeholder="未设置" value=${user.nickName } ><br>
			<span class="info">昵称要求为4~16个字符, 支持中文, 英文, 数字, "_", "-"</span><br>
			<span><span class="red">*</span>姓名: </span><input type="text" name="name" placeholder="未设置" value=${user.name } ><br>
			<span class="info">姓名要求为2-40位英文或2-20位中文, 支持空格, ".", 不能中文混搭</span><br>
			<span><span class="red">*</span>手机号: </span><input type="text" name="phone" placeholder="未设置" value=${user.phone }><br>
			<span class="info">添加手机号</span><br>
			<span>生日: </span><input type="date" name="birthday" placeholder="未设置" value=${user.birthday }>
			<br>
			<span><span class="red">*</span>性别: </span>
			<input type="radio" name="gender" id="male" value="男"
				<c:if test="${empty user.sex}">
					checked="checked"
				</c:if> 
				<c:if test="${user.sex == '男'}">
					checked="checked"
				</c:if>
			>
			<label for="male">男</label>
			<input type="radio" name="gender" id="female" value="女"
				<c:if test="${user.sex == '女'}">
					checked="checked"
				</c:if>
			> 
			<label for="female">女</label>
			<br>
			<span>邮箱: </span><input type="text" name="email" placeholder="未设置" value=${user.email }><br>
			<span><span class="red">*</span>常住地: </span>
			<select id="provinces">
				<option selected="selected">请选择</option>
				<option>江苏省</option>
			</select>
			<select id="city" name="city">
				<option selected="selected">
					<c:choose>
					 	<c:when test="${empty user.liveCity}">
					 		请选择
		       			</c:when>
		       			<c:otherwise>
		       				${user.liveCity}
		        		</c:otherwise>
					 </c:choose>
				</option>
				<option>徐州</option>
				<option>宿州</option>
				<option>南京</option>
			</select>
			<select id="area">
				<option selected="selected">请选择</option>
				<option>鼓楼</option>
				<option>云龙</option>
				<option>江宁</option>
			</select>
			<br>
			<span>详细地址: </span><input type="text" name="address_detail" placeholder="未设置" value=${user.address }><br>
			<span>婚姻: </span>
			<select disabled="disabled" name="marriage">
				<option selected="selected">
					<c:choose>
					 	<c:when test="${empty user.married}">
					 		请选择
		       			</c:when>
		       			<c:otherwise>
		       				${user.married}
		        		</c:otherwise>
					 </c:choose>
				</option>
				<option>已婚</option>
				<option>未婚</option>
			</select>
			<br>
			<span><span class="red">*</span>职业: </span>
			<select name="job">
				<option selected="selected">
					<c:choose>
					 	<c:when test="${empty user.job}">
					 		请选择
		       			</c:when>
		       			<c:otherwise>
		       				${user.job}
		        		</c:otherwise>
					 </c:choose>
				</option>
				<option>白领/一般职员</option>
				<option>公务员/事业单位</option>
				<option>工业/服务业人员</option>
				<option>自由职业/个体户/私营业主</option>
				<option>无业/失业/下岗</option>
				<option>退休</option>
				<option>学生</option>
			</select>
			<br>
			<span>学历: </span>
			<select name="education">
				<option selected="selected">
					<c:choose>
					 	<c:when test="${empty user.education}">
					 		请选择
		       			</c:when>
		       			<c:otherwise>
		       				${user.education}
		        		</c:otherwise>
					 </c:choose>
				</option>
				<option>博士及以上</option>
				<option>硕士</option>
				<option>本科</option>
				<option>大专</option>
				<option>大专以下</option>
			</select>
			<br>
			<div id="head">
				<img alt="头像正在加载中..." src="image/head.png" >
				<input type="file" name="headImg" id="headImg" style="display: none">
				<label for="headImg">>>>更换头像</label>
			</div>
			<div>
				<input type="button" value="编辑" id="edit">
				<input type="submit" value="保存" id="save">
				<input type="reset" value="取消" id="cancel">
			</div>
		</form>
	</div>
	
</body>
</html>