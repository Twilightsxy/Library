<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/resources/images/校徽.jpg">
<title>管理员 | 读者管理</title>
<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/admin/css/style.css"
	rel="stylesheet">
<!-- You can change the theme colors from here -->
<link
	href="${pageContext.request.contextPath}/resources/admin/css/colors/default-dark.css"
	id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header card-no-border fix-sidebar">
	<%@include file="header.jsp"%>



	<!-- ============================================================== -->
	<!-- End Topbar header -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- Left Sidebar - style you can find in sidebar.scss  -->
	<!-- ============================================================== -->
	<aside class="left-sidebar">
		<!-- Sidebar scroll-->
		<div class="scroll-sidebar">
			<!-- Sidebar navigation 导航界面-->
			<%@ include file="nav1.jsp"%>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>
	<!-- ============================================================== -->
	<!-- End Left Sidebar - style you can find in sidebar.scss  -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- Page wrapper  -->
	<!-- ============================================================== -->
	<div class="page-wrapper">
		<!-- ============================================================== -->
		<!-- Container fluid  -->
		<!-- ============================================================== -->
		<div class="container-fluid">


			<form action="${pageContext.request.contextPath}/librarian/reader/search"
				method="post">
				<input type="search" name="searchContent"
					class="btn btn-outline-primary"> <input type="submit"
					value="搜索" class="btn btn-outline-primary">
			</form>
			<br> <br>
			<!-- ============================================================== -->
			<!-- End Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Start Page Content -->
			<!-- ============================================================== -->
			<div class="row">
				<!-- column -->
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>用户名</th>
											<th>姓名</th>
											<th>密码</th>
											<th>电子邮件</th>

										</tr>
									</thead>
									<tbody>

										<tr>
											<td>${reader.readerNo}</td>
											<td>${reader.readerName}</td>
											<td>${reader.readerPassword}</td>
											<td>${reader.email}</td>



										</tr>

									</tbody>
								</table>


							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<!-- column -->
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>图书编号</th>
											<th>标题</th>

											<th>应还日期</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reutrnBooks}" var="book">
											<tr>

												<td>${book.bookNo}</td>
												<td>${book.title}</td>

												<td>${book.reserveTime}</td>



											</tr>
										</c:forEach>
									</tbody>
								</table>


							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<!-- End PAge Content -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Container fluid  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- footer -->
		<!-- ============================================================== -->
		<footer class="footer"> © 企业级小队 </footer>
		<!-- ============================================================== -->
		<!-- End footer -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Page wrapper  -->
	<!-- ============================================================== -->
	</div>





	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- slimscrollbar scrollbar JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/perfect-scrollbar.jquery.min.js"></script>
	<!--Wave Effects -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/waves.js"></script>
	<!--Menu sidebar -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/custom.min.js"></script>
</body>

</html>
