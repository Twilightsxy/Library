<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="LibraryFirstTag" prefix="library"%>

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
<title>管理员 | 书籍管理</title>
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
	if (check){
		document.getElementsById('delete').submit();
	}
}</script>
</head>

<body class="fix-header card-no-border fix-sidebar">
	<%@include file="header.jsp"%>
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">图书管理员</p>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
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
						<h3 class="text-themecolor">书籍列表</h3>
					</div>
					<h2>
						一共有
						<library:BookStatus />
						本书借出
					</h2>
				</div>


				<button class="btn btn-outline-primary" data-toggle="modal"
					data-target="#addBook">增加书籍</button>

				<br>
				<br>
				<form action="${pageContext.request.contextPath}/librarian/book/search"
					method="post">
					<select name="category" class="btn btn-outline-primary">
						<option value="15">目录</option>
						<option value="1">情感</option>
						<option value="2">家庭</option>
						<option value="3">婚姻</option>
						<option value="4">中国当代小说</option>
						<option value="5">社会</option>
						<option value="6">乡土</option>
						<option value="7">影视小说</option>
						<option value="8">军事</option>
						<option value="9">历史</option>
						<option value="10">职场</option>
						<option value="11">其他</option>
					</select> <select name="searchLimit" class="btn btn-outline-primary">
						<option value="title">题目</option>
						<option value="author">作者</option>
					</select> <input type="search" name="searchContent" value="${searchContent}"
						class="btn btn-outline-primary"> <input type="submit"
						value="搜索" class="btn btn-outline-primary">
				</form>
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
												<th>书籍编号</th>
												<th>书名</th>
												<th>作者</th>
												<th>价格</th>
												<th>出版时间</th>
												<th>出版社</th>
												<th>简介</th>
												<th>ISBN</th>
												<th>目录</th>
												<th>位置</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${books.items}" var="book">
												<tr>
													<td>${book.bookNo}</td>
													<td>${book.title}</td>
													<td>${book.author}</td>
													<td>${book.price}</td>
													<td>${book.time}</td>
													<td>${book.publish}</td>
													<td>${book.brief}</td>
													<td>${book.ISBN}</td>
													<td>${book.category}</td>
													<td>${book.location}</td>
													<c:if test="${book.status==0}">
														<td><button class="btn btn-outline-primary"
																style="background-color: green;">可借</button></td>
													</c:if>
													<c:if test="${book.status==1}">
														<td><button class="btn btn-outline-primary"
																style="background-color: yellow;">待审核</button></td>
													</c:if>
													<c:if test="${book.status==2}">
														<td><button class="btn btn-outline-primary"
																style="background-color: red;">已借出</button></td>
													</c:if>
													<td>
														<button class="btn btn-outline-primary"
															data-toggle="modal" data-target="#changeBook">修改</button>
															<form action="${pageContext.request.contextPath}/librarian/book/delete/${book.bookNo}" id="delete">
															<button class="btn btn-outline-warning"
																data-toggle="modal" onclick="tanchuang()">删除</button></form>
													 <!--弹出窗口 修改书籍-->
														<div class="modal fade" id="changeBook" role="dialog"
															aria-labelledby="gridSystemModalLabel">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title" id="gridSystemModalLabel">修改书籍</h4>
																	</div>
																	<div class="modal-body">
																		<div class="container-fluid">
																			<form method="post"
																				action="${pageContext.request.contextPath}/librarian/book/change">
																				<div class="form-group ">
																					<label for="bName" class="col-xs-3 control-label">书籍编号</label>
																					<div class="col-xs-8 ">
																						<input type="text"
																							class="form-control input-sm duiqi" name="bookNo"
																							value="${book.bookNo}" readonly>

																					</div>
																				</div>
																				<div class="form-group">
																					<label for="text" class="col-xs-3 control-label">书名</label>
																					<div class="col-xs-8 ">
																						<input type="text"
																							class="form-control input-sm duiqi" name="title"
																							value="${book.title}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="text" class="col-xs-3 control-label">作者</label>
																					<div class="col-xs-8 ">
																						<input type="text"
																							class="form-control input-sm duiqi" name="author"
																							value="${book.author}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">价格</label>
																					<div class="col-xs-8">
																						<input type="text"
																							class="form-control input-sm duiqi" name="price"
																							value="${book.price}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">出版时间</label>
																					<div class="col-xs-8">
																						<input type="text"
																							class="form-control input-sm duiqi" name="time"
																							value="${book.time}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">出版社</label>
																					<div class="col-xs-8">
																						<input type="text"
																							class="form-control input-sm duiqi"
																							name="publish" value="${book.publish}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">ISBN</label>
																					<div class="col-xs-8">
																						<input type="text"
																							class="form-control input-sm duiqi" name="ISBN"
																							value="${book.ISBN}">
																					</div>
																				</div>

																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">简介</label>
																					<div class="col-xs-8">
																						<input type="text"
																							class="form-control input-sm duiqi" name="brief"
																							value="${book.brief}">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="sOrd" class="col-xs-3 control-label">位置</label>
																					<div class="col-xs-6">
																						<input type="text"
																							class="form-control input-sm duiqi"
																							name="location" value="${book.location}">
																					</div>
																				</div>

																				<div class="modal-footer">
																					<button type="button"
																						class="btn btn-xs btn-xs btn-white"
																						data-dismiss="modal">取消</button>
																					<button type="submit"
																						class="btn btn-xs btn-xs btn-green">保存</button>
																					<!-- 点击保存后数据要保存到数据库中，待实现-->
																				</div>
																			</form>
																		</div>
																	</div>


																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div align="center">
										每页${books.pageSize}本书，
										第${books.currentPageNo}/${books.totalPageCount}页,共${books.totalCount}本书
										<c:if test="${books.previousPage}">
											<a
												href="<c:url value="/librarian/book?pageNo=${books.currentPageNo-1}" />">上一页</a>
										</c:if>
										<c:if test="${books.nextPage}">
											<a
												href="<c:url value="/librarian/book?pageNo=${books.currentPageNo+1}" />">下一页</a>
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


	<!--弹出窗口 添加书籍-->
	<div class="modal fade" id="addBook" role="dialog"
		aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">增加书籍</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<form name="tijiao" method="post"
							action="${pageContext.request.contextPath}/librarian/book/add">

							<div class="form-group">
								<label for="text" class="col-xs-3 control-label">书名</label>
								<div class="col-xs-8 ">
									<input type="text" class="form-control input-sm duiqi"
										name="title">
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="col-xs-3 control-label">作者</label>
								<div class="col-xs-8 ">
									<input type="text" class="form-control input-sm duiqi"
										name="author">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">价格</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="price">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">出版时间</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="time">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">出版社</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="publish">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">ISBN</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="ISBN">
								</div>
							</div>

							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">简介</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="brief">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">位置</label>
								<div class="col-xs-6">
									<input type="text" class="form-control input-sm duiqi"
										name="location">
								</div>
							</div>
							<div class="form-group">
								<label for="sOrd" class="col-xs-3 control-label">数量</label>
								<div class="col-xs-8">
									<input type="text" class="form-control input-sm duiqi"
										name="number" value="1">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-xs btn-xs btn-white"
									data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-xs btn-xs btn-green">保存</button>
								<!-- 点击保存后数据要保存到数据库中，待实现-->
							</div>
						</form>
					</div>
				</div>


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
