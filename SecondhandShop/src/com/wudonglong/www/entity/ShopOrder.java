package com.wudonglong.www.entity;

import java.io.Serializable;

public class ShopOrder implements Serializable{
	private int id;
	private String userid;
	private String username;
	private String useraddress;
	private String createtime;
	private int cost;
	private int status;
	private int type;
	

	public ShopOrder() {

	}
	
	public String getOrderStatusStr() {
		if(this.status == 1) {
			return "待审核";
		}else if(this.status == 2) {
			return "审核通过";
		}else if(this.status == 3) {
			return "配货中";
		}else if(this.status == 4) {
			return "发货";
		}else {
			return "确认收货";
		}
	}
	
	
	
	
	public ShopOrder(int id, String userid, String username, String useraddress, String createtime, int cost,
			int status, int type) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.useraddress = useraddress;
		this.createtime = createtime;
		this.cost = cost;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	
	
	
}
