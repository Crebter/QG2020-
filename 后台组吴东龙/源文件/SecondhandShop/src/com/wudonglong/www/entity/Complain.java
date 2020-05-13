package com.wudonglong.www.entity;

import java.io.Serializable;

public class Complain implements Serializable{
	private int id;
	private String content;
	private String contenttime;
	private String reply;
	private String replytime;
	private String nickname;
	
	

	
	
	public Complain(int id, String content, String contenttime, String reply, String replytime, String nickname) {
		super();
		this.id = id;
		this.content = content;
		this.contenttime = contenttime;
		this.reply = reply;
		this.replytime = replytime;
		this.nickname = nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContenttime() {
		return contenttime;
	}
	public void setContenttime(String contenttime) {
		this.contenttime = contenttime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	
	
	
	
	
	
	
	
}
