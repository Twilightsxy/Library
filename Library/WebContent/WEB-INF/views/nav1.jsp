<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/book"><i class="mdi mdi-book-open-variant"></i><span class="hide-menu">书籍</span></a></li>  
                         <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/book/return"><i class="mdi mdi-book-open-variant"></i><span class="hide-menu">还书管理</span></a></li>  
                         <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/reader">
                                 <i class="mdi mdi-account-check"></i><span class="hide-menu">读者账户</span>
                              </a>
                         </li>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/check" aria-expanded="false"><i class="mdi mdi-briefcase-check"></i><span class="hide-menu">借阅审核</span></a></li>
                       
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/reader/search" aria-expanded="false"><i class="mdi mdi-account-star"></i><span class="hide-menu">查找读者</span></a></li>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/librarian/manager" aria-expanded="false"><i class="mdi mdi-account-star"></i><span class="hide-menu">管理员账户</span></a></li>
                        
                       <li>&nbsp; </li>
                       <li>&nbsp; </li>
                       
                    </ul>
                    <div class="text-center m-t-30">
                        <a href="${pageContext.request.contextPath}/logout" class="btn waves-effect waves-light btn-info hidden-md-down"> 注销</a>
                    </div>
                </nav>
</body>
</html>