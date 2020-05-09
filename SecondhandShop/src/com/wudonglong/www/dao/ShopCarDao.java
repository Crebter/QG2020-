package com.wudonglong.www.dao;

import java.sql.SQLException;
import java.util.List;

import com.wudonglong.www.entity.ShopCar;

public interface ShopCarDao {
		
	
	/**
	 * 查询购物车的订单信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<ShopCar> getShop(String id) throws SQLException;
	
	
	/**
	 * 点击按钮增加购买数量
	 * @param id
	 * @return
	 */
	public boolean updateadd(int id);
	
	/**
	 * 点击按钮减少购买数量
	 * @param id
	 * @return
	 */
	public boolean updateminus(int id);
	
	
	/**
	 * 手动输入购买数量
	 * @param shopCar
	 * @return
	 */
	public boolean updateByInput(ShopCar shopCar);
	
	
	/**
	 * 增添购物车信息
	 * @param shopCar
	 * @return
	 */
	public boolean insertShopCar(ShopCar shopCar);
	
	
	/**
	 * 删除购物车信息
	 * @param id
	 * @return
	 */
	public boolean deleteShopCar(int id);
	
	
	/**
	 * 购买成功,设置订单状态
	 * @param id
	 * @return
	 */
	public boolean buySuccess(int id);
}
