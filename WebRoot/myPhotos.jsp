<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>我的照片</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
.container {
	width: 505px;
	margin: 0 auto;
}

.gallery {
	list-style-type: none;
	float: left;
	background: #ffffff;
	padding: 20px 20px 10px 20px;
	margin: 0;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
}

.gallery li {
	float: left;
	padding: 0 10px 10px 0;
}

.gallery li:nth-child  (6n ){
	padding-right: 0;
}

.gallery li a,.gallery li img {
	float: left;
}
</style>
		<!--引入开始-->
		<link rel="stylesheet" href="css/zoom.css" media="all" />
		<!--引入结束-->
		<style>
body {
	margin: 0;
	padding: 0;
}

ul#wimoban_nav {
	padding-left: 50px;
	margin-bottom: 10px;
	border-bottom: 2px solid #ccc;
	overflow: hidden;
	_zoom: 1;
}

ul#wimoban_nav li {
	float: left;
	display: inline;
	margin: 10px;
}

ul#wimoban_nav li a {
	display: block;
	font-size: 16px;
}

ul#wimoban_nav li a,#wimoban_p,#wimoban_p a {
	color: #000;
	font-family: "微软雅黑";
}

ul#wimoban_nav li a:hover,#wimoban_p a:hover {
	color: red;
}

