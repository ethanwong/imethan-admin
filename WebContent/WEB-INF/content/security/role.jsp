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
		<h1>
			Role <small>Control panel</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol>
		</section>
		<!-- Main content -->
		<section class="content">
		<div class="box">
			<div class="box-header with-border">header</div>
			<div class="box-body">body</div>
			<div class="box-footer clearfix"></div>
		</div>
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">Latest Orders</h3>
				<div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<div class="table-responsive">
					<table class="table no-margin">
						<thead>
							<tr>
								<th>Order ID</th>
								<th>Item</th>
								<th>Status</th>
								<th>Popularity</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="pages/examples/invoice.html">OR9842</a></td>
								<td>Call of Duty IV</td>
								<td><span class="label label-success">Shipped</span></td>
								<td><div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR1848</a></td>
								<td>Samsung Smart TV</td>
								<td><span class="label label-warning">Pending</span></td>
								<td><div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR7429</a></td>
								<td>iPhone 6 Plus</td>
								<td><span class="label label-danger">Delivered</span></td>
								<td><div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR7429</a></td>
								<td>Samsung Smart TV</td>
								<td><span class="label label-info">Processing</span></td>
								<td><div class="sparkbar" data-color="#00c0ef" data-height="20">90,80,-90,70,-61,83,63</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR1848</a></td>
								<td>Samsung Smart TV</td>
								<td><span class="label label-warning">Pending</span></td>
								<td><div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR7429</a></td>
								<td>iPhone 6 Plus</td>
								<td><span class="label label-danger">Delivered</span></td>
								<td><div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div></td>
							</tr>
							<tr>
								<td><a href="pages/examples/invoice.html">OR9842</a></td>
								<td>Call of Duty IV</td>
								<td><span class="label label-success">Shipped</span></td>
								<td><div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer clearfix">
				<a href="javascript::;" class="btn btn-sm btn-info btn-flat pull-left">Place New Order</a> <a href="javascript::;" class="btn btn-sm btn-default btn-flat pull-right">View All Orders</a>
			</div>
			<!-- /.box-footer -->
		</div>



	</section>
	</div>
</body>
</html>