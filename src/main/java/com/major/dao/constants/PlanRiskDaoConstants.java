package com.major.dao.constants;

public class PlanRiskDaoConstants {
	public static final String TABLE_NAME = "plan_risk";
	public static final String INSERT_FIELDS = " plan_id,risk_id,type, content, probability, influence, triggerOrThreshold, "
			+ "submitter, tracer,state ";
	public static final String SELECT_FIELDS = " id, plan_id as planId,risk_id as riskId,type, content, probability, influence, triggerOrThreshold, submitter, tracer,state ,create_time as createTime, update_time as updateTime";

	private PlanRiskDaoConstants() {

	}
}
