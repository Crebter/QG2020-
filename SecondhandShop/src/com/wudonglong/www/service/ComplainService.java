package com.wudonglong.www.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wudonglong.www.entity.Complain;

public interface ComplainService {
	/**
	 * 查询投诉信息
	 * @param cp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	
	/**
	 * 添加投诉信息
	 * @param cp
	 * @param nickname
	 * @param content
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insert(String nickname,String content,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 查出准备回复的投诉信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	
	/**
	 * @param complain
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doComplainUpdate(Complain complain,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 删除投诉信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}	
