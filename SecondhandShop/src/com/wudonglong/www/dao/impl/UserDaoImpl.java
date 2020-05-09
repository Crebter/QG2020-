package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wudonglong.www.dao.UserDao;
import com.wudonglong.www.entity.User;
import com.wudonglong.www.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	/**
	 * @describe 分页查询用户
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@Override
	public ArrayList<User> queryUserByPage(int currentPage,int pageSize){
		ArrayList<User> users = new ArrayList<>();
		User user = null;
		ResultSet rs = null;
		String sql = "select * from user where limit ?,?";
		Object[] params = {(currentPage-1)*pageSize,pageSize};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
				user = new User(rs.getString("id"),				
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("sex"),
						rs.getString("birthday"),
						rs.getString("card"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("uaddress"),
						rs.getString("paddress"),
						rs.getInt("status"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
	}
	
	
	/**
	 * @describe 根据ID查询一个用户
	 * @param id
	 * @return
	 */
	@Override
	public User queryUserByID(String id) {
		String sql = "select * from user where id = ?";
		User user = null;
		Object[] params = {id};
		ResultSet rs = null;
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
				user = new User(rs.getString("id"),				
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("sex"),
						rs.getString("birthday"),
						rs.getString("card"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("uaddress"),
						rs.getString("paddress"),
						rs.getInt("status"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
	}
	
	
	/**
	 * @describe 查看是否有此人
	 * @param id
	 * @return
	 */
	@Override
	public int selectByName(String id){
		int count = 0;
		ResultSet rs = null;
		String sql = "select * from user where id = ?";
		Object[] params = {id};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()){
				count = rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return count;
	}
	
	
	
	 
	

	
	
	/**
	 * 判断账号密码是否正确，并且获得用户的所有信息
	 * @param name
	 * @param pwd
	 * @return
	 * @Date 2020-04-30
	 */
	@Override
	public User selectAdmin(String id,String password){
		User user=null;
		ResultSet rs = null;
		String sql = "select * from user where id=? and password=?";
		Object[]  params = {id,password};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()){
				user = new User(rs.getString("id"),rs.getString("username"),rs.getString("password"),rs.getString("sex"),rs.getString("birthday"),
								rs.getString("card"),rs.getString("email"),rs.getString("phone"),rs.getString("uaddress"),rs.getString("paddress"),rs.getInt("status"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return user;
	}
	
	
		/**
		 * 增加用户
		 * @param User
		 * @return
		 */
		@Override
		public boolean addUser(User user) {
			String sql = "insert into user (id,username,password,sex,birthday,card,email,phone,uaddress,paddress,status) values(?,?,?,?,?,?,?,?,?,?,?) ";
			Object[] params = {user.getId(), user.getUsername(), user.getPassword(),user.getSex(),user.getBirthday(),
							   user.getCard(),user.getEmail(),user.getPhone(),user.getUaddress(),user.getPaddress(),user.getStatus()};
			return DBUtil.executeUpdate(sql, params);
		}
		
		
		
		/**
		 * 根据ID修改用户的信息
		 * @param user
		 * @return
		 */
		@Override
		public boolean updateUserByID(User user) {
			String sql = "update user set username=?,password=?,sex=?,birthday=?,card=?,email=?,phone=?,uaddress=?,paddress=?,status=? where id=?";			
			Object[] params = {user.getUsername(), user.getPassword(),user.getSex(),user.getBirthday(),
					   user.getCard(),user.getEmail(),user.getPhone(),user.getUaddress(),user.getPaddress(),user.getStatus(),user.getId()};
			return DBUtil.executeUpdate(sql, params);
		}
		
		
		/**
		 * 根据ID删除用户
		 * @param user
		 * @return
		 */
		@Override
		public boolean deleteUserByID(String id) {
			String sql="delete from user where id=?";
			Object[] params = {id};
			return DBUtil.executeUpdate(sql, params);
		}
		
	
		/**
		 * 忘记密码
		 * @param id
		 * @param card
		 * @param email
		 * @return
		 */
		public User check(String id,String card,String email){
			User user=null;
			ResultSet rs = null;
			String sql = "select * from user where id=? and card=? and email=?";
			Object[]  params = {id,card,email};
			rs = DBUtil.excuteQuery(sql, params);
			try {
				while(rs.next()){
					user = new User(rs.getString("id"),rs.getString("username"),rs.getString("password"),rs.getString("sex"),rs.getString("birthday"),
									rs.getString("card"),rs.getString("email"),rs.getString("phone"),rs.getString("uaddress"),rs.getString("paddress"),rs.getInt("status"));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
			return user;
		}
		
	
		/**
		 * 更新密码
		 * @param user
		 * @return
		 */
		public boolean updatePassword(String id,String password) {
			String sql = "update user set password=? where id=?";			
			Object[] params = { password,id};
			return DBUtil.executeUpdate(sql, params);
		}
		
		
		/**
		 * 查询所有用户
		 * @return
		 */
		public ArrayList<User> selectAll(){
			ArrayList<User> users = new ArrayList<>();
			User user = null;
			ResultSet rs = null;
			String sql = "select * from user ";
			rs = DBUtil.excuteQuery(sql, null);
			try {
				while(rs.next()) {
					user = new User(rs.getString("id"),				
							rs.getString("username"),
							rs.getString("password"),
							rs.getString("sex"),
							rs.getString("birthday"),
							rs.getString("card"),
							rs.getString("email"),
							rs.getString("phone"),
							rs.getString("uaddress"),
							rs.getString("paddress"),
							rs.getInt("status"));
					users.add(user);
				}
				return users;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		}
	
	
}
