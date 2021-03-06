<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ImEthan Admin</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${root}/theme/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${root}/theme/other/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${root}/theme/other/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${root}/theme/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${root}/theme/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${root}/theme/plugins/cryptojs/crypto-js-md5.js"></script>
    <script type="text/javascript">
	function login(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username == '' && password == ''){
			$(".login-box-msg").html("请输入账号和密码");
			return false;
		}else if(username == ''){
			$(".login-box-msg").html("请输入账号");
			return false;
		}else if(password == ''){
			$(".login-box-msg").html("请输入密码");
			return false;
		}else{
			var password = $('#password').val();
			$('#password').val(CryptoJS.MD5(password));
			return true;
		}
	}
	</script>
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="${root}/login"><b>ImEthan</b>Admin</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">
        	<security:authorize access="isRememberMe()">嘿，你记住我了</security:authorize>
	        <c:if test="${param.error != null}">
				Invalid username and password.
			</c:if>
			<c:if test="${param.logout != null}">
				You have been logged out.
			</c:if>
			<c:if test="${param.error == null && param.logout == null}">
				Sign in to start your session
			</c:if>
        </p>
        <form action="${root}/securitylogin" method="post" id="inputForm"  onsubmit="return login();"> 
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
          <div class="form-group has-feedback">
            <input id="username" type="text" class="form-control required" placeholder="Username" name="username" value="imethan">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input id="password" type="password" class="form-control required" placeholder="Password" name="password" value="123456">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                	<input type="checkbox" id="remember-me" name="remember-me" value="true"/>
                	Remember Me
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>

<!--         <div class="social-auth-links text-center"> -->
<!--           <p>- OR -</p> -->
<!--           <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a> -->
<!--           <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a> -->
<!--         </div>/.social-auth-links -->

<!--         <a href="#">I forgot my password</a><br> -->
<!--         <a href="register.html" class="text-center">Register a new membership</a> -->

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="${root}/theme/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${root}/theme/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${root}/theme/plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>