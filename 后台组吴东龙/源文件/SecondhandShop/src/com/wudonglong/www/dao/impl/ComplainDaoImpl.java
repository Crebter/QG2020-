package com.wudonglong.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wudonglong.www.dao.ComplainDao;
import com.wudonglong.www.entity.Complain;
import com.wudonglong.www.util.DBUtil;

public class ComplainDaoImpl implements ComplainDao {
	
	/**
	 * 查出所有投诉信息
	 * @return
	 */
	public List<Complain> selectAll(){
		ResultSet rs = null;
		List<Complain> complains = new ArrayList<>();
		String sql = "select * from complain";
		rs = DBUtil.excuteQuery(sql, null);
		try {
				while(rs.next()) {
			
					Complain complain = new Complain(
							rs.getInt("id"),
							rs.getString("content"),
							rs.getString("contenttime"),
							rs.getString("reply"),
							rs.getString("replytime"),
							rs.getString("nickname"));
					complains.add(complain);
				}
			}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
			}
			return complains;
	}
	
	/**
	 * 根据ID查出投诉信息
	 * @param id
	 * @return
	 */
	public Complain selectById(int id) {
		Complain complain = null;
		ResultSet rs = null;
		String sql = "select * from complain where id=?";
		Object[] params = {id};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
				complain = new Complain(
						rs.getInt("id"),
						rs.getString("content"),
						rs.getString("contenttime"),
						rs.getString("reply"),
						rs.getString("replytime"),
						rs.getString("nickname"));
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return complain;
	}

	
	
	/**
	 * 添加信息
	 * @param complain
	 * @return
	 */
	public boolean insert(Complain complain){
		String sql="insert into complain (content,contenttime,reply,replytime,nickname) values(?,now(),?,?,?)";
		Object[] params={complain.getContent(),complain.getReply(),complain.getReplytime(),complain.getNickname()};
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 更新回复信息
	 * @param complain
	 * @return
	 */
	public boolean update(Complain complain){
		String sql="update complain set reply=?,replytime=now() where id=?";
		Object[] params={complain.getReply(),complain.getId()};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 删除投诉
	 * @param id
	 * @return
	 */
	public boolean delete(int id){
		String sql="delete from complain where id=?";
		Object[] params={id};
		return DBUtil.executeUpdate(sql, params);
	}


	
	
	/**
	 * 获得表的总页数
	 * @param count
	 * @return
	 */
	public int totalPage(int count){
		int tpage = 1;
		String sql = "select * from complain";
		int sum = DBUtil.getTatalCount(sql,null);
		if(sum % count == 0) {
			tpage = sum/count;
		}else {
			tpage = sum/count+1;
		}
		return tpage;
	}
	
	/**
	 * 分页查询全部
	 * @param cpage
	 * @param count
	 * @return
	 */
	public List<Complain> seleteByPage(int cpage,int count){
		List<Complain> complains=new ArrayList<Complain>();
		ResultSet rs=null;
		String sql = "select * from complain limit ?,?";
		Object[] params = {(cpage-1)*count,count};
		rs = DBUtil.excuteQuery(sql, params);
		try {
			while(rs.next()) {
		
				Complain complain = new Complain(
						rs.getInt("id"),
						rs.getString("content"),
						rs.getString("contenttime"),
						rs.getString("reply"),
						rs.getString("replytime"),
						rs.getString("nickname"));
				complains.add(complain);
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
		}
		return complains;
	}

	 


	/**
	 * 修改昵称,回复信息
	 * @param complain
	 * @return
	 */
	public boolean updateName(Complain complain){
		String sql="update complain set nickname=?,reply=? where id=?";
		Object[] params = {complain.getNickname(),complain.getReply(),complain.getId()};
		return DBUtil.executeUpdate(sql, params);
	}

}
	
	

