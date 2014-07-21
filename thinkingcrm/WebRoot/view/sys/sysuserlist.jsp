<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- jquery &bootstrap ui validate -->
		<script type="text/javascript" src="js/sys/sysuserlist.js"></script>
	</head>
	<body>
			<div class="warp">
				<ul class="breadcrumb">
				    <li><span id="test">您当前的位置：</span><a href="#">系统管理</a> <span class="divider">>></span></li>
				    <li class="active">用户列表</li>
			    </ul>
			</div>
			<div id="compat-width">
				<div class="container-fluid">
				 <div class="row-fluid">
					<div class="span12">
						<form class="form-inline">
						  用户名：<input type="text" class="input-medium" id="userName" placeholder="用户名">
						  <span>姓名：<input type="text" class="input-medium" id="famliyName" placeholder="姓名"></span>
						 <button type="button" class="btn" onclick="search()">查询</button> 
						</form>
						<button type="button" class="btn btn-primary" onclick="showModal('add')">添加</button>
						<span class="mLft"><button type="button" class="btn" onclick="showModal('edit')">修改</button></span> 
						<span class="mLft"><button type="button" class="btn" onclick="deleteRow()">删除</button></span> 
						<table class="table table-bordered table-hover datatable"></table>
					</div>
				</div>
			  </div>
			</div>
			 <!-- 弹出框 -->
			 <div id="dialog-form" class="hide"></div>
	</body>
</html>
