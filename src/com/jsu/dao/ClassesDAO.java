package com.jsu.dao;

import com.jsu.bean.Classes;
import com.jsu.bean.Goods;
import com.jsu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassesDAO {
    public Classes getClassByClassID(int classID) {
        Classes classes=new Classes();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM yy_classes WHERE classes_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,classID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                classes.setId(rs.getInt("classes_id"));
                classes.setClassesName(rs.getString("classes_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return classes;
    }
}
