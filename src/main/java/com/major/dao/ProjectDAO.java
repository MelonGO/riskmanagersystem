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

@Mapper
public interface ProjectDAO {
	String TABLE_NAME = "project";
	String INSET_FIELDS = " name ";
	String SELECT_FIELDS = " id, name, create_time as createTime, update_time as updateTime";

	@Insert({ "insert into ", TABLE_NAME, "(", INSET_FIELDS, ") values (#{name})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addProject(Project project);

	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}" })
	Project selectById(int id);

	@Delete({ "delete from ", TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME })
	List<Project> selectAll();
	@Update({"update ", TABLE_NAME, " set name=#{name} where id=#{id}"})
	void updateProject(Project project);
}
