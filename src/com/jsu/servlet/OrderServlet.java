package com.jsu.servlet;

import com.jsu.bean.Goods;
import com.jsu.bean.Order;
import com.jsu.dao.GoodsDAO;
import com.jsu.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 开心点，伙计！
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        int orderID = Integer.valueOf(request.getParameter("id")) ;
        OrderDAO dao=new OrderDAO();
        Order order=dao.getOrderByID(orderID);
        GoodsDAO dao3=new GoodsDAO();
        if("confirm".equals(type)){
            dao.updateOrder(orderID, 0);
            dao3.updateGoods(order.getGoodsId(),0);
        }else if("cancel".equals(type)){
            dao.updateOrder(orderID,2);
            dao3.updateGoods(order.getGoodsId(),1);
        }else if("delete".equals(type)){
            dao.deleteOrder(orderID);
            Goods goods=dao3.getGoodsByGoodsID(order.getGoodsId());
            if(goods.getState()==2){
                dao3.updateGoods(order.getGoodsId(),1);
            }
            dao.deleteOrder(orderID);
        }
        response.sendRedirect("OrderPageListServlet");
    }
}
