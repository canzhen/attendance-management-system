﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="stylesheet" href="css/caicai.css" type="text/css" media="all" />
<script type="text/javascript">
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
	
}
//声明Pic对象
var size = 0;
var Pic = function(x,y,width,height,sno){
this.x = x;
this.y = y;
this.width = width;
this.height = height;
this.sno=sno;
}
//声明arr数组
var arr = new Array();
<%List<FaceEntity> faces;%>
	//初始化
	window.onload = function() {
		getValue();
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';
			initData();
			//左上角的x，y坐标，长宽
			for (var m = 0; m < size; m++) {
				if (arr[m].sno == null)
					ctx.strokeRect(arr[m].x, arr[m].y, arr[m].width,
							arr[m].height);
				else
					ctx.fillRect(arr[m].x, arr[m].y, arr[m].width,
							arr[m].height);
			}
			
			
		}
	}
	
	//初始化数组数据
	function initData() {		
		<%
		faces=new ArrayList<FaceEntity>();
		faces=(List<FaceEntity>)session.getAttribute("checkFaceList");
		int length = 1;
		length = faces.size(); %>
		//初始化二维数组
		size=<%=length%>;
		for(var m=0;m<size;m++){
			arr[m]=new Pic(0,0,0,0);
		}
		//二维数组赋值
		<%if(faces!=null){
			for(int i=0;i<length;i++){%>
			arr[<%=i%>].x = <%=faces.get(i).getlXInPic()%>;
			arr[<%=i%>].y = <%=faces.get(i).getlYInPic()%>;
			arr[<%=i%>].width = <%=faces.get(i).getWidth()%>;
			arr[<%=i%>].height = <%=faces.get(i).getHight()%>;
			arr[<%=i%>].sno=<%=faces.get(i).getSno()%>;
<%}
		}%>
	}
	function getValue() {
	
		document.all.table1.innerHTML = "";
        <% CourseInfo course = (CourseInfo)session.getAttribute("coursesInfo"); %>
	    var mytable = document.getElementById("myTable");
		document.getElementById("courseN").innerHTML =
        <%=course.getCname()%>;
        <%String url="";
        url = (String)session.getAttribute("classPicUrl");%>
        var bgurl = '"url('+<%=url%>+')"';
		if(bgurl==null){
			alert("fail");
			}else{
				document.getElementById("myCanvas").style.backgroundImage="url("+urlpic+")";
				}
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
<%=studentInfo.get(i).getSno()%>
	;

			//第二列
			newc = newl.insertCell();
			newc.innerHTML =
<%=studentInfo.get(i).getSname()%>
	;

			mytable.appendChild(tr);
		<%}%>
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
				<li><img src="./images/tx.png" class="studentimg" alt="" /><label
					class="studentname"><%=session.getAttribute("name")%> </label></li>
				<li><a href="" class="active">设置</a></li>

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
				<h1 style="color: #000000; align-content: center;" id="courseN"></h1>
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
		<div class="check_peopleimg">
			<<canvas id="myCanvas" width="800" height="370"
				style="background:url(d:\\1.jpg);background-size:100% 100%">
		</div>
</body>

</html>