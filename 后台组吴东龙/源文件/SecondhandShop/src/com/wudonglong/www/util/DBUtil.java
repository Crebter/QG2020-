package com.wudonglong.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//通用的数据操作方法

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/shop?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String username ="root";
	private static final String userpwd = "wudonglong";
	public static Connection con = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
	//创建一个集合，放置Connection，即连接池
	private static ArrayList<Connection> conList = new ArrayList<Connection>();
	
	
	//建立多【5】个Connection，便于后期调用
		static {
			for(int i=0;i<5;i++) {
				Connection con;
				try {
					con = createConnection();
					conList.add(con);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
	
		

		/**
		 * @describe 获得连接
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 * @Date 2020-04-29
		 */
		public static  Connection getConnection() throws ClassNotFoundException, SQLException {
			if(conList.isEmpty() == false) {
				Connection con = conList.get(0);//取得连接
				conList.remove(con);//当有一个人用的时候，其他人就不能用了
				return con;
			}else {
				//当集合里面没有连接的时候，新创建一个连接来用
				return createConnection();
			}
		}
		
		
		
		/**
		 * @describe 关闭连接(归还链接)
		 * @param con
		 */
		private static void closeConnection(Connection con) {
			//将Connection放回连接池就等于关闭
			conList.add(con);
		}
		
		
	
	/**
	 * 查询某个表的某个字段里面所持有的数据的总数
	 * @param sql
	 * @return
	 * @Date 2020-04-29
	 */
	public static int getTatalCount(String sql,Object[] params) {
		int count = 0;
		try {
			pstmt = createPreParedStatement(sql, params);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count++;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	
	
	/**
	 * 与数据库取得链接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @Date 2020-04-29
	 */
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return con = DriverManager.getConnection(url, username, userpwd);
	}
	
	
	/**
	 * 创建pstmt
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @Date 2020-04-29
	 */
	public static PreparedStatement createPreParedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		pstmt = getConnection().prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}				
		}
		return pstmt;
		
	}
	
	
	/**
	 * 关闭相关资源
	 * @param rs
	 * @param stmt
	 * @param con
	 * @Date 2020-04-29
	 */
	public static void closeAll(ResultSet rs,Statement stmt,Connection con) {
		try {
			if(rs!=null)
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt!=null)
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		try {
			if(con!=null)
			closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @describe 数据库通用的增删改
	 * @param sql
	 * @param params
	 * @return
	 * @Date 2020-04-29
	 */
	public static boolean executeUpdate(String sql,Object[] params) {
		try {
			pstmt = createPreParedStatement(sql,params);
			
			int count = pstmt.executeUpdate();
			if(count>0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
				closeAll(null, pstmt, con);
		}
	}

	
		/**
		 * @describe 数据库通用的查
		 * @param sql
		 * @param params
		 * @return
		 * @Date 2020-04-29
		 */
		public static ResultSet excuteQuery(String sql,Object[] params) {
				try {
					pstmt = createPreParedStatement(sql,params);
					rs = pstmt.executeQuery();
					return rs;//由于sql语句的条件错综复杂，所以通用代码只能返回结果集
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}

}
