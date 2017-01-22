$("#check").click(function(){
	if($(this).hasClass("desc-button-upState")){
		$('#changeDes').modal({
		})
	}
});
$("#trash").click(function(){
	if($(this).hasClass("delete-button-upState")){
		$('#deleteEnsure').modal({
		})
	}
});
$(".animate").click(function(){
	$("#outernotice").animate({marginTop:"0px"},1000,function(){
		$("#outernotice").animate({marginTop:"-55px"},1000);
	});
});
function sleep(d){
	  for(var t = Date.now();Date.now() - t <= d;);
}
$(".img-desc").live("click",function(){
	old=$(this).text().trim();
	$(this).css("display","none");
	$(this).next().css("display","inline");
	$(this).next().attr("value",$(this).text().trim());
	$(this).next().focus();
})
$(".nameInput").live("blur",function(){
	var newinput=$(this).attr("value");
	$(this).css("display","none");
	$(this).prev().css("display","block");
	$(this).prev().text($(this).attr("value"));
	$("[id='"+old+"']").attr("data-name",newinput);
	$("[id='"+old+"']").attr("id",newinput);
})