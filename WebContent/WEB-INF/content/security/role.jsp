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
			<small><i class="fa fa-home"></i> 权限配置 > 角色管理</small>
			<span style="padding-left: 20px;" id="warn-message"></span>
		</section>

		<!-- Main content -->
		<section class="content">
		    <div class="row">
	            <div class="col-md-4">
					<div class="box box-success">
						<div class="box-header with-border">
						角色列表
						<span id="leftshowmessage"></span>
						</div>
						<div class="box-body no-padding">
							<div class="btn-group" style="padding: 10px;">
								<a id="addRole" class="btn btn-primary btn-sm btn-flat">增加</a>
				            	<a id="deleteRole" class="btn btn-danger btn-sm btn-flat">删除</a>
							</div>
							<ul id="role-list" class="nav nav-pills nav-stacked">
		                   		<li class="active"><a href="#"><i class="fa fa-inbox"></i> Inbox <span class="label label-primary pull-right">12</span></a></li>
		                   		<li><a href="#"><i class="fa fa-envelope-o"></i> Sent</a></li>
		                    	<li><a href="#"><i class="fa fa-file-text-o"></i> Drafts</a></li>
		                    	<li><a href="#"><i class="fa fa-filter"></i> Junk <span class="label label-warning pull-right">65</span></a></li>
		                    	<li><a href="#"><i class="fa fa-trash-o"></i> Trash</a></li>
		                  	</ul>
						</div>
						<div class="box-footer clearfix"></div>
					</div>
	            </div>
	            <div class="col-md-8">
					<div class="box box-danger">
						<div class="box-header with-border">
							添加角色
							<span id="rightshowmessage"></span>
						</div>
						<div class="box-body">
							<form id="input-form" action="" method="post">
								<input type="hidden" id="id" name="id" value="">
								<div class="form-group">
									<label for="exampleInputTitle">名称</label>
									<input type="text" class="form-control required" id="name" placeholder="Enter name" name="name" >
								</div>
								<div class="form-group">
									<label for="exampleInputTitle">菜单授权</label> 
									<input type="hidden" class="form-control" id="menuPermission" name="menuPermission"  value="">
									<div class="panel panel-default">
										<div class="panel-body">
											<div id="role-menu-tree" class="ztree"></div>
										</div>
									</div>
								</div>
								<button id="saveRole" type="button" class="btn btn-primary btn-flat" >保存</button>
							</form>
						
						</div>
						<div class="box-footer clearfix"></div>
					</div>
	            </div>
            </div>

		</section>
	</div>
