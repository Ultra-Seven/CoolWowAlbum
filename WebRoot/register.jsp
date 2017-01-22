<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>注册</title>
		<link href="css/register.css" rel="stylesheet" type="text/css" />
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
	</head>

	<body>

		<img alt="logo" src="img/title.jpg" id="logo" height="90px"
			width="100%">
		<div id="main">
			<div id="main-head">
				<div id="line"></div>
				<div id="register"></div>
			</div>
			<form name="re" action="user/userRegist!userRegist.action" method="post"
						accept-charset="utf-8"
						id="registerForm">
				<div id="sub-main">
					<div id="left">
						<fieldset class="span6 reset" id="field1">
							<div class="form_row" id="input1">
								<div class="input" >
									<input name="u.account" placeholder="不超过10个英汉字" type="text"
										id="name" required />
									<label for="name">
										账号:
										<span class="blue">*</span>
									</label>
									<span class="notice" id="notice1"></span>
								</div>
							</div>

							<div class="form_row" id="input2">
								<div class="input" >
									<input name="u.email" placeholder="输入正确格式：XX@yy.com"
										type="email" id="email" required />
									<label for="email">
										邮箱:
										<span class="blue">*</span>
									</label>
									<span class="notice" id="notice2"></span>
								</div>
							</div>
							<div class="form_row" id="input3">
								<div class="input">
									<input name="u.password" placeholder="不超过20个纯英文数字"
										type="password" id="password" required />
									<label for="email">
										密码:
										<span class="blue">*</span>
									</label>
									<span class="notice" id="notice3"></span>
								</div>
							</div>
							<div class="form_row" id="input4">
								<div class="input">
									<input placeholder="请确认密码" type="password"
										id="re" required />
									<label for="email">
										确认密码:
										<span class="blue">*</span>
									</label>
									<span class="notice" id="notice4"></span>
								</div>
							</div>

						<input value="提交" type="button" class="btn btn-primary" id="submittwo"/>
						</fieldset>
					</div>
				</div>
				<div id="middle"></div>
				<div id="right">
					<fieldset id="field2">
					<div class="form-group" id="input5">
						<input id="passwdQuestion" name="u.passwdQuestion"
						placeholder="请输入您的密保问题" class="input-form" type="text" />
						<span class="notice" id="notice5"></span>
					</div>
					<div class="form-group" id="input6">
						<input id="passwdAnswer" name="u.passwdAnswer"
						placeholder="请输入您的密保答案" class="input-form" type="text" />
						<span class="notice" id="notice6"></span>
					</div>
					<nav class="special" id="other">
					<div id="back">
						<a href="login.html">BACK TO LOGIN</a>
					</div>
					</nav>
					</fieldset>
				</div>
			</form>		
		</div>
		
		<img alt="foot" src="img/foot.png" id="foot">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script src="js/index.js" type="text/javascript"></script>
		<script src="js/check.js" type="text/javascript" charset=“utf-8” language="JavaScript"></script>
	</body>
</html>
