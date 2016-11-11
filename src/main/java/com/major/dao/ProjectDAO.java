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
	String table_name = "project";
	String insert_fields = " name ";
	String select_fields = " id, name, create_time as createTime, update_time as updateTime";

	@Insert({ "insert into ", table_name, "(", insert_fields, ") values (#{name})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addProject(Project project);

	@Select({ "select ", select_fields, " from ", table_name, " where id=#{id}" })
	Project selectById(int id);

	@Delete({ "delete from ", table_name, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", select_fields, " from ", table_name })
	List<Project> selectAll();
	@Update({"update ", table_name, " set name=#{name} where id=#{id}"})
	void updateProject(Project project);
}
