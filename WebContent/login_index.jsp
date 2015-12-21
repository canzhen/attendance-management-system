<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="db.entity.Teacher"%>
<%@page import="db.entity.Teacher"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css" />
<link rel="stylesheet" type="text/css" href="css/wown.css" />
<link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
<title>登录页</title>
<!-- 

<meta http-equiv="refresh" content="5; url=student_checkself.jsp" />

	由于我们默认每次都返回到该界面，所以在该界面上我们做一些简单的逻辑判断
	可以通过session传递一些attribute来看是否登录成功
	若登录成功则几分钟内自动跳转到	课程界面
	若未登录则提示登录，若登录失败则用javascript写出用户名密码错误等等信息
 -->
<script type="text/javascript">
	window.onload()
	{
		if (true) {
			//登录成功判断跳进哪个界面
			if (true)
				document.location.href = "student_checkself.jsp"
			else
				document.location.href = "student_index.jsp"
		}

	}
</script>

</head>
<body>
	<div class="header">
		<div class="header-top">
			<div class="container">
				<div class="logo">
					<a href="index.html"><img src="images/logo.png"
						class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
				</div>
				<div class="header-right">
					<div class="menu">
						<span class="menu"> </span>
						<ul class="navigatoin">
							<li><a href="index.html" class="active">帮助</a></li>

						</ul>
						<div class="clearfix"></div>

					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="line">
		<a href="#"><img src="images/line.png" class="img-responsive"
			alt="" /></a>
		<div class="clearfix"></div>
	</div>
	<div class="login_center">

		<div class="message warning">
			<div class="inset">
				<div class="login-head">
					<h1>用户登录</h1>

				</div>
				<s:form action="login_check.action">
					<li>
						<div class="login_li">
							<input type="radio" name="identity" value="学生" checked="true">学生
							<input type="radio" name="identity" value="教师">教师 <input
								type="radio" name="identity" value="管理员">管理员
						</div>

					</li>
					<li><input type="text" class="text" placeholder="用户名"><a
						href="#" class=" icon user"></a></li>
					<div class="clear"></div>
					<li><input type="password" placeholder="密码"> <a
						href="#" class="icon lock"></a></li>
					<div class="clear"></div>
					<div class="submit">
						<s:a href=""></s:a>
						<s:submit value="登录" cssClass="inputSubmit" />
						<div class="clear"></div>
					</div>
				</s:form>
			</div>

		</div>
		<div style="text-align: center">
			<font color="red" size="12pt"><s:property value="msg" /></font>
		</div>

	</div>
	<div class="clear"></div>

	<div style="display: none"></div>
</body>

</html>