$(".pic").live("click",function(){
	var get=$(this).children(":nth-child(2)");
	$(".forPic").children().each(function(){
		$(this).children().removeClass("selected");
		$(this).children().children(":nth-child(2)").css("opacity","0");
		$(this).children().children(":nth-child(2)").css("visibility","hidden");
	});
	if($(this).hasClass("selected")){
		$(this).removeClass("selected");
		get.css("opacity","0");
		get.css("visibility","hidden");
	}
	else{
		$(this).addClass("selected");
		get.css("opacity","1");
		get.css("visibility","visible");
		var input=$(this).children(":nth-child(1)").attr("id");
		$("#picID").attr("value",input);
		$("#submit").attr("href","doodle/edit!full.action?photoId="+input);

	}
})
$("#submit").click(function(){
	if($(this).next().attr("value")!=0){
	}
	else{
		alert("未选择图片");
	}	
})