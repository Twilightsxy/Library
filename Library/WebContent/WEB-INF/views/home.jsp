<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
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
</head>
<body style="overflow: auto !important;">
	<div class="main-w3layouts" id="home">
		<!-- 导航栏 -->
		<%@include file="navbar.jsp"%>
		<!-- 导航栏 -->
	</div>
	<!--main-content-->

	<!-- 服务 -->
	<%@include file="services.jsp"%>
	<!-- 服务 -->

	<!-- 底部 -->
	<%@include file="bottom.jsp"%>
	<!-- //footer -->

	<!-- js -->

	<!--light-box-files -->
	<script>
		$(function() {
			$('.gallery-grid a').Chocolat();
		});
	</script>
	<!-- required-js-files-->

	<script>
		$(document).ready(function() {
			$("#owl-demo").owlCarousel({
				items : 1,
				lazyLoad : true,
				autoPlay : true,
				navigation : false,
				navigationText : false,
				pagination : true,
			});
		});
	</script>
	<!--//required-js-files-->



	<!-- start-smoth-scrolling -->

	<!-- bottom-top -->
	<!-- smooth scrolling -->
	<script type="text/javascript">
		$(document).ready(function() {
			$().UItoTop({
				easingType : 'easeOutQuart'
			});
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span
		id="toTopHover" style="opacity: 1;"> </span></a>


	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".aevents").click(function(event) {
				document.getElementById("post").style.display = 'none';
				document.getElementById("services").style.display = 'none';
				document.getElementById("team").style.display = 'none';
				$('html,body').animate({
					scrollTop : 0
				});
			});
			$(".aservices").click(function(event) {
				document.getElementById("services").style.display = 'block';
				document.getElementById("post").style.display = 'none';
				document.getElementById("team").style.display = 'none';
				$('html,body').animate({
					scrollTop : 0
				});
			});
			$(".aabout").click(function(event) {
				document.getElementById("services").style.display = 'none';
				document.getElementById("post").style.display = 'none';
				document.getElementById("team").style.display = 'none';
				$('html,body').animate({
					scrollTop : 0
				});
			});
			$(".ateam").click(function(event) {
				document.getElementById("team").style.display = 'block';
				document.getElementById("services").style.display = 'none';
				document.getElementById("post").style.display = 'none';
				$('html,body').animate({
					scrollTop : 0
				});
			});
			$(".ahome").click(function(event) {
				document.getElementById("post").style.display = 'block';
				document.getElementById("services").style.display = 'block';
				document.getElementById("team").style.display = 'block';
				$('html,body').animate({
					scrollTop : 0
				});
			});
		});
	</script>

</body>
</html>