<%@ page import="com.jsu.bean.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 开心点，伙计！
  Date: 2021/1/1
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
    List<Order> list=(List<Order>)request.getAttribute("orders");
    %>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script>
        $(function(){
            $("td>h1>a").addClass("btn btn-default btn-xs");

        });
        $(function() {
            $("#order-title").addClass("focus").css("pointer-events","none");
        });
    </script>
    <style>
        .order-tr td{
            height: 80px;
        }
        td{
            text-align: center;
        }
        h1{
            font-size: 14px;
        }
        .btn-group-xs>.btn, .btn-xs{
            font-size: 16px;
        }
        .footer-menu{
            padding-left: 0;!important;
        }
    </style>
</head>
<body>
<jsp:include page="../top2.jsp"></jsp:include>
<div style="position: relative">
    <h1 style="margin: 44px auto;text-align:center;width:1000px;font-size: 20px;margin-left: 100px;color: #ff6600;display: block;border-bottom: solid 1px #ddd;padding-bottom: 20px">我的订单</h1>
    <table style=" margin:44px auto"
           class="table table-striped table-bordered table-hover  table-condensed"
           align='center' border='1' cellspacing='0'>
        <tr id="order-title">
            <td><h1>订单编号</h1></td>
            <td><h1>订单创建时间</h1></td>
            <td><h1>物品详情</h1></td>
            <td><h1>卖家</h1></td>
            <td><h1>订单状态</h1></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <% for(Order order:list){%>
            <tr class="order-tr">
                <td><h1><%=order.getId()%></h1></td>
                <td><h1><%=order.getCreateTime()%></h1></td>
                <td style=";margin: 0;height: 90px;width: 240px">
                    <a href="GoodsServlet?type=select&id=<%=order.getGoodsId()%>" style="position: relative;overflow: hidden;float: left">
                        <div style="width: 80px;height: 80px;float: left;overflow: hidden">
                            <img src="./images/<%=order.getPicPath()%>" style="width: 80px" alt="">
                        </div>
                        <div style="float: left;margin-left: 5px;height: 80px;width: 120px">
                            <div style="font-size: 14px;word-wrap: break-word;height: 42px;overflow: hidden"><%=order.getGoodsName()%>></div>
                            <div style="font-size: 14px;margin-top: 10px;color:#ff830b">￥<%=order.getPrice()%></div>
                        </div>
                    </a>
                </td>
                <td><h1><a href="" style="border: none;color: #3B99FC;"><%= order.getSellerName()%></a></h1></td>
                <td><h1>
                    <% if(order.getState()==1){%>
                    等待确认
                    <%}else if(order.getState()==0){%>
                    订单已完成
                    <% }else if(order.getState()==2){%>
                    订单已取消
                    <%}%>
                </h1></td>

                <td><h1>
                    <% if(order.getState()!=0&&order.getState()!=2){%>
                    <a  href="OrderServlet?type=confirm&id=<%=order.getId()%>">确认订单</a>
                    <%}%>
                </h1></td>
                <td><h1>
                    <% if(order.getState()!=0&&order.getState()!=2){%>
                    <a href="OrderServlet?type=cancel&id=<%=order.getId()%>">取消订单</a>
                    <%}%>
                </h1></td>

                <td><h1><a href="OrderServlet?type=delete&id=<%=order.getId()%>">删除订单</a></h1></td>
            </tr>
        <%}%>
    </table>
    <nav>
        <ul class="pager">
            <li><a href="OrderPageListServlet?start=0">首  页</a></li>
            <li><a href="OrderPageListServlet?start=<%=request.getAttribute("pre")%>">上一页</a></li>
            <li><a href="OrderPageListServlet?start=<%=request.getAttribute("next")%>">下一页</a></li>
            <li><a href="OrderPageListServlet?start=<%=request.getAttribute("last")%>">末  页</a></li>
        </ul>
    </nav>
</div>

<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>