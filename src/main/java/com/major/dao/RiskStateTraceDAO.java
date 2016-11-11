package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.dao.constants.RiskStateTraceDaoConstants;
import com.major.model.RiskStateTrace;
@Mapper
public interface RiskStateTraceDAO {
	
	@Insert({ "insert into ", RiskStateTraceDaoConstants.TABLE_NAME, "(", RiskStateTraceDaoConstants.INSERT_FIELDS, ") values (#{riskId},#{name},#{description})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addRiskStateTrace(RiskStateTrace riskStateTrace);

	@Select({ "select ", RiskStateTraceDaoConstants.SELECT_FIELDS, " from ", RiskStateTraceDaoConstants.TABLE_NAME, " where id=#{id}" })
	RiskStateTrace selectById(int id);

	@Delete({ "delete from ", RiskStateTraceDaoConstants.TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", RiskStateTraceDaoConstants.SELECT_FIELDS, " from ", RiskStateTraceDaoConstants.TABLE_NAME })
	List<RiskStateTrace> selectAll();
	@Update({"update ", RiskStateTraceDaoConstants.TABLE_NAME, " set risk_id=#{riskId},name=#{name},description=#{description} where id=#{id}"})
	void updateRiskStateTrace(RiskStateTrace riskStateTrace);
	
	@Select({ "select ", RiskStateTraceDaoConstants.SELECT_FIELDS, " from ", RiskStateTraceDaoConstants.TABLE_NAME, " where risk_id=#{riskId}" })
	List<RiskStateTrace> getByRiskId(int riskId);

	
}
