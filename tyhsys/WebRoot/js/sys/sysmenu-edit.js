//表单值初始化
function pageInit(){
	//alert("page init ...");
	//alert($('#userForm').length);
	
	//$("input[name='smCode']").bind("blur",checkUnique());
}

// 检查唯一
function checkUnique(element){
	var chkVal = $(element).val();
	var inputName =  $(element).attr("name");
	if($.trim(chkVal) == '')
		return false;
	
	var json = '';
	// 检查菜单编码
	if(inputName == 'smCode'){
		json += '{';
		json += '"checkProperty":"SM_CODE",';
		json += '"checkValue":"'+ $.trim(chkVal) + '"';
		json += '}';
		checkUniqueBySmCode(json);
	}else if(inputName == 'smParent'){// 检查上级菜单编码
		json += '{';
		json += '"checkProperty":"SM_PARENT",';
		json += '"checkValue":"'+ $.trim(chkVal) + '"';
		json += '}';
		checkUniqueBySmParent(json);
	}
	
}

/**
 * 检查菜单编码唯一性
 * @param json
 */
function checkUniqueBySmCode(json){
	json = $.parseJSON(json);
	var url = "checkUnique?checkProperty=" + json.checkProperty + "&checkValue=" + json.checkValue;
	$.get(encodeURI(url),function(data){
		if(data.mes == 1){
			$.messager.alert('提示',"编码存在，请重新输入.",'info');
			$("input[name='smCode']").val('');
		}
	});
	
}

/**
 * 检查上级菜单编码是否存在
 * @param json
 */
function checkUniqueBySmParent(json){
	json = $.parseJSON(json);
	var url = "checkUnique?checkProperty=" + json.checkProperty + "&checkValue=" + json.checkValue;
	$.get(encodeURI(url),function(data){
		if(data.mes == 0){
			$.messager.alert('提示',"上级菜单编码不存在，请重新输入.",'info');
			$("input[name='smParent']").val('');
		}
	});
	
}

function addOrUpdate(){
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	//alert($("#dataForm").serializeArray())
	//return;
	$.post("addOrUpdateMenu",$("#dataForm").serializeArray(),function(data){
		$('#MyPopWindow').window('close');
		$('#listTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
}