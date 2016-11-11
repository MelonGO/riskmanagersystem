package com.major.dao;

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.dao.constants.ProjectDaoConstants;
import com.major.dao.constants.ProjectUserDaoConstants;
import com.major.dao.constants.UserDaoConstants;
import com.major.model.ProjectUser;
import com.major.model.User;
import com.major.model.Project;
@Mapper
public interface ProjectUserDAO {

	@Insert({ "insert into ", ProjectUserDaoConstants.TABLE_NAME, "(", ProjectUserDaoConstants.INSERT_FIELDS, ") values (#{projectId},#{userId})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addProjectUser(ProjectUser projectUser);

	@Select({ "select ", ProjectUserDaoConstants.SELECT_FIELDS, " from ", ProjectUserDaoConstants.TABLE_NAME, " where id=#{id}" })
	ProjectUser selectById(int id);

	@Delete({ "delete from ", ProjectUserDaoConstants.TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", ProjectUserDaoConstants.SELECT_FIELDS, " from ", ProjectUserDaoConstants.TABLE_NAME })
	List<ProjectUser> selectAll();
	@Update({"update ", ProjectUserDaoConstants.TABLE_NAME, " set project_id=#{projectId},user_id=#{userId}} where id=#{id}"})
	void updateRiskStateTrace(ProjectUser projectUser);
	
	@Select({ "select ",UserDaoConstants.SELECT_FIELDS_JOIN, " from ", ProjectUserDaoConstants.TABLE_NAME +" join "+UserDaoConstants.TABLE_NAME, " on user.id=project_user.user_id where project_id=#{projectId}" })
	List<User> getByProjectId(int projectId);
	
	@Select({ "select ", ProjectDaoConstants.SELECT_FIELDS_JOIN, " from ", ProjectUserDaoConstants.TABLE_NAME+" join "+ProjectDaoConstants.TABLE_NAME, "on project.id=project_user.project_id where user_id=#{userId}" })
	List<Project> getByUserId(int userId);
}
