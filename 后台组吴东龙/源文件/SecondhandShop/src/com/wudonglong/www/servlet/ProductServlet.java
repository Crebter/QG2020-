package com.wudonglong.www.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.service.impl.ProductServiceImpl;


public class ProductServlet extends BaseServlet {
	
	ProductServiceImpl productService = new ProductServiceImpl();
	
	
	/**
	 * 查询所有分类以及活动数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productService.selectAll(request, response);
	}

	
	
	
	/**
	 * 新增商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		SmartUpload su = new SmartUpload();
		
		su.initialize(this.getServletConfig(), request, response);
		
		try {
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		Files fs = su.getFiles();//获得所有文件
		
		File f = fs.getFile(0);//获得上传的文件
		
		String fname = f.getFileName();//获得文件名
		
		try {
			su.save("C:\\Users\\Destiny\\eclipse-workspace\\SecondhandShop\\WebContent\\images\\product");//保存图片到指定位置
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		Request req1 = su.getRequest();
	
		String name = req1.getParameter("productName");
		String id = req1.getParameter("parentId");
		String price = req1.getParameter("productPrice");
		String introduction = req1.getParameter("productDesc");
		String stock = req1.getParameter("productStock");
		String uid = req1.getParameter("uid");
		int valid = 3;
		
		Product newProduct = null;
		if(price!=null && stock!=null && id!=null){
			newProduct = new Product(0, name, introduction,Integer.parseInt(price), Integer.parseInt(stock), Integer.parseInt(id.split("-")[0]), Integer.parseInt(id.split("-")[1]), fname,uid,3,null);
		}
		
		//新增
		productService.add(newProduct,request,response);
	}
	
	
	
	
	
	/**
	 * 根据不同字段查询商品信息,并且分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectBypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String cp = request.getParameter("cp");
		productService.selectBypage(id, name,cp, request, response);
	}
	
	
	
	/**
	 * 展开商品的各种信息,并且保存最近浏览记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void productdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		List<Integer> ids = (List<Integer>)session.getAttribute("ids");
		productService.productdetail(id, ids,request,response);
	}
	
	/**
	 * 查看用户自己发布的商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void myProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		productService.myProduct(uid,request,response);
	}
	
	/**
	 * 删除商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		productService.delete(id,uid,request,response);
	}
	
	
	/**
	 * 获取要更新商品的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		productService.updateshow(id,request,response);
	}
	
	
	
	/**
	 * 修改商品信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		SmartUpload su = new SmartUpload();
		
		su.initialize(this.getServletConfig(), request, response);
		
		try {
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		Files fs = su.getFiles();//获得所有文件
		
		File f = fs.getFile(0);//获得上传的文件
		
		String fname = f.getFileName();//获得文件名
		
		try {
			su.save("C:\\Users\\Destiny\\eclipse-workspace\\SecondhandShop\\WebContent\\images\\product");//保存图片到指定位置
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		Request req1 = su.getRequest();
		String id = req1.getParameter("id");
		String name = req1.getParameter("productName");
		String pid = req1.getParameter("parentId");
		String price = req1.getParameter("productPrice");
		String introduction = req1.getParameter("productDesc");
		String stock = req1.getParameter("productStock");
		String uid = req1.getParameter("uid");
		String valid = req1.getParameter("valid");
		
		Product newProduct = null;
		if(price!=null && stock!=null && id!=null){
			newProduct = new Product(Integer.parseInt(id), name, introduction,Integer.parseInt(price), Integer.parseInt(stock), Integer.parseInt(pid.split("-")[0]), Integer.parseInt(pid.split("-")[1]), fname,uid,Integer.parseInt(valid),null);
		}
		
		//修改
		productService.update(newProduct,request,response);
	}
	
	
	/**
	 * 增加商品的小分类
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void addSmall(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		productService.addSmall(request,response);
	}
	
	
	/**
	 * 执行增加小分类
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doAddSmall(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String parentId = request.getParameter("parentId");
		
		String name = request.getParameter("name");
//		System.out.println(parentId);
//		System.out.println(name);
		productService.doAddSmall(parentId,name,request,response);
	}
	
	/**
	 * 删除小分类
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void deleteSmall(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		productService.deleteSmall(id,request,response);
	}
	
	
	/**
	 * 更新商品的权限
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void updateValid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String valid = request.getParameter("valid");
		productService.updateValid(id,valid,request,response);
	}
	
	/**
	 * 拒绝用户上传商品,查出该商品信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void refuse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		productService.refuse(id,request,response);
	}
	
	
	/**
	 * 执行拒绝商品上传
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doRefuse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String reason = request.getParameter("reason");
		productService.doRefuse(id,reason,request,response);
	}
}
