package com.major.dao.constants;

public class RiskStateTraceDaoConstants {
	public static final String TABLE_NAME = "risk_state_trace";
	public static final String INSERT_FIELDS = " risk_id,name,description";
	public static final String SELECT_FIELDS = " id,risk_id as riskId,name, description,create_time as createTime, update_time as updateTime";

	private RiskStateTraceDaoConstants() {

	}
}
