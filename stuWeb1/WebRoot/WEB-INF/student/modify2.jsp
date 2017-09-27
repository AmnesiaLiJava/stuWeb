<%@ page language="java" import="java.util.*,entity.Student"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	width: 700px;
	margin: 0px auto;
}

.form {
	width: 260px;
}

.form label {
	float: left;
	width: 60px;
}

.form .value {
	float: left;
	width: 200px;
}

.form div {
	clear: both;
	margin-top: 30px;
	overflow: hidden;
}

.btn {
	clear: both;
	text-align: center;
}

#save {
	clear: both;
	text-align: center;
	overflow: hidden;
}

#save input {
	
}

.form span {
	margin: 0 10px;
	font-size: 14px;
	font-weight: bold;
}

#main ul {
	overflow: hidden
}

#main ul li {
	list-style: none;
	float: left;
	margin-left: 60px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#save input").click(function() {

			var stus = "";
			$(".form").each(function(index, element) {

				stus += $(this).find("[name=id]").val() + ",";

				stus += $(this).find("[name=name]").val() + ",";

				var sex = "";
				$(this).find("[name=sex]").each(function() {

					if ($(this).prop("checked")) {
						sex = $(this).val();
					}
				})

				stus += sex + ",";
				stus += $(this).find("[name=age]").val() +",";
				
				stus += $(this).find("[name=bj]").val()+ ";";

			})
			stus = stus.substring(0, stus.length - 1);
			location.href = "stu?type=modify2&stus=" + stus;
		})

	})
</script>

</head>

<body>

	<div id="main">
		<ul>
			<c:forEach items="${stus}" var="stu">
				<li>
					<form class="form" action="stu" method="post">
						<input type="hidden" name="id" value="${stu.id }" />
						<div>
							<label>姓名</label><input type="text" name="name"
								class="form-control value" placeholder="请输入姓名"
								value="${stu.name}" />
						</div>
						<div>
							<label>性别</label> <input type="radio" name="sex" class="sex"
								value="男" <c:if test="${stu.sex eq '男'}">checked</c:if> /><span>男</span>
							<input type="radio" name="sex"
								<c:if test="${stu.sex eq '女'}">checked</c:if> value="女" /><span>女</span>
						</div>
						<div>
							<label>年龄</label><input type="text" name="age"
								class="form-control value" value="${stu.age}" />
						</div>
						<div>
							<label>性别</label> <select id="bj" name="bj"
								class="form-control value"><option value="0">请选择班级</option>
								<c:forEach items="${bjs}" var="bj">
									<option value="${bj.id}"
										<c:if test="${bj.id==stu.bj.id}">selected</c:if>>${bj.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="btn">
							<input type="submit" value="保存" class="btn btn-danger" />
						</div>
					</form>
				</li>
			</c:forEach>
		</ul>
		<div id="save">
			<input type="button" value="保存" class="btn btn-lg btn-primary" />
		</div>

	</div>


</body>
</html>
