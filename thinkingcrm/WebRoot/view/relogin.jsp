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
		<script src="js/main.js" type="text/javascript"></script>
		<script type="text/javascript">
			if(location.href != window.parent.location.href)
				window.parent.location.href = location.href;
			
			function login(){
				var userName = document.getElementById("userName");
				var password = document.getElementById("password");
				
				var message = document.getElementById("message");
				if(userName.value == ''){
					message.innerHTML = "请输入用户名!";
					return false;
				}
				if(password.value == ''){
					message.innerHTML = "请输入密码!";
					return false;
				}
				var loginForm = document.getElementById("loginForm");
				loginForm.action="welcome";
				loginForm.submit();
			}
			
			// 回车绑定登录事件
			document.onkeydown = function(e){
				if(!e)
					e = window.event;
				if((e.keyCode || e.which) == 13)
					login();
			};
		</script>		
	</head>
	<body class="login">

		<div class="wrap">
			<div class="clearfix"></div>
			<div class="login-content">
				<h4 style="color:white">Thinking CRM-用户登录</h4>
				<div class="login-box">
					<div class="">
						<form class="form-horizontal" id="loginForm" method="post">
						  <div class="control-group">
						    <label class="control-label" for="userName">用户名</label>
						    <div class="controls">
						      <input type="text" id="userName" maxLength=20 name="suUsername" placeholder="请输入用户名">
						    </div>
						  </div>
						  <div class="control-group">
						    <label class="control-label" for="password">密码</label>
						    <div class="controls">
						      <input type="password" id="password" name="suPassword" placeholder="请输入密码">
						    </div>
						  </div>
						  <div class="control-group">
						    <div class="controls">
						      <label class="checkbox">
						        <input type="checkbox"> 记住我
						      </label>
						      <button type="button" class="btn" onclick="login()">登录</button>
						    </div>
						    <p class="text-error controls" id="message">
						    	${message }
						    </p>
						  </div>
						</form>
					</div>	
				</div>	
			</div>
		</div>
	</body>
</html>
