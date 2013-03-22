
    var editUserId = '';
    jQuery(function(){
    	gridWidthAdapter('#listTable');
		$('#listTable').datagrid({
			title:'用户管理', //标题
			method:'post',
			singleSelect:false, //多选
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:false,//可折叠
			url:"queryUserList", //数据来源
			sortName: 'user.id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'uid', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			pageList:[10,15,20,30,50],
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'SU_USERNAME',align:'center',title:'账号',width:20,sortable:true},
				{field:'PI_NAME',align:'center',title:'姓名',width:20,sortable:true},
				{field:'PI_MOBILE',align:'center',title:'手机',width:20,sortable:true},
				{field:'PI_EMAIL',align:'center',title:'邮箱',width:20,sortable:true},
				{field:'SU_ACC_ENA',align:'center',title:'激活',width:18,sortable:true,
					formatter:function(value,row,index){return row.SU_ACC_ENA == 'Y' ? '是' : '否';}
				},
				{field:'CRT_C',align:'center',title:'创建人',width:20,sortable:true},
				{field:'CRT_DATE',align:'center',title:'创建时间',width:20,sortable:true}
			]],
			onLoadSuccess:function(){
				$('#listTable').datagrid('clearSelections');
			}
		});
	});
	
    //新增
    function addrow(){
    	editUserId = '';
    	showWindow("#MyPopWindow",{
  			title:'新增用户信息',
  			href:'sysuser-edit',
  			width:600,
  			height:470,
  			onLoad: function(){
  				$('#dataForm').form('clear');
  				$('#dataForm input[name="suAccEna"]').attr("checked",true);
  			}
  			
  		});
	}
    
    //更新
    function updaterow(){
		var rows = $('#listTable').datagrid('getSelections');
		if(rows.length==0 || rows.length > 1){
			$.messager.alert('提示',"请选择一条记录,再进行操作.",'info');
			return;
		}
		editUserId = rows[0].uid;
	
		showWindow("#MyPopWindow",{
  			title:'更新用户信息',
  			href:'sysuser-edit',
  			width:600,
  			height:470,
  			onLoad: function(){
  				setformVals(rows[0]);
  			}
  		});
	}
    
  	//编辑时，填充form值
    function setformVals(dataRow){
    	//账号只读
    	$("#dataForm input[name='suUsername']").attr("disalbed",true);
    	$("#dataForm input[name='suUsername']").attr("onblur","");
    	
		$("#dataForm input[name='id']").val(dataRow.uid);
		$("#dataForm input[name='suUsername']").val(dataRow.SU_USERNAME);
		$("#dataForm input[name='suPassword']").val(dataRow.SU_PASSWORD);
		var suAccEna = dataRow.SU_ACC_ENA;
		if(suAccEna != null && suAccEna != ""){
			$("#dataForm input[name='suAccEna'][value='"+ suAccEna +"']").attr("checked",true);
		}
		$("#dataForm input[name='person.id']").val(dataRow.PERSONID);
		$("#dataForm input[name='person.piName']").val(dataRow.PI_NAME);
		$("#dataForm input[name='person.piCode']").val(dataRow.PI_CODE);
		
		$("#org").combotree('setValue',dataRow.PI_ORG);
		$('#sup').combobox('setValue',dataRow.PI_SUP);
		
		$("#dataForm input[name='person.piPhone']").val(dataRow.PI_PHONE);
		$("#dataForm input[name='person.piMobile']").val(dataRow.PI_MOBILE);
		$("#dataForm input[name='person.piQq']").val(dataRow.PI_QQ);
		$("#dataForm input[name='person.piEmail']").val(dataRow.PI_EMAIL);
		$("#dataForm input[name='person.piIdcard']").val(dataRow.PI_IDCARD);
		
		var piSex = dataRow.PI_SEX;
		if(piSex != null && piSex != '')
			$("#dataForm input[name='person.piSex'][value='"+ piSex +"']").attr("checked",true);
		
		$("#dataForm input[name='person.piJoinDate']").val(dataRow.PI_JOIN_DATE);
		$("#dataForm input[name='person.piBirthDate']").val(dataRow.PI_BIRTH_DATE);
		$("#dataForm input[name='person.piDegree']").val(dataRow.PI_DEGREE);
		$("#dataForm input[name='person.piType']").val(dataRow.PI_TYPE);
		$("#dataForm input[name='person.piNation']").val(dataRow.PI_NATION);
		$("#dataForm input[name='person.piAddress']").val(dataRow.PI_ADDRESS);
    }
    
  //删除
  	function deleterow(){
  		var rows = $('#listTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择记录,再进行操作.",'info');
			return;
		}
  		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	var ps = "";
	        	$.each(rows,function(i,n){
	        		if(i==0) 
	        			ps += "?uid="+n.uid;
	        		else
	        			ps += "&uid="+n.uid;
	        	});
	        	$.post('deleteUsers'+ps,function(data){
		        	$('#listTable').datagrid('reload'); 
	        		$.messager.alert('提示',data.mes,'info');
	        	});
	        }
	    });
  	}
    //表格查询
	function searchUser(){
		var params = $('#listTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#listTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}