<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
	<div class="tc_sysbox">
		<div id="MovieInfo1" class="serchcondition">
		   <form id="dataForm" method="post"><!-- form 只做取值使用，不做提交-->
			<input type="hidden" name="id" id="uuid">
			<table cellspacing="0" cellpadding="0" style="width:98%">
				<tr>
					<td valign="top">
						<table name="roleInfo" cellspacing="0" cellpadding="0"  class="tablestyle tableborder">
					       	<colgroup>
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
							</tr>
							<tr>
								<td><span class="star">*</span>角色名称:</td>
								<td>
										<input type="text" class="textstyle wb80 easyui-validatebox" name="srName" validType="length[1,30]" required="true"/>
								</td>
							</tr>
							<tr>
								<td>
									备注:
								</td>
								<td>
										<textarea class="areastyle wb80 h60 easyui-validatebox" name="srMemo" validType="length[0,300]" />
								</td>
							</tr>
						</table>
					</td>
					<td>
						<div style="border:1px solid #cbdaed;width:220px;height:400px;overflow: auto;margin-left:10px;">
							<span style="color:#666;padding-left:3px;">全部菜单</span>
							<ul id="tt">
							    <li>
							        <span>Folder</span>
							        <ul>
							            <li>
							                <span>Sub Folder 1</span>
							                <ul>
							                    <li>
							                        <span><a href="#">File 11</a></span>
							                    </li>
							                    <li>
							                        <span>File 12</span>
							                    </li>
							                    <li>
							                        <span>File 13</span>
							                    </li>
							                </ul>
							            </li>
							            <li>
							                <span>File 2</span>
							            </li>
							            <li>
							                <span>File 3</span>
							            </li>
							        </ul>
							    </li>
							    <li>
							        <span>File21</span>
							    </li>
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
