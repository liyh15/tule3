var count=1;
var bcc=0;
var sss=0;
var str;
var isAllow=false;
var isSafe=false;
var seatType;
var falseDes = null; // 乘客行程方面的错误描述
$(function(){
	
	sss=parseInt($(".je").text());
	isAllow=false;
	$(".dui").css("background-color","white");
	$(".ty_right").css("background-color","#ddd");	
});


function bc(){
	var c1=bcc%2;
	if(c1==0){
		$(".dui").css("background-color","#FF8800");
		$(".ty_right").css("background-color","#FF9419");
		isSafe=true;
		bcc++;
	}else{
		isSafe=false;
		$(".dui").css("background-color","white");
		$(".ty_right").css("background-color","#ddd");
		bcc++;
	}	
}

//提交订单时所触发的事件(用于判断用户填写信息是否正确)
function allowConfirm() {
	
	
	var types = new Array();
	var numbers = new Array();
	// 判断名字用户名是否为空
	var name=$('input[name="username"]');
	$.each(name,function(index,value){
		if($(value).val()=="" || $(value).val()==null)
			{
			  $(value).next().css("display","inline");
			  isAllow=false;
			  falseDes=null;
			}
		else
			{
			$(value).next().css("display","none");
			}
	});
	
	// 判断联系电话不能为空
	var phone=$('input[name="phone"]');
	if(phone.val()=="" || phone.val()==null)
		{
		   phone.next().css("display","inline");
		   isAllow=false;
		   falseDes=null;
		}
	else
		{
		   phone.next().css("display","none");		
		}	
	
	// 判断用户信息是否重复
	// 判断证件类型和证件号码是否正确
	var bodytype=$('select[name="bodytype"]');
	
	$.each(bodytype,function(index,value){
		
		$(value).next().next().next().next().css("display","none");
		$(value).next().next().next().css("display","none");
		$(value).next().next().css("display","none");	
		var username = $(value).parent().prev().prev().prev().children().eq(1);
		var number=$(value).next();
		var name;
		var type;
		var code;
		name = username.val();
		type = $(value).val();
		code = number.val();
		// 将遍历得到的证件类型和证件号码加入到数组里面
		types[index] = type;
		numbers[index] = code;
	});
	
	// 判断乘客信息不能重复
	var forBoolean  = false; // 从内层for循环终止外层for循环的布尔值
	for(var i = 0; i < numbers.length; i++) {
		if(forBoolean) {
			break;
		}
		for(var j = 0; j < numbers.length; j++) {
			if(j != i) {
				if(types[i] == types[j] && numbers[i] == numbers[j]) {
					// 如果有两个信息相等的就不允许订单生成
					alert("用户信息重复，请重新填写");
					isAllow=false;
					forBoolean=true;
					falseDes=null;
					break;
				}
			}
		}
	}
	
	 $.each(bodytype,function(index,value){
			
			$(value).next().next().next().next().css("display","none");
			$(value).next().next().next().css("display","none");
			$(value).next().next().css("display","none");			
			if($(value).val()=="请选择证件类型")
				{
				  $(value).next().next().next().css("display","none");
				  $(value).next().next().css("display","inline");
				  isAllow=false;
				  falseDes=null;
				}
			else
				{
				$(value).next().next().css("display","none");
				$(value).next().next().next().css("display","none");
				var number=$(value).next();
				if(number.val()=="" || number.val()==null)
					{				    
					   number.next().next().css("display","inline");
					   isAllow=false;
					   falseDes=null;
					}
				}
	 });
	 if(!isAllow) {
			// 如果在之前的判断不被允许
			if(falseDes != null) {
				alert(falseDes);
			} else {
				alert("请输入正确的用户信息");
			}
	}	
	return isAllow&&isSafe;
}

// 可以让js睡眠
function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
		return;
	    }
}


