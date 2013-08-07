/*!
* code by komla
* Copyright 2013 zhaijihui
* http://www.linxiaobo.com
*/

$(document).ready(function(){
	//左边点击打开后天首页
	$(".index").click(function(){
		window.parent.frames["mainFrame"].location.href="main.html";
	});	
	//边栏导航展开
	$(".nav-control span").eq(0).click(function(){
		$("#sidebar").css({top:41})
		$("#sidebar .sub-nav").show();
	});
	//边栏导航折叠	
	$(".nav-control span").eq(1).click(function(){
		$("#sidebar").css({top:41})
		$("#sidebar .sub-nav").hide(0);
	});
	//边栏单项展开或关闭切换	
	$("#sidebar h4").click(function(){
		($(this).siblings().css("display")=="none")?$(this).siblings().css({display:"block"}):$(this).siblings().css({display:"none"});
		if($(".left .wrap").outerHeight()-31>$("#sidebar").outerHeight()){
			$("#sidebar").css({top:41})
		}
	});
	//检测上下滚动按钮显示事件
	$("#sidebar h4,.nav-control span").bind("click",function(){
		isShowScrollBtn();
	})
	isShowScrollBtn();
	//判断边栏导航上下滚动控制按钮是否显示
	function isShowScrollBtn(){
		($(".left .wrap").outerHeight()-31<$("#sidebar").outerHeight())?$(".scrolllink").css({display:"block"}):$(".scrolllink").css({display:"none"})
	};
	//边栏导航控制上下滚动

	$(".table .del").click(function(){
		//tableDelTr();
		if(confirm("你确认是要删除该行数据吗")){
			var obj=$(this).parent();
			obj.parent().remove();
		}
	})

})

//图片上传预览
$(function(){
 $("input[type='file']").change(function(evt){
     var files = evt.target.files; 
     for (var i = 0, f; f = files[i]; i++) {
       if (!f.type.match('image.*')) {
         continue;
       }
       var reader = new FileReader();
       reader.onload = (function(theFile) {
         return function(e) {  
          $(".preview img").attr({src:e.target.result,width:400,height:250});   //预览图片的位置                                            
         };
       })(f);
       reader.readAsDataURL(f);
     }
 });
});

function leftScroll(){
	var timer=null;
	$(".scrolllink span").eq(1).bind("mousedown",function(){
		sidebarScroll($("#sidebar"),-10);	
	})
	$(".scrolllink span").bind("mouseup",function(){
		clearInterval(timer)
	})	
	$(".scrolllink span").eq(0).bind("mousedown",function(){
		sidebarScroll($("#sidebar"),10);	
	})
	function sidebarScroll(obj,step){
		clearInterval(timer);
		timer=setInterval(function(){
			var offset=obj.offset();
			var iCurTop=offset.top+step;
			var minTop=$(".left .wrap").outerHeight()-obj.outerHeight()-31;
			if(iCurTop>41){
				iCurTop=41;
				clearInterval(timer);
			}else if(iCurTop<minTop){
				iCurTop=minTop;
				clearInterval(timer);
			}else{
				obj.css({
					top:iCurTop
				})					
			}
		},30)
	}
	function scrollFunc(e){
		var e=e||window.event;
		if(e.wheelDelta){
			sidebarScroll($("#sidebar"),e.wheelDelta/12)
		}else if(e.detail){
			sidebarScroll($("#sidebar"),-e.detail*3)
		}
	}
	if(document.addEventListener){
		document.addEventListener("DOMMouseScroll",scrollFunc,false)
	}
	document.onmousewheel=scrollFunc;
};


