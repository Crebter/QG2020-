package com.wudonglong.www.dao;

import java.util.ArrayList;

import com.wudonglong.www.entity.User;


public interface UserDao {

	/**
	 * @describe 分页查询用户
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public ArrayList<User> queryUserByPage(int currentPage,int pageSize);
	
	
	/**
	 * @describe 根据ID查询一个用户
	 * @param id
	 * @return
	 */
	public User queryUserByID(String id);
	
	/**
	 * @describe 查看是否有此人
	 * @param id
	 * @return
	 */
	public int selectByName(String id);
	
	
	
	 
	

	
	
	
	/**
	 * 判断账号密码是否正确，并且获得用户的所有信息
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User selectAdmin(String id,String password);
	
		/**
		 * 增加用户
		 * @param User
		 * @return
		 */
		public boolean addUser(User user);
		
		
		
		/**
		 * 根据ID修改用户的信息
		 * @param user
		 * @return
		 */
		public boolean updateUserByID(User user);
		
		
		/**
		 * 根据ID删除用户
		 * @param user
		 * @return
		 */
		public boolean deleteUserByID(String id);
		
		
		/**
		 * 忘记密码
		 * @param id
		 * @param card
		 * @param email
		 * @return
		 */
		public User check(String id,String card,String email);

		

		/**
		 * 修改密码
		 * @param id
		 * @param password
		 * @return
		 */
		public boolean updatePassword(String id,String password);

		
	
	
}
