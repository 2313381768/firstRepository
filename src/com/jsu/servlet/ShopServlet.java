package com.jsu.servlet;

import com.jsu.bean.Goods;
import com.jsu.bean.User;
import com.jsu.dao.GoodsDAO;
import com.jsu.dao.OrderDAO;
import com.jsu.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShopServlet() {
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
        // 获取对商品信息处理的请求类型
        String type = request.getParameter("type");
        int goodsID = Integer.valueOf(request.getParameter("id")) ;
        User user=(User)request.getSession()
                .getAttribute("SESSION_USER");

        GoodsDAO dao2=new GoodsDAO();
        Goods goods=dao2.getGoodsByGoodsID(goodsID);
        UserDAO userDAO=new UserDAO();
        int sellerID=goods.getSellerId();
        boolean flag= dao2.isExistLove(goodsID,user.getId());
        System.out.println(flag);
        if ("buy".equals(type)&&goods.getState()==1&&sellerID!=user.getId()) {
            // 获取请求查询的商品编号
            OrderDAO dao=new OrderDAO();
            dao.saveOrder(user.getId(),goodsID,1);
            GoodsDAO dao3=new GoodsDAO();
            dao3.updateGoods(goodsID,2);
            // 请求转发
            request.getRequestDispatcher("OrderPageListServlet").forward(
                    request, response);
        }else if("love".equals(type)&&goods.getState()==1&&sellerID!=user.getId()&&!flag){
            // 获取请求查询的商品编号
            GoodsDAO dao=new GoodsDAO();
            dao.loveGoods(user.getId(),goodsID);
            // 请求转发
            request.getRequestDispatcher("GoodsServlet?type=select&id="+goodsID).forward(
                    request, response);
        }else {
            request.getRequestDispatcher("GoodsServlet?type=select&id="+goodsID).forward(
                    request, response);
        }
    }

}
