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

/**
 * 企业信息处理Servlet
 *
 * @author QST青软实训
 *
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoodsServlet() {
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
		if ("select".equals(type)) {
			// 获取请求查询的商品编号
			int goodsID = Integer.valueOf(request.getParameter("id")) ;
			// 根据商品编号查询商品详细信息
			GoodsDAO dao = new GoodsDAO();
			Goods goods = dao.getGoodsByGoodsID(goodsID);
			// 将查询到的商品信息存入request请求域
			request.setAttribute("goods", goods);
			// 请求转发
			request.getRequestDispatcher("buy/shop.jsp").forward(
					request, response);
		}else if("add".equals(type)){
			User user=(User)request.getSession()
					.getAttribute("SESSION_USER");
			String gName=request.getParameter("gName");
			String gPrice=request.getParameter("gPrice");
			String gClasses=request.getParameter("gClasses");
			String gDescribe=request.getParameter("gDescribe");
			String gDiscount=request.getParameter("gDiscount");
			Goods goods=new Goods();
			goods.setLooks(0);
			goods.setDescribe(gDescribe);
			goods.setClassesId(Integer.valueOf(gClasses));
			if(gDiscount!=null&&!"".equals(gDiscount)) {
				goods.setDiscount(Integer.valueOf(gDiscount));
			}
			if(gPrice!=null&&!"".equals(gDiscount)) {
				goods.setPrice(Integer.valueOf(gPrice));
			}
			goods.setName(gName);
			goods.setState(1);
			goods.setSellerId(user.getId());
			new GoodsDAO().saveGoods(goods);
			request.getRequestDispatcher("index.jsp").forward(
					request, response);
		}else if("goodslist".equals(type)){
			int classesId = Integer.valueOf(request.getParameter("id")) ;
			request.setAttribute("classesId",classesId);
			// 请求转发
			request.getRequestDispatcher("classesList.jsp").forward(
					request, response);
		}else if("newlist".equals(type)){
			request.setAttribute("newlist",type);
			request.getRequestDispatcher("classesList.jsp").forward(
					request, response);
		}else if("cheaplist".equals(type)){
			request.setAttribute("cheaplist",type);
			request.getRequestDispatcher("classesList.jsp").forward(
					request, response);
		}
	}

}
