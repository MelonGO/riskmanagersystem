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
	String table_name = "risk_state_trace";
	String insert_fields = " risk_id,name,description";
	String select_fields = " id,risk_id as riskId,name, description,create_time as createTime, update_time as updateTime";
	@Insert({ "insert into ", table_name, "(", insert_fields, ") values (#{riskId},#{name},#{description})" })
	@SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.PREPARED)
	int addRiskStateTrace(RiskStateTrace riskStateTrace);

	@Select({ "select ", select_fields, " from ", table_name, " where id=#{id}" })
	RiskStateTrace selectById(int id);

	@Delete({ "delete from ", table_name, " where id=#{id}" })
	void deleteById(int id);

	@Select({ "select ", select_fields, " from ", table_name })
	List<RiskStateTrace> selectAll();
	@Update({"update ", table_name, " set risk_id=#{riskId},name=#{name},description=#{description} where id=#{id}"})
	void updateRiskStateTrace(RiskStateTrace riskStateTrace);
	
	@Select({ "select ", select_fields, " from ", table_name, " where risk_id=#{riskId}" })
	List<RiskStateTrace> getByRiskId(int riskId);

	
}
