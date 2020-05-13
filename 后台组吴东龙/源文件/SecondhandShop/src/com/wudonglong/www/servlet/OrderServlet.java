package com.wudonglong.www.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wudonglong.www.entity.ShopOrder;
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
	
	
	/**
	 * 生成订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		
		//买家信息
		User user = (User)session.getAttribute("user");
		
		//结算的各商品的ID
		String[] pid = request.getParameterValues("spID");
//		for(int i=0;i<pid.length;i++) {
//			System.out.println("商品的ID:"+pid[i]);
//		}
		//结算的各商品的购买数量
		String[] quantity = request.getParameterValues("number");
		
		//结算的各商品的单价
		String[] price = request.getParameterValues("sPPrice");
		
		//各商品的总价
		int [] cost = new int[pid.length];
		for(int i=0;i<pid.length;i++) {
			cost[i] = Integer.parseInt(quantity[i]) * Integer.parseInt(price[i]); 
		}
		/*
		 * 订单表字段
		 * id uid uaddress createtime cost status type seller quantity pid
		 */
		orderService.createOrder(user,pid,quantity,cost,request,response);
	}
	
	
	/**
	 * 买家身份
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Bought(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		orderService.Bought(uid,request,response);
		
	}
	
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		orderService.confirm(id,request,response);
	}
	
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String quantity = request.getParameter("quantity");
		String pid = request.getParameter("pid");
		orderService.cancel(id,quantity,pid,request,response);
		
	}
	
	
	/**
	 * 卖家身份
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Sold(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		orderService.Sold(uid, request, response);
	}
	
	
	/**
	 * 允许购买
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		orderService.allow(id, request, response);
	}
	
	/**
	 * 拒绝购买
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void refuse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		orderService.refuse(id,request,response);
	}
	
	
	/**
	 * 查出要修改的订单信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		orderService.update(id,request,response);
	}
	
	
	/**
	 * 执行修改订单操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		String uaddress = request.getParameter("uaddress");
		String createtime = request.getParameter("createtime");
		String cost = request.getParameter("cost");
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		String seller = request.getParameter("seller");
		String quantity = request.getParameter("quantity");
		String pid = request.getParameter("pid");
		ShopOrder newShopOrder = new ShopOrder(Integer.parseInt(id),uid,uaddress,createtime,Integer.parseInt(cost),Integer.parseInt(status),Integer.parseInt(type),seller,Integer.parseInt(quantity),Integer.parseInt(pid));
		orderService.doUpdate(newShopOrder,request,response);

	}
}
