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
import com.wudonglong.www.dao.ShopCarDao;
import com.wudonglong.www.dao.ShopOrderDao;
import com.wudonglong.www.dao.impl.ProductDaoImpl;
import com.wudonglong.www.dao.impl.ShopCarDaoImpl;
import com.wudonglong.www.dao.impl.ShopOrderDaoImpl;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.entity.ShopCar;
import com.wudonglong.www.entity.ShopOrder;
import com.wudonglong.www.entity.User;
import com.wudonglong.www.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	ProductDao productDao = new ProductDaoImpl();
	ShopCarDao shopCarDao = new ShopCarDaoImpl();
	ShopOrderDao shopOrderDao = new ShopOrderDaoImpl();	
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
			ShopCar shopCar = new ShopCar(0,product.getPicture(),product.getName(),product.getPrice(),Integer.parseInt(count),product.getStock(),product.getId(),uid,valid,product.getUid());
			
//			System.out.println(product.getUid());
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
	 * @param quantity
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void shopcarUpdate(String pid,String action,String quantity,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				ShopCar shopcar = new ShopCar(Integer.parseInt(pid),"1","1",1,Integer.parseInt(quantity),1,1,"1",1);
				flag = shopCarDao.updateByInput(shopcar);
				break;
			case "delete":
				flag = shopCarDao.deleteShopCar(Integer.parseInt(pid));
				break;
		}
		request.getRequestDispatcher("OrderServlet?method=shopcarselectAll").forward(request, response);
	}
	
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
	public void createOrder(User user,String[] pid,String[] quantity,int[] cost,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		/*
		 * 订单表字段
		 * id uid uaddress createtime cost status type seller quantity pid
		 */
		
		Product product = null;
		
		//根据已有的商品的ID得到卖家的ID
		String [] seller = new String[pid.length];
		for(int i=0;i<pid.length;i++) {
			product = productDao.selectById(Integer.parseInt(pid[i]));
			seller[i] = product.getUid();
		}
		int status = 3;
		int type = 3;
		boolean flag = false;
		for(int i=0;i<pid.length;i++) {			//id   uid           uaddress createtime cost status type seller           quantity                     pid
			ShopOrder shopOrder = new ShopOrder(0,user.getId(),user.getPaddress(),"1",cost[i],status,type,seller[i],Integer.parseInt(quantity[i]),Integer.parseInt(pid[i]));
			flag = shopOrderDao.insert(shopOrder);
		}
		if(flag == true) {
			out.print("<script>");
			out.print("alert('结算成功,等卖家审核');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('结算失败');");
			out.print("history.back();");
			out.print("</script>");
		}
		
	}
	
	
	/**
	 * 买家身份
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Bought(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//卖家同意的
		List<ShopOrder> boughtYes = shopOrderDao.selectBoughtYes(uid);
		
		//卖家不同意的
		List<ShopOrder> boughtNo= shopOrderDao.selectBoughtNo(uid);
		
		//等待卖家审核的
		List<ShopOrder> boughtWaiting = shopOrderDao.selectBoughtWaiting(uid);
		
		List<ShopOrder> boughtHas = shopOrderDao.selectBoughtHas(uid);

		HttpSession session = request.getSession();
		
		session.setAttribute("boughtYes", boughtYes);
		
		session.setAttribute("boughtNo", boughtNo);

		session.setAttribute("boughtWaiting", boughtWaiting);
		
		session.setAttribute("boughtHas", boughtHas);

		request.getRequestDispatcher("bought.jsp").forward(request, response);
	}
	
	/**
	 * 确认收货
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void confirm(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		shopOrderDao.confirm(Integer.parseInt(id));
		User user = (User)request.getSession().getAttribute("user");
		Bought(user.getId(),request,response);
	}
	
	
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
	public void cancel(String id,String quantity,String pid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//将原来的商品加上购买的数量
		productDao.addStock(Integer.parseInt(quantity), Integer.parseInt(pid));
		
		//删除该订单
		shopOrderDao.delete(Integer.parseInt(id));
		
		User user = (User)request.getSession().getAttribute("user");
		Bought(user.getId(),request,response);
	}
	
	
	/**
	 * 卖家身份
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Sold(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//自己已经同意的
		List<ShopOrder> soldYes = shopOrderDao.selectSoldYes(uid);
		
		//自己不同意的
		List<ShopOrder> soldNo= shopOrderDao.selectSoldNo(uid);
		
		//等待自己审核的
		List<ShopOrder> soldWaiting = shopOrderDao.selectSoldWaiting(uid);
		

		HttpSession session = request.getSession();
		
		session.setAttribute("soldYes", soldYes);
		
		session.setAttribute("soldNo", soldNo);

		session.setAttribute("soldWaiting", soldWaiting);
		

		request.getRequestDispatcher("sold.jsp").forward(request, response);
	}
	
	
	/**
	 * 允许买家购买
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allow(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		//修改订单状态(允许购买)
		shopOrderDao.allow(Integer.parseInt(id));
		
		//查出允许购买的订单
		ShopOrder shopOrder = shopOrderDao.selectById(Integer.parseInt(id));
		
		//用订单里面的商品ID查出要购买的商品的信息
		Product product = productDao.selectById(shopOrder.getPid());
		
		//修改要购买的商品信息   (减少库存)
		productDao.updateStock(shopOrder.getQuantity(), product.getId());
		
		//刷新页面
		Sold(user.getId(),request,response);
	}
	
	
	/**
	 * 拒绝买家购买
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void refuse(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		//修改订单状态
		shopOrderDao.refuse(Integer.parseInt(id));
		//刷新页面
		Sold(user.getId(),request,response);
	}
	
	/**
	 * 查出修改的订单信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查出待修改的订单
		ShopOrder oldShopOrder = shopOrderDao.selectById(Integer.parseInt(id));
		
		request.getSession().setAttribute("oldShopOrder", oldShopOrder);
		
		request.getRequestDispatcher("shopOrderUpdate.jsp").forward(request, response);
	}
	
	
	/**
	 * 修改订单信息
	 * @param id
	 * @param uaddress
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doUpdate(ShopOrder newShopOrder,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		//修改送货地址
		shopOrderDao.update(newShopOrder);
		//刷新页面
		Sold(user.getId(),request,response);	
	}
}
