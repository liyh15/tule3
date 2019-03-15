$(".stopp").click(function(){
    var a= $(this).parent().parent().parent().next().css("display");
    if(a=="block")
    	{
    	  $(this).parent().parent().parent().next().css("display","none");
    	}
    else
    	{
    	 $(this).parent().parent().parent().next().css("display","block");
    	}
   
});
function huanfn(){
	var decity=$("#start").val();
	var arrcity=$("#dest").val();
	$("#start").val(arrcity);
	$("#dest").val(decity);
}
function up(){
	$(".pkg_filter_item").css("height","105px");
	$(".slideup").css("display","none");
	$(".slidesign").css("display","block");
}
function sign(){
	$(".pkg_filter_item").css("height","308px");
	$(".slideup").css("display","block");
	$(".slidesign").css("display","none");
}
function moveon(){
	$(".APP_buy_detail").css("display","block");
}
function remove(){
	$(".APP_buy_detail").css("display","none");
}
/*function timebig(){
	$(".change1").css("font-size","150%");
}
function timenormal(){
	$(".change1").css("font-size","100%");
}*/
var count=0;
function change1(){
	count++;
	var c=count%2;
	if(c==0){
		$(".pkg_sort_item_id_1").css("color","#2e9900");
		$(".pkg_sort_item_1 .icon").css("background","url('image/train_desc.jpg')");
	}else{
		$(".pkg_sort_item_id_1").css("color","black");
		$(".pkg_sort_item_1 .icon").css("background","url('image/train_up.jpg')");
	}
}
var count1=0;
function change2(){
	count1++;
	var c=count1%2;
	if(c==0){
		$(".pkg_sort_item_id_2").css("color","black");
		$(".pkg_sort_item_2 .icon").css("background","url('image/train_up.jpg')");
	}else{
		$(".pkg_sort_item_id_2").css("color","#2e9900");
		$(".pkg_sort_item_2 .icon").css("background","url('image/train_desc.jpg')");
	}
}
var count2=0;
function change3(){
	count2++;
	var c=count2%2;
	if(c==0){
		$(".pkg_sort_item_id_3").css("color","black");
		$(".pkg_sort_item_3 .icon").css("background","url('image/train_up.jpg')");
	}else{
		$(".pkg_sort_item_id_3").css("color","#2e9900");
		$(".pkg_sort_item_3 .icon").css("background","url('image/train_desc.jpg')");
	}
}
var count3=0;
function change4(){
	count3++;
	var c=count3%2;
	if(c==0){
		$(".pkg_sort_item_id_4").css("color","black");
		$(".pkg_sort_item_4 .icon").css("background","url('image/train_up.jpg')");
	}else{
		$(".pkg_sort_item_id_4").css("color","#2e9900");
		$(".pkg_sort_item_4 .icon").css("background","url('image/train_desc.jpg')");
	}
}

// 根据火车类型筛选火车
$(".tn").click(function(){
	var type=$(event.target).attr("tname"); // 获得火车名称的前缀K G D T
	$(".checi").each(function(index,element){
			$(element).parent().parent().hide();
		});   
	var isNoChoose = true;
	$(".tn").each(function(index,element){
		var id = $(element).attr("id");
		if($(element).attr("checked")){
			isNoChoose = false;
			$(".checi").each(function(index,element){
				var text = $(element).children().eq(0).text().substring(0,1);
				if(text == id){
					$(element).parent().parent().show();
				}          
			}); 
		}
	});
	if(isNoChoose){
		$(".checi").parent().parent().show();
	}
});

// 根据座位类型筛选火车
$(".zw").click(function(){
	$(".J_Sleeper").parent().hide();
	var isNoChoose = true;
	$(".zw").each(function(index,element){
		if($(element).attr("checked")){
			isNoChoose = false;
			var lab = $($(element).next()).text();
			$(".J_Sleeper").each(function(index,element){
				if($(element).text() == lab){
					$(element).parent().show();
				}
			});
		}		
	});
	if(isNoChoose){
		$(".J_Sleeper").parent().show();
	}
	$(".price").each(function(index,element){
		var ishide = true;
		$(element).children().each(function(index,element){
			if($(element).css("display") != "none"){
				ishide=false;
			}
		});
		if(ishide){
			$(element).parent().parent().hide();
		}else{
			$(element).parent().parent().show();
		}
	});
});

function dtime(time1,time2){
	$(".startTime").each(function(index,element){
		 var tt=parseInt($(element).text().substring(0,2));
		 if(tt >= time1 && tt <= time2){
			 $(element).parent().parent().parent().show();
		 }
	});
}

$(".gt").click(function(){
	$(".checi").parent().parent().hide();
	var a = true;
	$(".gt").each(function(index,element){
		if($(element).attr("checked")){
			a = false;
			if(index == 0){
				dtime(0,6);
			}else if(index == 1){
				dtime(6,12);
			}else if(index == 2){
				dtime(12,18);
			}else if(index == 3){
				dtime(18,24);
			}
		}
	});
	if(a){
		$(".checi").parent().parent().show();
	}
});

function dtime2(time1,time2){
	$(".change2").each(function(index,element){
		 var tt=parseInt($(element).text().substring(0,2));
		 if(tt >= time1 && tt <= time2){
			 $(element).parent().parent().parent().parent().show();
		 }
	});
}

$(".at").click(function(){
	$(".checi").parent().parent().hide();
	var a = true;
	$(".at").each(function(index,element){
		if($(element).attr("checked")){
			a = false;
			if(index == 0){
				dtime2(0,6);
			}else if(index == 1){
				dtime2(6,12);
			}else if(index == 2){
				dtime2(12,18);
			}else if(index == 3){
				dtime2(18,24);
			}
		}
	});
	if(a){
		$(".checi").parent().parent().show();
	}
});

// 在列车详情页搜索列车
function searchTrain(){
	var start = $("#start").val();
	var end = $("#dest").val();
	var date = $("#date").val();
    location.href = "trainseacher?startArea="+start+"&endArea="+end+"&date="+date; 
}





