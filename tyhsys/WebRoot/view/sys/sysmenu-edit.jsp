<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
   <form id="dataForm" method="post">
	<input type="hidden" name="id" id="uuid">
	<div class="tc_sysbox">
	        <div class="menudiv"> 
				<span class="my_movie_on" id="MovieInfoT1" onClick="PanShow('MovieInfo','my_movie',1,2);"> <a href="#">菜单信息</a> </span>
				<span id="MovieInfoT2" onClick="PanShow('MovieInfo','my_movie',2,2);"> <a href="#">功能操作</a> </span>
		  	</div>

			<div id="MovieInfo1" class="serchcondition">
		       	<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
			       	<colgroup>
			       		<col class="wb15 talr bgtd" />
			       		<col class="wb35" />
			       	</colgroup>
			       	<tr>
						<td>
							<span class="star">*</span>编码:
						</td>
						<td valign="middle">
								<input type="text" class="textstyle wb80 easyui-validatebox" name="smCode" validType="length[3,30]" required="true"/>
						</td>
					</tr>
					<tr>
						<td><span class="star">*</span>菜单名称:</td>
						<td>
								<input type="text" class="textstyle wb80 easyui-validatebox" name="smName" validType="length[3,30]" required="true"/>
						</td>
					</tr>
					<!-- 
			       	<tr>
			       		<td><span class="star">*</span>标题:</td>
						<td colspan="3">
							<div>
								<input type="text" name="smTitle" class="textstyle wb92" maxlength="100" />
							</div>
						</td>
			       	</tr>
			       	 -->
					<tr>
						<td>
							父菜单:
						</td>
						<td>
								<input type="text" name="smParent" class="textstyle wb80 easyui-validatebox" validType="length[0,30]"  />
						</td>
					</tr>
					<tr>
						<td>
							是否可用:
						</td>
						<td>
								<input type="checkbox" name="smIsUsed" class="chkstyle" value="Y"/>
						</td>
					</tr>
					<tr>
						<td>
							是否顶级:
						</td>
						<td>
								<input type="checkbox" name="smIsTop" class="chkstyle" value="Y"/>
						</td>
					</tr>
					<tr>
						<td>
							<span class="star">*</span>链接页面:
						</td>
						<td>
								<input type="text" class="textstyle wb80 easyui-validatebox" name="smPage"  validType="length[1,100]" required="true"/>
						</td>
						
					</tr>
					<tr>
						<td>
							菜单功能:
						</td>
						<td>
								<textarea class="areastyle wb80 h60 easyui-validatebox" name="smDescription" validType="length[0,300]" />
						</td>
					</tr>
				</table>
			</div>
			
			<div class="cdzy wb98" id="MovieInfo2">
	  			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				  	<tr>
						<td class="bgf">
							<input class="Btn BtnNml toolbtnalign" type="button" value="新建" onclick="addOper()"/>
						</td>
					</tr>
				  	<tr>
						<td id="units" class="vat">
						&nbsp;
						</td>
					</tr>
				</table>
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
	</form>
  </body>
</html>
