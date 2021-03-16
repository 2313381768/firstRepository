<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	// 获得请求的绝对地址
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录 - 易易二手交易平台</title>
	<base href="<%=basePath%>">
	<meta
			content="二手,二手交易,校园交易,二手买卖,旧物买卖,旧物交易,旧物转手，校园二手交易，校园二手买卖，校园买卖，校园交易"
			name="keywords">
	<meta
			content="易易专注于为校园大学生提供二手交易的机会，同时秉承公平交易和优质售后，让还有利用价值的东西不需要进入垃圾桶，让买家买的放心，让卖家卖的开心。"
			name="description">
	<link rel="stylesheet" type="text/css" href="css/base.css"/>
	<link rel="stylesheet" type="text/css" href="css/footerCss.css"/>
	<script type="text/javascript">
		function validate() {
			var email = document.getElementById("email");
			var password = document.getElementById("password");
			if (email.value == "") {
				alert("邮箱不能为空！");
				email.focus();
				return false;
			}
			if (password.value == "") {
				alert("密码不能为空！");
				password.focus();
				return false;
			}
			return true;
		}
	</script>
	<style>
		.main-footer{
			background-color: transparent;
			margin-top: 0;
		}
		.f-c-top .footer-block .footer-title{
			color: #8f95a3;
		}
		.content-box{
			width: 100%;
			background-image: url("images/bg01.jpg");
			margin-top: 20px;
			position: relative;
			background-repeat: repeat-x;
			background-position: center;
			background-size: auto 150%;
			padding-top: 10px;
			min-height: 650px;
			background-size: 130% auto;
		}
		.content-wraper{
			margin-left: auto;
			margin-right: auto;
			width: 900px;
		}
		.login-title{
			font-size: 35px;
			margin: 15px 0px 20px 0px;
			font-weight: normal;
			color: #fff;
			margin-left: auto;
			margin-right: auto;
			text-shadow: 1px 1px 5px #222;
			width: 520px;
		}
		.content-login-box{
			background-color: white;
			width: 480px;
			margin-left: auto;
			margin-right: auto;
		}
		.content-login-box .login_l{
			margin-left: auto;
			margin-right: auto;
			width: 400px;
			padding: 10px 10px;
			margin-bottom: 10px;
			border-radius: 5px;
			background-color: rgba(252, 252, 252, 1);
			font-size: 14px;
		}
		.content-login-box .login_l .font14{
			font-size: 18px;
			font-weight: bold;
			color: #3d566e;
			margin-bottom: 20px;
		}
		.content-login-box .login_l>div{
			margin-top: 20px;
		}
		.content-login-box .login_l .tn-textbox{
			width: 100%;
			border-radius: 5px;
			border: solid 1px #ddd;
			padding: 8px;
			font-size: 15px;
			margin-bottom: 10px;
		}
		.content-login-box .login_l .form-button-area{
			height: 30px;
		}
		.tn-button-text{
			width: 150px;
			height: 30px;
			color: white;
			background-color: #ff9900;
			border: none;
			margin-left: 125px;
			float: left;
			transition: 1s all;
		}
		.tn-button-text:hover{
			background-color: orange;
		}
		.login-bottom{
			margin-top: 40px !important;
			height: 20px;
		}
		.login-bottom a{
			color: #489ae3;
		}
		.login-bottom a:hover{
			color: #ff6600;
		}
		.login-bottom .bottom-register{
			float: left;
		}
		.login-bottom .find-password{
			float: right;
		}
	</style>
</head>
<body style="background-color: white">
<%
	String applicantEmail = "";
	String applicantPwd = "";
// 从客户端读取Cookie
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if ("COOKIE_USEREMAIL".equals(cookie.getName())) {
				// 解密获取存储在Cookie中的求职者Email
				applicantEmail = com.jsu.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
			}
			if ("COOKIE_USERPWD".equals(cookie.getName())) {
				// 解密获取存储在Cookie中的求职者登录密码
				applicantPwd = com.jsu.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
			}
		}
	}
