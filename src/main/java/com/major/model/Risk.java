package com.major.model;

public class Risk {
	private int id;
	private String name;
	private String content;
	private String probability;
	private String influence;
	private String triggerOrThreshold;
	private String submitter;
	private String stalker;
	private String date;
	
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
	
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	public String getStalker() {
		return stalker;
	}
	public void setStalker(String stalker) {
		this.stalker = stalker;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
