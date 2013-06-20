$(document).ready(function () {
	//菜单
	displayRoleMenus();
	
	
	//展开收起
	$("#shouqi").toggle(function () {
		$("#content_left").css({"margin-left":"-180px"});
		$("#shouqi").attr("class", "switch_hide");
		
		$("#workplace").css({"margin-left":"20px"});
	}, function () {
		$("#workplace").css({"margin-left":"200px"});
		
		$("#content_left").css({"margin-left":"0px"});
		$("#shouqi").attr("class", "switch_show");
		
	});
	
});


//显示菜单
function displayRoleMenus(){
	if(global_role_menus.length == 0){
		alert("该用户无菜单权限!");
		return;
	}
	var shtml = '';
	$.each(global_role_menus,function(index,menu){
		//shtml += '<li class="selectedli"><a href="#" target="frame_content" onclick="setActiveMenu($(this).parent());"><span>主页</span></a></li>';
		if(index == 0){
			shtml += '<li class="selectedli"><a href="#" onclick="setActiveMenu($(this).parent());displayModelMenus('+ index +');"><span>'+ menu.menuname +'</span></a></li>';
		}else{
			shtml += '<li><a href="#" onclick="setActiveMenu($(this).parent());displayModelMenus('+ index +');"><span class="menuunselected">'+ menu.menuname +'</span></a></li>';
		}
	});
	$('#navmenu').append(shtml);
	
	displayModelMenus(0);
}

//显示模块菜单
function displayModelMenus(menuIndex){
	var menu = global_role_menus[menuIndex];
	var shtml = '';
	if(menu.children){//判断二级菜单是否存在
		$.each(menu.children,function(index,submenu){
			shtml += '<div class="menuitem">';
			shtml += '<span class="fthmenu">'+ submenu.menuname +'</span>';
			shtml += '<ul>';
			if(submenu.children){//判断是否有三级菜单
				$.each(submenu.children,function(k,ss){
					shtml += '<li>';
					shtml += '<a href="'+ ss.menuurl +'" target="frame_content">'+ ss.menuname +'</a>';
					shtml += '</li>';
				});
			}
			shtml += '</ul>';
			shtml += '</div>';
		});
	}
	
	$('#menu').html(shtml);
	
	//清空iframe
	$('#frame_content').attr("src",'');
}

// 设置顶端激活的菜单的样式
function setActiveMenu(obj) {
	if (obj.hasClass("selectedli")) {
		return;
	}
	var selObjs = $(".selectedli");
	selObjs.removeClass("selectedli");
	selObjs.find("span").addClass("menuunselected");
	
	obj.find("span").removeClass("menuunselected");
	obj.addClass("selectedli");
	$(document.documentElement).scrollTop(0);
}


