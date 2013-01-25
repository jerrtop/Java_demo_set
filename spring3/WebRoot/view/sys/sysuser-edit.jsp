<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
  </head>
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<form id="userForm" method="post" style="margin: 10;text-align: left;">
		<input type="hidden" name="id" id="uuid">
		账号：<input name="suUsername" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true"> <br>
		姓名：<input name="suNameCn" validType="length[3,30]" style="width:200px;" class="easyui-validatebox" required="true"></input><br>
		密码：<input name="suPassword" style="width: 200" type="password"  validType="length[6,30]" class="easyui-validatebox" required="true"> <br>
		激活：<input name="suAccEna" type="checkbox" value="Y"/><br>
		创建员工：<input name="createPerson" type="checkbox" disabled value="Y"><br>
		<br>
		<span style="padding-left:40px;position:absolute;">
			<div class="mybuttondiv" onclick="addOrUpdateUser();"><span class="mybutton_icon mybutton-bookmark"></span>保 存</div>
			<div class="mybuttondiv" onclick="closeWindow('#MyPopWindow');"><span class="mybutton_icon mybutton-arrowreturn-1-s"></span>返 回</div>
		</span>
	</form>
  </body>
	<script type="text/javascript">
		pageInit();
	</script>
</html>
