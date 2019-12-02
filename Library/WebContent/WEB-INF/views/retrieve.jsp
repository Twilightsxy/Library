<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">

<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/校徽.jpg" />
<title>图书馆 | 找回密码</title>

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>
<!--此处使用的是bootstrap1.min.css，（注意一）并不是bootstrap.min.css-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap1.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login_style.css">
<!-- js文件 -->
<script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
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
	<script src="${pageContext.request.contextPath}/resources/js/Untitled-2.js"></script>

</head>
<body class="style-2">
	<div id="loadingDiv" name="loadingDiv"></div>


	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<ul class="menu">
					<li><a href="<c:url value="/retrieve" />">找回密码 | </a></li>
					<li><a href="<c:url value="/home" />">图书馆主页 | </a></li>
					<li class="active"><a href="<c:url value="/loginReader" />"> 登录</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 ">

				<!-- 这里不知道Ajax怎么改，就可以改一下，直接往retrieve/sendEmail提交表单，在里面check，然后发邮件 -->
				<!-- 提交表单 -->
				<form class="fh5co-form animate-box"
					data-animate-effect="fadeInRight" name="forgotPassForm"
					id="forgotPassForm" action="${pageContext.request.contextPath}/retrieve/sendEmail" method="post">
					<h2>找回密码</h2>
					<div class="form-group">
						<label for="username" class="sr-only">用户名</label> <input
							type="text" class="form-control" id="username" name="username"
							required
							oninvalid="setCustomValidity('请填写此字段！')"
							oninput="setCustomValidity('')" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">邮箱</label> <input
							type="email" class="form-control" id="email" required
							oninvalid="setCustomValidity('请填写此字段！')"
							oninput="setCustomValidity('')" name="email" placeholder="请输入邮箱">
					</div>
					<div class="form-group">
						<input id="check" type="button" value="提交"
							onclick="checkTheInfo()" class="btn btn-primary">
					</div>
				</form>


			</div>
		</div>
		<br> <br> <br> <br> <br> <br>
		<div class="row" style="padding-top: 60px; clear: both;">
			<div class="col-md-12 text-center">
				<p>
					&copy; 技术支持 <a href="#" target="_blank"> 图书馆小分队 </a>

				</p>
			</div>
		</div>
	</div>

	
</body>
<script>
	function checkTheInfo() {
		var username = document.getElementById("username").value;
		var email = document.getElementById("email").value;
		$
				.ajax({
					type : "POST",
					async : true,
					cache : false,
					url : "ForgotPassServlet",
					contentType : "application/x-www-form-urlencoded",
					data : {
						"username" : username,
						"email" : email
					},
					traditional : true,
					beforeSend : function() {
						var html = '<div style="height: 100%;'
								+ 'opacity: 0.5;'
								+ 'position: fixed;'
								+ 'z-index:999;'
								+ 'width: 100%;" >'
								+ '<img src="images/loading.gif" width="100%" height="100%" />'
								+ '</div>';
						document.getElementById("loadingDiv").style.zindex = 999;
						$("#loadingDiv").append(html);
					},
					success : function(data) {
						$("#loadingDiv").empty();
						var objs = eval("(" + data + ")");
						var isRight = objs.allRight;
						var fit = objs.accountFitEmail;
						var type = objs.accountType;
						var code = objs.code;
						var userName = objs.userName;
						if (isRight && fit && type && code.length != 1) {
							$("#forgotPassForm").empty();
							var div = '<h2>Retrieve Password</h2>'
									+ '<div class="form-group">'
									+ '<input  id="realCode" name="realCode"' 
									+ 'type="hidden" value="'
									+ code
									+ '">'
									+ '<label for="username" class="sr-only">Verification Code</label> <input '
									+ 'type="text" class="form-control"'
									+ 'id="userCode" name="userCode"'
									+ 'required'
									+ 'oninvalid="setCustomValidity('
									+ '"Please fill in this field!"'
									+ ')"'
									+ 'oninput="setCustomValidity('
									+ '""'
									+ ')"'
									+ 'placeholder="Verification Code" >'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<input id="recoverPass" type="button" value="Submit"'
									+ 'class="btn btn-primary" onclick="checkTheCode('
									+ userName + ')">' + '</div>';
							$("#forgotPassForm").append(div);
						} else if (isRight && fit && type && code.length == 1) {
							alert("Send email successfully!");
							window.location.href = "login.jsp";

						} else if (isRight && fit == false && type == true)
							alert("email or username is wrong!");
						else if (isRight && fit && type == false)
							alert("your username does not exist!");
					},
					error : function() {
						alert("connect failed!");
					}
				});
	}
