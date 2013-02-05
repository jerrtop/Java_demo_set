//表单值初始化
function pageInit(){
	//alert("page init ...");
	//alert($('#userForm').length);
}

function addOrUpdateMenu(){
	var r = $('#menuForm').form('validate');
	if(!r) {
		return false;
	}
	$.post("addOrUpdateMenu",$("#menuForm").serializeArray(),function(data){
		$('#MyPopWindow').window('close');
		$('#menuTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
}