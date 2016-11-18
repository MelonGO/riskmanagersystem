package com.major.model;

public class PlanRisk {
	private int id;
	private int planId;
	private int riskId;
	private String type;
	private String content;
	private String probability;
	private String influence;
	private String triggerOrThreshold;
	private Integer submitter;
	private Integer tracer;
	private int  state;
	private String createTime;
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getRiskId() {
		return riskId;
	}
	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getTriggerOrThreshold() {
		return triggerOrThreshold;
	}
	public void setTriggerOrThreshold(String triggerOrThreshold) {
		this.triggerOrThreshold = triggerOrThreshold;
	}
	public Integer getSubmitter() {
		return submitter;
	}
	public void setSubmitter(Integer submitter) {
		this.submitter = submitter;
	}
	public Integer getTracer() {
		return tracer;
	}
	public void setTracer(Integer tracer) {
		this.tracer = tracer;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
