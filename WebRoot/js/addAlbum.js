function submit(){
	var input=$("#inputName").attr("value");
	$.ajax({ 
		type: "POST", 
		url: "photo/photoAdd!albumAdd.action", 
		data: {name:input}, 
		success: function(data){
			if(data=="success"){
			window.location.href="photo/photoLoad!photoLoad.action";
			}
			else{
				$('#addFail').modal({
				})
			}
		}
	});
}