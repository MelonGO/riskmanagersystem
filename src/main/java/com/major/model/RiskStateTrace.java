package com.major.model;

public class RiskStateTrace {
	private int id;
	private int planRiskId;
	private int state;
	private String description;
	private String createTime;
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlanRiskId() {
		return planRiskId;
	}
	public void setPlanRiskId(int planRiskId) {
		this.planRiskId = planRiskId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
