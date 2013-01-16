<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>relogin</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    ${message}
    <form action="<%=request.getContextPath()%>/welcome" method="post">
    	姓名：<input name="name"><br/>
    	密码：<input type="password" name="password"><br/>
    	<input type="submit" value="登陆">
    </form>
  </body>
</html>
