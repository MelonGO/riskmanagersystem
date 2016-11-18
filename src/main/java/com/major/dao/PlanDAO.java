package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.dao.constants.PlanDaoConstants;
import com.major.model.Plan;
@Mapper
public interface PlanDAO {

	@Insert({ "insert into ", PlanDaoConstants.TABLE_NAME, "(", PlanDaoConstants.INSERT_FIELDS,
			") values (#{projectId},#{name})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addPlanRisk(Plan plan);

	@Select({ "select ", PlanDaoConstants.SELECT_FIELDS, " from ", PlanDaoConstants.TABLE_NAME, " where id=#{id}" })
	Plan selectById(int id);

	@Delete({ "delete from ", PlanDaoConstants.TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", PlanDaoConstants.SELECT_FIELDS, " from ", PlanDaoConstants.TABLE_NAME })
	List<Plan> selectAll();

	@Update({ "update ", PlanDaoConstants.TABLE_NAME,
			" set project_id=#{projectId},name=#{name}   where id=#{id}" })
	void updatePlan(Plan plan);

	@Select({ "select ", PlanDaoConstants.SELECT_FIELDS, " from ", PlanDaoConstants.TABLE_NAME,
			" where project_id=#{projectId}" })
	List<Plan> getByProjectId(int projectId);

}