function addfn(){
				count++;
				$(".money").text("￥"+sss+"x"+count);	
				 $(".money1").text(5+"x"+count);
				if($(".left_zqp").css("display")=="none")  /*有送票的时候*/
					{					
					   var a=sss*count;
					   var b=5*count+5;
					   var c=a+b;
					   $(".je").text(c);	   
					}
				else
					{
					  var a=sss*count;
					  $(".je").text(a);	  
					}
				if(count<=5){
					var div =$("<div class='personinfo'></div>");
					$(".tjck").before(div);
					var divnum=$("<div class='num'><span>"+"第"+"<strong>"+count+"</strong>"+"位"+"</span><br></div>");
					var divname=$("<div class='name'><span class='left'>姓名：</span><input type='text'name='username'class='username'onblur='checkPersonalTwo(this)'><span style='color:red;display:none'>姓名不能为空</span><img src='image/train_yc.jpg' onmouseover='xm1(this)' onmouseout='xm2(this)'><div class='xm' style='display: none;'>1.乘客姓名与证件号必须与乘车时使用证件上的名字和号码一致，如有中文名，请填写中文名。<br>2.如名字中包含生僻字可直接输入拼音代替。例如：'王鬳'可输入为'王yan'。<br>3.姓名中最多输入不超过30个字符(一个汉字记为2个字符)，如果超过30个字符，请按姓名中第一个汉字或者英文字符开始顺序连续输入30个字符(空格字符不输入)。<br>4.姓名中有'.'或'。'时，请仔细辨认身份证原件上的'.'或'。',正确输入。</div></div>");
					var span=$("<span class='lx'>成人票</span>");
					var select=$("<div class='zjlx'><span class='left'>证件类型：</span><select class='select'name='bodytype'onchange = 'checkPersonThree(this)'><option>请选择证件类型</option><option>二代身份证</option><option>军官证</option></select><input type='text'name='number'class='numberCode' onblur = 'checkPersonal(this)'><span style='color:red;display:none'>请选择证件类型</span><span style='color:red;display:none'>证件号码不能为空</span><span style='color:red;display:none'>请输入正确的用户信息</span><img src='image/train_yc.jpg' onmouseover='zjxx1(this)' onmouseout='zjxx2(this)'><div class='zjxx' style='display: none;'>请您仔细核对后输入，如填写错误，可能导致出票错误，产生退票费用！</div></div>");
					var divtj=$("<div class='tj'><img src='image/train_tj.jpg' class='jia' onclick=''><span style='font-size: 12px;'>添加儿童票(暂未开放)</span><span style='font-size: 12px;margin-left: 55px;'>每名成年乘客可免费携1名1.2米的儿童，该儿童可不用购票</span><span onclick='gpsm(this)' style='font-size: 12px;margin-left: 5px;color: #56AC32;cursor: pointer;'>购票说明<img class='tjsm'></span><div class='gpsm' style='display: none;'>&nbsp&nbsp&nbsp&nbsp&nbsp低于1.5米低于1.5米儿童可购儿童票，但须跟成人票一起购买，使用同行成人证件购票并凭此证件取票。高于1.5米高于1.5米的儿童须购全价票。如果儿童无有效证件，则只能在线下售票窗口购买。儿童票按实际订单票价收取，出票成功后，如产生差价退款，会在3-7个工作日返回您的支付账户。购买儿童票时，乘车儿童有有效身份证件的，请填写本人有效身份证件信息。乘车儿童没有有效身份证件的，使用同行成年人的有效身份证件信息；购票时不受第一条限制，但购票后、开车前须办理换票手续方可进站乘车。备注：请根据儿童实际身高购票，途牛不承担因儿童身高与所购车票不符而无法进站的责任。</div></div>");
					var del=$("<div style='width:35px;background:#ddd;padding:5px;margin-left:800px;margin-bottom:10px;cursor:pointer;' onclick='del(this)'>删除</div>");
					var br=$("<br>");
					$(div).append(divnum);
					$(div).append(divname);
					$(div).append(br);
					$(div).append(span);
					$(div).append(select);
					$(div).append(divtj);
					$(div).append(del);
					$(".tjkc_btn").css("margin-left","230px")
					switch(count){
						case 2:
							 str="3个";
							 break;
						case 3:
							str="2个";
							break;
						case 4:
							str="1个";
							break;
						case 5:
							str="0个";
							break;
					}
					$(".sy").text("还可以添加"+str+"乘客");
				}
			}
