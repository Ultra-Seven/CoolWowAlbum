<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>图片搜索</title>

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
		<link href="css/search_css.css" rel="stylesheet" />
	</head>
	<body>
		<div class="wrapper">
			<div class="header">
			</div>
			<div class="main">
				<div class="logo">
					<div class="logo_pic"></div>
					<div class="logo_title"></div>
				</div>
				<div class="searchBar">
					<form action="photo/search.action" id="formAction">
						<input type="text" name="search" class="search" id="searchInput" />
						<input type="submit" value="酷我一下" id="search_btn"
							class="search_btn btn btn-primary" />
					</form>
				</div>
			</div>
			<div class="footer">
			</div>
		</div>
	</body>
</html>
