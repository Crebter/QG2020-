package com.wudonglong.www.service;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.wudonglong.www.entity.ShopOrder;
import com.wudonglong.www.entity.User;

public interface OrderService {
	/**
	 * 将商品加入购物车
	 * @param pid
	 * @param count
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addShopCar(String pid,String count,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
	
	/**
	 * 查询自己的购物车
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void shopcarselectAll(User user,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException;
	
	
	/**
	 * 更新购物车信息
	 * @param pid
	 * @param action
	 * @param quantity
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void shopcarUpdate(String pid,String action,String quantity,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 生成订单
	 * @param user
	 * @param pid
	 * @param quantity
	 * @param cost
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createOrder(User user,String[] pid,String[] quantity,int[] cost,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 买家身份
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Bought(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 确认收货
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void confirm(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 取消订单
	 * @param id
	 * @param quantity
	 * @param pid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cancel(String id,String quantity,String pid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 卖家身份
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Sold(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 允许买家购买
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allow(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 拒绝买家购买
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void refuse(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 查出修改的订单信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 修改订单信息
	 * @param id
	 * @param uaddress
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doUpdate(ShopOrder newShopOrder,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
