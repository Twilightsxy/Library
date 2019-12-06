<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人借阅情况</title>
<style type="text/css">
p {
	text-indent: 4em;
	padding: 0px;
	margin: 0px;
}

h1 {
	text-indent: 2em;
	padding: 0px;
	margin: 0px;
}

.divNew {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000
}
</style>
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/resources/images/校徽.jpg">

<link href="${pageContext.request.contextPath}/resources/css/navbar.css"
	rel="stylesheet" type="text/css" media="all">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" media="all">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" media="all">
<link
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.css"
	rel="stylesheet">

<link
	href="http://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i&subset=latin-ext"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Covered+By+Your+Grace"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/chocolat.css"
	rel="stylesheet" type="text/css" media="screen">
<!-- //online-fonts -->
<script
	src="${pageContext.request.contextPath}/resources/js/responsiveslides.min.js"></script>
<!-- start-smoth-scrolling -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/move-top.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/easing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap-3.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.chocolat.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/owl.carousel.js"></script>
</head>
<body>
	<%@include file="navbar.jsp" %>
	<br><br><br><br>
	<h1 align="center">借阅中</h1>
	<c:forEach items="${ingBooks}" var="book">
		<div class="divNew">
			<!-- 这里多写了一个链接，传当前的bookNo作为参数 -->
			<br>
			<p>图书编号： ${book.bookNo}</p>
			<p>标题： ${book.title}</p>
			<p>作者： ${book.author}</p>
			<p>应还日期： ${book.shouldReturnTime}</p>
			<br>
		</div>
	</c:forEach>


	<br>
	<br>
	<br>
	<h1 align="center">借阅历史</h1>
	<c:forEach items="${returnBooks}" var="book">
		<div class="divNew">
			<!-- 这里多写了一个链接，传当前的bookNo作为参数 -->
			<br>
			<p>图书编号： ${book.bookNo}</p>
			<p>标题： ${book.title}</p>
			<p>作者： ${book.author}</p>
			<p>归还日期日期： ${book.shouldReturnTime}</p>
			<br>
		</div>
	</c:forEach>
	<%@include file="bottom.jsp" %>
</body>
</html>