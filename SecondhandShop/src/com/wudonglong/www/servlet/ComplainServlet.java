package com.wudonglong.www.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wudonglong.www.entity.Complain;
import com.wudonglong.www.service.impl.ComplainServiceImpl;


public class ComplainServlet extends BaseServlet {
	
	ComplainServiceImpl complainService = new ComplainServiceImpl();
	
	/**
	 * 查询投诉信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		complainService.select(request, response);
	}


	/**
	 * 添加投诉信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		complainService.insert(nickname,content, request, response);
	}
	
	/**
	 * 查出要回复的投诉信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		complainService.update(id, request, response);
	}
	
	/**
	 * 回复投诉信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doComplainUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String contenttime = request.getParameter("contenttime");
		String reply = request.getParameter("reply");
		Complain complain = new Complain(Integer.parseInt(id),content,contenttime,reply,null,nickname);
		complainService.doComplainUpdate(complain, request, response);
	}
	
	/**
	 * 删除投诉信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		complainService.delete(id, request, response);
	}
	
}
