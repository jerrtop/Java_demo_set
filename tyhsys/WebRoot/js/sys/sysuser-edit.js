//表单值初始化
function pageInit(){
	//alert("page init ...");
	//alert($('#userForm').length);
}

function addOrUpdateUser(){
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	$.post("addOrUpdateUser",$("#dataForm").serializeArray(),function(data){
		closeWindow('#MyPopWindow');
		$('#listTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
}