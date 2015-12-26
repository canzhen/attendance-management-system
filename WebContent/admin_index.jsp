<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList" import="utils.*"
	import="java.util.*" import="pic.entity.*" import="db.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="jquery/jquery.js"></script>
<link rel="stylesheet" href="css/header.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="css/daohang.css" type="text/css" media="all" />
<script language="JavaScript" type="text/javascript">

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
				<li><img src="./images/tx.png" class="studentimg" alt=""  width="28" height="28"/> <label
					class="studentname">管理员</label></li>
				<li><a href="login_logout"  class="active">设置</a></li>
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
				<img src="images/bbb_01.jpg"/><img src="images/bbb_02.jpg"
					id="2"/><img src="images/bbb_03.jpg"/><img
					src="images/bbb_04.jpg"/>
			</div>
			<div class="div2">
				<h1 style="color: #000000; align-content: center;">管理员</h1>
			</div>

			<div class="div2">
				<div class="xwzx"></div>
				设置
			</div>
		</div>
	</div>

	<form class="setting"
		style="margin-top: 30px; font-size: 10pt; font-family: '微软雅黑'; color: #000000;" action="admin_setStartDate"
			method="post">
		<p>开学日期：<input type="text" name="date"/></p>
		<input type="submit"  value="保存" style="margin-top: 10px;"/>

	</form>
</body>

</html>