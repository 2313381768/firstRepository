package com.jsu.servlet;

import com.jsu.bean.User;
import com.jsu.dao.GoodsDAO;
import com.jsu.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * @author 开心点，伙计！
 */
@WebServlet("/SellGoodsServlet")
@MultipartConfig
public class SellGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SellGoodsServlet() {
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
        if(type.equals("step1")) {
            request.getRequestDispatcher("buy/sellGoods.jsp").forward(
                    request, response);
        }else if(type.equals("addPic")){
            // 获取上传文件域
            Part part = request.getPart("gPic");
            System.out.println(part);
            // 获取上传文件名称
            String cd = part.getHeader("Content-Disposition");
            //截取不同类型的文件需要自行判断
            String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
            System.out.println(fileName);
            // 为防止上传文件重名，对文件进行重命名
            String newFileName = System.currentTimeMillis()
                    + fileName.substring(fileName.lastIndexOf("."));
            // 将上传的文件保存在服务器项目发布路径的applicant/images目录下
            String filepath = getServletContext().getRealPath("images");
            File f = new File(filepath);
            if (!f.exists()) {
                f.mkdirs();
            }
            part.write(filepath + "/" + newFileName);
            // 从会话对象中获取当前用户简历标识
            int goodsId=Integer.valueOf(request.getParameter("goodsId"));
            // 更新简历照片
            System.out.println("头像保存路径为：" + goodsId);
            GoodsDAO dao=new GoodsDAO();
            dao.updateGoodsPic(goodsId, newFileName);
            // 照片更新成功，回到“我的简历”页面
            response.sendRedirect("GoodsServlet?type=select&id="+goodsId);
        }
    }
}
