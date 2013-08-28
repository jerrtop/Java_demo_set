<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<body>
			<div class="warp">
				<ul class="breadcrumb">
				    <li><span id="test">您当前的位置：</span><a href="#">首页</a> <span class="divider">>></span></li>
				    <li><a href="#">用户列表</a> <span class="divider">>></span></li>
				    <li class="active">添加用户</li>
			    </ul>
			</div>
			<div id="compat-width">
				<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<form class="form-search">
							<input class="input-medium search-query" type="text" /> <input class="input-medium search-query" type="text" /> <button type="submit" class="btn">查找</button>
						
						</form>
							<table class="table table-striped table-bordered table-hover datatable"></table>
						</div>
				</div>
			</div>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
						var options = {
							"bFilter" : false,
							"sDom": '<"top">rt<"bottom"lip><"clear">',
	        				"oLanguage": {
	        						"sUrl":"js/bootstrap-datatables/zh_CN.txt"
								},
							"bProcessing": true,
					        "bServerSide": true,
					        "sAjaxSource": "user/queryUserList",
					        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
					            oSettings.jqXHR = $.ajax( {
					              "contentType": "application/json",
					              "dataType": "json",
					              "type": "POST",
					              "url": sSource,
					              "data": JSON.stringify(aoData),
					              "success": fnCallback
					            } );
					          },
					        "aoColumns": [
					                      { "mData": "SU_USERNAME" ,"sTitle":"用户名"},
					                      { "mData": "PI_NAME" ,"sTitle":"姓名"},
					                      { "mData": "PI_MOBILE" ,"sTitle":"手机"},
					                      { "mData": "PI_EMAIL" ,"sTitle":"邮箱"}
					                  ]
											
						};
		    			$('.datatable').dataTable(options);
				} );
			</script>
	</body>
</html>
