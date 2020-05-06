package com.wudonglong.www.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String username;
	private String password;
	private String sex;
	private String birthday;
	private String card;
	private String email;
	private String phone;
	private String uaddress;
	private String paddress;
	private int status;
	
	
	
	public User() {
	}
	
	
	public User(String id,String password) {
		this.id = id;
		this.password = password;
	}
	
	
	
	
	
	public User(
			String id, String username, String password, String sex, String birthday, String card,
			String email,String phone, String uaddress, String paddress, int status) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.card = card;
		this.email = email;
		this.phone = phone;
		this.uaddress = uaddress;
		this.paddress = paddress;
		this.status = status;
	}
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
