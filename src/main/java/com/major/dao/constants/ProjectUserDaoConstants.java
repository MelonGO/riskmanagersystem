package com.major.dao.constants;

public class ProjectUserDaoConstants {
	public static final String TABLE_NAME = "project_user";
	public static final String INSERT_FIELDS = " project_id,user_id ";
	public static final String SELECT_FIELDS = " id, project_id as projectId, user_id as userId, create_time as createTime, update_time as updateTime";
	private ProjectUserDaoConstants(){
		
	}
	
}
