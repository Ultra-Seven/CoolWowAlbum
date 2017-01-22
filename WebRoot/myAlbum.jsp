<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
<style>.album{width:225px !important;height:225px !important;}
</style>
		<title>我的相册</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/myAlbum_css.css" rel="stylesheet" />
		<link href="css/model_css.css" rel="stylesheet" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body style="OVERFLOW-X: hidden; ">
		<div class="wrapped">
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
									<a href="other/return!returnMyAccount.action" role="menuitem" tabindex="-1">我的相册</a>
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
					<s:if test="#session.logUser.account==#session.showAccount.account">
					<div class="bt-upload-wrapper">
						<a class="bt-upload yahei" href="uploadView!albumView.action"> <span
							class="icon icon-pic-upload"></span>上传照片 </a>
					</div>
					</s:if>
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
				<header class="codrops-header">
				<h1>
					My Album
				</h1>
				</header>
				<script type="text/javascript" src="js/jquery.js"></script>
				<script type="text/javascript" src="js/jquery.min.js"></script>
				<script type="text/javascript" src="js/three.min.js"></script>
				<script type="text/javascript" src="js/assets.js"></script>
				<script type="text/javascript" src="js/preloadjs.min.js"></script>
				<script type="text/javascript" src="js/TweenMax.min.js"></script>
				<script type="text/javascript" src="js/Stats.js"></script>
				<script type='text/javascript'>
			$(function(){
			    var box = $(".box").ig3js({
			        manifest: [${sessionScope.pre}],
			        imagePath: 'uploadFiles/',
			        alphaBackground: true,
			        onNavigateComplete: function(obj){
			        }
			    });
			   
			});
			</script>
				<div class="normalDisplay">
					<div class="album-create">
						<s:if test="#session.logUser.account==#session.showAccount.account">
						<a class="createAlbum" href="addAlbum.jsp"></a>
						</s:if>
					</div>
					<div class="disList" style="min-height: 312px;">
						<ul class="album-list clearfix">
							${sessionScope.album}
						</ul>
					</div>
				</div>
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
		<!--Modal-->
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="js/jquery.spasticNav.js"></script>	
				<script type="text/javascript" src="js/image-gallery-three.js"></script>
		<script type="text/javascript">
    	$('#nav').spasticNav();
	</script>
		<script type="text/javascript">
		$(".content-album").mouseover(function() {
			$("#albumHover").css({"opacity":"0.8"});
			//this.
		});
		$(".content-album").mouseout(function() {
			$("#albumHover").css({"opacity":"1"});
			//this.
		});
	</script>

	</body>
</html>