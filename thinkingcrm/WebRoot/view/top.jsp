<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Thinking CRM,the crm is thinking</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/style.css" rel="stylesheet">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			var d = '${systemTime}';
			/**
			 * 取得服务器时间的方法
			 */
			d = Number(d);
			d = new Date(d);
			var weekArray = new Array('星期天','星期一','星期二','星期三','星期四','星期五','星期六');
			function setClock(){
				var next = d.getTime()+1000;
				d.setTime(next);
				var ss = d.getSeconds();
				var day = d.getDay();
			    $('#clock').html(
			    d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日 " +
			    weekArray[day] + " " +
			    d.getHours() + ":" + d.getMinutes() + ":" + ((ss<10)?('0'+ss):ss)
			    );
			 }  
			 window.setInterval(setClock, 1000);
			 
			 function loginOut(){
				 if(confirm("确定要退出吗？")){
					 location.href = "user/loginOut";
				 }
			 }
		</script>
	</head>
<body class="top">
	<div class="logo pull-left">
		<small>Thinking CRM</small>
	</div>
	<div class="login-info pull-right">
		<ul class="nav">
			<li id="clock"></li>
			<li>${userSessionInfo.person.piName}</li>
<!-- 			<li>清除缓存</li> -->
<!-- 			<li><a href="http://www.zhaijihui.com" target="_blank">网站首页</a></li> -->
			<li><a href="#" onclick="loginOut()">退出</a></li>
			<li><iframe width="200" scrolling="no" height="70" frameborder="0" allowtransparency="true" src="http://www.tianqi.com/index.php?c=code&id=12&color=%23FFFFFF&icon=3&num=1"></iframe></li>
		</ul>
	</div>	
</body>
</html>
