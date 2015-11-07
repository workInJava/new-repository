<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<title>账户设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="../css/jquery.mobile.theme-1.4.5.css">
<script src="../javascripts/jquery.min.js"></script>
<script src="../javascripts/jquery.mobile-1.4.5.js"></script>
</head>
<body class="ui-mobile-viewport ui-overlay-a">
	<div style="padding-bottom: 36px;"
		class="ui-page ui-page-theme-a ui-page-footer-fixed ui-page-active"
		tabindex="0" data-url="testpage" data-role="page" id="testpage">
		<div class="ui-panel-wrapper">
			<div class="ui-content" role="main" >

				<%
					String flag = (String) request.getAttribute("flag");
					if ("fail".equals(flag)) {
				%>
				<b><front style="color: red;">用户名或密码错误，重新输入．</front></b>
				<%
					}
				%>
				<form action="customer/login" method="POST">
					<div>
						<label for="text-basic">登录名:</label> <input name="useName"
							id="text-basic" value="" type="text">
					</div>
					<div>
						<label for="password">密码:</label> <input name="password"
							id="password" value="" autocomplete="off" type="password">
					</div>
					<input  value="登陆" type="button"> 
					<input  value="免费注册" type="button">
				</form>
			</div>
		</div>
	</div>
</body>
</html>