package com.wudonglong.www.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wudonglong.www.dao.ProductDao;
import com.wudonglong.www.dao.impl.ProductDaoImpl;
import com.wudonglong.www.dao.impl.ShopCarDaoImpl;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.entity.ShopCar;
import com.wudonglong.www.entity.User;

public class OrderServiceImpl {
	
	ProductDao productDao = new ProductDaoImpl();
	ShopCarDaoImpl shopCarDao = new ShopCarDaoImpl();
	
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
	public void addShopCar(String pid,String count,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		Product product = null;
		
		//查出该商品的所有信息
		if(pid != null) {
			product = productDao.selectById(Integer.parseInt(pid));
		}
		
		User user = (User)session.getAttribute("user");
		String uid = null;
		//已登陆
		if(user != null) {
			
			uid = user.getId();
			int valid = 1;
			//int id, String picture, String pname, int price, int quantity, int stock, int pid, String uid,int valid
			ShopCar shopCar = new ShopCar(0,product.getPicture(),product.getName(),product.getPrice(),Integer.parseInt(count),product.getStock(),product.getId(),uid,valid);
			
			//加入购物车
			shopCarDao.insertShopCar(shopCar);
			
			//查询购物车
			List<ShopCar> shopCars = shopCarDao.getShop(uid);
			
			//将购物车信息返回前端
			session.setAttribute("shopCars", shopCars);
			
		}
		//未登录 
		else {
			out.print("<script>");
			out.print("alert('请先登录');");
			out.print("location.href='login.jsp';");
			out.print("</script>");
			out.close();
			return;
		}	
		request.getRequestDispatcher("shopcar.jsp").forward(request, response);
	}
	
	/**
	 * 查询自己的购物车
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void shopcarselectAll(User user,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(user == null) {
			out.print("<script>");
			out.print("alert('请先登录');");
			out.print("location.href='login.jsp';");
			out.print("</script>");
			out.close();
			return;
		}else {
			//查询购物车
			List<ShopCar> shopCars = shopCarDao.getShop(user.getId());
			
			//将购物车信息返回前端
			session.setAttribute("shopCars", shopCars);
			request.getRequestDispatcher("shopcar.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * 更新购物车信息
	 * @param pid
	 * @param action
	 * @param qunatity
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void shopcarUpdate(String pid,String action,String qunatity,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		switch(action) {
			case "add":
				flag = shopCarDao.updateadd(Integer.parseInt(pid));
				break;
			case "sub":
				flag = shopCarDao.updateminus(Integer.parseInt(pid));
				break;
			case "close":
				ShopCar shopcar = new ShopCar(Integer.parseInt(pid),"1","1",1,Integer.parseInt(qunatity),1,1,"1",1);
				flag = shopCarDao.updateByInput(shopcar);
				break;
			case "delete":
				flag = shopCarDao.deleteShopCar(Integer.parseInt(pid));
				break;
		}
		request.getRequestDispatcher("OrderServlet?method=shopcarselectAll").forward(request, response);
		
		
	}
	
}
