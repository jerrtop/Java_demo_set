
    jQuery(function(){
    	gridWidthAdapter('#userTable');
		$('#userTable').datagrid({
			//title:'用户列表', //标题
			method:'post',
			//iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto',//'406', //高度
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
				{field:'user.suUsername',title:'账号',width:20,sortable:true,
					formatter:function(value,row,index){return row.user.suUsername;} //需要formatter一下才能显示正确的数据
				},
				{field:'user.suNameCn',title:'姓名',width:20,sortable:true,
					formatter:function(value,row,index){return row.user.suNameCn;}
				},
				{field:'user.suAccEna',title:'激活',width:18,sortable:true,
					formatter:function(value,row,index){return row.user.suAccEna == 'Y' ? '是' : '否'}
				},
				{field:'user.crtC',title:'创建人',width:20,sortable:true,
					formatter:function(value,row,index){
						return row.user.crtC;
					}
				},
				{field:'user.crtDate',title:'创建时间',width:20,sortable:true,
					formatter:function(value,row,index){
						var date = new Date(row.user.crtDate);
						return date.format('yyyy-MM-dd hh:mm:ss');
					}
				}
			]],
//			toolbar:[{
//				text:'新增',
//				iconCls:'icon-add',
//				handler:function(){
//					addrow();
//				}
//			},'-',{
//				text:'更新',
//				iconCls:'icon-edit',
//				handler:function(){
//					updaterow();
//				}
//			},'-',{
//				text:'删除',
//				iconCls:'icon-remove',
//				handler:function(){
//					deleterow();
//				}
//			},'-'],
			onLoadSuccess:function(){
				$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
    	
		//下拉表格初始化，这个东西在ajax下要尽量少用，太变态了，每加载一次就会重新创建一次，隐藏在页面上，
		//时间一长效率很低，用firebug一看页面上有几十个同样的层保存着下拉框中的内容，只有整个页面全部刷新才清除。
		//不知道新版本修正了没有，我目前的做法是点击菜单的时候手动清除一下。
		
		//$('#deptCombo').combogrid({
			//idField:'id', //ID字段
		    //textField:'name', //显示的字段
		   // url:"org/queryAll",
		   // fitColumns: true,
			//striped: true,
			//editable:false,//不可编辑，只能选择
		   // columns:[[
		     //   {field:'code',title:'编号',width:100},
		    //    {field:'name',title:'名称',width:150}
		    //]]
		//});

	});
	
    //新增
    function addrow(){
    	showWindow("#MyPopWindow",{
  			title:'新增用户信息',
  			href:'sysuser-edit',
  			width:300,
  			height:250,
  			onLoad: function(){
  				$('#userForm').form('clear');
  				$('#userForm input[name="suAccEna"]').attr("checked",true);
  				$('#userForm input[name="createPerson"]').attr("checked",true);
  			}
  			
  		});
	}
  //更新
    function updaterow(){
		var rows = $('#userTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
		if(rows.length==0 || rows.length > 1){
			$.messager.alert('提示',"请选择一条记录,再进行操作.",'info');
			return;
		}
	
		showWindow("#MyPopWindow",{
  			title:'更新用户信息',
  			href:'sysuser-edit',
  			width:300,
  			height:250,
  			onLoad: function(){
  			//自动将数据填充到表单中，无需再查询数据库，这里需要注意：
  			//如果用的是struts2，它的表单元素的名称都是user.id这样的，那load的时候不能加.user要.form('load', rows[0]);
  			//而spring mvc中表单元素的名称不带对象前缀，直拉就是id，所以这里load的时候是：.form('load', rows[0].user)
  				$("#userForm").form('load', rows[0].user);
  			}
  		});
	}
  	
  //删除
  	function deleterow(){
  		var rows = $('#userTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择记录,再进行操作.",'info');
			return;
		}
  		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	//var rows = $('#userTable').datagrid('getSelections');
	        	var ps = "";
	        	$.each(rows,function(i,n){
	        		if(i==0) 
	        			ps += "?uid="+n.uid;
	        		else
	        			ps += "&uid="+n.uid;
	        	});
	        	$.post('deleteUsers'+ps,function(data){
		        	$('#userTable').datagrid('reload'); 
	        		$.messager.alert('提示',data.mes,'info');
	        	});
	        }
	    });
  	}
    //表格查询
	function searchUser(){
		var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}