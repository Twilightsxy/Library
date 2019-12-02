<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/校徽.jpg" />
<title>图书管理员 | 登录</title>


<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>

<!--此处使用的是bootstrap1.min.css，（注意一）并不是bootstrap.min.css-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap1.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login_style2.css">


<!-- js文件 -->
<script src="${pageContext.request.contextPath}/resources/js/modernizr-2.6.2.min.js"></script>
<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</head>
<body class="style-3">
	<div style="background-color: #AD0205; height: 100px; ">
		<div style="padding: 10px 0;">
			<h2 align="center" style="color: white;">图书管理员</h2>
		</div>
    </div>
	
	<div class="container">
		
		<div class="row">
			<div class="col-md-5 col-md-push-4">


				<!-- 登录表单 -->
				<form class="fh5co-form animate-box"
					data-animate-effect="fadeInRight" method="post"
					onsubmit="return check()" action="${pageContext.request.contextPath}/login/librarian">
					<h2>管理员登录</h2>

					<c:choose>
						<c:when test="${!allTrue}">
							<div class="form-group">
								<label for="username" class="sr-only">用户名</label> <input
									type="text" class="form-control " id="username" name="username"
									required
									oninvalid="setCustomValidity('Please fill in this field!')"
									oninput="setCustomValidity('')" placeholder="用户名">
							</div>
							<div class="form-group">
								<label for="password" class="sr-only">密码</label> <input
									type="password" class="form-control" id="password" required
									oninvalid="setCustomValidity('请填写此字段')"
									oninput="setCustomValidity('')" name="password"
									placeholder="密码" autocomplete="off">
							</div>
						</c:when>
						<c:when test="${rNoError}">
							<div class="form-group">
								<label for="username" class="sr-only">用户名</label> <input
									type="text" class="form-control red " id="username"
									name="username" required
									oninvalid="setCustomValidity('Please fill in this field!')"
									oninput="setCustomValidity('')" placeholder="用户名错误！">
							</div>
							<div class="form-group">
								<label for="password" class="sr-only">密码</label> <input
									type="password" class="form-control red " id="password"
									required="required" name="password" placeholder="密码" required
									oninvalid="setCustomValidity('Please fill in this field!')"
									oninput="setCustomValidity('')" autocomplete="off">
							</div>
						</c:when>
						<c:when test="${rPassError}">
							<div class="form-group">
								<label for="username" class="sr-only">用户名</label> <input
									type="text" class="form-control red " id="username"
									name="username" required
									oninvalid="setCustomValidity('Please fill in this field!')"
									oninput="setCustomValidity('')" placeholder="用户名">
							</div>
							<div class="form-group">
								<label for="password" class="sr-only">密码</label> <input
									type="password" class="form-control red " id="password"
									required
									oninvalid="setCustomValidity('请填写此字段！')"
									oninput="setCustomValidity('')" name="password"
									placeholder="密码错误！" autocomplete="off">
							</div>
						</c:when>
					</c:choose>
					
					<div class="form-group">
						<input id="submit" type="submit" value="登录"
							class="btn btn-primary" onclick="setMyText()">
					</div>
				</form>


			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="row" style="padding-top: 60px; clear: both;">
			<div class="col-md-12 text-center">
				<p>
					&copy; 技术支持 <a href="#" target="_blank"> 图书馆小分队 </a>
					
				</p>
			</div>
		</div>
	</div>

	

</body>
</html>
