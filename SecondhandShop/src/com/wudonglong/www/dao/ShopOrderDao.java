package com.wudonglong.www.dao;

import java.util.List;

import com.wudonglong.www.entity.ShopOrder;

public interface ShopOrderDao {
	/**
	 * 查出所有订单
	 * @return
	 */
	public  List<ShopOrder> selectAll();
	
	
	/**
	 * 根据ID查出一个订单
	 * @param id
	 * @return
	 */
	public  ShopOrder selectById(int id);
	
	
	/**
	 * 根据页面大小获得shoporder表的总页数
	 * @param count
	 * @return
	 */
	public  int totalPage(int count);
	
	/**
	 * 分页查询所有订单
	 * @param cpage
	 * @param count
	 * @return
	 */
	public  List<ShopOrder> selectAll(int cpage,int count);
	
	/**
	 * 生成订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean insert(ShopOrder shopOrder);
	
	/**
	 * 修改订单信息
	 * @param shopOrder
	 * @return
	 */
	public  boolean update(ShopOrder shopOrder);
	
	/**
	 * 删除订单信息
	 * @param id
	 * @return
	 */
	public  boolean delete(int id);
	
	/**
	 * 查找买到的宝贝(卖家已经同意的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtYes(String uid);
	
	/**
	 * 查询已经收到货的宝贝
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtHas(String uid);
	
	/**
	 * 查找买到的宝贝(卖家不同意的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtNo(String uid);
	
	
	/**
	 * 查找买到的宝贝(卖家还在审核中的)
	 * @param uid
	 * @return
	 */
	public  List<ShopOrder> selectBoughtWaiting(String uid);
	
	
	/**
	 * 确认收货
	 * @param id
	 * @return
	 */
	public boolean confirm(int id);
	
	
	/**
	 * 查找卖出的宝贝(自己同意的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldYes(String seller);
	
	
	/**
	 * 查找卖出的宝贝(自己不同意的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldNo(String seller);
	
	
	/**
	 * 查找卖出的宝贝(自己还在审核中的)
	 * @param seller
	 * @return
	 */
	public  List<ShopOrder> selectSoldWaiting(String seller);
	
	
	/**
	 * 允许买家购买
	 * @param id
	 * @return
	 */
	public boolean allow(int id);
	
	/**
	 * 拒绝买家购买
	 * @param id
	 * @return
	 */
	public boolean refuse(int id);
	
}
