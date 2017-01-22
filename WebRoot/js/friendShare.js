$(".moveZX").live("mouseover",function(){
	var get=$(this).children(":nth-child(1)");
	get.stop();
	get.next().stop();
	get.next().next().stop();
	get.animate({marginTop:"-20px",opacity:"0.8"},500);
	get.next().animate({opacity:"1",height:"33px"},500);
	get.next().next().animate({opacity:"0.8"},500);
});
$(".moveZX").live("mouseout",function(){
	var get=$(this).children(":nth-child(1)");
	get.stop();
	get.next().stop();
	get.next().next().stop();
	get.animate({marginTop:"5%",opacity:"1"},500);
	get.next().animate({opacity:"0",height:"0px"},500);
	get.next().next().animate({opacity:"0"},500);
});
$(".aixin").live("click",function(){
	var get=$(this).children();
	if(!get.hasClass("xc-liked")){
		get.addClass("xc-liked");
		var input=$(this).parent().attr("id");
		$.ajax({ 
			type: "POST", 
			url: "user/share!addFriendShare.action", 
			data: {pictureId:input}, 
			success: function(data){
				get.parent().parent().parent().attr("id",data);
			}
		});
		get.parent().animate({marginTop:"-183px"},100);
		get.parent().animate({marginTop:"-177px"},200);
		get.parent().animate({marginTop:"-180px"},100);
	}
	else{
		get.removeClass("xc-liked");
		var input=$(this).parent().parent().attr("id");
		$.ajax({ 
			type: "POST", 
			url: "user/share!deleteFriendShare.action", 
			data: {id:input}, 
			success: function(data){
			}
		});
	}
});