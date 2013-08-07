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
		<script src="js/main.js"></script>
		<script type="text/javascript">
			$(function(){
				leftScroll();
			})
					
		</script>
	</head>
	<body class="left">
		<div class="wrap">
			<div class="nav-control">
				<span><i class="icon-plus"></i>展开全部</span>
				<span><i class="icon-minus"></i>折叠全部</span>
			</div>
			<div class="sidebar" id="sidebar">
				<ul class="nav">
					<li class="index">
						<h4><i class="icon-home"></i>后台首页</h4>
					</li>
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
						<h4><i class="icon-shopping-cart"></i>订单管理</h4>
						<ul class="nav nav-list sub-nav">
							<li><a href="order_list.html" target="mainFrame">订单列表</a></li>
							<li><a>店铺列表</a></li>
							<li><a>店铺列表</a></li>
							<li><a>店铺列表</a></li>
						</ul>					
					</li>
					<li>
						<h4><i class="icon-cog"></i>内容管理</h4>
						<ul class="nav nav-list sub-nav">
							<li><a href="usermsg_manage.html" target="mainFrame">用户留言</a></li>
							<li><a href="notice_manage.html" target="mainFrame">网站公告</a></li>
							<li><a href="index_slider.html" target="mainFrame">首页轮播</a></li>
							<li><a href="ad_manage.html" target="mainFrame">广告管理</a></li>
						</ul>					
					</li>					
					<li>
						<h4><i class="icon-cog"></i>系统管理</h4>
						<ul class="nav nav-list sub-nav">
							<li><a href="terminal_list.html" target="mainFrame">终端机列表</a></li>
							<li><a href="address_manage.html" target="mainFrame">地址库管理</a></li>
							<li><a>标签管理</a></li>
							<li><a href="baiduMap.html" target="mainFrame">百度地图</a></li>
						</ul>					
					</li>
				</ul>
			</div>
			<div class="scrolllink">
				<span><i class="icon-chevron-up"></i></span>
				<span><i class="icon-chevron-down"></i></span>
			</div>	
		</div>
		
	</body>
</html>
