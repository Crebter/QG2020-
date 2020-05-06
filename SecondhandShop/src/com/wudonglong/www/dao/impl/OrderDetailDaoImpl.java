package com.wudonglong.www.dao.impl;

import com.wudonglong.www.entity.OrderDetail;
import com.wudonglong.www.util.DBUtil;

public class OrderDetailDaoImpl {
	
	
	
	/**
	 * 增加商品的详情
	 * @param orderDetail
	 * @return
	 */
	public boolean add(OrderDetail orderDetail) {
		String sql="insert into orderdetail values(null,?,?,?,?)";
		Object[] params = {orderDetail.getOrderid(),orderDetail.getPid(),orderDetail.getQuantity(),orderDetail.getCost()};
		return DBUtil.executeUpdate(sql, params);
	}
}
