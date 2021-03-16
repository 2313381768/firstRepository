<%@ page import="com.jsu.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jsu.dao.GoodsDAO" %>
<%@ page import="com.jsu.bean.Classes" %>
<%@ page import="com.jsu.dao.ClassesDAO" %><%--
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
<%
    String type=(String) request.getAttribute("type");
    System.out.println(type);
    GoodsDAO dao=new GoodsDAO();
    String tit;
    List<Goods> list=null;
    if ("cheaplist".equals(type)) {
        list = dao.getGoodsListByNewestCheapList();
        tit="减价区";
    }else if("newlist".equals(type)){
        list = dao.getGoodsListByNewest();
        tit="最新物品";
    }else {
        int classesId=(Integer) request.getAttribute("classesId");
        list = dao.getGoodsListByNewest(classesId);
        Classes classes=new ClassesDAO().getClassByClassID(classesId);
        tit=classes.getClassesName();
    }
    %>
<div class="body-area">
    <div class=body-center>
        <div class="title-model">
            <%=tit%>
        </div>
        <div class="goods">
           <%
                if (list != null)
                    for (Goods c : list) {
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
</div>
<div class="clear" style="height: 40px"></div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
