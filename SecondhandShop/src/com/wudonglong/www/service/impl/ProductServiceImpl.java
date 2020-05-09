package com.wudonglong.www.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wudonglong.www.dao.ProductDao;
import com.wudonglong.www.dao.ProductTypeDao;
import com.wudonglong.www.dao.impl.ProductDaoImpl;
import com.wudonglong.www.dao.impl.ProductTypeDaoImpl;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.entity.ProductType;
import com.wudonglong.www.service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	  ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
	  ProductDao productDao = new ProductDaoImpl();
	  
	  
	/**
	 * 查询所有分类数据以及活动数据(页面的枢纽)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> ids = null;
		
		//查询大分类
		List<ProductType> blist = productTypeDao.selectBig();
		
		//查询小分类
		List<ProductType> slist = productTypeDao.selectSmall();
		
		//查询低价商品
		List<Product> tlist = productDao.selectAllByT();
		
		//查询库存多的商品
		List<Product> stocklist = productDao.selectAllByStock();
		
		//传回前端
		session.setAttribute("blist", blist);
		session.setAttribute("slist", slist);
		session.setAttribute("tlist", tlist);
		session.setAttribute("stocklist", stocklist);
		
		
		
		//查询最近浏览的商品
		if(session.getAttribute("ids") != null) {
			
			ids = (List<Integer>)session.getAttribute("ids");
			List<Product> lastlylist = productDao.selectAllById(ids);
			session.setAttribute("lastlylist", lastlylist);
		}
		//跳转回首页
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
	/**
	 * 新增商品信息
	 * @param product
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void add(Product product,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = productDao.insert(product);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('新增成功');");
			out.print("location.href='ProductServlet?method=selectAll';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('新增失败');");
			out.print("location.href='ProductServlet?method=selectAll';");
			out.print("</script>");
			out.close();
		}
	}
	
	
	
	/**
	 * 根据不同字段查询商品信息,并且分页
	 * @param id
	 * @param name
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectBypage(String id,String name,String cp,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			//查询大分类
			List<ProductType> blist = productTypeDao.selectBig();
			
			//查询小分类
			List<ProductType> slist = productTypeDao.selectSmall();
			
//			//查询低价商品
//			List<Product> tlist = productDao.selectAllByT();
//			
//			//查询热卖商品
//			List<Product> hlist = productDao.selectAllByHot();
				
			//传回前端
			session.setAttribute("blist", blist);
			session.setAttribute("slist", slist);
//			session.setAttribute("tlist", tlist);
//			session.setAttribute("hlist", hlist);
			
				
				

			
			int cpage = 1;//当前页数
			int count = 8;//页面大小
			int tpage = 0;
			//如果有上传页数,则改变当前页,否则默认显示第一页
			if(cp!=null) {
				cpage = Integer.parseInt(cp);
			}
			
			//查出的商品集合
			List<Product> product = null;
			
			
			//按大类查出来的商品信息
			if(id!=null) {
				int newid = Integer.parseInt(id);
				product = productDao.selectAllByParentId(cpage, count, newid);
				tpage = productDao.totalPageByParentId(count, newid);
			}
			
			else {
				//按名字搜索出来的商品信息
				product = productDao.selectAllByName(name);
				tpage = productDao.totalPageByname(count, name);
			}
			
			

			
			
			//根据特定字段查出来的商品
			session.setAttribute("product", product);
			
			//当前页数
			session.setAttribute("cpage", cpage);
			
			//总页数
			session.setAttribute("tpage", tpage);
			
			//搜索关键字
			session.setAttribute("search_words", name);
			
			if(id!=null) {
				session.setAttribute("typeid",Integer.parseInt(id));
			}
			//跳转到显示页面
			request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}
	
	
	
	/**
	 * 展开商品的各种信息,并且保存最近浏览记录
	 * @param id
	 * @param ids
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void productdetail(String id,List<Integer> ids,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Product> lastlylist = null;
		
		//查询大分类
		List<ProductType> blist = productTypeDao.selectBig();
		
		//查询小分类
		List<ProductType> slist = productTypeDao.selectSmall();

		//传回前端
		session.setAttribute("blist", blist);
		session.setAttribute("slist", slist);
		
		//一开始还未有记录，创建id集合
		if(ids == null) {
			ids = new ArrayList<Integer>();
		}
		
		//最近浏览超过10个，则更新
		if(ids.size() >= 10) {
			ids.remove(0);
		}
		
		//如果打开的商品是之前保存过的,则不用更新
		if(id!=null &&(!ids.contains(Integer.parseInt(id)))) {
			ids.add(Integer.parseInt(id));
		}
		
		session.setAttribute("ids", ids);
		ids = (ArrayList<Integer>)session.getAttribute("ids");
		if(ids!=null) {
			lastlylist = productDao.selectAllById(ids);
			session.setAttribute("lastlylist", lastlylist);
		}

		Product p = null;
		if(id != null) {
			p = productDao.selectById(Integer.parseInt(id));
		}
//		System.out.println(p.getUid());
//		System.out.println(p.getName());
//		System.out.println(p.getValid());

		session.setAttribute("productDetail", p);
		request.getRequestDispatcher("productShow.jsp").forward(request, response);
	}
	
	
	
	/**
	 * 查看用户上传的商品
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void myProduct(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//查询审核通过的商品
		List<Product> valid1 = productDao.myProductValid1(uid);
		
		//查询审核不通过的商品
		List<Product> valid2 = productDao.myProductValid2(uid);
		
		//查询正在审核的商品
		List<Product> valid3 = productDao.myProductValid3(uid);
		
		session.setAttribute("valid1", valid1);
		session.setAttribute("valid2", valid2);
		session.setAttribute("valid3", valid3);
		
		request.getRequestDispatcher("myproduct.jsp").forward(request, response);

	}	
	
	/**
	 * 删除商品
	 * @param id
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(String id,String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productDao.del(Integer.parseInt(id));
		myProduct(uid,request,response);
	}
	
	
	
	/**
	 * 获取要更新的商品的信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateshow(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Product productUpdate = productDao.selectById(Integer.parseInt(id));
		
		//查询大分类
		List<ProductType> blist = productTypeDao.selectBig();
		
		//查询小分类
		List<ProductType> slist = productTypeDao.selectSmall();

		//传回前端
		session.setAttribute("blist", blist);
		session.setAttribute("slist", slist);
		
		session.setAttribute("productUpdate", productUpdate);
		request.getRequestDispatcher("productupdate.jsp").forward(request, response);

	}
	
	
	/**
	 * 修改商品信息
	 * @param product
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void update(Product product,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = productDao.update(product);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('修改成功');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('修改失败');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}
	}
	

	/**
	 * 查询大分类,用于增加小分类
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addSmall(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//查询打分类
		List<ProductType> Blist = productTypeDao.selectBig();
		
		request.getSession().setAttribute("Blist", Blist);
		request.getRequestDispatcher("productTypeAdd.jsp").forward(request, response);
	}
	
	/**
	 * 执行增加小分类
	 * @param parentId
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void doAddSmall(String parentId,String name,HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductType type = new ProductType(name,Integer.parseInt(parentId));
		boolean flag = productTypeDao.insertSmall(type);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('增加成功');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('增加失败');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}
	}
	
	
	/**
	 * 删除一个小分类
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void deleteSmall(String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = productTypeDao.delete(Integer.parseInt(id));
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
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
	
	/**
	 * 更新商品权限
	 * @param id
	 * @param valid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void updateValid(String id,String valid,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = productDao.updateValid(Integer.parseInt(id), Integer.parseInt(valid));
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('更新成功');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('更新失败');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}
	}
	
	/**
	 * 查出拒绝的商品的信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void refuse(String id,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Product updateProduct = productDao.selectById(Integer.parseInt(id));
		request.getSession().setAttribute("updateProduct", updateProduct);
		request.getRequestDispatcher("refuse.jsp").forward(request, response);
	}
	
	
	/**
	 * 执行拒绝商品上传
	 * @param id
	 * @param reason
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void doRefuse(String id,String reason,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = productDao.refuse(Integer.parseInt(id),2, reason);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("<script>");
			out.print("alert('执行成功');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}else {
			out.print("<script>");
			out.print("alert('执行失败');");
			out.print("location.href='UserServlet?method=admin';");
			out.print("</script>");
			out.close();
		}
	}

	
}	
