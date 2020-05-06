package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.entity.ShopOrder;
import com.wudonglong.www.util.DBUtil;

public class ShopOrderDaoImpl {

	/**
	 * 查出所有订单
	 * @return
	 */
	public  List<ShopOrder> selectAll(){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder";
		rs = DBUtil.excuteQuery(sql, null);
		try {
			while (rs.next()) {
				ShopOrder shopOrder = new ShopOrder(
						rs.getInt("id"),
						rs.getString("userid"),
						rs.getString("username"),
						rs.getString("useraddress"),
						rs.getString("createtime"),
						rs.getInt("cost"),
						rs.getInt("status"),
						rs.getInt("type"));
				shopOrders.add(shopOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return shopOrders;
	}
	
	
	/**
	 * 根据ID查出一个订单
	 * @param id
	 * @return
	 */
	public  ShopOrder selectById(int id){
		ResultSet rs = null;
		ShopOrder shopOrder = null;
		String sql = "select * from shoporder where id=?";
		Object[] params = {id};		
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while (rs.next()) {
				shopOrder = new ShopOrder(
						rs.getInt("id"),
						rs.getString("userid"),
						rs.getString("username"),
						rs.getString("useraddress"),
						rs.getString("createtime"),
						rs.getInt("cost"),
						rs.getInt("status"),
						rs.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return shopOrder;
	}
	
	
	/**
	 * 根据页面大小获得shoporder表的总页数
	 * @param count
	 * @return
	 */
	public  int totalPage(int count){
		int tpage = 1;
		String sql = "select * from shoporder";
		int sum = DBUtil.getTatalCount(sql,null);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	/**
	 * 分页查询所有订单
	 * @param cpage
	 * @param count
	 * @return
	 */
	public  List<ShopOrder> selectAll(int cpage,int count){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder order by id desc limit ?,?";
		Object[] params = {count*(cpage-1),count};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("userid"),
							rs.getString("username"),
							rs.getString("useraddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"));
					shopOrders.add(shopOrder);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return shopOrders;
	}
	
	/**
	 * 插入订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean insert(ShopOrder shopOrder){
		String sql="insert into shoporder values(null,?,?,?,?,?,?)";
		Object[] params={shopOrder.getUserid(),shopOrder.getUsername(),shopOrder.getUseraddress(),shopOrder.getCreatetime(),shopOrder.getCost(),shopOrder.getStatus(),shopOrder.getType()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 修改订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean update(ShopOrder shopOrder){
		String sql="update shoporder set username=?,useraddress=?,cost=?,status=? where id=?";
		Object[] params={shopOrder.getUsername(),shopOrder.getUseraddress(),shopOrder.getCost(),shopOrder.getStatus(),shopOrder.getId()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 删除订单信息
	 * @param id
	 * @return
	 */
	public  boolean delete(int id){
		String sql="delete from shoporder where id=?";
		Object[] params={id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	
}
