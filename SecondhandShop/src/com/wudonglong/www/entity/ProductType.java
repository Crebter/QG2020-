package com.wudonglong.www.entity;

import java.io.Serializable;

public class ProductType implements Serializable{
	private int id;
	private String name;
	private int parentid;
	
	
	public ProductType() {

	}
	
	
	
	
	public ProductType(int id, String name, int parentid) {
		this.id = id;
		this.name = name;
		this.parentid = parentid;
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
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	
	
	
	
	
	
	
}
