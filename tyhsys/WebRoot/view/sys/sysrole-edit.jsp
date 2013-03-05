<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<div class="tc_sysbox">
		<div id="MovieInfo1" class="serchcondition">
		   <form id="dataForm" method="post"><!-- form 只做取值使用，不做提交-->
			<input type="hidden" name="id" id="uuid">
			<table name="roleInfo" cellspacing="0" cellpadding="0"  class="tablestyle tableborder">
		       	<colgroup>
		       		<col class="wb15 talr bgtd" />
		       		<col class="wb35" />
		       		<col class="wb15 talr bgtd" />
		       		<col class="wb35" />
		       	</colgroup>
		       	<tr>
					<td>
						<span class="star">*</span>编码:
					</td>
					<td valign="middle">
							<input type="text" class="textstyle wb80 easyui-validatebox" name="srCode" validType="length[2,30]" required="true" onblur="checkUnique(this)"/>
					</td>
					<td><span class="star">*</span>角色名称:</td>
					<td>
							<input type="text" class="textstyle wb80 easyui-validatebox" name="srName" validType="length[1,30]" required="true"/>
					</td>
				</tr>
				<tr>
					<td>
						菜单权限:
					</td>
					<td colspan="3" class="bgf">
						<div style="height:340px;overflow: auto;margin-left:10px;margin-top:5px">
							<span style="color:#666;padding-left:3px;">全部菜单</span>
							<ul id="menu">
							</ul>
						</div>
					</td>
				</tr>
		</table>
		</form>
		</div>
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

	

	<script type="text/javascript">
		pageInit();// 页面加载完毕执行，body onLoad也不行，必须这么调用
	</script>
  </body>
</html>
