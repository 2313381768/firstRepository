package com.jsu.dao;

import com.jsu.bean.Goods;
import com.jsu.bean.Order;
import com.jsu.bean.User;
import com.jsu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 开心点，伙计！
 */
public class OrderDAO {
    public void saveOrder(int userID, int goodsID,int state){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO yy_order(goods_id,user_id,order_state,order_createtime) VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,goodsID);
            pstmt.setInt(2,userID);
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(3,state);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    public void updateOrder(int orderID,int state){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE yy_order SET order_state = ? WHERE order_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,state);
            pstmt.setInt(2,orderID);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    public void deleteOrder(int orderID){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "DELETE FROM yy_order WHERE order_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,orderID);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    public List<Order> getOrderList(int userID) {
        return getOrderList(0, Short.MAX_VALUE,userID);
    }
    public List<Order> getOrderList(int start, int count,int userID){
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * FROM yy_order WHERE user_id=? order by order_createtime  desc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userID);
            pstmt.setInt(2,start);
            pstmt.setInt(3,count);
            UserDAO uDao=new UserDAO();
            GoodsDAO gDAO=new GoodsDAO();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order=new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setGoodsId(rs.getInt("goods_id"));
                order.setCreateTime(rs.getTimestamp("order_createtime"));
                Goods goods=gDAO.getGoodsByGoodsID(order.getGoodsId());

                User seller=uDao.findByUserID(goods.getSellerId());
                order.setGoodsName(goods.getName());
                if (goods.getPictrues().size()!=0) {
                    order.setPicPath(goods.getPictrues().get(0));
                }
                order.setSellerName(seller.getName());
                order.setState(rs.getInt("order_state"));
                order.setPrice(goods.getPrice());
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return list;
    }
    public Order getOrderByID(int orderID){
        Order order=new Order();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * FROM yy_order WHERE order_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,orderID);
            UserDAO uDao=new UserDAO();
            GoodsDAO gDAO=new GoodsDAO();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setGoodsId(rs.getInt("goods_id"));
                order.setCreateTime(rs.getTimestamp("order_createtime"));
                Goods goods=gDAO.getGoodsByGoodsID(order.getGoodsId());
                User seller=uDao.findByUserID(goods.getSellerId());
                order.setGoodsName(goods.getName());
                order.setPicPath(goods.getPictrues().get(0));
                order.setSellerName(seller.getName());
                order.setState(rs.getInt("order_state"));
                order.setPrice(goods.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return order;
    }
}
