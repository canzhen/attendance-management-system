<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList" import="utils.*"
	import="java.util.*" import="pic.entity.*" import="db.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="css/header.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/daohang.css" type="text/css" media="all" />
<script type="text/javascript" src="jquery/jquery.js"></script>
<title>Insert title here</title>
<script language="JavaScript" type="text/javascript">
<%ArrayList list1;
CourseInfo course;%>
var count;
function judge(){
	<%
	list1 = (ArrayList) session.getAttribute("coursesInfo");
	course=(CourseInfo)list1.get(0);%>
	 count = <%=session.getAttribute("count")%>;
	if(count!=1)
	{ 
		$("#start").hide();
		$("#now").hide();
		
	};
}
function start() {
	window.location.href="teacher.index.jsp";
}
function set() {
	window.location.href="setting.jsp";
}
function recordTotal() {
	window.location.href="classInfo.jsp";
}
function record(){
	window.location.href="timeUp.jsp";
}
</script>
</head>
<body onload="judge()">
	<div class="container">
		<div class="logo">
			<a href="index.html"><img src="images/logo.png"
				class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
		</div>
		<div class="header-right">

			<ul class="navigatoin">
				<li><img src="./images/tx.png" class="studentimg" alt="" width="32px" height="32px"/> <label
					class="studentname" ><%=session.getAttribute("name")%> </label></li>
				<li><a href="" class="active">注销</a>
			</ul>
			<div class="clearfix"></div>
		</div>

		<div class="line">
			<a href="#"><img src="images/line.png" class="img-responsive"
				alt="" /></a>
			<div class="clearfix"></div>
		</div>

	</div>

	<div class="daohang">
		<div class="div1">
			<div class="left_top">
				<img src="images/bbb_01.jpg"><img src="images/bbb_02.jpg"
					id="2"><img src="images/bbb_03.jpg"><img
					src="images/bbb_04.jpg">
			</div>
			<div class="div2">
				<h1 style="color: #000000; align-content: center;"><%=course.getCname()%></h1>
			</div>
<div class="div2" id="start" onclick="start()">
				开始点名
			</div>
			<div class="div2" onclick="set()">
				点名设置
			</div>
			<div class="div2" onclick="record()" id="now">
				本堂课点名记录
			</div>
			<div class="div2" onclick="recordTotal()">
				本学期点名记录
			</div>
		</div>
	</div>

	<form class="setting"
		style="margin-top: 30px; font-size: 10pt; font-family: '微软雅黑'; color: #000000;" action="" method="post">
		<p>课程名称：<%=course.getCname() %></p>
		<p>上课时间：<%=course.getTime() %></p>
		<p>点名时间</p>
		<input type="text" onfocus="this.value = '';" value="<%=course.getCheckTime() %>"
			onblur="if (this.value == '') {this.value = '请输入';}" name="check_time">
		<p>*最大缺勤数:</p>
		<input type="text" onfocus="this.value = '';" value="<%=course.getMaxAbsence() %>"
			onblur="if (this.value == '') {this.value = '请输入';}" name="max_absence">
		<input type="submit" value="保存" style="margin-top: 10px;" >
	</form>
</body>
</html>