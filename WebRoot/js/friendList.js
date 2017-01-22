function test(){
	var input=document.getElementById("creat").value;
	$.ajax({ 
			type: "POST", 
			url: "user/friends!addFriends.action", 
			data: {classification:input}, 
			success: function(data){
				if(data=="success"){
					location.reload();
				}
				else if(data=="already"){
					sleep(450);
	   				$('#ratyService6').modal({
					})
				}
				else{
					sleep(450);
	   				$('#ratyService5').modal({
					})
				}
			}
	});
}
$(".friend").mouseover(function(){
	$(this).css("background-color","gainsboro");
})

$(".friend").mouseout(function(){
	$(this).css("background-color","whitesmoke");
})
$(".friendtype").live("mouseover",function(){
	$(this).css("background-color","gainsboro");
	if($(this).children(":nth-child(2)").children(":nth-child(2)").attr("id")=="weifenlei"){
		
	}
	else{
		$(this).children(":nth-child(4)").css("display","block");
	}
})

$(".friendtype").live("mouseout",function(){
	$(this).css("background-color","whitesmoke");
	$(this).children(":nth-child(4)").css("display","none");
})
$(".friendtype").click(function(){
	if($(this).parent().next().attr("id")=="done"){
		$(this).parent().next().attr("id","doing");
		var a=$(this);
		$(this).parent().next().children().animate({height:"0px"},500);
		$(this).parent().next().fadeTo(200,0.2);
		$(this).parent().next().children().children(":nth-child(1)").children().animate({height:"0px"},500,function(){
			$(this).css("display","none");
			$(this).parent().next().children().css("display","none");
			$(this).parent().next().next().children().css("display","none");
			$(this).parent().next().next().next().children().css("display","none");
			a.parent().next().attr("id","undo");
		});
	}
	else if($(this).parent().next().attr("id")=="undo"){
		$(this).parent().next().attr("id","done");
		$(this).parent().next().fadeTo(500,1);
		$(this).parent().next().children().animate({height:"76px"},500);
		$(this).parent().next().children().children(":nth-child(1)").children().animate({height:"60px"},500);
		$(this).parent().next().children().children(":nth-child(1)").children().css("display","block");
		$(this).parent().next().children().children(":nth-child(2)").children().css("display","block");
		$(this).parent().next().children().children(":nth-child(3)").children().css("display","block");
		$(this).parent().next().children().children(":nth-child(4)").children().css("display","block");
	}

})
$(".href").click(function(){
	$(this).parent().parent().animate({letterSpacing:"-7px"},500);
	$(this).fadeTo(500,0,function(){
		$(this).css("display","none");
		$(this).next().css("display","inline");
		$(this).next().fadeTo(500,1);
		$(this).next().next().css("display","inline");
		$(this).next().next().fadeTo(500,1);
		$(this).parent().parent().animate({letterSpacing:"1px"},800);
	});
})
$(".no").click(function(){
	$(this).parent().parent().animate({letterSpacing:"-7px"},500);
	$(this).fadeTo(500,0);
	$(this).prev().fadeTo(500,0,function(){
		$(this).css("display","none");
		$(this).next().css("display","none");
		$(this).prev().css("display","inline");
		$(this).prev().fadeTo(500,1);
		$(this).parent().parent().animate({letterSpacing:"1px"},500);
	});
})
$(".yes").click(function(){
	var input=$(this).parent().parent().prev().text();
	var input2=$(this).parent().parent().prev().prev().text();
	var first=$(this);
	$.ajax({ 
		type: "POST", 
		url: "user/friends!deleteFriend.action", 
		data: {id:input,classId:input2}, 
		success: function(data){
			first.parent().parent().parent().parent().prev().children().children(":nth-child(2)").children().text("("+data+")");
		}
	});
	$(this).parent().parent().parent().addClass("deleted");
	$(this).parent().parent().parent().animate({height:"0px"},500,function(){
		$(this).css("display","none");
	});
})
$("#oprate").click(function(){
	document.getElementById("creat").value="";
	$('#ratyService4').modal({
	})
})
$("#trash").live("click",function(){
	$(this).parent().animate({opacity:"0",marginLeft:"30%"},500,function(){
		$(this).css("display","none");
	});
	$(this).parent().prev().animate({opacity:"0.4"},500);
	$(this).parent().prev().prev().animate({marginLeft:"10%",opacity:"0.4"},500);
	$(this).parent().prev().prev().prev().css("display","block");
	$(this).parent().prev().prev().prev().animate({marginLeft:"1%",opacity:"1"},500);
})
$("#pencil").live("click",function(){
	$(this).parent().animate({opacity:"0",marginLeft:"30%"},500,function(){
		$(this).css("display","none");
	});
	$(this).parent().prev().prev().children(":nth-child(1)").css("display","block");
	$(this).parent().prev().prev().children(":nth-child(2)").css("display","none");
	$(this).parent().prev().prev().children(":nth-child(1)").focus();
})
$("#changeName").live("blur",function(){
	$(this).css("display","none");
	$(this).next().css("display","block");
	var input=$(this).attr("value");
	var input2=$(this).parent().prev().attr("id");
	var get=$(this);
	$.ajax({ 
		type: "POST", 
		url: "user/friends!rename.action", 
		data: {name:input,classId:input2}, 
		success: function(data){
			if(data=="success"){
				get.next().text(input);
				get.attr("value",input);
			}
		}
	});
	$(this).parent().next().next().css("display","block");
	$(this).parent().next().next().animate({opacity:"1",marginLeft:"0px"},500);
})
$("#remove").live("click",function(){
	$(this).parent().next().animate({marginLeft:"3%",opacity:"1"},500);
	$(this).parent().next().next().animate({opacity:"1"},500);
	$(this).parent().next().next().next().css("display","block");
	$(this).parent().next().next().next().animate({opacity:"1",marginLeft:"0px"},500);
	$(this).parent().animate({marginLeft:"-20%",opacity:"0"},500,function(){
		$(this).css("display","none");
	});
})
$("#ok").live("click",function(){
	var input=$(this).parent().attr("id")
	var get=$(this);
	$.ajax({ 
		type: "POST", 
		url: "user/friends!deleteFriendList.action", 
		data: {classId:input}, 
		success: function(data){
			if(data=="no"){
				location.reload();
			}
			else{
				get.parent().parent().animate({height:"0px"},500,function(){
					$(this).css("display","none");
				});
			}
		}
	});
})
$(".tiaozhuanyemian").live("click",function(){
	var input=$(this).parent().parent().parent().attr("id");
	alert(input);
	$.ajax({ 
		type: "POST", 
		url: "visit!visitFriend.action", 
		data: {id:input}, 
		success: function(data){
			window.location.href="photo/photoLoad!photoLoad.action";
		}
	});
})
function sleep(d){
  for(var t = Date.now();Date.now() - t <= d;);
}