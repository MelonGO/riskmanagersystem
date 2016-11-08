package com.major.model;

public class Project {
	private int id;
	private String name;
	private String createTime;
	private String updateTime;
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
	public String getcreateTime() {
		return createTime;
	}
	public void setcreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getupdateTime() {
		return updateTime;
	}
	public void setupdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
