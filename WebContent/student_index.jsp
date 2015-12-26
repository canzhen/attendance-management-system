<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="utils.*" import="java.util.*" import="pic.entity.*"
	import="java.io.File"
	import="db.entity.*" pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
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
var count = 0;
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
<%String url = "";%>
//声明arr数组
var arr = new Array();

var c = 60 *<%=TimerHelper.getDaojishi((String)session.getAttribute("id"),(String)session.getAttribute("cno"))%>;
	//10分钟
	var t;
	var m;
	var s;

	function timedCount() {
		if (c == 0) {
			m = 0;
			s = 0;
			window.location.href = "timeUp.jsp";
		} else {
			m = parseInt(c / 60);
			s = c - 60 * m;
			document.getElementById('min').innerHTML = m + "分";
			document.getElementById('sec').innerHTML = s + "秒";
			c = c - 1;
			t = setTimeout("timedCount()", 1000);
		}
	}
	//初始化
	window.onload = function() {
		//判断是否开始点名
		judge();
		
		id = <%=session.getAttribute("id")%>;
		name = "<%=session.getAttribute("name")%>";
		document.getElementById("stuname").innerText=name;
		
		var canvas = document.getElementById('myCanvas');
		if (canvas.getContext && count == 1) {
			var ctx = canvas.getContext('2d');

			ctx.strokeStyle = '#ff0000';
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
	function judge(){
		count = <%=session.getAttribute("coursesNum")%>;

		//var testimg = "images/test.jpg";
		//document.getElementById("myCanvas").style.backgroundImage="url("+urlpic+")";
		if ( count == 1 ){//当天有一节课
			
			<% ArrayList<CourseInfo> course = new ArrayList<CourseInfo>();
			  Object temp1 = session.getAttribute("coursesInfo");
			  course = (ArrayList<CourseInfo>) temp1;
			  String cname = course.get(0).getCname();
			  url = (String)session.getAttribute("picUrl");%>

			var coursename = "<%=cname%>";
			document.getElementById("courseTeacher").innerHTML= coursename;

			
			var urlpic = "<%=url%>";
			if(urlpic==null){
				document.getElementById("tips").innerHTML="还未开始点名或者图片上传不成功，请耐心等候";
			}else{
					document.getElementById("myCanvas").style.backgroundImage="url("+urlpic+")";
			}
			
		}else if ( count > 1){//课程冲突
			
			
			document.location.href="student_info.jsp";
			
		}else if ( count == -1 ){//当天无课
			
			
			document.location.href="student_info.jsp";
		}
	}
	//初始化数组数据
	function initData() {		
		<% 
		List<FaceEntity> faces=new ArrayList<FaceEntity>();
		String urlzb = (String)session.getAttribute("picUrl");
		urlzb = "c:\\"+urlzb;

		PicFace picFace=new PicFace(new File(urlzb));
		faces=picFace.getFaces();
		int size = 0;
		if(faces!=null)
			size = faces.size();
		//session.putValue("facesList",faces);
		//session.putValue("picface", picFace); %>
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

			ctx.strokeStyle = '#ff0000';
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
				ctx.clearRect(0, 0, 800, 400);
				//把所有的脸选择改为false
				for (var n = 0; n < arr.length; n++) {
					arr[n].selected = false;
				}
				//把所有的脸的框框绘制出来
				init();
				ctx.fillStyle = 'rgba(255,0,0,0.5)';
				ctx.strokeStyle = '#ff0000';
				//把选中的脸标记位true
				arr[i].selected = true;
				
				document.getElementById("index").value=i;
				
				//绘制选中的脸
				ctx.fillRect(arr[i].x, arr[i].y, arr[i].width, arr[i].height);
				ctx.strokeRect(arr[i].x, arr[i].y, arr[i].width, arr[i].height);
				break;
			}
		}
	}
	
	
	//注销
	function logout(){
		document.location.href="login_logout";
	}

	
	function cancleFunction(){
		init();
	}
</script>
<body>
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
							<li><img src="images/default.png" class="studentimg" alt="" width="32" height="32"/><label
								class="studentname" id="stuname"></label></li>
							<li><a href="" class="active" onclick="logout()">注销</a>
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
			<label id="courseTeacher" 
				class="check_coursefont"></label>
		</div>
		<div id="CountMsg" class="HotDate">
			<span>还剩 </span> <span id="min"></span> 
			<span id="sec">00秒</span>
		</div>
		<div class="check_tip" id="tips">请在图中找出并选择你自己，确定提交</div>
		<div class="check_peopleimg">
			<canvas id="myCanvas" width="800" height="400"
				style="background:url();background-size:100% 100%">
		</div>
		<s:form  action="student_addFace"  method="post">
			<s:textfield name="index" id="index" cssStyle="visibility:hidden"></s:textfield>
			<s:div cssClass="check_divsubmitall">
				<input class="check_submit" type="button" value="取消" onclick="cancleFunction()"/>
				<s:submit value="确定" cssClass="check_submit" onclick="submitFunction()"/>
			</s:div>
		</s:form>

	</div>
</body>
</html>