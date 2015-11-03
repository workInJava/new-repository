<%@ page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
<title>账户设置</title>
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
</head>
<body>

	<form  action=""  method="POST">
		
		姓名：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="name" id="text-basic" value="" type="text"></div><br>
		证件号：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="number" id="textinput-hide" placeholder="身份证号/护照号" value="" type="text"></div><br>
		手机号：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="phone" id="text-basic" value="" type="text"></div><br>
		登陆密码：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="password" id="password" value="" autocomplete="off" type="password"></div><br>
		再次输入登陆密码：<div class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset">
		<input name="repassword" id="password" value="" autocomplete="off" type="password"></div><br>
		<button class="ui-btn ui-shadow ui-corner-all" style="background:green">注册</button>
	</form>
	
	<div>为了能更好的向您提供理赔服务，请先填写您个人的真实身份信息，我们承诺将不会向第三方泄露您的个人信息．</div><br><br>
	<div>赔付宝</div><br>
	<div>www.peifubao.com</div>

</body>
</html>