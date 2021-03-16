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
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 开心点，伙计！
 */
@WebServlet("/UserBaseInfoServlet")
public class UserBaseInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserBaseInfoServlet() {
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
// 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取请求操作类型
        String type = request.getParameter("type");
        if("update".equals(type)){
            // 封装请求数据
           User user=(User)request.getSession().getAttribute("SESSION_USER");
            String name=request.getParameter("realName");
            String sex=request.getParameter("gender");
            String phone=request.getParameter("telephone");
            String birth=request.getParameter("birthday");
            UserDAO dao=new UserDAO();
            user.setName(name);
            user.setSex(sex);
            user.setPhone(phone);
            user.setAge(birth);
            dao.updateUserBaseInfo(user);
            request.getRequestDispatcher("buy/userBaseInfo.jsp").forward(request, response);
        }
    }

}
