<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content-wrapper">
		<section class="content-header">
		<small><i class="fa fa-home"></i> 权限配置 > 用户管理</small>
			
		</section>
		<section class="content">
			<div class="box">
					<sec:authentication property="principal" />

				<div class="box-header" style="padding-bottom: 0px;">
					<sec:authorize ifAnyGranted="添加用户">
						<button class="btn btn-default btn-flat">增加</button>
						<button class="btn btn-primary btn-flat">修改</button>
						<button class="btn btn-success btn-flat">查看</button>
	<!-- 					<button class="btn btn-warning btn-flat">删除</button> -->
	<!-- 					<button class="btn btn-info btn-flat">查看</button> -->
						<button class="btn btn-danger btn-flat">删除</button>
	                </sec:authorize>
                </div>
                <div class="box-body">
				    <table id="jqGrid"></table>
    				<div id="jqGridPager"></div>
				</div>
			</div>
		</section>
		

		

	</div>
	
	<script type="text/javascript">
	$(document).ready(function () {
        $("#jqGrid").jqGrid({
            url: '${root}/security/user/json',
            mtype: "GET",
			styleUI : 'Bootstrap',
            datatype: "json",
			rowList: [10, 20, 30],
			colNames: ['用户昵称','账号名称', '角色','密码', '创建时间','操作'],
			colModel: [	
			           	{ name: 'nickname',  width: 150, align: "center" },
			           	{ name: 'username',  width: 150, align: "center" },
			           	{ name: 'roles', width: 150, align: "center",formatter:operationRoles },
						{ name: 'password', width: 150, align: "center" },
						{ name: 'createTime', width: 150, align: "center"},
						{ name: 'id', width: 200, align: "center",formatter:operation}
					  ],
            height: 250,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: "#jqGridPager",
			autowidth : true,
			autoheight : true,
			rownumbers : true,
 			viewrecords: true,
 			multiselect : true
        }).closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
        
		function operationRoles(cellvalue, options, rowObject){
			var rolenames = "";
			$.each(cellvalue, function(i, item) {
				if(i != 0){
					rolenames += ",";
				}
				rolenames += item.name;
			});
			return rolenames;
		};
		
		function operation(cellvalue, options, rowObject) {
			var modifyOperation = "<a id='operation1' href='javascript:;' onclick='modifyUser("+cellvalue+")' >修改</a>";
			var deleteOPeration = "<a id='operation2' href='javascript:;' onclick='deleteUser("+cellvalue+")' >删除</a>";
			return modifyOperation + " " + deleteOPeration;
		};
        
        
    });
	
	function deleteUser(url){
		deleteOne(url);
	};
	
	</script>
</body>
</html>