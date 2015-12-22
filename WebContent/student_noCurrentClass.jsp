<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		//需要获取的参数:总行数，每列的值，进读条在程序中生成html语句传过来字符串,字符串格式入下
		document.all.table1.innerHTML = "";
		var mytable = document.getElementById("myTable");
		//获取课程数
		var getTr = <%= (int)session.getAttribute("classNum")%>;
		//动态创建表格

		for (var i = 1; i <= getTr; i++) {
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			var newl, newc;
			newl = mytable.insertRow();
			//第一列
			newc = newl.insertCell();
			newc.innerHTML = 'Javaee&nbsp;&nbsp;李辉';

			//第二列
			newc = newl.insertCell();
			newc.innerHTML = '周二&nbsp;&nbsp;6节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[1-16周]';

			//第三列
			newc = newl.insertCell();
			newc.innerHTML = '<div class="progress" id="myprogress">'
					+ '<span id="progressbar_out" class="blue" style="width: 33%;"><span id="progressbar_in">1/3</span></span>'
					+ '</div>';

			mytable.appendChild(tr);
		}

	}
</script>
<body onload="getValue();">
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
							<li><img src="images/tx.png" class="studentimg" alt="" /><label
								class="studentname">张三</label></li>
							<li><a href="" class="active">设置</a>
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
			<div id="scroll_begin">哈哈哈哈哈，你javaee点名都没来，你要挂科啦啦啦</div>
			<div id="scroll_end"> </div>
		</div>
		<script type="text/javascript">
			ScrollImgLeft();
		</script>
	</div>
	<!--
    <table>
        <tr>
            <td>行数:
                <input type="text" id="inputTr" />
            </td>
            <td>列数:
                <input type="text" id="inputTd" />
            </td>
            <tr>
                <tr>
                    <td>
                        <input type="button" value="确定" onClick="getValue()">
                    </td>
                    <td>
                        <input type="button" value="取消" onClick="cancle();" />
                    </td>
                </tr>
    </table>
-->
	<!--
    <div class="progress" id="myprogress">
        <span id="progressbar_out" class="blue" style="width: 20%;"><span id="progressbar_in">20%</span></span>
    </div>
-->
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