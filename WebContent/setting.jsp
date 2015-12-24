<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="css/header.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/caicai.css" type="text/css" media="all" />
<script type="text/javascript" src="jquery/jquery.js"></script>
<title>Insert title here</title>
<script language="JavaScript" type="text/javascript">
function start() {
	window.location.href="teacher.index.jsp";
}
function set() {
	window.location.href="setting.jsp";
}
function recordTotal() {
	window.location.href="recordTotal.jsp";
}
function record(){
	window.location.href="timeUp.jsp";
}
</script>
</head>
<body>
	<div class="container">
		<div class="logo">
			<a href="index.html"><img src="images/logo.png"
				class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
		</div>
		<div class="header-right">

			<ul class="navigatoin">
				<li><img src="./images/tx.png" class="studentimg" alt="" /> <label
					class="studentname">张三</label></li>
				<li><a href="" class="active">设置</a>
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
				<h1 style="color: #000000; align-content: center;">JavaEE</h1>
			</div>
<div class="div2" onclick="start()">
				开始点名
			</div>
			<div class="div2" onclick="set()">
				点名设置
			</div>
			<div class="div2" onclick="record()">
				本堂课点名记录
			</div>
			<div class="div2" onclick="recordTotal()">
				本学期点名记录
			</div>
		</div>
	</div>

	<form class="setting"
		style="margin-top: 30px; font-size: 10pt; font-family: '微软雅黑'; color: #000000;">
		<p>课程名称：Javaee</p>
		<p>上课时间：1-16周，6-7节</p>
		<p>点名时间：</p>
		<input type="text" onfocus="this.value = '';"
			onblur="if (this.value == '') {this.value = '请输入';}">
		<p>*最大缺勤数:</p>
		<input type="text" onfocus="this.value = '';"
			onblur="if (this.value == '') {this.value = '请输入';}"><br />

		<input type="submit" value="保存" style="margin-top: 10px;">

	</form>
</body>
</html>