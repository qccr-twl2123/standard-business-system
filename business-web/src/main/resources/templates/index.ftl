<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>FaceGo后台管理系统</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="/style/css/global.css" media="all">
		<link rel="stylesheet" href="/style/css/index.css" media="all">
        <script type="text/javascript" src="/layui/layui.js"></script>
        <script type="text/javascript" src="/style/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/style/js/ajaxData.js"></script>
        <script type="text/javascript" src="/style/datas/nav.js"></script>
        <script type="text/javascript" src="/style/js/index.js"></script>
	</head>

	<body>
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<div class="logo">
							<img src="/style/images/logo.png" class="logo-img">
						</div>
						<span class="facego-title">FaceGo后台管理系统</span>
					</div>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<div class="layui-body" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>首页</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="padding: 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="/test/main" name="facego"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2018 &copy;
						<a href="#">www.arcsoft.com</a>
					</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
		</div>

	</body>


</html>