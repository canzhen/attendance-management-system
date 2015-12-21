<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="utils.*" import="java.util.*" import="pic.entity.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css" />
<link rel="stylesheet" type="text/css" href="css/wown.css" />
<title>Insert title here</title>
</head>

<script type="text/javascript">
//声明Pic对象
var size = 0;
var Pic = function(x,y,width,height){
this.x = x;
this.y = y;
this.width = width;
this.height = height;
}
//声明arr数组
var arr = new Array();

	//初始化
	window.onload = function() {
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';
			initData();
			//左上角的x，y坐标，长宽
			for(var m=0;m<size;m++){
				ctx.strokeRect(arr[m].x, arr[m].y, arr[m].width, arr[m].height);
			}
			
			ctx.strokeRect(116, 66, 50, 50);

			//添加事件响应   
			canvas.addEventListener('click', function(e) {
				p = getEventPosition(e);

				reDraw(p, ctx);

			}, false);
		}
	}
	//初始化数组数据
	function initData() {		
		<%String url="http://homework2zbing-classpic.stor.sinaapp.com/20bc08e8aa5eceb82822b101ec9e662d%20%281%29.jpg";
		Picture pic=new Picture();
		List<FaceEntity> faces=new ArrayList<FaceEntity>();
		PicFace picFace=new PicFace(url);
		faces=picFace.getFaces();
		pic.giveFaces(url, faces);
		int size = 1;
		size = faces.size();
		session.putValue("picface", picFace);
		%>
		//初始化二维数组
		size=<%=size%>;
		for(var m=0;m<size;m++){
			arr[m]=new Pic(0,0,0,0);
		}
		//二维数组赋值
		<%if(faces!=null){
			for(int i=0;i<size;i++){%>
			arr[<%=i%>].x = <%=faces.get(i).getlXInPic()%>;
			arr[<%=i%>].y = <%=faces.get(i).getlYInPic()%>;
			arr[<%=i%>].width = <%=faces.get(i).getWidth()%>;
			arr[<%=i%>].height = <%=faces.get(i).getHight()%>;
			
<%}
		}%>
	}
	//基本绘图
	function init() {
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';
			//initData();
			//左上角的x，y坐标，长宽
			for(var m=0;m<size;m++){
				ctx.strokeRect(arr[m].x, arr[m].y, arr[m].width, arr[m].height);
			}
			
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
				for (var n = 0; n < arr.length; n++) {
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
				style="background:url(http://homework2zbing-classpic.stor.sinaapp.com/20bc08e8aa5eceb82822b101ec9e662d%20%281%29.jpg);background-size:100% 100%">
		</div>
		<div class="check_divsubmitall">
			<input class="check_submit" type="button" value="取消" /> <input
				class="check_submit" type="submit" value="确定" />
		</div>

	</div>
</body>
</html>