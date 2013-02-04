<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>企业信息化开发平台</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="indexbg" >
	<div class="wrap w1000 mauto" style="height:75%">
    	<div class="content">
	    	 <table width="100%" height="90%" border="0" cellSpacing="0" cellPadding="0">
		    	<tr>
		    		<td align="center" valign="middle" style="padding-left:100px;border:0px;">
			    		 <form action="welcome" method="post">
					    	<span class="td_title_content" style="font-size:17px">账  号 </span><input name="suUsername" />
					    	&nbsp;&nbsp;&nbsp;&nbsp;
					    	<span class="td_title_content" style="font-size:17px">密  码 </span><input type="password" name="suPassword" />
					    	<input type="submit" style="font-size:13px;" value="登 录" />
					    </form>
		    		</td>
		    	</tr>
		    </table>
	        <div class="footer">
	            <span>
	                Copyright @ Jerry  2013
	            </span>
	        </div>
		 </div>
    </div>
</div>
</body>
</html>
