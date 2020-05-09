package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.dao.ProductDao;
import com.wudonglong.www.entity.Product;
import com.wudonglong.www.util.DBUtil;

public class ProductDaoImpl implements ProductDao{
	
	/**
	 * 查询审核通过的商品
	 * @return
	 * @Date 2020-05-01
	 */
	public  List<Product> selectAll(){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where valid=1";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 根据商品名字查询审核通过的商品
	 * @param name
	 * @return
	 */
	public  List<Product> selectAllByName(String name){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where name like ? and valid=1";
		Object[] params = {"%"+name+"%"};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("introduction"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getInt("parentid"),
							rs.getInt("childid"),
							rs.getString("picture"),
							rs.getString("uid"),
							rs.getInt("valid"),
							rs.getString("reason"));
				products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
		
	}
	
	

	
	/**
	 * 根据ID查询审核通过的商品
	 * @param id
	 * @return
	 */
	public  Product selectById(int id) {
		Product product = null;
		ResultSet rs = null;
		String sql = "select * from product where id=?";
		Object[] params = {id};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
			product = new Product(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("introduction"),
					rs.getInt("price"),
					rs.getInt("stock"),
					rs.getInt("parentid"),
					rs.getInt("childid"),
					rs.getString("picture"),
					rs.getString("uid"),
					rs.getInt("valid"),
					rs.getString("reason"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return product;
	}
	
	
	/**
	 * 根据大分类查出审核通过的商品
	 * @param parentid
	 * @return
	 */
	public  List<Product> selectAllByParentId(int parentid){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where parentid=? and valid=1";
		Object[] params = {parentid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	
	/**
	 * 根据小分类查出审核通过的商品
	 * @param childid
	 * @return
	 */
	public  List<Product> selectAllByChildId(int childid){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where childid=? and valid=1";
		Object[] params = {childid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	/**
	 * 增加商品信息
	 * @param product
	 * @return
	 */
	public boolean insert(Product product) {
		String sql = "insert into product values(null,?,?,?,?,?,?,?,?,?,null)";
		Object[] params = {product.getName(),product.getIntroduction(),product.getPrice(),product.getStock(),product.getParentid(),product.getChildid(),product.getPicture(),product.getUid(),3};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	public  boolean update(Product product) {
			String sql = "update product set name=?,introduction=?,price=?,stock=?,parentid=?,childid=?,picture=?,uid=?,valid=? where id=?";
			Object[] params = {product.getName(),product.getIntroduction(),product.getPrice(),product.getStock(),product.getParentid(),product.getChildid(),product.getPicture(),product.getUid(),product.getValid(),product.getId()};
			return DBUtil.executeUpdate(sql, params);
		}
	
	
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public  boolean del(int id){							
		String sql = "delete from product where id=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	
	/**
	 * 根据页面大小获得product表的总页数
	 * @param count 页面大小
	 * @return
	 */
	public  int totalPage(int count){
		int tpage = 1;
		String sql = "select * from product where valid=1";
		int sum = DBUtil.getTatalCount(sql,null);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	
	/**
	 * 计算按大类分类商品的总页数
	 * @param count 页面大小
	 * @param parentid
	 * @return
	 */
	public  int totalPageByParentId(int count,int parentid){
		int tpage = 1;
		String sql = "select * from product where parentid=? and valid=1";
		Object[] params = {parentid};
		int sum = DBUtil.getTatalCount(sql,params);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	
	/**
	 * 计算按小类分类商品的总页数
	 * @param count 页面大小
	 * @param parentid
	 * @return
	 */
	public  int totalPageByChildId(int count,int childid){
		int tpage = 1;
		String sql = "select * from product where childid=? and valid=1";
		Object[] params = {childid};
		int sum = DBUtil.getTatalCount(sql,params);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	
	/**
	 * 计算按名字分类商品的总页数
	 * @param count 页面大小
	 * @param name	商品名字
	 * @return
	 */
	public  int totalPageByname(int count,String name){
		int tpage = 1;
		String sql = "select * from product where name=? and valid=1";
		Object[] params = {name};
		int sum = DBUtil.getTatalCount(sql,params);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	
	/**
	 * 分页查询所有商品
	 * @param cpage
	 * @param count
	 * @return
	 */
	public  List<Product> selectAll(int cpage,int count){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where valid=1 order by id desc limit ?,?";
		Object[] params = {count*(cpage-1),count};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 按大类分类查询商品
	 * @param cpage
	 * @param count
	 * @param parentid
	 * @return
	 */
	public  List<Product> selectAllByParentId(int cpage,int count,int parentid){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where parentid = ? and valid=1 limit ?,?";
		Object[] params = {parentid,count*(cpage-1),count};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 按小类分类查询商品
	 * @param cpage
	 * @param count
	 * @param childid
	 * @return
	 */
	public  List<Product> selectAllByChildId(int cpage,int count,int childid){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where childid = ? and valid=1 order by id desc limit ?,?";
		Object[] params = {childid,count*(cpage-1),count};
		rs = DBUtil.excuteQuery(sql, params);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 根据一个id数组查询单个商品的所有信息
	 * @param ids
	 * @return
	 */
	public  List<Product> selectAllById(List<Integer> ids){
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		try {
			for(int i=0;i<ids.size();i++) {
				String sql = "select * from product where id = ? and valid=1";
				Object[] params = {ids.get(i)};
				rs = DBUtil.excuteQuery(sql, params);
					while(rs.next()) {
							Product product = new Product(
									rs.getInt("id"),
									rs.getString("name"),
									rs.getString("introduction"),
									rs.getInt("price"),
									rs.getInt("stock"),
									rs.getInt("parentid"),
									rs.getInt("childid"),
									rs.getString("picture"),
									rs.getString("uid"),
									rs.getInt("valid"),
									rs.getString("reason"));
						products.add(product);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 查询低价商品
	 * @return
	 */
	public  List<Product> selectAllByT(){
			List<Product> products = new ArrayList<>();
			ResultSet rs = null;
			String sql = "select * from product where valid=1 order by price asc limit 0,8";
			rs = DBUtil.excuteQuery(sql, null);
			try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}finally {
					DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
				}
			return products;
	}
	
	/**
	 * 按库存排行查询
	 * @return
	 */
	public  List<Product> selectAllByStock(){
			List<Product> products = new ArrayList<>();
			ResultSet rs = null;
			String sql = "select * from product where valid=1 order by stock desc limit 0,12 ";
			rs = DBUtil.excuteQuery(sql, null);
			try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}finally {
					DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
				}
			return products;
	}
	
	/**
	 * 查询审核成功的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid1(String uid) {
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where uid=? and valid = 1";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("introduction"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getInt("parentid"),
							rs.getInt("childid"),
							rs.getString("picture"),
							rs.getString("uid"),
							rs.getInt("valid"),
							rs.getString("reason"));
				products.add(product);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	/**
	 * 查询审核失败的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid2(String uid) {
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where uid=? and valid = 2";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("introduction"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getInt("parentid"),
							rs.getInt("childid"),
							rs.getString("picture"),
							rs.getString("uid"),
							rs.getInt("valid"),
							rs.getString("reason"));
				products.add(product);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 查询正在审核的商品
	 * @param uid
	 * @return
	 */
	public List<Product> myProductValid3(String uid) {
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where uid=? and valid = 3";
		Object[] params = {uid};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("introduction"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getInt("parentid"),
							rs.getInt("childid"),
							rs.getString("picture"),
							rs.getString("uid"),
							rs.getInt("valid"),
							rs.getString("reason"));
				products.add(product);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	
	
	/**
	 * 减少商品库存
	 * @param stock
	 * @param id
	 * @return
	 */
	public boolean updateStock(int quantity,int id) {
		String sql ="update product set stock=stock-? where id=? and valid=1";
		Object[] params = {quantity,id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 取消订单,库存加上购买数量
	 * @param quantity
	 * @param id
	 * @return
	 */
	public boolean addStock(int quantity,int id) {
		String sql ="update product set stock=stock+? where id=? ";
		Object[] params = {quantity,id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	/**
	 * 查出所有待审核的商品
	 * @return
	 */
	public List<Product> selectAudit() {
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from product where valid=3";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("introduction"),
								rs.getInt("price"),
								rs.getInt("stock"),
								rs.getInt("parentid"),
								rs.getInt("childid"),
								rs.getString("picture"),
								rs.getString("uid"),
								rs.getInt("valid"),
								rs.getString("reason"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
		return products;
	}
	
	/**
	 * 更新商品信息
	 * @param id
	 * @param valid
	 * @return
	 */
	public boolean updateValid(int id,int valid) {
		String sql = "update product set valid=? where id=?";
		Object[] params = {valid,id};
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 拒绝商品上传
	 * @param id
	 * @param valid
	 * @param reason
	 * @return
	 */
	public boolean refuse(int id,int valid,String reason) {
		String sql = "update product set valid=?,reason=? where id=?";
		Object[] params = {valid,reason,id};
		return DBUtil.executeUpdate(sql, params);
	}
}
