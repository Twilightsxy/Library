
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div role="tabpanel" class="tab-pane" id="user">
		<div class="check-div form-inline">
			<div class="col-xs-3" id="readerUpperButton" name="readerUpperButton">
				<button class="btn btn-yellow btn-xs" data-toggle="modal"
					data-target="#addUser">Add Reader</button>
			</div>
			<div class="col-xs-4">
				<input type="text" class="form-control input-sm"
					placeholder="Input Text to Search" name="searchReaderContent"
					id="searchReaderContent">
				<button type="button" class="btn btn-white btn-xs "
					onclick="searchReader()">Search</button>
			</div>
		</div>
		<div class="data-div">
			<div class="row tableHeader" name="readerDisplayLabelForm"
				id="readerDisplayLabelForm">
				<div class="col-xs-2 ">Account</div>
				<div class="col-xs-2 ">Name</div>
				<div class="col-xs-2">Password</div>
				<div class="col-xs-2">Email</div>
				<div class="col-xs-2">Fine</div>
				<div class="col-xs-2" style="padding-left: 500px">Operation</div>
			</div>

			<div class="tablebody" name="displayReaderDiv" id="displayReaderDiv">

			</div>
		</div>
		<!--Page码块-->
		<footer class="footer" id="readerPageFooter" name="readerPageFooter">
			<ul class="pagination">
				<li><select id="readerPageSelect" name="readerPageSelect"
					onchange="displayReader(this.options[this.options.selectedIndex].value)">
				</select> Page</li>
				<li class="gray" id="readerTotalPageLi" name="readerTotalPageLi"></li>
				<li><i class="glyphicon glyphicon-menu-left"
					id="readerPageDown" name="readerPageDown" onclick=readerPageDown()>
				</i></li>
				<li><i class="glyphicon glyphicon-menu-right" id="readerPageUp"
					name="readerPageUp" onclick=readerPageUp()> </i></li>
			</ul>
		</footer>

		<!--弹出添加用户窗口-->
		<div class="modal fade" id="addUser" role="dialog"
			aria-labelledby="gridSystemModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form class="form-horizontal" action="ReaderManageServlet"
						method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="gridSystemModalLabel">Add Reader</h4>
						</div>
						<div class="modal-body">
							<div class="container-fluid">

								<div class="form-group ">
									<label for="sName" class="col-xs-3 control-label">Account：</label>
									<div class="col-xs-8 ">
										<input type="text" class="form-control input-sm duiqi"
											name="newReaderAcc" id="newReaderAcc" placeholder="">
									</div>
								</div>

								<div class="form-group ">
									<label for="sName" class="col-xs-3 control-label">Name：</label>
									<div class="col-xs-8 ">
										<input type="text" class="form-control input-sm duiqi"
											name="newReaderName" id="newReaderName" placeholder="">
									</div>
								</div>

								<div class="form-group ">
									<label for="sName" class="col-xs-3 control-label">Password：</label>
									<div class="col-xs-8 ">
										<input type="password" class="form-control input-sm duiqi"
											name="newReaderPassword" id="newReaderPassword"
											placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label for="sOrd" class="col-xs-3 control-label">Email：</label>
									<div class="col-xs-8">
										<input type="email" class="form-control input-sm duiqi"
											name="newReaderEmail" id="newReaderEmail" placeholder="">
									</div>
								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-xs btn-white"
								data-dismiss="modal">Cancel</button>
							<button type="submit" class="btn btn-xs btn-green"
								onclick="saveNewReader()" data-dismiss="modal">Save</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<!--弹出修改用户窗口-->
		<script>
					</script>
		<form class="form-horizontal">
			<div class="modal fade" id="reviseUser" role="dialog"
				aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="gridSystemModalLabel">Edit
								Reader</h4>
						</div>
						<div class="modal-body">
							<div class="container-fluid">

								<div class="form-group ">
									<label for="sName" class="col-xs-3 control-label">Account:</label>
									<div class="col-xs-8 ">
										<input type="hidden" name="lastReaderAcc" id="lastReaderAcc"
											value=""> <input type="text"
											class="form-control input-sm duiqi" id="changeReaderAcc"
											name="changeReaderAcc" placeholder="">
									</div>
								</div>

								<div class="form-group ">
									<label for="sName" class="col-xs-3 control-label">Name:</label>
									<div class="col-xs-8 ">
										<input type="text" class="form-control input-sm duiqi"
											id="thisReaderName" name="thisReaderName" placeholder="">
									</div>
								</div>

								<div class="form-group">
									<label for="sOrd" class="col-xs-3 control-label">Password:</label>
									<div class="col-xs-8">
										<input type="password" class="form-control input-sm duiqi"
											id="thisReaderPass" name="thisReaderPass" placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label for="sKnot" class="col-xs-3 control-label">Email:</label>
									<div class="col-xs-8">
										<input type="email" class="form-control input-sm duiqi"
											id="thisReaderEmail" name="thisReaderEmail" placeholder="">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-xs btn-white"
								data-dismiss="modal">Cancel</button>
							<button type="button" id="lastRNo" name="lastRNo"
								class="btn btn-xs btn-green" data-dismiss="modal"
								onclick="saveThisReader()">Save</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</form>
		<!-- /.modal -->

		<!--弹出删除用户警告窗口-->
		<form>
			<div class="modal fade" id="deleteUser" role="dialog"
				aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="gridSystemModalLabel">Mention</h4>
						</div>
						<div class="modal-body">
							<div class="container-fluid">Are you sure to delete the
								reader? Before you delete this user, please check if this user
								has book(s) still not return.</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-xs btn-white"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn  btn-xs btn-danger"
								onclick="deleteReader()" data-dismiss="modal">Delete</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</form>
		<!-- /.modal -->

	</div>
