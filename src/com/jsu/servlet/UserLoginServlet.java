package com.jsu.servlet;


import com.jsu.bean.User;
import com.jsu.dao.UserDAO;
import com.jsu.utils.CookieEncryptTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 求职者登录功能实现
 *
 * @author QST青软实训
 *
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLoginServlet() {
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
        PrintWriter out = response.getWriter();
        // 获取请求参数
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String requestPath = request.getParameter("requestPath");
        // 登录验证
        UserDAO dao = new UserDAO();
        int userID = dao.login(email, password);
        if (userID != 0) {
            // 用户登录成功,将求职者信息存入session
            User user = dao.findByUserID(userID);
            request.getSession().setAttribute("SESSION_USER", user);
            // 通过Cookie记住邮箱和密码
            rememberMe(rememberMe, email, password, request, response);
            //判断是否已存在请求路径
            if(!"".equals(requestPath) && null!=requestPath){
                response.sendRedirect(requestPath);
            }else {
                response.sendRedirect("index.jsp");
            }
        } else {
            // 用户登录信息错误，进行错误提示
            out.print("<script type='text/javascript'>");
            out.print("alert('用户名或密码错误，请重新输入！');");
            out.print("window.location='login.jsp';");
            out.print("</script>");
        }
    }

    private void rememberMe(String rememberMe, String email, String password,
                            HttpServletRequest request, HttpServletResponse response) {
        // 判断是否需要通过Cookie记住邮箱和密码
        if ("true".equals(rememberMe)) {
            // 记住邮箱及密码
            Cookie cookie = new Cookie("COOKIE_USEREMAIL",
                    CookieEncryptTool.encodeBase64(email));
            cookie.setPath("/");
            cookie.setMaxAge(365 * 24 * 3600);
            response.addCookie(cookie);

            cookie = new Cookie("COOKIE_USERPWD",
                    CookieEncryptTool.encodeBase64(password));
            cookie.setPath("/");
            cookie.setMaxAge(365 * 24 * 3600);
            response.addCookie(cookie);
        } else {
            // 将邮箱及密码Cookie清空
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("COOKIE_USEREMAIL".equals(cookie.getName())
                            || "COOKIE_USERPWD".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }

}
