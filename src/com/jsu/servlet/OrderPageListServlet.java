package com.jsu.servlet;

import com.jsu.bean.Order;
import com.jsu.bean.User;
import com.jsu.dao.OrderDAO;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/OrderPageListServlet")
public class OrderPageListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderPageListServlet() {
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
        int start = 0;
        int count = 5;
        User user=(User)request.getSession().getAttribute("SESSION_USER");
        System.out.println(user.getId()+"-------------");
        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数start时
        }
        int next = start;
        if (start + count < new OrderDAO().getOrderList(user.getId()).size()) {
            next = start + count;
        }
        int pre = start;
        if (start - count >= 0) {
            pre = start - count;
        }
        int last = new OrderDAO().getOrderList(user.getId()).size() - new OrderDAO().getOrderList(user.getId()).size() % 5;

        List<Order> order = new OrderDAO().getOrderList(start, count,user.getId());
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);
        request.setAttribute("orders", order);
        request.getRequestDispatcher("buy/order.jsp").forward(request, response);
    }
}
