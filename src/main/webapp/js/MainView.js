$(".city a").click(function(){
	$(this).css({"background-color":"#42B312","color":"white"});
	$(this).parent().siblings().each(function(){
		$($(this).children().get(0)).css({"background-color":"white","color":"#404040"});
	});
});