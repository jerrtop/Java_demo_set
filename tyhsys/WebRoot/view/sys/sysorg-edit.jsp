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
					<td><span class="star">*</span>编码:</td>
					<td><input name="soCode" validType="length[1,30]" class="textstyle wb80 easyui-validatebox" required="true" onblur="checkUnique(this)"></td>
				</tr>
				<tr>
					<td><span class="star">*</span>名称:</td>
					<td><input name="soName" validType="length[1,30]" class="textstyle wb80 easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td>上级组织:</td>
					<td><input type="hidden" name="soParent" ><input type="text" name="soParentName" readonly="true" class="textreadonly wb80"></td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" class="tablestyle">
				<tr>
					<td class="bgf">
							<div class="juzhong w150">
								<input class="Btn BtnNml" type="button" value="保 存" onclick="addOrUpdate()"/>
								<input class="Btn BtnNml" type="button" value="关 闭" onclick="closeWindow('#MyPopWindow')"/>
							</div>
					</td>
				</tr>
			</table>				
		</div>
	</form>
  </body>
</html>
