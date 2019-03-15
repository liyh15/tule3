function fn2(){
		var a=document.getElementById("one");
		alert(a);
	}
	
	function fn1(){
		var in11=document.getElementById("in1");
		var in22=document.getElementById("in2");
		var a=in11.value;
		var b=in22.value;
		in22.value=a;
		in11.value=b;
	}
	onload=function(){
		
		var imgs=document.getElementById("nav_right").getElementsByTagName("img");
		for(var i=0;i<imgs.length;i++){
			imgs[i].style.left=i*767+"px";
		}
	}
	var mouseId;	
	mouseoff=function(){
		mouseId=setInterval(move,1000);
		Console.log(1);
	}
	mouseon=function(){
		clearInterval(mouseId);
		Console.log(2);
	}
	
		var timeId=setInterval(function(){
			var count=0;
			var imgs=document.getElementById("nav_right").getElementsByTagName("img");
			for(var i=0;i<imgs.length;i++){
				var img  = imgs[i];
				var a=parseInt(img.style.left);
				if(a<=-767){
				a=img.style.left=767*(imgs.length-1);
				
				}
				a-=1;
				img.style.left=a+"px";
			}
		},10);
	function fn2(){
		var in11=document.getElementById("in11");
		var in22=document.getElementById("in22");
		var a=in11.value;
		var b=in22.value;
		in22.value=a;
		in11.value=b;
	}
	function fn1(z){
		var s = document.getElementsByClassName("nav_top2");
		for(var i=0;i<s.length;i++){
			var d=s[i];
			
			d.style.borderBottomColor="white";
			
		}
	}