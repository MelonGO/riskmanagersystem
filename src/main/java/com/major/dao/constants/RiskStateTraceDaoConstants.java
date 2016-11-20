package com.major.dao.constants;

public class RiskStateTraceDaoConstants {
	public static final String TABLE_NAME = "risk_state_trace";
	public static final String INSERT_FIELDS = " plan_risk_id,state,description";
	public static final String SELECT_FIELDS = " id,plan_risk_id as planRiskId,state, description,create_time as createTime, update_time as updateTime";

	private RiskStateTraceDaoConstants() {

	}
}
