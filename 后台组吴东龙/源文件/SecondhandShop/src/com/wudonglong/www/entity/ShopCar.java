package com.wudonglong.www.entity;

import java.io.Serializable;

public class ShopCar implements Serializable{
	private int id;
	private String picture;
	private String pname;
	private int price;
	private int quantity;
	private int stock;
	private int pid;
	private String uid;
	private int valid;
	private String seller;
	
	public ShopCar() {

	}
	
	
	
	
	
	
	
	
	
	
	
	public ShopCar(int id, String picture, String pname, int price, int quantity, int stock, int pid, String uid,
			int valid, String seller) {
		super();
		this.id = id;
		this.picture = picture;
		this.pname = pname;
		this.price = price;
		this.quantity = quantity;
		this.stock = stock;
		this.pid = pid;
		this.uid = uid;
		this.valid = valid;
		this.seller = seller;
	}











	public ShopCar(int id, String picture, String pname, int price, int quantity, int stock, int pid, String uid,int valid) {
//		super();
		this.id = id;
		this.picture = picture;
		this.pname = pname;
		this.price = price;
		this.quantity = quantity;
		this.stock = stock;
		this.pid = pid;
		this.uid = uid;
		this.valid = valid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}





	public String getSeller() {
		return seller;
	}





	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	
	
	
	
	
	
	
}
