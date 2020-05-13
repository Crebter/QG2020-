package com.wudonglong.www.entity;

import java.io.Serializable;

public class ShopOrder implements Serializable{
	private int id;
	private String uid;
	private String uaddress;
	private String createtime;
	private int cost;
	private int status;
	private int type;
	private String seller;
	private int quantity;
	private int pid;
	
	public ShopOrder() {

	}





	public ShopOrder(int id, String uid, String uaddress, String createtime, int cost, int status, int type,
			String seller, int quantity, int pid) {
		super();
		this.id = id;
		this.uid = uid;
		this.uaddress = uaddress;
		this.createtime = createtime;
		this.cost = cost;
		this.status = status;
		this.type = type;
		this.seller = seller;
		this.quantity = quantity;
		this.pid = pid;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
