<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<form id="dataForm" method="post" style="margin: 10;text-align: left;">
		<input type="hidden" name="id" id="uuid">
  		<div class="tc_sysbox">
  			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				<colgroup>
					<col class="wb25 talr bgtd"></col>
					<col></col>
				</colgroup>
				<tr>
					<td><span class="star">*</span>账号:</td>
					<td><input name="suUsername" validType="length[3,30]" class="textstyle wb80 easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td><span class="star">*</span>姓名:</td>
					<td><input name="suNameCn" validType="length[3,30]" class="textstyle wb80 easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td><span class="star">*</span>密码:</td>
					<td><input type="password" name="suPassword" validType="length[6,30]" class="textstyle wb80 easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td>激活:</td>
					<td><input name="suAccEna" type="checkbox" value="Y"/></td>
				</tr>
				<tr>
					<td>创建员工:</td>
					<td><input name="createPerson" type="checkbox" disabled = "disabled" value="Y" checked = "checked"></td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" class="tablestyle">
				<tr>
					<td class="bgf">
							<div class="juzhong w150">
								<input class="Btn BtnNml" type="button" value="保 存" onclick="addOrUpdateUser()"/>
								<input class="Btn BtnNml" type="button" value="关 闭" onclick="closeWindow('#MyPopWindow')"/>
							</div>
					</td>
				</tr>
			</table>				
		</div>
	</form>
	
	<!-- 内嵌的页面无法支持src式导入的js，所以页面加载完成调用外部js函数 -->
	<script type="text/javascript">
		pageInit();
	</script>
  </body>
</html>
