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
    
    <title>我的好友</title>
    
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
	<link href="css/myPhotos_css.css" rel="stylesheet"/>
	<link href="css/friendList.css" rel="stylesheet"/>
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
		<div id="zxMain">
			<div id="oprate">
				<div id="addlist">
					<div class="icon-plus icon-white" ></div>
					<span>创建列表</span>
				</div>
			</div>
			<div id="blank"></div>
				<s:iterator var="Friends" value="#session.friends" status="numbers">
				<div id="friendtypelist">
					<ul class="friendtype">
						<li class="ensuretwo" id=<s:property value="#Friends.classId"/>>
							<div class="icon-ok ok" id="ok"></div>
							<div class="icon-remove remove" id="remove"></div>
						</li>
						<li class="friendTypeName">
							<input type="text" id="changeName" class="changeName" value=<s:property value="#Friends.classification"/>>
							<s:if test="%{#Friends.classification!=\"未分类\"}">
								<div class="positonBigtwo">
							</s:if>
							<s:else>
       							<div class="positonBigtwo" id="weifenlei">
    						</s:else>
								<s:property value="#Friends.classification"/>
							</div>
						</li>
						<li class="friendTypeNumber"><div class="positonBigtwo">(<s:property value="#Friends.num"/>)</div></li>
						<li class="unsee">
							<div class="icon-pencil pencil" id="pencil"></div>
							<div class="icon-trash trash" id="trash"></div>
						</li>
					</ul>
					</div>
					<div id="done">
					<s:iterator var="Friend" value="#Friends.friend" status="number">		
						<ul class="friend" id=<s:property value="#Friend.friendId"/>>
				   			<li class="friendPic"><img src=<s:property value="#Friend.image"/> weight="60px" height="60px"></li>
							<li class="friendName"><div class="positionSmall">
								<a class="tiaozhuanyemian">
									<s:property value="#Friend.name"/>
								</a>
							</div></li>
							<li class="friendIntro"><div class="positionSmall"><s:property value="#Friend.introduction"/></div></li>
							<li style="display:none;"><s:property value="#Friends.classId"/></li>
							<li style="display:none;"><s:property value="#Friend.id"/></li>
							<li class="friendManage">
								<div class="positionSmall">
									<a class="href">Delete</a>
									<a class="ensure yes">YES</a>
									<a class="ensure no">NO</a>
								</div>
							</li>
						</ul>
					</s:iterator>
					</div>
				</s:iterator>
			
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
<div id="ratyService4" class="modal hide fade">      
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>创建分类</h5>
    </div><!--Modal header-->
    <div class="modal-body">
        <div class="row">
           	<div  class="span1"></div>
            <div class="span4 ">
              	<input type="text" placeholder="创建分类，字数不超过6个字" id="creat">
            </div>
            <div  class="span4" id="ratingstar"  ></div>
            <div  class="span1" id="target"></div>
        </div>
    </div><!--Modal body-->
    <div class="modal-footer">
    	<a id="submit" class="btn btn-primary" onClick="test()" data-dismiss="modal">submit</a>
      	<a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->
<div id="ratyService5" class="modal hide fade">      
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>输入错误</h5>
    </div><!--Modal header-->
    <div class="modal-body">
        <div class="row">
           	<div  class="span1"></div>
            <div class="span4 ">
              	<h5>输入有误！</h5>
            </div>
            <div  class="span4" id="ratingstar"  ></div>
            <div  class="span1" id="target"></div>
        </div>
    </div><!--Modal body-->
    <div class="modal-footer">
      	<a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->
<div id="ratyService6" class="modal hide fade">      
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5>输入错误</h5>
    </div><!--Modal header-->
    <div class="modal-body">
        <div class="row">
           	<div  class="span1"></div>
            <div class="span4 ">
              	<h5>输入名称重复！</h5>
            </div>
            <div  class="span4" id="ratingstar"  ></div>
            <div  class="span1" id="target"></div>
        </div>
    </div><!--Modal body-->
    <div class="modal-footer">
      	<a href="login#other" class="btn" data-dismiss="modal" >Close</a>
    </div><!--Modal footer-->
</div> <!--Modal-->

  	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>	
  	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
	<script type="text/javascript" src="js/friendList.js"></script>
	<script type="text/javascript">
    	$('#nav').spasticNav();
	</script>
  </body>
</html>
