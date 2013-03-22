<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<form id="dataForm" method="post" style="margin: 10;text-align: left;">
		<input type="hidden" name="id" id="uuid">
  		<div class="tc_sysbox">
  			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				<colgroup>
					<col class="wb12 talr bgtd"></col>
					<col class="wb35"></col>
					<col class="wb12 talr bgtd"></col>
					<col class="wb35"></col>
				</colgroup>
				<tr>
					<td><span class="star">*</span>账号:</td>
					<td><input name="suUsername" validType="length[1,30]" class="textstyle wb80 easyui-validatebox" required="true" onblur="checkUnique(this)"></td>
					<td><span class="star">*</span>密码:</td>
					<td><input type="password" name="suPassword" validType="length[6,32]" class="textstyle wb80 easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td>激活:</td>
					<td colspan="3" class="bgf"><input name="suAccEna" type="checkbox" value="Y"/></td>
				</tr>
			</table>
			<table  cellspacing="0" cellpadding="0" class="tablestyle tableborder" style="margin-top:3px;">
				<col class="wb12"></col>
				<col class="wb35"></col>
				<col class="wb12"></col>
				<col class="wb35"></col>
				<tr>
					<td colspan="4" class="bgtd"><b>员工信息</b></td>
					<input type="hidden" name="person.id" id="personId">
				</tr>
				<tr>
					<td><span class="star">*</span>姓名:</td>
					<td><input name="person.piName" validType="length[1,30]" class="textstyle wb80 easyui-validatebox" required="true"></td>
					<td>员工编号:</td>
					<td><input name="person.piCode" validType="length[0,20]" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td><span class="star">*</span>部门:</td>
					<td>
						<input id="org" name="person.piOrg" class="textstyle wb80" style="width:180px;">
<!--  						<select id="org" name="person.piOrg" ></select> -->
					</td>
					<td>主管人:</td>
					<td><input id="sup" name="person.piSup" class="textstyle wb80" style="width:180px"></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td>
						<input name="person.piPhone" validType="length[0,30]" class="textstyle wb80 easyui-validatebox">
					</td>
					<td>手机:</td>
					<td><input name="person.piMobile" validType="length[0,11]" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td>QQ:</td>
					<td><input name="person.piQq" validType="length[0,30]" class="textstyle wb80 easyui-validatebox"></td>
					<td>邮箱:</td>
					<td><input name="person.piEmail" validType="length[0,30]" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td>身份证:</td>
					<td><input name="person.piIdcard" validType="length[0,18]" class="textstyle wb80 easyui-validatebox"></td>
					<td>性别:</td>
					<td><input name="person.piSex" type="radio" value="M">男<input name="person.piSex" type="radio" value="F">女</td>
				</tr>
				<tr>
					<td>入职日期:</td>
					<td><input name="person.piJoinDate" class="textstyle wb80 easyui-validatebox"></td>
					<td>出生日期:</td>
					<td><input name="person.piBirthDate" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td>学历:</td>
					<td><input name="person.piDegree" validType="length[0,30]" class="textstyle wb80 easyui-validatebox"></td>
					<td>员工类型:</td>
					<td><input name="person.piType" validType="length[0,30]" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td>籍贯:</td>
					<td colspan="3"><input name="person.piNation" validType="length[0,100]" class="textstyle wb80 easyui-validatebox"></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td colspan="3"><input name="person.piAddress" validType="length[0,100]" class="textstyle wb80 easyui-validatebox"></td>
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
	<script>
		pageLoader();
	</script>
  </body>
</html>
