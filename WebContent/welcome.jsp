<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/wown.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery/jquery-2.1.4.min.js"></script>
<title>欢迎页</title>
<!-- <meta http-equiv="refresh" content="5; url=login_check" /> -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#test").click(function() {
			window.location.href="login_index.jsp";
		});
		
	});
</script>

</head>
<body>
	<div class="welcome_center">
		<img src="images/welcome_logo.png" width="748" height="400" id="test2">
	</div>
	</br>
	<div class="welcome_center">
		<img id="test" src="images/welcome_go.gif" width="392" height="59">
	</div>


</body>

</html>