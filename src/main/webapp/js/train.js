$(function() {
	// 城市选择框默认影藏
	$("#citylistfirst").hide();
	$("#citybuttonlist").hide();
	$(".div2").click(function() {
		$(this).parent().toggleClass('close1');
		$(this).parent().toggleClass('open1');
		$(this).toggleClass('close2');
		$(this).toggleClass('open2');
	});
	// 加载城市选择框
	$("#citylistfirst").load("../city_list.jsp");
	$(".inputcitya").focus(function() {
		eq=-1;
		if($("#citybuttonlist").css("display") == "none"){
			$("#citylistfirst").css({
				"top" : "95px",
				"left" : "34px"
			});
			$("#citylistfirst").show();
		}
	});
	$(".inputcityb").focus(function() {
		eq=-1;
		if($("#citybuttonlist").css("display") == "none"){
			$("#citylistfirst").css({
				"top" : "144px",
				"left" : "34px"
			});
			$("#citylistfirst").show();
		}
	});
});
function huanfn() {
	var decity = $("#p1").val();
	var arrcity = $("#p2").val();
	$("#p1").val(arrcity);
	$("#p2").val(decity);
}
window.onclick = function(event) {
	var obj = $("#citylistfirst")[0];
	if (obj != document.activeElement
			&& $(event.target).attr("class") != "inputcitya"
			&& $(event.target).attr("class") != "inputcityb") {
		// 获取包含此组件的所有祖先元素
		var e = $(event.target).parents();
		var isNotInCity = true;
		e.each(function(index, element) {
			if ($(element).css("id") == "city_list_out") {
				isNotInCity = false;
			}
			if ($(element).css("id") != "citybuttonlist") {
				$("#citybuttonlist").hide();
			}
		});
		if (isNotInCity) {
			$("#citylistfirst").hide();
		}
	}
}

