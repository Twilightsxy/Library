<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<header class="topbar">
		<nav class="navbar top-navbar navbar-expand-md navbar-light">
			<!-- ============================================================== -->
			<!-- Logo -->
			<!-- ============================================================== -->
			<div class="navbar-header">
				<b></b><span> <img
					src="${pageContext.request.contextPath}/resources/images/校徽.jpg"
					alt="homepage" class="dark-logo" height="50" width="50" />

				</span>
			</div>
			<!-- ============================================================== -->
			<!-- End Logo -->
			
			<!-- ============================================================== -->
			<div class="navbar-collapse">
				<!-- ============================================================== -->
				<!-- toggle and nav items -->
				<!-- ============================================================== -->
				<h2>欢迎您，管理员${sessionScope.librarian.lName}！</h2>
				
			</div>
		</nav>
	</header>
</body>
</html>