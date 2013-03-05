String.prototype.trim = function(){
	var t = this.replace(/(^\s*)|(\s*$)/g,""); 
	return t.replace(/(^\u3000*)|(\u3000*$)/g,"");
};
function killErrors(){
	return true;
}
//window.onerror = killErrors;
Date.prototype.format = function(fmt){
	var d = this ? new Date(this) : new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	if(month < 10){
		month = '0' + month;
	}
	var date = d.getDate();
	if(date < 10){
		date = '0' + date;
	}
	
	var Hours = d.getHours();
	if(Hours < 10){
		Hours = '0' + Hours;
	}
	var Minutes=d.getMinutes();
	if(Minutes < 10){
		Minutes = '0' + Minutes;
	}
	var Seconds=d.getSeconds();
	if(Seconds < 10){
		Seconds = '0' + Seconds;
	}
	
	var formatValue;
	if(fmt == 'yyyy-MM-dd'){
		formatValue = year + '-' + month + '-' + date;
	}
	
	if(fmt == 'yyyy-MM-dd hh:mm:ss'){
		formatValue = year + '-' + month + '-' + date + " " + Hours + ":" + Minutes + ":" + Seconds;
	}
	return formatValue;
};

//Grid自适应宽度
function gridWidthAdapter(selector) { //TODO datagrid寬度自適應IE8bug
	/**
	$(window).resize(function() {
		alert("window resize");
		var winW = 1000;
		if (window.innerHeight) {
			winW = window.innerWidth;
		} else if (document.documentElement
				&& document.documentElement.clientHeight) { // IE 6
			winW = document.documentElement.clientWidth;
		} else if (document.body) {
			winW = document.body.clientWidth;
		}

		//$(selector).jqGrid('setGridWidth', winW - 18);
		$(selector).datagrid('resize',{width:winW-5});  
	});
	*/
}

function PanShow(objname,classname,curid,maxid)
{
	for(var i=1;i<=maxid;i++)
	{
		var obj=document.getElementById(objname+i);
		if(obj)
		{
			obj.style.display="none";
			var objtitle=document.getElementById(objname+"T"+i);
			if(objtitle)
			{
				objtitle.className=classname+"_off";
			}
		}
	}
	var obj=document.getElementById(objname+curid);
	if(obj)
	{
		obj.style.display="block";
	}
	var objtitle=document.getElementById(objname+"T"+curid);
	if(objtitle)
	{
		objtitle.className=classname+"_on";
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

//将jquery系列化后的值转为name:value的形式。
function convertArrayToJson(o) {  
	var v = {}; 
	for (var i in o) { 
		if (typeof (v[o[i].name]) == 'undefined') 
			v[o[i].name] = o[i].value; 
		else 
			v[o[i].name] += "," + o[i].value; 
	} 
	return v; 
}
