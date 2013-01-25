
    jQuery(function(){
    	gridWidthAdapter('#menuTable');
		$('#menuTable').datagrid({
			title:'菜单列表', //标题
			method:'post',
			//iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto',//'406', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:false,//可折叠
			url:"queryMenuList", //数据来源
			sortName: 'menu.id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'uid', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			pageList:[10,15,20,30,50],
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'menu.smCode',title:'编码',width:10,sortable:true,
					formatter:function(value,row,index){return row.menu.smCode;} //需要formatter一下才能显示正确的数据
				},
				{field:'menu.smName',title:'菜单名称',width:30,sortable:true,
					formatter:function(value,row,index){return row.menu.smName;}
				},
				{field:'menu.parentMenu.smName',title:'上级菜单',width:30,sortable:true,
					formatter:function(value,row,index){return (row.menu.parentMenu == null ? '': row.menu.parentMenu.smName);}
				},
				{field:'menu.crtC',title:'创建人',width:15,sortable:true,
					formatter:function(value,row,index){
						return row.menu.crtC;
					}
				},
				{field:'menu.crtDate',title:'创建时间',width:15,sortable:true,
					formatter:function(value,row,index){
						var date = new Date(row.menu.crtDate);
						return date.format('yyyy-MM-dd hh:mm:ss');
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					addrow();
				}
			},'-',{
				text:'更新',
				iconCls:'icon-edit',
				handler:function(){
					updaterow();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow();
				}
			},'-'],
			onLoadSuccess:function(){
				$('#menuTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});

	});
	
    //新增
    function addrow(){
    	showWindow("#MyPopWindow",{
  			title:'新增菜单信息',
  			href:'sysmenu-edit',
  			width:560,
  			height:320,
  			onLoad: function(){
  				$('#userForm').form('clear');
  				$('#userForm input[name="suAccEna"]').attr("checked",true);
  				$('#userForm input[name="createPerson"]').attr("checked",true);
  			}
  			
  		});
	}
  //更新
    function updaterow(){
		var rows = $('#menuTable').datagrid('getSelections');
		if(rows.length==0 || rows.length > 1){
			$.messager.alert('提示',"请选择一条记录,再进行操作.",'info');
			return;
		}
	
		showWindow("#MyPopWindow",{
  			title:'更新菜单信息',
  			href:'sysmenu-edit',
  			width:560,
  			height:320,
  			onLoad: function(){
  			//自动将数据填充到表单中，无需再查询数据库，这里需要注意：
  			//而spring mvc中表单元素的名称不带对象前缀，直拉就是id，所以这里load的时候是：.form('load', rows[0].user)
  				$("#menuForm").form('load', rows[0].menu);
  			}
  		});
	}
  	
  //删除
  	function deleterow(){
  		var rows = $('#menuTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择记录,再进行操作.",'info');
			return;
		}
  		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	//var rows = $('#menuTable').datagrid('getSelections');
	        	var ps = "";
	        	$.each(rows,function(i,n){
	        		if(i==0) 
	        			ps += "?uid="+n.uid;
	        		else
	        			ps += "&uid="+n.uid;
	        	});
	        	$.post('deleteMenus'+ps,function(data){
		        	$('#menuTable').datagrid('reload'); 
	        		$.messager.alert('提示',data.mes,'info');
	        	});
	        }
	    });
  	}
    //表格查询
	function searchUser(){
		var params = $('#menuTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#menuTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}