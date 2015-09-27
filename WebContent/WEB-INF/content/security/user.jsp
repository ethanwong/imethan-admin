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
			<div class="box box-success">
				<div class="box-header with-border">
						用户管理
						<span id="showmessage"></span>   
				</div>
                <div class="box-body">
                	<div class="btn-group">
                		<security:authorize ifAnyGranted="添加用户">
                			<a id="inputUser" class="btn btn-primary btn-flat btn-sm margin-bottom">增加</a>
                		</security:authorize>
                		<security:authorize ifAnyGranted="保存用户">
							<a id="modifyUser" class="btn btn-default btn-flat btn-sm margin-bottom">修改</a>
						</security:authorize>
						<security:authorize ifAnyGranted="删除用户">
							<a id="deleteUser" class="btn btn-danger btn-flat btn-sm margin-bottom">删除</a>
						</security:authorize>
					</div>
					<div class="box-tools pull-right">
	                    <div class="has-feedback">
	                      <input type="text" class="form-control input-sm" placeholder="Search user">
	                      <span class="glyphicon glyphicon-search form-control-feedback"></span>
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
		
		//添加表单内容
		var inputHtmlContent = ""+
				"<form action='' id='user-input-form' role='form' method='post'>"+
				"<input type='hidden'name='id'  id='user-input-id' value=''/>"+
				"<div class='form-group'>"+
				 "   <label for='exampleInputEmail1'>Nickname</label>"+
				  "  <input type='text' class='form-control required' id='user-input-nickname' name='nickname' placeholder='Enter nickname' value=''>"+
				  "</div>"+
				  "<div class='form-group'>"+
				   " <label for='exampleInputEmail1'>Username</label>"+
				   " <input type='text' class='form-control required' id='user-input-username' name='username' placeholder='Enter username' value=''>"+
				  "</div>"+
				  "<div class='form-group'>"+
				   " <label for='exampleInputEmail1'>Password</label>"+
				   " <input type='text' class='form-control required' id='user-input-password' name='password' placeholder='Enter password' value=''>"+
				  "</div>"+
				  "<div class='form-group'>"+
				   " <label for='exampleInputEmail1'>Rolename</label>"+
				   " <select id='user-input-roleId' name='roleId' class='form-control required'>"+
				   " </select>"+
				  "</div>"+
				"</form>";
				
		//加载角色信息
		function loadRoleList(roleId){
			$.ajax({
				url:"${root}/security/user/rolejson",
				type:"POST",
				dateType:"json",
				success:function(data){
					var result = eval("(" + data + ")");
					$("#user-input-roleId").html("");
					$.each(result, function(i, item) {
// 						if(item.id == roleId){
// 							$("#user-input-roleId").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
// 						}else{
							$("#user-input-roleId").append("<option value='"+item.id+"'>"+item.name+"</option>");
// 						}
						
					});
				}
			});
		};
		
		//添加用户
		$("#inputUser").click(function(){			
			setGeneralModal("添加用户",inputHtmlContent,"","确定","user-input-form").bind('click',function(){
				if($("#user-input-form").valid()){
					var id = $("#user-input-id").val();
					var nickname = $("#user-input-nickname").val();
					var username = $("#user-input-username").val();
					var password = $("#user-input-password").val();
					var roleId = $("#user-input-roleId").val();
					
					$.ajax({
						url:"${root}/security/user/save?roleId="+roleId+"&id="+id+"&username="+username+"&password="+password+"&nickname="+nickname,
						type:"POST",
						dateType:"json",
						success:function(msg){
							showMessage(msg,"");
							
							$("#jqGrid").trigger("reloadGrid");
						}
					});
				}else{
					console.log("valid error");
				};
			});
			loadRoleList("");//加载角色信息
		});
		
		//修改用户
		$("#modifyUser").click(function(){
			var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
			console.log("rowId:"+rowid);
			if(rowid == null){
				showWarn("请选择用户","");
			}else{
				setGeneralModal("修改用户",inputHtmlContent,"","确定","user-input-form").bind('click',function(){
					if($("#user-input-form").valid()){
						var id = $("#user-input-id").val();
						var nickname = $("#user-input-nickname").val();
						var username = $("#user-input-username").val();
						var password = $("#user-input-password").val();
						var roleId = $("#user-input-roleId").val();
						
						$.ajax({
							url:"${root}/security/user/save?roleId="+roleId+"&id="+id+"&username="+username+"&password="+password+"&nickname="+nickname,
							type:"POST",
							dateType:"json",
							success:function(msg){
								showMessage(msg,"");
								
								$("#jqGrid").trigger("reloadGrid");
							}
						});
					}else{
						console.log("valid error");
					};
				});
				//加载详情
				$.ajax({
					url:"${root}/security/user/detail/"+rowid,
					type:"POST",
					dateType:"json",
					success:function(data){
						var userInfo  = eval("(" + data + ")");
						
						var id = $("#user-input-id").val(userInfo.id);
						var nickname = $("#user-input-nickname").val(userInfo.nickname);
						var username = $("#user-input-username").val(userInfo.username);
						var password = $("#user-input-password").val(userInfo.password);
		 				loadRoleList(userInfo.roleId);//加载角色信息
					}
				});
				

			}
			
		});
		
		//查看详情
		$("#detailUser").click(function(){
			
		});
		
		//删除用户
		$("#deleteUser").click(function(){
			var rowids = $("#jqGrid").jqGrid('getGridParam','selarrrow');
			var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
			console.log("rowids:"+rowids);
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