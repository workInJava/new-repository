<%@ page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
<title>账户设置</title>
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
</head>
<body>

	<% String flag = (String)request.getAttribute("flag");
		if("fail".equals(flag)){
		%>	
    <b><front style="color: red;">用户名或密码错误，重新输入．</front></b>
	<%
		}
	%>
	<form  action="customer/login"  method="POST">
		登录名：<input type="text" name="Name"><br>
		密码：<input type="text" name="Password"><br> 
		<button class="ui-btn ui-shadow ui-corner-all" style="color=">登陆</button>
		<button class="ui-btn ui-shadow ui-corner-all">免费注册</button>
		
		
	</form>

</body>
</html>