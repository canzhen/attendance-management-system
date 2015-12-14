<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="db.entity.Teacher"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
<!-- 
	由于我们默认每次都返回到该界面，所以在该界面上我们做一些简单的逻辑判断
	可以通过session传递一些attribute来看是否登录成功
	若登录成功则几分钟内自动跳转到	课程界面
	若未登录则提示登录，若登录失败则用javascript写出用户名密码错误等等信息
 -->
 <%
 	List<String> test = (List<String>)session.getAttribute("result");
 %>
</head>
<body>
登录界面首页<br>
教师姓名：
<% for (int i = 0; i < test.size(); i++){ %>
<%= test.get(i) %>
<%} %>
</body>
</html>