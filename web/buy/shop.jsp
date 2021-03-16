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
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/shop.css"/>

</head>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<%
    Goods goods = (Goods) request.getAttribute("goods");
    CommentDAO dao=new CommentDAO();
    UserDAO dao2=new UserDAO();
    ClassesDAO dao3=new ClassesDAO();
    List<Comment> list=dao.getGoodsComment(goods.getId());
    User seller=dao2.findByGoodsID(goods.getId());
    Classes gClasses =dao3.getClassByClassID(goods.getClassesId());
    User user=(User)request.getSession().getAttribute("SESSION_USER");
    int loginId= user.getId();
%>
<div class="shop-main">
    <div class="shop-wraper">
        <div class="shop-area">
            <div class="shop-box-title">
                <a href="">买卖区</a>
                <i class="fa fa-caret-right"></i>
                <a href=""><%= gClasses.getClassesName()%></a>
            </div>
            <div class="shop-box" >
                <div class="shop-img">
                    <%if(goods.getSellerId()==loginId){%>
                    <a href="/buy/GoodsPicUpload.jsp?goodsId=<%=goods.getId()%>">
                    <img src="./images/<%if(goods.getPictrues().size()!=0){%>
<%=goods.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>" alt="">
                    </a><%}else {%>
                    <img src="./images/<%if(goods.getPictrues().size()!=0){%>
<%=goods.getPictrues().get(0)%>
<%}else{%>默认图片.png<%}%>" alt="">
                    <%}%>
                </div>
                <div class="shop-img-right">
                    <div class="shop-title">
                        <span style="color: #ff6600">出售</span>
                        <span><%= goods.getName()%></span>
                    </div>
                    <div class="shop-price">
                        <span style="color: #ff6600">价格</span>
                        <% if(goods.getDiscount()!=0){%>
                        <span>￥<%= goods.getDiscount()%></span>
                        <span style="text-decoration: line-through;font-size: 18px;color: #999">￥<%= goods.getPrice()%></span>
                        <%}else{%>
                        <span>￥<%= goods.getPrice()%></span>
                        <%} %>
                    </div>
                    <div class="seller">
                        <div class="d-name">卖家</div>
                        <div class="d-value"><a href="" class="user-name" tyle="font-size: 16px!important;"><span class="span-name" ><%= seller.getName()%></span><span class="span-sellcount">(<%= seller.getSellcount()%>)</span></a></div>
                    </div>
                    <div class="looks">
                        <div class="d-name">查看次数</div>
                        <div class="d-value"><%= goods.getLooks()%></div>
                    </div>
                    <div class="up-time">
                        <div class="d-name">上架时间</div>
                        <div class="d-value"><%= goods.getUpTime()%></div>
                    </div>
                    <div class="shop-state">
                        <div class="d-name">物品状态</div>
                        <div class="d-value" id="g-state">
                            <% if(goods.getState()==1){%>
                            进行中
                            <%}else if(goods.getState()==2){%>
                            等待确认订单
                            <%}else {%>
                            已结束
                            <% }%>
                        </div>
                    </div>
                    <div class="shop-classes">
                        <div class="d-name">分类</div>
                        <div class="d-value"><%= gClasses.getClassesName()%></div>
                    </div>
                    <div class="btn-box-buy">
                        <a id="buyIt" href="ShopServlet?type=buy&id=<%=goods.getId()%>" onclick="<%=goods.getState()%>">购买物品</a>
                    </div>
                    <div class="btn-box-love">
                        <a  id="loveIt" href="ShopServlet?type=love&id=<%=goods.getId()%>">收藏物品</a>
                    </div>
                    <script>
                        <% if(goods.getState()!=1){%>
                        $("#buyIt").attr("disabled",true);
                        $("#loveIt").attr("disabled",true);
                        $("#g-state").css("color","#ff6600")
                        $("#buyIt").css("background-color","gray")
                        $("#loveIt").css("background-color","gray").css("color","white")
                        <%}%>
                    </script>
                </div>

            </div>
        </div>
        <div class="goods-describe">
            <div class="con-box">
                <div class="con-box-inner">
                    <div class="con-box-title">
                        <h1 class="con-h1">物品介绍</h1>
                    </div>
                    <div class="con-box-content">
                        <div class="describe-content">
                            <div class="describe-text-area">
                                <%= goods.getDescribe()%>
                            </div>
                            <div class="img-area">
                                <% for (String path:goods.getPictrues()){;%>
                                <div class="img-out-box">
                                    <div class="img-inner-box">
                                        <img src="./images/<%=path%>" alt="">
                                    </div>
                                </div>
                                <%}%>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="tips">
                            小提醒：建议线上交易，若要求私下交易，請小心留意。若有诈骗行为，请及时举报。
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="clear"></div>
        <div class="goods-comment">
                <div class="con-box">
                    <div class="con-box-inner">
                    <div class="con-box-title">
                        <h1 class="con-h1">物品评价</h1>
                    </div>
                    <div class="con-box-content">
                        <div class="comment-content">
                            <ul class="comment-menu">
                                <% for(Comment comment:list){%>
                                <li class="con-li">
                                    <div class="li-inner-box">
                                        <div style="overflow: hidden;float: left">
                                            <div class="head-img">

                                                <img src="./images/<% if(comment.getHeadPic()==null){ %><%= "profile_pic.png"%><%}else {%><%= comment.getHeadPic()%><% }%>" alt="">

                                            </div>
                                            <div class="head-img-right">
                                                <div class="user-name">
                                                    <a href=""><span class="span-name">
                                                <% if (comment.getUserName()!=null){%>
                                                <%= comment.getUserName()%>
                                                <%}else{%>
                                                易易用户
                                                <%}%>
                                            </span>
                                                        <span class="span-sellcount">(<%= comment.getSellcount()%>)</span></a>
                                                </div>
                                                <div class="user-conment">
                                                    <%= comment.getComment()%>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
