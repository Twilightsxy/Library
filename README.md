# 在线图书管理系统：企业级开发大作业
## 环境
1. JDK 1.8
2. PostgreSQL 11
3. Tomcat 9
## Spring MVC架构

## URL映射图
### ManageReaderController：
1. librarian/reader/，管理读者。
    1. librarian/reader/add，添加读者。
    2. librarian/reader/delete，删除读者。
    3. librarian/reader/change，修改读者。
    4. librarian/reader/search，搜索读者。
### ManageBookController：
1. librarian/book/，管理书籍。
   1. librarian/book/add，添加书籍。
   2. librarian/book/delete/{bookNo}，删除书籍。
   3. librarian/book/change，修改书籍。
   4. librarian/book/search，查询图书。
   5. librarian/book/return，归还书籍。
### ManageCheckController：
1. librarian/check/，管理借阅审批。
   1. librarian/check/audit/{bookno}，审核通过。
### ManageLibrarianController：
1. librarian/manager，管理管理员。
   1. librarian/manager/add，添加。
   2. librarian/manager/delete，删除。
   3. librarian/manager/change，修改管理员。
### HomeController：
1. /,主页。
   1. home，主页数据展示。
   2. home/bookSearch，主页查书功能。
   3. login/reader，读者登录。
   4. login/librarian，管理员登录。
   5. retrieve，找回密码。
   6. logout，退出登录。
### ReaderController:
1. reader，读者的所有操作。
   1. reader/cart，进入购物车界面。 
   2. reader/cart/reserve/{bookno}，预约书籍。
   3. reader/personInfo，查看读者借阅和借阅历史。

## JSP命名
1. adminHome --> manageBook
2. account --> manageReader
3. admin --> manageLibrarian
4. check --> manageCheck
5. reader --> viewReader 

