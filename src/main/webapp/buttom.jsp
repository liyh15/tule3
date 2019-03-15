<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	
	$(".ck-switch .ck-switch-off").unbind('click').bind('click',function(){
		$('.ck-switch .ck-switch-on').addClass('ck-switch-current ck-switch-current-3').html('');
		$('.ck-switch .ck-switch-off').removeClass('ck-switch-current ck-switch-current-40').html('否');
	});	
	$("#two").unbind('click').bind('click',function(){
		if($(this).hasClass('ck-switch-on')){
			$('.ck-switch .ck-switch-on').removeClass('ck-switch-current ck-switch-current-3').html('是');
			$('.ck-switch .ck-switch-off').addClass('ck-switch-current ck-switch-current-40').html('');
		}
	});


</script>
<style type="text/css">
/**	外部div式样		*/
.ck-switch{
	width: 75px;
  	height: 26px;
  	margin: 0px auto;
  	position: relative;
  	-moz-border-radius: 50px;
  	-webkit-border-radius: 50px;
  	border-radius: 50px;
  	-moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.2);
  	-webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.2);
  	box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.2);
}
/**	是.的效果#66b9b3绿色	*/
.ck-switch-on{
  	color: #66b9b3;
  	position: absolute;
  	left: 10px;
  	z-index: 0;
  	font-weight: bold;
}
/**	否.的效果#cccccc灰色	*/
.ck-switch-off{
	color: #CCCCCC;
	position: absolute;
 	right: 10px;
	z-index: 0;
	font-weight: bold;
	text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.15);
}
/**	绿色椭圆小块	*/
.ck-switch-current{
  	display: block;
  	width: 30px;
  	height: 20px;
 	cursor: pointer;
  	position: absolute;
  	top: 3px;
  	z-index: 1;
  	background: #66b9b3;
  	-moz-border-radius: 50px;
  	-webkit-border-radius: 50px;
  	border-radius: 50px;
}
/**	left定位	*/
.ck-switch-current-3{
	left: 3px;
}
.ck-switch-current-40{
	left: 42px;
}
</style>
</head>
<body>
<div class="ck-switch"> 
 <span class="ck-switch-on">是</span>    
 <span class="ck-switch-off ck-switch-current ck-switch-current-40" id="two">
 </span>
 </div>
</body>
</html>