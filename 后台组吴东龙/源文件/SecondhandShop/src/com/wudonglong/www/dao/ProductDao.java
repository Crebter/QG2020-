package com.wudonglong.www.dao;



import java.util.List;

import com.wudonglong.www.entity.Product;

public interface ProductDao {
	
	/**
	 * 查询审核通过的商品
	 * @return
	 * @Date 2020-05-01
	 */
	public  List<Product> selectAll();
	
	
	
	/**
	 * 根据商品名字查询审核通过的商品
	 * @param name
	 * @return
	 */
	public  List<Product> selectAllByName(String name);
	
	

	
	/**
	 * 根据ID查询审核通过的商品
	 * @param id
	 * @return
	 */
	public  Product selectById(int id);
	
	
	/**
	 * 根据大分类查出审核通过的商品
	 * @param parentid
	 * @return
	 */
	public  List<Product> selectAllByParentId(int parentid);
	
	
	
	
	/**
	 * 根据小分类查出审核通过的商品
	 * @param childid
	 * @return
	 */
	public  List<Product> selectAllByChildId(int childid);
	
	
	/**
	 * 增加商品信息
	 * @param product
	 * @return
	 */
	public boolean insert(Product product);
	
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	public  boolean update(Product product);
	
	
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public  boolean del(int id);
	
	
	/**
	 * 根据页面大小获得product表的总页数
	 * @param count 页面大小
	 * @return
	 */
	public  int totalPage(int count);
	
	
	/**
	 * 计算按大类分类商品的总页数
	 * @param count 页面大小
	 * @param parentid
	 * @return
	 */
	public  int totalPageByParentId(int count,int parentid);
	
	
	/**
	 * 计算按小类分类商品的总页数
	 * @param count 页面大小
	 * @param parentid
	 * @return
	 */
	public  int totalPageByChildId(int count,int childid);
	
	
	/**
	 * 计算按名字分类商品的总页数
	 * @param count 页面大小
	 * @param name	商品名字
	 * @return
	 */
	public  int totalPageByname(int count,String name);
	
	/**
	 * 分页查询所有商品
	 * @param cpage
	 * @param count
	 * @return
	 */
	public  List<Product> selectAll(int cpage,int count);
	
	
	
	/**
	 * 按大类分类查询商品
	 * @param cpage
	 * @param count
	 * @param parentid
	 * @return
	 */
	public  List<Product> selectAllByParentId(int cpage,int count,int parentid);
	
	
	
	/**
	 * 按小类分类查询商品
	 * @param cpage
	 * @param count
	 * @param childid
	 * @return
	 */
	public  List<Product> selectAllByChildId(int cpage,int count,int childid);
	
	
	/**
	 * 根据一个id数组查询单个商品的所有信息
	 * @param ids
	 * @return
	 */
	public  List<Product> selectAllById(List<Integer> ids);
	
	
	
	/**
	 * 查询低价商品
	 * @return
	 */
	public  List<Product> selectAllByT();
	
	/**
	 * 按库存排行查询
	 * @return
	 */
	public  List<Product> selectAllByStock();
	
	/**
	 * 查询审核成功的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid1(String uid);
	
	/**
	 * 查询审核失败的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid2(String uid);
	
	
	
	/**
	 * 查询正在审核的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid3(String uid);
	
	
	
	/**
	 * 减少商品库存
	 * @param stock
	 * @param id
	 * @return
	 */
	public boolean updateStock(int quantity,int id);
	
	/**
	 * 取消订单,库存加上购买数量
	 * @param quantity
	 * @param id
	 * @return
	 */
	public boolean addStock(int quantity,int id);
	
	/**
	 * 查出所有待审核的商品
	 * @return
	 */
	public List<Product> selectAudit();
	
	/**
	 * 更新商品信息
	 * @param id
	 * @param valid
	 * @return
	 */
	public boolean updateValid(int id,int valid);

	/**
	 * 拒绝商品上传
	 * @param id
	 * @param valid
	 * @param reason
	 * @return
	 */
	public boolean refuse(int id,int valid,String reason);

}