function addfn1(){
				count++;
				/* var div=$("<div class='person_name'>李元浩</div>"); */
				if(count<=5){
					var div =$("<div class='personinfo'></div>");
					$(".tjck").before(div);
					var divnum=$("<div class='num'><span>"+"第"+"<strong>"+count+"</strong>"+"位"+"</span><br></div>");
					var divname=$("<div class='name'><span class='left'>姓名：</span><input type='text'name='username' class='username'><img src='image/train_yc.jpg' onmouseover='xm1(this)' onmouseout='xm2(this)'><div class='xm' style='display: none;'>1.乘客姓名与证件号必须与乘车时使用证件上的名字和号码一致，如有中文名，请填写中文名。<br>2.如名字中包含生僻字可直接输入拼音代替。例如：'王鬳'可输入为'王yan'。<br>3.姓名中最多输入不超过30个字符(一个汉字记为2个字符)，如果超过30个字符，请按姓名中第一个汉字或者英文字符开始顺序连续输入30个字符(空格字符不输入)。<br>4.姓名中有'.'或'。'时，请仔细辨认身份证原件上的'.'或'。',正确输入。</div></div>");
					var span=$("<span class='lx'>儿童票</span>");
					var select=$("<div class='zjlx' style='margin-bottom:20px;'><span class='left'>证件类型：</span><select class='select'name='bodytype'><option>请选择证件类型</option><option>二代身份证</option><option>军官证</option></select><input type='text' name='number' class='numberCode'><img src='image/train_yc.jpg' onmouseover='zjxx1(this)' onmouseout='zjxx2(this)'><div class='zjxx' style='display: none;'>请您仔细核对后输入，如填写错误，可能导致出票错误，产生退票费用！</div></div>");
					var br=$("<br>");
					var del=$("<div style='width:35px;background:#ddd;padding:5px;margin-left:800px;margin-bottom:10px;cursor:pointer;' onclick='del(this)'>删除</div>");
					$(div).append(divnum);
					$(div).append(divname);
					$(div).append(br);
					$(div).append(span);
					$(div).append(select);
					$(div).append(del);
					$(".tjkc_btn").css("margin-left","230px")
					switch(count){
					case 2:
						 str="3个";
						 break;
					case 3:
						str="2个";
						break;
					case 4:
						str="1个";
						break;
					case 5:
						str="0个";
						break;
				}
				$(".sy").text("还可以添加"+str+"乘客");
				}
			}
function del(that){
	
	$(that).parent().nextAll().each(function(){
		var a=$(this).children().eq(0).children().eq(0).children().eq(0);
		var value=parseInt($(this).children().eq(0).children().eq(0).children().eq(0).text());
		value--;
		a.text(value);		
	});	
	$(that).parent().remove();	
	count--;
		switch(count){
		case 1:
			str="4个";
			break;
		case 2:
			str="3个";
			break;
		case 3:
			str="2个";
			break;
		case 4:
			str="1个";
			break;
		case 5:
			str="0个";
			break;
		}
		$(".money").text("￥"+sss+"x"+count);	
		if($(".left_zqp").css("display")=="none")  /*有送票的时候*/
			{					
			   var a=sss*count;
			   var b=10*count;
			   var c=a+b;
			   $(".money1").text(5+"x"+count);
			   $(".je").text(c);	   
			}
		else
			{
			 $(".money1").text(5+"x"+count);
			  var a=sss*count;
			  $(".je").text(a);	  
			}
		$(".sy").text("还可以添加"+str+"乘客");	
	}
var c=0;
function gpsm(d){
	c++;
	var c1=c%2;
	if(c1==1){
		$(d).children().css("background-image","url('image/train_tjsm1.jpg')");
		$(d).next().css("display","block");
	}else{
		$(d).children().css("background-image","url('image/train_tjsm.jpg')");
		$(d).next().css("display","none");
	}
}

