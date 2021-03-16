package com.jsu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(
		urlPatterns = { "/buy/*"},
		servletNames = {"com.jsu.servlet.GoodsServlet","com.jsu.servlet.OrderServlet","com.jsu.servlet.OrderPageListServlet","com.jsu.servlet.SellGoodsServlet","com.jsu.servlet.ShopServlet","com.jsu.servlet.UserBaseInfoServlet","com.jsu.servlet.UserHeadPicServlet"},
		initParams = { @WebInitParam(name = "loginPage", value = "login.jsp") }, 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserAuthorityFilter implements Filter {

	private String loginPage = "login.jsp";

	public UserAuthorityFilter() {

	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		loginPage = fConfig.getInitParameter("loginPage");
		if (null == loginPage) {
			loginPage = "login.jsp";
		}
	}
	@Override
	public void destroy() {
		this.loginPage = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("SESSION_USER") == null) {
			String requestPath = req.getRequestURI();
			if (req.getQueryString() != null) {
				requestPath += "?" + req.getQueryString();
			}
			req.setAttribute("requestPath", requestPath);
			request.getRequestDispatcher( "/" + loginPage)
					.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}
