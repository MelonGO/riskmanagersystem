package com.major.model;

public class RiskStateTrace {
	private int id;
	private int riskId;
	private String name;
	private String description;
	private String createTime;
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getriskId() {
		return riskId;
	}
	public void setriskId(int riskId) {
		this.riskId = riskId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
