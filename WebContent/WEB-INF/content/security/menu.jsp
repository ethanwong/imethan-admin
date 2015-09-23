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
			<small><i class="fa fa-home"></i> 权限配置 > 菜单管理</small>
			<span style="padding-left: 20px;" id="warn-message"></span>
		</section>
		<div id="showmessage"></div>        
        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-4">
            	<a id="addRootMenu" class="btn btn-primary margin-bottom">增加一级</a>
            	<a id="addSecondMenu" class="btn btn-primary margin-bottom">增加二级</a>
            	<a id="modifyMenu" class="btn btn-primary margin-bottom">修改</a>
            	<a id="deleteMenu" class="btn btn-danger margin-bottom">删除</a>
				<div class="box box-solid">
					<div class="box-header with-border"><h3 class="box-title">菜单列表</h3></div>
					<div class="box-body">
						<div id="menu-tree" class="ztree"></div>
					</div>
					<div class="box-footer clearfix"></div>
				</div>
            </div><!-- /.col -->
            <div class="col-md-8">
            	<a id="addRootMenu" class="btn btn-info margin-bottom">一键授权</a>
            	<a id="addRootMenu" class="btn btn-info margin-bottom">添加授权</a>
            	<a id="addRootMenu" class="btn btn-info margin-bottom">修改</a>
            	<a id="addRootMenu" class="btn btn-danger margin-bottom">删除</a>
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">菜单授权</h3>
                  <div class="box-tools pull-right">
                    <div class="has-feedback">
                      <input type="text" class="form-control input-sm" placeholder="Search Mail">
                      <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                  </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <div class="mailbox-controls">
                    <!-- Check all button -->
                    <button class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>
                    <div class="btn-group">
                      <button class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
                      <button class="btn btn-default btn-sm"><i class="fa fa-reply"></i></button>
                      <button class="btn btn-default btn-sm"><i class="fa fa-share"></i></button>
                    </div><!-- /.btn-group -->
                    <button class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                    <div class="pull-right">
                      1-50/200
                      <div class="btn-group">
                        <button class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
                        <button class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
                      </div>
                    </div>
                  </div>
                  <div class="table-responsive mailbox-messages">
                    
                  </div>
                </div>
                <div class="box-footer no-padding">
					
                </div>
              </div><!-- /. box -->
            </div><!-- /.col -->

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
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="menu-input-modal-button-save" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加菜单modal结束 -->

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
			console.log("event:"+event);
			console.log("treeId:"+treeId);
			console.log("treeNode:"+treeNode);
			console.log("treeNode name:"+treeNode.name);
			console.log("treeNode id:"+treeNode.id);
			
			//加载资源信息
			if(treeNode.root==true){
				
			}else{
				
			}
		};
		
		//获取选中节点
		function getSelectNode(){
			var treeObj = $.fn.zTree.getZTreeObj("menu-tree");
			var nodes = treeObj.getSelectedNodes();
			var node = nodes[0];
			return node;
		};
		
		$(document).ready(function() {
			
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
					showWarn("请选择一级菜单");
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
					showWarn("请选择菜单");
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
					showWarn("请选择菜单");
				}else{
					//确定删除的提示
					var url= "${root}/security/menu/delete/"+node.id;
					$('#deleteConfirmModal').modal();
					$("#deleteConfirmModalClick").click(function(){
						$.ajax({
							url:url,
							type:"POST",
							dateType:"json",
							success:function(data){
								var result = eval("(" + data + ")");
								initZtree();//初始化菜单ztree
								showWarn("删除成功");
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
							showWarn("添加成功");	
							initZtree();//初始化菜单ztree
						},
						error:function(){
							showError("添加失败");	
						}
					});
				}
			});
		});
	</script>
	
</body>
</html>