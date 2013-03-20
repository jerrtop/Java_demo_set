<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="../js/jquery/jquery-easyui-1.2.1/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="../js/jquery/jquery-easyui-1.2.1/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="../css/syscontent.css" />
		<script type="text/javascript" src="../js/jquery/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-easyui-1.2.1/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-easyui-1.2.1/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
	<script type="text/javascript">
		jQuery(function(){
// 			var options = {
// 				url:"fetchOrgs",
// 				method:"get",
// 				animate:true,
// 				dnd:true //拖拽
// 			};
			var options = {
					url:"fetchOrgs",
					method:"get",
					animate:true
				};
			$('#org').tree(options);
		});
		
		function addrow(){
			showWindow("#MyPopWindow",{
	  			title:'新增',
	  			href:'popWindow',
	  			width:500,
	  			height:180,
	  			onLoad: function(){
	  				$('#dataForm').form('clear');
	  				var selectedNode = $('#org').tree('getSelected');
	  				if(selectedNode != null){
	  					$('#dataForm input[name="soParentName"]').val(selectedNode.text);
	  					$('#dataForm input[name="soParent"]').val(selectedNode.id);
	  				}
	  			}
	  		});
		}
		
		function updaterow(){
			var selectedNode = $('#org').tree('getSelected');
			if(selectedNode == null){
				$.messager.alert('提示',"请选择节点,再进行操作.",'info');
				return;
			}
			
			showWindow("#MyPopWindow",{
	  			title:'编辑',
	  			href:'popWindow',
	  			width:500,
	  			height:180,
	  			onLoad: function(){
	  				$('#dataForm').form('clear');
	  				$('#dataForm input[name="soCode"]').attr("onblur","");
	  				$('#dataForm input[name="soCode"]').attr("disabled","disabled");
	  				
	  				$('#dataForm input[name="id"]').val(selectedNode.id);
	  				$('#dataForm input[name="soCode"]').val(selectedNode.attributes.soCode);
	  				$('#dataForm input[name="soName"]').val(selectedNode.text);
  					$('#dataForm input[name="soParentName"]').val(selectedNode.attributes.parentName);
  					$('#dataForm input[name="soParent"]').val(selectedNode.attributes.parentId);
	  			}
	  		});
		}
		
		function deleterow(){
			var selectedNode = $('#org').tree('getSelected');
			if(selectedNode == null){
				$.messager.alert('提示',"请选择节点,再进行操作.",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(r){  
			    if (r){
					$.post('deleteOrgs?uid=' + selectedNode.id,function(data){
						$.messager.alert('提示',data.mes,'info');
						if(data.mes=='删除成功'){
							$('#org').tree('remove',selectedNode.target);//移除节点及子节点
						}
					});
			    }  
			}); 
			
		}
		
		// 检查唯一
		function checkUnique(element){
			var chkVal = $(element).val();
			if($.trim(chkVal) == '')
				return false;
			
			var url = "checkUnique?checkProperty=SO_CODE&checkValue=" + $.trim(chkVal);
			$.get(encodeURI(url),function(data){
				if(data.mes == 1){
					$.messager.alert('提示',"编码存在，请重新输入.",'info');
					$("input[name='soCode']").val('');
				}
			});
		}
		
		function addOrUpdate(){
			var r = $('#dataForm').form('validate');
			if(!r) {
				return false;
			}
			
			$.post('addOrUpdate',$("#dataForm").serializeArray(),function(data){
				closeWindow('#MyPopWindow');
				$.messager.alert('提示',data.mes,'info');
				
				//新增
				if(data.mes == '操作成功' && $('input[name="id"]').val() == ''){
					var postData = [{
						id:data.id,
						text:$('input[name="soName"]').val(),
						attributes:{
							soCode:$('input[name="soCode"]').val(),
							parentId:$('input[name="soParent"]').val(),
							parentName:$('input[name="soParentName"]').val()
						}
					}];
					rebuildTree(postData);
				}
			});
		}
		
		function rebuildTree(postData){
			var selectedNode = $('#org').tree('getSelected');
			var parentDOM = null;
			if(selectedNode != null){
				//先展开
				if(selectedNode.state == 'closed'){
					$('#org').tree('expand',selectedNode.target);
				}
				parentDOM = selectedNode.target;
			}
			var param = {
					parent:parentDOM,
					data:postData
			};
			$('#org').tree('append',param);
		}
	</script>
	</head>

	<body>
		<div class="systembox">
			<div class="listitle">
				您所在的位置：系统管理 - 组织架构管理
			</div>
			<div class="toolbtns">
				<input class="Btn BtnNml toolbtnalign" type="button" value="新 增" onclick="addrow()"/><input class="Btn BtnNml toolbtnalign" type="button" value="编 辑" onclick="updaterow()"/><input class="Btn BtnNml toolbtnalign" type="button" value="删 除" onclick="deleterow()"/>
			</div>
			<table cellspacing="0" cellpadding="0" class="tablestyle tableborder" >
				<tr>
					<td>
						<div style="float:left;padding-top:5px;height:350px;">
							<ul id="org">
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>

		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false"></div>
	</body>
</html>
