<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="db.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css" />
<link rel="stylesheet" type="text/css" href="css/wown.css" />
<link rel="stylesheet" type="text/css" href="css/processbar.css" />
<title>学生首页</title>
<!-- 
	若该时间段有课，那么显示该节课的各种信息。
	如果正在点名并且老师已经上传图片，那么显示图片供学生签到。
 -->


</head>
<script>
    
	function ScrollImgLeft() {
		var speed = 50;
		var scroll_begin = document.getElementById("scroll_begin");
		var scroll_end = document.getElementById("scroll_end");
		var scroll_div = document.getElementById("scroll_div");
		scroll_end.innerHTML = scroll_begin.innerHTML + scroll_begin.innerHTML
				+ scroll_begin.innerHTML;

		function Marquee() {
			if (scroll_end.offsetWidth - scroll_div.scrollLeft <= 0)
				scroll_div.scrollLeft -= scroll_begin.offsetWidth;
			else
				scroll_div.scrollLeft++;
		}
		var MyMar = setInterval(Marquee, speed);
		scroll_div.onmouseover = function() {
			clearInterval(MyMar);
		}
		scroll_div.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		}
	}

	function getValue() {
		var name = "<%=session.getAttribute("name")%>";
		document.getElementById("stuname").innerText = name;
		//需要获取的参数:总行数，每列的值，进读条在程序中生成html语句传过来字符串,字符串格式入下
		document.all.table1.innerHTML = "";
		var mytable = document.getElementById("myTable");
		

		//动态创建表格
	<%ArrayList<CourseInfo> course = new ArrayList<CourseInfo>();
			Object temp1 = session.getAttribute("coursesInfo");
			course = (ArrayList<CourseInfo>) temp1;
			int length = course.size();%>
			//获取课程数
			var getTr = <%=length%>;
		<%for (int i = 0; i < length; i++) {%>
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			var newl, newc;
			newl = mytable.insertRow();
			//第一列
			newc = newl.insertCell();
			
			newc.innerHTML = '<%=course.get(i).getCname()%>';

			//第二列
			newc = newl.insertCell();
			
			newc.innerHTML = '<%=course.get(i).getTime()%>';

			<% int absenceNum = course.get(i).getAbsenceNum();
			   int maxAbsence = course.get(i).getMaxAbsence();
			   int result = (int) (((double)absenceNum / (double)maxAbsence)*100);
			   String show = absenceNum +"/" + maxAbsence;
			   String finalresult = result + "%";
			   String str;
			   if((double)course.get(i).getAbsenceNum()/(double)course.get(i).getMaxAbsence() > 0.6){
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

			
			<%String warning=null;
				for(int i=0;i<course.size();i++){
				if((double)course.get(i).getAbsenceNum()/(double)course.get(i).getMaxAbsence() > 0.6){
					if(warning==null)
						warning = " ";
					warning = warning + course.get(i).getCname()+"  ";
				}
				
			}
				if(warning!=null)
					warning = warning + "这些课程，你的缺勤次数太多啦，小心挂科！！！最后真诚的祝你科科都不挂！！";
				else
					warning = "出勤好积极！再接再厉，最后真诚的祝你科科都不挂！！";
			%>
		document.getElementById("scroll_begin").innerHTML="<%=warning%>";
		ScrollImgLeft();

	}
	//注销
	function logout(){
		document.location.href="login_logout";
	}

</script>
<body onload="getValue();">
	<div class="header">
		<div class="header-top">
			<div class="container">
				<div class="logo">
					<a href="welcome.jsp"><img src="images/logo.png"
						class="img-responsive" alt="" /></a> <font class="login_font_title">超人点名</font>
				</div>
				<div class="header-right">
					<div class="menu">
						<span class="menu"> </span>
						<ul class="navigatoin">
							<li><img src="images/tx.png" class="studentimg" alt="" /><label
								class="studentname" id="stuname">张三</label></li>
							<li><a href="" class="active" onclick="logout()">设置</a>
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
	<div id="gongao">
		<div class="scroll_div" id="scroll_div"
			style="width: 60%; height: 30px; margin: 0 auto; white-space: nowrap; overflow: hidden;">
			<div id="scroll_begin"></div>
			<div id="scroll_end"> </div>
		</div>
	
	</div>

	<div class="table-c">
		<table id="myTable">
			<tbody>
				<tr>			
					<td>课程</td>
					<td>时间</td>
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