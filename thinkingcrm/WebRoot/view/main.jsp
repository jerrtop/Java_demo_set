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
		
		<!-- bootstrap datatables -->
		<link href="css/bootstrap-datatables/dataTables.bootstrap.css" rel="stylesheet" media="screen">
		<script src="js/bootstrap-datatables/jquery.dataTables.js" type="text/javascript"></script>
		<script src="js/bootstrap-datatables/dataTables.bootstrap.js" type="text/javascript"></script>
		
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
			
			 function loginOut(){
				 if(confirm("确定要退出吗？")){
					 location.href = "user/loginOut";
				 }
			 }
			 
			 function loadcontent(url){
				 $('#workspace').load(url);
			 }
			 
			
			//显示菜单
			 var global_role_menus = ${userSessionRoleMenus};
			 function displayRoleMenus(){
			 	if(global_role_menus.length == 0){
			 		alert("该用户无菜单权限!");
			 		return;
			 	}
			 	var shtml = '';
			 	$.each(global_role_menus,function(index,menu){
			 		shtml += '<li><a href="#" onclick="displayModelMenus('+ index +')">'+ menu.menuname +'</a></li>';
			 	});
			 	$('#top_menus').html(shtml);
			 	$('#top_menus li:first').addClass("active");
			 	displayModelMenus(0);
			 }

			 //显示模块菜单
			 function displayModelMenus(menuIndex){
			 	var menu = global_role_menus[menuIndex];
			 	var shtml = '';
			 	if(menu.children){//判断二级菜单是否存在
			 		$.each(menu.children,function(index,submenu){
			 			shtml += '</ul>';
			 			shtml += '</div>';
			 			
			 			shtml += '<li>';
		 				shtml += '<h4><i class="icon-user"></i>'+ submenu.menuname +'</h4>';
		 				shtml += '<ul class="nav nav-list sub-nav">';
		 				if(submenu.children){//判断是否有三级菜单
			 				$.each(submenu.children,function(k,ss){
		 						shtml += '<li><a href="#" onclick="loadcontent(\''+ ss.menuurl +'\')">'+ ss.menuname +'</a></li>';
			 				});
		 				}
		 				shtml += '</ul>';					
		 				shtml += '</li>';
			 		});
			 	}
			 	$('#model_menus').html(shtml);
			 }
			 
			 $(function(){
					window.setInterval(setClock, 1000);
					displayRoleMenus();
					loadcontent('content');
					
					if($('.sidebar').height() > $('#workspace').height()){
						$('#workspace').height($('.sidebar').height());
					}else{
						$('.sidebar').height($('#workspace').height());
					}
					
					
				}); 
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
							<li id="clock">正在加载...</li>
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
							<ul class="nav" id="top_menus">
								<li>
									<a href="#">正在加载...</a>
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
						<ul class="nav" id="model_menus">
							<li>
								<h4>正在加载...</h4>					
							</li>
						</ul>
					</div>
				  </div>
				  <div id="workspace" class="content"></div>
				</div>
			</div>
</body>
</html>
