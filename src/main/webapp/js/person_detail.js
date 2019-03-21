$(function() {
	$("input[type='text']").css({"border":"0", "background-color": "#fff"});
	
	$(".info").css({"opacity": "0"});
	$("input[type='text']").attr("disabled", true);
	$("select").attr("disabled", "disabled");
	$("#edit").click(function() {
		$("input[type='text']").attr("disabled", false);
		$("input[type='text']").css({"border": "1px solid #999999"});
		$("select").attr("disabled", false);
		$(".info").css({"opacity": "100"});
		$("#edit").css({"display": "none"});
		$("#edit").siblings("input").css({"display": "inline-block"});	
	});
	$("#save").click(function() {
		alert("保存成功");
	});
	$("#cancel").click(function() {
		$(".info").css({"opacity": "0"});
		$("#edit").css({"display": "inline-block"});
		$("#cancel").css({"display": "none"});
		$("#save").css({"display": "none"});
		$("input[type='text']").css({"border":"0", "background-color":"#fff"});
		$("input[type='text']").attr("disabled", true);
		$("select").attr("disabled", true);
	});
	
	// input框中的内容发生变化时触发该事件
	$("#headImg").change(function() {
		// 只上传一张图片, 所以是第一个
		var file = this.files[0];
		// file的文件类型是image/xxx.png, 判断是否是图片
		var mime = file.type.split("/")[0];
		// 判断图片的大小, 计算结果以MB为单位
		var size = Math.round(file.size/1024/1024);
		if(size>3) {
			alert("图片过大, 请选择小于3M的图片");
		} else {
			// 创建一个读取对象
			var reader = new FileReader();
			// 将图片读取为base64格式
			reader.readAsDataURL(file);
			
			 var url="user/putHeadImage.do";
		     //上传文件时必须这样写
			 var data=new FormData($("#form_id")[0]);
		     $.ajax({
		    	    "url":url,
		    	    "data":data,
		    	    "dataType":"json",
		    	    "type":"post",
		    	    "contentType":false, //上传时的固定配置(添加就可以了)
	    	    	"processData":false, //上传时的固定配置(添加就可以了)
		    	    "success":function(data){
		    	    	  alert(data.message);
		    	    	  if(data.state == 200) {
		    	    		  $.ajax({
		    	    			  "url":"user/imageUrl.do" 	    			  
		    	    		  });
		    	    		  $("#headImage").remove();
		    	    		  var head = $("<img alt=\"头像正在加载中...\" src=\"user/imageUrl.do\" id = \"headImage\">");
		    	    		  $("#headImg").before(head);
		    	    		
		    	    	  }
		    	    }
		      });	
		}				
	});
})


