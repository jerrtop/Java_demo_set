<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/sys/main.js"></script>
</head>

<body>
<div id="header">
  <div id="headtop">
  	<div id="logo"><a><img src="images/logo.png"/></a></div>
    <div id="toprightbox">
		<span class="userInfo">admin，欢迎您!</span>
		<span class="sysbtn"><a href="#">[修改密码]</a><a href="#">[注销退出]</a></span>
	</div>
	<div style="clear:both;"></div>
  </div>
  <div id="nav">
  	<ul id="navmenu">
  		<li class="selectedli"><a href="#" target="frame_content" onclick="setActiveMenu($(this).parent());"><span>主页</span></a></li>
		<li><a href="user/list" target="frame_content" onclick="setActiveMenu($(this).parent());"><span class="menuunselected">系统管理</span></a></li>
		<div style="clear:both;"></div>
  	</ul>
	<div style="clear:both;"></div>
  </div>
</div>
<div id="content">
	<div id="content_left">
		<div id="switchbar">
		  <img class="switch_show" title="打开/关闭左侧栏" ></img>  
		</div>
		<div id="menu">
			<div class="menuitem">
				<span class="fthmenu">系统管理</span>
				<ul>
					<li>
						<a href="user/list" target="frame_content">用户管理</a>
					</li>
					<li>
						<a href="user/list" target="frame_content">用户管理</a>
					</li>
					<li>
						<a href="user/list" target="frame_content">用户管理</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
  	<div id="workplace">
		<iframe name="frame_content" id="frame_content" width="100%" height="500" src="user/list" frameborder="0"></iframe>
		<script type="text/javascript">	
			function reinitIframe(){
				$('#frame_content').load(function(){
					$('#frame_content').height(100);
				});
				var iframe = document.getElementById("frame_content");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					$('#frame_content').height(height);
				}catch (ex){}
				}
				//window.setInterval("reinitIframe()", 200);	
				
		</script>
  </div>
</div>
</body>
</html>