
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>酷我相册</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/index_css.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/slicebox.css" />
<link rel="stylesheet" type="text/css" href="css/custom.css" />
<!-- hot pictures css -->
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo2.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
</head>

<body>
<div class="wrapped">
	<div class="header">
		<!-- 导航栏 -->
		<div class="nav" id="container">
			<ul id="nav">
        		<li ><s:if test="#session.logUser.account==#session.showAccount.account">
								<a href="photo/photoLoad!photoLoad.action">我的相册</a>
							</s:if>
							<s:else>
								<a href="photo/photoLoad!photoLoad.action">别人的相册</a>
							</s:else></li>
       	 		<li id="selected"><a href="user/index.action">图片广场</a></li>
       	 		<s:if test="#session.logUser.account==#session.showAccount.account">
        		<li><a href="doodle/doodleView!showWall.action?userId=1">涂鸦墙</a></li>
        		</s:if>
        		<s:else >
        		<li><a href="doodleView!showWall.action?userId=2">涂鸦墙</a></li>
        		</s:else>
        		<li><a href="search.jsp">搜索</a></li>
        	</ul>
        	<div class="leftNav" id="leftNav">
        		<span class="welcome">欢迎你，<s:property value="#session.logUser.account"/></span>
        		<span class="icon-user"></span>
        		<div class="dropdown">
        			<button class="btn dropdown-toggle " type="button" id="dropdownMenu1" data-toggle="dropdown">  
              				<span class="caret"></span>  
        			</button>  
        			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">  
            			<li role="presentation">
									<a href="ucp/ucp.action?owner=<s:property value="#session.logUser.account" />" role="menuitem" tabindex="-1">个人中心</a>
								</li>
            			<li role="presentation"><a href="other/return!returnMyAccount.action" role="menuitem" tabindex="-1">我的相册</a></li>  
            			<li role="presentation"><a href="msg/msg.action" role="menuitem" tabindex="-1">我的私信</a></li>  
            			<li role="presentation"><a href="ucp/ucp.action#settings" role="menuitem" tabindex="-1">个人设置</a></li>  
            			<li role="presentation"><a href="user/logOff!logOff.action" role="menuitem" tabindex="-1">退出</a></li>  
            		</ul>    
        		</div>
        	</div>
		</div>
		<!-- 滚动box-->
		<div class="sliceBox" id="sliceBox">
			<div class="wrapper">
				<header class="codrops-header">
				<h1>Top Five </h1>	
				</header>
				<ul id="sb-slider" class="sb-slider">
					<li>
						<a href="${sessionScope.href[0]}" target="_blank"><img src="${sessionScope.picsUrl[0]}" alt="image1"/></a>
						<div class="sb-description">
							<h3>${sessionScope.picName[0]}</h3>
						</div>
					</li>
					<li>
						<a href="${sessionScope.href[1]}" target="_blank"><img src="${sessionScope.picsUrl[1]}" alt="image2"/></a>
						<div class="sb-description">
							<h3>${sessionScope.picName[1]}</h3>
						</div>
					</li>
					<li>
						<a href="${sessionScope.href[2]}" target="_blank"><img src="${sessionScope.picsUrl[2]}" alt="image3"/></a>
						<div class="sb-description">
							<h3>${sessionScope.picName[2]}</h3>
						</div>
					</li>
					<li>
						<a href="${sessionScope.href[3]}" target="_blank"><img src="${sessionScope.picsUrl[3]}" alt="image4"/></a>
						<div class="sb-description">
							<h3>${sessionScope.picName[3]}</h3>
						</div>
					</li>
					<li>
						<a href="${sessionScope.href[4]}" target="_blank"><img src="${sessionScope.picsUrl[4]}" alt="image5"/></a>
						<div class="sb-description">
							<h3>${sessionScope.picName[4]}</h3>
						</div>
					</li>
					
				</ul>
				<div id="shadow" class="shadow"></div>
				<div id="nav-arrows" class="nav-arrows">
					<a href="#">Next</a>
					<a href="#">Previous</a>
				</div>

			</div><!-- /wrapper -->
		</div>
	</div>
	<div class="main">
		<div class="containerMain">
			<header class="codrops-header">
				<h1>Hot Pictures </h1>	
			</header>
			<div class="grid">
				<figure class="effect-lily">
					<img src="${sessionScope.picsUrl[5]}" alt="img01"/>
					<figcaption>
						<h2>${sessionScope.picName[5]} <span>${sessionScope.personName[5]}</span></h2>
						<p>date:${sessionScope.date[5]}</p>
						<a href="${sessionScope.href[5]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-sadie">
					<img src="${sessionScope.picsUrl[6]}" alt="img02"/>
					<figcaption>
						<h2>${sessionScope.picName[6]} <span>${sessionScope.personName[6]}</span></h2>
						<p>date:${sessionScope.date[6]} </p>
						<a href="${sessionScope.href[6]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-honey">
					<img src="${sessionScope.picsUrl[7]}" alt="img03"/>
					<figcaption>
						<h2>${sessionScope.picName[7]} <span>${sessionScope.personName[7]}</span> <i>Now</i></h2>
						<a href="${sessionScope.href[7]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-layla">
					<img src="${sessionScope.picsUrl[8]}" alt="img04"/>
					<figcaption>
						<h2>${sessionScope.picName[8]} <span>${sessionScope.personName[8]}</span></h2>
						<p>date:${sessionScope.date[8]}</p>
						<a href="${sessionScope.href[8]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-zoe">
					<img src="${sessionScope.picsUrl[9]}" alt="img05"/>
					<figcaption>
						<h2>${sessionScope.picName[9]} <span>${sessionScope.personName[9]}</span></h2>
						<span class="icon-heart"></span>
						<span class="icon-eye"></span>
						<span class="icon-paper-clip"></span>
						<p>date:${sessionScope.date[9]}</p>
						<a href="${sessionScope.href[9]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-oscar">
					<img src="${sessionScope.picsUrl[10]}" alt="img06"/>
					<figcaption>
						<h2>${sessionScope.picName[10]} <span>${sessionScope.personName[10]}</span></h2>
						<p>date:${sessionScope.date[10]}</p>
						<a href="${sessionScope.href[10]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-marley">
					<img src="${sessionScope.picsUrl[11]}" alt="img07"/>
					<figcaption>
						<h2>${sessionScope.picName[11]} <span>${sessionScope.personName[11]}</span></h2>
						<p>date:${sessionScope.date[11]}</p>
						<a href="${sessionScope.href[11]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-ruby">
					<img src="${sessionScope.picsUrl[12]}" alt="img08"/>
					<figcaption>
						<h2>${sessionScope.picName[12]} <span>${sessionScope.personName[12]}</span></h2>
						<p>date:${sessionScope.date[12]}</p>
						<a href="${sessionScope.href[12]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-roxy">
					<img src="${sessionScope.picsUrl[13]}" alt="img09"/>
					<figcaption>
						<h2>${sessionScope.picName[13]} <span>${sessionScope.personName[13]}</span></h2>
						<p>date:${sessionScope.date[13]}</p>
						<a href="${sessionScope.href[13]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-bubba">
					<img src="${sessionScope.picsUrl[14]}" alt="img10"/>
					<figcaption>
						<h2>${sessionScope.picName[14]} <span>${sessionScope.personName[14]}</span></h2>
						<p>date:${sessionScope.date[14]}</p>
						<a href="${sessionScope.href[14]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-romeo">
					<img src="${sessionScope.picsUrl[15]}" alt="img11"/>
					<figcaption>
						<h2>${sessionScope.picName[15]} <span>${sessionScope.personName[15]}</span></h2>
						<p>date:${sessionScope.date[15]}</p>
						<a href="${sessionScope.href[15]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-dexter">
					<img src="${sessionScope.picsUrl[16]}" alt="img12"/>
					<figcaption>
						<h2>${sessionScope.picName[16]} <span>${sessionScope.personName[16]}</span></h2>
						<p>date:${sessionScope.date[16]}</p>
						<a href="${sessionScope.href[16]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-sarah">
					<img src="${sessionScope.picsUrl[17]}" alt="img13"/>
					<figcaption>
						<h2>${sessionScope.picName[17]} <span>${sessionScope.personName[17]}</span></h2>
						<p>date:${sessionScope.date[17]}</p>
						<a href="${sessionScope.href[17]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-chico">
					<img src="${sessionScope.picsUrl[18]}" alt="img14"/>
					<figcaption>
						<h2>${sessionScope.picName[18]} <span>${sessionScope.personName[18]}</span></h2>
						<p>date:${sessionScope.date[18]}</p>
						<a href="${sessionScope.href[18]}">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-milo">
					<img src="${sessionScope.picsUrl[19]}" alt="img15"/>
					<figcaption>
						<h2>${sessionScope.picName[19]} <span>${sessionScope.personName[19]}</span></h2>
						<p>date:${sessionScope.date[19]}</p>
						<a href="${sessionScope.href[19]}">View more</a>
					</figcaption>			
				</figure>
			</div>
		</div><!-- /container -->
		
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.spasticNav.js"></script>
<script type="text/javascript" src="js/jquery.slicebox.js"></script>
		<script type="text/javascript">
			$(function() {
				var Page = (function() {
					var $navArrows = $( '#nav-arrows' ).hide(),
						$shadow = $( '#shadow' ).hide(),
						slicebox = $( '#sb-slider' ).slicebox( {
							onReady : function() {
								$navArrows.show();
								$shadow.show();
							},
							orientation : 'r',
							cuboidsRandom : true,
							disperseFactor : 30
						} ),
						init = function() {
							initEvents();
						},
						initEvents = function() {

							// add navigation events
							$navArrows.children( ':first' ).on( 'click', function() {

								slicebox.next();
								return false;

							} );

							$navArrows.children( ':last' ).on( 'click', function() {
								
								slicebox.previous();
								return false;

							} );

						};
						return { init : init };

				})();

				Page.init();

			});
		</script>
<script type="text/javascript">
    $('#nav').spasticNav();
</script>
</body>
</html>