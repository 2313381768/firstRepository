<%@ page import="com.jsu.bean.Goods" %>
<%@ page import="com.jsu.dao.CommentDAO" %>
<%@ page import="com.jsu.bean.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jsu.dao.UserDAO" %>
<%@ page import="com.jsu.bean.User" %>
<%@ page import="com.jsu.dao.ClassesDAO" %>
<%@ page import="com.jsu.bean.Classes" %>
<%@ page import="com.jsu.dao.GoodsDAO" %><%--
  Created by IntelliJ IDEA.
  User: 开心点，伙计！
  Date: 2020/12/31
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ClassesDAO dao3=new ClassesDAO();
    User seller=(User)request.getSession()
            .getAttribute("SESSION_USER");
%>
<head>
    <meta charset="UTF-8">
    <title>出售物品</title>
    <link rel="stylesheet" type="text/css" href="../css/base.css"/>
    <link rel="stylesheet" type="text/css" href="../css/sellGoods.css"/>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <script>
        function showD(){
            $("#sell-discount").css("display","block");
        }
        function hideD(){
            $("#sell-discount").css("display","none");
        }
    </script>
</head>
<body>
<jsp:include page="../top2.jsp"></jsp:include>

<div class="sell-g-main">
    <div class="sell-g-wraper">
        <form action="GoodsServlet?type=add" method="post">
            <div class="row-d1">
                <h1 style="font-size: 25px;color: #ff6600">出售物品</h1>
            </div>
            <div class="row-d1">
                <label class="sell-label">物品名称:</label><input class="sell-input" type="text" name="gName" id="gName">
            </div>
            <div class="row-d1">
                <label class="sell-label">物品价格:</label><input class="sell-input" type="text" name="gPrice" id="gPrice">
            </div >
            <div class="row-d1">
                <div class="dropdown">
                    <label class="sell-label">物品分类:</label>
                    <select class="sell-select" name="gClasses">
                        <option value="1">摄影产品</option>
                        <option value="2">手机通讯</option>
                        <option value="3">电脑</option>
                        <option value="4">影音产品</option>
                        <option value="5">游戏机、模型</option>
                        <option value="6">电器家具</option>
                        <option value="7">潮流服饰</option>
                        <option value="8">手表</option>
                        <option value="9">单车及运动</option>
                        <option value="10">其他</option>
                    </select>
                </div>
            </div>
            <div class="row-d1">
                <label class="sell-label" >物品描述:</label>
                <div style="clear: both"></div>
                <textarea id="sell-discribe" class="sell-discribe" name="gDescribe" id="gDescribe">
                </textarea>
            </div>
            <div class="row-d1">
                <label class="sell-label">设置折扣:</label><input class="sell-radio" type="radio" name="discount" value="1" onclick="hideD()"><label class="sell-label" >否</label>
                <input class="sell-radio" type="radio" name="discount" value="2" onclick="showD()"><label class="sell-label" >是</label>
            </div>
            <div style="clear: both"></div>
            <div class="row-d1" id="sell-discount" style="display: none">
                <label class="sell-label" >折扣价格:</label><input class="sell-input" type="text" name="gDiscount" id="gDiscount">
            </div>
            <div class="row-d1">
                <input name="submit" type="submit" class="submit-btn"
                       value="提 交">
            </div>
        </form>
    </div>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
