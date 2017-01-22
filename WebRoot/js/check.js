function checkAccount() {
				var check=true;
				var notice=$("#notice1");
				var value=$("#name").val();
				if (value.length > 10){
					notice.html("too long");
					notice.fadeIn("slow");
					check=false;
				}
				else if (value===""){
					notice.html("not null");
					notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				return check;
			}
			function checkEmail() {
				var check=true;
				var notice=$("#notice2");
				var value=$("#email").val();
				var trueMail=/[\w]+@[\w]+\.+com/;
				if(value==="") {
					notice.html("not null");
					notice.fadeIn("slow");
					check=false;
				}
				else if(!trueMail.test(value)){
					notice.html("wrong input");
					notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				return check;
			}
			function checkPassword() {
				var check=true;
				var notice=$("#notice3");
				var value=$("#password").val();
				var pw=/\W/g;
				if (value===""){
					notice.html("not null");
					notice.fadeIn("slow");
					check=false;
				}
				else if(pw.test(value)){
					notice.html("wrong input");
					notice.fadeIn("slow");
					check=false;
				}
				 else if (value.length > 20){
					notice.html("too long");
					notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				return check;
			}
			function checkRe() {
				var check=true;
				var notice=$("#notice4");
				var value=$("#re").val();
				var password=$("#password").val();
				if(password!==value) {
		    		notice.html("not the same");
		    		notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				
				return check;
			}
			function checkQuestion() {
				var passwdQuestion=document.getElementById("passwdQuestion").value;
				var check=true;
				var notice=$("#notice5");
				if(passwdQuestion==="") {
					notice.html("not null");
					notice.fadeIn("slow");
					check=false;
				}
				else if(passwdQuestion.length>40){
					notice.html("too long");
					notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				return check;
			}
			function checkAnswer() {
				var passwdAnswer=document.getElementById("passwdAnswer").value; 
				var check=true;
				var notice=$("#notice6");
				if(passwdAnswer==="") {
					notice.html("not null");
					notice.fadeIn("slow");
					check=false;
				}
				else if(passwdAnswer.length>40){
					notice.html("too long");
					notice.fadeIn("slow");
					check=false;
				}
				else {
					notice.fadeOut("slow");
				}
				return check;
			}
			$("#name").blur(checkAccount);
			$("#email").blur(checkEmail);
			$("#password").blur(checkPassword);
			$("#re").blur(checkRe);
			$("#passwdQuestion").blur(checkQuestion);
			$("#passwdAnswer").blur(checkAnswer);
			$("#submittwo").click(function() {
				var input=$("#name").val();
				$.ajax({ 
					type: "POST", 
					url: "user/userRegist!checkAccount.action", 
					data: {account:input}, 
					success: function(data){
						if(data=="success"){
							if(checkAccount()&&checkEmail()&&checkPassword()&&checkRe()&&checkQuestion()==true){
								$("#registerForm").submit();
							}
						}
					}
				});
			});