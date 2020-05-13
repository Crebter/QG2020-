package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.dao.ShopCarDao;
import com.wudonglong.www.entity.ShopCar;
import com.wudonglong.www.util.DBUtil;

public class ShopCarDaoImpl implements ShopCarDao{
	
	
	/**
	 * 查询购物车的订单信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<ShopCar> getShop(String id) throws SQLException{
		List<ShopCar> shopCars = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shopcar where uid=? and valid=1 order by id desc";
		Object[] params = {id};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					ShopCar shopCar = new ShopCar(  rs.getInt("id"),
													rs.getString("picture"),
													rs.getString("pname"),
													rs.getInt("price"),
													rs.getInt("quantity"),
													rs.getInt("stock"),
													rs.getInt("pid"),
													rs.getString("uid"),
													rs.getInt("valid"),    
													rs.getString("seller") );
					shopCars.add(shopCar);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
			return shopCars;
	}
	
	
	/**
	 * 点击按钮增加购买数量
	 * @param id
	 * @return
	 */
	public boolean updateadd(int id) {
		String sql ="update shopcar set quantity = quantity+1 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 点击按钮减少购买数量
	 * @param id
	 * @return
	 */
	public boolean updateminus(int id) {
		String sql ="update shopcar set quantity = quantity-1 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 手动输入购买数量
	 * @param shopCar
	 * @return
	 */
	public boolean updateByInput(ShopCar shopCar) {
		String sql ="update shopcar set quantity =? where id=?";
		Object[] params = {shopCar.getQuantity(),shopCar.getId()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 增添购物车信息
	 * @param shopCar
	 * @return
	 */
	public boolean insertShopCar(ShopCar shopCar) {
		String sql = "insert into shopcar value(null,?,?,?,?,?,?,?,?,?)";
		Object[] params = {shopCar.getPicture(),shopCar.getPname(),shopCar.getPrice(),shopCar.getQuantity(),shopCar.getStock(),shopCar.getPid(),shopCar.getUid(),shopCar.getValid(),shopCar.getSeller()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	
	/**
	 * 删除购物车信息
	 * @param id
	 * @return
	 */
	public boolean deleteShopCar(int id) {
		String sql = "delete from shopcar where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 购买成功,设置订单状态
	 * @param id
	 * @return
	 */
	public boolean buySuccess(int id) {
		String sql = "update shopcar set valid=2 where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
}
