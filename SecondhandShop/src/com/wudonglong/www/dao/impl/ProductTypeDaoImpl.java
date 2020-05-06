package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.dao.ProductTypeDao;
import com.wudonglong.www.entity.ProductType;
import com.wudonglong.www.util.DBUtil;

public class ProductTypeDaoImpl implements ProductTypeDao  {

	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<ProductType> selectAll(){
		List<ProductType> types = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from producttype";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
					ProductType type = new ProductType(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("parentid"));
						types.add(type);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return types;
	}
	
	
	
	
	/**
	 * 根据Id查询单个
	 * @param id
	 * @return
	 */
	public ProductType selectById(int id){
		ProductType type = null;
		ResultSet rs = null;
		String sql = "select * from producttype where id=?";
		Object[] params = {id};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					type = new ProductType(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("parentid"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return type;
	}
	
	/**
	 * 新增一个小分类,依附于大分类
	 * @param type
	 * @return
	 */
	public boolean insertSmall(ProductType type){
		String sql = "insert into producttype values(null,?,?)";
		Object[] params = {type.getName(),type.getParentid()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 新增一个大分类
	 * @param type
	 * @return
	 */
	public boolean insertBig(ProductType type){
		String sql = "insert into producttype values(null,?,0)";
		Object[] params = {type.getName()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 分局ID修改分类信息
	 * @param type
	 * @return
	 */
	public boolean update(ProductType type){
		String sql = "update  producttype set name=?,parentid=? where id=?";
		Object[] params = {type.getName(),type.getParentid(),type.getId()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 根据ID删除一个分类
	 * @param type
	 * @return
	 */
	public boolean delete(ProductType type){
		String sql = "delete from producttype where id=?";
		Object[] params = {type.getId()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 查询所有大分类
	 * @return
	 */
	public List<ProductType> selectBig(){
		List<ProductType> types = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from producttype where parentid = 0";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
					ProductType type = new ProductType(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("parentid"));
						types.add(type);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return types;
	}
	
	
	/**
	 * 查出所有小分类
	 * @return
	 */
	public List<ProductType> selectSmall(){
		List<ProductType> types = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from producttype where parentid!=0";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
					ProductType type = new ProductType(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("parentid"));
						types.add(type);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return types;
	}
	
}
