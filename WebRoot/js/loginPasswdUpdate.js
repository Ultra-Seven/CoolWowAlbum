function checkSubmit(){
	var input=document.getElementById("checkCode").value;
	if(input!==""){
		$.ajax({ 
				type: "POST", 
				url: "user/image!userGetCheck.action", 
				data: {check:input}, 
				success: function(data){ 
					if(data=="true"){
						submitUser();
					}
					else{
						$('#ratyService6').modal({
						})
					}
				}
		});
	}
	else{
		$('#ratyService7').modal({
		})
	}
}
function submitUser(){
	var input=$("#account").attr("value");
	var input2=$("#password").attr("value");
	$.ajax({ 
		type: "POST", 
		url: "user/userLogin!userLogin.action", 
		data: {account:input,password:input2}, 
		success: function(data){
			if(data=="wrong"){
				$('#ratyService9').modal({
				})
			}
			else if(data=="forbidden"){
				$('#ratyService8').modal({
				})
			}
			else if(data=="success"){
				window.location.href="user/index.action";
			}
			else{
				window.location.href="user/admin!getUsers.action";
			}
		}
	});
}
function InformationSubmit(){
		var input=document.getElementById("account2").value;
		$.ajax({ 
   			type: "POST", 
   			url: "user/passwdFind!userGetQuestion.action", 
  			data: {account:input}, 
   			success: function(data){ 
   				if(data===""){
   					document.getElementById("ajaxQuestion").style.display="none";
   					document.getElementById("answer").style.display="none";
   					document.getElementById("newPasswd").style.display="none";
   					document.getElementById("answer").value="";
   					document.getElementById("newPasswd").value="";
   					document.getElementById("submit").style.display="none";
   				}
   				else{
     				document.getElementById("ajaxQuestion").innerHTML="密保问题:"+data;
     				document.getElementById("answer").style.display="inline";
     				document.getElementById("ajaxQuestion").style.display="block";
     			}
   			}
		});
	}
	function HasValue(){
		var input=document.getElementById("newPasswd").value;
		if(input!==""){
			document.getElementById("submit").style.display="inline";
		}
		else{
			document.getElementById("submit").style.display="none";
		}
	}
	function PasswdSubmit(){
		var input=document.getElementById("answer").value;
		var input2=document.getElementById("account2").value;
		$.ajax({ 
   			type: "POST", 
   			url: "user/passwdFind!userGetAnswer.action", 
  			data: {account:input2}, 
   			success: function(data){ 
   				if(input===data){
     				document.getElementById("newPasswd").style.display="inline";
     			}
     			else{
     				document.getElementById("newPasswd").style.display="none";
     				document.getElementById("submit").style.display="none";
     				document.getElementById("newPasswd").value="";
     			}
   			}
		});
	}
	function test(){
		var input=document.getElementById("account2").value;
		var input2=document.getElementById("newPasswd").value;
		var input3=document.getElementById("answer").value;
		$.ajax({ 
   			type: "POST", 
   			url: "user/passwdFind!userPasswdUpdate.action", 
  			data: {account:input,answer:input3,newpasswd:input2}, 
   			success: function(data){ 
   				document.getElementById("ajaxQuestion").style.display="none";
   				document.getElementById("answer").style.display="none";
   				document.getElementById("newPasswd").style.display="none";
   				document.getElementById("account2").value="";
   				document.getElementById("answer").value="";
   				document.getElementById("newPasswd").value="";
   				document.getElementById("submit").style.display="none";
   				document.getElementById("outcome").innerHTML=data;
   				sleep(450);
   				$('#ratyService5').modal({
				})
   				
   			}
		});  
	}
	function sleep(d){
  for(var t = Date.now();Date.now() - t <= d;);
}