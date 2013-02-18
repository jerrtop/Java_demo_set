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
		<script type="text/javascript" src="../js/index.js"></script>
		<script type="text/javascript" src="../js/sys/sysuserlist.js"></script>
		<script type="text/javascript" src="../js/sys/sysuser-edit.js"></script>
	</head>

	<body>
		<div class="systembox">
			<div class="listitle">
				您所在的位置：系统管理 - 用户管理
			</div>
			<div class="listsearch">
				<table class="querytable">
				  <colgroup>
				  	<col class="wb13"/>
				  	<col class="wb25"/>
				  	<col class="wb13"/>
				  	<col class="wb25"/>
				  	<col class="wb30"/>
				  </colgroup>
				  <tr>
					<td class="talr">用户名:</td>
					<td><input type="text"  class="textstyle" name="suUserName"/></td>
					<td class="talr">姓名:</td>
					<td><input type="text" class="textstyle" name="suNameCn"/></td>
					<td class="talc"><input class="Btn BtnNml" type="button" value="查 询"/></td>
				  </tr>
				</table>
			</div>
			<div class="toolbtns">
				<input class="Btn BtnNml" type="button" value="新 增" /><input class="Btn BtnNml" type="button" value="编 辑"/><input class="Btn BtnNml" type="button" value="删 除"/>
			</div>
			<div class="listcond">
				<table id="userTable"></table>
			</div>
		</div>

		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false"></div>
	</body>
</html>