</script>
<script type="text/javascript">
	function checkTheCode(userName) {
		var realCode = document.getElementById("realCode").value;
		var userCode = document.getElementById("userCode").value;
		if (realCode == userCode) {
			$("#forgotPassForm").empty();
			var div = '<h2>Retrieve Password</h2>'
					+ '<div class="form-group">'
					+ '<input name="recoveringLibName" id="recoveringLibName"' 
						+ 'type="hidden" value="'
						+ userName
						+ '">'
					+ '<label for="username" class="sr-only">Your new password</label> <input '
					+ 'type="password" class="form-control" id="libNewPass" name="libNewPass"'
					+ 'required'
					+ 'oninvalid="setCustomValidity('
					+ '"Please fill in this field!"'
					+ ')"'
					+ 'oninput="setCustomValidity('
					+ '""'
					+ ')" placeholder="Password" >'
					+ '</div>'
					+ '<div class="form-group">'
					+ '<label for="password" class="sr-only">Input again</label> <input '
					+ 'type="password" class="form-control" id="againNewPass" required '
					+ 'oninvalid="setCustomValidity('
					+ '"Please fill in this field!"'
					+ ')"'
					+ 'oninput="setCustomValidity('
					+ '""'
					+ ')" name="againNewPass"'
					+ 'placeholder="Password" >'
					+ '</div>'
					+ '<div class="form-group">'
					+ '<input id="submitPass" type="button" value="Submit"'
					+ 'class="btn btn-primary" onclick="checkThePassAndSubmit()">'
					+ '</div>';
			$("#forgotPassForm").append(div);
		} else
			alert("The code is wrong, please try again!");
	}
</script>
<script>
	function checkThePassAndSubmit() {
		var username = document.getElementById("recoveringLibName").value;
		var fstPass = document.getElementById("libNewPass").value;
		var secPass = document.getElementById("againNewPass").value;
		if (fstPass == secPass)
			$
					.ajax({
						type : "POST",
						async : true,
						cache : false,
						url : "RecoverLibPassServlet",
						contentType : "application/x-www-form-urlencoded",
						data : {
							"recoveringLibName" : username,
							"libNewPass" : fstPass
						},
						traditional : true,
						success : function(data) {
							var objs = eval("(" + data + ")");
							if (objs.status == false)
								alert("Your new password should not be the same to your old password!");
							else {
								div = '<meta http-equiv="refresh" content=3;url=login.jsp>'
										+ '<div>'
										+ '<h2>'
										+ 'Change password successfully!<br>'
										+ 'Attention!!Page will redirect in<span id="num" style="display=inline;">3</span>seconds to login page!'
										+ '</h2>' + '</div>';
								$("#forgotPassForm").empty();
								$("#forgotPassForm").append(div);
							}
						},
						error : function() {
							alert("connect failed!");
						}
					});
		else
			alert("These two passwords are not the same!Please try again!");
	}
</script>
<script type='text/javascript'>
	var i = 3;
	function getTime() {
		document.getElementById('num').innerHTML = "<font color='red'>" + i
				+ "</font>";
		i -= 1;
		var x = setTimeout('getTime()', 1000)//1000毫秒=1秒
		if (i <= 0) {
			clearTimeout(x);
		}
	}
	window.οnlοad = getTime;//开始执行倒计时
</script>
</html>