</body>
<script>
		                       function toDeleteThisReader(deleteReader) {
				                	document.getElementById("lastReaderAcc").value = deleteReader;
		                     }
		               </script>
<script>
		                       function toModifyThisReader(changeReader){
		                    	   $.ajax({
			               				type : "POST",
			               				async : false,
			               				cache : false,
			               				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			               				data : {"changeReader":changeReader},
			               				url : "TurnToThisReaderServlet",
			               				traditional : true,
			               				success : function(data) {
			               					var objs = eval('(' + data + ')');
			               					var account = objs.account;
			               					var name = objs.name;
			               					var password = objs.password;
			               					var email = objs.email;
			               					document.getElementById("lastReaderAcc").value = account;
			               					document.getElementById("changeReaderAcc").value = account;
			               					document.getElementById("thisReaderName").value = name;
			               					document.getElementById("thisReaderPass").value = password;
			               					document.getElementById("thisReaderEmail").value = email;
			               				},
			               				error : function() {
			               					alert("connect failed!");
			               				}
			               			});
		                       }
		   
	                    </script>
<script>
		                       function displayBorrow(borrowReader){
		                    	   $.ajax({
		               				type : "POST",
		               				async : false,
		               				cache : false,
		               				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		               				data : {"borrowReader":borrowReader},
		               				url : "DisplayBorrowServlet",
		               				traditional : true,
		               				success : function(data) {
		               					var objs = eval('(' + data + ')');
		               					$("#displayReaderDiv").empty();
		               					$("#readerDisplayLabelForm").empty();
		               					$("#readerUpperButton").empty();
		               					var div3 = '<span onclick="displayReader(1)" aria-controls="book" role="tab" '
		        								+ 'data-toggle="tab" style="cursor: pointer;"><span '
		        								+ 'class="glyphicon glyphicon glyphicon-chevron-left"></span>Back</span>';
		        						$("#readerUpperButton").append(div3);
		               					var div2 = '<div class="col-xs-1">Book Number</div>'
		               							+ '<div class="col-xs-2">Title</div>'
		               							+ '<div class="col-xs-2">Author</div>'
		               							+ '<div class="col-xs-3">Borrow Time</div>'
		               							+ '<div class="col-xs-1">Fine</div>'
		               							+ '<div class="col-xs-1">Returned?</div>';
		               							
		               					$("#readerDisplayLabelForm").append(div2);
		               					for ( var num in objs) {
		               						var bookNo = objs[num].bookNo;
		               						var bookTitle = objs[num].bookTitle;
		               						var bookAuthor = objs[num].bookAuthor;
		               						var borrowTime = objs[num].borrowTime;
		               						var isReturned = objs[num].isReturned;
		               						var fine = objs[num].fine;
		               						if(fine != 0)
		               						var div = '<div class="row">'
		    										+ '<div class="col-xs-1 ">'
		    										+ bookNo
		    										+ '</div>'
		    										+ '<div class="col-xs-2">'
		    										+ bookTitle
		    										+ '</div>'
		    										+ '<div class="col-xs-2">'
		    										+ bookAuthor
		    										+ '</div>'
		    										+ '<div class="col-xs-3">'
		    										+ borrowTime
		    										+ '</div>'
		    										+ '<div class="col-xs-1">'
		    										+ fine
		    										+ '</div>'
		    										+ '<div class="col-xs-1">'
		    										+ isReturned
		    										+ '</div>'
		    										+ '<div class="col-xs-2">'
		    										+ '<button class="btn btn-success btn-xs" data-toggle="modal" '
		    										+ 'data-target="#penalty">Fine</button>'
		    										
		    										+ '<button class="btn btn-danger btn-xs" data-toggle="modal" '
		    										+ 'onclick="deleteBorrow('
		    										+ bookNo
		    										+ ','
		    										+ borrowTime
		    										+ ')">Delete</button>'
		    										+ '</div>'
		    										+ '</div>';
		    										else div = '<div class="row">'
			    										+ '<div class="col-xs-1">'
			    										+ bookNo
			    										+ '</div>'
			    										+ '<div class="col-xs-2">'
			    										+ bookTitle
			    										+ '</div>'
			    										+ '<div class="col-xs-2">'
			    										+ bookAuthor
			    										+ '</div>'
			    										+ '<div class="col-xs-3">'
			    										+ borrowTime
			    										+ '</div>'
			    										+ '<div class="col-xs-1">'
			    										+ fine
			    										+ '</div>'
			    										+ '<div class="col-xs-1">'
			    										+ isReturned
			    										+ '</div>'
			    										
			    										+ '</div>';
		               						$("#displayReaderDiv").append(div);
		               					}
		               				},
		               				error : function() {
		               					alert("connect failed!");
		               				}
		               			});
		               		}
	                    </script>
