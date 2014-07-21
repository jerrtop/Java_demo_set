<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="form-horizontal" id="data-form" action="">
	<input type="hidden" id="id" name="id" />
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label" for="suUsername"><span class="redS">*</span>账号:</label>
				<div class="controls">
					<input type="text" name="suUsername" id="suUsername">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">激活</label>
				<div class="controls">
					<input type="checkbox" value="Y" name="suAccEna" id="suAccEna" checked="checked">
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label" for="suPassword"><span class="redS">*</span>密码:</label>
				<div class="controls">
					<input type="password" name="suPassword" id="suPassword">
				</div>
			</div>
		</div>
	</div>
	<legend style="font-size:14px;font-weight:700;">员工信息</legend>
	<input type="hidden" name="person.id" id="personId">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label"><span class="redS">*</span>姓名:</label>
				<div class="controls">
					<input type="text" name="person.piName" id="piName">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">部门:</label>
				<div class="controls">
				<input type="text" name="person.piOrg" id="piOrg">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电话:</label>
				<div class="controls">
				<input type="text" name="person.piPhone" id="piPhone">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">QQ:</label>
				<div class="controls">
				<input type="text" name="person.piQq" id="piQq">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">身份证:</label>
				<div class="controls">
				<input type="text" name="person.piIdcard" id="piIdcard">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入职日期:</label>
				<div class="controls">
				<input type="text" name="person.piJoinDate" id="piJoinDate">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学历:</label>
				<div class="controls">
				<input type="text" name="person.piDegree" id="piDegree">
				</div>
			</div>
			
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">员工编号:</label>
				<div class="controls">
				<input type="text" name="person.piCode" id="piCode">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">主管人:</label>
				<div class="controls">
				<input type="text" name="person.piSup" id="piSup">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机:</label>
				<div class="controls">
				<input type="text" name="person.piMobile" id="piMobile">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">邮箱:</label>
				<div class="controls">
				<input type="text" name="person.piEmail" id="piEmail">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">性别:</label>
				<div class="controls">
				<input type="radio" name="person.piSex" value="M" checked>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="person.piSex" value="F">女
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出生日期:</label>
				<div class="controls">
				<input type="text" name="person.piBirthDate" id="piBirthDate">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">员工类型:</label>
				<div class="controls">
				<input type="text" name="person.piType" id="piType">
				</div>
			</div>
		</div>
	</div>
</form>
