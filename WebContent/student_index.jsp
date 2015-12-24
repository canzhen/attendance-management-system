<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="utils.*" import="java.util.*" import="pic.entity.*"
	import="db.entity.*" pageEncoding="UTF-8"%>
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
var id;
var name;
var size = 0;
var Pic = function(x,y,width,height){
this.x = x;
this.y = y;
this.width = width;
this.height = height;
this.selected = false;
}
var FaceJS = function(sno,cX,cY,lXInPic,lYInPic,width,hight){
	this.sno = sno;      
	this.cX = cX;        
	this.cY = cY;        
	this.lXInPic = lXInPic;   
	this.lYInPic = lYInPic;     
	this.width = width;       
	this.hight = hight;
}
//声明arr数组
var arr = new Array();

	//初始化
	window.onload = function() {
		//判断是否开始点名
		judge();
		
		id = <%=session.getAttribute("id")%>;
		name = "<%=session.getAttribute("name")%>";
		document.getElementById("stuname").innerText=name;
		
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#0000ff';
			initData();
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

	//初始化数组数据
	function initData() {		
		<% String url="http://homework2zbing-classpic.stor.sinaapp.com/20bc08e8aa5eceb82822b101ec9e662d%20%281%29.jpg";
		List<FaceEntity> faces=new ArrayList<FaceEntity>();
		PicFace picFace=new PicFace(url);
		faces=picFace.getFaces();
		int size = 1;
		size = faces.size();
		session.putValue("picface", picFace); %>
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
			for (var m = 0; m < size; m++) {
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
	
	function judge(){
		var count = <%=session.getAttribute("count")%>;
		
		if ( count == 1 ){//当天有一节课，返回SUCCESS
			//session.put("coursesInfo", courses.get(0));//传入当前课程的类，包含具体信息
			<% ArrayList course = new ArrayList();
			  Object temp1 = session.getAttribute("coursesInfo");
			  course = (ArrayList) temp1; %>
			document.getElementById("courseTeancher").innerHTML="temp";
			document.getElementById("myCanvas").style.background="url(http://homework2zbing-classpic.stor.sinaapp.com/20bc08e8aa5eceb82822b101ec9e662d%20%281%29.jpg)";
		}else if ( count > 1){//课程冲突，返回SUCCESS，由界面判断处理
			//session.put("coursesInfo", courses);//课程冲突，将所有课传入，便于页面显示
			var str=" ";
			<%  List<CourseInfo> coursect = new ArrayList<CourseInfo>();
			   coursect = (ArrayList<CourseInfo>)session.getAttribute("coursesInfo");
				
				for(int i=0;i<coursect.size();i++){%>
					str = str+<%=coursect.get(i).getCname()%>+" , ";
				<%}%>
				
			alert(str+"上课时间冲突,不能签到");
		}else if ( count == -1 ){//当天无课，返回SUCCESS，由界面判断处理
			//session.put("coursesInfo", "这周不属于上课周，放假或者为自习周，无课");
		
			var str = <%=session.getAttribute("courseInfo")%>;
			alert(str);
		}
	}

	//传值给studentAction
	function putFace() {
	
		for(var n=0;n<arr.length;n++){
			if(arr[n].selected){
				var facejs = new FaceJS(id,0,0,arr[n].x,arr[n].y,arr[n].width,arr[n].height);
				break;
				}
			}
 		form1.action="student_addFace?face=facejs";		 
		form1.submit();
		
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
								class="studentname" id="stuname">张三</label></li>
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
			<label id="courseTeacher" name="courseTeacher"
				class="check_coursefont">Javaee 李辉</label>
		</div>
		<div class="check_tip">请在图中找出并选择你自己，确定提交</div>
		<div class="check_peopleimg">
			<canvas id="myCanvas" width="800" height="370"
				style="background:url();background-size:100% 100%">
		</div>
		<form method="post" onsubmit="putFace()" name="form1">
			<div class="check_divsubmitall">
				<input class="check_submit" type="button" value="取消" />
				<input class="check_submit" type="submit" value="确定" />
			</div>
		</form>

	</div>
</body>
</html>