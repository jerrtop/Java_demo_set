
    jQuery(function(){
    	gridWidthAdapter('#listTable');
		$('#listTable').datagrid({
			title:'菜单管理', //标题
			method:'post',
			//iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			//height:'auto',//'406', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:false,//可折叠
			url:"queryMenuList", //数据来源
			sortName: 'SM_CODE', //排序的列
			sortOrder: 'asc', //倒序
			remoteSort: true, //服务器端排序
			idField:'uid', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			pageList:[10,15,20,30,50],
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'SM_CODE',title:'编码',width:15,sortable:true,
					formatter:function(value,row,index){return row.SM_CODE;} //需要formatter一下才能显示正确的数据
				},
				{field:'SM_NAME',title:'菜单名称',width:28,sortable:true,
					formatter:function(value,row,index){return row.SM_NAME;}
				},
				{field:'SM_PAGE',title:'菜单页面',width:28,sortable:true,
					formatter:function(value,row,index){return row.SM_PAGE;}
				},
				{field:'parentMenuName',title:'上级菜单',width:28,sortable:true,
					formatter:function(value,row,index){return (row.parentMenuName == null ? '': row.parentMenuName);}
				},
				{field:'CRT_C',title:'创建人',width:15,sortable:true,
					formatter:function(value,row,index){
						return row.CRT_C;
					}
				},
				{field:'CRT_DATE',title:'创建时间',width:15,sortable:true,
					formatter:function(value,row,index){
						var date = new Date(row.CRT_DATE);
						return date.format('yyyy-MM-dd hh:mm:ss');
					}
				}
			]],
			onLoadSuccess:function(){
				$('#listTable').datagrid('clearSelections');
			}
		});

	});
	
    //新增
    function addrow(){
    	showWindow("#MyPopWindow",{
  			title:'新增菜单信息',
  			href:'sysmenu-edit',
  			width:450,
  			height:380,
  			onLoad: function(){
  				$('#dataForm').form('clear');
  				//$('#dataForm input[name="suAccEna"]').attr("checked",true);
  				//$('#dataForm input[name="createPerson"]').attr("checked",true);
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
	
		showWindow("#MyPopWindow",{
  			title:'更新菜单信息',
  			href:'sysmenu-edit',
  			width:450,
  			height:380,
  			onLoad: function(){
  				$("#menuForm").form('load', rows[0].menu);
  			}
  		});
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
	        	$.post('deleteMenus'+ps,function(data){
		        	$('#listTable').datagrid('reload'); 
	        		$.messager.alert('提示',data.mes,'info');
	        	});
	        }
	    });
  	}
    //表格查询
	function search(){
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
		search();
	}