<script>
		function displayReader(pageNum){
			$.ajax({
				type : "POST",
				async : false,
				cache : false,
				data : {"pageNum": pageNum},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				url : "DisplayReaderServlet",
				traditional : true,
				success : function(data) {
					var objs = eval('(' + data + ')');
					$("#displayReaderDiv").empty();
					$("#readerDisplayLabelForm").empty();
					$("#readerUpperButton").empty();
					var div3 = '<button class="btn btn-yellow btn-xs" data-toggle="modal" '
							+ 'data-target="#addUser">Add Reader</button>';
					$("#readerUpperButton").append(div3);
   					var div2 = '<div class="col-xs-2 ">Account</div>'
   							+ '<div class="col-xs-2">Name</div>'
   							+ '<div class="col-xs-2">Password</div>'
   							+ '<div class="col-xs-2">Email</div>'
   							+ '<div class="col-xs-2" style="padding-left:100px">Total Fine</div>'
   							+ '<div class="col-xs-2" style="padding-left:312px">Operation</div>';
   					$("#readerDisplayLabelForm").append(div2);
   						if(pageNum == 1){
							$("#readerPageSelect").empty();
							var totalPageNum = objs[0].totalPage;
							$("#readerTotalPageLi").text('Total ' + totalPageNum + ' Page');
							for(var i = 1; i <= objs[0].totalPage; i++){
								var optionBar = '<option value="'
								+ i
								+'">'
								+ i
								+ '</option>';
								$("#readerPageSelect").append(optionBar);
							}
						}
						for ( var num in objs) {
							var readerNo = objs[num].readerNo;
							var readerName = objs[num].readerName;
							var readerPassword = objs[num].readerPassword;
							var readerEmail = objs[num].readerEmail;
							var readerFine = objs[num].readerFine;
							var div = '<div class="row">'
									+ '<div class="col-xs-2">'
									+ readerNo
									+ '</div>'
									+ '<div class="col-xs-2">'
									+ readerName
									+ '</div>'
									+ '<div class="col-xs-2">'
									+ readerPassword
									+ '</div>'
									+ '<div class="col-xs-2">'
									+ readerEmail
									+ '</div>'
									+ '<div class="col-xs-2" style="padding-left:119px">'
									+ readerFine
									+ '</div>'
									+ '<div class="col-xs-4" style="padding-left:266px">'
									+ '<button class="btn btn-success btn-xs" data-toggle="modal"'
									+ 'data-target="#reviseUser" onclick="toModifyThisReader('
									+ readerNo
									+ ')">Edit</button>'
									+ '<button class="btn btn-danger btn-xs" data-toggle="modal"'
									+ 'data-target="#deleteUser" onclick="toDeleteThisReader('
									+ readerNo
									+ ')">Delete</button>'
									+ '<button class="btn btn-xs" data-toggle="modal" onclick="displayBorrow('
									+ readerNo
									+')" style="color: #f5f5f5; background-color: #f1c40f;">Record</button>'
									+ '</div>'
									+ '</div>';
							$("#displayReaderDiv").append(div);
						}
				},
				error : function() {
					alert("connect failed!");
				}
			});
		}
	</script>
