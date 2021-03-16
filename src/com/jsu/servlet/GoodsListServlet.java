package com.jsu.servlet;


import com.jsu.bean.Goods;
import com.jsu.bean.User;
import com.jsu.dao.GoodsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodsListServlet() {
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        System.out.println(type);
        if("goodslist".equals(type)){
            int classesId = Integer.valueOf(request.getParameter("id")) ;
            request.setAttribute("classesId",classesId);
            // 请求转发
            request.getRequestDispatcher("classesList.jsp").forward(
                    request, response);
        }else if("newlist".equals(type)){
            request.setAttribute("type",type);
            request.getRequestDispatcher("classesList.jsp").forward(
                    request, response);
        }else if("cheaplist".equals(type)){
            request.setAttribute("type",type);
            request.getRequestDispatcher("classesList.jsp").forward(
                    request, response);
        }
    }

}
