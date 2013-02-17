
var changeMore = 1;
$(document).ready(function () {
	fetchMenuList();
	$("#navmore").click(function (e) {
		if (changeMore == 1) {
			e.stopPropagation();
			$("#morebox").stop().slideDown("fast");
			$("#morebox").height("auto");
			$("#bgIframe").height($("#morebox").height());
			$("#iframebox").stop().show();
			var obj = window.frames["frame_content"].document;
			if (obj.ef) {
				//empty
			} else {
				obj.ef = "y";
				$(obj).click(function () {
					$(window.parent.document).find("#morebox").stop().slideUp("fast");
					$(window.parent.document).find("#iframebox").stop().hide();
					changeMore = 1;
				});
			}
			changeMore = 2;
		} else {
			$("#morebox").stop().slideUp("fast");
			$("#iframebox").stop().hide();
			changeMore = 1;
		}
	});
	$("#shouqi").toggle(function () {
		$("#con_left").css({"left":"-208px"});
		$("#sidebar").hide();
		$("#con_right").css({"paddingLeft":"20px"});
		$("#content").css({width:'98%'});
		$("#shouqi img").attr("src", "img/sys/switch_right.gif");
	}, function () {
		$("#con_left").css({"left":"-30"});
		$("#sidebar").show();
		$("#content").css({width:'85%'});
		$("#con_right").css({"paddingLeft":"230"});
		$("#shouqi img").attr("src", "img/sys/switch_left.gif");
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
function fetchMenuList() {
	$("#frame_content").show();
	
	//-- JERRY --//
	var dataCount = 15;
	if (dataCount > 0) {
		var length = dataCount / 5;
		var shtml = "";
		shtml += "<h2>\u5168\u90e8\u529f\u80fd\u83dc\u5355</h2>";
		for (var k = 0; k < length; k++) {
			shtml += "<ul>";
			for (var i = k * 5; i < k * 5 + 5 && i < dataCount; i++) {
				shtml += "<li class=\"appendMenuItem\">";
				shtml += "<h3>\u4e00\u7ea7\u83dc\u5355</h3>";
				shtml += "<span>";
				for (var j = 0; j < 8; j++) {
					if (j >= 5) {
						shtml += "<a name=\"link_a_" + k + "\" style=\"display:none;\" href=\"#\" target=\"frame_content\" onclick=\"$(document.documentElement).scrollTop(0);\" >\u4e8c\u7ea7\u83dc\u5355</a>";
					} else {
						shtml += "<a href=\"#\" target=\"frame_content\" onclick=\"$(document.documentElement).scrollTop(0);\" >\u4e8c\u7ea7\u83dc\u5355</a>";
					}
				}
				shtml += "<div style=\"clear:both;\"></div>";
				shtml += "</span>";
				shtml += "<div style=\"clear:both;\"></div>";
				shtml += "</li>";
			}
			shtml += "</ul>";
			shtml += "<span name=\"moreMenu_" + k + "\" class=\"moremenu\">\u663e\u793a\u66f4\u591a\u83dc\u5355</span>";
		}
		$("#morebox").html(shtml);
		$("#morebox a").click(function () {
			$("#morebox").hide();
			$("#iframebox").hide();
			$("#con_left").animate({"left":"-208px"}, 500);
			$("#con_right").animate({"paddingLeft":"20px"}, 500);
			$("#shouqi img").attr("src", "../images/switch_right.gif");
			if (changeMore == 2) {
				changeMore = 1;
			}
		});
		$("#navmore").show();
	} else {
		$("#navmore").hide();
	}
}

//弹出窗口
function showWindow(selector,options){
	$(selector).window(options);

}
//关闭弹出窗口
function closeWindow(selector){
	$(selector).window('close');

}

