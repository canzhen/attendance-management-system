<%@page import="java.util.ArrayList" import="utils.*"
	import="java.util.*" import="pic.entity.*" import="db.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/header.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/daohang.css" type="text/css"
	media="all" />
<title>老师首页</title>
<!-- 
	若该段时间有课，则老师可以上传
 -->
<script language="JavaScript" type="text/javascript">
	
var length = 0;
<%List<CourseInfo> list = new ArrayList<CourseInfo>();
			list = (List<CourseInfo>) session.getAttribute("coursesInfo");
			List<CourseInfo> listN = new ArrayList<CourseInfo>();%>
	function getValue() {
<%CourseInfo course = new CourseInfo();
			course.setAbsenceNum(0);
			course.setCheckTime(0);
			course.setCname("");
			course.setCno("");
			course.setMaxAbsence(0);
			course.setTime("");
			course.setTno("");
			
			int m=0;
			for (; m < list.size(); m++) {
				listN.add(list.get(m));
			}
			for(m=list.size();m<6;m++){
				listN.add(course);
			}
			%>
			length = <%=list.size()%>;
	}
	


</script>
</head>
<body onload="getValue()">
	<div class="header">
		<div class="header-top">
			<div class="container">

				<div class="logo">
					<a href="index.html"><img src="images/logo.png"
						class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
				</div>
				<div class="header-right">

					<ul class="navigatoin">
						<li><img src="./images/tx.png" class="studentimg" alt=""
							width="32px" height="32px" /><label class="studentname"><%=session.getAttribute("name")%>
						</label></li>
						<li><a href="login_logout"  class="active">注销</a>
						
					</ul>
					<div class="clearfix"></div>
				</div>

				<div class="line">
					<a href="#"><img src="images/line.png" class="img-responsive"
						alt="" /></a>
					<div class="clearfix"></div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a onclick="jumpZ()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jumpZ() {
										if (length > 0) {
											document.getElementById("ccid").value = "0";
											document.getElementById("form1").submit();
										}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(0).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(0).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a href="#" onclick="jumpF()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jumpF() {
										if (length > 1) {
											document.getElementById("ccid").value = "1";
											document.getElementById("form1").submit();
									}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(1).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(1).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a href="#" onclick="jumpS()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jumpS() {
										if (length > 2) {
											document.getElementById("ccid").value = "2";
											document.getElementById("form1").submit();
									}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(2).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(2).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a href="#" onclick="jump3()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jump3() {
										if (length > 3) {
											document.getElementById("ccid").value = "3";
											document.getElementById("form1").submit();
							
									}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(3).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(3).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a href="#" onclick="jump4()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jump4() {
										if (length > 4) {
											document.getElementById("ccid").value = "4";
											document.getElementById("form1").submit();
									}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(4).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(4).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div id="portfoliolist">
					<div class="portfolio icon mix_all"
						style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper wow bounceIn capt"
							data-wow-delay="0.4s">
							<a href="#" onclick="jump5()" class="b-link-stripe b-animate-go">
								<script type="text/javascript">
									function jump5() {
										if (length >5) {
											document.getElementById("ccid").value = "5";
											document.getElementById("form1").submit();
									}
									}
								</script>
								<h1 style="margin: auto; color: #000000;"><%=listN.get(5).getCname()%></h1>
								<h4 style="margin: auto; color: #3071A9;">
									<%=listN.get(5).getTime()%>
								</h4>
								<div class="b-wrapper">
									<h2 class="b-animate b-from-left    b-delay03 ">点名</h2>
									<p class="b-animate b-from-left    b-delay03 ">View</p>
								</div>
							</a>
						</div>
					</div>
				</div>
<s:form hidden="hidden" action="teacher_gotoAClass" id="form1"  method="post">
<s:textfield id="ccid" name="ccid"></s:textfield></s:form>
				<!-- Script for gallery Here -->
				<script type="text/javascript" src="./js/jquery.mixitup.min.js"></script>
				<script type="text/javascript">
					$(function() {

						var filterList = {

							init : function() {

								// MixItUp plugin
								// http://mixitup.io
								$('#portfoliolist').mixitup({
									targetSelector : '.portfolio',
									filterSelector : '.filter',
									effects : [ 'fade ' ],
									easing : 'snap',
									// call the hover effect
									onMixEnd : filterList.hoverEffect()
								});

							},

							hoverEffect : function() {

								// Simple parallax effect
								$('#portfoliolist .portfolio').hover(
										function() {
											$(this).find('.label').stop()
													.animate({
														bottom : 0
													}, 200, 'easeOutQuad');
											$(this).find('img').stop().animate(
													{
														top : -30
													}, 500, 'easeOutQuad');
										},
										function() {
											$(this).find('.label').stop()
													.animate({
														bottom : -40
													}, 200, 'easeInQuad');
											$(this).find('img').stop().animate(
													{
														top : 0
													}, 300, 'easeOutQuad');
										});

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
</body>
</html>