function square(s){
	$(".square").css("border","1px solid #ddd");
	$(".sp1").css("color","gray");
	$(".sp3").css("color","gray");
	$(s).children().eq(0).css("color","#FC893D");
	$(s).children().eq(3).css("color","#FC893D");
	$(s).children().eq(5).css("color","#FC893D");
	$(s).css("border","1px solid #FC893D");
	var money=parseInt($(s).children().eq(1).text());
	seatType=$(s).children().eq(0).text();
	$(".seattt").val(seatType);
	sss=money;
	$(".money").text("￥"+money+"x"+count);	
	if($(".left_zqp").css("display")=="none")
	{
	   $(".money1").text(5+"x"+count);
	   var moneytwo=money*count+5*count+5;
	   $(".je").text(moneytwo);	   
	}	
	else
		{
		 $(".money1").text(5+"x"+count);
		   var moneytwo=money*count;
		   $(".je").text(moneytwo);	   		
		}
}
function change(){	
	if($(".left_zqp").css("display")=="none")
	{
		
	   var money=parseInt($(".je").text());
	   $(".money1").text(5+"x"+count);
	   var moneytwo=money-5*count-5;
	   $(".je").text(moneytwo);	   
	}		
	$(".left_zqp").css("display","block");
	$(".right_zqp").css("display","none");
	$(".qp_left").css("background","white");
	$(".qp_left").css("border-top","2px solid #2E9900");
	$(".qp_left").css("border-bottom","none");
	$(".qp_left").css("border-right","1px solid #ddd");
	$(".qp_right").css("border-left","none");
	$(".qp_right").css("border-top","none");
	$(".qp_right").css("border-bottom","1px solid #ddd");
	$(".qp_right").css("background","#F8F8F8");
	$(".price1").css("display","block");
	$(".price2").css("display","none");
}
function change1(){
	$(".price1").css("display","none");
	if($(".price2").css("display")=="none")
		{
		  var money=parseInt($(".je").text());
		  $(".money1").text(5+"x"+count);
		  //alert(money)
		  var moneytwo=5*count+money+5;
		  $(".je").text(moneytwo);
		}	
	$(".price2").css("display","block");
	$(".left_zqp").css("display","none");
	$(".right_zqp").css("display","block");
	$(".qp_right").css("background","white");
	$(".qp_right").css("border-top","2px solid #2E9900");
	$(".qp_right").css("border-bottom","none");
	$(".qp_right").css("border-left","1px solid #ddd");
	$(".qp_left").css("border-right","none");
	$(".qp_left").css("border-top","none");
	$(".qp_left").css("border-bottom","1px solid #ddd");
	$(".qp_left").css("background","#F8F8F8");
}

