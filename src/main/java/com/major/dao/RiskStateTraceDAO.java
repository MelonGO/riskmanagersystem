package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import com.major.model.RiskStateTrace;
@Mapper
public interface RiskStateTraceDAO {
	String TABLE_NAME = "risk_state_trace";
	String INSET_FIELDS = " risk_id,name,description";
	String SELECT_FIELDS = " id,risk_id as riskId,name, description,create_time as createTime, update_time as updateTime";
	@Insert({ "insert into ", TABLE_NAME, "(", INSET_FIELDS, ") values (#{riskId},#{name},#{description})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addRiskStateTrace(RiskStateTrace riskStateTrace);

	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}" })
	RiskStateTrace selectById(int id);

	@Delete({ "delete from ", TABLE_NAME, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME })
	List<RiskStateTrace> selectAll();
	@Update({"update ", TABLE_NAME, " set risk_id=#{riskId},name=#{name},description=#{description} where id=#{id}"})
	void updateRiskStateTrace(RiskStateTrace riskStateTrace);
	
	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME, " where risk_id=#{riskId}" })
	RiskStateTrace getByRiskId(int riskId);

	
}
