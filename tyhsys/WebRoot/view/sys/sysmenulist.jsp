<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="../js/jquery/jquery-easyui-1.2.1/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="../js/jquery/jquery-easyui-1.2.1/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="../css/syscontent.css" />
		<script type="text/javascript" src="../js/jquery/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-easyui-1.2.1/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-easyui-1.2.1/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/sys/sysmenulist.js"></script>
		<script type="text/javascript" src="../js/sys/sysmenu-edit.js"></script>
	</head>

	<body>
		<div class="systembox">
			<div class="listitle">
				您所在的位置：系统管理 - 菜单管理
			</div>
			<div class="listsearch">
			  <form id="queryForm">
				<table class="querytable">
				  <tr>
					<td class="talr">编码:</td>
					<td><input type="text"  class="textstyle" name="smCode"/></td>
					<td class="talr">菜单名称:</td>
					<td><input type="text" class="textstyle" name="smName"/></td>
					<td class="talr">上级菜单编码:</td>
					<td><input type="text" class="textstyle" name="smParent"/></td>
					<td class="talr"><input class="Btn BtnNml toolbtnalign" type="button" value="查 询" onclick="search()"/><input class="Btn BtnNml toolbtnalign" type="button" value="重 置" onclick="clearForm()"/></td>
				  </tr>
				</table>
			  </form>
			</div>
			<div class="toolbtns">
				<input class="Btn BtnNml toolbtnalign" type="button" value="新 增" onclick="addrow()"/><input class="Btn BtnNml toolbtnalign" type="button" value="编 辑" onclick="updaterow()"/><input class="Btn BtnNml toolbtnalign" type="button" value="删 除" onclick="deleterow()"/>
			</div>
			<div class="listcond">
				<table id="listTable"></table>
			</div>
		</div>

		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false"></div>
	</body>
</html>
