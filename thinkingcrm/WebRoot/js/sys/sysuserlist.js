var oTable;
$(document).ready(function() {
		initDialog();
		var options = {
			"bFilter" : false,
			"sDom": '<"top"<"clear">>rt<"bottom"ilp<"clear">>',
			"oLanguage": {
					"sUrl":"js/bootstrap-datatables/zh_CN.txt"
				},
			"bProcessing": true,
	        "bServerSide": true,
	        //"bSort":false,
	        "sAjaxSource": "user/queryUserList",
	        "fnServerData": retrieveData,
	        "aoColumns": [
	                      { "mData":"uid","bSortable": false},
	                      { "mData": "SU_USERNAME" ,"sTitle":"用户名","bSortable": false},
	                      { "mData": "PI_NAME" ,"sTitle":"姓名","bSortable": false},
	                      { "mData": "PI_MOBILE" ,"sTitle":"手机","bSortable": false},
	                      { "mData": "PI_EMAIL" ,"sTitle":"邮箱","bSortable": false}
	                  ],
	        "aoColumnDefs": [
				{ "bVisible": false, "aTargets": [0] }
			] 
							
		};
		oTable=$('.datatable').dataTable(options);
		
} );
//row click event
var selectedRow = {};
function rowAddClickEvents(){
	$(".datatable tbody tr").click( function( e ) {
		selectedRow = oTable.fnGetData( this);
        if ( $(this).hasClass('row_selected') ) {
            $(this).removeClass('row_selected');
        }
        else {
            oTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
}

//验证
function addValidator(){
	$('#data-form').validate({
	    rules: {
	    	'suUsername': {
		        minlength: 2,
		        maxlength: 30,
		        required: true,
		    },
		    'suPassword':{
		    	minlength:6,
		    	maxlength:32,
		    	required:true
		    },
		    'person.piName':{
		    	minlength:2,
		    	maxlength:30,
		    	required:true
		    }
	    },
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element.closest('.control-group').removeClass('error');
			element.remove();
		}
	  });
}

var responseObject = {};
//服务端发现送请求
function retrieveData( sSource, aoData, fnCallback, oSettings ) {
	aoData.push( { "name": "searchUserName", "value": $("#userName").val() } );
	aoData.push( { "name": "searchFamliyName", "value": $("#famliyName").val() } );
    oSettings.jqXHR = $.ajax( {
      "contentType": "application/json",
      "dataType": "json",
      "type": "POST",
      "url": sSource,
      "data": JSON.stringify(aoData),
      "success": function(resp){
    	  responseObject = resp;
    	  fnCallback(resp);
    	  rowAddClickEvents();
      }
    } );
}
//查询
function search(){
	//刷新Datatable，会自动激发retrieveData
	oTable.fnDraw();
}
//init dialog
function initDialog(){
	$('#dialog-form').load("user/sysuser-edit?" + Math.random(),function(){
		addValidator();
	});
	$("#dialog-form").dialog({
		autoOpen: false,
		height: 500,
		width: 720,
		modal: true,
		buttons: {
			"保存": save,
			"关闭": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
		}
	});
}
//弹出dialog
function showModal(oper){
	var v_title = '';
	if(oper == 'add'){
		v_title = '添加用户';
	}else{
		if(fnGetSelected( oTable ).length != 1){
			alert('请选择要修改的行.');
			return ;
		}
		v_title = '修改用户';
		fillForm(selectedRow);
	}
	$('#dialog-form').dialog({title:v_title});
	$('#dialog-form').dialog('open');
}

//编辑时，填充form值
function fillForm(dataRow){
	//账号只读
	$("#data-form input[name='suUsername']").attr("disabled",true);
	$("#data-form input[name='suUsername']").attr("onblur","");
	
	$("#data-form input[name='id']").val(dataRow.uid);
	$("#data-form input[name='suUsername']").val(dataRow.SU_USERNAME);
	$("#data-form input[name='suPassword']").val(dataRow.SU_PASSWORD);
	var suAccEna = dataRow.SU_ACC_ENA;
	if(suAccEna != null && suAccEna != ""){
		$("#data-form input[name='suAccEna'][value='"+ suAccEna +"']").attr("checked",true);
	}
	$("#data-form input[name='person.id']").val(dataRow.PERSONID);
	$("#data-form input[name='person.piName']").val(dataRow.PI_NAME);
	$("#data-form input[name='person.piCode']").val(dataRow.PI_CODE);
	
	//$("#org").combotree('setValue',dataRow.PI_ORG);
	//$('#sup').combobox('setValue',dataRow.PI_SUP);
	
	$("#data-form input[name='person.piPhone']").val(dataRow.PI_PHONE);
	$("#data-form input[name='person.piMobile']").val(dataRow.PI_MOBILE);
	$("#data-form input[name='person.piQq']").val(dataRow.PI_QQ);
	$("#data-form input[name='person.piEmail']").val(dataRow.PI_EMAIL);
	$("#data-form input[name='person.piIdcard']").val(dataRow.PI_IDCARD);
	
	var piSex = dataRow.PI_SEX;
	if(piSex != null && piSex != '')
		$("#data-form input[name='person.piSex'][value='"+ piSex +"']").attr("checked",true);
	
	$("#data-form input[name='person.piJoinDate']").val(dataRow.PI_JOIN_DATE);
	$("#data-form input[name='person.piBirthDate']").val(dataRow.PI_BIRTH_DATE);
	$("#data-form input[name='person.piDegree']").val(dataRow.PI_DEGREE);
	$("#data-form input[name='person.piType']").val(dataRow.PI_TYPE);
	$("#data-form input[name='person.piNation']").val(dataRow.PI_NATION);
	$("#data-form input[name='person.piAddress']").val(dataRow.PI_ADDRESS);
}

//保存
function save(){
	var b = $('#data-form').valid();
	if(!b)
		return;
	
	var toSaveJson = $("#data-form").serializeArray();
	$.post("user/addOrUpdateUser",toSaveJson,function(data){
		if(data.mes == '1'){
			$('#data-form').form('clear');
			$('#dialog-form').dialog('close');
			oTable.fnDraw(); 
		}else{
			alert('操作失败!请联系管理员。');
		}
	});
}
/* Get the rows which are currently selected */
function fnGetSelected( oTableLocal )
{
    return oTableLocal.$('tr.row_selected');
}

function deleteRow(){
	var anSelected = fnGetSelected( oTable );
    if ( anSelected.length !== 0 ) {
    	if(!confirm('确定要删除吗?'))
    		return false;
    	
    	$.post('user/deleteUsers?uid='+ selectedRow.uid,function(data){
    		if(data.mes == '1'){
    			search();
    		}else{
    			alert('删除失败.请联系管理员!');
    		}
    	});
    }else{
    	alert('请选择要删除的记录.');
    }
}