<%@ page import="com.jsu.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:https="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易易-二手买卖上易易！</title>
<link rel="shortcut icon" href="img\favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="css/indexTop.css"/>
<link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="css/base.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/base.js" defer></script>
</head>
<body style="margin: 0;padding: 0">
<% User user=(User) request.getSession().getAttribute("SESSION_USER");%>
<div class="header-area">
    <!--  头上部 -->
    <div class="header-top">
        <div class="container">
            <ul class="yy-quick-menu">
                <li><a href="index.jsp">首页</a></li>
                <li><a href="">新闻中心</a></li>
                <li><a href="">相机镜头</a></li>
                <li><a href="">手机平板</a></li>
                <li><a href="">汽车热话</a></li>
                <li><a href="">二手买卖</a></li>
                <li><a href="">摄影旅游</a></li>
                <li><a href="">作品发布</a></li>
                <li><a href="">更多</a></li>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
    <div class="header-bottom">

        <h1 class="logo">
            <a href="javascript:" ></a>
        </h1>
        <div class="box-top" >

            <h1 >
                <a href="index.jsp">二手集市</a>
            </h1>
            <ul class="user-item">
                <li class="li-item"><a href="login.jsp">会员登录</a></li>
                <li class="li-item"><a href="register.jsp">会员注册</a></li>
                <li class="li-item my-center">
                    个人中心
                    <i id="center-id" class="fa fa-angle-down" aria-hidden="true" style="color: #999;margin-left: 3px"></i>
                    <ul class="menu-my">
                        <li>
                            <a href="OrderPageListServlet">历史订单</a>
                        </li>
                        <li>
                            <a href="buy/userBaseInfo.jsp">个人信息</a>
                        </li>
                        <li>
                            <a  href="javascript:;">账户安全</a>
                        </li>
                        <li>
                            <a href="javascript:;">地址管理</a>
                        </li>
                        <li>
                            <a  href="javascript:;">退出登录</a>
                        </li>
                    </ul>
                </li>
                <li class="li-item"><a href="">我的收藏</a></li>
                <%if(user!=null){%>
                <li style=" float: right;
    margin-left: 5px;
    margin-right: 5px;
    text-align: center;
    display: block;
    width: 160px;
    color: #2f8fca;
    font-size: 16px;
    cursor: pointer;">你好，<a href="" style="color: #2f8fca;"><%=user.getName()%></a></li>
                <%}%>
            </ul>
        </div>
        <div class="box-bottom">
            <ul class="use-menu">
                <li><a href="index.jsp">主页</a></li>
                <li><a href="">使用规则</a></li>
                <li><a href="">常见问题</a></li>
                <li>
                    <div class="header-search">
                        <div class="search-bg">
                            <input type="text " placeholder="搜索物品" class="search-input">
                        </div>
                        <a class="search-btn">
                            <span style="color: white">搜索</span>
                        </a>
                    </div>
                </li>
                <li class="sell" >
                    <a href="SellGoodsServlet?type=step1">出售物品</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
