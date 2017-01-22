/*javascript for ucp page
 * created by XuHailong
 */

var passwordReg1 = /^[A-Za-z0-9\!\@\#\$\%\^\&\*\~\.\_\-\+\=\{\[\}\]\|\\\;\:\'\"\<\,\>\.\?\/\`]{6,20}$/ ;	// letters||numbers||signals
var passwordReg2 = /^[0-9]+$/ ;				//all numbers
var id = $('#loggeduserid').html();
var name = $('#loggeduser').html();
var num = $("#currentpage").html();
 
$(document).ready(function()
{
	$('#nav').spasticNav();
	
	function alert_modal(inf)
	{
		$("#modal-text").html(inf);
		$('#infModal').modal('show');
	}
	
	num = $("#currentpage").html()  - 0 + 1 ;
	id = $('#loggeduserid').html();
	name = $('#loggeduser').html();
	
    var hash = document.location.hash;
    var prefix = "tab_";
    if (hash) 
    {
        $('.nav-tabs a[href='+hash.replace(prefix,"")+']').tab('show');
    }

    // Change hash for page-reload
    $('.nav-tabs a').on
    (
    	'shown.bs.tab', function (e) 
    	{
            window.location.hash = e.target.hash.replace("#", "#" + prefix);
    	}
    );
    
	//display default text instead of null
	if($("#profiletext").html()  == "")
		$("#profiletext").html("该用户暂无个人资料。")	
		
	//disable all tags except home if logged user is not owner
	if(!($('#owner').html() == $('#loggeduser').html() ) )
		{
			$('#tab2').hide();
			$('#tab3').hide();
			$('#tab4').hide();
		}
	//active the label of current page
	$(".pagination li:nth-child(" + num + ")").addClass("active");

	
	//modify gender icon
	if($("#userGender").val() == "0" )
		{
			$("#genderIcon").html("♂");
			$("#genderIcon").addClass("male");
			$("#genderIcon").removeClass("female");
		}
	if($("#userGender").val() == "1" )
	{
		$("#genderIcon").html("♀");
		$("#genderIcon").addClass("female");
		$("#genderIcon").removeClass("male");
	}
	//initial gender and description in settings
	$("input[name=gender]").val( [$("#userGender").val() ] );
	$("textarea[name=description]").val($("#userDesc").val() );
	
	//test password
	$("#oldPw").blur
	(function()
	{
		if($("#userPw").val() === $("#oldPw").val() )
		{
			$("#feedback1").removeClass("red");   
			$("#feedback1").addClass("green");
			$("#feedback1").html("密码正确");
		}
		else
		{
			$("#feedback1").removeClass("green");   
			$("#feedback1").addClass("red");
			$("#feedback1").html("密码错误！");
		}
	});
	
	$("#newPw").blur
	(function()
	{
		if(!passwordReg1.test($("#newPw").val() ) || passwordReg2.test($("#newPw").val() ) )
		{ 
			$("#feedback2").html("只能是字母、数字或符号，长度必须在6-20位之间，且不得是纯数字");
			$("#feedback2").removeClass("green");
			$("#feedback2").addClass("red");
		}
		else
		{
			if($("#newPw").val() === $("#oldPw").val() )
			{
				$("#feedback2").removeClass("green");   
				$("#feedback2").addClass("red");
				$("#feedback2").html("密码不能与之前相同！");
			}
			else
			{
				$("#feedback2").removeClass("red");  
				$("#feedback2").addClass("green");  
				$("#feedback2").html("输入正确");
			}
		} 
	});
	$("#newPw2").blur
	(function()
	{
		if($("#newPw").val() !== $("#newPw2").val() )
		{ 
			$("#feedback3").removeClass("green");
			$("#feedback3").addClass("red");
			$("#feedback3").html("两次输入的密码不相同！");
		}  
		else
		{
			$("#feedback3").removeClass("red");  
			$("#feedback3").addClass("green");  
			$("#feedback3").html("输入正确");
		}
	});	
	$("#pwSubmit").click
	(function() 
	{	
		if($("#userPw").val() !== $("#oldPw").val() )
		{
			alert_modal("旧密码输入错误！");  
			return false; 
		}
		if(!passwordReg1.test($("#newPw").val() ) || passwordReg2.test($("#newPw").val() ) )
		{ 
			alert_modal("新密码格式不正确!");  
			return false; 
		}	
		if($("#newPw").val() !== $("#newPw2").val() )
		{ 
			alert_modal("两次输入的密码不相同!");  
			return false;  
		}      
		return true;  
	});
	//success modal
	if($("#inf").html() == "suc")
		{
			$("modal-text").html("操作成功！");
			$('#infModal').modal('show');
		}
	//avatar upload flash
	swfobject.addDomLoadEvent
	(function () 
	{
		var swf = new fullAvatarEditor("js/fullAvatarEditor.swf", "js/expressInstall.swf", "swfContainer", {
			    id : 'swf',
				//upload_url : 'avatarupload.jsp?userid='+ id +'&username=' + name,	//上传接口
			    upload_url : 'avatarupload.jsp?username=' + name,
				method : 'post',	//传递到上传接口中的查询参数的提交方式。更改该值时，请注意更改上传接口中的查询参数的接收方式
				src_upload : 0,		//是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
				avatar_box_border_width : 0,
				avatar_sizes : '125*125',
				avatar_sizes_desc : '125*125像素'
			}, function (msg) {
				switch(msg.code)
				{
					case 1 : 
						//alert("页面成功加载了组件！");
						break;
					case 2 : 
						//alert("已成功加载图片到编辑面板。");
						document.getElementById("upload").style.display = "inline";
						break;
					case 3 :
						if(msg.type == 0)
						{
							alert("摄像头已准备就绪且用户已允许使用。");
						}
						else if(msg.type == 1)
						{
							alert("摄像头已准备就绪但用户未允许使用！");
						}
						else
						{
							alert("摄像头被占用！");
						}
					break;
					case 5 : 
						if(msg.type == 0)
						{
							if(msg.content.sourceUrl)
							{
								//alert("原图已成功保存至服务器，url为：\n" + msg.content.sourceUrl+"\n\n" + "头像已成功保存至服务器，url为：\n" + msg.content.avatarUrls.join("\n\n")+"\n\n传递的userid="+msg.content.userid+"&username="+msg.content.username);
							}
							else
							{
								$("#avatar").attr("src", "." + msg.content.avatarUrls);
								//alert("头像已成功保存至服务器，url为：\n" + msg.content.avatarUrls.join("\n\n")+"\n\n传递的userid="+msg.content.userid+"&username="+msg.content.username);
							}
						}
					break;
				}
			}
		);
    });
});
