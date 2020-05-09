package com.wudonglong.www.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wudonglong.www.entity.Product;

public interface ProductService {
	/**
	 * 查询所有分类数据以及活动数据(页面的枢纽)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 新增商品信息
	 * @param product
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void add(Product product,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	
	/**
	 * 根据不同字段查询商品信息,并且分页
	 * @param id
	 * @param name
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectBypage(String id,String name,String cp,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	
	/**
	 * 展开商品的各种信息,并且保存最近浏览记录
	 * @param id
	 * @param ids
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void productdetail(String id,List<Integer> ids,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	
	/**
	 * 查看用户上传的商品
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void myProduct(String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 删除商品
	 * @param id
	 * @param uid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(String id,String uid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	
	/**
	 * 获取要更新的商品的信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateshow(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * 修改商品信息
	 * @param product
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void update(Product product,HttpServletRequest request, HttpServletResponse response) throws IOException;
	

	/**
	 * 查询大分类,用于增加小分类
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addSmall(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
	/**
	 * 执行增加小分类
	 * @param parentId
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void doAddSmall(String parentId,String name,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	/**
	 * 删除一个小分类
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void deleteSmall(String id,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/**
	 * 更新商品权限
	 * @param id
	 * @param valid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void updateValid(String id,String valid,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/**
	 * 查出拒绝的商品的信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void refuse(String id,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
	
	/**
	 * 执行拒绝商品上传
	 * @param id
	 * @param reason
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void doRefuse(String id,String reason,HttpServletRequest request, HttpServletResponse response) throws IOException;
}
