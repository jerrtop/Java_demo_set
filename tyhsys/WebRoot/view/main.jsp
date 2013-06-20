<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="js/jquery/jquery-easyui-1.2.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="js/jquery/jquery-easyui-1.2.1/themes/default/easyui.css" />
<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-easyui-1.2.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-easyui-1.2.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/sys/main.js"></script>
<script type="text/javascript">
	var global_role_menus = ${userSessionRoleMenus};
	// 2013/6/19更新：1) 增加系统时间显示；2)增加修改密码功能
	var d = '${systemTime}';
	/**
	 * 取得服务器时间的方法
	 */
	d = Number(d);
	d = new Date(d);
	function setClock(){
		var next = d.getTime()+1000;
		d.setTime(next);
		var ss = d.getSeconds();
	    $('#clock').html(
	    d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日 " +
	    d.getHours() + ":" + d.getMinutes() + ":" + ((ss<10)?('0'+ss):ss)
	    );
	 }  
	 window.setInterval(setClock, 1000);
	 
	 function togglePWDWindow(){
		 $('#PWDWindow').css({top:220,left:500});
		 $('#PWDWindow').toggle(300);
	 }
	 
	 function checkOldPwd(){
		 $.post('welcome/checkOldPwd?pwd='+$('#oldPWD').val() ,function(data){
			 if(data.mes == 0){
				 $.messager.alert('提示',"原密码输入不正确,请重新输入!",'info');
			 }
		 });
	 }
	 
	 function checkPwdIsSame(){
		 var pwd= $('#newPWD').val();
		 var pwd2= $('#newPWD2').val();
		 if(pwd == '' || pwd2 == '')
			 return false;
		 
		 if(pwd != pwd2){
			 $.messager.alert('提示',"确认密码和新密码不相同,请重新输入.",'info');
			 return false;
		 }
		 return true;
	 }
	 
	 function updatePWD(){
		var r = $('#passwordForm').form('validate');
		if(!r) {
			return false;
		}
		
		if(checkPwdIsSame()){
			$.post('welcome/updatePwd?pwd='+$('#newPWD').val(),function(data){
				if(data.mes == 1){
					$.messager.alert('提示',"密码修改成功.",'info');
					togglePWDWindow();
				}else{
					$.messager.alert('提示',"密码修改失败.",'info');
				}
			});
		}
		
	 }
</script>
</head>

<body>
<div id="header">
  <div id="headtop">
  	<div id="logo"><a><img src="images/logo.png"/></a></div>
    <div id="toprightbox">
		<span class="userInfo">${userSessionInfo.suUsername} ，欢迎您!</span>
		<span id="clock" style="margin-left:65px;"></span>
		<span class="sysbtn"><a href="#" onclick="togglePWDWindow()">[修改密码]</a><a href="user/loginOut">[注销退出]</a></span>
	</div>
	<div id="PWDWindow" style="display:none;position:absolute;border:8px solid #DFE0E0;width:280px;height:125px;background:white;padding:3px 10px;">
		<form id="passwordForm" method="post" action="" >
		<table>
			<tr>
				<td>原密码：</td><td><input type="password" id="oldPWD" validType="length[6,32]" class="easyui-validatebox" required="true" onblur="checkOldPwd()"/></td>
			</tr>
			<tr>
				<td>新密码：</td><td><input type="password" id="newPWD" validType="length[6,32]" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td>确认新密码：</td><td><input type="password" id="newPWD2" validType="length[6,32]" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
						<input type="button" value="保 存" onclick="updatePWD()"/>
							<input type="button" value="关 闭" onclick="togglePWDWindow()"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div style="clear:both;"></div>
  </div>
  <div id="nav">
  	<ul id="navmenu">
		<!--   		
			<li class="selectedli"><a href="#" target="frame_content" onclick="setActiveMenu($(this).parent());"><span>主页</span></a></li>
			<li><a href="user/list" target="frame_content" onclick="setActiveMenu($(this).parent());"><span class="menuunselected">系统管理</span></a></li>
		-->
		<div style="clear:both;"></div>
  	</ul>
	<div style="clear:both;"></div>
  </div>
</div>
<div id="content">
	<div id="content_left">
		<div id="switchbar">
		  <img id="shouqi" class="switch_show" title="打开/关闭左侧栏" ></img>  
		</div>
		<div id="menu">
			<div class="menuitem">
				<!-- 
				<span class="fthmenu">系统管理</span>
				<ul>
					<li>
						<a href="user/list" target="frame_content">用户管理</a>
					</li>
					<li>
						<a href="menu/list" target="frame_content">菜单管理</a>
					</li>
					<li>
						<a href="role/list" target="frame_content">角色管理</a>
					</li>
					<li>
						<a href="#" target="frame_content">权限管理</a>
					</li>
				</ul>
				 -->
			</div>
		</div>
	</div>
  	<div id="workplace">
		<iframe name="frame_content" id="frame_content" width="100%" height="500" src="" frameborder="0"></iframe>
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