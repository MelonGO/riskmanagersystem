package com.major.dao.constants;

public class PlanDaoConstants {
	public static final String TABLE_NAME = "plan";
	public static final String INSERT_FIELDS = " project_id,name ";
	public static final String SELECT_FIELDS = " id, project_id as projectId,name ,create_time as createTime, update_time as updateTime";

	private PlanDaoConstants() {

	}
}
