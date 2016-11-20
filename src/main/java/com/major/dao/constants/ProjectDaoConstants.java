package com.major.dao.constants;

public class ProjectDaoConstants {
	public static final String TABLE_NAME = "project";
	public static final String INSERT_FIELDS = " name ";
	public static final String SELECT_FIELDS = " id, name, create_time as createTime, update_time as updateTime";
	public static final String SELECT_FIELDS_JOIN = "project.id, project.name, project.create_time as createTime, project.update_time as updateTime";
	private ProjectDaoConstants(){
		
	}
}
