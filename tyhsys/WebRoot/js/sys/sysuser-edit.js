//表单初始化
function pageLoader(){

	controlInit();
}

//控件初始化
function controlInit(){
	$('input[name="person.piJoinDate"]').datebox({
		formatter:function(str){
			var d = new Date(str);
			return d.format('yyyy-MM-dd');
		}
	});
	$('input[name="person.piBirthDate"]').datebox({
		formatter:function(str){
			var d = new Date(str);
			return d.format('yyyy-MM-dd');
		}
	});
	
	$('#org').combotree({
		url:'queryOrgs',
		onBeforeCollapse:false
	});
	
	var supUrl = 'querySups';
	if(editUserId != ''){
		supUrl += '?userId=' + editUserId;
	}
	$('#sup').combobox({
		method:'post',
		url:supUrl,  
	    valueField:'id',  
	    textField:'text'
	});
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