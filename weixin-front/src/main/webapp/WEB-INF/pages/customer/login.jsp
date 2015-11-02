<%@ page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
<title>账户设置</title>
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
</head>
<body>

	<% String flag = request.getParameter("flag");
		if("fail".equals(flag)){
		%>	
    <b><front style="color: red;">用户名或密码错误，重新输入．</front></b>
	<%
		}
	%>
	<form  action="login"  method="POST">
		
		登录名：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="Name" id="text-basic" value="" type="text"></div><br>
		密码：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="Password" id="text-basic" value="" type="text"></div><br>
		<button class="ui-btn ui-shadow ui-corner-all"  style="background:green">登陆</button>
		<button class="ui-btn ui-shadow ui-corner-all">免费注册</button>
	</form>
	
	<div>为了能更好的向您提供理赔服务，请先注册赔付宝账户，我们承诺将不会向第三方泄露您的个人信息．</div><br><br>
	<div>赔付宝</div><br>
	<div>www.peifubao.com</div>

</body>
</html>