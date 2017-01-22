
$(".userlist").mouseover(function(){
	$(this).css("background","ghostwhite");
	$(this).children(":nth-child(2)").css("color","rgb(46, 52, 84)");
	$(this).children(":nth-child(4)").css("color","rgb(46, 52, 84)");
	$(this).children(":nth-child(4)").children().children().css("color","rgb(46, 52, 84)");
	$(this).children(":nth-child(5)").children().children().css("color","rgb(46, 52, 84)");
	if($(this).children(":nth-child(3)").attr("id")!="wrong"){
		$(this).children(":nth-child(3)").css("color","rgb(46, 52, 84)");
		$(this).children(":nth-child(3)").children().children().css("color","rgb(46, 52, 84)");
	}
	else{
		$(this).children(":nth-child(3)").css("color","red");
		$(this).children(":nth-child(3)").children().children().css("color","red");
	}
})
$(".userfriendlist").mouseover(function(){
	$(this).css("background","ghostwhite");
	$(this).children(":nth-child(1)").css("color","rgb(46, 52, 84)");
	if($(this).children(":nth-child(2)").attr("id")!="wrong"){
		$(this).children(":nth-child(2)").css("color","rgb(46, 52, 84)");
		$(this).children(":nth-child(2)").children().children().css("color","rgb(46, 52, 84)");
	}
	else{
		$(this).children(":nth-child(2)").css("color","red");
		$(this).children(":nth-child(2)").children().children().css("color","red");
	}
})
$(".userfriendlist").mouseout(function(){
	$(this).css("background","none");
	$(this).children(":nth-child(1)").css("color","white");
	$(this).children(":nth-child(2)").children().children().css("color","white");
})
$(".userdetaillist").mouseover(function(){
	$(this).css("background","ghostwhite");
	$(this).children(":nth-child(1)").css("color","rgb(46, 52, 84)");
	$(this).children(":nth-child(2)").css("color","rgb(46, 52, 84)");
	$(this).children(":nth-child(2)").children().children().css("color","rgb(46, 52, 84)");
	if($(this).children(":nth-child(2)").attr("class")=="userdetailpassword"){
		var text=$(this).children(":nth-child(2)").children().children(":nth-child(2)").attr("value");
		$(this).children(":nth-child(2)").children().children(":nth-child(1)").html(text);
	}
	if($(this).children(":nth-child(2)").attr("class")=="userdetailemail"){
		$(this).children(":nth-child(2)").children().children(":nth-child(2)").css("background-color","ghostwhite");
	}
	if($(this).children(":nth-child(2)").attr("class")=="userdetailpassword"){
		$(this).children(":nth-child(2)").children().children(":nth-child(2)").css("background-color","ghostwhite");
	}
})
$(".userdetaillist").mouseout(function(){
	$(this).css("background","none");
	$(this).children(":nth-child(1)").css("color","white");
	$(this).children(":nth-child(2)").children().children().css("color","white");
	if($(this).children(":nth-child(2)").attr("class")=="userdetailpassword"){
		var text=$(this).children(":nth-child(2)").children().children(":nth-child(2)").attr("value");
		var length=text.length+1;
		var str = new Array(length).join("*");
		$(this).children(":nth-child(2)").children().children().html(str);
	}
	if($(this).children(":nth-child(2)").attr("class")=="userdetailemail"){
		$(this).children(":nth-child(2)").children().children(":nth-child(2)").css("background-color","rgb(93, 109, 126)");
	}
	if($(this).children(":nth-child(2)").attr("class")=="userdetailpassword"){
		$(this).children(":nth-child(2)").children().children(":nth-child(2)").css("background-color","rgb(93, 109, 126)");
	}
})
$(".userlist").mouseout(function(){
	$(this).css("background","none");
	$(this).children(":nth-child(2)").css("color","white");
	$(this).children(":nth-child(3)").css("color","white");
	$(this).children(":nth-child(4)").css("color","white");
	$(this).children(":nth-child(3)").children().children().css("color","white");
	$(this).children(":nth-child(4)").children().children().css("color","white");
	$(this).children(":nth-child(5)").children().children().css("color","white");
})
$(".change").click(function(){
	if($(this).attr("id")=="stateone"){
		$(this).text("Ban");
		$(this).css("color","red");
		$(this).attr("id","statetwo");
		$(this).parent().parent().attr("id","wrong");
		var input=$(this).parent().attr("class");
		$.ajax({ 
			type: "POST", 
			url: "user/admin!updateState.action", 
			data: {state:"1",account:input}, 
			success: function(data){
			}
		});
	}
	else if($(this).attr("id")=="statetwo"){
		$(this).text("Gag");
		$(this).css("color","red");
		$(this).attr("id","statethree");
		var input=$(this).parent().attr("class");
		$.ajax({ 
			type: "POST", 
			url: "user/admin!updateState.action", 
			data: {state:"2",account:input}, 
			success: function(data){
			}
		});
	}
	else if($(this).attr("id")=="statethree"){
		$(this).text("B-G");
		$(this).css("color","red");
		$(this).attr("id","statefour");
		var input=$(this).parent().attr("class");
		$.ajax({ 
			type: "POST", 
			url: "user/admin!updateState.action", 
			data: {state:"3",account:input}, 
			success: function(data){
			}
		});
	}
	else if($(this).attr("id")=="statefour"){
		$(this).text("Normal");
		$(this).css("color","rgb(46, 52, 84)");
		$(this).attr("id","stateone");
		$(this).parent().parent().attr("id","right");
		var input=$(this).parent().attr("class");
		$.ajax({ 
			type: "POST", 
			url: "user/admin!updateState.action", 
			data: {state:"0",account:input}, 
			success: function(data){
			}
		});
	}
	else if($(this).attr("id")=="friends"){
		if($(this).parent().parent().parent().next().next().attr("id")=="donetwo"){
			$(this).parent().parent().parent().next().next().attr("id","undotwo");
			$(this).parent().parent().parent().next().next().fadeTo(200,0.2);
			$(this).parent().parent().parent().next().next().children().animate({height:"0px",borderBottomWidth:"0px"},500);
		}
		if($(this).parent().parent().parent().next().attr("id")=="undo"){
			$(this).parent().parent().parent().next().attr("id","done");
			$(this).parent().parent().parent().next().fadeTo(200,1);
			$(this).parent().parent().parent().next().children().animate({height:"50px",borderBottomWidth:"1px"},500);
		}
		else if($(this).parent().parent().parent().next().attr("id")=="done"){
			$(this).parent().parent().parent().next().attr("id","undo");
			$(this).parent().parent().parent().next().fadeTo(200,0.2);
			$(this).parent().parent().parent().next().children().animate({height:"0px",borderBottomWidth:"0px"},500);
		}
	}
	else if($(this).attr("id")=="details"){
		if($(this).parent().parent().parent().next().attr("id")=="done"){
			$(this).parent().parent().parent().next().attr("id","undo");
			$(this).parent().parent().parent().next().fadeTo(200,0.2);
			$(this).parent().parent().parent().next().children().animate({height:"0px",borderBottomWidth:"0px"},500);
		}
		if($(this).parent().parent().parent().next().next().attr("id")=="undotwo"){
			$(this).parent().parent().parent().next().next().attr("id","donetwo");
			$(this).parent().parent().parent().next().next().fadeTo(200,1);
			$(this).parent().parent().parent().next().next().children().animate({height:"50px",borderBottomWidth:"1px"},500);
		}
		else if($(this).parent().parent().parent().next().next().attr("id")=="donetwo"){
			$(this).parent().parent().parent().next().next().attr("id","undotwo");
			$(this).parent().parent().parent().next().next().fadeTo(200,0.2);
			$(this).parent().parent().parent().next().next().children().animate({height:"0px",borderBottomWidth:"0px"},500);
		}
	}
	else if($(this).attr("id")=="emailinput"){
		var text=$(this).text();
		$(this).css("display","none");
		$(this).next().css("display","inline");
		$(this).next().css("background-color","ghostwhite");
		$(this).next().attr("value",text);
		$(this).next().focus();
	}
	else if($(this).attr("id")=="passwdinput"){
		$(this).css("display","none");
		$(this).next().css("display","inline");
		$(this).next().css("background-color","ghostwhite");
		$(this).next().attr("value");
		$(this).next().focus();
	}
	else if($(this).attr("id")=="delete"){
		var input=$(this).parent().parent().next().attr("id");
		var input2=$(this).parent().parent().next().attr("value");
		var get=$(this);
		$.ajax({ 
			type: "POST", 
			url: "user/admin!deleteFriend.action", 
			data: {friendId:input2,idFriend:input}, 
			success: function(data){
				get.parent().parent().parent().animate({height:"0px",borderBottomWidth:"0px"},500,function(){
					location.reload();
				});
			}
		});
	}
})
$(".emailinputtwo").blur(function(){
	var text=$(this).attr("value");
	$(this).css("display","none");
	$(this).prev().css("display","inline");
	$(this).prev().text(text);
	var input=$(this).next().attr("id");
	$.ajax({ 
		type: "POST", 
		url: "user/admin!updateEmail.action", 
		data: {account:input,email:text}, 
		success: function(data){
		}
	});
})
$(".passwdinputtwo").blur(function(){
	var text=$(this).attr("value");
	$(this).css("display","none");
	$(this).prev().css("display","inline");
	$(this).prev().text(text);
	var input=$(this).next().attr("id");
	$.ajax({ 
		type: "POST", 
		url: "user/admin!updatePasswd.action", 
		data: {account:input,passwd:text}, 
		success: function(data){
		}
});
})


