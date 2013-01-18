<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理系统登录</title>
  </head>
  
  <body bgcolor="#1078b5">
    <table width="100%" height="100%" border="0" cellSpacing="0" cellPadding="0">
    	<tr>
    		<td align="center">
	    		 <form action="welcome" method="post">
			    	<span class="td_title_content" style="font-size:17px">账  号：</span><input name="suUsername" />
			    	&nbsp;&nbsp;&nbsp;&nbsp;
			    	<span class="td_title_content" style="font-size:17px">密  码：</span><input type="password" name="suPassword" />
			    	<input type="submit" style="font-size:13px;" value="登 录" />
			    </form>
    		</td>
    	</tr>
    </table>
   
  </body>
</html>
