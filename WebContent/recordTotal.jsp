<%@page import="java.util.ArrayList" import="utils.*"
	import="java.util.*" import="pic.entity.*" import="db.entity.*" import="db.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="jquery/jquery.js"></script>

<link rel="stylesheet" type="text/css" href="css/wown.css" />
<link rel="stylesheet" type="text/css" href="css/processbar.css" />
<link rel="stylesheet" href="css/header.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/caicai.css" type="text/css" media="all" />
<script language="JavaScript" type="text/javascript">
<%ArrayList list1;
CourseInfo course;%>
function judge(){
	<%
	list1 = (ArrayList) session.getAttribute("coursesInfo");
	course=(CourseInfo)list1.get(0);%>
document.getElementById("courseN").innerHTML =
<%=course.getCname()%>
;
document.getElementById("teacherN").innerHTML =
<%=session.getAttribute("name")%>
;
	var count = <%=session.getAttribute("classNum")%>;
	if(count!=1)
	{ 
		$("#start").hide();
		$("#now").hide();
	};

	
	session.get(id),
	<%List<StudentInfo> studentInfo = DBHelper.getStudentInfoForAClassByCnoTno((String)session.getAttribute("id"),(String)session.getAttribute("ccid"));
	int length = studentInfo.size();
	%>
	//获取课程数
	var getTr = <%=length%>;
<%for (int i = 0; i < length; i++) {%>
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	var newl, newc;
	newl = mytable.insertRow();
	//第一列
	newc = newl.insertCell();
	
	newc.innerHTML = '<%=studentInfo.get(i).getSno()%>';

	//第二列
	newc = newl.insertCell();
	
	newc.innerHTML = '<%=studentInfo.get(i).getSname()%>';

	<% int absenceNum = studentInfo.get(i).getAbsenceNum();
	   int maxAbsence = course.getMaxAbsence();
	   int result = (int) (((double)absenceNum / (double)maxAbsence)*100);
	   String show = absenceNum +"/" + maxAbsence;
	   String finalresult = result + "%";
	   String str;
	   if((double)studentInfo.get(i).getAbsenceNum()/course.getMaxAbsence() > 0.6){
		   str = "'<div class=\"progress\" id=\"myprogress\"><span id=\"progressbar_out\" class=\"red\" style=\"width:"+finalresult+";\"><span id=\"progressbar_in\">"+show+"</span></span></div>'";

	   }else{
		   str = "'<div class=\"progress\" id=\"myprogress\"><span id=\"progressbar_out\" class=\"blue\" style=\"width:"+finalresult+";\"><span id=\"progressbar_in\">"+show+"</span></span></div>'";

	   }
	%>
	//第三列
	newc = newl.insertCell();
	newc.innerHTML = <%=str%>;

	mytable.appendChild(tr);
	<%}%>

}
<%List<StudentInfo> list = new ArrayList<StudentInfo>();
			list = (List<StudentInfo>) session.getAttribute("absenceList");%>
	function start() {
		window.location.href = "teacher.index.jsp";
	}
	function set() {
		window.location.href = "setting.jsp";
	}
	function recordTotal() {
		window.location.href = "recordTotal.jsp";
	}
	function record() {
		window.location.href = "timeUp.jsp";
	}
</script>
</head>


<body onload="getValue();">
	<div class="container">
		<div class="logo">
			<a href="index.html"><img src="images/logo.png"
				class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
		</div>
		<div class="header-right">

			<ul class="navigatoin">
				<li><img src="./images/tx.png" class="studentimg" alt="" /> <label
					class="studentname" id="teacherN"></label></li>
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
				<img src="images/bbb_01.jpg"/><img src="images/bbb_02.jpg"
					id="2"/><img src="images/bbb_03.jpg"/><img
							src="images/bbb_04.jpg"/>
			</div>
			<div class="div2">
				<h1 style="color: #000000; align-content: center;" id="courseN"></h1>
			</div>

			<div class="div2" onclick="start()" id="start">开始点名</div>
			<div class="div2" onclick="set()">点名设置</div>
			<div class="div2" onclick="record()" id="now">本堂课点名记录</div>
			<div class="div2" onclick="recordTotal()">本学期点名记录</div>
		</div>
	</div>

	<div class="table-c">
		<table id="myTable">
			<tbody>
				<tr>
					<td>学号</td>
					<td>姓名</td>
					<td>缺课情况<font size="2pt" color="#8D8D8D">&nbsp;&nbsp;&nbsp;(缺勤数/最大缺勤数)</font></td>
				</tr>
				
			</tbody>
		</table>
	</div>
	<div id="table1">
		<p>&nbsp;</p>
	</div>
</body>

</html>