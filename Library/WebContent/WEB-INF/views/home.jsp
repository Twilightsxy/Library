<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
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
<link href="resources/css/navbar.css" rel="stylesheet" type="text/css"
	media="all">
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="all">
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" media="all">
<link href="resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" media="all">
<link href="resources/css/owl.carousel.css" rel="stylesheet">
<!-- //css files -->
<!-- online-fonts -->
<link
	href="http://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i&subset=latin-ext"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Covered+By+Your+Grace"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/chocolat.css" type="text/css"
	media="screen">
<!-- //online-fonts -->

</head>
<body style="overflow: auto !important;">
	<div class="main-w3layouts" id="home">
		<!-- 导航栏 -->
		<%@include file="navbar.jsp"%>
		<!-- 导航栏 -->
	</div>
	<!--main-content-->
	<!-- 公告 -->
	<%@include file="post.jsp"%>
	<!-- 公告 -->
	<!-- 排行榜 -->
	<!-- 排行榜 -->

	<!-- 服务 -->
	<%@include file="services.jsp"%>
	<!-- 服务 -->

	<!-- 队伍 -->
	<%@include file="team.jsp"%>
	<!-- 队伍 -->

	<!-- 底部 -->
	<%@include file="bottom.jsp"%>
	<!-- //footer -->

	<!-- js -->



	<script src="resources/js/jquery.chocolat.js"></script>

	<!--light-box-files -->
	<script>
		$(function() {
			$('.gallery-grid a').Chocolat();
		});
	</script>
	<!-- required-js-files-->


	<script src="resources/js/owl.carousel.js"></script>
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

	<script src="resources/js/responsiveslides.min.js"></script>


	<!-- start-smoth-scrolling -->
	<script type="text/javascript" src="resources/js/move-top.js"></script>
	<script type="text/javascript" src="resources/js/easing.js"></script>
	<!-- start-smoth-scrolling -->

	<!-- bottom-top -->
	<!-- smooth scrolling -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear'
				};
			 */
			$().UItoTop({
				easingType : 'easeOutQuart'
			});
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span
		id="toTopHover" style="opacity: 1;"> </span></a>
	<!-- //smooth scrolling -->
	<!--// bottom-top -->
	<script type="text/javascript" src="resources/js/bootstrap-3.1.1.min.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".apost").click(function(event) {
				document.getElementById("post").style.display = 'block';
				document.getElementById("services").style.display = 'none';
				document.getElementById("team").style.display = 'none';
				$('html,body').animate({
					scrollTop : 0
				});
			});
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