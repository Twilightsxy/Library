<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ch">
<head>
<meta charset="UTF-8" />
<title>导航栏</title>

</head>
<body>
	<div>
		<!--头部-->
		<div class="head">
			<div class="logo">
				<img src="${pageContext.request.contextPath}/resources/images/校徽.jpg" width="60" height="60">
			</div>
			<div class="head-nav">
				<div class="head-nav-con clearFloat">
					<ul>
						<c:choose>
							<c:when test="${sessionScope.username == null}">
							
							<li style="width:60px;margin-right: 30px;margin-left: 30px"></li>
								<li style="width:60px;margin-right: 30px;margin-left: 30px"><a href="${pageContext.request.contextPath}/home"
									class="hvr-underline-from-center apost">主页</a></li>
								<li style="width:110px;margin-right: 30px;margin-left: 30px"><a href="${pageContext.request.contextPath}/404"
									class="hvr-underline-from-center ateam">开发团队</a></li>
								<li style="width:70px;margin-right: 30px;margin-left: 30px"><a href="${pageContext.request.contextPath}/login/reader"
									class="hvr-underline-from-center " >登录</a></li>
							</c:when>
							<c:when test="${sessionScope.username != null}">
								<li style="width:110px"><a href="index.jsp#find"
									class="hvr-underline-from-center aservices"></a></li>
									<li style="width:110px"><a href="index.jsp#find"
									class="hvr-underline-from-center aservices"></a></li>
									<li style="width:110px"><a href="index.jsp#find"
									class="hvr-underline-from-center aservices"></a></li>
								<li style="width:70px"><a href="${pageContext.request.contextPath}/home"
									class="hvr-underline-from-center ahome">主页</a></li>
								
								<li style="width:110px"><a href="${pageContext.request.contextPath}/reader/cart"
									class="hvr-underline-from-center ateam">借阅车</a></li>
								<li style="width:220px"><a href="${pageContext.request.contextPath}/reader/personInfo"
									class="hvr-underline-from-center " target="_blank">借阅情况</a></li>
								<form action="logout" name="tuichuForm"></form>
								<li style="width:80px"><a href="${pageContext.request.contextPath}/logout"
									class="hvr-underline-from-center " data-toggle="modal"
									data-target="#logout">注销</a></li>
							</c:when>
						</c:choose>

					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="logout" role="dialog"
			aria-labelledby="gridSystemModalLabel" aria-hidden="true" style="margin-top: 100px;">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="gridSystemModalLabel">注销</h4>
					</div>
						<div class="modal-body">
							<div class="container-fluid">你确定要注销吗？</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-xs btn-xs btn-white"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-xs btn-xs btn-green"
								value="Confirm" onclick="tc()" data-toggle="modal" data-target="#hasLogout">确定</button>
							<!-- 点击保存后数据要保存到数据库中，待实现-->
						</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="hasLogout" role="dialog"
			aria-labelledby="gridSystemModalLabel" >
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
						<div class="modal-body">
							<div class="container-fluid">已退出！</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-xs btn-xs btn-white">好的</button>
							<!-- 点击保存后数据要保存到数据库中，待实现-->
						</div>
				</div>
			</div>
		</div>
</body>
<script>
	function tc() {
		$("#hasLogout").modal("show");
		document.forms["tuichuForm"].submit();
	}
</script>
</html>