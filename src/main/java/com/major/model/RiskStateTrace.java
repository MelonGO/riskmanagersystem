package com.major.model;

public class RiskStateTrace extends Base{

	private int planRiskId;
	private int state;
	private String description;


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
	
}
