<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
  </head>
  
  <body>
  	<!-- 验证还是jquery-validation好用，这里省事没用 -->
<form id="userForm" method="post" style="margin: 10;text-align: left;">
	<input type="hidden" name="id" id="uuid">
	<div class="tc_sysbox">
		<div id="iteminformationDiv">
	        <div class="menudiv"> 
				<span class="my_movie_on" id="MovieInfoT1" onClick="PanShow('MovieInfo','my_movie',1,3);"> <a href="#">菜单信息</a> </span>
				<span id="MovieInfoT2" onClick="PanShow('MovieInfo','my_movie',2,3);"> <a href="#">功能操作</a> </span>
				<!--<span id="MovieInfoT4" onClick="PanShow('MovieInfo','my_movie',4,4);"> <a href="#">视频</a> </span>-->
		  	</div>

			<div id="MovieInfo1" class="serchcondition">
		       	<table cellspacing="0" cellpadding="0" class="prompt_table tablestyle tableborder">
			       	<colgroup>
			       		<col class="wb15 talr bgtd" />
			       		<col class="wb30" />
			       		<col class="wb15 talr bgtd" />
			       		<col class="wb30" />
			       	</colgroup>
			       	<tr>
						<td>
							<span class="star">*</span>编码:
						</td>
						<td valign="middle">
							<div>
								<input type="text" class="textstyle wb80" name="smCode" maxlength="13" />
							</div>
						</td>
						<td>菜单名称:</td>
						<td>
							<div>
								<input type="text" class="textstyle wb80" name="smName"/>
							</div>
						</td>
					</tr>
			       	<tr>
			       		<td><span class="star">*</span>标题:</td>
						<td colspan="3">
							<div>
								<input type="text" name="smTitle" class="textstyle wb93" maxlength="100" />
							</div>
						</td>
			       	</tr>
					<tr>
						<td>
							<span class="star">*</span>是否可用:
						</td>
						<td>
							<div>
								<input type="checkbox" name="smIsUsed" class="textstyle wb70" />
							</div>
						</td>
						<td>
							<span class="star">*</span>是否顶级:
						</td>
						<td>
							<div>
								<input type="checkbox" class="textstyle wb80"  name="smIsTop"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="star">*</span>父菜单:
						</td>
						<td>
							<div>
								<input type="text" name="smParent" class="textstyle wb70" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="star">*</span>链接页面:
						</td>
						<td>
							<div>
								<input type="text" class="textstyle wb80" name="smPage"/>
							</div>
						</td>
						
					</tr>
					<tr>
						<td>
							<span class="star">*</span>包含页面:
						</td>
						<td>
							<div>
								<textarea class="areastyle wb93 h60" name="smContainPage"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="star">*</span>菜单功能:
						</td>
						<td>
							<div>
								<textarea class="areastyle wb93 h60" name="smDescription"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="cdzy" id="MovieInfo2">
	  			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				  	<tr>
						<td>
							<div class="mybuttondiv"  onClick="createUnit()"><span class="mybutton_icon mybutton-plusthick"></span>新建功能</div>
						</td>
					</tr>
				  	<tr>
						<td id="units" class="vat">
						&nbsp;
						</td>
					</tr>
				</table>
			</div>
			
			<div>
				<table cellspacing="0" cellpadding="0" class="tablestyle tableborder">
				<tr>
					<td>
						<div class="juzhong w120">
	               			<div class="mybuttondiv" onclick="addOrUpdateUser();"><span class="mybutton_icon mybutton-bookmark"></span>保 存</div>
							<div class="mybuttondiv" onclick="closeWindow('#MyPopWindow');"><span class="mybutton_icon mybutton-arrowreturn-1-s"></span>返 回</div>
	               		</div>
					</td>
				</tr>
				</table>
			</div>
		</div>
	</div>
</form>
  </body>
	<script type="text/javascript">
		pageInit();
	</script>
</html>
