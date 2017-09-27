<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: #fff;
}

body {
	width: 180px;
}

#menu {
	margin: 0px 0 0 20px;
}

#menu .yi {
	width: 180px;
	height: 40px;
	font-size: 20px;
	background-color: #337ab7;
	border: 1px solid #666;
	color: #fff;
	font-weight: bold;
	line-height: 40px;
	text-align: center;
	margin-top: 5px;
	border-radius: 3px;
}

#menu .yi:hover {
	background-color: #286090;
	cursor: pointer;
}

#menu .er {
	width: 180px;
	margin: 0 auto;
}

#menu span {
	font-size: 14px;
	font-weight: normal;
	line-height: 40px;
	font-size: 14px;
	height: 40px;
}

#menu .er li {
	list-style: none;
	width: 180px;
	height: 28px;
	font-size: 14px;
	background-color: #337ab7;
	border: 1px solid #666;
	color: #fff;
	line-height: 28px;
	text-align: center;
	margin-top: 1px;
	border-radius: 3px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$(".yi").click(function() {
			if ("none" == $(this).next().css("display")) {
				$(this).next().slideDown(500);
				$(this).find("span").html("[-]");
			} else {
				$(this).next().slideUp(500);
				$(this).find("span").html("[+]");

			}

		})

	})
</script>
</head>

<body>
	<div id="menu">
		<div class="yi">
			<span>[-]</span>学生管理
		</div>
		<ul class="er">
			<li><a href="stu?type=search" target="main">查看</a></li>
			<li><a href="stu?type=showAdd" target="main">新增</a></li>
		</ul>

		<div class="yi">
			<span>[-]</span>班级管理
		</div>
		<ul class="er">
			<li><a href="bj" target="main">查看</a></li>
			<li><a href="bj?type=showAdd" target="main">新增</a></li>
		</ul>

		<div class="yi">
			<span>[-]</span>科目管理
		</div>
		<ul class="er">
			<li>查看</li>
			<li>新增</li>
		</ul>

		<div class="yi">
			<span>[-]</span>成绩管理
		</div>
		<ul class="er">
			<li>查看</li>
			<li>新增</li>
		</ul>

	</div>
</body>
</html>
