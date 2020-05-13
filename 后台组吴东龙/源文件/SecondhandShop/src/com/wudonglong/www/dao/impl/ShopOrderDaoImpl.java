package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.dao.ShopOrderDao;
import com.wudonglong.www.entity.ShopOrder;
import com.wudonglong.www.util.DBUtil;

public class ShopOrderDaoImpl implements ShopOrderDao{

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
						rs.getString("uid"),
						rs.getString("uaddress"),
						rs.getString("createtime"),
						rs.getInt("cost"),
						rs.getInt("status"),
						rs.getInt("type"),
						rs.getString("seller"),
						rs.getInt("quantity"),
						rs.getInt("pid"));
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
						rs.getString("uid"),
						rs.getString("uaddress"),
						rs.getString("createtime"),
						rs.getInt("cost"),
						rs.getInt("status"),
						rs.getInt("type"),
						rs.getString("seller"),
						rs.getInt("quantity"),
						rs.getInt("pid"));
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
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 生成订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean insert(ShopOrder shopOrder){
		String sql="insert into shoporder values(null,?,?,now(),?,?,?,?,?,?)";
		Object[] params={shopOrder.getUid(),shopOrder.getUaddress(),shopOrder.getCost(),shopOrder.getStatus(),shopOrder.getType(),shopOrder.getSeller(),shopOrder.getQuantity(),shopOrder.getPid()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 修改订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean update(ShopOrder shopOrder){
		String sql="update shoporder set uid=?,uaddress=?,cost=?,status=?,type=?,seller=?,quantity=?,pid=? where id=?";
		Object[] params={shopOrder.getUid(),shopOrder.getUaddress(),shopOrder.getCost(),shopOrder.getStatus(),shopOrder.getType(),shopOrder.getSeller(),shopOrder.getQuantity(),shopOrder.getPid(),shopOrder.getId()};
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
	
	/**
	 * 查找买到的宝贝(卖家已经同意的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtYes(String uid){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where uid=? and status=1 and type=3";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 查询已经收到货的宝贝
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtHas(String uid){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where uid=? and status=1 and type=1";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 查找买到的宝贝(卖家不同意的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtNo(String uid){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where uid=? and status=2";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 查找买到的宝贝(卖家还在审核中的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtWaiting(String uid){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where uid=? and status=3";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 确认收货
	 * @param id
	 * @return
	 */
	public boolean confirm(int id) {
		String sql="update shoporder set type=1 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 查找卖出的宝贝(自己同意的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldYes(String seller){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where seller=? and status=1";
		Object[] params = {seller};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 查找卖出的宝贝(自己不同意的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldNo(String seller){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where seller=? and status=2";
		Object[] params = {seller};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 查找卖出的宝贝(自己还在审核中的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldWaiting(String seller){
		List<ShopOrder> shopOrders = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shoporder where seller=? and status=3";
		Object[] params = {seller};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopOrder shopOrder = new ShopOrder(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("uaddress"),
							rs.getString("createtime"),
							rs.getInt("cost"),
							rs.getInt("status"),
							rs.getInt("type"),
							rs.getString("seller"),
							rs.getInt("quantity"),
							rs.getInt("pid"));
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
	 * 允许买家购买
	 * @param id
	 * @return
	 */
	public boolean allow(int id) {
		String sql = "update shoporder set status=1 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 拒绝买家购买
	 * @param id
	 * @return
	 */
	public boolean refuse(int id) {
		String sql = "update shoporder set status=2 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
}
