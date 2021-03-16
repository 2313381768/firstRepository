package com.jsu.dao;


import com.jsu.bean.User;
import com.jsu.utils.DBUtils;

import java.sql.*;

public class UserDAO {

    /**
     * 验证Email是否已被注册
     *
     * @return
     */
    public boolean isExistEmail(String email) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM yy_user WHERE user_email=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close( conn, pstmt,rs);
        }
        return false;
    }
    public User findByUserID(int userID) {
        User user=new User();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM yy_user WHERE user_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setAge(rs.getString("user_age"));
                user.setEmail(rs.getString("user_email"));
                user.setHeadPic(rs.getString("user_headpic"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setSex(rs.getString("user_sex"));
                user.setPhone(rs.getString("user_phone"));
                user.setSellcount(rs.getInt("user_sellcount"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close( conn, pstmt,rs);
        }
        return user;
    }
    public User findByGoodsID(int goodsID) {
        User user=new User();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT u.*\n" +
                "FROM yy_goods g,yy_user u\n" +
                "WHERE  g.USER_ID=u.USER_ID\n" +
                "and g.GOODS_ID=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, goodsID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setAge(rs.getString("user_age"));
                user.setEmail(rs.getString("user_email"));
                user.setHeadPic(rs.getString("user_headpic"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setSex(rs.getString("user_sex"));
                user.setPhone(rs.getString("user_phone"));
                user.setSellcount(rs.getInt("user_sellcount"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close( conn, pstmt,rs);
        }
        return user;
    }
    public void updateUserSellCount(int userId){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE yy_user SET user_sellcount=user_sellcount+1 WHERE user_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    public void updateHeadShot(int userId,String path){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE yy_user SET user_headpic=? WHERE user_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,path);
            pstmt.setInt(2,userId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    public void updateUserBaseInfo(User user){
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE yy_user SET user_sex=?,user_name=?,user_age=?,user_phone=? WHERE user_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getSex());
            pstmt.setString(2,user.getName());
            pstmt.setString(3,user.getAge());
            pstmt.setString(4,user.getPhone());
            pstmt.setInt(5,user.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
    }
    /**
     * 求y用户信息注册保存
     *
     * @param email
     * @param password
     */
    public void save(String email, String password) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO yy_user(user_email,user_password,user_registdate,user_sellcount) VALUES(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(4, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,null );
        }
    }



    /**
     * 注册用户登录
     *
     * @param email
     * @param password
     * @return
     */
    public int login(String email, String password) {
        int applicantID = 0;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT user_id FROM yy_user WHERE user_email=? and user_password=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                applicantID = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return applicantID;
    }


}
