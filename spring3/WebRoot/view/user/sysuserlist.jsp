<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<html>
	<head>
		<script type="text/javascript" src="${ctx }/js/sys/sysuserlist.js"></script>
		<script type="text/javascript" src="${ctx }/js/sys/sysuser-edit.js"></script>
	</head>

	<body>
		<div class="sysbox">
			<div class="syshead">
				<span class="pagename">用户信息</span>
			</div>
			<div class="serchcondition">
				<form id="queryForm" style="margin:10;">
					<table width="98%" cellspacing="0" cellpadding="0" class="queryTable">
							<col width="30%"/>
			             	<col width="30%"/>
			             	<col width="40%"/>
						<tr>
							<td>
								账号：
								<input name="suUsername" style="width: 200;">
							</td>
							<td>
								姓名：
								<input name="suNameCn" style="width:200px;"></input>
							</td>
							<td align="left">
								<div class="mybuttondiv"  onclick="searchUser();"><span class="mybutton_icon mybutton-icon-search"></span>查 询</div>
								<!-- <a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a>  -->
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="padding:5" id="tabdiv">
				<table id="userTable"></table>
			</div>
		</div>
		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false"></div>
	</body>
</html>
