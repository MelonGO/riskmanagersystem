package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.dao.constants.PlanRiskDaoConstants;
import com.major.model.PlanRisk;

@Mapper
public interface PlanRiskDAO {
	@Insert({ "insert into ", PlanRiskDaoConstants.TABLE_NAME, "(", PlanRiskDaoConstants.INSERT_FIELDS,
			") values (#{planId},#{riskId}, #{type},#{content},#{probability},#{influence},#{triggerOrThreshold},#{submitter},#{tracer})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addPlanRisk(PlanRisk planRisk);

	@Select({ "select ", PlanRiskDaoConstants.SELECT_FIELDS, " from ", PlanRiskDaoConstants.TABLE_NAME, " where id=#{id}" })
	PlanRisk selectById(int id);

	@Delete({ "delete from ", PlanRiskDaoConstants.TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", PlanRiskDaoConstants.SELECT_FIELDS, " from ", PlanRiskDaoConstants.TABLE_NAME })
	List<PlanRisk> selectAll();

	@Update({ "update ", PlanRiskDaoConstants.TABLE_NAME,
			" set plan_id=#{planId},risk_id=#{riskId},type=#{type},content=#{content},probability=#{probability},influence=#{influence},triggerOrThreshold=#{triggerOrThreshold},submitter=#{submitter},tracer=#{tracer}   where id=#{id}" })
	void updatePlanRisk(PlanRisk planRisk);

	@Select({ "select ", PlanRiskDaoConstants.SELECT_FIELDS, " from ", PlanRiskDaoConstants.TABLE_NAME,
			" where plan_id=#{planId}" })
	List<PlanRisk> getByPlanId(int planId);
}
