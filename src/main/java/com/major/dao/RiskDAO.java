package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import com.major.model.Risk;
@Mapper
public interface RiskDAO {
	String table_name = "risk";
    String insert_fields = " project_id, type, content, probability, influence, triggerOrThreshold, "
    		+ "submitter, tracer ";
    String select_fields = " id, project_id as projectId, type, content, probability, influence, triggerOrThreshold, submitter, tracer ,create_time as createTime, update_time as updateTime";

    @Insert({"insert into ", table_name, "(", insert_fields,
            ") values (#{projectId}, #{type},#{content},#{probability},#{influence},#{triggerOrThreshold},#{submitter},#{tracer})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addRisk(Risk risk);

    @Select({"select ", select_fields, " from ", table_name, " where id=#{id}"})
    Risk selectById(int id);

    @Delete({"delete from ", table_name, " where id=#{id}"})
    void deleteById(int id);
    
    @Select({"select ", select_fields, " from ", table_name})
    List<Risk> selectAll();
    @Update({"update ", table_name, " set project_id=#{projectId},type=#{type},content=#{content},probability=#{probability},influence=#{influence},triggerOrThreshold=#{triggerOrThreshold},submitter=#{submitter},tracer=#{tracer}   where id=#{id}"})
    void updateRisk(Risk risk);
    
    @Select({"select ", select_fields, " from ", table_name, " where project_id=#{projectId}"})
    List<Risk> getByProjectId(int projectId);
    
    
}
