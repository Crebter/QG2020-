package com.wudonglong.www.dao;


import java.util.List;

import com.wudonglong.www.entity.Complain;

public interface ComplainDao {
	/**
	 * 查出所有投诉信息
	 * @return
	 */
	public List<Complain> selectAll();
	
	/**
	 * 根据ID查出投诉信息
	 * @param id
	 * @return
	 */
	public Complain selectById(int id);

	
	
	/**
	 * 添加信息
	 * @param complain
	 * @return
	 */
	public boolean insert(Complain complain);

	/**
	 * 更新回复信息
	 * @param complain
	 * @return
	 */
	public boolean update(Complain complain);
	/**
	 * 删除投诉
	 * @param id
	 * @return
	 */
	public boolean delete(int id);


	
	
	/**
	 * 获得表的总页数
	 * @param count
	 * @return
	 */
	public int totalPage(int count);
	
	/**
	 * 分页查询全部
	 * @param cpage
	 * @param count
	 * @return
	 */
	public List<Complain> seleteByPage(int cpage,int count);

	 


	/**
	 * 修改昵称,回复信息
	 * @param complain
	 * @return
	 */
	public boolean updateName(Complain complain);
}
