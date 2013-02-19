//表单值初始化
function pageInit(){
	//alert("page init ...");
	//alert($('#userForm').length);
}

function addOrUpdateUser(){
	var r = $('#userForm').form('validate');
	if(!r) {
		return false;
	}
	$.post("addOrUpdateUser",$("#userForm").serializeArray(),function(data){
		closeWindow('#MyPopWindow');
		$('#userTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
}