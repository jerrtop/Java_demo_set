<script type="text/javascript">
	var d = '${systemTime}';
	/**
	 * 取得服务器时间的方法
	 */
	d = Number(d);
	d = new Date(d);
	var weekArray = new Array('星期天','星期一','星期二','星期三','星期四','星期五','星期六');
	function setClock(){
		var next = d.getTime()+1000;
		d.setTime(next);
		var ss = d.getSeconds();
		var day = d.getDay();
	    $('#clock').html(
	    d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日 " +
	    weekArray[day] + " " +
	    d.getHours() + ":" + d.getMinutes() + ":" + ((ss<10)?('0'+ss):ss)
	    );
	 }  
	 window.setInterval(setClock, 1000);
	 
	 function loginOut(){
		 if(confirm("确定要退出吗？")){
			 location.href = "user/loginOut";
		 }
	 }
</script>
<div class="top">
	<div class="logo pull-left">
		<small>Thinking CRM</small>
	</div>
	<div class="login-info pull-right">
		<ul class="nav">
			<li id="clock"></li>
			<li>${userSessionInfo.person.piName}</li>
			<li><a href="#" onclick="loginOut()">退出</a></li>
			<li></li>
		</ul>
	</div>
</div>

