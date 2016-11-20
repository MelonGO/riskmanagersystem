package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.major.model.PlanRiskList;


public interface PlanRiskListDAO {
	String table_name = "planRiskList";
    String insert_fields = " plan_id, risk_id ";
    String select_fields = " id,plan_id, risk_id";

    @Insert({"insert into ", table_name, "(", insert_fields,
            ") values (#{planId}, #{riskId})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addPlanRisk(PlanRiskList planRiskList);

    @Select({"select ", select_fields, " from ", table_name, " where id=#{id}"})
    PlanRiskList selectById(int id);

    @Delete({"delete from ", table_name, " where id=#{id}"})
    void deleteById(int id);

    @Delete({"delete from ", table_name, " where risk_id=#{riskId}"})
    void deleteByRiskId(int id);
    
    @Delete({"delete from ", table_name, " where plan_id=#{planId}"})
    void deleteByPlanId(int project_id);
  
    @Select({"select ", select_fields, " from ", table_name, " where plan_id=#{planId}"})
    List<PlanRiskList> getByPlanId(int planId);

}

