//表单值初始化
function pageInit(){
	//alert("page init ...");
	//alert($('#userForm').length);
}

function addOrUpdate(){
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	$.post("addOrUpdateMenu",$("#dataForm").serializeArray(),function(data){
		$('#MyPopWindow').window('close');
		$('#listTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
}