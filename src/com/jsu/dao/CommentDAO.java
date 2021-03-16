package com.jsu.dao;

import com.jsu.bean.Comment;
import com.jsu.bean.Goods;
import com.jsu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public List<Comment> getGoodsComment(int goodsId){
        List<Comment> list=new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT USER_SELLCOUNT,u.USER_ID,g.GOODS_ID,USER_NAME,GOODS_COMMENT,USER_HEADPIC\n" +
                    "FROM yy_user u,yy_goods g,yy_evaluate e\n" +
                    "WHERE u.USER_ID=e.USER_ID and e.GOODS_ID=g.GOODS_ID\n" +
                    "and g.GOODS_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,goodsId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setUserId(rs.getInt("user_id"));
                comment.setGoodsId(rs.getInt("goods_id"));
                comment.setComment(rs.getString("goods_comment"));
                comment.setHeadPic(rs.getString("user_headpic"));
                comment.setUserName(rs.getString("user_name"));
                comment.setSellcount(rs.getInt("user_sellcount"));
                list.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return list;
    }
}
