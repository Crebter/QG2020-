package com.wudonglong.www.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 抽取Servlet共同的部分
 * @author Destiny
 *
 */
public class BaseServlet extends HttpServlet {
       
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			request.setCharacterEncoding("utf-8");
			//获取请求的方法
			String methodName = request.getParameter("method");			
			//获取指定类的字节码对象
			Class<? extends BaseServlet> clazz = this.getClass();//让this继承BaseServlet对象			
			//通过类的字节码对象获取方法的字节码对象			
			Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		
	}

	
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
