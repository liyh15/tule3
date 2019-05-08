function showSecond() {
	var obj = event.srcElement;
//	console.log(obj);
	var lis = left_navigation.getElementsByClassName("second");
	for(var i=0; i<lis.length; i++) {
		var myLi = lis[i];
		var myUl = myLi.getElementsByTagName("ul")[0];
		// myUl.style.display = "none";
		if(obj == myLi) {
			if(myUl.style.display == "block") {
				myUl.style.display = "none";
				obj.style.backgroundImage = "url(image/DownArror.png)";
			} else {
				myUl.style.display = "block";
				obj.style.backgroundImage = "url(image/UpArror.png)";
			}
		} else {
			myLi.style.backgroundImage = "url(image/DownArror.png)";
			myUl.style.display = "none";
		}
	}
}
// 进行订单筛选的时候改变筛选按钮的颜色和订单列表
function change(num) {
	var obj = event.target;
	var lis = right_navigation.getElementsByTagName("li");
	for(var i=0; i<lis.length; i++) {
		var li = lis[i];
		if(obj == li) {
			obj.style.boxSizing = "border-box";
			obj.style.borderBottom = "3px solid blue";
			obj.style.color = "blue";
		} else {
			li.style.borderBottom = "0";
			li.style.color = "#000";
		}
	}
	$(".remain-time").each(function(index,value){
		
		if(num == 0) {
			// 如果选择全部订单，则显示全部的订单
			$(value).parent().parent().css("display","inline-block");
		} else if(num == 1) {
			// 如果选择代付款，则选择未付款的订单
			$(value).parent().parent().css("display","inline-block");
			if($(value).text().indexOf("未付款") == -1) {
				$(value).parent().parent().css("display","none");
			}
		} else if(num == 2) {
			// 如果选择未出行的订单
			var id = $(value).prev().text();
			$.ajax({
				"url": "order/checkOrderStartTime.do",
			    "data":"id="+id,
			    "dataType":"json",
			    "type":"post",
			    "success":function(data){
		           if(data.state == 400) {
		        	   $(value).parent().parent().css("display","none");
		           } else {
		        	   $(value).parent().parent().css("display","inline-block");
		           }
			    }
			});
		}
		// 还有待点评的订单还没有开始弄
	});	
}
$(function() {
	$(".second").children().eq(1).children().click(function() {
		var obj = $(event.target);
		//获取点击的按钮所对应的target属性对应的值
		var url = obj.attr("target");
		if(url!="#")
			{
			  $("#right").load(url);			
			}		
	});
	
});

// 删除订单的操作
$(".deleteorder").click(function(){
	var id = $(this).attr("oid");
	if(confirm('确实要删除该订单吗?')){
		$.ajax({
			"url": "order/deleteOrder.do",
		    "data":"id="+id,
		    "dataType":"json",
		    "type":"post",
		    "success":function(data){
	           alert(data.message);
	           window.location.reload();
		    }
		});
	}	
});

// 付款操作
$(".operation").click(function(){
	
	var id = $(this).attr("oid");
	if(confirm('确认要付款码?')){
		console.log("aaa");
		$.ajax({
			"url": "user/payForOrder.do",
		    "data":"orderId="+id,
		    "dataType":"json",
		    "type":"post",
		    "success":function(data){
		    	alert(data.message);
		    },
		    "error":function(){
		    	alert("系统异常，请稍后重试");
		    }
		});
		sleep(200);
	}	
});


function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
		return;
	    }
}






