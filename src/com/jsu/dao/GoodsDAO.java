package com.jsu.dao;


import com.jsu.bean.Goods;
import com.jsu.bean.User;
import com.jsu.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 开心点，伙计！
 */
public class GoodsDAO {
	public void updateGoodsPic(int goodsId,String path){
		System.out.println(goodsId+"----"+path);
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT yy_pictrue(PICTRUE_PATH,GOODS_ID) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,path);
			pstmt.setInt(2,goodsId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
	}
	public Goods getGoodsByGoodsID(int goodsID) {
		Goods goods=new Goods();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods  WHERE goods_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,goodsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setSellerId(rs.getInt("user_id"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return goods;
	}
	public void loveGoods(int userID,int goodsID){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO yy_favorite(goods_id,user_id) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,goodsID);
			pstmt.setInt(2,userID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
	}
	public void updateGoods(int goodsID,int state){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE yy_goods SET goods_state = ? WHERE goods_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,state);
			pstmt.setInt(2,goodsID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
	}
	public List<Goods> getGoodsListByNewest(int classesId) {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods WHERE classes_id=? AND goods_state=1 ORDER BY GOODS_UPTIME";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,classesId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setSellerId(rs.getInt("user_id"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<Goods> getGoodsListByNewestFive(int classesId) {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods WHERE classes_id=? AND goods_state=1 ORDER BY GOODS_UPTIME DESC limit 5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,classesId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setSellerId(rs.getInt("user_id"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<Goods> getGoodsListByNewestFive() {
		List<Goods> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				String sql = "SELECT * FROM yy_goods WHERE goods_state=1 ORDER BY GOODS_UPTIME DESC limit 5";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<Goods> getGoodsListByNewest() {
		List<Goods> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods WHERE goods_state=1 ORDER BY GOODS_UPTIME DESC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				goods.setSellerId(rs.getInt("user_id"));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<Goods> getGoodsListByNewestCheapFive() {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods\n" +
					"WHERE GOODS_DISCOUNT <>\"\"\n" +
					"AND goods_state=1 ORDER BY GOODS_UPTIME DESC\n" +
					"LIMIT 5";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				goods.setSellerId(rs.getInt("user_id"));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<Goods> getGoodsListByNewestCheapList() {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM yy_goods\n" +
					"WHERE GOODS_DISCOUNT <>\"\"\n" +
					" AND goods_state=1 ORDER BY GOODS_UPTIME DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("goods_id"));
				goods.setName(rs.getString("goods_name"));
				goods.setState(rs.getInt("goods_state"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setUpTime(rs.getTimestamp("goods_uptime"));
				goods.setDescribe(rs.getString("goods_describe"));
				goods.setClassesId(rs.getInt("classes_id"));
				goods.setLooks(rs.getInt("goods_looks"));
				goods.setPictrues(getGoodsPictrue(goods.getId()));
				goods.setSellerId(rs.getInt("user_id"));
				list.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public List<String> getGoodsPictrue(int goodsId) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT PICTRUE_PATH FROM yy_goods,yy_pictrue\n" +
					"WHERE yy_goods.GOODS_ID=yy_pictrue.GOODS_ID\n" +
					"and yy_goods.GOODS_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,goodsId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String pic=rs.getString("pictrue_path");
				list.add(pic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,rs );
		}
		return list;
	}
	public void saveGoods(Goods goods){
			Connection conn = null;
			try {
				conn = DBUtils.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO yy_goods(goods_name,goods_state,goods_price,goods_describe,classes_id,goods_uptime,goods_discount,goods_looks,user_id) VALUES(?,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, goods.getName());
				pstmt.setInt(2, goods.getState());
				pstmt.setDouble(3, goods.getPrice());
				pstmt.setString(4,goods.getDescribe());
				pstmt.setInt(5, goods.getClassesId());
				pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				pstmt.setDouble(7, goods.getDiscount());
				pstmt.setInt(8, goods.getLooks());
				pstmt.setInt(9,goods.getSellerId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(conn, pstmt,null );
			}
	}
	public boolean isExistLove(int goodsId,int userId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM yy_favorite WHERE goods_id=? AND user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
			pstmt.setInt(2, userId);
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
}
