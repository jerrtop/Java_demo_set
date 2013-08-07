<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Thinking CRM,the crm is thinking</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/style.css" rel="stylesheet">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>	
		<script src="js/main.js"></script>
	</head>
<body class="main">
	<div class="wrap data-info">
		<canvas width="800" height="450" id="draw"></canvas>
	</div>			
	<script type="text/javascript">

		function drawcrile(colors,rs){
			var obj=document.getElementById("draw");
			var ctx=obj.getContext("2d");
			var startAngle=0;
			var endAngle=0;
			for(var i=0;i<colors.length;i++){
				endAngle=startAngle+Math.PI*2*rs[i]/sum(rs);
				ctx.fillStyle=colors[i];
				ctx.strokeStyle=colors[i];
				ctx.beginPath();
				ctx.moveTo(200,150);
				ctx.arc(200,150,100,startAngle,endAngle,false);
				ctx.closePath();  
				ctx.fill();	
				ctx.stroke();
				startAngle=endAngle;		
			}			
		}

		function sum(arr){
			var s=0;
			for(var i=0;i<arr.length;i++){
				s+=arr[i]
			}
			return s;
		}

		function init(){
			var data={
				color:["#d2b3a7","#afc4e1","#c0def8","#2b4c5e"],
				radius:[0.5,1,1.75,2]
			}
			drawcrile(data.color,data.radius);
		}
		window.onload=init();


	</script>
</body>
</html>
