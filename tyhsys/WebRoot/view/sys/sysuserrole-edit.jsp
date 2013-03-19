<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
	<form id="dataForm" method="post">
		<input type="hidden" name="id" id="uuid">
  		<div class="tc_sysbox">
  			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				<colgroup>
					<col class="wb10 talr bgtd"></col>
					<col class="wb40"></col>
					<col class="wb10 talr bgtd"></col>
					<col class="wb40"></col>
				</colgroup>
				<tr>
					<td>账号:</td>
					<td><input name="suUsername" class="textreadonly wb80" readonly="true"></td><!-- 只读 -->
					<td>姓名:</td>
					<td><input name="suNameCn" class="textreadonly wb80"  readonly="true"></td>
				</tr>
			</table>
			<table  cellspacing="0" cellpadding="0" class="tablestyle tableborder listcond">
				<tr>
					<td class="bgtd">关联角色</td>
				</tr>
				<tr>
					<td id="roles" class="bgf">
						正在加载...
					</td>
				</tr>
			</table>
		</div>
		<table cellspacing="0" cellpadding="0" class="tooldialog">
			<tr>
				<td class="bgf">
						<div class="juzhong w150">
							<input class="Btn BtnNml" type="button" value="保 存" onclick="save()"/>
							<input class="Btn BtnNml" type="button" value="关 闭" onclick="closeWindow('#MyPopWindow')"/>
						</div>
				</td>
			</tr>
		</table>				
	</form>
  </body>
</html>
