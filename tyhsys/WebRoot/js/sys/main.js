$(document).ready(function () {
	
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


