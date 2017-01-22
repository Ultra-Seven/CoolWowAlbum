<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<em><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>我的涂鸦板</title>
	
	<!-- jQuery -->
	<script type="text/javascript" src="./inc/jquery.1.8.2.min.js"></script>
	<script type="text/javascript" src="./inc/jquery.ui.core.min.js"></script>
	<script type="text/javascript" src="./inc/jquery.ui.widget.min.js"></script>
	<script type="text/javascript" src="./inc/jquery.ui.mouse.min.js"></script>
	<script type="text/javascript" src="./inc/jquery.ui.draggable.min.js"></script>
	
	<!-- wColorPicker -->
	<link rel="Stylesheet" type="text/css" href="./inc/wColorPicker.css" />
	<script type="text/javascript" src="./inc/wColorPicker.js"></script>
	
	<!-- wPaint -->
	<link rel="Stylesheet" type="text/css" href="./wPaint.css" />
	<script type="text/javascript" src="js/wPaint.js"></script>
	
	<style>
		body, html{margin:0px;}
	</style>

	<script type="text/javascript">
		$(window).resize(function()
		{
			var width = $(window).width();
			var height = $(window).height();

			$('#wPaint').css({
				width: width,
				height: height
			});

			var wp = $("#wPaint").data('_wPaint');

			//var imageData = $("#wPaint").wPaint("image"); // if you want to maintain the image after resizing the canvas

			$(wp.canvas).attr('width', width + 'px').attr('height', height + 'px');

			//$("#wPaint").wPaint("image", imageData);
		})

		$(document).ready(function(){ $(window).resize(); });
	</script>
</head>
<body>
	<div id="wPaint" style="position:relative; background:#CACACA;"></div>
	<button id="submit" onClick="save()" style="width: 130px;  height: 51px;">上传至涂鸦墙</button>
	<input type="hidden" value="${sessionScope.doodle}">

	<script>
		$("#wPaint").wPaint();
		
	</script>
	<script type="text/javascript">
		function drawBeauty(beauty){
			$("#wPaint").children().first().attr("id","cv");
			var mycv = document.getElementById("cv");
			var myctx = mycv.getContext("2d");
			myctx.drawImage(beauty, 0, 0);
		}
		function load(){
			var beauty = new Image();  
			beauty.src = "${sessionScope.doodle}" ; 
			if(beauty.complete){
				$("#wPaint").css({"width":beauty.width,"height":beauty.height});
  					$("#wPaint").children().first().attr("width",beauty.width);
					$("#wPaint").children().first().attr("height",beauty.height);
   				drawBeauty(beauty);
			}
			else{
  				beauty.onload = function(){
  				alert(beauty.width);
  					$("#wPaint").css({"width":beauty.width,"height":beauty.height});
  					$("#wPaint").children().first().attr("width",beauty.width);
					$("#wPaint").children().first().attr("height",beauty.height);
					drawBeauty(beauty);
					};
					beauty.onerror = function(){
						window.alert('图片加载失败，请重试');
					};
				};   
			}//load
		if (document.all) {
		 	window.attachEvent('onload', load);  
		 }
		 else {  
		  	window.addEventListener('load', load, false);
		 }
	</script>
	
	<script type="text/javascript">
		function save(){
			var canvas=document.getElementById("cv");
			convertCanvasToImage(canvas);
		}
		function convertCanvasToImage(canvas) {
			var image = new Image();
			image.src = canvas.toDataURL("image/png");
			alert("yes")//路径自己改改- -image
			var data = canvas.toDataURL();  
  			//删除字符串前的提示信息 "data:image/png;base64,"  
    		var b64 = data.substring(22);  
  			$.ajax({ 
  				url: "doodle/saveCanvas!saveCanvas.action",
  				type:'post', 
  				data: {data: b64,picId:${sessionScope.doodlePic}}, 
  				success:  
            	function ()  
           	 	{  
            		window.location = "doodle/doodleView!showWall.action?userId=1";
            	}  
    		});  
			return image;
		}
</script>
</body>

</html></em>