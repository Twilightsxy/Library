<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="application/x-javascript">
	
	
	
		
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } 




</script>
<!--// Meta tag Keywords -->
<!-- css files -->
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
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/resources/images/校徽.jpg">
<meta charset="UTF-8">
<title>借阅车</title>
<style type="text/css">
.divNew {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000
}
p {
	text-indent: 4em;
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<br><br><br><br>
	<h1 align="center">欢迎您，读者${sessionScope.username}!</h1>

	<div align="left">
		<!-- 借阅车里的书只有两种状态，在库或者待审批 -->
		<c:forEach items="${books}" var="book">
			<div class="divNew">
				<!-- 这里显示的是加入借阅车的书 -->
				<br>
				<p>图书编号： ${book.bookNo}</p>
				<p>标题： ${book.title}</p>
				<p>作者： ${book.author}</p>
				<p>出版社： ${book.publish}</p>
				<p>简介： ${book.brief}</p>
				<c:if test="${book.status==0 }">
					<p style="color:green;">
						状态： <a
							href="${pageContext.request.contextPath}/reader/cart/reserve/${book.bookNo}">可借</a>
					</p>
				</c:if>
				<!-- 1是待审核状态 -->
				<c:if test="${book.status==1 }">
					<p style="color:yellow;">状态： 待审核</p>
				</c:if>
				<!-- 2是借出未归还 -->
				<c:if test="${book.status==2 }">
				
				<p style="color:red;">
						状态： 未归还
					</p>
					
				</c:if>
				<br>
			</div>
		</c:forEach>

	</div>
	<br>
	<br>
	<br>
	<%@include file="bottom.jsp"%>
</body>
</html>