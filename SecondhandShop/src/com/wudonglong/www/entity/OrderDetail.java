package com.wudonglong.www.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable{
	private int id;
	private int orderid;
	private int pid;
	private int quantity;
	private int cost;
	
	public OrderDetail() {

	}
	
	
	public OrderDetail(int id, int orderid, int pid, int quantity, int cost) {
		this.id = id;
		this.orderid = orderid;
		this.pid = pid;
		this.quantity = quantity;
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	
}
