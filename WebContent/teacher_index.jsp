<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="utils.*" import="java.util.*" import="pic.entity.*"
	import="db.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jquery/jquery.js"></script>
<script language="JavaScript" type="text/javascript">
	var c = 60 *
<%=session.getAttribute("daojishi")%>;
	//10分钟
	var t;
	var m;
	var s;

	function timedCount() {
		if (c == 0) {
			m = 0;
			s = 0;
			window.location.href = "timeUp.jsp";
			$("#now").show();
		} else {
			m = parseInt(c / 60);
			s = c - 60 * m;
			document.getElementById('min').innerHTML = m + "分";
			document.getElementById('sec').innerHTML = s + "秒";
			c = c - 1;
			t = setTimeout("timedCount()", 1000);
			$("#now").hide();
		}
	}
	function getValue() {
<%ArrayList list;
			CourseInfo course;
			list = (ArrayList) session.getAttribute("coursesInfo");
			course=(CourseInfo)list.get(0);%>
	;
	}
	function start() {
	}
	function set() {
		window.location.href = "setting.jsp";
	}
	function recordTotal() {
		window.location.href = "classInfo.jsp";
	}
	function record() {
		window.location.href = "timeUp.jsp";
	}
</script>

<link rel="stylesheet" href="./css/header.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="css/daohang.css" type="text/css" media="all" />
</head>

<body onload="getValue()">
	<div class="container">
		<div class="logo">
			<a href="index.html"><img src="images/logo.png"
				class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
		</div>
		<div class="header-right">

			<ul class="navigatoin">
				<li><img src="./images/tx.png" class="studentimg" alt="" width="32px" height="32px"/> <label
					class="studentname"><%=session.getAttribute("name")%></label></li>
				<li><a href="" class="active">注销</a></li>
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
				<img src="images/bbb_01.jpg" /><img src="images/bbb_02.jpg" id="2" /><img
					src="images/bbb_03.jpg" /><img src="images/bbb_04.jpg" />
			</div>
			<div class="div2">
				<h1 style="color: #000000; align-content: center;" ><%=course.getCname()%></h1>
			</div>

			<div class="div2" onclick="start()" id="start">开始点名</div>
			<div class="div2" onclick="set()">点名设置</div>
			<div class="div2" onclick="record()" id="now">本堂课点名记录</div>
			<div class="div2" onclick="recordTotal()">本学期点名记录</div>
		</div>
	</div>
	<div style="font-family: '微软雅黑'; color: #FF0000; font-size: 18pt;">
		<div class="fileUpload btn btn-primary">
			<!--<span>Upload</span>-->
			<!-- <input type="file" class="upload" name="myFile" id="file0" /> -->
			<s:form action="teacher_savePic" method="POST" enctype="multipart/form-data">

				<s:fielderror />

				<s:file name="pic" id="file0" />

				<s:submit />

			</s:form>
		</div>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本次点名时间为10分钟！
		<div id="CountMsg" class="HotDate" style="margin-top: 0.7em">
			<span>还剩 </span> <span id="min">00分</span> <span id="sec">00秒</span>
		</div>

		<img src="" id="img0" width="800" height="350"
			style="margin-bottom: 2em; margin-top: 1.5em" />


	</div>
	<script>
		var objUrl
		$("#file0").change(function() {
			objUrl = getObjectURL(this.files[0]);
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img0").attr("src", objUrl);
			}
		});
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null;
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
	</script>

</body>

</html>