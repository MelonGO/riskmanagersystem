package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import com.major.dao.constants.RiskDaoConstants;
import com.major.model.Risk;
@Mapper
public interface RiskDAO {
    @Insert({"insert into ", RiskDaoConstants.TABLE_NAME, "(", RiskDaoConstants.INSERT_FIELDS,
            ") values (#{projectId}, #{type},#{content},#{probability},#{influence},#{triggerOrThreshold},#{submitter},#{tracer})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addRisk(Risk risk);

    @Select({"select ", RiskDaoConstants.SELECT_FIELDS, " from ", RiskDaoConstants.TABLE_NAME, " where id=#{id}"})
    Risk selectById(int id);

    @Delete({"delete from ", RiskDaoConstants.TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
    
    @Select({"select ", RiskDaoConstants.SELECT_FIELDS, " from ", RiskDaoConstants.TABLE_NAME})
    List<Risk> selectAll();
    @Update({"update ", RiskDaoConstants.TABLE_NAME, " set project_id=#{projectId},type=#{type},content=#{content},probability=#{probability},influence=#{influence},triggerOrThreshold=#{triggerOrThreshold},submitter=#{submitter},tracer=#{tracer}   where id=#{id}"})
    void updateRisk(Risk risk);
    
    @Select({"select ", RiskDaoConstants.SELECT_FIELDS, " from ", RiskDaoConstants.TABLE_NAME, " where project_id=#{projectId}"})
    List<Risk> getByProjectId(int projectId);
    
    
}
