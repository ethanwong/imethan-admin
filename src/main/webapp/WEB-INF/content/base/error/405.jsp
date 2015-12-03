<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>405</title>
<link href="${root}/theme/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
<link href="${root}/theme/other/css/imethan.css" rel="stylesheet" type="text/css"  />
</head>
<body>
         <div class="widget-content">
           <div class="error_ex">
             <h1>405</h1>
             <h3>Something is wrong here. Method not allowed!</h3>
             <p>Access to this page is forbidden</p>
             <a class="btn btn-warning btn-big"  href="${root}/login">Back to Home</a> </div>
         </div>
          <%@ page session="false" %>
</body>
</html>