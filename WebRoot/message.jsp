<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="org.apache.struts2.util.Counter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>站内信</title>
<base href="<%=basePath%>">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/message_css.css" rel="stylesheet"/>
<link href="css/model_css.css" rel="stylesheet"/>
<link href="css/myPhotos_css.css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/message.js"></script>
</head>

<!-- user control panel page created by XuHailong 
	note: should consider wrong owner name
-->
<body style="OVERFLOW-X: hidden; ">
	<!--if owner is null, change it to session.logUser.account -->
	<s:set name="owner" value="#request.owner"></s:set>	
	<s:if test="owner==null">
		<s:set name="owner" value="#session.logUser.account" />
	</s:if>	
	<s:if test="owner==''">
		<s:set name="owner" value="#session.logUser.account" />
	</s:if>
	
	<!-- get owner's information -->
	<s:bean name="com.fudan2015.biz.impl.UserBizImpl" id="userBiz" />
	<s:if test="#userBiz.userFind(#owner).account==null">
		<s:set name="owner" value="#session.logUser.account" />
	</s:if>

	<!-- get page's information -->
	<s:set name="pagea" value="#parameters.pagea[0]"></s:set>
	<%-- <s:property value="#pagea" />  --%>
	<s:if test="#pagea < 1">
		<s:set name="pagea" value="1" />
	</s:if>	
	<s:set name="sta" value="( (#pagea)*10)-10" />
	<%-- <s:property value="#pagea" />
	<s:property value="#sta" /> --%>
	<s:set name="preva" value="#pagea - 1"/>
	<s:set name="nexta" value="#pagea - 0 + 1"/>
	<%-- <s:property value="#nexta" /> --%>
	<s:set name="sizea" value="(inmsgs.size - 1) / 10 + 1"/>	
	<%-- <s:property value="#sizea" />
	<s:property value="inmsgs.size" /> --%>
	<s:if test="inmsgs == null">
		<s:set name="sizea" value="1" />
	</s:if>	
	<%-- <s:property value="#sizea" /> --%>
	<s:if test="#preva < 1">
		<s:set name="preva" value="1" />
	</s:if>	
	<s:if test="#nexta > #sizea">
		<s:set name="nexta" value="#sizea" />
	</s:if>	
	
	<s:set name="pageb" value="#parameters.pageb[0]"></s:set>
	<%-- <s:property value="#pageb" /> --%>
	<s:if test="#pageb < 1">
		<s:set name="pageb" value="1" />
	</s:if>	
	<s:set name="stb" value="( (#pageb)*10)-10" />
	<%-- <s:property value="#pageb" />
	<s:property value="#stb" /> --%>
	<s:set name="prevb" value="#pageb - 1"/>
	<s:set name="nextb" value="#pageb - 0 + 1"/>
	<%-- <s:property value="#next" /> --%>
	<s:set name="sizeb" value="(outmsgs.size - 1) / 10 + 1"/>	
	<s:if test="outmsgs == null">
		<s:set name="sizeb" value="1" />
	</s:if>	
	<s:if test="#prevb < 1">
		<s:set name="prevb" value="1" />
	</s:if>	
	<s:if test="#nextb > #sizeb">
		<s:set name="nextb" value="#sizeb" />
	</s:if>	
	
	<s:set name="label" value="#parameters.label[0]" />
	<s:if test="#label < 1">
		<s:set name="label" value="1" />
	</s:if>	
	
	<!--transport owner and loggeduser to js -->	
	<div id="inf" style="display:none;"><s:property value="inf" default="" /></div>
	<div id="owner" style="display:none;"><s:property value="owner" default="" /></div>
	<div id="page" style="display:none;"><s:property value="#parameters.page" default="1" /></div>
	<div id="label" style="display:none;"><s:property value="#parameters.label" default="1" /></div>
	<div id="loggeduser" style="display:none;"><s:property value="#session.logUser.account" /></div>
	<div id="loggeduserid" style="display:none;"><s:property value="#session.logUser.id" /></div>
	<div id="currentpage1" style="display:none;"><s:property value="#pagea" /></div>
	<div id="currentpage2" style="display:none;"><s:property value="#pageb" /></div>
	<!-- send logged user's gender and description to js -->
	<input type="hidden" id="userGender" name="usergender" value="<s:property value="#userBiz.userFind(#session.logUser.account).gender" />" > 
	<input type="hidden" id="userDesc" name="userDesc" value="<s:property value="#userBiz.userFind(#session.logUser.account).description" />" >
	<input type="hidden" id="userAva" name="userAva" value="<s:property value="#userBiz.userFind(#session.logUser.account).image" />" >
	<!-- NOTICE: USER'S PASSWORD WILL BE SHOWED IN THE HTML DIRECTLY -->
	<input type="hidden" id="userPw" name="userPw" value="<s:property value="#userBiz.userFind(#session.logUser.account).password" />" > 
	
	<div class="wrapped"><div class="header">
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
		<div>
			  <!-- Nav tabs -->
			  <ul id="tabs" class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active" id="tab1"><a href="#inbox" aria-controls="inbox" role="tab" data-toggle="tab">收件箱</a></li>
			    <li role="presentation" id="tab2"><a href="#outbox" aria-controls="outbox" role="tab" data-toggle="tab">发件箱</a></li>
			    <li role="presentation" id="tab3"><a href="#writemsg" aria-controls="writemsg" role="tab" data-toggle="tab">写私信</a></li>
			  </ul>
			
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="inbox">
					<table class="table table-hover">
						  	<tr><th colspan="5"> 收件箱 </th></tr>
						  	<tr>
						  		<th> 发送者 </th>
						  		<th colspan="2"> 内容</th>
						  		<th> 发送时间</th>
						  		<th></th>
						  	</tr>
							  	<s:subset source="inmsgs" start="#sta" count="10">
							  		<s:iterator status="status">
						 				<tr>
						 					<th>
							  					<a href="ucp/ucp.action?owner=<s:property value="[0].sender" />"><s:property value="[0].sender" /> </a>
							  				</th>	
							  				<td colspan="2">
							  					<s:if test="[0].state==2">
							  						<form class="form-inline" method="post" action="msg/msg!addFriend.action">
							  							<input type="hidden" name="friend" value="<s:property value="[0].sender" />" > 
							  							请求添加您为好友&nbsp;&nbsp;&nbsp;
							  							<input type="hidden" name="delId" value="<s:property value="[0].id" />" > 
							  							<input type="submit" class="btn frdbtm" value="确定" />
							  						</form>
							  					</s:if>
							  					<s:else>
							  						<s:property value="[0].content" />
							  					</s:else>
							  				</td>
							  				<td>
							  					<s:property value="[0].date" />
							  				</td>
							  				<td>
							  					<form class="form-inline" method="post" action="msg/msg!delMsg.action">
							  						<button class="btn replybtn">回复</button>
							  						<input type="hidden" name="delId" value="<s:property value="[0].id" />" > 
							  						<input type="hidden" name="delState" value="3" >
							  						<input type="submit" class="btn delbtn" value="删除"/>
							  					</form>
							  				</td>
						  				</tr>
							  		</s:iterator>
							  	</s:subset>
						    <tr><td colspan="5">
						    	<div class="pagination" id="pagination1">
								   <ul>
								      <li><a href="msg/msg.action?label=1&pagea=<s:property value="#preva" />">Prev</a></li>
								      <s:bean name="org.apache.struts2.util.Counter"  id="counter" >       
										  <s:param name="first"  value="1" />
										  <s:param name="last"  value="#sizea" />
										  <s:iterator>
								     		 <li><a href="msg/msg.action?label=1&pagea=<s:property />"><s:property /></a></li>        
										  </s:iterator>
									  </s:bean>    
								      <li><a href="msg/msg.action?label=1&pagea=<s:property value="#nexta" />">Next</a></li>
								   </ul>
								</div>
							</td></tr>
					  	</table>
					  
					</div>
			    <div role="tabpanel" class="tab-pane" id="outbox">
			    	<table class="table">
						  	<tr><th colspan="5"> 发件箱 </th></tr>
						  	<tr>
						  		<th> 接收者 </th>
						  		<th colspan="2"> 内容</th>
						  		<th> 发送时间</th>
						  		<th></th>
						  	</tr>
							  	<s:subset source="outmsgs" start="#stb" count="10">
							  		<s:iterator status="status">
						 				<tr>
						 					<th>
							  					<a href="ucp/ucp.action?owner=<s:property value="[0].reciever" />"><s:property value="[0].reciever" /> </a>
							  				</th>	
							  				<td colspan="2">
							  					<s:if test="[0].state==2">
							  						好友请求
							  					</s:if>
							  					<s:else>
							  						<s:property value="[0].content" />
							  					</s:else>
							  				</td>
							  				<td>
							  					<s:property value="[0].date" />
							  				</td>
							  				<td>
							  					<form method="post" action="msg/msg!delMsg.action">
							  						<input type="hidden" name="delId" value="<s:property value="[0].id" />" >
							  						<input type="hidden" name="delState" value="4" >
							  						<input type="submit" class="btn delbtn" value="删除"/>
							  					</form>
							  				</td>
						  				</tr>
							  		</s:iterator>
							  	</s:subset>
						    <tr><td colspan="5">
						    	<div class="pagination" id="pagination2">
								   <ul>
								      <li><a href="msg/msg.action?label=2&pageb=<s:property value="#prevb" />">Prev</a></li>
								      <s:bean name="org.apache.struts2.util.Counter"  id="counter" >       
										  <s:param name="first"  value="1" />
										  <s:param name="last"  value="#sizeb" />
										  <s:iterator>
								     		 <li><a href="msg/msg.action?label=2&pageb=<s:property />"><s:property /></a></li>        
										  </s:iterator>       
									  </s:bean>    
								      <li><a href="msg/msg.action?label=2&pageb=<s:property value="#nextb" />">Next</a></li>
								   </ul>
								</div>
							</td></tr>
					  </table>
			    </div>
			  
			    <div role="tabpanel" class="tab-pane" id="writemsg">
			    	<h4>发送私信</h4>
					<form class="form-inline" method="post" action="msg/msg!sendMsg">
						<input id="input1" type="text" name="sendTo" placeholder="发送到">
						<input id="input2" class="input-xlarge" type="text" name="content" placeholder="内容">
						<button id="submit1" type="submit" class="btn">提交</button>
					</form>
					<h4>添加好友</h4>
					<form class="form-inline" method="post" action="msg/msg!sendMsg">
						<input id="input3" type="text" name="sendTo" placeholder="待添加好友名">
						<input id="input4" type="hidden" name="state" value="2">
						<button id="submit1" type="submit" class="btn">提交</button>
					</form>
			    </div>
			  
			  </div>
		</div> <div class="footer">
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
	</div>
	
	<div id="infModal" class="modal fade" role="dialog" aria-labelledby="modalLabel">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="modalLabel">信息</h4>
	      </div>
	      <div class="modal-body">
	        <p id="modal-text">操作成功！</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>
