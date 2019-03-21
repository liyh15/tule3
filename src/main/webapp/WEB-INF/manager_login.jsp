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
                <form action="">
                    <div class="br-content">

                         <div class="input-group mb-4 bootint">
                             <div class="input-group-prepend">
                                 <span class="input-group-text"><i class="fa fa-user"></i></span>
                             </div>
                             <input type="text" class="form-control" placeholder="Username">
                         </div>

                         <div class="input-group mb-4 bootint">
                             <div class="input-group-prepend">
                                 <span class="input-group-text"><i class="fa fa-unlock-alt"></i></span>
                             </div>
                             <input type="password" class="form-control" placeholder="Your Password">
                         </div>

                        <div class="br-text">
                            <p>
                                <span>忘记密码?</span>
                                <a href="">找回</a>
                            </p>
                        </div>
                        <div style="padding-top: 10px">
                            <input type="button" class="btn" value="登录">
                        </div>
                        <div class="be-con">
                            <span>2018 - 2019</span>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
	
</body>
</html>