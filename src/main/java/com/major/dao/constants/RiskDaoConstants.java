package com.major.dao.constants;

public class RiskDaoConstants {
	public static final String TABLE_NAME = "risk";
	public static final String INSERT_FIELDS = " project_id, type, content, probability, influence, triggerOrThreshold, "
			+ "submitter, tracer ";
	public static final String SELECT_FIELDS = " id, project_id as projectId, type, content, probability, influence, triggerOrThreshold, submitter, tracer ,create_time as createTime, update_time as updateTime";

	private RiskDaoConstants() {

	}
}
