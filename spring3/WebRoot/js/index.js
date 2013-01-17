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