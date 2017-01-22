<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>批量管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/model_css.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<link href="css/management_css.css" rel="stylesheet" />
	</head>

	<body style="OVERFLOW-X: hidden; ">
		<div id="outernotice">
			<div id="innernotice">操作成功</div>
		</div>
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
				<section class="global-xc-body">
				<div class="global-main-wrapper wordwrap clearfix">
					<div class="container clearfix">
						<div class="grid grid-20">
							<div id="manage-title">
								<div class="manage-title-body yahei">
									<a class="yahei" href="photo/photoLoad!photoLoad.action">我的相册</a>&nbsp;&gt;&nbsp;
									<!-- 动态显示 -->
									${sessionScope.albumContent}
								</div>
								<a class="goback" href="photo/photoLoad!photoLoad.action">返回上页
									&gt;&gt;</a>
							</div>
							<div id="manage-bar">
								<ul class="manage-bar">
									<li>
										<div class="mybtn desc-button desc-button-disabledState" id="check"></div>
									</li>
									<li>
										<div class="mybtn delete-button delete-button-disabledState" id="trash"></div>
									</li>

								</ul>
								<div class="select-tips" style="display: block;">
									点击下方照片进行多选！
								</div>
							</div>
							<div id="pic-list">
								${sessionScope.managePhoto}
							</div>

							<div id="pager-box" class="pager" style="display: none;">
								<a class="pager_first" href="javascript:void(0);" hidefocus="">首页</a><a
									class="pager_prev" href="javascript:void(0);" hidefocus="">上一页</a><a
									class="pager_button" href="javascript:void(0);" hidefocus="">0</a><a
									class="pager_button" href="javascript:void(0);" hidefocus="">1</a><a
									class="pager_button" href="javascript:void(0);" hidefocus="">2</a><a
									class="pager_button" href="javascript:void(0);" hidefocus="">3</a><a
									class="pager_button" href="javascript:void(0);" hidefocus="">4</a><a
									class="pager_next" href="javascript:void(0);" hidefocus="">下一页</a><a
									class="pager_last" href="javascript:void(0);" hidefocus="">尾页</a>
							</div>
						</div>
					</div>
				</div>
				</section>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<!-- 修改名称模态 -->
				
			
			
			
			
			
			
			
			<div id="changeDes" class="modal hide fade mod-editmutipicname-modal-wrapper modal-400" style="margin-left: -365px; margin-top: 5%; top: 0px; left: 50%;">      
		<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
            <h5 id="zxTitle">修改描述</h5>
        </div><!--Modal header-->
            <div class="modal-body clearfix">
              			<div class="form-validator clearfix">
							<ul class="pic-container clearfix xc-scroll-box" id="modelDesc">
							</ul>
						</div>
            </div><!--Modal body-->
           	<div class="modal-footer">
           		<a class="button btn btn-primary animate"
							data-validate="submit" data-dismiss="modal" id="submit1">确认</a>
						<a class="button btn btn-default" href="#"
							data-dismiss="modal" id="cancelDesc">取消</a>
            </div><!--Modal footer-->
	</div> <!--Modal-->
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			</div>
			<div class="footer">
				<div id="footer-background">
					<nav class="special" id="other">
					<span id="footbar1"> <a href="#ratyService"
						data-toggle="modal" data-target="#ratyService"
						data-hover="SUGGESTION">SUGGESTION</a> </span>
					<span id="footbar2"> <a href="#ratyService"
						data-toggle="modal" data-target="#ratyService"
						data-hover="REMARKS">REMARKS</a> </span>
					<span id="footbar3"> <a href="#ratyService"
						data-toggle="modal" data-target="#ratyService"
						data-hover="BUGREPORT">BUGREPORT</a> </span>
					<span id="footbar4"> <a href="#ratyService2"
						data-toggle="modal" data-target="#ratyService2" data-hover="STAFF">STAFF</a>
					</span>
					<span id="footbar5"> <a href="#ratyService3"
						data-toggle="modal" data-target="#ratyService3"
						data-hover="THANKS">THANKS</a> </span>
					</nav>
				</div>
				<div class="rights">
					<p class="copyrightlinka">
						Copyright © 1996 - 2015 Ultra-Seven Corporation, All Rights
						Reserved
					</p>
					<p class="linka">
						赛文公司 版权所有
					</p>
				</div>
			</div>
		</div>
		<div id="ratyService" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					&times;
				</button>
				<h5>
					E-MAIL US
				</h5>
			</div>
			<!--Modal header-->
			<div class="modal-body">
				<div class="row">
					<div class="span1"></div>
					<div class="span4 ">
						<h5>
							电子邮件：14302010039@fudan.edu.cn
						</h5>
						<h5>
							欢迎来信!
						</h5>
					</div>
					<div class="span4" id="ratingstar"></div>
					<div class="span1" id="target"></div>
				</div>
			</div>
			<!--Modal body-->
			<div class="modal-footer">
				<a href="login#other" class="btn" data-dismiss="modal">Close</a>
			</div>
			<!--Modal footer-->
		</div>
		<!--Modal-->
		<div id="ratyService2" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					&times;
				</button>
				<h5>
					STAFF
				</h5>
			</div>
			<!--Modal header-->
			<div class="modal-body">
				<div class="row">
					<div class="span1"></div>
					<div class="span4 ">
						<h5>
							魏子耘，梁旭，许海龙，朱潇
						</h5>
					</div>
					<div class="span4" id="ratingstar"></div>
					<div class="span1" id="target"></div>
				</div>
			</div>
			<!--Modal body-->
			<div class="modal-footer">
				<a href="login#other" class="btn" data-dismiss="modal">Close</a>
			</div>
			<!--Modal footer-->
		</div>
		<!--Modal-->
		<div id="ratyService3" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					&times;
				</button>
				<h5>
					THANKS
				</h5>
			</div>
			<!--Modal header-->
			<div class="modal-body">
				<div class="row">
					<div class="span1"></div>
					<div class="span4 ">
						<h5>
							THANK YOU FOR SUPPORTING US
						</h5>
					</div>
					<div class="span4" id="ratingstar"></div>
					<div class="span1" id="target"></div>
				</div>
			</div>
			<!--Modal body-->
			<div class="modal-footer">
				<a href="login#other" class="btn" data-dismiss="modal">Close</a>
			</div>
			<!--Modal footer-->
		</div>
		<div id="deleteEnsure" class="modal hide fade">
			<!--Modal header-->
			<div class="modal-body">
				<div class="title1">
					<div class="title2">
						<h5>
							确定要删除吗
						</h5>
					</div>
					<div id="button">
						<a class="button btn btn-primary animate" data-validate="submit" data-dismiss="modal" id="submit2">确认</a>
						<a class="btn" data-dismiss="modal">关闭</a>
					</div>
				</div>
			</div>
			<!--Modal body-->
			<!--Modal footer-->
		</div>
		<!--Modal-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/management.js"></script>
		<script type="text/javascript">
	$('#nav').spasticNav();
