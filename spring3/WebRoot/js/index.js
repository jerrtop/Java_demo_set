String.prototype.trim = function(){
	var t = this.replace(/(^\s*)|(\s*$)/g,""); 
	return t.replace(/(^\u3000*)|(\u3000*$)/g,"");
};
function killErrors(){
	return true;
}
//window.onerror = killErrors;