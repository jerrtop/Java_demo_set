<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
  	<script type="text/javascript">
		jQuery(function($){
			//下拉表格初始化
		});
		
		function addOrUpdateUser(){
			var r = $('#userForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("user/addOrUpdate",$("#userForm").serializeArray(),function(data){
				$('#MyPopWindow').window('close');
				$('#userTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
		
	</script>
  </head>
  
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<form id="userForm" method="post" style="margin: 10;text-align: left;">
		<input type="hidden" name="id" id="uuid">
		账号：<input name="suUsername" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true"> <br>
		姓名：<input name="suNameCn" validType="length[3,30]" style="width:200px;"></input><br>
		密码：<input name="suPassword" style="width: 200" type="password"  validType="length[6,30]" class="easyui-validatebox" required="true"> <br>
		激活：<input name="suAccEna" type="checkbox" value="Y"/><br>
		创建员工：<input name="createPerson" type="checkbox" disabled value="Y"><br>
		<br>
		<span style="padding-left:55px;">
			<a href="#" id="btn-add" onclick="addOrUpdateUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
		</span>
	</form>
  </body>
</html>
