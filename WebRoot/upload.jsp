<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>相片上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="css/model_css.css" rel="stylesheet"/>
	<link href="css/upload_css.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.uploadify.min.js"></script>
	
  </head>
  
  <body style="OVERFLOW-X: hidden; ">
  <div class="wrapper">
    <div class="header">
				<!-- 导航栏 -->
				<div class="nav" id="container">
					<ul id="nav">
						<li id="selected">
							<s:if test="#session.logUser.account==#session.showAccount.account">
								<a href="photo/photoLoad!photoLoad.action">我的相册</a>
							</s:if>
							<s:else>
								<a href="photo/photoLoad!photoLoad.action">别人的相册</a>
							</s:else>
						</li>
						<li>
							<a href="user/index.action">图片广场</a>
						</li>
						<s:if test="#session.logUser.account==#session.showAccount.account">
        					<li><a href="doodle/doodleView!showWall.action?userId=1">涂鸦墙</a></li>
        				</s:if>
        				<s:else >
        					<li><a href="doodleView!showWall.action?userId=2">涂鸦墙</a></li>
        				</s:else>
						<li>
							<a href="search.jsp">搜索</a>
						</li>
					</ul>
					<div class="leftNav" id="leftNav">
						<span class="welcome">欢迎你，<s:property
								value="#session.logUser.account" />
						</span>
						<span class="icon-user"></span>
						<div class="dropdown">
							<button class="btn dropdown-toggle " type="button"
								id="dropdownMenu1" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenu1">
								<li role="presentation">
									<a href="ucp/ucp.action?owner=<s:property value="#session.logUser.account" />" role="menuitem" tabindex="-1">个人中心</a>
								</li>
								<li role="presentation">
									<a href="return!returnMyAccount.action" role="menuitem" tabindex="-1">我的相册</a>
								</li>
								<li role="presentation">
									<a href="msg/msg.action" role="menuitem" tabindex="-1">我的私信</a>
								</li>
								<li role="presentation">
									<a href="ucp/ucp.action#settings" role="menuitem" tabindex="-1">个人设置</a>
								</li>
								<li role="presentation">
									<a href="user/logOff!logOff.action" role="menuitem" tabindex="-1">退出</a>
								</li>
							</ul>
						</div>
						
					</div>
				</div>
				<div class="headerContent">
					<div class="user-info-wrapper">
						<div class="user-name-wrapper clearfix">
							<h1 class="user-name yahei">
								<s:property value="#session.showAccount.account" />
							</h1>
						</div>
					</div>
					<div class="user-num-wrapper">
						<ul class="num-list clearfix">
							<li class="num-item">
							<s:if test="#session.logUser.account==#session.showAccount.account">
								<a href="user/share!getSharePic.action" class="num-link" target="_blank"> <em
									class="num"><s:property value="#session.showAccount.shareNum" /></em> <span class="num-info yahei">分享</span> </a>
								</s:if>
								<s:else>
								<a class="num-link" target="_blank"> <em
									class="num"><s:property value="#session.showAccount.shareNum" /></em> <span class="num-info yahei">分享</span> </a>
								</s:else>
								</li>
								<li class="num-item">
								<s:if test="#session.logUser.account==#session.showAccount.account">
								<a href="user/friends!getFriendList.action" class="num-link" target="_blank"> <em
									class="num"><s:property value="#session.showAccount.friendsNum" /></em> <span class="num-info yahei">好友</span> </a>
								</s:if>
								<s:else>
								<a class="num-link" target="_blank"> <em
									class="num"><s:property value="#session.showAccount.friendsNum" /></em> <span class="num-info yahei">好友</span> </a>
								</s:else>
							</li>
							
							<li class="num-item last-item">
								<a href="photo/photoLoad!photoLoad.action" class="num-link"> <em class="num"><s:property value="#session.showAccount.picturesNum" /></em> 
								<span class="num-info yahei">照片</span> </a>
							</li>
						</ul>
					</div>
				</div>
				<div class="user-nav-bar">
					<div class="bg-nav"></div>
					<div class="nav-wrapper grid-responsive">
						<div class="img-wrapper">
							<img src="<s:property value="#session.showAccount.image" />" alt="头像">
						
						</div>
						
						
						<ul class="nav-list">
							<li class="nav-item first-item current">
								<a href="photo/photoLoad!photoLoad.action" class="nav-link yahei">相册</a>
							</li>
							<s:if test="#session.logUser.account==#session.showAccount.account">
								<li class="nav-item ">
									<a href="user/share!getSharePic.action" class="nav-link yahei">我的分享</a>
								</li>
								<li class="nav-item ">
									<a href="doodle/doodleView!showWall.action?userId=1" class="nav-link yahei">我的涂鸦墙</a>
								</li>
							</s:if>
						</ul>
					</div>
				</div>
			</div>
    <div class="main">
	    <div class="mainContent">
	    	<div class="setting">
	    		<div class="setting-album">
	    			<span class="albumclass">选择相册：</span>
	    			<div class="dropdown" id="album-list">  
						${sessionScope.uploadList}
					</div>  
	    		</div>
	    	</div>
	    	<div class="uploading">
		    	<div class="div_row1">
		    		<div class="picBack"></div>
				 	<input type="file" name="uploadify" id="uploadify" /> 
				 	
				 	<div id="fileQueue"></div>
				 	<div id="result"></div>
					<div id="progress"></div> 
				</div>
				<a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;     
		        <a href="javascript:$('#uploadify').uploadify('cancel', '*')">取消上传队列</a> 
			</div> 
		</div> 
    </div>
    <div class="footer">
    	<div id="footer-background">
			<nav class="special" id="other">
				<span id="footbar1">
					<a href="#ratyService" data-toggle="modal" data-target="#ratyService" data-hover="SUGGESTION">SUGGESTION</a>				</span>
				<span id="footbar2">
					<a href="#ratyService" data-toggle="modal" data-target="#ratyService" data-hover="REMARKS">REMARKS</a>				</span>
				<span id="footbar3">
					<a href="#ratyService" data-toggle="modal" data-target="#ratyService" data-hover="BUGREPORT">BUGREPORT</a>				</span>
				<span id="footbar4">
					<a href="#ratyService2" data-toggle="modal" data-target="#ratyService2" data-hover="STAFF">STAFF</a>				</span>
				<span id="footbar5">
					<a href="#ratyService3" data-toggle="modal" data-target="#ratyService3" data-hover="THANKS">THANKS</a>				</span>			
			</nav>
		</div>
		<div class="rights">
			<p class="copyrightlinka"> Copyright © 1996 - 2015 Ultra-Seven Corporation,  All Rights Reserved</p>
			<p class="linka"> 赛文公司  版权所有</p>
		</div>
	</div>
