// 城市的数组
var cityArray = [];

// 判断选择城市框是在哪个选择框下面，通过top的定位值来判断
function chooseCityA() {
	var obj = $(event.target).text();
	var top = $("#citylistfirst").css("top");
	if (top == "95px") {
		$(".inputcitya").val(obj);
		// 如果终点站城市文本框为空，则让选择框消失
		if($(".inputcityb").val() == "" || $(".inputcityb").val() == null){
			$("#citylistfirst").css("top","144px");
		}else{
			$("#citylistfirst").hide();
		}
	} else {
		$(".inputcityb").val(obj);
		// 如果起点站城市文本框为空，则让选择框消失
		if($(".inputcitya").val() == "" || $(".inputcitya").val() == null){
			$("#citylistfirst").css("top","95px");
		}else{
			$("#citylistfirst").hide();
		}
	}	
}

// 从缓存中读取城市的数据
$(function() {
	$("#city_list_body").children().hide();
	$("#city_list_body").children().eq(0).show();
	$.ajax({
		"url" : "../staticdata/getCity.do",
		"type" : "POST",
		"dataType" : "json",
		"success" : function(data) {
			for (var i = 0; i < data.param.length; i++) {
				// 获得城市的集合，通过城市的拼音的首字母来进行分类
				var a = data.param[i].pinyin.toUpperCase().substring(0, 1);
				// 通过首字母进行排序然后存到对应的列表中
				$("#" + a).append(
						$("<span class='city' onclick='chooseCityA()'><a>"
								+ data.param[i].name + "</a></span>"));
				// 将所有的城市对象加入到数组中
				cityArray[i] = data.param[i];
			}
		}
	});
});

// 出发城市输入框改变的时候出发的事件		
$(".inputcitya").keyup(function() {
					var summary = $(this).val();
					$("#citybuttonlist").show();
					$("#citylistfirst").hide();
					$("#citybuttonlist").css({
						"top" : "95px",
						"left" : "34px"
					});
					$("#citybuttonlist").html("");
					var hasCity = false;
					for (var i = 0; i < cityArray.length; i++) {
						if (summary == "" || summary == null) {
							$("#citylistfirst").show();
							$("#citybuttonlist").hide();
						}else if (cityArray[i].pinyin.indexOf(summary) == 0
								|| cityArray[i].name.indexOf(summary) == 0
								&& summary != "") {
							$("#citybuttonlist")
									.append(
											$("<p style='cursor: pointer' onclick='chooseCityB("
													+ "\""
													+ cityArray[i].name
													+ "\""
													+ ")'>"
													+ cityArray[i].name
													+ "("
													+ cityArray[i].pinyin
													+ ")"
													+ "</p>"));
							hasCity = true;
						}
					}
					if (!hasCity) {
						$("#citybuttonlist").hide();
						eq = -1;
					}
				});

// 判断选择城市下拉菜单在哪个选择框下面，通过top的定位置来判断
function chooseCityB(cityName) {
	var top = $("#citybuttonlist").css("top");
	if (top == "95px") {
		$(".inputcitya").val(cityName);
	} else {
		$(".inputcityb").val(cityName);
	}
	$("#citybuttonlist").hide();
}

// 到达城市输入框改变的时候出发的事件
$(".inputcityb").keyup(function() {
			var summary = $(this).val();
			$("#citybuttonlist").show();
			$("#citylistfirst").hide();
			$("#citybuttonlist").css({
				"top" : "144px",
				"left" : "34px"
			});
			$("#citybuttonlist").html("");
			var hasCity = false;
			for (var i = 0; i < cityArray.length; i++) {
				if (summary == null || summary == "") {		
					$("#citylistfirst").show();
					$("#citybuttonlist").hide();
				}else if (cityArray[i].pinyin.indexOf(summary) == 0
						|| cityArray[i].name.indexOf(summary) == 0
						&& summary != "") {
					$("#citybuttonlist").append(
							$("<p onclick='chooseCityB(" + "\""
									+ cityArray[i].name + "\"" + ")'>"
									+ cityArray[i].name + "("
									+ cityArray[i].pinyin + ")" + "</p>"));
					hasCity = true;
				}
			}
			if (!hasCity) {
				$("#citybuttonlist").hide();
				eq = -1;
			}
		});

// 通过城市列表框来选择城市
$("#city_list_title>ul>li").each(function(index, element) {
	$(element).click(function() {
		$(this).css({
			"color" : "#FFFFFF",
			"background-color" : "#11d311"
		});
		$(this).siblings().css({
			"color" : "#000000",
			"background-color" : "#e5e5e5"
		});
		$("#city_list_body").children().hide();
		$("#city_list_body").children().eq(index).show();
	});
});

var eq = -1;
// 获得键盘的ascall码值
$(document).keyup(
		function(event) {
			if (event.keyCode == 13) {
				// 如果是回车的话判断是否选择了城市,回车键的ascall码值是13
				if ($("#citybuttonlist").css("display") != "none") {
					// 判断是哪个城市文本框选择的
					if ($("#citybuttonlist").css("top") == "95px") {
						var length=$("#citybuttonlist").children().eq(eq).text().indexOf("(");
						if($(".inputcityb").val() == "" || $(".inputcityb").val() == null){
						$(".inputcitya").val($("#citybuttonlist").children().eq(eq).text().substring(0,length));
						$("#citylistfirst").css({
							"top" : "144px",
							"left" : "34px"
						});
						$("#citylistfirst").show();
						$(".inputcityb").focus();		
						}				
					} else {
						var length=$("#citybuttonlist").children().eq(eq).text().indexOf("(");
						$(".inputcityb").val($("#citybuttonlist").children().eq(eq).text().substring(0,length));
						if($(".inputcitya").val() == "" || $(".inputcitya").val() == null){
							$("#citylistfirst").css({
								"top" : "95px",
								"left" : "34px"
							});
							$("#citylistfirst").show();
							$(".inputcitya").focus();
						}						
					}
					$("#citybuttonlist").hide();
				}
			}
			if (event.keyCode == 40 && $("#citybuttonlist").css("display") != "none") {
				eq++;
				if (eq == $("#citybuttonlist").children().length) {
					eq--;
				}
				$("#citybuttonlist").children().css("background-color",
						"#FFFFFF");
				$("#citybuttonlist").children().eq(eq).css("background-color",
						"#63ca33");
			}

			if (event.keyCode == 38 && $("#citybuttonlist").css("display") != "none") {
				eq = eq - 1;
				if (eq < 0) {
					eq++;
					$("#citybuttonlist").children().eq(eq).css(
							"background-color", "#63ca33");
				}
					$("#citybuttonlist").children().css("background-color",
							"#FFFFFF");
					$("#citybuttonlist").children().eq(eq).css(
							"background-color", "#63ca33");
			    if($("#citybuttonlist").css("top") == "95px"){
			    	 moveEnd($(".inputcitya").get(0));
			    }else{
			    	 moveEnd($(".inputcityb").get(0));
			    }			   
			}
		});

// 将文本框光标移动到最后面
function moveEnd(obj){
    var len = obj.value.length; 
    if (document.selection) { 
        var sel = obj.createTextRange(); 
        sel.moveStart('character',len); //设置开头的位置
        sel.collapse(); 
        sel.select(); 
    } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') { 
        obj.selectionStart = obj.selectionEnd = len; 
    } 
} 