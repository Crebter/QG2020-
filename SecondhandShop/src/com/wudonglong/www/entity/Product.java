package com.wudonglong.www.entity;

import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String name;
	private String introduction;
	private int price;
	private int stock;
	private int parentid;
	private int childid;
	private String picture;
	private String uid;//上传商品的用户
	private int valid;//审核是否 通过
	private String reason;//管理员返回的信息
	
	/*
	 * valid  
	 * 
	 * 1----审核通过
	 * 2----审核不通过
	 * 3----待审核
	 * 
	 */
	
	
	public Product() {

	}
	
	
	
	
	
	
	
	
	public Product(int id, String name, String introduction, int price, int stock, int parentid, int childid,
			String picture, String uid, int valid, String reason) {
		super();
		this.id = id;
		this.name = name;
		this.introduction = introduction;
		this.price = price;
		this.stock = stock;
		this.parentid = parentid;
		this.childid = childid;
		this.picture = picture;
		this.uid = uid;
		this.valid = valid;
		this.reason = reason;
	}








	public Product(int id, String name, String introduction, int price, int stock, int parentid, int childid,
			String picture) {
//		super();
		this.id = id;
		this.name = name;
		this.introduction = introduction;
		this.price = price;
		this.stock = stock;
		this.parentid = parentid;
		this.childid = childid;
		this.picture = picture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getChildid() {
		return childid;
	}
	public void setChildid(int childid) {
		this.childid = childid;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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






	public String getReason() {
		return reason;
	}






	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
	
	
	
	
}
