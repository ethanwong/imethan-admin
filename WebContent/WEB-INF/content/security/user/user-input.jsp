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
					添加用户 <span id="showmessage"></span>
				</div>
				<div class="box-body">
					<security:authorize ifAnyGranted="更新用户">
						<form id="input-form" action="" class="form-horizontal" method="post" role='form' >
							<input type="hidden" id="id" name="id" value="${entity.id}"/>
							<div class="form-group">
								<label for="nickname" class="col-sm-2 control-label">昵称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control required" id="nickname" name="nickname" placeholder="输入昵称" value="${entity.nickname}">
								</div>
							</div>
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">账号</label>
								<div class="col-sm-4">
									<input type="text" class="form-control required" id="username" name="username" placeholder="输入账号" value="${entity.username}">
								</div>
							</div>
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-4">
									<input type="email" class="form-control required" id="email" name="email" placeholder="输入email" value="${entity.email}">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="password" class="form-control required" name="password" id="password" placeholder="输入密码">
								</div>
							</div>
							<div class="form-group">
								<label for="password2" class="col-sm-2 control-label">确认密码</label>
								<div class="col-sm-4">
									<input type="password" class="form-control required" name="password2" id="password2" placeholder="确认密码">
								</div>
							</div>
							<div class="form-group">
								<label for="roleId" class="col-sm-2 control-label">角色</label>
								<div class=" col-sm-4">
									<select id="roleId" name="roleId" class='form-control required'>
										<c:forEach items="${roles}" var="role">
											<option value="${role.id}" <c:if test="${role.id eq entity.roleIdForInput}">selected="selected"</c:if>>${role.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-8">
									<a type="button" href="${root}/security/user" class="btn btn-flat btn-default ">返回</a>
									<button type="button" class="btn btn-flat btn-info saveUser">确认</button>
								</div>
							</div>
						</form>
					</security:authorize>
				</div>
				<div class="box-footer"></div>
			</div>
		</section>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			
			//添加校验
			$("#input-form").validate({
				rules: {
				 username:{required:true,rangelength:[3,10],
				    remote:{
				           type:"POST",
				           url:"${root}/security/user/isExistsName",
				           data:{
				             	id:function(){return $("#id").val();},
				        	    username:function(){return $("#username").val();}
				           } 
				          } 
				      },
				  password2:{
					  required:true,
					  equalTo:'#password'
				  }
				},
				messages: {
					username:{
					 required:"账号不能为空！",
					 rangelength:"账号名位数必须在3到10字符之间！",
					 remote:"账号名已经存在！"
					},
					password2:{
						equalTo:"密码不一致"
					}
				}
			});
			
			
			$(".saveUser").click(function(){
				if($("#input-form").valid()){
					var id = $("#id").val();
					var nickname = $("#nickname").val();
					var email = $("#email").val();
					var username = $("#username").val();
					var password =  CryptoJS.MD5($("#password").val());
					var roleId = $("#roleId").val();
					
					$.ajax({
						url:"${root}/security/user/save?roleId="+roleId+"&id="+id+"&username="+username+"&password="+password+"&nickname="+nickname+"&email="+email,
						type:"POST",
						dateType:"json",
						success:function(msg){
							showMessage(msg,"");
							if(eval("(" + msg + ")").success){
								setTimeout(function(){
									window.location.href="${root}/security/user"; 
								}, 3000);
							}
						}
					});
				}
			});

		})
	</script>
</body>
</html>