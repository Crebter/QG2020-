package com.wudonglong.www.dao;

import java.util.List;

import com.wudonglong.www.entity.ProductType;

public interface ProductTypeDao {

	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<ProductType> selectAll();
	
	
	
	
	/**
	 * 根据Id查询单个
	 * @param id
	 * @return
	 */
	public ProductType selectById(int id);
	
	/**
	 * 新增一个小分类,依附于大分类
	 * @param type
	 * @return
	 */
	public boolean insertSmall(ProductType type);
	
	/**
	 * 新增一个大分类
	 * @param type
	 * @return
	 */
	public boolean insertBig(ProductType type);
	/**
	 * 分局ID修改分类信息
	 * @param type
	 * @return
	 */
	public boolean update(ProductType type);
	
	/**
	 * 根据ID删除一个分类
	 * @param type
	 * @return
	 */
	public boolean delete(ProductType type);
	
	
	/**
	 * 查询所有大分类
	 * @return
	 */
	public List<ProductType> selectBig();
	
	
	/**
	 * 查出所有小分类
	 * @return
	 */
	public List<ProductType> selectSmall();
	
}