#wimoban_p {
	text-align: center;
	font-size: 14px;
	clear: both;
}
</style>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/model_css.css" rel="stylesheet" />
		<link href="css/myPhotos_css.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/default_fitPic.css">
		<link href='css/style_fitPic.css' rel='stylesheet'>
		<link rel="stylesheet" type="text/css" href="css/showPics_css.css">
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-ui.min.js"></script>
		<script src="js/comment.js"></script>


	</head>

	<body oncontextmenu='return false' ondragstart='return false' style="OVERFLOW-X: hidden; ">
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
			<div class="main" style="padding-bottom:50px">
				<section class="mod-topbar-wrapper grid-responsive"
					id="xcTileTopbar">
				<div class="user-box clearfix">
					<div class="title-box clearfix">
						<div class="album-info">
							${sessionScope.photoInfo}
						</div>
					</div>
					${sessionScope.albumList}
				</div>
				<div class="info-wrapper clearfix">
					<div class="info-top-line">
					</div>
					<div class="detail-wrapper">
						<div class="control-box clearfix">
							<ul class="control-bar">
								<s:if test="#session.logUser.id==#session.showAccount.id">
									<li class="item muti-manage">
										<a class="bt-item yahei" href="management.jsp" title="批量管理"
											id="management">批量管理</a>
									</li>
								</s:if>
								<li class="item galleria" id="galleria"
									style="display: list-item;">
									<a class="bt-item yahei" href="supersized.jsp" title="播放幻灯片"
										id="supersized" target="_blank">播放幻灯片</a>
								</li>
								<s:if test="#session.logUser.id!=#session.showAccount.id">
									<li class="item">
										<a class="share" href="user/share!getFriendSharePic.action">添加分享</a>
									</li>
								</s:if>
							</ul>

						</div>
					</div>
				</div>
				</section>
				<div class="grid-wrapper">
					<input type="hidden" value=<s:property value="#session.photo" />
						id="photoArr" />
					<div class="container">
						<!-- 代码开始 -->
						<ul class="gallery">


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

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
		<script src='js/raf.js'></script>
		<script src='js/transit.js'></script>
		<script src='js/velocity.js'></script>
		<script src='js/imgload.adem.js'></script>
		<script src='js/stackgrid.adem.js'></script>
		<script src='js/website.js'></script>
		<script src='js/comment.js'></script>
		<script src='js/myPhoto.js'></script>

		<script type="text/javascript">
    	$('#nav').spasticNav();
		</script>

		<script type="text/javascript">
		$(".grid-item").mouseover(function() {
			$(this).css({"opacity":"0.8"});
			$(this).find('.namespace').css({"display":"block"});
			$(this).find('.namespace').animate({"height":"20px"},800);
			//this.
		});
		$(".grid-item").mouseout(function() {
			$(this).css({"opacity":"1"});
			$(this).find('.namespace').css({"display":"none"});
			$(this).find('.namespace').animate({"height":"0px"},800);
			//this.
		});
	</script>
		<script type="text/javascript">
		var choosePic;
		$(document).ready(function () {   
			
			$("#management").attr("href","photo/managePhoto!managePhoto.action?albumName="+$("#photoH1").text().trim()); 
			$("#supersized").attr("href","photo/supersized!supersized.action?albumName="+$("#photoH1").text().trim()); 
			event.stopPropagation();
			var $str=$("#photoArr").attr("value");
			//alert(${sessionScope.photo});
			var obj=eval(${sessionScope.photo});
			var first=0;
			var last=obj.length>10?10:obj.length;
			for(var i=0;i<obj.length;i++) {
				$(".gallery").append(obj[i]);
			}
			$(".grid-item a").click(function() {
				choosePic=$(this).attr("data-id");
				$.ajax({ 
					type: "POST", 
					url: "photo/photoView!zoomPic.action", 
					data: {photoId:choosePic}, 
					success: function(data){
						var splitstr= new Array();
						splitstr=data.split(",");
						$(".single-pic-modify-name").html(splitstr[0]);
						$(".source").html(splitstr[1]);
						$(".view .view-num").html("("+splitstr[2]+")");
						$(".comment-num").html("("+splitstr[3]+")");
					}
				});
				$.ajax({ 
					type: "POST", 
					url: "comment/commentLoad!showComment.action", 
					data: {photoId:choosePic}, 
					success: function(data){
						//alert(data);
						var comment=eval("["+data+"]");
						var li="";
						for(var i=0;i<comment.length;i++) {
							li+="<li class="+comment[i].id+"><div =\"comment-list-item "+"\" id=pic"+choosePic+"><a target=\"_blank\" " +
							"href=\"#\"> <img src=\""+comment[i].head+"\" width=\"30\" height=\"30\"> " +
							"</a><div><a target=\"_blank\" href=\"#\"><strong>"+comment[i].observer+"说：</strong>" +
							//get content and author
							"</a><blockquote>"+comment[i].comment+"</blockquote><cite> <span>"+comment[i].date+"</span>"+
							
							
							"<s:if test='#session.logUser.account==#session.showAccount.account'>"+
							"<a class" +
							//get date
							"=\"delete-comment\" href=\"\" onclick=\"deleteData("+comment[i].id+")\">删除</a>"
							"</s:if>"+
							
							+"</span> </cite></div></div></li>";
							//define code used for delete
						}
						$("#commentList").html(li);
					}
				});
				$(".submit").attr("data-id",choosePic);
			});
			
			
			/*$(window).scroll(function () {
				if(($(window).scrollTop() + $(window).height()) == $(document).height()&&($(".grid-item").length>0)) {
					alert("到底了");
					first=last;
					last=obj.length-first>10?(last+10):obj.length;
					for(var i=first;i<last;i++) {
						$(".grid-wrapper").append(obj[i]);
					}
				}
			});*/
			(function(e){
	function u(u){
		function c(){
			function h(e){
				e.show();
				n.removeClass("loading");
				
				//$(".content").css({"width":"1000px","height":"600px","margin-left":"auto","margin-right":"auto"});
				//$(".content").append("<div class=commentInfor>lll</div>");
			}
			var t=e(this),r=parseInt(n.css("borderLeftWidth")),i=s-r*2,u=o-r*2,a=t.width(),f=t.height();
			if(a==n.width()&&a<=i&&f==n.height()&&f<=u){h(t);
			return}if(a>i||f>u){var l=u<f?u:f,c=i<a?i:a;
			if(l/f<=c/a){t.width(a*l/f);t.height(l)}
			else{
				t.width(c);
				t.height(f*c/a)}}
			var widthImg=$("#zoom img").width();
			var heightImg=$("#zoom img").height();
			if(widthImg>1300||heightImg>640) {
				var index1=640*widthImg;
				var index2=1300*heightImg
				
				if(index1>index2) {
					var rate=heightImg/widthImg;
					widthImg=1280;
					heightImg=widthImg*rate;
				}
				else {
					var rate=widthImg/heightImg;
					heightImg=620;
					widthImg=heightImg*rate;
				}
				t.width(widthImg);
				t.height(heightImg);
				
			}
			
				n.animate({width:t.width(),height:t.height(),marginTop:-(t.height()/2)-r,marginLeft:-(t.width()/2)-r},200,function(){
				
				h(t)})
				
		}
		if(u)u.preventDefault();
		var a=e(this),f=a.attr("href");
		if(!f)return;
		var l=e(new Image).hide();
		e("#zoom .previous, #zoom .next").show();
		if(a.hasClass("zoom"))e("#zoom .previous, #zoom .next").hide();if(!r){r=true;t.show();
		e("body").addClass("zoomed")}n.html(l).delay(500).addClass("loading");
		l.load(c).attr("src",f);i=a}
		function a(){
			var t=i.parent("li").prev();
			if(t.length==0)t=e(".gallery li:last-child");
			t.find("a").trigger("click")
		}
		function f(){
			var t=i.parent("li").next();
			if(t.length==0)t=e(".gallery li:first-child");
			t.children("a").trigger("click")}
		function l(s){
			if(s)s.preventDefault();
			r=false;i=null;
			t.hide();
			e("body").removeClass("zoomed");
			n.empty()
		}
		function c(){
			/*原photos中comment实现的整体html：
			 * <div class="comment-container">
									<div class="single-comment-title">
										<span class="comment">评论 <span class="comment-num">(1)</span>
										</span>
										<span class="view">浏览 <span class="view-num">(7)</span>
										</span>
										<span class="arr"></span>
									</div>
									<div class="single-comment-more" style="display: none;">
										<a href="#" class="more"> <span class="arr"></span>点击查看较早评论
										</a>
									</div>
									<div class="single-comment-list" style="display: block">
										<ol id="commentList">
											${sessionScope.comments}
										</ol>
									</div>
									<div class="single-comment-submit">
										<div class="single-comment-submit-input">
											<textarea id="add-comment" rows="2" class=""
												placeholder="我也说一句"></textarea>
											<label for="add-comment" class="">
												<em>0</em>/150
											</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input  type="button" id="addComment" value="发表"/>"
										</div>
										<div class="single-comment-submit-vcode"
											style="display: none;">
										</div>
									</div>
								</div>
					temp为转移后的代码：		
					var temp="<div class=\"comment-container\"><div class=\"single-comment-title\"><span class=\"comment\">评论 <span class=\"comment-num\">" +
					"(1)</span></span><span class=\"view\">浏览 <span class=\"view-num\">(7)</span></span><span class=\"arr\"></span></div>" +
					"<div class=\"single-comment-more\" style=\"display: none;\"><a href=\"#\" class=\"more\"> <span class=\"arr\"></span>点击查看较早评论" +
					"</a></div><div class=\"single-comment-list\" style=\"display: block\"><ol id=\"commentList\">${sessionScope.comments}</ol>" +
					"</div><div class=\"single-comment-submit\"><div class=\"single-comment-submit-input\"><textarea id=\"add-comment\" rows=\"2\" class=\"\"" +
					" placeholder=\"我也说一句\"></textarea><label for=\"add-comment\" class=\"\"><em>0</em>/150</label><input  " +
					"type=\"button\" id=\"addComment\" value=\"发表\"/></div><div class=\"single-comment-submit-vcode\" style=\"display: none;\">" +
					"</div></div></div>";
					
					注意：comment.js上有几个方法绑定(包括增加评论 删除评论 显示大图【应该已经不需要了】）
					      css里面有部分修改（针对之前的页面）
					      里面的$如何取你跟我说过然而我没理解所以当初写在jsp里现在写在就js里你自己改改…………
			 */
			
			s=e(window).width();
			//里面commentContent我不知道能不能直接用+temp+不能的话直接粘上面temp里代码
			o=e(window).height()}
			var temp="<div class=picinfo>"+
					"<div class=user-info>"+
					"<a href=# target=_blank class=\"portrait xc-nsclick\">"+
					"<img class=img src=headPortrait/default.png></a>"+
					"<p class=name><a href=# target=_blank class=xc-nsclick></a></p>"+
					"<p class=source></p></div>"+
					"<p class=pic-name>"+
					"<a href=# class=single-pic-modify-name title=点击修改>相片名字</a>"+
					"</p></div>"+
			"<div class=comment-container><div class=single-comment-title><span class=comment>评论 <span class=comment-num>" +
			"</span></span><span class=view>浏览 <span class=view-num></span></span><span class=arr></span></div>" +
			"<div class=single-comment-more style=\"display:none;\"><a href=# class=more> <span class=arr></span>点击查看较早评论" +
			"</a></div><div class=single-comment-list style=\"display:block\"><ol id=commentList>" +
			//alert!!!!
			"</ol></div><div class=single-comment-submit><div class=single-comment-submit-input><textarea id=add-comment rows=2 class=textarea" +
			" placeholder=我也说一句></textarea></div><div class=single-comment-submit-op >" +
			"<a href=# data-action=addReply class=\"btn btn-primary submit\">评论</a>"+
			"</div></div></div>";
			e("body").append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class=showContent><div class="img"><div class="content loading"></div></div><div class=commentContent>'+temp+'</div></div></div>');
			//name
			var userName="${sessionScope.showAccount.account}";
			$(".name .xc-nsclick").html(userName);
			//portrait
			var headPortrait="${sessionScope.showAccount.image}";
			$(".portrait .img").attr("src",headPortrait);
			
			//pic information
			$(".submit").on("click",function() {
					choosePic=$(this).attr("data-id");
					var index=0;
					var value=$(".textarea").val();
					
					if(value==="") {
						alert("评论不可为空");
					}
					else {
						$.ajax({ 
						type: "POST", 
						url: "comment/newComment!newComment.action", 
						data: {comment:value,
								photoId:choosePic}, 
						success: function(data){
							var liObj=eval("["+data+"]");
							
							
							var addli="<li class="+liObj[0].id+"><div =\"comment-list-item "+"\" id=pic"+choosePic+"><a target=\"_blank\" " +
							"href=\"#\"> <img src=\""+liObj[0].head+"\" width=\"30\" height=\"30\"> " +
							"</a><div><a target=\"_blank\" href=\"#\"><strong>"+liObj[0].observer+"说：</strong>" +
							//get content and author
							
								
							
							"</a><blockquote>"+liObj[0].comment+"</blockquote><cite> <span>"+liObj[0].date+"</span>"+
							""+
							"</span> </cite></div></div></li>";
					
							
							
							$('#commentList').append(addli);
							index=liObj[0].commentNum;
							$(".comment-num").html("("+index+")");
						}
						});
					}
				});
			
			//date
			
			
			
			var t=e("#zoom").hide(),n=e("#zoom .content"),r=false,i=null,s=e(window).width(),o=e(window).height();
			(function(){t.on("click",function(t){t.preventDefault();if(e(t.target).attr("id")=="zoom")l()});
			e("#zoom .close").on("click",l);e("#zoom .previous").on("click",a);e("#zoom .next").on("click",f);
			e(document).keydown(function(e){if(!i)return;
			if(e.which==38||e.which==40)e.preventDefault();
			if(e.which==27)l();if(e.which==37&&!i.hasClass("zoom"))a();
			if(e.which==39&&!i.hasClass("zoom"))f()});
			if(e(".gallery li a").length==1)e(".gallery li a").addClass("zoom");
			e(".zoom, .gallery li a").on("click",u)})();
			(function(){e(window).on("resize",c)})();
			(function(){e(window).on("mousewheel DOMMouseScroll",function(e){if(!i)return;e.stopPropagation();
			e.preventDefault();
			e.cancelBubble=false})})()})(jQuery);
			
		});	
		
		
		
	</script>
	<script>
		$(".bt-upload-wrapper .bt-upload").attr("href","uploadView!albumView.action?albumId="+$("#photoH1").attr("data-id"));	
	</script>
	</body>
</html>
