<%@page import="java.util.ArrayList" import="utils.*" import="java.util.*" import="pic.entity.*"
	import="db.entity.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="css/header.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="css/caicai.css" type="text/css" media="all" />
<title>老师首页</title>
<!-- 
	若该段时间有课，则老师可以上传
 -->
 <script language="JavaScript" type="text/javascript">
 <%int i=0;%>
 <% List<CourseInfo> list=new ArrayList<CourseInfo>();
 list=(List<CourseInfo>)session.getAttribute("coursesInfo");%>
 function jump() {
	var value1=document.getElementById("cno").value;
	document.getElementById("cnoN").value=value1;
	document.getElementById("form").submit();
	window.location.href="setting.jsp";
}
</script>
</head>
<body>
	<div class="header">
	<div class="header-top">
		<div class="container">
			
				<div class="logo">
            <a href="index.html"><img src="images/logo.png" class="img-responsive" alt="" /></a>
            <font class="login_font_title">超人点名</font>
        </div>
        <div class="header-right">

             <ul class="navigatoin">
                <li><img src="./images/tx.png" class="studentimg" alt=""/><label class="studentname"><%=session.getAttribute("name")%> </label></li>
                            <li><a href="" class="active">设置</a>

            </ul>
        <div class="clearfix"> </div>
    </div>

    <div class="line">
        <a href="#"><img src="images/line.png" class="img-responsive" alt="" /></a>
        <div class="clearfix"> </div>
    </div>

<div class="portfolio">
  <div class="portfolio-info">
	<div class="container">
		<%for( ;i<list.size();i++){ %>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"  style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s" onclick="jump(i)">		
							<a href="#"class="b-link-stripe b-animate-go" onclick="jump()">
								<h1 style="margin: auto; color: #000000;"><%=list.get(i).getCname() %></h1>
								<h4 style="margin: auto; color: #3071A9;"><%=list.get(i).getTime() %></h4>
								<h4 style="margin: auto; color: #3071A9;" id="cno"><%=list.get(i).getCno() %></h4>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">view</p>
								</div>
							</a>
		                </div>
					</div>
					</div>
					<%} %>
					<form method="post" id="form"hidden="hidden">
					<input id="cnoN" type="text"/>
					<input type="submit"></form>
				<!--  <div id="portfoliolist">
					<div class="portfolio icon mix_all" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s">		
							<a href="single.html" class="b-link-stripe b-animate-go">
								<h1 style="margin: auto; color: #000000;">分布式计算</h1>
								<h4 style="margin: auto; color: #3071A9;">1-16周</h2>
								<h4 style="margin: auto; color: #3071A9;">曾立刚</h2>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
		                </div>
					</div>
					</div><div id="portfoliolist">
					<div class="portfolio icon mix_all" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s">		
							<a href="single.html" class="b-link-stripe b-animate-go">
								<h1 style="margin: auto; color: #000000;">商务谈判</h1>
								<h4 style="margin: auto; color: #3071A9;">9-12周</h2>
								<h4 style="margin: auto; color: #3071A9;">程辉</h2>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
		                </div>
					</div>
					</div><div id="portfoliolist">
					<div class="portfolio icon mix_all" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s">		
							<a href="single.html" class="b-link-stripe b-animate-go">
								<h1 style="margin: auto; color: #000000;">系统分析</h1>
								<h4 style="margin: auto; color: #3071A9;">1-16周</h2>
								<h4 style="margin: auto; color: #3071A9;">张红延</h2>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
		                </div>
					</div>
					</div>
					<div id="portfoliolist">
					<div class="portfolio icon mix_all"  style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s">		
							<a href="single.html" class="b-link-stripe b-animate-go">
								
								<h1 style="margin: auto; color: #000000;">软件测试</h1>
								<h4 style="margin: auto; color: #3071A9;">1-16周</h2>
								<h4 style="margin: auto; color: #3071A9;">王洪亮</h2>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
		                </div>
					</div>
					</div>
					<div id="portfoliolist">
					<div class="portfolio icon mix_all" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt" data-wow-delay="0.4s">		
							<a href="single.html" class="b-link-stripe b-animate-go">
								<h1 style="margin: auto; color: #000000;">大型数据库</h1>
								<h4 style="margin: auto; color: #3071A9;">1-16周</h2>
								<h4 style="margin: auto; color: #3071A9;">李辉</h2>
								 <div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
								  <p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
		                </div>
					</div>-->	
				</div>
				<!-- Script for gallery Here -->
				<script type="text/javascript" src="./js/jquery.mixitup.min.js"></script>
					<script type="text/javascript">
					$(function () {
						
						var filterList = {
						
							init: function () {
							
								// MixItUp plugin
								// http://mixitup.io
								$('#portfoliolist').mixitup({
									targetSelector: '.portfolio',
									filterSelector: '.filter',
									effects: ['fade '],
									easing: 'snap',
									// call the hover effect
									onMixEnd: filterList.hoverEffect()
								});				
							
							},
							
							hoverEffect: function () {
							
								// Simple parallax effect
								$('#portfoliolist .portfolio').hover(
									function () {
										$(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
										$(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
									},
									function () {
										$(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
										$(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
									}		
								);				
							
							}
				
						};
						
						// Run the show!
						filterList.init();	
					});	
					</script>
				<!-- Gallery Script Ends -->
			<div class="clearfix"></div>
	</div>
	</div>
</div>	
<!--<div class="footer">
	<div class="p-button">
			<a href="#" class="Donec btn-1 btn1-1b">Hire me!</a></br>
			<div class="sc-icons">
			</div>
			<div class="sc-icons-1">
			</div>
	</div>
	<div class="copy-rights">
					 <p>Template by<a href="http://w3layouts.com/"> 家瓦伊意</a></p>
				</div>
</div>			-->
</body>
</html>