<script type="text/javascript">
	//页面加载时初始化脚本
	$(document).ready(function () {
		
		//添加校验
		$("#input-form").validate({
			rules: {
			 name:{required:true,rangelength:[3,10],
			    remote:{
			           type:"POST",
			           url:"${root}/security/role/isExistsName",
			           data:{
			             	id:function(){return $("#id").val();},
			        	   name:function(){return $("#name").val();}
			           } 
			          } 
			      }
			},
			messages: {
			 name:{
				 required:"角色名不能为空！",
				 rangelength:"角色名位数必须在3到10字符之间！",
				 remote:"角色名已经存在！"
				 }
			}
		});
		
		//加载角色列表
		reloadRoleList();
		
		//添加角色
		$("#addRole").click(function(){
			$("#id").val("");
			$("#name").val("");
			$("#menuPermission").val("");
			setResourcePermissionZtree(0);
			
		});
		
		//保存角色
		$("#saveRole").click(function(){
			
			if($("#input-form").valid()){
				var id = $("#id").val();
				var name = $("#name").val();
				var menuPermission = $("#menuPermission").val();
				
				$.ajax({
					url:"${root}/security/role/save?id="+id+"&name="+name+"&menuPermission="+menuPermission,
					type:"POST",
					dateType:"json",
					success:function(msg){
						showMessage(msg,"right");
						var checkedId = id;
						//加载角色列表
						reloadRoleList(checkedId);
					}
				});
				
				location.href = "#top";
			};
			
		});
		
		//删除角色
		$("#deleteRole").click(function(){
			var id = $("#role-list").find(".active").attr("itemid");
			
			console.log("deleteRole id:"+id);
			//确定删除的提示
			var url= "${root}/security/role/delete/"+id;
			setDeleteModal().bind('click',function(){
				$.ajax({
					url:url,
					type:"POST",
					dateType:"json",
					success:function(msg){
						showMessage(msg,"left");
						
						reloadRoleList();
					}
				});
			});
			
		});
	});
	
	//加载角色列表
	function reloadRoleList(checkedId){
		$("#role-list").html("");
		$.ajax({
			url:"${root}/security/role/json",
			type:"POST",
			dateType:"json",
			success:function(data){
				var result = eval("(" + data + ")");
				$.each(result, function(i, item) {
					console.log("checkedId:"+checkedId)
					if(checkedId != undefined){
						if(item.id == checkedId){
							var li = "<li class='active' id='a"+i+"' itemid='"+item.id+"'><a href='#'> "+item.name+"</a></li> ";
							$("#role-list").append(li);
							setDetail(item.id);//展开选中角色信息
						}else{
							var li = "<li id='a"+i+"' itemid='"+item.id+"'><a href='#'> "+item.name+"</a></li> ";
							$("#role-list").append(li);
						}
					}else{
						if(i==0){
							var li = "<li class='active' id='a"+i+"' itemid='"+item.id+"'><a href='#'> "+item.name+"</a></li> ";
							$("#role-list").append(li);
							 setDetail(item.id);//默认展开第一个角色信息
						}else{
							var li = "<li id='a"+i+"' itemid='"+item.id+"'><a href='#' > "+item.name+"</a></li> ";
							$("#role-list").append(li);
						};
					}
					
					
					//绑定点击事件
					$('#a'+i).bind('click', function() { 
						//设置选中样式
						$.each(result, function(j, item2) {
							if(j != i){
								$("#a"+j).attr("class","");
							}else{
								$("#a"+j).attr("class","active");
							}
						});
						//展开右侧信息
						setDetail(item.id);
					}); 

		        });
			},
			error:function(data){
			}
		});
	};
	
	//设置角色详情
	function setDetail(id){
		$.ajax({
			url:"${root}/security/role/detail/"+id,
			type:"POST",
			dateType:"json",
			success:function(data){
				var result = eval("(" + data + ")");
				$("#id").val(result.id);
				$("#name").val(result.name);
				setResourcePermissionZtree(result.id);
				
				$("#input-form").valid();
				$("#menuPermission").valid();
			}
		});
	}
	
	//ztree参数设置
	var setting = {
			view: {
				showLine: true,
				fontCss : {size:"14"}
			},
			check: {
				enable: true
			},
			data: {
				key: {
					children: "childrens",
					checked: "checked"
				},
				simpleData: {
					enable: true
				}
			},
			callback:{
//					onClick:clickNode,
				onCheck: onCheck
			}
	};
	
	//设置资源授权树
	function setResourcePermissionZtree(roleId){
		$.ajax({
			url:"${root}/security/role/menupermission/"+roleId,
			type:"POST",
			dateType:"json",
			success:function(data){
				var zNodes = eval("(" + data + ")");
				var zTree = $.fn.zTree.init($("#role-menu-tree"), setting, eval(zNodes));
				
				//设置已经选中节点表单隐藏域信息
				var checkedNodes = zTree.getCheckedNodes(true);
				$.each(checkedNodes, function(i, item) {
					var node = item.nodeType+"-"+item.id;
		 			var source = $("#menuPermission").val();
		 			var target = source+","+node;
		 			$("#menuPermission").val(target);
				});
			}
		});
		
	};
	
	//点击节点复选框事件
	function onCheck(e, treeId, treeNode) {
		$("#menuPermission").val("");
		var zTree = $.fn.zTree.getZTreeObj("role-menu-tree");
		
		var checkedNodes = zTree.getCheckedNodes(true);
		$.each(checkedNodes, function(i, item) {
			var node = item.nodeType+"-"+item.id;
 			var source = $("#menuPermission").val();
 			var target = source+","+node;
 			$("#menuPermission").val(target);
		});
	};
</script>
</body>
</html>