function checkPersonal(sub) {
	
	// 证件号码框失去焦点之后会进行服务器的判断
	var before = $(sub).prev();
	var name;
	var type;
	var code;
	if($(sub).val() == null || $(sub).val() == "") {
		
		 before.next().next().next().css("display","inline");
		 before.next().next().css("display","none");
		 before.next().next().next().next().css("display","none");
		 isAllow=false;			 
	} else {			
		before.next().next().next().css("display","none");
		code = $(sub).val();
		if(before.val()=="请选择证件类型") {
			  
			  before.next().next().next().next().css("display","none");
        	  before.next().next().next().css("display","none");
			  before.next().next().css("display","inline");
			  isAllow=false;
        } else {
        	
        	before.next().next().next().next().css("display","none");
        	before.next().next().css("display","none");
        	type = before.val();
        	var username = $(sub).parent().prev().prev().prev().children().eq(1);  
        	if(username.val() == null || username.val() == "") {     
        		username.next().css("display","inline");
        		isAllow=false;
        	} else {
        		username.next().css("display","none");
        		name = username.val();
        		$.ajax({
            		"url":"user/checkPersonalCode.do",
            		"data":"name="+name+"&type="+type+"&code="+code,
            		"dataType":"json",
            		"type":"post",     		
            		"success":function(data){
            		   if(data.state == 400) {
            			   isAllow = false;
            			   before.next().next().next().next().css("display","inline");
            		   } else if(data.state == 200) {
            			   
            			   before.next().next().next().next().css("display","none");
            			   // 判断证件类型和证件号码是否正确
            			   var bodytype=$('select[name="bodytype"]');
            			   $.each(bodytype,function(index,value){
            					
            					$(value).next().next().next().next().css("display","none");
            					$(value).next().next().next().css("display","none");
            					$(value).next().next().css("display","none");			
            					if($(value).val()=="请选择证件类型")
            						{
            						  $(value).next().next().next().css("display","none");
            						  $(value).next().next().css("display","inline");
            						  isAllow=false;
            						}
            					else
            						{
            						$(value).next().next().css("display","none");
            						$(value).next().next().next().css("display","none");
            						var number=$(value).next();
            						if(number.val()=="" || number.val()==null)
            							{				    
            							   number.next().next().css("display","inline");
            							   isAllow=false;
            							}
            						else
            							{
            							   // 当三个文本框都不为空时，判断用户信息是否正确
            							   number.next().next().css("display","none");
            							   // 进行用户信息是否正确的判断
            							   var username = $(value).parent().prev().prev().prev().children().eq(1); 
            							   var name;
            							   var type;
            							   var code;
            							   name = username.val();
            							   type = $(value).val();
            							   code = number.val();
            							   // 将遍历得到的证件类型和证件号码加入到数组里面
            							   $.ajax({
            				            		"url":"user/checkPersonalCode.do",
            				            		"data":"name="+name+"&type="+type+"&code="+code,
            				            		"dataType":"json",
            				            		"type":"post",     		
            				            		"success":function(data){
            				            		   if(data.state == 400) {
            				            			   isAllow = false;
            				            			   $(value).next().next().next().next().css("display","inline");
            				            			   return isAllow;
            				            		   } else if(data.state == 200) {
            				            			   $(value).next().next().next().next().css("display","none");
            				            			    // 判断乘客是否可以购买当前车票
            				            				// 获取日期安排编号
            				            				var trafficId = $("#trafficId").text();
            				            				$.ajax({
            				            			 		"url":"user/checkPersonalTwo.do",
            				            			 		"data":"trafficId="+trafficId+"&type="+type+"&code="+code,
            				            			 		"dataType":"json",
            				            			 		"type":"post",     		
            				            			 		"success":function(data){
            				            			 		   if(data.state == 400) {
            				            			 			   alert("身份证号"+code+":"+data.message)
            				            			 			   falseDes = "身份证号"+code+":"+data.message;
            				            			 			   isAllow = false;
            				            			 		   } else {
            				            			 			  falseDes = null;
            				            			 			   isAllow = true;
            				            			 		   }
            				            			 	}
            				            				});	            			   
            				            		   }
            				            	}
            				        		});
            							}
            						}				
            				});	
            		   }
            	}
        		});
        	}
        }				
	}   
}

/**
 * 名字的js时间，用来判断用户输入信息的正确性
 * @param tag
 */
function checkPersonalTwo(tag){
	
	// 获取这个名字所对应的证件号码
	var numberCode = $(tag).parent().next().next().next().children().eq(2);

	checkPersonal(numberCode);	
}

// 证件类型的筛选
function checkPersonThree(tag) {
	
	var numberCode = $(tag).next();
	checkPersonal(numberCode);	
}

// 判断用户信息是否重复
function checkIsRepeat() {
	var types = new Array();
	var numbers = new Array();
	// 判断证件类型和证件号码是否正确
	var bodytype=$('select[name="bodytype"]');
	
	$.each(bodytype,function(index,value){
		
		$(value).next().next().next().next().css("display","none");
		$(value).next().next().next().css("display","none");
		$(value).next().next().css("display","none");	
		var username = $(value).parent().prev().prev().prev().children().eq(1);
		var number=$(value).next();
		var name;
		var type;
		var code;
		name = username.val();
		type = $(value).val();
		code = number.val();
		// 将遍历得到的证件类型和证件号码加入到数组里面
		types[index] = type;
		numbers[index] = code;
	});
	
	// 判断乘客信息不能重复
	var forBoolean  = false; // 从内层for循环终止外层for循环的布尔值
	for(var i = 0; i < numbers.length; i++) {
		if(forBoolean) {
			break;
		}
		for(var j = 0; j < numbers.length; j++) {
			if(j != i) {
				if(types[i] == types[j] && numbers[i] == numbers[j]) {
					// 如果有两个信息相等的就不允许订单生成
					alert("用户信息重复，请重新填写");
					isAllow=false;
					forBoolean=true;
					falseDes=null;
					break;
				}
			}
		}
	}
	return isAllow;
}



