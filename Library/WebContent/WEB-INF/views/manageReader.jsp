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
<script type="text/javascript">
	function tanchuang() {
		var check = alert("确定删除？");
		if (check) {
			document.getElementsById('delete').submit();
		}
	}
</script>
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
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="row page-titles">
				<div class="col-md-5 align-self-center">
					<h3 class="text-themecolor">账户列表</h3>
				</div>


			</div>
			<button class="btn btn-outline-primary" data-toggle="modal"
				data-target="#addUser">增加用户</button>


			<br>
			<br>
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
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${readers.items}" var="reader" varStatus="a">
											<tr>
												<td>${reader.readerNo}</td>
												<td>${reader.readerName}</td>
												<td>${reader.readerPassword}</td>
												<td>${reader.email}</td>
												<td>
													<button class="btn btn-outline-primary" data-toggle="modal"
														data-target="#reviseUser${a.index}">修改</button>
													<form
														action="${pageContext.request.contextPath}/librarian/reader/delete"
														id="delete" method="get">
														<input type="hidden" name="readerNo" value="${reader.readerNo}">
														<button class="btn btn-outline-warning"
															data-toggle="modal" onclick="tanchuang()">删除</button>
													</form> <!--弹出修改用户窗口-->
													<form
														action="${pageContext.request.contextPath}/librarian/reader/change"
														method="post">
														<div class="modal fade" id="reviseUser${a.index}"
															role="dialog" aria-labelledby="gridSystemModalLabel">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title" id="gridSystemModalLabel">修改读者账户</h4>
																	</div>
																	<div class="modal-body">
																		<div class="container-fluid">

																			<div class="form-group ">
																				<label for="sName" class="col-xs-3 control-label">账户:</label>
																				<div class="col-xs-8 ">
																					<input type="text"
																						class="form-control input-sm duiqi"
																						name="readerNo" value="${reader.readerNo}"
																						readonly="readonly">
																				</div>
																			</div>

																			<div class="form-group ">
																				<label for="sName" class="col-xs-3 control-label">用户名:</label>
																				<div class="col-xs-8 ">
																					<input type="hidden" name="oldReaderNo"
																						value="${reader.readerNo}"> <input
																						type="text" class="form-control input-sm duiqi"
																						name="readerName" value="${reader.readerName}">
																				</div>
																			</div>

																			<div class="form-group">
																				<label for="sOrd" class="col-xs-3 control-label">密码:</label>
																				<div class="col-xs-8">
																					<input type="text"
																						class="form-control input-sm duiqi"
																						name="readerPassword"
																						value="${reader.readerPassword}">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="sKnot" class="col-xs-3 control-label">电子邮件:</label>
																				<div class="col-xs-8">
																					<input type="email"
																						class="form-control input-sm duiqi" name="email"
																						value="${reader.email}">
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-xs btn-white"
																			data-dismiss="modal">取消</button>
																		<button type="submit" id="lastRNo" name="lastRNo"
																			class="btn btn-xs btn-green">保存</button>
																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<div align="center">
									每页${readers.pageSize}名用户，
									第${readers.currentPageNo}/${readers.totalPageCount}页,共${readers.totalCount}名用户
									<c:if test="${readers.previousPage}">
										<a
											href="<c:url value="/librarian/reader?pageNo=${readers.currentPageNo-1}" />">上一页</a>
									</c:if>
									<c:if test="${readers.nextPage}">
										<a
											href="<c:url value="/librarian/reader?pageNo=${readers.currentPageNo+1}" />">下一页</a>
									</c:if>
								</div>
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




	<!--弹出添加用户窗口-->
	<div class="modal fade" id="addUser" role="dialog"
		aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<sf:form commandName="reader" method="POST"
					action="${pageContext.request.contextPath}/librarian/reader/add">
					<sf:errors path="*" cssClass="error" />
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="gridSystemModalLabel">添加账户</h4>
					</div>
					<div class="modal-body">
						<div class="container-fluid">

							<div class="form-group ">
								<label for="sName" class="col-xs-3 control-label">账户：</label>
								<div class="col-xs-8 ">
									<sf:input type="text" class="form-control input-sm duiqi"
										path="readerNo" id="newReaderAcc"></sf:input>
									<sf:errors path="readerNo" cssClass="error" />
								</div>
							</div>

							<div class="form-group ">
								<label for="sName" class="col-xs-3 control-label">用户名：</label>
								<div class="col-xs-8 ">
									<sf:input type="text" class="form-control input-sm duiqi"
										path="readerName" id="newReaderName"></sf:input>
									<sf:errors path="readerName" cssClass="error" />
								</div>
							</div>

							<div class="form-group ">
								<label for="sName" class="col-xs-3 control-label">密码：</label>
								<div class="col-xs-8 ">
									<sf:input type="password" class="form-control input-sm duiqi"
										path="readerPassword" id="newReaderPassword"></sf:input>
									<sf:errors path="readerPassword" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">电子邮件：</label>
								<div class="col-xs-8">
									<sf:input type="email" class="form-control input-sm duiqi"
										path="email" id="newReaderEmail"></sf:input>
									<sf:errors path="email" cssClass="error" />
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-white"
							data-dismiss="modal">取消</button>
						<input type="submit" class="btn btn-xs btn-green" value="保存" />
					</div>
				</sf:form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
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
