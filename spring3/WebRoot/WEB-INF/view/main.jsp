<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主页</title>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function(){
			loadMenu();
		});
		
		function setmain(title,href){
			$(".combo-panel").parent(".panel").remove(); //清楚所有class为combo-panel的class为panel的父元素，解决combobox在页面缓存的问题
			var centerURL = href;
			var centerTitle = title;
			$('body').layout('panel','center').panel({
				title:"所在位置："+centerTitle,
				href:centerURL
			});
			$('body').layout('panel','center').panel('refresh');
			return false;		
		}
		
		//弹出窗口
		function showWindow(options){
			jQuery("#MyPopWindow").window(options);
		}
		//关闭弹出窗口
		function closeWindow(){
			$("#MyPopWindow").window('close');
		}
		
		//加载菜单
		function loadMenu(){
		
		}
	</script>	
  </head>
  <!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
  <body class="easyui-layout" id="mainBody">
		<br><!-- 上 -->
		<div region="north" split="false" style="height:60px;" border="false">
			<br>
			<span style="font-size:16px;font-weight:bold;margin-left: 5px;">
				用户管理系统标准版
			</span>
			<span style="text-align: right;padding-left: 850px;">欢迎： ${userSessionInfo.suNameCn}</span>
			<span style="padding-left:30px;"><a href="${ctx}/user/loginOut">退出</a></span>
			<div style="padding-left:80px;">
				<span>
					包括用户管理，权限管理，组织结构管理等功能，采用Spring3 Mvc + Jquery easyUI框架开发
				</span>
			</div>
		</div>
		
		<!-- 左-->
		<div region="west" class="menudiv" split="true" title="系统菜单" style="width:200px;overflow:hidden;">
			<div id="menuDiv" class="easyui-accordion" fit="false" border="false" animate="false">
				<div title="系统管理" style="overflow:hidden;">
					<ul>					
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('用户管理','${ctx}/user/list')" >用户管理</li>
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('菜单管理','${ctx}/user/list')" >菜单管理</li>
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('角色管理','${ctx}/user/list')" >角色管理</li>
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('权限管理','${ctx}/user/list')" >权限管理</li>
					</ul>	
				</div>
				<div title="XXX管理" style="overflow:hidden;">
					<ul>					
						<li id="rightLi${child.id}">XXX管理</li>
						<li id="rightLi${child.id}">XXX管理</li>	
					</ul>					
				</div>
				<div title="XXX管理" style="overflow:hidden;">
					<ul>					
						<li id="rightLi${child.id}">XXX管理</li>
						<li id="rightLi${child.id}">XXX管理</li>
						<li id="rightLi${child.id}">XXX管理</li>	
					</ul>					
				</div>
			</div>
		</div>
		
		<!-- 中 -->
		<div region="center" class="maindiv" title="所在位置: 首页" style="overflow-x:hidden;padding: 0px;" href="${ctx}/html/welcome.html" ></div>
		<!-- 下 -->
		<div region="south" split="true" style="height:30px;text-align:center;">
			星火科技 copyright@2013 qq:1487183669
		</div>
		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;"></div>
  </body>

</html>
