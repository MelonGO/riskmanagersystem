package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import com.major.model.Risk;
@Mapper
public interface RiskDAO {
	String TABLE_NAME = "risk";
    String INSET_FIELDS = " project_id, type, content, probability, influence, triggerOrThreshold, "
    		+ "submitter, tracer ";
    String SELECT_FIELDS = " id, project_id as projectId, type, content, probability, influence, triggerOrThreshold, submitter, tracer ";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{projectId}, #{type},#{content},#{probability},#{influence},#{triggerOrThreshold},#{submitter},#{tracer})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addRisk(Risk risk);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Risk selectById(int id);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
    
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME})
    List<Risk> selectAll();
    @Update({"update ", TABLE_NAME, " set project_id=#{projectId},type=#{type},content=#{content},probability=#{probability},influence=#{influence},triggerOrThreshold=#{triggerOrThreshold},submitter=#{submitter},tracer=#{tracer}   where id=#{id}"})
    void updateRisk(Risk risk);
    
}
