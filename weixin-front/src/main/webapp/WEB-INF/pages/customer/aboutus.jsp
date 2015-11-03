<%@ page language="java" contentType="text/html;charset=utf-8"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>帮助中心</title>
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css">
<script src="../javascripts/jquery.min.js"></script>
<script src="../javascripts/jquery.mobile-1.4.5.js"></script>
<style id="inset-tablist">
.tablist-left {
	width: 25%;
	display: inline-block;
}

.tablist-content {
	width: 60%;
	display: inline-block;
	vertical-align: top;
	margin-left: 5%;
}
</style>
</head>

<body>
	<div data-demo-html="true">
		<div class="ui-tabs ui-widget ui-widget-content ui-corner-all"
			data-role="tabs" id="tabs">
			<div role="navigation" class="ui-navbar" data-role="navbar">
				<ul role="tablist"
					class="ui-grid-b ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
					<li aria-selected="true" aria-labelledby="ui-id-1"
						aria-controls="one" tabindex="0" role="tab"
						class="ui-block-a ui-state-default ui-corner-top ui-tabs-active ui-state-active"><a
						id="ui-id-1" tabindex="-1" role="presentation"
						class="ui-link ui-btn ui-tabs-anchor ui-btn-active" href="#one"
						data-ajax="false">关于我们</a></li>
					<li aria-selected="false" aria-labelledby="ui-id-2"
						aria-controls="two" tabindex="-1" role="tab"
						class="ui-block-b ui-state-default ui-corner-top"><a
						id="ui-id-2" tabindex="-1" role="presentation"
						class="ui-link ui-btn ui-tabs-anchor" href="#two"
						data-ajax="false">理赔服务</a></li>
					<li aria-selected="false" aria-labelledby="ui-id-3"
						aria-controls="ui-tabs-1" tabindex="-1" role="tab"
						class="ui-block-c ui-state-default ui-corner-top"><a
						id="ui-id-3" tabindex="-1" role="presentation"
						class="ui-link ui-btn ui-tabs-anchor"
						href="ajax-content-ignore.html" data-ajax="false">联系我们</a></li>
				</ul>
			</div>
			<div style="display: block;" aria-hidden="false" aria-expanded="true"
				role="tabpanel" aria-labelledby="ui-id-1" id="one"
				class="ui-body-d ui-content ui-tabs-panel ui-widget-content ui-corner-bottom">
				<h1></h1>
			</div>
			<div aria-hidden="true" aria-expanded="false" style="display: none;"
				role="tabpanel" aria-labelledby="ui-id-2" id="two"
				class="ui-tabs-panel ui-widget-content ui-corner-bottom">
				<h1></h1>
			</div>
			<div aria-hidden="true" aria-expanded="false" style="display: none;"
				role="tabpanel" aria-labelledby="ui-id-3" aria-live="polite"
				class="ui-tabs-panel ui-widget-content ui-corner-bottom"
				id="ui-tabs-1">
				<ul
					class="ui-body-d ui-content ui-tabs-panel ui-widget-content ui-corner-bottom"
					data-role="listview" data-inset="true">
					<li><a
						class="ui-btn">邮箱：cs@peifubao.com</a></li>
					<li><a class="ui-btn">热线：400_***_***（周一到周五9:30-17:30）</a></li>
					<li><a class="ui-btn">微信：培富葆</a></li>
					<li><a class="ui-btn">地址：上海市杨浦区国定路335号1号楼4001室</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>