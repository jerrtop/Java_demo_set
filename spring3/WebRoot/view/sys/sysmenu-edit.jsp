<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
  </head>
  
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
<form id="menuForm" method="post" style="margin: 10;text-align: left;">
	<input type="hidden" name="id" id="uuid">
	<div class="tc_sysbox">
		<div id="iteminformationDiv">
	        <div class="menudiv" style="width:98%"> 
				<span class="my_movie_on" id="MovieInfoT1" onClick="PanShow('MovieInfo','my_movie',1,2);"> <a href="#">菜单信息</a> </span>
				<span id="MovieInfoT2" onClick="PanShow('MovieInfo','my_movie',2,2);"> <a href="#">功能操作</a> </span>
		  	</div>

			<div id="MovieInfo1" class="serchcondition" style="width:98%">
		       	<table cellspacing="0" cellpadding="0" class="prompt_table tablestyle tableborder">
			       	<colgroup>
			       		<col class="wb15 talr bgtd" />
			       		<col class="wb35" />
			       		<col class="wb15 talr bgtd" />
			       		<col class="wb40" />
			       	</colgroup>
			       	<tr>
						<td>
							<span class="star">*</span>编码:
						</td>
						<td valign="middle">
							<div>
								<input type="text" class="textstyle wb80 easyui-validatebox" name="smCode" validType="length[3,30]" required="true"/>
							</div>
						</td>
						<td><span class="star">*</span>菜单名称:</td>
						<td>
							<div>
								<input type="text" class="textstyle wb80 easyui-validatebox" name="smName" validType="length[3,30]" required="true"/>
							</div>
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
						<td colspan="3">
							<div>
								<input type="text" name="smParent" class="textstyle  wb92 easyui-validatebox" validType="length[0,30]"  />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							是否可用:
						</td>
						<td>
							<div>
								<input type="checkbox" name="smIsUsed" class="chkstyle" value="Y"/>
							</div>
						</td>
						<td>
							是否顶级:
						</td>
						<td>
							<div>
								<input type="checkbox" name="smIsTop" class="chkstyle" value="Y"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="star">*</span>链接页面:
						</td>
						<td colspan="3">
							<div>
								<input type="text" class="textstyle  wb92 easyui-validatebox" name="smPage"  validType="length[1,100]" required="true"/>
							</div>
						</td>
						
					</tr>
					<!-- 
					<tr>
						<td>
							<span class="star">*</span>包含页面:
						</td>
						<td colspan="3">
							<div>
								<textarea class="areastyle  wb92 h60" name="smContainPage"/>
							</div>
						</td>
					</tr>
					 -->
					<tr>
						<td>
							菜单功能:
						</td>
						<td colspan="3">
							<div>
								<textarea class="areastyle  wb92 h60 easyui-validatebox" name="smDescription" validType="length[0,300]" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="cdzy" id="MovieInfo2"  style="width:98%">
	  			<table cellspacing="0" cellpadding="0" class="prompt_table tablestyle tableborder">
				  	<tr>
						<td>
							<div class="mybuttondiv" onClick="createUnit()"><span class="mybutton_icon mybutton-plusthick"></span>新建功能</div>
						</td>
					</tr>
				  	<tr>
						<td id="units" class="vat">
						&nbsp;
						</td>
					</tr>
				</table>
			</div>
			
			<div style="padding-left:160px;position:absolute;padding-top:5px;">
             	<div class="mybuttondiv" onclick="addOrUpdateMenu();"><span class="mybutton_icon mybutton-bookmark"></span>保 存</div>
				<div class="mybuttondiv" onclick="closeWindow('#MyPopWindow');"><span class="mybutton_icon mybutton-arrowreturn-1-s"></span>返 回</div>
             </div>
		</div>
	</div>
</form>
  </body>
	<script type="text/javascript">
		pageInit();
	</script>
</html>
