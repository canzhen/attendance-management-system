<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="utils.*" import="java.util.*" import="pic.entity.*"
	import="db.entity.*" import="db.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="jquery/jquery.js"></script>

<link rel="stylesheet" type="text/css" href="css/wown.css" />
<link rel="stylesheet" type="text/css" href="css/processbar.css" />
<link rel="stylesheet" href="css/header.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/daohang.css" type="text/css" media="all" />
<script type="text/javascript">
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
	
}
	function getValue() {
	
		document.all.table1.innerHTML = "";
        <% CourseInfo course = ((List<CourseInfo>)session.getAttribute("coursesInfo")).get(0); %>
	    var mytable = document.getElementById("myTable");
		//动态创建表格
<%List<StudentInfo> studentInfo=DBHelper.getStudentInfoForAClassByCnoTno((String)session.getAttribute((String)session.getAttribute("id")),course.getCno());%>
	<%for (int i = 0; i <studentInfo.size(); i++){%>
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			var newl, newc;
			newl = mytable.insertRow();
			//第一列
			newc = newl.insertCell();
			newc.innerHTML =
'<%=studentInfo.get(i).getSno()%>'
	;

			//第二列
			newc = newl.insertCell();
			newc.innerHTML =
'<%=studentInfo.get(i).getSname()%>'
	;

			mytable.appendChild(tr);
		<%}%>
	}
</script>

</head>

<body onload="getValue()">
	<div class="container">
		<div class="logo">
			<a href="index.html"><img src="images/logo.png"
				class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
		</div>
		<div class="header-right">

			<ul class="navigatoin">
				<li><img src="./images/tx.png" class="studentimg" alt="" width="32px" height="32px"/><label
					class="studentname"><%=session.getAttribute("name")%> </label></li>
				<li><a href="login_logout"  class="active">注销</a></li>

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
				<h1 style="color: #000000; align-content: center;" id="courseN"><%=course.getCname()%></h1>
			</div>
			<div class="div2" onclick="start()" id="start">开始点名</div>
			<div class="div2" onclick="set()">点名设置</div>
			<div class="div2" onclick="record()" id="now">本堂课点名记录</div>
			<div class="div2" onclick="recordTotal()">本学期点名记录</div>
		</div>
	</div>
	<h1 style="margin-top: 2em;">
		本次课缺勤记录
		</h1>
		<div class="table-c">
			<table id="myTable">
				<tbody>
					<tr>
						<td>学号</td>
						<td>姓名</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="table1">
			<p>&nbsp;</p>
		</div>
</body>

</html>