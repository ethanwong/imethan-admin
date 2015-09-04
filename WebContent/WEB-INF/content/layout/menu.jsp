<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar ">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar ">
    <!-- Sidebar user panel -->
<!--     <div class="user-panel"> -->
<!--       <div class="pull-left image"> -->
<%--         <img src="${root}/theme/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image"> --%>
<!--       </div> -->
<!--       <div class="pull-left info"> -->
<!--         <p>Alexander Pierce</p> -->
<!--         <a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
<!--       </div> -->
<!--     </div> -->
    <!-- search form -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="Search...">
        <span class="input-group-btn">
          <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
        </span>
      </div>
    </form>
    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu ">
      <li class="header">MAIN NAVIGATION</li>
      <li class="">
        <a href="${root}/console">
          <i class="fa fa-dashboard"></i> <span>用户首页</span>
        </a>
      </li>
      
<!--       <li class="treeview"> -->
<!--         <a href="#"> -->
<!--           <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i> -->
<!--         </a> -->
<!--         <ul class="treeview-menu"> -->
<!--           <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li> -->
<!--           <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li> -->
<!--         </ul> -->
<!--       </li> -->
      <li class="treeview active">
        <a href="#">
          <i class="fa fa-users"></i>
          <span>权限管理</span><b class="arrow pull-right fa fa-angle-left"></b>
        </a>
        <ul class="treeview-menu">
          <li class="active"><a href="${root}/security/user"><i class="fa fa-circle-o"></i> 用户管理</a></li>
          <li><a href="${root}/security/role"><i class="fa fa-circle-o"></i> 角色管理</a></li>
          <li><a href="${root}/security/menu"><i class="fa fa-circle-o"></i> 菜单管理</a></li>
          <li><a href="${root}/security/permission"><i class="fa fa-circle-o"></i> 授权管理</a></li>
        </ul>
      </li>
      
      <li class="treeview">
        <a href="#">
          <i class="fa fa-folder"></i>
          <span>基础页面</span><b class="arrow pull-right fa fa-angle-left"></b>
        </a>
        <ul class="treeview-menu">
          <li><a href="${root}/theme/"><i class="fa fa-circle-o"></i> 404错误</a></li>
          <li><a href="${root}/theme/"><i class="fa fa-circle-o"></i> 500错误</a></li>
        </ul>
      </li>

<!--       <li class="header">LABELS</li> -->
<!--       <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li> -->
<!--       <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li> -->
<!--       <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li> -->
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>