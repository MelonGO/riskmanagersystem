package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.model.Project;
import com.major.dao.constants.ProjectDaoConstants;
@Mapper
public interface ProjectDAO {
	
	@Insert({ "insert into ", ProjectDaoConstants.TABLE_NAME, "(", ProjectDaoConstants.INSERT_FIELDS, ") values (#{name})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addProject(Project project);

	@Select({ "select ", ProjectDaoConstants.SELECT_FIELDS, " from ", ProjectDaoConstants.TABLE_NAME, " where id=#{id}" })
	Project selectById(int id);

	@Delete({ "delete from ", ProjectDaoConstants.TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ",  ProjectDaoConstants.SELECT_FIELDS, " from ", ProjectDaoConstants.TABLE_NAME })
	List<Project> selectAll();
	@Update({"update ", ProjectDaoConstants.TABLE_NAME, " set name=#{name} where id=#{id}"})
	void updateProject(Project project);
}
