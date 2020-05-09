package com.wudonglong.www.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wudonglong.www.entity.User;

public interface UserService {
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
	public void login(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/**
	 * 增加用户
	 * @param user
	 * @param veryCode
	 * @param Code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void add(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	
	/**
	 * 校验用户是否存在
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void checkID(String id,HttpServletRequest request, HttpServletResponse response) throws IOException;
		
	
	/**
	 * 根据ID删除用户
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void delete(String id,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	/**
	 * 根据ID修改用户
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void update(User user,HttpServletRequest request, HttpServletResponse response) throws IOException;
		
		
		
		
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException;
  

		
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
	public void check(String id,String password,String card,String email,String code,HttpServletRequest request, HttpServletResponse response) throws IOException;


	/**
	 * 管理员身份
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


	/**
	 * 根据ID删除用户,并且跳转页面
	 * @param uid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void deleteById(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
