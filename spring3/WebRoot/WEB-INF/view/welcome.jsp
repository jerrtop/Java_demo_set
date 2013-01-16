<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>spring3</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			
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
	</script>	
  </head>
  <!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
  <body class="easyui-layout" id="mainBody">
		<!-- 上 -->
		<div region="north" split="false" style="height:100px;text-align: center;" border="false">
			<h1>欢迎： ${userSessionInfo.name}</h1>
		</div>
		
		<!-- 左-->
		<div region="west" class="menudiv" split="true" title="系统菜单" style="width:200px;overflow:hidden;">
			<div id="menuDiv" class="easyui-accordion" fit="false" border="false" animate="false">
				<div title="用户管理" style="overflow:hidden;">
					<ul>					
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('用户管理','${ctx}/user/list')" >用户管理</li>
					</ul>	
					<ul>					
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('用户管理','${ctx}/user/list')" >用户管理</li>
					</ul>	
					<ul>					
						<li id="rightLi${child.id}" style="cursor: pointer;" 
							onclick="setmain('用户管理','${ctx}/user/list')" >用户管理</li>
					</ul>						
				</div>
				<div title="部门管理" style="overflow:hidden;">
					<ul>					
						<li id="rightLi${child.id}">部门管理</li>
						<li id="rightLi${child.id}">部门管理</li>	
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
		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;"></div>
  </body>
</html>
