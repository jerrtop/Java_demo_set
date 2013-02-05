<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>企业信息化开发平台</title>
		<link href="css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function submitLogin(){
				//TODO 1.验证
				
				//2.提交
				document.getElementById("loginForm").submit();
			}
		
		</script>
	</head>

<body style="background:#fff;">
<div class="login">
  <div class="loginBg">
    <h1 class="loginH1"></h1>
    <div class="loginForm">
    <form id="loginForm" method="post" action="welcome">
          <input type="hidden" name="forceLogin" id="forceLogin" value="N"/>
	      <input type="text" class="txt focus" name="suUsername" value="用户名" title="请输入账号"/>
	      
	      <input type="password" class="txt" name="suPassword" title="请输入密码" onkeydown="if(event.keyCode==13||event.which==13)submitLogin();"/>
	      
	      <input type="button" class="button green"  value="登录" id="submitBtn" onclick="submitLogin()"  style="width: 120px;height: 38px;font-size: 18px"/>
	     
	      <span class="errtips" id="tips" >${message}</span>
      </form>
    </div>
  </div>
</div>
</body>
</html>
