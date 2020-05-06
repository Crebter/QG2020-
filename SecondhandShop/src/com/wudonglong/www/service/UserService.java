package com.wudonglong.www.service;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wudonglong.www.entity.User;

public interface UserService {
	public void login(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException;

	
	public void add(User user,String veryCode,String Code,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	public void checkID(String id,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	public void delete(String id,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	//修改用户信息
	public void update(User user,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	//退出登录
	public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException;

	
	public void check(String id, String password, String card, String email, String code, HttpServletRequest request,
			HttpServletResponse response) throws IOException;

}