</div>
<div id="ratyService" class="modal hide fade">      
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>E-MAIL US</h5>
    </div><!--Modal header-->
    <div class="modal-body">
        <div class="row">
          	<div  class="span1"></div>
            <div class="span4 ">
              	<h5>电子邮件：14302010039@fudan.edu.cn</h5>
				<h5>欢迎来信!</h5>
            </div>
            <div  class="span4" id="ratingstar"  ></div>
           	<div  class="span1" id="target"></div>
       	</div>
	</div><!--Modal body-->
    <div class="modal-footer">
    	<a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->
<div id="ratyService2" class="modal hide fade">      
  	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>STAFF</h5>
  	</div><!--Modal header-->
   	<div class="modal-body">
   		<div class="row">
			<div  class="span1"></div>
           	<div class="span4 ">
          		<h5>魏子耘，梁旭，许海龙，朱潇</h5>
           	</div>
          	<div  class="span4" id="ratingstar"  ></div>
          	<div  class="span1" id="target"></div>
      	</div>
	</div><!--Modal body-->
    <div class="modal-footer">
        <a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->
<div id="ratyService3" class="modal hide fade">      
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>THANKS</h5>
    </div><!--Modal header-->
    <div class="modal-body">
        <div class="row">
           	<div  class="span1"></div>
            <div class="span4 ">
              	<h5>THANK YOU FOR SUPPORTING US</h5>
            </div>
            <div  class="span4" id="ratingstar"  ></div>
            <div  class="span1" id="target"></div>
        </div>
    </div><!--Modal body-->
    <div class="modal-footer">
      	<a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->
   <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
	<script type="text/javascript">
    	$('#nav').spasticNav();
	</script>
	<script type="text/javascript">
		 $(function() {
            $("#uploadify").uploadify({
            	'buttonText':'选择上传数据',//上传按钮的文字
            	'width'     : 200,
                'swf'       : 'uploadify/uploadify.swf', //是组件自带的flash，用于打开选取本地文件的按钮
                'uploader'      : 'upload',//处理上传的路径
                'cancelImage'     : 'uploadify/uploadify-cancel.png',//取消上传文件的按钮图片
   				'method'        : 'get',   				
   				'formData'		: {'albumId':$("#accountmenu").attr("data-id")},
   				'button_image_url':'<%=basePath%>',
   				'fileObjName'   : 'uploadify',//和input的name属性值保持一致就好，Struts2就能处理了   
   				'auto'           : false, //选定文件后是否自动上传，默认false
   				'multi'          : true, //是否允许同时上传多文件，默认false
   				'queueID'		: 'fileQueue',
   				'debug'			: false,
   				'removeCompleted': true,
   				'removeTimeout' : 0.5,
   				'requeueErrors'	: true,
   				'progressData'	: "all",// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
   				'queueSizeLimit' : 10, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
   				'fileSizeLimit'	: 50*1024*1024, //设置单个文件大小限制，单位为byte ,50M
   				//如果配置了以下的'fileExt'属性，那么这个属性是必须的 
     			'fileTypeDesc': '支持格式:jpg/gif/jpeg/png/bmp.', 
    			//允许的格式
          		'fileTypeExts': '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
   			  	 //动态传参数
    			'onUploadStart' : function(file) {
           			$("#uploadify").uploadify("settings","formData",{"albumId":$("#accountmenu").attr("data-id")});
    			},
   			  	'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal,queueBytesLoaded) {
					$("#result").append("<div>文件\""+file.name+"\"共"+totalBytesTotal+"字节，已上传"+totalBytesUploaded + "字节!</div><br/>");
   			 		if(totalBytesUploaded==totalBytesTotal){
   			 			$("#result").append("<div>文件\""+file.name+"\"上传成功！</div><br/>");
   			 		}
   	        	},
   				'onUploadComplete' : function(file) {
   				 	$("#result").empty();
   					$("#result").append("<div>文件"+file.name+"上传成功！</div><br/>");
   					var str=<s:property value="#session.logUser.picturesNum" />;
   					
   		        },
   				'onUploadSuccess' : function(file,data,response) {	
	   				//	alert(data);
	   						var retdata=eval("(" + data + ")");
	   						alert(retdata.msg);
	   					//提示消息
	   					$("#result").append("<div>"+retdata.msg+"</div><br/>");
   				},
   				'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {
 					    $("#result").html(errorString);
   				},
       });
    });
	</script>
  </body>
</html>
