package com.wudonglong.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.wudonglong.www.entity.User;
import com.wudonglong.www.service.UserService;
import com.wudonglong.www.service.impl.UserServiceImpl;


public class UserServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();
	
	/**
	 * 判断用户是否登录,并且跳转到相应页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @Date 2020-04-30
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//接受前端数据
		String id = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String veryCode = request.getParameter("veryCode");

		//获得CodeServlet生成的验证码(在session域里)
		String Code =(String)request.getSession().getAttribute("code");
		User user = new User(id,password);
		userService.login(user,veryCode,Code,request,response);
	}
	
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @Date 2020-04-30
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("userName");
		String username = request.getParameter("name");
		String password = request.getParameter("rePassWord");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String card = request.getParameter("card");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String uaddress = request.getParameter("uaddress");
		String paddress = request.getParameter("paddress");
		int status = 1;
		
		//获得CodeServlet生成的验证码(在session域里)
		String veryCode = request.getParameter("veryCode");
		
		//获得系统生成的验证码
		String Code =(String)request.getSession().getAttribute("code");
		User user = new User(id, username, password, sex, birthday, card, email, phone, uaddress,paddress, status);

		userService.add(user,veryCode,Code,request,response);
		
		}
		
		
		/**
		 * 校验用户是否已存在
		 * @param request
		 * @param response
		 * @throws IOException
		 * @Date 2020-04-30
		 */
		public void checkID(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("uname");
			userService.checkID(id, request, response);
		}
	
		
		
		
		/**
		 * 根据ID删除用户
		 * @param request
		 * @param response
		 * @throws IOException
		 * @Date 2020-04-30
		 */
		public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			userService.delete(id, request, response);
		}
		
		
		/**
		 * 修改用户信息
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("utf-8");
			
			String id = request.getParameter("userName");
			String username = request.getParameter("name");
			String password = request.getParameter("passWord");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String card = request.getParameter("card");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String uaddress = request.getParameter("uaddress");
			String paddress = request.getParameter("paddress");
			int status = Integer.parseInt(request.getParameter("status"));
			

			User newUser = new User(id, username, password, sex, birthday, card, email, phone, uaddress,paddress, status);
			userService.update(newUser, request, response);
		}

		/**
		 * 用户退出登录
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			userService.exit(request, response);
		}
	
		
		
		/**
		 * 找回密码
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("userName");
			String password = request.getParameter("rePassWord");
			String card = request.getParameter("card");
			String email = request.getParameter("email");
			String code = request.getParameter("veryCode");
			userService.check(id,password,card,email,code,request,response);
		}
	
	
	
	
	
	}	

	
	


