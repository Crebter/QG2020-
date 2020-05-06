package com.wudonglong.www.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wudonglong.www.entity.User;
import com.wudonglong.www.service.impl.OrderServiceImpl;


public class OrderServlet extends BaseServlet {
	
	OrderServiceImpl orderService = new OrderServiceImpl();
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addShopCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setCharacterEncoding("utf-8");
		String pid = request.getParameter("id");
		String count = request.getParameter("count");
		orderService.addShopCar(pid, count, request, response);
	}
	

	
	/**
	 * 点击查看自己的购物车
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void shopcarselectAll(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		request.setCharacterEncoding("utf-8");
		User user = (User)request.getSession().getAttribute("user");
		orderService.shopcarselectAll(user, request, response);
	}

	/**
	 * 更新购物车信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void shopcarUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pid = request.getParameter("pid");
		String action = request.getParameter("action");
		String quantity = request.getParameter("quantity");
		orderService.shopcarUpdate(pid,action,quantity,request,response);
		
	}

}
