<%@ page import="com.jsu.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jsu.dao.GoodsDAO" %><%--
  Created by IntelliJ IDEA.
  User: 开心点，伙计！
  Date: 2020/12/26
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" xmlns:https="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易易-二手买卖上易易！</title>
<link rel="shortcut icon" href="img\favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="css/indexBody.css"/>
<link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="css/slideCss.css"/>
<link rel="stylesheet" type="text/css" href="css/base.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/slide.js" defer></script>
</head>
<body style="margin: 0;padding: 0">
<jsp:include page="top.jsp"></jsp:include>
<div class="body-area">
    <div class="body-box">
        <div class="classes-box">
            <h1  class="classes-tittle">
                <i class="fa fa-bars" aria-hidden="true"></i>
                分类
            </h1>
            <ul class="goods-classes">
                <li><a href="GoodsListServlet?type=goodslist&id=1">摄影产品</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=2">手机通讯</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=3">电脑</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=4">影音产品</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=5">游戏机、模型</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=6">电器家具</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=7">潮流服饰</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=8">手表</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=9">单车及运动</a></li>
                <li><a href="GoodsListServlet?type=goodslist&id=10">其他</a></li>
            </ul>
        </div>
        <div class="slide-box">
            <div class="slide-main">
                <div class="slide">
                    <div class="slide-bd">
                        <div class="imgdiv" style="width: 900px; height: 450px;">
                            <ul style="left: 0;">
                                <li><img src="./images/slide06.jpg" alt=""></li>
                                <li><img src="./images/slide01.jpg" alt=""></li>
                                <li><img src="./images/slide02.jpg" alt=""></li>
                                <li><img src="./images/slide03.jpg" alt=""></li>
                                <li><img src="./images/slide04.jpg" alt=""></li>
                                <li><img src="./images/slide05.jpg" alt=""></li>
                            </ul>
                            <!--
                            左右选择按钮-->
                            <span class="lf"><i class="fa fa-chevron-left " aria-hidden="true" style="color: #09bf4d;"></i></span>
                            <span class="rf"><i class="fa fa-chevron-right" aria-hidden="true" style="color: #09bf4d;"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class=body-center>
        <div class="title-model">
            最新物品
            <div class="more">
                <a href="GoodsListServlet?type=newlist">更多<i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>

        <div class="newest-goods">
            <%
                GoodsDAO dao=new GoodsDAO();
                List<Goods> newestList=dao.getGoodsListByNewestFive();
                if (newestList != null)
                    for (Goods c : newestList) {
            %>
            <div class="model-box">
                <a href="GoodsServlet?type=select&id=<%=c.getId() %>">
                    <div class="model-box-inner">
                        <div class="image_wrapper">
                            <img src="./images/<%if(c.getPictrues().size()!=0){%>
<%=c.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>" class="item_image lazyloadx" style="position: absolute; top: 0px; left: 0px;">
                        </div>
                        <div class="info_wrapper">
                            <div class="trade_title"><%=c.getName()%>
                            </div>
                            <div class="trade_info">
                                    <span class="price">￥<%= c.getPrice()%></span>
                            </div>
                        </div>
                       </div>

                </a>
            </div>
            <% }%>
        </div>
        <div class="clear"></div>
        <div class="title-model">
            最新手机
            <div class="more">
                <a href="GoodsListServlet?type=goodslist&id=2">更多<i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>

        <div class="newes-iphone">
            <%
                List<Goods> phoneList = dao.getGoodsListByNewestFive(2);
                if (phoneList != null)
                    for (Goods c : phoneList) {
            %>
            <div class="model-box">
                <a href="GoodsServlet?type=select&id=<%=c.getId() %>">
                    <div class="model-box-inner">
                        <div class="image_wrapper">
                            <img src="./images/<%if(c.getPictrues().size()!=0){%>
<%=c.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>
" class="item_image lazyloadx" style="position: absolute; top: 0px; left: 0px;">
                        </div>
                        <div class="info_wrapper">
                            <div class="trade_title"><%=c.getName()%>
                            </div>
                            <div class="trade_info">
                                <span class="price">￥<%= c.getPrice()%></span>
                            </div>
                        </div>
                    </div>

                </a>
            </div>
            <% }%>
        </div>
        <div class="clear"></div>
        <div class="title-model">
            最新游戏机及游戏软件
            <div class="more">
                <a href="GoodsListServlet?type=goodslist&id=5">更多<i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>

        <div class="newes-psp">
            <%
                List<Goods> pspList =dao.getGoodsListByNewestFive(5);
                if (pspList != null)
                    for (Goods c : pspList) {
            %>
            <div class="model-box">
                <a href="GoodsServlet?type=select&id=<%=c.getId() %>">
                    <div class="model-box-inner">
                        <div class="image_wrapper">
                            <img src="./images/<%if(c.getPictrues().size()!=0){%>
<%=c.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>" class="item_image lazyloadx" style="position: absolute; top: 0px; left: 0px;">
                        </div>
                        <div class="info_wrapper">
                            <div class="trade_title"><%=c.getName()%>
                            </div>
                            <div class="trade_info">
                                <span class="price">￥<%= c.getPrice()%></span>
                            </div>
                        </div>
                    </div>

                </a>
            </div>
            <% }%>
        </div>
        <div class="clear"></div>
        <div class="title-model">
            最新减价物品
            <div class="more">
                <a href="GoodsListServlet?type=cheaplist&goodslist">更多<i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>
        <div class="newes-cheapest">
            <%
                List<Goods> cheapestList = dao.getGoodsListByNewestCheapFive();
                if (cheapestList != null)
                    for (Goods c : cheapestList) {
            %>
            <div class="model-box">
                <a href="GoodsServlet?type=select&id=<%=c.getId() %>">
                    <div class="model-box-inner">
                        <div class="image_wrapper">
                            <img src="./images/<%if(c.getPictrues().size()!=0){%>
<%=c.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>" class="item_image lazyloadx" style="position: absolute; top: 0px; left: 0px;">
                        </div>
                        <div class="info_wrapper">
                            <div class="trade_title"><%=c.getName()%>
                            </div>
                            <div class="trade_info">
                                <span class="price">￥<%= c.getPrice()%></span>
                            </div>
                        </div>
                    </div>

                </a>
            </div>
            <% }%>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="clear" style="height: 40px"></div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
