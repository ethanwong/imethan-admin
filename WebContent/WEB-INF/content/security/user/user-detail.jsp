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
					用户详情
					<span id="showmessage"></span>   
				</div>
                <div class="box-body form-horizontal">
                	<security:authorize ifAnyGranted="查看用户">
						<div class="form-group">
							<div class="col-sm-2 columnlable">昵称：</div>
							<div class="col-sm-10 columncontent">${entity.nickname}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 columnlable">账号：</div>
							<div class="col-sm-10 columncontent">${entity.username}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 columnlable">角色：</div>
							<div class="col-sm-10 columncontent">${entity.roleName}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 columnlable">Email：</div>
							<div class="col-sm-10 columncontent">${entity.email}</div>
						</div>
					</security:authorize>
				</div>
			</div>
		</section>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function () {
		
	})
	</script>
</body>
</html>