<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业信息化开发平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body  style="background-color: #F3F9FF">
		<div class="head">
			<div class="headtop">
				<div class="logo">
					<a href="main.html"><!-- <img src="${ctx}/img/sys/index_logo.png" />  -->
						Spring3 MVC + Jquery EasyUI 1.2 开发平台
					</a>
					<span id="wi" style="padding-top:5px;padding-left:60px;">admin，欢迎使用xxx系统</span>
					<div id="ts"
						style="color:#ff0000;background:#feffdd;border:1px solid #e6ea83;text-align:center;width:450px;height:25px;line-height:25px; position:absolute;top:25px;left:400px;display:none;">
						您的浏览器版本太低了，我们建议您使用IE7以上版本的浏览器
					</div>
					<div style="clear:both;"></div>
				</div>
				<div class="hrightbox">
					<span class="tab1"> <a id="mfpw" onclick=""
						style="margin-right:10px;cursor:pointer;text-decoration:underline;">[修改密码]</a>
						<a onclick="javascript:location.href='${ctx }/user/loginOut';"
						style="cursor:pointer;text-decoration:underline;">[注销退出]</a> </span>
				</div>
				<div style="clear:both;"></div>
			</div>
			<div class="navbox">
				<ul class="navmain">
					<li class="selectedli">
						<a href="view/welcome.jsp" id="mainhtml" target="frame_content"
							onclick="setActiveMenu($(this).parent());">主页</a>
					</li>
					<li class="appendMenuItem">
						<span class="spanbg"><a
							href="${ctx}/user/list" target="frame_content"
							onclick="setActiveMenu($(this).parent().parent());">系统管理</a>
						</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="content">
			<div class="con_left" id="con_left" style="left:-30px;">
				<span class="shouqi" id="shouqi"><img src="${ctx }/img/sys/switch_left.gif" /></span>
			</div>	
				<div id="sidebar">
					<ul>
					 <li><h3><a href="#" class="manage">系统管理</a></h3>
							<ul style="margin-top: 8px;line-height: 14px;">
								<li><a href="user/list" target="frame_content" class="useradd">用户管理</a></li>
								<li><a href="#" class="report_seo">权限管理</a></li>
								<li><a href="#" class="group">角色管理</a></li>
								<li><a href="#" class="report">菜单管理</a></li>
							</ul>
						</li>
						<li><h3><a href="#" class="manage">系统管理</a></h3>
							<ul style="margin-top: 8px;line-height: 13px;">
								<li><a href="user/list" target="frame_content">用户管理</a></li>
								<li><a href="#">权限管理</a></li>
								<li><a href="#">角色管理</a></li>
								<li><a href="#">菜单管理</a></li>
							</ul>
						</li>
						<li><h3><a href="#" class="manage">系统管理</a></h3>
							<ul style="margin-top: 8px;line-height: 13px;">
								<li><a href="user/list" target="frame_content">用户管理</a></li>
								<li><a href="#">权限管理</a></li>
								<li><a href="#">角色管理</a></li>
								<li><a href="#">菜单管理</a></li>
							</ul>
						</li>
					</ul>       
			  	</div>
			
			<div class="con_right" id="con_right">
				<div id="content" style="height:490px;">
					<iframe name="frame_content" id="frame_content" height="100%" width="100%" border="0" frameborder="0" src="view/welcome.jsp">
						浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
					</iframe>
				</div>
			</div>
			
		</div>
		<!-- 
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
				window.setInterval("reinitIframe()", 200);	
				
		</script>
	 -->
	</body>
</html>
