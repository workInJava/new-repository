<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>账户设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="../css/jquery.mobile.theme-1.4.5.css">
<script src="../javascripts/jquery.min.js"></script>
<script src="../javascripts/jquery.mobile-1.4.5.js"></script>
</head>
<body>
	<div class="ui-content" role="main">

		<c:if test="${!flag}">
		<b><font style="color: red;">${msg}</font></b>
		</c:if>
		<form action="login" method="POST">
			<div>
				<label for="text-basic">登录名:</label> 
				<input name="useName" id="text-basic" value="${useName}" type="text">
			</div>
			<div>
				<label for="password">密码:</label> 
				<input name="password" id="password" value="${password}" autocomplete="off" type="password">
			</div>
			<input value="登陆" type="submit">
			<input value="免费注册" type="button">
		</form>
	</div>
</body>
</html>