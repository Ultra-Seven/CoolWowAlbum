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
<title>用户中心</title>
<base href="<%=basePath%>">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/ucp_css.css" rel="stylesheet"/>
<link href="css/model_css.css" rel="stylesheet"/>
<link href="css/myPhotos_css.css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
<script type="text/javascript" src="js/ucp.js"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/fullAvatarEditor.js"></script>
</head>

<!-- user control panel page created by XuHailong 
	note: should consider wrong owner name
-->
<body style="OVERFLOW-X: hidden; ">
	<!--if owner is null, change it to session.logUser.account -->
	<s:set name="owner" value="#parameters.owner[0]"></s:set>
	<s:if test="owner==null">
		<s:set name="owner" value="#request.owner"></s:set>	
	</s:if>	
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
	<s:set name="pages" value="#parameters.page[0]"></s:set>
	<%-- <s:property value="#pages" /> --%>
	<s:if test="#pages < 1">
		<s:set name="pages" value="1" />
	</s:if>	
	<s:set name="st" value="( (#pages)*10)-10" />
	<%-- <s:property value="#pages" />
	<s:property value="#st" /> --%>
	<s:set name="prev" value="#pages - 1"/>
	<s:set name="next" value="#pages - 0 + 1"/>
	<%-- <s:property value="#next" /> --%>
	<s:set name="size" value="(cmts.size - 1) / 10 + 1"/>	
	<s:if test="cmts == null">
		<s:set name="size" value="1" />
	</s:if>	
	<s:if test="#prev < 1">
		<s:set name="prev" value="1" />
	</s:if>	
	<s:if test="#next > #size">
		<s:set name="next" value="#size" />
	</s:if>	
	<!--transport owner and loggeduser to js -->	
	<div id="inf" style="display:none;"><s:property value="inf" default="" /></div>
	<div id="owner" style="display:none;"><s:property value="owner" default="" /></div>
	<div id="page" style="display:none;"><s:property value="#parameters.page" default="1" /></div>
	<div id="loggeduser" style="display:none;"><s:property value="#session.logUser.account" /></div>
	<div id="loggeduserid" style="display:none;"><s:property value="#session.logUser.id" /></div>
	<div id="currentpage" style="display:none;"><s:property value="#pages" /></div>
	<!-- send logged user's gender and description to js -->
	<input type="hidden" id="userGender" name="usergender" value="<s:property value="#userBiz.userFind(#session.logUser.account).gender" />" > 
	<input type="hidden" id="userDesc" name="userDesc" value="<s:property value="#userBiz.userFind(#session.logUser.account).description" />" >
	<input type="hidden" id="userAva" name="userAva" value="<s:property value="#userBiz.userFind(#session.logUser.account).image" />" >
	<!-- NOTICE: USER'S PASSWORD WILL BE SHOWED IN THE HTML DIRECTLY -->
	<input type="hidden" id="userPw" name="userPw" value="<s:property value="#userBiz.userFind(#session.logUser.account).password" />" > 
	
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
		<div>
			  <!-- Nav tabs -->
			  <ul id="tabs" class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active" id="tab1"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">个人主页</a></li>
			    <li role="presentation" id="tab2"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">个人设置</a></li>
			    <li role="presentation" id="tab3"><a href="#updavatar" aria-controls="updavatar" role="tab" data-toggle="tab">头像上传</a></li>
			    <li role="presentation" id="tab4"><a href="#updatepw" aria-controls="updatepw" role="tab" data-toggle="tab">修改密码</a></li>
			  </ul>
			
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="home">
					<div class="row-fluid">
					  <div class="offset1 span3" id="profilebar">
					    <table class="table">
					  		<tr><th>个人资料 </th></tr>
					  		<!-- 头像未实现 -->
					  		<tr><td><img id="avatar" src="<s:property value="#userBiz.userFind(#owner).image" />" width="127" height="128" alt="avatar"/></td></tr>
					  		<tr><td><s:property value="#request.owner" /><span id="genderIcon"></span></td></tr>	
							<tr><td><div id="profiletext"><s:property value="#userBiz.userFind(#owner).description" /></div></td></tr>
					    </table>
					  </div>
					  <div class="offset1 span7" id="commentbar">
					  	<table class="table table-hover">
						  	<tr><th colspan="3"> 留言板 </th></tr>
						  	<tr>
						  		<th> 留言人 </th>
						  		<th> 留言 </th>
						  		<th> 留言时间 </th>
						  	</tr>
						 	<!--10comments per page  -->
							  	<s:subset source="cmts" start="#st" count="10">
							  		<s:iterator status="status">
						 				<tr>
						 					<th>
							  					<a href="ucp/ucp.action?owner=<s:property value="[0].observer" />"><s:property value="[0].observer" /> </a>
							  				</th>	
							  				<td>
							  					<s:property value="[0].comment" />
							  				</td>
							  				<td>
							  					<s:property value="[0].date" />
							  				</td>
						  				</tr>
							  		</s:iterator>
							  	</s:subset>
						    <tr><td colspan="3">
						    	<div class="pagination">
								   <ul>
								      <li><a href="ucp/ucp.action?page=<s:property value="#prev" />&owner=<s:property value="#request.owner" />">Prev</a></li>
								      <s:bean name="org.apache.struts2.util.Counter"  id="counter" >       
										  <s:param name="first"  value="1" />
										  <s:param name="last"  value="#size" />
										  <s:iterator>
								     		 <li><a href="ucp/ucp.action?page=<s:property />&owner=<s:property value="#request.owner" />"><s:property /></a></li>        
										  </s:iterator>       
									  </s:bean>    
								      <li><a href="ucp/ucp.action?page=<s:property value="#next" />&owner=<s:property value="#request.owner" />">Next</a></li>
								   </ul>
								</div>
							</td></tr>
						  	<tr><th colspan="3">
							  	<form class="form-inline" method="post" action="ucp/ucp!addComment">
							  		<input type="text" name="comment" placeholder="发表留言">
					  			    <button type="submit" class="btn">提交</button>
							  	</form>
							</th></tr>
					  	</table>
					  </div>
					</div>
				</div>
			    <div role="tabpanel" class="tab-pane" id="settings">
			    	<form class="form-horizontal" method="post" action="ucp/ucp!UpdateProfile.action">	
			    	  <div class="control-group">
					   	<label for="inputGender" class="control-label"><strong>性别</strong></label>
					    <div class="radio controls" id="inputGender">
					        <label class="checkbox inline">
							  <input type="radio" name="gender" value="0" checked="checked"> 男
						    </label>
						    <label class="checkbox inline">
							  <input type="radio" name="gender" value="1"> 女
						    </label>
					    </div>
					  </div>
					  <div class="control-group">
					   	<label for="inputDesc" class="control-label"><strong>签名</strong></label>
					    <div class="controls">
					    	<textarea name="description" rows="3" id="inputDesc"></textarea>
					    </div>
					  </div>
					  <div class="control-group">
					    <div class="controls">
					      <button type="submit" class="btn btn-default">修改</button>
					    </div>
					  </div>
					</form>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="updavatar">
			    	<div>
					<p id="swfContainer">
                  		  本组件需要安装Flash Player后才可使用，请从<a href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
					</p>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="updatepw">
			    	<form class="form-horizontal" method="post" action="ucp/ucp!UpdatePassword.action">	
					  <div class="control-group">
					   	<label for="oldPw" class="control-label"><strong>旧密码</strong></label>
					    <div class="controls">
					    	<input type="password" name="oldPassword" id="oldPw" />
					    	<span id="feedback1"> </span>
					    </div>
					  </div>				
					  <div class="control-group">
					   	<label for="newPw" class="control-label"><strong>新密码</strong></label>
					    <div class="controls">
					    	<input type="password"  name="NewPassword" id="newPw" />
					    	<span id="feedback2"> </span>
					    </div>
					  </div>				
					  <div class="control-group">
					   	<label for="newPw2" class="control-label"><strong>重复新密码</strong></label>
					    <div class="controls">
					    	<input type="password" name="newPw2" id="newPw2" />
					    	<span id="feedback3"> </span>
					    </div>
					  </div>
					  <div class="control-group">
					    <div class="controls">
					      <button type="submit" class="btn btn-default" id="pwSubmit">修改</button>
					    </div>
					  </div>
					</form>
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
