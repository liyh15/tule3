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

function change() {
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