%>
<!-- 网站公共头部 -->
<jsp:include page="top2.jsp"></jsp:include>
<!-- 登录部分开始 -->
<div class="content-box">

	<div class="content-login">
		<div class="content-wraper">
			<div class="login-title">欢迎来到易易</div>
			<div class="content-login-box">
				<!-- 登录表单开始 -->
				<form action="UserLoginServlet" method="post"
					  onsubmit="return validate();">
					<div class="login_l">
						<p class="font14" style="color: gray">邮箱登录</p>
						<div >
							<input class="tn-textbox" type="text" name="email" placeholder="邮箱地址" id="email" value="<%=applicantEmail%>">
						</div>
						<div >
						<input class="tn-textbox" type="password" name="password" placeholder="密码" id="password" value="<%=applicantPwd%>">
						</div>
						<div class="form-button-area">
							<div >
								<input name="submit" type="submit" class="tn-button-text"
									   value="登   录">
								<div style="float: left;line-height: 30px;margin-left: 10px"> <input
									checked="checked" name="rememberMe" id="rememberMe"
									 type="checkbox" value="true">
								<label
									for="RememberMe" style="color: gray;"> 记住密码</label></div>
							</div>
						</div>
						<div class="login-bottom">
							<span class="bottom-register">
								尚未注册？<a href="">立即注册</a>
							</span>
							<span class="find-password"><a href="">找回密码</a></span>
						</div>
						<div class="clear"></div>
						<!-- 从拦截器中获取被拦截前的请求地址 -->
						<input type="hidden" name="requestPath" value="${requestScope.requestPath }">
					</div>
				</form>
				<!-- 登录表单结束 -->
			</div>
		</div>

	</div>

</div>
<!-- 登录部分结束 -->

<!-- 网站公共尾部 -->
<div class="main-footer">
	<div class="footer">
		<div class="footer-container">
			<div class="f-c-top">
				<div class="footer-block">
					<h1 class="footer-title">
						新闻互动
					</h1>
					<ul class="footer-menu">
						<li><a href="">新闻中心</a></li>
						<li><a href="">Yiyi达人</a></li>
						<li><a href="">Yiyi模型</a></li>
						<li><a href="">作品发表区</a></li>
						<li><a href="">讨论区</a></li>
					</ul>
				</div>
				<div class="footer-block">
					<h1 class="footer-title"> 相机手机</h1>
					<ul class="footer-menu">
						<li><a href="">相机资料馆</a></li>
						<li><a href="">镜头资料馆</a></li>
						<li><a href="">手机资料馆</a></li>
						<li><a href="">平板资料馆</a></li>
						<li><a href="">产品使用心得</a></li>
					</ul>
				</div>
				<div class="footer-block">
					<h1 class="footer-title">二手买卖</h1>
					<ul class="footer-menu">
						<li><a href=""> 买卖区首页</a></li>
						<li><a href=""> 摄影产品</a></li>
						<li><a href="">手机通讯</a></li>
						<li><a href="">电脑资讯</a></li>
						<li><a href="">影音家电</a></li>
						<li><a href="">手表潮流</a></li>
						<li><a href="">搜寻物品</a></li>
					</ul>
				</div>
				<div class="footer-block">
					<h1 class="footer-title">环球旅游</h1>
					<ul class="footer-menu">
						<li><a href="">摄影好去处</a></li>
						<li><a href="">旅游热卖</a></li>
						<li><a href="">目的地攻略</a></li>
						<li><a href="">旅游作品</a></li>
						<li><a href="">达人旅游分享</a></li>
					</ul>
				</div>
				<div class="footer-block">
					<h1 class="footer-title">shop</h1>
					<ul class="footer-menu">
						<li><a href="">门市资料</a></li>
						<li><a href="">最新货品</a></li>
						<li><a href="">热卖相机</a></li>
						<li><a href="">热卖镜头</a></li>
						<li><a href="">热卖DSLR</a></li>
						<li><a href="">热卖EVIL</a></li>
					</ul>
				</div>
				<div class="footer-block">
					<h1 class="footer-title">Yiyi</h1>
					<ul class="footer-menu">
						<li><a href="">关于我们</a></li>
						<li><a href="">使用条款</a></li>
						<li><a href="">隐私条例</a></li>
						<li><a href="">联络我们</a></li>
						<li><a href="">会员注册</a></li>
						<li><a href="">忘记密码</a></li>
					</ul>
				</div>
			</div>
			<div class="f-c-bottom">
				<h1>Copyright © 2020 DCFever.com. All rights reserved.</h1>
			</div>
		</div>
	</div>
</div>
</body>
</html>