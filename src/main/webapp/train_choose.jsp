<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>付款信息</title>
	<link rel="stylesheet" href="css/traininfo.css">
</head>
<body>
  <%
      String id=request.getParameter("seatid");
      request.setAttribute("seatid", id);    
  %>
	<div class="head2nd">
		<div class="content clearfix">
			<a id="logo" href="#" title="乐途">
				<img src="image/train_logo.png" alt="乐途">
			</a>
			<div class="order_steps">
				<div class="flow_num">
					<ol class="clearfix">
						<li class="green">1</li>
						<li class="green_line"></li>
						<li class="grey">2</li>
						<li class="grey_line"></li>
						<li class="grey">3</li>
					</ol>
				</div>
				<div class="flow_word">
					<ol class="clearfix">
						<li class="green">填写订单</li>
						<li class="grey">在线支付</li>
						<li class="grey">等待出票</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<form action="orderdetail" method="get" onsubmit="return allowConfirm()">
		<div class="wrapper clearfix">
		<div class="content clearfix J-flag">
			<div class="order_box">
				<h1 class="order_title">车次信息</h1>
				<div class="tk_modify_btn">
					<a href="http://localhost:8080/day09/%E7%81%AB%E8%BD%A6%E7%A5%A8%E8%AF%A6%E6%83%85%E9%A1%B5.jsp">修改车次></a>
				</div>
				<div class="order_content">
					<div class="train_detail clearfix">
						<div class="train_name">
							<div class="train_num">${trainArrange.trainName}</div>
						</div>
						<div class="train_date_start">
							<strong class="train_det_time">${trainArrange.startTime}</strong>
							<span class="train_det_station">${trainArrange.startStation}
								<i class="icon icon_start"></i>
							</span>
							<span class="train_det_date">
								${trainArrange.date}
							</span>
						</div>
						<div class="train_duration">
							<div class="train_duration_left"></div>
							<strong class="train_duration_time">${trainArrange.totalTime}</strong>
							<div class="train_duration_right"></div>
							<div class="train_icon"></div>
						</div>
						<div class="train_date_end">
							<strong class="train_det_time">${trainArrange.endTime}</strong>
							<span class="train_det_station">${trainArrange.endStation}
								<i class="icon icon_end"></i>
							</span>
							<span class="train_det_date">
								${trainArrange.date}
							</span>
						</div>
					</div>
				</div>
				<div class="order_bottom">
				    <c:forEach items="${trainArrange.trainSeats}" var="seat" varStatus="seats">
				       <c:choose>
				           <c:when test="${seats.getIndex()==seatid}">				          
				             <div class="square yell" onclick="square(this)">
						     <span class="sp1" name="seattype" style="color:#ff8040">${seat.seatType}</span>￥<span class="sp2">${seat.price}</span><br>
						     <span class="sp1" style="color:#ff8040">余票</span><span class="sp2">${seat.count}</span><span class="sp3">张</span>
				           </c:when>
				           <c:otherwise>
				             <div class="square1 square" onclick="square(this)">
						     <span class="sp1">${seat.seatType}</span>￥<span class="sp2">${seat.price}</span><br>
						     <span class="sp1">余票</span><span class="sp2">${seat.count}</span><span class="sp3">张</span>				           
				           </c:otherwise>
				       </c:choose>		         		        
					</div>
				    </c:forEach>
					<br><span class="ry">
						<input type="checkbox" name="ry" id="wp"><label for="wp">若无票，愿意接受无坐票</label>
					</span>
				</div>
			</div>
			<div class="bd">
				<span class="bd1">绑定12306，出票更快速！</span><a href="#" class="bd2">马上绑定</a>
			</div>
			<div class="pass panel mt20">
			<div class="panel_head clearfix">
				<div class="f1">
					<h2 style="float: left;">
						<span class="icon pass_icon mr10 vm"></span>
						<span class="vm">乘车人信息</span>
					</h2>
					<div class="tnbnt">途乐帮你填</div>
				</div>
			</div>
			<span class="person">
			</span>
			<div class="personinfo">
				<div class="num">
					<span>第<strong>1</strong>位</span><br>
				</div>
				<div class="name">
					<span class="left" >姓名</span>					
					<input type="text" name="username">
					<span style="color:red;display:none">姓名不能为空</span>
					<img src="image/train_yc.jpg" onmouseover="xm1(this)" onmouseout="xm2(this)" class="xmxx">
					<div class="xm" style="display: none;">
						1.乘客姓名与证件号必须与乘车时使用证件上的名字和号码一致，如有中文名，请填写中文名。<br>
						2.如名字中包含生僻字可直接输入拼音代替。例如："王鬳"可输入为"王yan"。<br>
						3.姓名中最多输入不超过30个字符(一个汉字记为2个字符)，如果超过30个字符，请按姓名中第一个汉字或者英文字符开始顺序连续输入30个字符(空格字符不输入)。<br>
						4.姓名中有"."或"。"时，请仔细辨认身份证原件上的"."或"。",正确输入。
					</div>
				</div>
				<br class="br"><span class="lx">成人票</span>
				<div class="zjlx">
					<span class="left">证件类型：</span>
					<select class="select" name="bodytype">
						<option>请选择证件类型</option>
						<option>二代身份证</option>
						<option>军官证</option>
					</select>										
					<input type="text" name="number">
					<span style="color:red;display:none">请选择证件类型</span>
					<span style="color:red;display:none">证件号码不能为空</span>
					<img src="image/train_yc.jpg" onmouseover="zjxx1(this)" onmouseout="zjxx2(this)">
					<div class="zjxx" style="display: none;">
						请您仔细核对后输入，如填写错误，可能导致出票错误，产生退票费用！
					</div>
				</div>
				<div class="tj">
					<img src="image/train_tj.jpg" class="jia" onclick=""><span style="font-size: 12px;">添加儿童票(暂未开放)</span>
					<span style="font-size: 12px;margin-left: 55px;">每名成年乘客可免费携1名1.2米的儿童，该儿童可不用购票</span>
					<span onclick="gpsm(this)" style="font-size: 12px;margin-left: 5px;color: #56AC32;cursor: pointer;">购票说明<img class="tjsm"></span>
					<div class="gpsm" style="display: none;">
							&nbsp&nbsp&nbsp&nbsp&nbsp低于1.5米
							低于1.5米儿童可购儿童票，但须跟成人票一起购买，使用同行成人证件购票并凭此证件取票。
							高于1.5米
							高于1.5米的儿童须购全价票。如果儿童无有效证件，则只能在线下售票窗口购买。
							儿童票按实际订单票价收取，出票成功后，如产生差价退款，会在3-7个工作日返回您的支付账户。
							购买儿童票时，乘车儿童有有效身份证件的，请填写本人有效身份证件信息。乘车儿童没有有效身份证件的，使用同行成年人的有效身份证件信息；购票时不受第一条限制，但购票后、开车前须办理换票手续方可进站乘车。
							备注：请根据儿童实际身高购票，途牛不承担因儿童身高与所购车票不符而无法进站的责任。
					</div>
				</div>
			</div>
			<div class="tjck" style="margin-left: 85px;border-top: 1px solid #eee;">
				<div class="tjkc_btn" onclick="addfn()">+添加乘客</div><span class="sy">你还可以添加4个乘客</span><br>
				<select class="bx">
					<option>不购买保险</option>
					<option>购买4元保险</option>
					<option>购买6元保险</option>
				</select>
			</div>
		</div>
		<div class="qp">
				<div class="qp_left" onclick="change()">
					<img src="image/train_piao1.jpg"><span class="zqp">自取票</span><span class="zqp1">（人工窗口或自动取票机）</span>
					<img src="image/train_yc.jpg" class="qupiao" onmouseover="m1()" onmouseout="m2()">
					<div class="zqpxx" style="display: none;">
						1.二代身份证可在火车站、代售点、自动售票机取票（机器无法识别身份证需要到火车站取票）。<br>
						2.部分车站支持刷二代身份证直接乘车，具体可咨询车站。<br>
						3.护照、回乡证、台胞证请在火车票核验后检票，如已办理核验手续可至火车站或代售点使用原件直接取票。<br>
						4.儿童票需要换取纸质车票才可以进站乘车
					</div>
				</div>
				<div class="qp_right" onclick="">
					<img src="image/train_piao2.jpg"><span class="zqp">送票上门(暂未开放)</span>
					<img src="image/train_yc.jpg" class="qupiao" onmouseover="spsm1()" onmouseout="spsm2()">
					<div class="spsm" style="display: none;">
						1.送票上门预售期是28天，付款后会尽快出票，出票成功后2-3个工作日送达，节假日顺延。<br>
						2.请务必准确填写配送地址，不在选择范围内的地区请勿下单，如地址错误导致无法送达，责任自行承担。<br>
						3.选择该服务，如需办理退票或改签，请携带纸质车票和购票有效证件前往火车站办理。<br>
						4.部分乡镇及偏远地区不能保证送达，请选择市、区、县内地址。
					</div>
				</div>
				<div class="left_zqp" style="display: block;">
					<span class="phone">手机号码：</span><input type="text" name="phone" value="123"><span style="color:red;display:none">手机号不能为空</span>
					<img src="image/train_yc.jpg" class="qupiao1" onmouseover="phone1()" onmouseout="phone2()">
					<div class="phonetx" style="display: none;">
						购票后，我们将发送短信至该手机号，请您仔细核对！
					</div>
				</div>
				<div class="right_zqp" style="display: none;">
					<span class="r1"><span class="xing">*</span><span class="lxr">联系人：</span><input type="text"></span><br>
					<span class="r1"><span class="xing">*</span><span class="lxr">手机号：</span><input type="text"></span><br>
					<span class="r2"><span class="xing">*</span><span class="lxr">省份城市:</span>
						<select>
							<option>请选择省</option>
							<option>江苏省</option>
							<option>山东省</option>
							<option>广东省</option>
						</select>
						<select>
							<option>请选择市</option>
							<option>南京市</option>
							<option>徐州市</option>
							<option>广州市</option>
						</select>
						<select>
							<option>请选择区/县</option>
							<option>雨花台区</option>
							<option>云龙区</option>
							<option>鼓楼区</option>
						</select>
					</span><br>
					<span class="r3"><span class="xing">*</span><span class="lxr">详细地址：</span><input type="text"></span><br>
				</div>
			</div>
			<div class="ty">
				<div class="ty_left">
					<div class="dui" onclick="bc()">√</div><span class="yd">我已完成阅读</span>
					<span class="yd2">《火车票平台用户协议》</span><span class="yd2">《保险经纪委托合同及客户告知书》</span>
					<span class="yd">并接受所有条款</span>
				</div>
				<input type="submit" value="同意以上条款，提交订单" class="ty_right">
			</div>
		</div>
			<div class="tsall">
			 	<div class="ts">
			 		<span  class="h"><img src="image/train_ts.jpg" id="ts"><span>温馨提示</span></span>
			 		<span class="sp">自取票（预售期一般是30天）</span><br>
			 		<span class="sp">6:00-23:00支付完成，2小时内通知购票</span><br>
			 		<span class="sp">结果；</span><br>
			 		<span class="sp">23:00-6:00支付完成，早6:00点后通知</span><br>
			 		<span class="sp">购票结果。</span><br>
			 		<span class="sp2">短信通知：</span>
			 		<span class="sp">购票后将短信通知是否购票成功，如因运</span><br>
			 		<span class="sp">营商网关延迟可能导致您无法及时接受短</span><br>
			 		<span class="sp">信，请到我的订单中查看出票情况。</span><br>
			 		<span class="sp2">退票须知：</span>
			 		<span class="sp">在线退票时间：6:00-22:50，退款将于1-</span><br>
			 		<span class="sp">7个工作日退回原支付账户；</span><br>
			 		<span class="sp">已取纸质票或离发车时间30分内，请前往车</span><br>
			 		<span class="sp">站窗口办理退票；</span><br>
			 		<span class="sp">车站窗口退票或改签，退款7-15个工作日</span><br>
			 		<span class="sp">左右退回原支付账号。</span><br>
			 		<a class="sp3">更多>></a>
			 	</div>
			 </div>
			 <div class="price1" style="display: block;">
			 	<h1 class="jsxx">结算信息</h1>
			 	<div class="myprice">
			 		<span class="cpq">车票价格</span><span class="money">￥${trainSeat.price}x1</span>			 		
			 	</div>
			 	<div class="myprice">
			 	   <span class="seatt">座位类型:</span><span class="seattype"><input  style="width:50px" type="text"  class="seattt" name="seattype" value="${trainSeat.seatType }" readonly="readonly"></span>
			 	</div>			 	
			 </div>
			  <div class="price2" style="display: none;">
			 	<h1 class="jsxx">结算信息</h1>
			 	<div class="myprice">
			 		<span class="cpq">车票价格</span><span class="money">￥46.5x1</span><br>
			 		<span class="cpq">铁路客票代收费</span><span class="money1">￥5x1</span><br>
			 		<span class="cpq">配送费</span><span class="money2">￥5</span><br>
			 	</div>			 	
			 </div>
			 <div class="allprice">
			 			<span class="ddje">&nbsp&nbsp&nbsp&nbsp订单金额:</span>
			 			</span><span class="je">${trainSeat.price}</span>元
			 		</div>
		</div>
	</form>
</body>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/trainpuy.js"></script>
<script type="text/javascript" src="js/traininfo.js"></script>
</html>