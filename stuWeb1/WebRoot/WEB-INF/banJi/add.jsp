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

<title>My JSP 'add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">

<style>
#main {
	width: 260px;
	margin: 10px auto;
}

#form label {
	float: left;
	width: 60px;
}

#form .value {
	float: left;
	width: 200px;
}

#form div {
	clear: both;
	margin-top: 30px;
	overflow: hidden;
}

#btn {
	text-align: center;
}

#form span {
	margin: 0 10px;
	font-size: 14px;
	font-weight: bold;
}
</style>
</head>

<body>

	<div id="main">
		<form id="form" action="bj" method="post">
			<input type="hidden" name="type" value="add" />
			<div>
				<label>名称</label><input type="text" name="name"
					class="form-control value" /  placeholder="请输入姓名">
			</div>
			

			<div id="btn">
				<input type="submit" value="保存" class="btn btn-danger" />
			</div>
		</form>

	</div>


</body>
</html>
