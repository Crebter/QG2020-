package com.wudonglong.www.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wudonglong.www.dao.ComplainDao;
import com.wudonglong.www.dao.ProductDao;
import com.wudonglong.www.dao.ProductTypeDao;
import com.wudonglong.www.dao.ShopOrderDao;
import com.wudonglong.www.dao.UserDao;
import com.wudonglong.www.dao.impl.ComplainDaoImpl;
import com.wudonglong.www.dao.impl.ProductDaoImpl;
import com.wudonglong.www.dao.impl.ProductTypeDaoImpl;
import com.wudonglong.www.dao.impl.ShopOrderDaoImpl;
import com.wudonglong.www.dao.impl.UserDaoImpl;
import com.wudonglong.www.entity.Complain;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.entity.ProductType;
import com.wudonglong.www.entity.ShopOrder;
import com.wudonglong.www.entity.User;
import com.wudonglong.www.service.UserService;


public class UserServiceImpl implements UserService{
		
	UserDao userDao = new UserDaoImpl();
	ProductDao productDao = new ProductDaoImpl();
	ShopOrderDao shopOrderDao = new ShopOrderDaoImpl();	
	ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
	ComplainDao complainDao = new ComplainDaoImpl();
	/**
	 * 判断用户名与用户密码是否正确,并跳转页面
	 * @param user
	 * @param veryCode
	 * @param Code
	 * @param request
	 * @param response
	 * @throws IOException
	 * @Date 2020-04-30
	 */
	@Override
	public void login(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException  {
			//设置响应编码
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//验证码正确,根据用户身份跳转到相应的用户界面
			if(veryCode.equals(Code)) {
				User result = userDao.selectAdmin(user.getId(), user.getPassword());
				if(result != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", result);
					//管理员
					if(result.getStatus()==2) {
						response.sendRedirect("UserServlet?method=admin");
					}else {
						response.sendRedirect("ProductServlet?method=selectAll");
					}
				}
				//账号或者密码不正确
				else {
					out.print("<script>");
					out.print("alert('用户名或密码错误');");
					out.print("location.href='login.jsp';");
					out.print("</script>");
					out.close();
				}
			}
			//验证码不对,跳转回登录页面
			else {
				out.print("<script>");
				out.print("alert('验证码错误');");
				out.print("location.href='login.jsp';");
				out.print("</script>");
				out.close();
			}
	}
	
	/**
	 * 增加用户
	 * @param user
	 * @param veryCode
	 * @param Code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Override
	public void add(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException  {
			//设置响应编码
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//验证码正确,判断添加的信息是否与已有的信息重复
			if(veryCode.equals(Code)) {
				User result = userDao.queryUserByID(user.getId());
				if(result != null) {
					out.print("<script>");
					out.print("alert('该用户名已经被使用');");
					out.print("location.href='register.jsp';");
					out.print("</script>");
					out.close();
				}else {
					boolean flag = userDao.addUser(user);
					if(flag == true) {
						out.print("<script>");
						out.print("alert('注册成功,请登录');");
						out.print("location.href='login.jsp';");
						out.print("</script>");
						out.close();
					}else {
						out.print("<script>");
						out.print("alert('注册失败,请重试');");
						out.print("location.href='register.jsp';");
						out.print("</script>");
						out.close();
					}
				}
			}
			//验证码不对,跳转回注册页面
			else {
				out.print("<script>");
				out.print("alert('验证码错误');");
				out.print("location.href='register.jsp';");
				out.print("</script>");
				out.close();
			}
	}
	
	
	
	/**
	 * 校验用户是否存在
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void checkID(String id,HttpServletRequest request, HttpServletResponse response) throws IOException  {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user = userDao.queryUserByID(id);
		PrintWriter out = response.getWriter();
		 if(user == null){
			out.print("<msg>false</msg>");
		}else{
			out.print("<msg>true</msg>");
		}
		out.close();
	}
		
	
	/**
	 * 根据ID删除用户
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void delete(String id,HttpServletRequest request, HttpServletResponse response) throws IOException  {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		boolean flag = userDao.deleteUserByID(id);
		PrintWriter out = response.getWriter();
		 if(flag == true){
				out.print("<script>");
				out.print("alert('删除成功');");
				out.println("history.back();");
				out.print("</script>");
				out.close();
		}else{
			out.print("<script>");
			out.print("alert('删除失败');");
			out.println("history.back();");
			out.print("</script>");
			out.close();
		}
	}
	
	
	/**
	 * 根据ID修改用户
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void update(User user,HttpServletRequest request, HttpServletResponse response) throws IOException  {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		boolean flag = userDao.updateUserByID(user);
		PrintWriter out = response.getWriter();
		 if(flag == true){
				out.print("<script>");
				out.print("alert('修改成功');");
				out.println("history.back();");
				out.print("</script>");
				out.close();
		}else{
			out.print("<script>");
			out.print("alert('修改失败');");
			out.println("history.back();");
			out.print("</script>");
			out.close();
		}
	}
	
	
	
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
		}
  

		
	/**
	 * 忘记密码
	 * @param id
	 * @param card
	 * @param email
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void check(String id,String password,String card,String email,String code,HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String syscode = (String)request.getSession().getAttribute("code");
		//验证码正确,判断信息是否正确
		if(syscode.equals(code)) {
			User user = userDao.check(id, card, email);
			if(user != null) {
				boolean flag = userDao.updatePassword(id,password);
				if(flag == true) {
					out.print("<script>");
					out.print("alert('修改成功');");
					out.print("location.href='login.jsp';");
					out.print("</script>");
					out.close();
				}
			}
			//个人信息不正确
			else {
				out.print("<script>");
				out.print("alert('您填写的信息有误');");
				out.print("location.href='findpassword.jsp';");
				out.print("</script>");
				out.close();
			}
		}
		//验证码不对,重新填写信息
		else {
			out.print("<script>");
			out.print("alert('验证码错误');");
			out.print("location.href='findpassword.jsp';");
			out.print("</script>");
			out.close();
		}
	}


	/**
	 * 管理员身份
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//查出所有用户
		List<User> allUsers = userDao.selectAll();
		
		//查出所有的商品
		List<Product> allProducts = productDao.selectAll();
		
		//查出所有待审核的商品
		List<Product> products = productDao.selectAudit();
		
		//查询大分类
		List<ProductType> blist = productTypeDao.selectBig();
		
		//查询小分类
		List<ProductType> slist = productTypeDao.selectSmall();
		
		//查出所有订单
		List<ShopOrder> allShopOrders = shopOrderDao.selectAll();
		
		//查出所有投诉消息
		List<Complain> Allcomplains = complainDao.selectAll();
		
		session.setAttribute("allUsers", allUsers);
		session.setAttribute("allProducts", allProducts);
		session.setAttribute("products", products);
		session.setAttribute("blist", blist);
		session.setAttribute("slist", slist);
		session.setAttribute("allShopOrders", allShopOrders);
		session.setAttribute("Allcomplains", Allcomplains);
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);
		
	}


	/**
	 * 根据ID删除用户,并且跳转页面
	 * @param uid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void deleteById(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDao.deleteUserByID(uid);
		admin(request, response);
	}
}



	

