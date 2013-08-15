<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Thinking CRM,the crm is thinking</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
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
			$(function(){
				window.setInterval(setClock, 1000);
				loadcontent('content');
				
				if($('.sidebar').height() > $('#workspace').height()){
					$('#workspace').height($('.sidebar').height());
				}else{
					$('.sidebar').height($('#workspace').height());
				}
			}); 
			 
			
			 function loginOut(){
				 if(confirm("确定要退出吗？")){
					 location.href = "user/loginOut";
				 }
			 }
			 
			 function loadcontent(url){
				 $('#workspace').load(url);
			 }
		</script>
	</head>
<body>
			<div class="row-fluid">
				<div class="span12 top">
					<div class="logo pull-left">
						<small>Thinking CRM</small>
					</div>
					<div class="login-info pull-right">
						<ul class="nav">
							<li id="clock"></li>
							<li>${userSessionInfo.person.piName}</li>
							<li><a href="#" onclick="loginOut()">退出</a></li>
							<li></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="navbar">
						<div class="navbar-inner">
							<ul class="nav">
								<li class="active">
									<a href="#">系统管理</a>
								</li>
								<li>
									<a href="#">链接</a>
								</li>
								<li>
									<a href="#">链接</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
				  <div class="left">
					<div class="sidebar" id="sidebar">
						<ul class="nav">
							<li>
								<h4><i class="icon-user"></i>用户管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="user_list.html" target="mainFrame">用户列表</a></li>
									<li><a href="admin_list.html" target="mainFrame">管理员列表</a></li>
									<li><a href="add_admin.html" target="mainFrame">添加管理员</a></li>
								</ul>					
							</li>
							<li>
								<h4><i class="icon-tags"></i>店铺管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="shop_list.html" target="mainFrame">店铺列表</a></li>
									<li><a href="add_shop.html" target="mainFrame">添加店铺</a></li>
								</ul>				
							</li>
							<li>
								<h4><i class="icon-tags"></i>店铺管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="shop_list.html" target="mainFrame">店铺列表</a></li>
									<li><a href="add_shop.html" target="mainFrame">添加店铺</a></li>
								</ul>				
							</li>
							<li>
								<h4><i class="icon-tags"></i>店铺管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="shop_list.html" target="mainFrame">店铺列表</a></li>
									<li><a href="add_shop.html" target="mainFrame">添加店铺</a></li>
								</ul>				
							</li>
							<li>
								<h4><i class="icon-tags"></i>店铺管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="shop_list.html" target="mainFrame">店铺列表</a></li>
									<li><a href="add_shop.html" target="mainFrame">添加店铺</a></li>
								</ul>				
							</li>
							<li>
								<h4><i class="icon-tags"></i>店铺管理</h4>
								<ul class="nav nav-list sub-nav">
									<li><a href="shop_list.html" target="mainFrame">店铺列表</a></li>
									<li><a href="add_shop.html" target="mainFrame">添加店铺</a></li>
								</ul>				
							</li>
						</ul>
					</div>
				  </div>
				  <div id="workspace" class="content"></div>
				</div>
			</div>
</body>
</html>
