package com.jsu.servlet;


import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求职者登录功能实现
 *
 * @author QST青软实训
 *
 */
@WebServlet("/ServletText")
public class ServletText extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletText() {
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
        response.setContentType("text/html;charset=UTF-8");
        String email=request.getParameter("email");
        System.out.println(email);
        String password=request.getParameter("password");
        System.out.println(password);
    }

}
