<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
</head>
<body>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            <small>菜单管理</small>
          </h1>
          <ol class="breadcrumb" style="float: left;">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Mailbox</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-3">
            	<a  id="addMenu" class="btn btn-primary btn-block margin-bottom">增加一级菜单</a>
              	<p></p>
				<div class="box box-solid">
					<div class="box-header with-border"><h3 class="box-title">Labels</h3></div>
					<div class="box-body">
						<div id="menu-tree" class="ztree"></div>
					</div>
					<div class="box-footer clearfix"></div>
				</div>
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title">Labels</h3>
                  <div class="box-tools">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div>
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                    <li><a href="#"><i class="fa fa-circle-o text-red"></i> Important</a></li>
                    <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> Promotions</a></li>
                    <li><a href="#"><i class="fa fa-circle-o text-light-blue"></i> Social</a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
            <div class="col-md-9">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Inbox</h3>
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
                      </div><!-- /.btn-group -->
                    </div><!-- /.pull-right -->
                  </div>
                  <div class="table-responsive mailbox-messages">
                    
                  </div><!-- /.mail-box-messages -->
                </div><!-- /.box-body -->
                <div class="box-footer no-padding">
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
                      </div><!-- /.btn-group -->
                    </div><!-- /.pull-right -->
                  </div>
                </div>
              </div><!-- /. box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

	<div class="modal fade" id="inputMenu">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">增加菜单</h4>
				</div>
				<div class="modal-body">
					<form id="input-form" role="form" action="${root}/console/security/resource/save" method="post">
						<input type="hidden" id="id" name="id" value="">
						<input type="hidden" id="parentId" name="parentId" value="">
						<input type="hidden" id="isRoot" name="root" value="true">
						<div class="form-group">
							<label for="exampleInputTitle">Name</label>
							<input type="text" class="form-control required" id="name" placeholder="Enter name" name="name" >
						</div>
						<div class="form-group">
							<label for="exampleInputTitle">Module</label>
							<input type="text" class="form-control required" id="module" placeholder="Enter module" name="module" >
						</div>
						<div class="form-group">
							<label for="exampleInputTitle">Url</label>
							<input type="text" class="form-control required" id="url" placeholder="Enter url" name="url" >
						</div>
						<div class="form-group">
							<label for="exampleInputDescribe">Intro</label>
							<textarea class="form-control required" rows="3" id="intro" placeholder="Enter intro" name="intro" ></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

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
		
		//点击节点事件
		function clickNode(event, treeId, treeNode){
			console.log("event:"+event);
			console.log("treeId:"+treeId);
			console.log("treeNode:"+treeNode);
			console.log("treeNode name:"+treeNode.name);
			//加载资源信息
			if(treeNode.root==true){
				
			}else{
				
			}
	
		};
		
		$(document).ready(function() {
			
			initZtree();//初始化ztree
	
			$("#addMenu").click(function() {
				$('#inputMenu').modal();
				
			// $('#inputMenu').modal('toggle');
			});
		});
	</script>
	
</body>
</html>