<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ImEthan Admin V0.0.1</title>
    <link rel="shortcut icon" href="${root}/theme/images/favicon.ico" type="image/x-icon" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${root}/theme/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${root}/theme/other/font-awesome-4.4.0/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${root}/theme/dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<%--     <link rel="stylesheet" href="${root}/theme/dist/css/skins/_all-skins.min.css"> --%>
    <link rel="stylesheet" href="${root}/theme/dist/css/skins/_all-skins.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${root}/theme/plugins/iCheck/flat/blue.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="${root}/theme/plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="${root}/theme/plugins/daterangepicker/daterangepicker-bs3.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="${root}/theme/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery 2.1.4 -->
    <script src="${root}/theme/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="${root}/theme/plugins/jQueryUI/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${root}/theme/bootstrap/js/bootstrap.min.js"></script>
  
    <!-- jQuery Knob Chart -->
    <script src="${root}/theme/plugins/knob/jquery.knob.js"></script>
    <!-- daterangepicker -->
    <script src="${root}/theme/other/js/moment.min.js"></script>
    <script src="${root}/theme/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="${root}/theme/plugins/datepicker/bootstrap-datepicker.js"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="${root}/theme/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <!-- Slimscroll -->
    <script src="${root}/theme/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${root}/theme/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="${root}/theme/dist/js/pages/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${root}/theme/dist/js/demo.js"></script>
    
    <!-- ztree begin -->
	<link rel="stylesheet" href="${root}/theme/other/jtree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${root}/theme/other/jtree/js/jquery.ztree.all-3.5.js"></script>
<%-- 	<script type="text/javascript" src="${root}/theme/other/jtree/js/jquery.ztree.core-3.5.js"></script> --%>
	<!-- ztree end -->
    
    <!-- jqgrid begin-->
    <script type="text/ecmascript" src="${root}/theme/other/jqGrid/grid.locale-cn.js"></script>
    <script type="text/ecmascript" src="${root}/theme/other/jqGrid/jquery.jqGrid.min.js"></script>
    <link href="${root}/theme/other/jqGrid/ui.jqgrid-bootstrap.css" rel="stylesheet" type="text/css"  />
	<!-- jqgrid end-->
	
    <link href="${root}/theme/other/css/imethan.css" rel="stylesheet" type="text/css"  />
    <!-- jquery-validation begin-->
	<script src="${root}/theme/other/jquery-validation-1.14.0/jquery.form.js"></script>
    <script src="${root}/theme/other/jquery-validation-1.14.0/jquery.validate.min.js"></script>
	<script src="${root}/theme/other/jquery-validation-1.14.0/jquery.metadata.js"></script>
	<script src="${root}/theme/other/jquery-validation-1.14.0/additional-methods.js"></script>
	<script src="${root}/theme/other/jquery-validation-1.14.0/messages_zh.min.js"></script>
    <!-- jquery-validation end-->
	<decorator:head></decorator:head>
</head>
  <!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
<body class="hold-transition skin-blue-light fixed">
    <div class="wrapper">
		<%@ include file="/WEB-INF/content/layout/header.jsp"%>
		<%@ include file="/WEB-INF/content/layout/menu.jsp"%>
		<decorator:body></decorator:body>
		<%@ include file="/WEB-INF/content/layout/footer.jsp"%>
	</div>
	<!-- AdminLTE App -->
	<script src="${root}/theme/dist/js/app.js"></script>
	<script src="${root}/theme/other/js/imethan.js"></script>
	
	<!-- warnConfirmModal begin -->
	<div class="modal fade" id="warnConfirmModal" tabindex="-1" role="dialog" aria-labelledby="warnConfirmModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body">
					这个是提示信息
				</div>
				<div class="modal-footer">
					<button id="warnConfirmModalClick"  type="button" class="btn btn-defaul" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>	
	<!-- warnConfirmModal end -->
</body>
</html>
