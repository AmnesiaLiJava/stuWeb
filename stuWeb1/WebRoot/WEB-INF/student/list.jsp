<%@page import="util.Pagination"%>
<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<style>
#main {
	width: 650px;
	margin: 10px auto;
}

#mes {
	float: right;
	width: 150px;
	height: 36px;
	line-height: 30px;
	letter-spacing: 3px;
	color: red;
}

.fenye {
	float: right;
	margin: 0;
	padding: 0;
	margin: 0;
}

.fenye li {
	list-style: none;
	float: left;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 140%;
	border: 1px solid #ddd;
	text-align: center;
	color: #337ab7;
	margin-left: -1px;
}

.fenye li:hover {
	background: #eee;
	cursor: pointer;
}

.fenye li:first-child {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px;
}

.fenye li:last-child {
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
}

.fenye .active {
	color: #fff;
	background-color: #337ab7;
	border-color: #337ab7;
}

.fenye .active:hover {
	color: #fff;
	background-color: #337ab7;
	border-color: #337ab7;
}

.selected {
	background: #337ab7
}

.btn-group {
	margin-top: 50px;
}

#search {
	overflow: hidden;
	margin-bottom: 20px;
}

#search label {
	float: left;
	margin-right: 10px;
	height: 30px;
	line-height: 30px;
}

#search  #searchBtn {
	
}

#search .value {
	width: 120px;
	height: 30px;
	float: left;
	margin-right: 20px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>

<script>
	$(document)
			.ready(
					function() {

						var ye = ${p.ye};
						var maxYe = ${p.maxYe};
						$("#pre")
								.click(
										function() {
											if (ye > 1) {

												var name = $("#name").val();
												var sex = $("#sex").val();
												var age = $("#age").val();
												location.href = "stu?type=search&ye="
														+ (ye - 1)
														+ "&name="
														+ name
														+ "&sex="
														+ sex
														+ "&age=" + age;

											} else {
												showMes("已经是第一页了");
											}
										})
						$("#next")
								.click(
										function() {
											if (ye < maxYe) {
												var name = $("#name").val();
												var sex = $("#sex").val();
												var age = $("#age").val();
												window.location.href = "stu?type=search&ye="
														+ (ye + 1)
														+ "&name="
														+ name
														+ "&sex="
														+ sex
														+ "&age=" + age;
												;
											} else {
												showMes("已经是最后一页了");
											}
										})

						$("[ name=numPage]")
								.click(
										function() {
											var name = $("#name").val();
											var sex = $("#sex").val();
											var age = $("#age").val();
											window.location.href = "stu?type=search&ye="
													+ $(this).html()
													+ "&name="
													+ name
													+ "&sex="
													+ sex
													+ "&age=" + age;
											;
										})
						$("#add").click(function() {
							window.location.href = "stu?type=showAdd";

						})

						function showMes(mes) {
							$("#mes").html(mes);
							setTimeout(function() {
								$("#mes").html("");
							}, 1000);
						}
						$("#modify")
								.click(
										function() {
											var array = new Array();
											$("tbody tr")
													.each(
															function(index,
																	element) {
																if ($(this)
																		.attr(
																				"class") == "selected") {
																	array
																			.push($(
																					this)
																					.data(
																							"id"));
																}
															})
											if (array.length == 0) {
												showMes("请选中一条数据");
											} else {
												location.href = "stu?type=showModify2&selectId="
														+ array;
											}
										})
						$("#delete")
								.click(
										function() {
											var array = new Array();
											$("tbody tr")
													.each(
															function(index,
																	element) {
																if ($(this)
																		.attr(
																				"class") == "selected") {
																	array
																			.push($(
																					this)
																					.data(
																							"id"));
																}
															})
											if (array.length == 0) {
												showMes("请选中一条数据");
											} else {
												alert(array);
												var type = confirm("确认要删除数据吗？");
												if (type) {
													location.href = "stu?type=delete&selectId="
															+ array;
												}

											}
										})
						$("tbody tr").click(function() {
							$(this).toggleClass("selected");

						})

					})
</script>
</head>

<body>
	<div id="main">
		<div id="search">
			<form action="stu" method="post">
				<input type="hidden" name="type" value="search" /> <label>姓名</label>
				<input type="text" id="name" name="name" class="form-control value"
					value="${condition.name}" /> <label>性别</label> <select id="sex"
					name="sex" class="form-control value"><option>请选择性别</option>
					<option value="男"
						<c:if test="${condition.sex eq '男'}">selected</c:if>>男</option>
					<option value="女"
						<c:if test="${condition.sex eq '女'}">selected</c:if>>女</option></select> <label>年龄</label>
				<input type="text" id="age" name="age" class="form-control value"
					<c:if test="${condition.age!=-1}">value="${condition.age}"</c:if> />
				<input id="searchBtn" type="submit" value="查找"
					class="btn btn-primary" />
			</form>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>班级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="stu" items="${stus}">
					<tr data-id="${stu.id }">
						<td>${stu.name}</td>
						<td>${stu.sex}</td>
						<td>${stu.age}</td>
					    <td>${stu.bj.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="fenye">
			<li id="pre">上一页</li>
			<c:forEach begin="${p.begin}" end="${p.end}" varStatus="status">
				<li name="numPage"
					<c:if test="${p.ye==status.index}"> class="active"</c:if>>${status.index }</li>
			</c:forEach>
			<li id="next">下一页</li>
		</ul>
		<div id="mes"></div>

		<div class="btn-group">
			<button id="add" type="button" class="btn btn-danger">新增</button>
			<button id="modify" type="button" class="btn btn-primary">修改</button>
			<button id="delete" type="button" class="btn btn-primary">删除</button>
		</div>


	</div>

</body>
</html>
