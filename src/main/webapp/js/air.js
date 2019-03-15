
	$(function(){
		$("#oneway_button").css({"background-color":"#42B312","color":"white"});
		$("#toway_button").css({"background-color":"white","color":"green"});
		$("#redate").attr("disabled","disabled");
		
		var nowdate=new Date();
		var year=nowdate.getFullYear();
		var month=nowdate.getMonth()+1;
		var date=nowdate.getDate();
		/* $("#dedate").attr("min","2018-10-10"); */
		if(month<10&&date<10){
			$("#dedate").attr("min",year+"-0"+month+"-0"+date);
		}else if(date<10){
			$("#dedate").attr("min",year+"-"+month+"-0"+date);
		}else if(month<10){
			$("#dedate").attr("min",year+"-0"+month+"-"+date);
		}else{
			$("#dedate").attr("min",year+"-"+month+"-"+date);
		} 
		
		
		if(month<10&&date<10){
			$("#redate").attr("min",year+"-0"+month+"-0"+date);
		}else if(date<10){
			$("#redate").attr("min",year+"-"+month+"-0"+date);
		}else if(month<10){
			$("#redate").attr("min",year+"-0"+month+"-"+date);
		}else{
			$("#redate").attr("min",year+"-"+month+"-"+date);
		}  
		
		var timestamp=nowdate.getTime();
		timestamp=timestamp+24*60*60*5*1000;
		var maxdate=new Date(timestamp);
		var year=parseInt(maxdate.getFullYear());
		var date=maxdate.getDate();
		 var remonth=redate.getMonth(); 
		var month=maxdate.getMonth()+1;
		if(month<10&&date<10){
			$("#dedate").attr("max",year+"-0"+month+"-0"+date);
		}else if(date<10){
			$("#dedate").attr("max",year+"-"+month+"-0"+date);
		}else if(month<10){
			$("#dedate").attr("max",year+"-0"+month+"-"+date);
		}else{
			$("#dedate").attr("max",year+"-"+month+"-"+date);
		} 
		
		
	})

	var imgs=['image/1.jpg','image/3.jpg','image/3.jpg','image/3.jpg','image/3.jpg','image/3.jpg'];
	var count=1;
	setInterval(function(){
		$("#img").css("display","block")
		count=count%6;
		$("#img").attr("src",imgs[count]);
		var cirs=document.getElementsByClassName("cir");
		for(var i=0;i<cirs.length;i++){
			var cir=cirs[i];
			if(count==i){
				cir.style.backgroundColor="#FF8800";
			}else{
				cir.style.backgroundColor="white";
			}
		}
		count++;
	},4000);


	function fn(obj){
		count=obj-1;
		$("#img").attr("src",imgs[count]);
		var cirs=document.getElementsByClassName("cir");
		for(var i=0;i<cirs.length;i++){
			var cir=cirs[i];
			if(count==i){
				cir.style.backgroundColor="#FF8800";
			}else{
				cir.style.backgroundColor="white";
			}
		}
	}


	function oneway(){
		$('#dedate').val("");
		$('#redate').val("");
		$("#oneway_button").css({"background-color":"#42B312","color":"white"});
		$("#toway_button").css({"background-color":"white","color":"green"});
		$("#redate").attr("disabled","disabled");
		/* var date=$('#dedate').val();
		$("#dedate").val("2017-01-01"); */
	}

	function toway(){
		$("#oneway_button").css({"background-color":"white","color":"green"});
		$("#toway_button").css({"background-color":"#42B312","color":"white"});
		$("#redate").removeAttr("disabled");
		var date=$('#dedate').val();
		$('#dedate').val(date);
		var timestamp=Date.parse(date);
		timestamp=timestamp+24*60*60*2*1000;
		var redate=new Date(timestamp);
		var year=parseInt(redate.getFullYear());
		var date=redate.getDate();
		var month=redate.getMonth()+1;
		if(month<10&&date<10){
			$("#redate").val(year+"-0"+month+"-0"+date);
		}else if(date<10){
			$("#redate").val(year+"-"+month+"-0"+date);
		}else if(month<10){
			$("#redate").val(year+"-0"+month+"-"+date);
		}else{
			$("#redate").val(year+"-"+month+"-"+date);
		}
		
		
	}


	function warnfn(){
		$("#wrancontent").css("display","block");
	}

	function removewarn(){
		$("#wrancontent").css("display","none");
	}


	function huanfn(){
		var decity=$("#decity").val();
		var arrcity=$("#arrcity").val();
		$("#decity").val(arrcity);
		$("#arrcity").val(decity);
	}
