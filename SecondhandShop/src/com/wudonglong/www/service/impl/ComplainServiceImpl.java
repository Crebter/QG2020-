package com.wudonglong.www.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wudonglong.www.dao.ComplainDao;
import com.wudonglong.www.dao.impl.ComplainDaoImpl;
import com.wudonglong.www.entity.Complain;
import com.wudonglong.www.service.ComplainService;

public class ComplainServiceImpl implements ComplainService{
	ComplainDao complainDao = new ComplainDaoImpl();
	
	/**
	 * 查询投诉信息
	 * @param cp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<Complain> allComplains = complainDao.selectAll();
		
		session.setAttribute("allComplains", allComplains);
		
		request.getRequestDispatcher("complain.jsp").forward(request, response);
		
	}
	
	
	
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
	public void insert(String nickname,String content,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date).toString();
		Complain complain = new Complain(0,content,time,null,null,nickname);
		complainDao.insert(complain);
		select(request,response);	
	}
	
	/**
	 * 查出准备回复的投诉信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Complain updateComplain = complainDao.selectById(Integer.parseInt(id));
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(updateComplain != null) {
			request.getSession().setAttribute("updateComplain", updateComplain);
			request.getRequestDispatcher("complainUpdate.jsp").forward(request, response);
		}else {
			out.print("<script>");
			out.print("alert('查询失败');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}
	}
	
	
	
	/**
	 * @param complain
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doComplainUpdate(Complain complain,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = complainDao.update(complain);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('处理成功');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('处理失败');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}
	}
	
	/**
	 * 删除投诉信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = complainDao.delete(Integer.parseInt(id));
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('删除成功');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('删除失败');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}
	}
}
