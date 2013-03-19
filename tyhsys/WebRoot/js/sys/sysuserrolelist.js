	jQuery(function(){
		gridWidthAdapter('#listTable');
		$('#listTable').datagrid({
			title:'用户角色管理', //标题
			method:'post',
			//iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			//height:'500',//'406', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:false,//可折叠
			url:"queryUserList", //数据来源
			sortName: 'id', //排序的列
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
				{field:'SU_NAME_CN',align:'center',title:'姓名',width:20,sortable:true},
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
		
	  //更新
	function setUserRole(){
		var rows = $('#listTable').datagrid('getSelections');
		if(rows.length==0 || rows.length > 1){
			$.messager.alert('提示',"请选择一条记录,再进行操作.",'info');
			return;
		}
	
		showWindow("#MyPopWindow",{
			title:'设置用户角色',
			href:'sysuserrole-edit',
			width:500,
			height:260,
			onLoad: function(){
				setformVals(rows[0]);
			}
		});
	}
	
	//编辑时，填充form值
	function setformVals(dataRow){
		$("#dataForm input[name='id']").val(dataRow.uid);
		$("#dataForm input[name='suUsername']").val(dataRow.SU_USERNAME);
		$("#dataForm input[name='suNameCn']").val(dataRow.SU_NAME_CN);
		
		var url = 'getUserRoles?userId=' + dataRow.uid + '&' + Math.random(); 
		$.get(url,function(data){
			var result = data.result;
			var shtml = '';
			$.each(result,function(i,res){
				if(res.CHECKED == 'true'){
					shtml += '<input type="checkbox" class="chkstyleIn" checked="checked" value="'+ res.ID +'" /><label>' + res.SR_NAME + '</label>';
				}else{
					shtml += '<input type="checkbox" class="chkstyleIn" value="'+ res.ID +'" /><label>' + res.SR_NAME + '</label>';
				}
			});
			$('#roles').html(shtml);
		});
	}
	
	function save(){
		var poststr = '[';
		poststr += '{"userId":"' + $('#uuid').val() + '"},';
		poststr += '[';
		
		var roles = $('#roles').find('input:checked');
		roles.each(function(i,chk){
			poststr += '{';
			poststr += '"roleId":"' + $(chk).val() + '"';
			poststr += '}';
			if(i < roles.length - 1)
				poststr += ',';
		});
		
		poststr += ']';
		poststr += ']';
		
		$.post('addOrUpdateUserRole',"data="+poststr,function(data){
			closeWindow('#MyPopWindow');
			$.messager.alert('提示',data.mes,'info');
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