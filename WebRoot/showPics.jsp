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

		<title>相片查看</title>

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

.gallery li:nth-child (6n){
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
	</head>
	<body oncontextmenu='return false' ondragstart='return false'>
		<!--<h1>jQuery zoomVisualizer Plugin Demo</h1>-->
		<div class="container">
			<!-- 代码开始 -->
			<ul class="gallery">
				<li>
					<a href="uploadFiles/image1.jpg"><img
							src="uploadFiles/image1.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image2.jpg"><img
							src="uploadFiles/image2.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image3.jpg"><img
							src="uploadFiles/image3.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image4.jpg"><img
							src="uploadFiles/image4.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image5.jpg"><img
							src="uploadFiles/image5.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image6.jpg"><img
							src="uploadFiles/image6.jpg" />
					</a>
				</li>
				<li>
					<a href="uploadFiles/image7.jpg"><img
							src="uploadFiles/image7.jpg" />
					</a>
				</li>

			</ul>
			<script src="js/jquery.js"></script>
			<script src="js/zoom.min.js"></script>
			<script type="text/javascript">
				
			</script>
			<!-- 代码结束 -->
		</div>
		
	</body>
</html>