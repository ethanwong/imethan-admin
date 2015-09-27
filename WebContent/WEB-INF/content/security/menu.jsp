<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
</head>
<body>
      <div class="content-wrapper">
        <section class="content-header">
			<small><i class="fa fa-home"></i> 权限配置 > 菜单授权管理</small>
		</section>
		     
        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-4">
				<div class="box box-primary">
					<div class="box-header with-border">
						菜单管理
						<span id="leftshowmessage"></span>   
					</div>
					<div class="box-body">
						<div class="btn-group">
							<a id="addRootMenu" class="btn btn-primary  btn-sm btn-flat">增加一级</a>
			            	<a id="addSecondMenu" class="btn btn-info  btn-sm btn-flat">增加二级</a>
			            	<a id="modifyMenu" class="btn btn-default  btn-sm btn-flat">修改</a>
			            	<a id="deleteMenu" class="btn btn-danger  btn-sm btn-flat">删除</a>
						</div>
						<div id="menu-tree" class="ztree"></div>
					</div>
					<div class="box-footer clearfix"></div>
				</div>
            </div><!-- /.col -->
            <div class="col-md-8">
              <div class="box box-info">
                <div class="box-header with-border">
                	 授权管理
                	 <span id="rightshowmessage"></span>    
                </div>
                <div class="box-body">
                	<div class="btn-group">
		                <a id="quickAddPermissionPreview" class="btn btn-success margin-bottom btn-sm btn-flat">快捷授权</a>
		            	<a id="addPermission" class="btn btn-info margin-bottom btn-sm btn-flat">添加授权</a>
		            	<a id="modifyPermission" class="btn btn-default margin-bottom btn-sm btn-flat">修改</a>
		            	<a id="deletePermission" class="btn btn-danger margin-bottom btn-sm btn-flat">删除</a>
                	</div>
                	<div class="box-tools pull-right">
	                    <div class="has-feedback">
	                      <input type="text" class="form-control input-sm" placeholder="Search permission">
	                      <span class="glyphicon glyphicon-search form-control-feedback"></span>
	                    </div>
                 	</div>
					<table id="jqGrid" class="table-bordered table-striped"></table>
    				<div id="jqGridPager"></div>
                </div>
                <div class="box-footer no-padding">
					
                </div>
              </div>
            </div>

          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	
	
	<!-- 添加菜单modal开始 -->
	<div class="modal fade" id="menu-input-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">增加菜单</h4>
				</div>
				<div class="modal-body">
					<form id="menu-input-form" role="form" action="${root}/security/menu/save" method="post">
						<input type="hidden" id="menu-id" name="id" value="">
						<input type="hidden" id="menu-parentId" name="parentId" value="">
						<input type="hidden" id="menu-isRoot" name="root" value="true">
						
						<div id="menu-input-form-content"></div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-flat" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary btn-flat" id="menu-input-modal-button-save">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加菜单modal结束 -->
	<!-- 添加授权modal开始 -->
	<div class="modal fade" id="permission-input-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加授权</h4>
				</div>
				<div class="modal-body">
					<form id="permission-input-form" role="form" action="${root}/security/permission/save" method="post">
						<input type="hidden" id="permission-id" name="id" value="">
						<input type="hidden" id="permission-menu-id" name="menu.id" value="">
						
						<div id="permission-input-form-content">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-flat" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary btn-flat" id="permission-input-modal-button-save" >保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加授权modal结束 -->
	
	<script type="text/javascript">
		
		//ztree参数设置
		var setting = {
				view: {
					showLine: true
				},
				data: {
					key: {
						children: "childrens"
					},
					simpleData: {
						enable: true
					}
				},
				callback:{
					onClick:clickNode
				}
		};
		
		//初始化ztree
		function initZtree(){
			$.ajax({
				url:"${root}/security/menu/json",
				type:"POST",
				success:function(msg){
					var zNodes = msg;
					$.fn.zTree.init($("#menu-tree"), setting, eval("(" + zNodes + ")"));
				}
			});
		};
		
		//点击菜单树节点事件
		function clickNode(event, treeId, treeNode){
// 			console.log("event:"+event);
// 			console.log("treeId:"+treeId);
// 			console.log("treeNode:"+treeNode);
			console.log("treeNode name:"+treeNode.name);
			console.log("treeNode id:"+treeNode.id);
			
			$("#jqGrid").jqGrid('setGridParam', {
				url : '${root}/security/permission/json/'+treeNode.id+'/'+treeNode.root,
				page:1
			}).trigger("reloadGrid");
		};
		
		//初始化授权信息
		function initPermission(menuId,isRoot){
	        $("#jqGrid").jqGrid({
	            url: '${root}/security/permission/json/'+menuId+"/"+isRoot,
	            mtype: "POST",
				styleUI : 'Bootstrap',
	            datatype: "json",
				rowList: [10, 20, 30],
				colNames: ['','','授权名称','URL','操作'],
				colModel: [	{name : 'id',key:true,hidden:true},
							{name : 'menuId',hidden:true},
				           	{ name: 'name',width:'100', align: "center" },
							{ name: 'url', width:'100',align: "center"},
							{ name: '',  width:'100',align: "center",formatter:operation}
						  ],
	            height: 250,
	            rowNum: 10,
	            rowList: [10, 20, 30],
	            pager: "#jqGridPager",
				autowidth : true,
				autoheight : true,
				rownumbers : false,
	 			viewrecords: true,
	 			multiselect : true
	        }).closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
		}
		
		
		function operation(cellvalue, options, rowObject) {
			console.log("cellvalue:"+rowObject.id);
			var modifyOperation = "<a class='btn btn-xs modifyPermission' onclick='modifyPermission("+rowObject.id+")'>修改</a>";
			var deleteOPeration = "<a class='btn btn-xs deletePermission' onclick='deletePermission("+rowObject.id+")'>删除</a>";
			return modifyOperation + " " + deleteOPeration;
		};
		
		function modifyPermission(rowId){
			$('#jqGrid').jqGrid('setSelection',rowId);
			$("#modifyPermission").click();
		}
		
		function deletePermission(rowId){
			$('#jqGrid').jqGrid('setSelection',rowId);
			$("#deletePermission").click();
		}

		
		//获取选中节点
		function getSelectNode(){
			var treeObj = $.fn.zTree.getZTreeObj("menu-tree");
			var nodes = treeObj.getSelectedNodes();
			var node = nodes[0];
			return node;
		};
		
		$(document).ready(function() {
			//初始化授权列表
			initPermission(0,true);
			
			initZtree();//初始化菜单ztree
			
			//添加菜单和修改菜单的表单内容
			var menuInputFormContent = 
						"<div class='form-group'>"+
							"<label for='menu-name'>Name</label>"+
							"<input type='text' class='form-control required' id='menu-name' placeholder='Enter name' name='name' >"+
						"</div>"+
						"<div class='form-group'>"+
							"<label for='exampleInputTitle'>Module</label>"+
							"<input type='text' class='form-control required' id='menu-module' placeholder='Enter module' name='module' >"+
						"</div>"+
						"<div class='form-group'>"+
							"<label for='exampleInputTitle'>Url</label>"+
							"<input type='text' class='form-control required' id='menu-url' placeholder='Enter url' name='url' >"+
						"</div>"+
						"<div class='form-group'>"+
							"<label for='exampleInputDescribe'>Intro</label>"+
							"<textarea class='form-control required' rows='3' id='menu-intro' placeholder='Enter intro' name='intro' ></textarea>"+
						"</div>";
			
			//添加一级菜单
			$("#addRootMenu").click(function() {
				$('#menu-input-modal').modal();
				//填充表单内容
				$("#menu-input-form-content").html(menuInputFormContent);
				$("#menu-isRoot").val("true");
				
				$('#menu-input-modal').find(".modal-title").html("添加一级菜单");
				//清空表单内容
				//$('#menu-input-form').clearForm();
				
			});
			
			//添加二级菜单
			$("#addSecondMenu").click(function() {
				var node = getSelectNode();
				if(node==undefined || node.root!=true){
					showWarn("请选择一级菜单","left");
				}else{
					$('#menu-input-modal').find(".modal-title").html("添加二级菜单");
					$('#menu-input-modal').modal();
					//填充表单内容
					$("#menu-input-form-content").html(menuInputFormContent);
					$("#menu-isRoot").val("false");
					$("#menu-parentId").val(node.id);
				}
			});
			
			//修改菜单
			$("#modifyMenu").click(function(){
				var node = getSelectNode();
				if(node==undefined){
					showWarn("请选择菜单","left");
				}else{
					$('#menu-input-modal').find(".modal-title").html("修改菜单");
					$('#menu-input-modal').modal();
					$("#menu-input-form-content").html(menuInputFormContent);
					
					$("#menu-id").val(node.id);
					$("#menu-name").val(node.name);
					$("#menu-module").val(node.module);
					$("#menu-url").val(node.url2);
					$("#menu-intro").val(node.intro);
					$("#menu-isRoot").val(node.root);
					$("#menu-parentId").val(node.parentId);

				}
			});	
			
			//删除菜单
			$("#deleteMenu").click(function(){
				var node = getSelectNode();
				if(node==undefined){
					showWarn("请选择菜单","left");
				}else{
					//确定删除的提示
					var url= "${root}/security/menu/delete/"+node.id;
					setDeleteModal().bind('click',function(){
						$.ajax({
							url:url,
							type:"POST",
							dateType:"json",
							success:function(msg){
								showMessage(msg,"left");
								initZtree();//初始化菜单ztree
							}
						});
					});
				}
			});			
			
			//保存菜单
			$('#menu-input-modal-button-save').click(function(){
				if($("#menu-input-form").valid()){
					
					var id = $("#menu-id").val();
					var name = $("#menu-name").val();
					var module = $("#menu-module").val();
					var url = $("#menu-url").val();
					var intro = $("#menu-intro").val();
					var root = $("#menu-isRoot").val();
					var parentId = $("#menu-parentId").val();
					
					$.ajax({
						url:"${root}/security/menu/save?name="+name+"&module="+module+"&url="+url+"&intro="+intro+"&root="+root+"&id="+id+"&parentId="+parentId,
						type:"POST",
						dateType:"json",
						success:function(msg){
							showMessage(msg,"left");
							initZtree();//初始化菜单ztree
						},
						error:function(){
							showError("添加失败","left");	
						}
					});
					$('#menu-input-modal').modal('toggle')
					
				}
			});			
			
			//快捷增加授权
			$("#quickAddPermissionPreview").click(function(){
				var node = getSelectNode();
				if(node==undefined){
					showWarn("请选择菜单","right");
				}else{
					var title = "快捷授权预览";
					var htmlContent = "";
					var menuId = node.id;
					
					//获取预览信息
					$.ajax({
						url:"${root}/security/permission/quickAddPreview/"+menuId,
						type:"POST",
						dateType:"json",
						success:function(data){
							var result = eval("(" + data + ")");
							var listLength = result.length;
							
							htmlContent +=  
							"<form action='' id='permission-quick-input-form' role='form' method='post'>"+
			                  "<table class='table table-bordered table-hover '>"+
			                    "<tr>"+
			                      "<th>名称</th>"+
			                      "<th>Url</th>"+
			                    "</tr>";
			                    
								$.each(result, function(i, item) {
									var tr = "<tr>"+
												"<td>"+
													"<input id='permission-quick-input-name"+i+"' name='permission-quick-input-name"+i+"' value="+item.name+" type='text' class='form-control required'/>"+
												"</td>"+
												"<td>"+
													"<input id='permission-quick-input-url"+i+"' name='permission-quick-input-url"+i+"' value="+item.url+" type='text' class='form-control required'/>"+
												"</td>"+
											"</tr>";
									htmlContent += tr;
								});
								
							htmlContent += "</table></form>";
							
							//modal展现预览信息，并且绑定确定添加事件
							setGeneralModal(title,htmlContent,"","确定添加","permission-quick-input-form").bind('click',function(){
								//获取name和url
								var  allNameAndUrl = "";	
	 							for (var i=0;i<5;i++){
	 								var name = $("#permission-quick-input-name"+i).val();
									var url = $("#permission-quick-input-url"+i).val();
									var nameAndUrl = name+":"+url;
									
									allNameAndUrl+=nameAndUrl;
									allNameAndUrl+=",";
								};
								
								if($("#permission-quick-input-form").valid()){
									$.ajax({
										url:"${root}/security/permission/quickAddSave/"+node.id+"?allNameAndUrl="+allNameAndUrl,
										type:"POST",
										dateType:"json",
										success:function(msg){
											showMessage(msg,"right");
											
											$("#jqGrid").jqGrid('setGridParam', {
												url : '${root}/security/permission/json/'+node.id+'/'+node.root
											}).trigger("reloadGrid");
										}
									});
								}else{
									console.log("valid error");
								};
								
							});
							
							//添加校验规则
// 							$("#permission-quick-input-form").validate();
// 							for (var i=0;i<5;i++){
// 	 							$("#permission-quick-input-name"+i).rules("add", { required: true });
// 								$("#permission-quick-input-url"+i).rules("add", { required: true});
// 							}

						},
						error:function(){
							showError("获取预览信息失败","right");	
						}
					});
				}
			});
			
			var permissionInputFormContent = ""+
			  "<div class='form-group'>"+
			   " <label for='exampleInputEmail1'>Name</label>"+
			   " <input type='text' class='form-control required' id='permission-name' name='name' placeholder='Enter name' value=''>"+
			  "</div>"+
			  "<div class='form-group'>"+
			  "  <label for='exampleInputEmail1'>Url</label>"+
			  "  <input type='text' class='form-control required' id='permission-url' name='url' placeholder='Enter url' value=''>"+
			  "</div>";
			  
			//添加授权
			$("#addPermission").click(function(){
				var node = getSelectNode();
				if(node==undefined){
					showWarn("请选择菜单","right");
				}else{
					$('#permission-input-modal').modal();
					$("#permission-input-form-content").html(permissionInputFormContent);
					$("#permission-menu-id").val(node.id);
					
				}
			});
			
			//保存授权
			$("#permission-input-modal-button-save").click(function(){
				if($("#permission-input-form").valid()){
					var id = $("#permission-id").val();
					var menuId = $("#permission-menu-id").val();
					var name = $("#permission-name").val();
					var url = $("#permission-url").val();
					
					$.ajax({
						url:"${root}/security/permission/save?name="+name+"&url="+url+"&menu.id="+menuId+"&id="+id,
						type:"POST",
						dateType:"json",
						success:function(msg){
							showMessage(msg,"right");
							var node = getSelectNode();
							$("#jqGrid").jqGrid('setGridParam', {
								url : '${root}/security/permission/json/'+node.id+'/'+node.root
							}).trigger("reloadGrid");
							
						},
						error:function(){
							showError("添加失败","right");	
						}
					});
					$('#permission-input-modal').modal('toggle')
				}
			});
			
			//修改授权
			$("#modifyPermission").click(function(){
				var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
				console.log("rowId:"+rowid);
				if(rowid == null){
					showWarn("请选择授权","right");
				}else{
					var data = $("#jqGrid").jqGrid('getRowData', rowid);
					$('#permission-input-modal').modal();
					$("#permission-input-form-content").html(permissionInputFormContent);
					$("#permission-id").val(data.id);
					$("#permission-menu-id").val(data.menuId);
					$("#permission-name").val(data.name);
					$("#permission-url").val(data.url);
				}

			});
			
			//删除授权
			$("#deletePermission").click(function(){
				var rowids = $("#jqGrid").jqGrid('getGridParam','selarrrow');
				var rowid = $("#jqGrid").jqGrid('getGridParam','selrow');
				console.log("rowids:"+rowids);
				if(rowids == ''){
					showWarn("请选择授权","right");
				}else{
					
					var url= "${root}/security/permission/delete/"+rowids;
					
					setDeleteModal().bind('click',function(){
						$.ajax({
							url:url,
							type:"POST",
							dateType:"json",
							success:function(msg){
								showMessage(msg,"right");
								$("#jqGrid").trigger("reloadGrid");
							},
							error:function(){
								showError("删除失败");	
							}
						});
					});
				}
			});
			
		});
		
	</script>
	
</body>
</html>