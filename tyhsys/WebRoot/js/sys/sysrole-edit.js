//表单值初始化
function pageInit(){
	$('#tt').tree();
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

	//menu
	var menuJSON = $("#dataForm").serializeArray();
	menuJSON = JSON.stringify(menuJSON);//讲json对象转为string
	menuJSON = menuJSON.substring(1,menuJSON.length - 1);
	//opers
	var opersJSON = '';
	var opersRow = $('#opersForm table[name="opersInfo"] tr');
	$.each(opersRow,function(i){
		if(i == 0) return;//表头
		opersJSON +='{"name":"smOpers.smoName","value":"' + $(this).find("input[name='smOpers.smoName']").val()+'"},';
		opersJSON +='{"name":"smOpers.smoOperation","value":"' + $(this).find("input[name='smOpers.smoOperation']").val()+'"},';
		opersJSON +='{"name":"smOpers.smoValid","value":"' + ($(this).find("input[name='smOpers.smoValid']").attr("checked") == true ? $(this).find("input[name='smOpers.smoValid']").attr("value") : '') +'"},';
	});
	if(opersJSON.length > 0)
		opersJSON = opersJSON.substring(0,opersJSON.length - 1);
	
	var postJSON = '';
	if(opersJSON != '')
		postJSON = '[' + menuJSON + ',' + opersJSON + ']';
	else
		postJSON = '[' + menuJSON + ']';
	
	postJSON = JSON.parse(postJSON);
	
	
//	$.post("addOrUpdateMenu",$("#dataForm").serializeArray(),function(data){
//		$('#MyPopWindow').window('close');
//		$('#listTable').datagrid('reload');  
//		$.messager.alert('提示',data.mes,'info');
//	});
	
	$.post("addOrUpdateMenu",postJSON,function(data){
		$('#MyPopWindow').window('close');
		$('#listTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
	
}

function addOper(){
	var shtml = '';
	shtml += '<tr>';
	shtml += '<td><input type="text" class="textstyle wb80" name="smOpers.smoName" value=""/></td>';
	shtml += '<td><input type="text" class="textstyle wb80" name="smOpers.smoOperation" value="" /></td>';
	shtml += '<td><input type="checkbox" class="chkstyle" name="smOpers.smoValid" value="Y" checked="checked"/></td>';
	shtml += '<td><a href="javascript:void(0)" onclick="deleteOper(this)">删除</a></td>';
	shtml += '</tr>';
	$("table[name='opersInfo']").append(shtml);
}

function deleteOper(o){
	$(o).parent().parent().remove();
}
