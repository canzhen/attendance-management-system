<%@ page language="java" contentType="text/html; charset=UTF-8"
import="utils.*"
import="java.util.*"
import="pic.entity.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css" />
<link rel="stylesheet" type="text/css" href="css/wown.css" />
<title>Insert title here</title>
</head>
<%
String url="";
Picture pic=new Picture();
List<FaceEntity> faces=new ArrayList<FaceEntity>();
PicFace picFace=new PicFace(url);
session.putValue("picface", picFace);
pic.giveFaces(url, faces, picFace);
%>
<script type="text/javascript">
	//初始化
	window.onload = function(){
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';

			//左上角的x，y坐标，长宽
			ctx.strokeRect(10, 10, 50, 50);
			ctx.strokeRect(70, 10, 50, 50);

			//添加事件响应   
			canvas.addEventListener('click', function(e) {
				p = getEventPosition(e);

				reDraw(p, ctx);

			}, false);
		}
	}
	function init() {
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';

			//左上角的x，y坐标，长宽
			ctx.strokeRect(10, 10, 50, 50);
			ctx.strokeRect(70, 10, 50, 50);
			//添加事件响应   
			canvas.addEventListener('click', function(e) {
				p = getEventPosition(e);

				reDraw(p, ctx);

			}, false);
		}
		}
	//得到点击的坐标   
	function getEventPosition(ev) {
		var x, y;
		if (ev.layerX || ev.layerX == 0) {
			x = ev.layerX;
			y = ev.layerY;
		} else if (ev.offsetX || ev.offsetX == 0) { // Opera   
			x = ev.offsetX;
			y = ev.offsetY;
		}
		return {
			x : x,
			y : y
		};
	}
	var arr = [ {
		x : 10,
		y : 10,
		width : 50,
		height : 50
	}, {
		x : 70,
		y : 10,
		width : 50,
		height : 50
	}, {
		x : 130,
		y : 10,
		width : 50,
		height : 50
	}, {
		x : 190,
		y : 10,
		width : 50,
		height : 50
	}, {
		x : 250,
		y : 10,
		width : 50,
		height : 50
	}, {
		x : 310,
		y : 10,
		width : 50,
		height : 50
	} ];
	//重绘   
	function reDraw(p, ctx) {
		var whichObject = [];
		for (var i = 0; i < arr.length; i++) {
			if (p && (arr[i].x + arr[i].width) >= p.x && p.x >= arr[i].x
					&& (arr[i].y + arr[i].height) >= p.y && p.y >= arr[i].y) {
				whichObject.push(i);
				//清空所有的绘图
				ctx.clearRect(0, 0, 800, 370);
				//把所有的脸选择改为false
				for(var n=0;n<arr.length;n++){
						arr[n].selected = false;
					}
				//把所有的脸的框框绘制出来
				init();
				ctx.fillStyle = 'rgba(0,0,255,0.5)';
				ctx.strokeStyle = '#0000ff';
				//把选中的脸标记位true
				arr[i].selected = true;
				//绘制选中的脸
				ctx.fillRect(arr[i].x, arr[i].y, arr[i].width, arr[i].height);
				ctx.strokeRect(arr[i].x, arr[i].y, arr[i].width, arr[i].height);
				break;
			}
		}
	}
</script>
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
	<div class="welcome_center">
		<div>
			<label id="courseTeancher" name="courseTeancher"
				class="check_coursefont">Javaee 李辉</label>
		</div>
		<div class="check_tip">请在图中找出并选择你自己，确定提交</div>
		<div class="check_peopleimg">
			<canvas id="myCanvas" width="800" height="370"
				style="background:url(images/test.jpg);background-size:100% 100%">
		</div>
		<div class="check_divsubmitall">
			<input class="check_submit" type="button" value="取消" /> <input
				class="check_submit" type="submit" value="确定" />
		</div>

	</div>
</body>
</html>