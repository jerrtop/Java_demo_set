//表单值初始化
function pageInit(){
	
	fetchMenus();
}

function fetchMenus(){
	//var data1 = [{"id":"1","text":"系统管理","state":"open","children":[{"id":"2","text":"用户管理","state":"open"},{"id":"3","text":"菜单管理","state":"open"},{"id":"4","text":"权限管理","state":"open"},{"id":"12","text":"角色管理","state":"open"}]}];
	var options = {
		url:"fetchMenus?srRoleId=" + editRowId + '&' + Math.random(),
		method:"get",
		animate:true,
		checkbox:true,
		cascadeCheck:false,
		onBeforeCollapse:function(node){
			return false;
		},
		onCheck:checkedMenuOpers,
		onLoadSuccess:function(node,data){
			if(data != null && data != ''){
				appendOpersToMenu(data);
			}
		}
	};
	$('#menu').tree(options);
}

//选中菜单时，选中操作复选框
function checkedMenuOpers(node,checked){
	// Bug1000:第一次远程载入数据时，上级选中时，导致将所有下级选中的bug
	if(checked)
		$(node.target).find(".tree-checkbox").attr("class","tree-checkbox tree-checkbox1");
	else
		$(node.target).find(".tree-checkbox").attr("class","tree-checkbox tree-checkbox0");
	
	$('input[check-id="'+ node.id +'"]').attr("checked",checked);
	
	var childs = $('#menu').tree("getChildren",node.target);
	if(childs != null && childs != undefined && childs != ''){
		$.each(childs,function(i,obj){
			checkedMenuOpers(obj,checked);
		});
	}
}

//在菜单树上显示操作
function appendOpersToMenu(data){
	$.each(data,function(){
		var node_id = this.id;
		var menu = $('div[node-id="'+ node_id +'"]');
		
		// 重新设置checked状态，修复Bug1000
		if(this.checked)
			menu.find(".tree-checkbox").attr("class","tree-checkbox tree-checkbox1");
		else
			menu.find(".tree-checkbox").attr("class","tree-checkbox tree-checkbox0");
			
			
		if(this.attributes != undefined){
			var menuOpers = this.attributes.menuOpers;
			var selectedMenuOpers = this.attributes.selectedMenuOpers;
			if(menuOpers != undefined){
				
				var shtml = '<span span-id="' + node_id + '" class="menuOpers">';
				var opers = menuOpers.split(',');
				$.each(opers,function(i,oper){
					if(oper != undefined){
						var operArray = oper.split('=');
						if(selectedMenuOpers.indexOf(operArray[1]) != -1)//操作权限
							shtml += '<input type="checkbox" check-id="' + node_id + '" value="' + operArray[1] + '" checked="checked"/><label>'+ operArray[0] +'</label>';
						else
							shtml += '<input type="checkbox" check-id="' + node_id + '" value="' + operArray[1] + '" /><label>'+ operArray[0] +'</label>';
					}
				});
				shtml += '</span>';
				menu.append(shtml);
				
				//递归
				if(this.children != undefined)
					appendOpersToMenu(this.children);
			}
		}
	});
}

// 检查唯一
function checkUnique(element){
	var chkVal = $(element).val();
	if($.trim(chkVal) == '')
		return false;
	
	var url = "checkUnique?checkProperty=SR_CODE&checkValue=" + $.trim(chkVal);
	$.get(encodeURI(url),function(data){
		if(data.mes == 1){
			$.messager.alert('提示',"编码存在，请重新输入.",'info');
			$("input[name='srCode']").val('');
		}
	});
}

function addOrUpdate(){
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}

	var roleJSON,menuJSON;
	//角色JSON
	roleJSON = '{';
	roleJSON += 	'"id":"' + $("#dataForm #uuid").val() + '",';
	roleJSON += 	'"srCode":"' + $("#dataForm input[name='srCode']").val() + '",';
	roleJSON += 	'"srName":"' + $("#dataForm input[name='srName']").val() + '"';
	roleJSON += '}';
	
	//菜单权限JSON
	menuJSON = '[';
	var menus = $('#menu').tree("getChecked");
	if(menus != '' && menus != null){
		var menuOper = '';
		$.each(menus,function(i,obj){
			if(i == 0)
				menuOper = '{';
			else
				menuOper += ',{';
			
			menuOper += '"srmMenuId":"' + obj.id + '",';
			var opers = $('input[check-id="'+ obj.id +'"]:checked');
			var operValues = '';
			$.each(opers,function(k,oper){
				if(k == 0)
					operValues = $(oper).attr("value");
				else
					operValues += ',' + $(oper).attr('value');
			});
			menuOper += '"srmOpers":"' + operValues + '"';
			menuOper += '}';
		});
		
		menuJSON += menuOper;
	}
	menuJSON += ']';
	
	roleJSON = JSON.parse(roleJSON);
	roleJSON.menuopers = JSON.parse(menuJSON);
	$.post("addOrUpdateRole","data=" + JSON.stringify(roleJSON),function(data){
		$('#MyPopWindow').window('close');
		$('#listTable').datagrid('reload');  
		$.messager.alert('提示',data.mes,'info');
	});
	
}
