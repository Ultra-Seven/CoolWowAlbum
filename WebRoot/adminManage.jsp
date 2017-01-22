<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Admin Manage</title>
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/admin.css" />
		<script src="js/modernizr.custom.js"></script>
	</head>
	<body>
		<div class="container">
			<ul id="gn-menu" class="gn-menu-main">
				<li><a href="user/logOff!logOff.action">logoff</a></li>
				<li><a class="codrops-icon codrops-icon-prev" href="/CoolWowAlbum/login.html"><span>Back to the loginPage</span></a></li>
			</ul>
			<header>
				<h1>Admin Manage</h1>	
			</header>
		</div><!-- /container -->
		<div id="main">
			<s:iterator var="User" value="#session.allUserzx" status="numbers">
			<s:if test="%{#User.identity!=1}">
				<ul class="userlist">
					<li id="userpic">
						<img src=<s:property value="#User.image"/> width="60px" height="60px">
					</li>
					<li class="username">
						<div id="userName"><s:property value="#User.account"/></div>
					</li>
					<s:if test="%{#User.state==0}">
						<li class="userstate">
					</s:if>
					<s:else>
						<li class="userstate" id="wrong">
					</s:else>
						<div id="userState" class=<s:property value="#User.account"/>>
							<s:if test="%{#User.state==0}">
								<a class="change" id="stateone">
								Normal
							</s:if>
							<s:if test="%{#User.state==1}">
       							<a class="change" id="statetwo">
								Ban
    						</s:if>
    						<s:if test="%{#User.state==2}">
       							<a class="change" id="statethree">
								Gag
    						</s:if>
    						<s:if test="%{#User.state==3}">
       							<a class="change" id="statefour">
								B-G
    						</s:if>
							
							</a>
						</div>
					</li>
					<li class="userfriend">
						<div id="userFriend"><a class="change" id="friends"><s:property value="#User.friendsNum"/> Friends</a></div>
					</li>
					<li class="usermanage">
						<div id="userManage"><a class="change" id="details">DETAILS</a></div>
					</li>		
				</ul>
				</s:if>
				<div class="userFriendlistTwo" id="undo">
					<s:iterator var="Friend" value="#User.friend" status="number">
						<ul class="userfriendlist">
							<li class="userfriendname">
								<div id="userfriendName"><s:property value="#Friend.name"/></div>
							</li>
							<li class="userfriendstate">
								<div id="userfriendState">
										<a><s:property value="#Friend.classification"/></a>
								</div>
							</li>	
							<li class="userfriendmanage">
								<div id="userfriendState"><a class="change" id="delete">Delete</a></div>
							</li>
							<input type="hidden" id=<s:property value="#User.id"/> value=<s:property value="#Friend.id"/>>
						</ul>
					</s:iterator>
				</div>
				<div class="userDetaillistTwo" id="undotwo">
					<ul class="userdetaillist">
					<li class="userpassword">
						<div id="userfriendName">UserPasswd</div>
					</li>
					<li class="userdetailpassword">
						<div id="userfriendState">
							<a class="change" id="passwdinput">HIDDEN</a>
							<input type="text" class="passwdinputtwo" id="passwdinputtwo" value=<s:property value="#User.password"/>>
							<input type="hidden" id=<s:property value="#User.account"/>>
						</div>
					</li>	
				</ul>
				<ul class="userdetaillist">
					<li class="useremail">
						<div id="userfriendName">UserEmail</div>
					</li>
					<li class="userdetailemail">
						<div id="userfriendState">
							<a class="change" id="emailinput"><s:property value="#User.email"/></a>
							<input type="text" class="emailinputtwo" id="emailinputtwo">
							<input type="hidden" id=<s:property value="#User.account"/>>
						</div>
					</li>	
				</ul>
				<ul class="userdetaillist">
					<li class="useralbum">
						<div id="userfriendName">UserAlbum</div>
					</li>
					<li class="userdetailalbum">
						<div id="userfriendState"><a><s:property value="#User.albumNum"/></a></div>
					</li>	
				</ul>
				<ul class="userdetaillist">
					<li class="userpic">
						<div id="userfriendName">UserPicture</div>
					</li>
					<li class="userdetailpic">
						<div id="userfriendState"><a><s:property value="#User.picturesNum"/></a></div>
					</li>	
				</ul>
				</div>
			</s:iterator>
		</div>
		
		<script src="js/jquery.min.js"></script>
		<script src="js/admin.js"></script>
		<script src="js/classie.js"></script>
	</body>
</html>