</script>
		<script type="text/javascript">
	function showBar() {
		if (select > 0) {
			if ($(".delete-button").hasClass("delete-button-disabledState")) {
				$(".delete-button").removeClass("delete-button-disabledState");
				$(".delete-button").addClass("delete-button-upState");
			}
			if ($(".desc-button").hasClass("desc-button-disabledState")) {
				$(".desc-button").removeClass("desc-button-disabledState");
				$(".desc-button").addClass("desc-button-upState");
			}
		} else {
			if ($(".delete-button").hasClass("delete-button-upState")) {
				$(".delete-button").removeClass("delete-button-upState");
				$(".delete-button").addClass("delete-button-disabledState");
			}
			if ($(".desc-button").hasClass("desc-button-upState")) {
				$(".desc-button").removeClass("desc-button-upState");
				$(".desc-button").addClass("desc-button-disabledState");
			}
		}
	}
	var select = 0;
	$(".pic").toggle(function() {
		$(this).addClass("selected");
		select++;
		showBar();
	}, function() {
		$(this).removeClass("selected");
		select--;
		showBar();
	});
	$(".delete-button").mouseover(function() {
		if ($(".delete-button").hasClass("delete-button-upState")) {
			$(this).addClass("delete-button-overState");
		}
	});
	$(".delete-button").mouseout(function() {
		if ($(".delete-button").hasClass("delete-button-upState")) {
			$(this).removeClass("delete-button-overState");
		}
	});
	$(".desc-button").mouseover(function() {
		if ($(".desc-button").hasClass("desc-button-upState")) {
			$(this).addClass("desc-button-overState");
		}
	});
	$(".desc-button").mouseout(function() {
		if ($(".desc-button").hasClass("desc-button-upState")) {
			$(this).removeClass("desc-button-overState");
		}

	});
