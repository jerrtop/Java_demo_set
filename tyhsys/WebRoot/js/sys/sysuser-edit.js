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

//检查账号唯一
function checkUnique(element){
	var chkVal = $(element).val();
	if($.trim(chkVal) == '')
		return false;
	
	var url = "checkUnique?checkProperty=SU_USERNAME&checkValue=" + $.trim(chkVal);
	$.get(encodeURI(url),function(data){
		if(data.mes == 1){
			$.messager.alert('提示',"账号存在，请重新输入.",'info');
			$("input[name='suUsername']").val('');
		}
	});
}