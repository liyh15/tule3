<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/malogin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
	
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>管理员</title>
</head>
<body>

    <div>
        <div class="be-content pren">

            <div class="ioc_text">
                <img src="${pageContext.request.contextPath }/image/title.png" alt="">
                <span>途乐管理员登录界面</span>
            </div>
            <div>
                <form action="login.do" id = "form_data">
                    <div class="br-content">

                         <div class="input-group mb-4 bootint">
                             <div class="input-group-prepend">
                                 <span class="input-group-text"><i class="fa fa-user"></i></span>
                             </div>
                             <input type="text" class="form-control" placeholder="请输入账号" name = "phone">
                         </div>

                         <div class="input-group mb-4 bootint">
                             <div class="input-group-prepend">
                                 <span class="input-group-text"><i class="fa fa-unlock-alt"></i></span>
                             </div>
                             <input type="password" class="form-control" placeholder="请输入密码" name = "password">
                             
                         </div>                         
                         <div class="input-group mb-4 bootint">      
                                       
                             <input type="code" class="form-control" placeholder="验证码" name = "code" id = "codeInput"> 
                             <img src="codePic.do" class = "codeImage"/>
                             <span style = "font-size:12px;cursor:pointer" class = "changeCode">看不清，换一张</span>                
                         </div>
                        
                        <div style="padding-top: 10px">
                            <input type="button" class="btn" value="登录">
                        </div>
                        <div class="be-con">                           
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
	
</body>
<script type="text/javascript">

    $(".btn").click(function(){    	
    	var form = $("#form_data").serialize();
    	$.ajax({
			"url": "login.do",
		    "data":form,
		    "dataType":"json",
		    "type":"post",
		    "success":function(data){
	           if(data.state == 400) {
	        	   alert(data.message);
	           } else {
	        	   // 登录成功跳转到管理员主界面
	        	   window.location.href="mainView.do";
	           }
		    }
		});
    });
    
    // 更换验证码
    $(".changeCode").click(function(){
    	$.ajax({
			"url": "codePic.do",
		    "type":"post"
		});
    	$(".codeImage").remove();
    	$("#codeInput").after("<img src='codePic.do' class = 'codeImage'/>");
    });
</script>
</html>