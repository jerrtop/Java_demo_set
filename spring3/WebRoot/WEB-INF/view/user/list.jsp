<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <script type="text/javascript">
    jQuery(function($){
		$('#userTable').datagrid({
			title:'用户列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"user/queryList", //数据来源
			sortName: 'user.id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'uid', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'user.name',title:'名字',width:20,sortable:true,
					formatter:function(value,row,index){return row.user.name;} //需要formatter一下才能显示正确的数据
				},
				{field:'user.age',title:'年龄',width:20,sortable:true,
					formatter:function(value,row,index){return row.user.age;}
				},
				{field:'user.birthday',title:'生日',width:30,sortable:true,
					formatter:function(value,row,index){return row.user.birthday;}
				},
				{field:'user.deptId',title:'部门',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.deptName;  //该列的值是deptId，显示的是deptName
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
				$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
    	
		//下拉表格初始化，这个东西在ajax下要尽量少用，太变态了，每加载一次就会重新创建一次，隐藏在页面上，
		//时间一长效率很低，用firebug一看页面上有几十个同样的层保存着下拉框中的内容，只有整个页面全部刷新才清除。
		//不知道新版本修正了没有，我目前的做法是点击菜单的时候手动清除一下。
		$('#deptCombo').combogrid({
			idField:'id', //ID字段
		    textField:'name', //显示的字段
		    url:"dept/queryAll",
		    fitColumns: true,
			striped: true,
			editable:false,//不可编辑，只能选择
		    columns:[[
		        {field:'code',title:'编号',width:100},
		        {field:'name',title:'名称',width:150}
		    ]]
		});

	});
    //新增
    function addrow(){
    	showWindow({
  			title:'增加用户信息',
  			href:'user/popWindow',
  			width:300,
  			height:250,
  			onLoad: function(){
  				$('#userForm').form('clear');
  			}
  			
  		});
	}
  //更新
    function updaterow(){
		var rows = $('#userTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的用户",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一位用户进行更新",'info');
			return;
		}
		showWindow({
  			title:'更新用户信息',
  			href:'user/popWindow',
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
  		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	var rows = $('#userTable').datagrid('getSelections');
	        	var ps = "";
	        	$.each(rows,function(i,n){
	        		if(i==0) 
	        			ps += "?uid="+n.uid;
	        		else
	        			ps += "&uid="+n.uid;
	        	});
	        	$.post('user/delete'+ps,function(data){
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
    
	</script>	
  </head>
  
  <body>
    <form id="queryForm" style="margin:10;text-align: center;">
		<table width="100%">
			<tr>
			<td>名字：<input name="name" style="width: 200"></td>
			<td>年龄：<input class="easyui-numberspinner" name="age" min="1" max="120" increment="1" style="width:200px;"></input></td>
			<td align="center"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
			</tr>
			<tr>
			<td>生日：<input name="birthday" style="width: 200" class="Wdate" onClick="WdatePicker()"></td>
			<td>部门：<input id="deptCombo" name="dept.id" style="width: 200"></td>
			<td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<div style="padding:10" id="tabdiv">
		<table id="userTable"></table>
	</div>
  </body>
</html>
