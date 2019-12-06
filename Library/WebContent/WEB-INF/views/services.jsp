<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户端的纯文本界面</title>
<style type="text/css">
p {
	text-indent: 4em;
	padding: 0px;
	margin: 0px;
}

.divNew {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000
}
</style>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1 align="center">图书查找</h1>
	<div align="center">
		<form action="${pageContext.request.contextPath}/home/bookSearch"
			method="post">
			<select name="category" style="margin-bottom: 10px;">
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
			</select> <select name="searchLimit" style="margin-bottom: 10px;">
				<option value="title">题目</option>
				<option value="author">作者</option>
			</select> <input type="search" name="searchContent" value="${searchContent}">
			<input type="submit" value="搜索">
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div align="left">
		<c:forEach items="${books.items}" var="book">
			<div class="divNew">
				<!-- 这里多写了一个链接，传当前的bookNo作为参数 -->
				<br>
				<p>图书编号： ${book.bookNo}</p>
				<p>标题： ${book.title}</p>
				<p>作者： ${book.author}</p>
				<p>出版社： ${book.publish}</p>
				<p>简介： ${book.brief}</p>
				<!-- 0是在库 -->
				<c:if test="${sessionScope.username == null}">
					<p>
						<a href="${pageContext.request.contextPath}/login/reader">请登录</a>
					</p>
				</c:if>
				<c:if test="${sessionScope.username != null}">
					<c:if test="${book.status==0 }">
						<p>状态： 在库</p>
						<p>
							<a
								href="${pageContext.request.contextPath}/reader/cart/${book.bookNo}">加入借阅车</a>
						</p>
					</c:if>
					<!-- 1是待审核 -->
					<c:if test="${book.status==1 }">
						<p>状态： 待审核</p>
					</c:if>
					<!-- 2是借出未归还 -->
					<c:if test="${book.status==2 }">
						<p>状态： 借出</p>
					</c:if>
				</c:if>
				<br>
			</div>
		</c:forEach>

	</div>
	<br>
	<br>
	<br>
	<div align="center">
		每页${books.pageSize}条书籍，
		第${books.currentPageNo}/${books.totalPageCount}页,共${books.totalCount}本书籍
		<c:if test="${books.previousPage}">
			<a href="<c:url value="/home?pageNo=${books.currentPageNo-1}" />">上一页</a>
		</c:if>
		<c:if test="${books.nextPage}">
			<a href="<c:url value="/home?pageNo=${books.currentPageNo+1}" />">下一页</a>
		</c:if>
	</div>

	<br>
	<br>
	<br>

</body>
</html>