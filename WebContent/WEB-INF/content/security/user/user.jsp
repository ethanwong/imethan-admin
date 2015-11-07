<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
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
			<div class="box box-primary">
				<div class="box-header with-border">
						用户管理
						<span id="showmessage"></span>   
				</div>
                <div class="box-body">
                	<div class="row">
                		<div class="col-md-8">
	                		<security:authorize ifAnyGranted="添加用户">
	                			<a href="${root}/security/user/input" class="btn btn-info btn-flat  margin-bottom">增加</a>
	                		</security:authorize>
	                		<security:authorize ifAnyGranted="更新用户">
	                			<a id="modifyUser" href="#" class="btn btn-default btn-flat  margin-bottom">修改</a>
	                		</security:authorize>
							<security:authorize ifAnyGranted="删除用户">
								<a id="batchDeleteUser" class="btn btn-danger btn-flat  margin-bottom">删除</a>
							</security:authorize>
                		</div>
                		<div class="col-md-4">
	                		<div class="input-group">
	                			<input type="text" id="search_LIKES_username" class="form-control"  placeholder="输入账号搜索"/>
	                			<span class="input-group-btn">
	                				<button type="button" id="search" class=" btn btn-info btn-flat"><i class="glyphicon glyphicon-search"></i></button>
	                			</span>
	                		</div>
                		</div>
                	</div>

				    <table id="jqGrid" class="table-bordered table-striped"></table>
    				<div id="jqGridPager"></div>
				</div>
			</div>
		</section>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function () {
		
		//搜索
		$("#search").click(function() {
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'search_LIKES_username' : $('#search_LIKES_username').val()
				},
				page : 1
			}).trigger("reloadGrid");
		});
		
		//修改用户
		$("#modifyUser").click(function(){
			var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
			console.log("rowId:"+rowid);
			if(rowid == null){
				showWarn("请选择用户","");
			}else{
				window.location.href="${root}/security/user/input/"+rowid; 
			}
			
		});
		
		//批量删除用户
		$("#batchDeleteUser").click(function(){
			var rowids = $("#jqGrid").jqGrid('getGridParam','selarrrow');
			var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
			if(rowids == ''){
				showWarn("请选择用户","right");
			}else{
				var url= "${root}/security/user/delete/"+rowids;
				setDeleteModal().bind('click',function(){
					$.ajax({
						url:url,
						type:"POST",
						dateType:"json",
						success:function(msg){
							showMessage(msg,"");
							$("#jqGrid").trigger("reloadGrid");
						},
						error:function(){
							showError("删除失败");	
						}
					});
				});
			}
		});
		
        $("#jqGrid").jqGrid({
            url: '${root}/security/user/json',
            mtype: "POST",
			styleUI : 'Bootstrap',
            datatype: "json",
			rowList: [10, 20, 30],
			colNames: ['用户昵称','账号名称', '角色', '创建时间','操作'],
			colModel: [	
			           	{ name: 'nickname',  width: 150, align: "center" },
			           	{ name: 'username',  width: 150, align: "center" },
			           	{ name: 'roles', width: 150, align: "center",formatter:operationRoles },
						{ name: 'createTime', width: 150, align: "center"},
						{ name: 'id', width: 200, align: "center",formatter:operation}
					  ],
            height: 250,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: "#jqGridPager",
			autowidth : true,
			autoheight : true,
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
			var detailOperation = "<a id='operation1' href='${root}/security/user/detail/"+cellvalue+"' >查看</a> ";
			var modifyOperation = "<a id='operation1' href='${root}/security/user/update/"+cellvalue+"' >修改</a>";
			var deleteOPeration = "<a id='operation2' href='javascript:;' onclick='deleteUser("+cellvalue+")' >删除</a>";
			return detailOperation + modifyOperation + " " + deleteOPeration;
		};
        
    });
	
	//删除
	function deleteUser(id){
		deleteOne("${root}/security/user/delete/"+id);
	};
	
	</script>
</body>
</html>