<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>leftMeun</title>
<style type="text/css">
.top{
    /* 设置宽度高度背景颜色 */
    height: 110px; /*高度改为自动高度*/
    width:100%;
    margin-left: 0;
    background:rgba(23, 222, 255, 0.63);
    position: fixed; /*固定在顶部*/
    top: 0;/*离顶部的距离为0*/
    margin-bottom: 5px;
     z-index: 999;
}
.top ul{
    /* 清除ul标签的默认样式 */
    width: auto;/*宽度也改为自动*/
    list-style-type: none;
    white-space:nowrap;
    overflow: hidden;
    margin-left: 5%;
    /* margin-top: 0;          */
    padding: 0;

}
.top li {
    float:left; /* 使li内容横向浮动，即横向排列  */
    margin-right:2%;  /* 两个li之间的距离*/
    position: relative;
    overflow: hidden;
}

.top li a{
   /* 设置链接内容显示的格式*/
    display: block; /* 把链接显示为块元素可使整个链接区域可点击 */
    color:white;
    text-align: center;
    padding: 3px;
    overflow: hidden;
    text-decoration: none; /* 去除下划线 */
  border:1px solid #EEE8DD;
  cursor:hand;
  display:inline;
  font-weight:bold;
  float:left;
  margin-top:30px;
}


.top li a:link{
  color:#3C3C3C;
  text-decoration:none;
}

.top li a:hover{
  color:#A0522D;
  text-decoration:none;
}


.top ul li ul{
    /* 设置二级菜单 */
    margin-left: -0.2px;
    background:rgb(189, 181, 181);
    position: relative;
    display: none; /* 默认隐藏二级菜单的内容 */

}
.top ul li ul li{
    /* 二级菜单li内容的显示 */
    
    float:none;
    text-align: center;
}
.top ul li:hover ul{
    /* 鼠标选中二级菜单内容时 */
    display: block;
}
body{
    background:#eff3f5;
}
#userName{
	color:white;
	}
</style>

<body>

<div  class="top">


  <ul>
  		<li><img src="images/logo.jpg" style=" opacity: 0.6;margin: 20px auto;"></li>
       	<li id="personInfor">
				<p id="userName">Welcome! <%=request.getSession().getAttribute("userName")%></p>
				<!--实现从数据库读取名字写入-->
				<form action="LogoutServlet" method="get">
					<p>
						<input id="submit" type="submit" value="Logout"
							class="btn btn-danger btn-xs" style="width: 140px;">
						<!--实现退出登录-->
					</p>
				</form>
			</li>
			
			<!-- <li class="meun-title"> </li> -->
			
			<li><a href="#book"
				aria-controls="book" role="tab" data-toggle="tab"
					onclick="displayBook('1')">
				<img src="images/icon_source.png">Books
			</a></li>
			
			<li> <a href="#lend"
				aria-controls="lend" role="tab" data-toggle="tab">
				<img src="images/icon_source.png">Lend
			</a></li>
			
			<li><a href="#return"
				aria-controls="return" role="tab" data-toggle="tab">
				<img src="images/icon_source.png">Return
			</a></li>
			
			<li><a href="#money" aria-controls="money" role="tab"
				data-toggle="tab">
				<img src="images/icon_user.png">Money
			</a></li>
			
			<li><a href="#user" aria-controls="user" role="tab"
				data-toggle="tab" onclick="displayReader('1')">
				<img src="images/icon_card.png">Readers
			</a></li>
			
			<li><a href="#notice" aria-controls="notice"
				role="tab" data-toggle="tab" onclick="postsearch()">
				<img src="images/icon_source.png">Announcements
			</a></li>
			
			<li><a href="#category" aria-controls="category"
				role="tab" data-toggle="tab" onclick="turnToCategory('1')">
				<img src="images/icon_change.png">Category
			</a></li>
			
			<li><a href="#location" aria-controls="location"
				role="tab" data-toggle="tab" onclick="turnToLocation('1')">
				<img src="images/icon_house.png">Location
			</a></li>	
    
  </ul>
  
  
  
  
</div>
</body>


</head>	
		
</html>