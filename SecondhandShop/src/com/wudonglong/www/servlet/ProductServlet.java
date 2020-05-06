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
		
		Product newProduct = null;
		if(price!=null && stock!=null && id!=null){
			newProduct = new Product(0, name, introduction,Integer.parseInt(price), Integer.parseInt(stock), Integer.parseInt(id.split("-")[0]), Integer.parseInt(id.split("-")[1]), fname);
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
}
