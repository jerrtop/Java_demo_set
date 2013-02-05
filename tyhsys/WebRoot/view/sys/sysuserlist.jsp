<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
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
				您现在所在的位置：系统管理-用户管理
			</div>
			<div class="searchcond">
				<table class="querytable">
				  <tr>
					<td>用户名:</td>
					<td><input type="text"  class="textstyle" name="suUserName"/></td>
					<td>姓名:</td>
					<td><input type="text" class="textstyle" name="suNameCn"/></td>
					<td><div class="mybuttondiv" onclick="search()"><span class="mybutton_icon mybutton-icon-search"></span>查 询</div></td>
				  </tr>
				</table>
			</div>
			<div class="listcond">
				<table id="userTable"></table>
			</div>
		</div>

		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false"></div>
	</body>
</html>
