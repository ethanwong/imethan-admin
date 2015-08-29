<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/base/taglibs.jsp"%>
<header class="main-header">
   <!-- Logo -->
   <a href="index2.html" class="logo">
     <!-- mini logo for sidebar mini 50x50 pixels -->
     <span class="logo-mini"><b>I</b>EA</span>
     <!-- logo for regular state and mobile devices -->
     <span class="logo-lg"><b>ImEthan</b>Admin</span>
   </a>
   <!-- Header Navbar: style can be found in header.less -->
   <nav class="navbar navbar-static-top" role="navigation">
     <!-- Sidebar toggle button-->
     <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
       <span class="sr-only">Toggle navigation</span>
     </a>
     <div class="navbar-custom-menu">
       <ul class="nav navbar-nav">
         <!-- Notifications: style can be found in dropdown.less -->
         
         
         <!-- User Account: style can be found in dropdown.less -->
         <li class="dropdown user user-menu">
           <a href="#" class="dropdown-toggle" data-toggle="dropdown">
             <img src="${root}/theme/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
             <span class="hidden-xs">Alexander Pierce</span>
           </a>
           <ul class="dropdown-menu">
             <!-- User image -->
             <li class="user-header">
               <img src="${root}/theme/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
               <p>
                 Alexander Pierce - Web Developer
                 <small>Member since Nov. 2012</small>
               </p>
             </li>
             <!-- Menu Body -->
             <li class="user-body">
               <div class="col-xs-4 text-center">
                 <a href="#">Followers</a>
               </div>
               <div class="col-xs-4 text-center">
                 <a href="#">Sales</a>
               </div>
               <div class="col-xs-4 text-center">
                 <a href="#">Friends</a>
               </div>
             </li>
             <!-- Menu Footer-->
             <li class="user-footer">
               <div class="pull-left">
                 <a href="#" class="btn btn-default btn-flat">Profile</a>
               </div>
               <div class="pull-right">
                 <a href="#" class="btn btn-default btn-flat">Sign out</a>
               </div>
             </li>
           </ul>
         </li>
         <!-- Control Sidebar Toggle Button -->
         <li>
           <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
         </li>
       </ul>
     </div>
   </nav>
</header>