</script>
<!-- 用于向后台传递json -->
<script type="text/javascript">
	$("#submit1").click(function() {
		var id_array=new Array();
		var newName_array=new Array();
		$(".img-item-wrapper").each(function() {
			id_array.push($(this).attr("data-id"));
			newName_array.push($(this).children(":nth-child(2)").children(":nth-child(1)").text().trim());
		});
		var photoId="";
		var photoName="";
		for(var i=0;i<id_array.length;i++) {
			photoId=photoId+id_array[i];
			photoName=photoName+newName_array[i];
			if(i!=id_array.length-1) {
				photoId=photoId+",";
				photoName=photoName+",";
			}
		}
		
		$.ajax({
        	type:"post",
            async:false,
            url :"update.action",        
            data : {photoId:photoId,photoName:photoName},
            dataType : "json",
            success : function(data) {
            	
            }

        });
	});
	$("#submit2").click(function() {
		var id_array=new Array();
		var photoId="";
		var albumName=$(".myAlbumName").attr("title");
		var albumId=$(".myAlbumName").attr("data-id");
		$(".pic").each(function() {
			if($(this).hasClass("selected")) {
				id_array.push($(this).attr("data-id"));
			}
		});
		for(var i=0;i<id_array.length;i++) {
			photoId=photoId+id_array[i];
			if(i!=id_array.length-1) {
				photoId=photoId+",";
			}
		}
		$.ajax({
        	type:"post",
            async:false,
            url :"delete.action",        
            data : {deleteId:photoId,albumId:albumId,albumName:albumName},
            dataType : "json",
            success : function(data) {
            	//重载当前页面
            	//alert("1");
            	window.location ="photo/photoView!photoView.action?albumName="+$(".myAlbumName").text().trim()+"&albumId="+albumId;
            }
		});
	});
</script>
<script type="text/javascript">

$(".desc-button-upState").live("click",function() {
	var id=new Array();
	var url=new Array();
	var name=new Array();
	$(".pic").each(function() {
		if($(this).hasClass("selected")) {
			id.push($(this).attr("data-id"));
			url.push($(this).children(":nth-child(1)").attr("src"));
			name.push($(this).attr("data-name"))
		}
		
	});
	var showContent="";
	for(var i=0;i<id.length;i++) {
		var $content=$("<li class=img-item-wrapper data-id="+id[i]+"><div class=img-container><img src="+url[i]+"></div><div class=img-desc-container>"
			+"<div class=img-desc title="+name[i]+">"+name[i]+"</div><input type=text maxlength=10 class=nameInput id=nameInput style=width:50%;></div></li>");
		$("#modelDesc").append($content);
	}
	
});
$(".desc-button").click(function() {
	$("#modelDesc").empty();
});
</script>
	</body>
</html>