<script>
		function searchReader()
		{
		   var readerSearch = document.getElementById("searchReaderContent").value;
		   $.ajax
		   (
				{
				   type : "POST",
				   async : false,
				   cache : false,
			       contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				   url : "ReaderSearchServlet",
				   data : {"searchReaderContent" : readerSearch},
				   traditional : true,
				   success : function(data) 
				   {
					  var objs = eval('(' + data + ')');
					  $("#displayReaderDiv").empty();
					  for ( var num in objs) 
					  {
						 var readerNo = objs[num].readerNo;
						 var readerPassword = objs[num].readerPassword;
						 var readerEmail = objs[num].readerEmail;
						 var readerFine = objs[num].readerFine;
						 var div = '<div class="row">'
							+ '<div class="col-xs-2">'
							+ readerNo
							+ '</div>'
							+ '<div class="col-xs-2">'
							+ readerPassword
							+ '</div>'
							+ '<div class="col-xs-2">'
							+ readerEmail
							+ '</div>'
							+ '<div class="col-xs-2">'
							+ readerFine
							+ '</div>'
							+ '<button class="btn btn-success btn-xs" data-toggle="modal"'
							+ 'data-target="#reviseUser" onclick="xg('
							+ readerNo
							+ ')">Edit</button>'
							+ '<button class="btn btn-danger btn-xs" data-toggle="modal"'
							+ 'data-target="#deleteUser" onclick="sc('
							+ readerNo
							+ ')">Delete</button>'
							+ '<a class="linkCcc"  aria-controls="char"'
							+ 'role="tab" data-toggle="tab"><button'
							+ 'class="btn btn-danger btn-xs" data-toggle="modal" onclick="displayBorrow('
							+ readerNo
							+')" >Record</button></a>'
							+ '</div>';
						$("#disReaderDiv").append(div);
					 }
				  },
				  error : function() 
				  {
					alert("connect failed!");
				  }
			   }
			 );
		}
	</script>
<script>
			function deleteReader(deleteReader) {
		        	var deleteReader = document.getElementById("lastReaderAcc").value;
				    	$.ajax({
		               			type : "POST",
		               			async : false,
		               			cache : false,
		               			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		               			data : {"lastReaderAcc":deleteReader},
		               			url : "DeleteReaderServlet",
		               			traditional : true,
		               			success : function(data) {
		               				alert("Delete reader successfully!");
		               				displayReader(1);
		               			},
		               			error : function() {
		               				alert("connect failed!");
		               			}
		               	});
		    }
	</script>
<script>
			function saveThisReader() {
					var lastAccout = document.getElementById("lastReaderAcc").value;
					var thisAccount = document.getElementById("changeReaderAcc").value;
					var name = document.getElementById("thisReaderName").value;
					var password = document.getElementById("thisReaderPass").value;
					var email = document.getElementById("thisReaderEmail").value;
				    	$.ajax({
		               			type : "POST",
		               			async : false,
		               			cache : false,
		               			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		               			data : {"lastReaderAcc":lastAccout, "thisReaderAcc": thisAccount, 
		               						"changeReaderName": name, "changeReaderPassword": password, 
		               							"changeReaderEmail": email},
		               			url : "ChangeReaderServlet",
		               			traditional : true,
		               			success : function(data) {
		               				alert("Modify reader successfully!");
		               				displayReader(1);
		               			},
		               			error : function() {
		               				alert("connect failed!");
		               			}
		               	});
		    }
	</script>
<script>
			function saveNewReader() {
					var accout = document.getElementById("newReaderAcc").value;
					var name = document.getElementById("newReaderName").value;
					var password = document.getElementById("newReaderPassword").value;
					var email = document.getElementById("newReaderEmail").value;
				    	$.ajax({
		               			type : "POST",
		               			async : false,
		               			cache : false,
		               			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		               			data : {"accout": accout, "name": name, 
		               				"password": password, "email": email},
		               			url : "ReaderManageServlet",
		               			traditional : true,
		               			success : function(data) {
		               				alert("Create reader successfully!");
		               				displayReader(1);
		               			},
		               			error : function() {
		               				alert("connect failed!");
		               			}
		               	});
		    }
	</script>
<script>
		   function deleteBorrow(bookNo,data){
			      alert("111");
			      $("#borrowBookNo").val(bookNo);
			      $("#borrowData").val(data);
		          //document.getElementById("borrowBookNo").value = bookNo;
		          //document.getElementById("borrowData").value = data;
		          document.forms["deleteborrow"].submit();
		             //var i = $("#borrowReader").val();
		           
		          }
	</script>
</html>